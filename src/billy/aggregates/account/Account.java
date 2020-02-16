package billy.aggregates.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import billy.aggregates.Aggregate;
import billy.domain.Currency;
import billy.events.Event;
import billy.events.account.AccountCreatedEvent;
import billy.events.account.ChargeAppliedEvent;
import billy.events.account.PaymentAppliedEvent;

public class Account extends Aggregate {
	
	private BigDecimal balance;
	private List<Charge> pendingCharges;
	
	public Account(long id, List<Event> events) {
		super(id, events);
		
		// Si no tiene eventos es porque es una cuenta nueva
		if (events.size() == 0) {
			addEvent(new AccountCreatedEvent(id, new Date(), this.getVersion() + 1, BigDecimal.ZERO));
		}
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	public Account addCharge(Charge charge) {
		if (charge.getCurrency() != Currency.ARS)
			throw new UnsupportedOperationException();
		
		BigDecimal newBalance = balance.subtract(charge.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
		addEvent(new ChargeAppliedEvent(this.getId(), new Date(), this.getVersion() + 1,
				charge.getId(),
				charge.getEventId(),
				charge.getAmount(),
				charge.getCurrency(),
				charge.getType(),
				charge.getEventDate(),
				newBalance));
		return this;
	}

	public Account addPayment(Payment payment) {
		if (payment.getCurrency() != Currency.ARS)
			throw new UnsupportedOperationException();
		
		if (payment.getAmount().add(balance).compareTo(BigDecimal.ZERO) > 0)
			throw new UnsupportedOperationException();
		
		BigDecimal newBalance = balance.add(payment.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		// Me fijo que cargos hay pendientes que se pueden pagar con el pago que ingresa par asociarlos
		BigDecimal remaining = payment.getAmount();
		List<UUID> paidCharges = new ArrayList<>();
		for(int i = 0; i < pendingCharges.size(); ++i) {
			Charge charge = pendingCharges.get(i);
			if (remaining.compareTo(BigDecimal.ZERO) <= 0) {
				break;
			}
			BigDecimal amount = charge.getBalance().compareTo(remaining) > 0 ? remaining : charge.getBalance();
			paidCharges.add(charge.getId());
			remaining = remaining.subtract(amount);
		}
		addEvent(new PaymentAppliedEvent(this.getId(), new Date(), this.getVersion() + 1,
				payment.getAmount(),
				payment.getCurrency(),
				newBalance,
				paidCharges));
		return this;
	}
	
	@SuppressWarnings("unused")
	private void apply(AccountCreatedEvent event) {
		this.balance = event.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
		this.pendingCharges = new ArrayList<>();
	}
	
	@SuppressWarnings("unused")
	private void apply(ChargeAppliedEvent event) {
		this.balance = event.getBalance();
		
		this.pendingCharges.add(new Charge(
				event.getId(),
				event.getEventId(),
				event.getAmount(),
				event.getCurrency(),
				event.getType(),
				event.getEventDate()));
	}
	
	@SuppressWarnings("unused")
	private void apply(PaymentAppliedEvent event) {
		this.balance = event.getBalance();
		
		// Actualizo los balances de los cargos asociados
		BigDecimal remaining = event.getAmount();
		List<Charge> completedCharges = new ArrayList<>();
		for(int i = 0; i < pendingCharges.size(); ++i) {
			Charge charge = pendingCharges.get(i);
			
			// Si el pago ya no tiene remanente salgo
			if (remaining.compareTo(BigDecimal.ZERO) <= 0) {
				break;
			}
			
			// Actualizo los balances
			BigDecimal amount = charge.getBalance().compareTo(remaining) > 0 ? remaining : charge.getBalance();
			charge.setBalance(charge.getBalance().subtract(amount));
			remaining = remaining.subtract(amount);
			
			// Me fijo si el cargo esta completamente pagado
			if (charge.getBalance().equals(BigDecimal.ZERO)) {
				completedCharges.add(charge);
			}
		}
		// Lo saco de la lista de pendientes
		completedCharges.forEach(c -> pendingCharges.remove(c));
	}
	
}
