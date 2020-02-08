package billy.aggregates.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import billy.aggregates.Aggregate;
import billy.events.account.ChargeAppliedEvent;
import billy.events.account.PaymentAppliedEvent;

public class Account extends Aggregate {
	
	private long userId;
	private BigDecimal balance;
	private List<Charge> pendingCharges = new ArrayList<>();
	
	public Account(long id, long userId) {
		super(id);
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public Account addCharge(Charge charge) {
		addEvent(new ChargeAppliedEvent(this.getId(), new Date(), this.getVersion() + 1,
				charge.getEventId(),
				charge.getAmount(),
				charge.getCurrency(),
				charge.getType(),
				charge.getEventDate()));
		return this;
	}

	public Account addPayment(Payment payment) {
		BigDecimal remaining = payment.getAmount();
		List<Charge> paidCharges = new ArrayList<>();
		for(int i = 0; i < pendingCharges.size(); ++i) {
			Charge charge = pendingCharges.get(i);
			if (remaining.compareTo(BigDecimal.ZERO) <= 0) {
				break;
			}
			BigDecimal amount = charge.getBalance().compareTo(remaining) > 0 ? remaining : charge.getBalance();
			paidCharges.add(charge);
			remaining = remaining.subtract(amount);
		}
		addEvent(new PaymentAppliedEvent(this.getId(), new Date(), this.getVersion() + 1,
				payment.getAmount(),
				payment.getCurrency(),
				paidCharges));
		return this;
	}
	
	@SuppressWarnings("unused")
	private void apply(ChargeAppliedEvent event) {
		this.balance = this.balance.subtract(event.getAmount());
		this.pendingCharges.add(new Charge(
				event.getEventId(),
				event.getAmount(),
				event.getCurrency(),
				event.getType(),
				event.getEventDate()));
	}
	
	@SuppressWarnings("unused")
	private void apply(PaymentAppliedEvent event) {
		if (event.getAmount().compareTo(balance) > 0)
			throw new UnsupportedOperationException();
		
		this.balance = this.balance.add(event.getAmount());
		BigDecimal remaining = event.getAmount();
		List<Integer> completedCharges = new ArrayList<>();
		for(int i = 0; i < pendingCharges.size(); ++i) {
			Charge charge = pendingCharges.get(i);
			if (remaining.compareTo(BigDecimal.ZERO) <= 0) {
				break;
			}
			BigDecimal amount = charge.getBalance().compareTo(remaining) > 0 ? remaining : charge.getBalance();
			charge.setBalance(charge.getBalance().subtract(amount));
			remaining = remaining.subtract(amount);
			if (charge.getBalance().equals(BigDecimal.ZERO)) {
				completedCharges.add(i);
			}
		}
		completedCharges.forEach(i -> pendingCharges.remove(i.intValue()));
	}
	
}
