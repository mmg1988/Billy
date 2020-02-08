package billy.events.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import billy.aggregates.account.Charge;
import billy.domain.Currency;
import billy.events.Event;

public class PaymentAppliedEvent extends Event {

	private BigDecimal amount;
	private Currency currency;
	private List<Charge> paidCharges = new ArrayList<>();

	public PaymentAppliedEvent(long aggregateId, Date timestamp, int version,
			BigDecimal amount, Currency currency, List<Charge> paidCharges) {
		super(aggregateId, timestamp, version);
		this.amount = amount;
		this.currency = currency;
		this.paidCharges = paidCharges;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public List<Charge> getPaidCharges() {
		return paidCharges;
	}
	
}
