package billy.aggregates.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import billy.domain.ChargeType;
import billy.domain.Currency;

public class Charge {

	private UUID id;
	private long eventId;
	private BigDecimal amount;
	private Currency currency;
	private ChargeType type;
	private Date eventDate;
	private BigDecimal balance;
	
	protected Charge() {
	}

	public Charge(UUID id, long eventId, BigDecimal amount, Currency currency, ChargeType type, Date eventDate) {
		this.id = id;
		this.eventId = eventId;
		this.amount = amount;
		this.currency = currency;
		this.type = type;
		this.eventDate = eventDate;
		this.balance = amount;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public long getEventId() {
		return eventId;
	}
	
	public void setEventId(long eventId) {
		this.eventId = eventId;
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
	
	public ChargeType getType() {
		return type;
	}
	
	public void setType(ChargeType type) {
		this.type = type;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
