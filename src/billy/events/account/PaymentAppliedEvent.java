package billy.events.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import billy.domain.Currency;
import billy.events.Event;

public class PaymentAppliedEvent extends Event {

	private BigDecimal amount;
	private Currency currency;
	private BigDecimal balance;
	private List<UUID> paidCharges;
	
	protected PaymentAppliedEvent() {
	}

	public PaymentAppliedEvent(long aggregateId, Date timestamp, int version,
			BigDecimal amount, Currency currency, BigDecimal balance, List<UUID> paidCharges) {
		super(aggregateId, timestamp, version);
		this.amount = amount;
		this.currency = currency;
		this.balance = balance;
		this.paidCharges = paidCharges;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public List<UUID> getPaidCharges() {
		return paidCharges;
	}

	public void setPaidCharges(List<UUID> paidCharges) {
		this.paidCharges = paidCharges;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
