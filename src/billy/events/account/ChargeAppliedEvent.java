package billy.events.account;

import java.math.BigDecimal;
import java.util.Date;

import billy.domain.ChargeType;
import billy.domain.Currency;
import billy.events.Event;

public class ChargeAppliedEvent extends Event {

	private long eventId;
	private BigDecimal amount;
	private Currency currency;
	private ChargeType type;
	private Date eventDate;
	
	public ChargeAppliedEvent(long aggregateId, Date timestamp, int version, long eventId, 
			BigDecimal amount, Currency currency, ChargeType type, Date eventDate) {
		super(aggregateId, timestamp, version);
		this.eventId = eventId;
		this.amount = amount;
		this.currency = currency;
		this.type = type;
		this.eventDate = eventDate;
	}

	public long getEventId() {
		return eventId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public ChargeType getType() {
		return type;
	}

	public Date getEventDate() {
		return eventDate;
	}
	
}
