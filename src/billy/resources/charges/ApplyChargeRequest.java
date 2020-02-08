package billy.resources.charges;

import java.math.BigDecimal;
import java.util.Date;

import billy.domain.ChargeType;
import billy.domain.Currency;

public class ApplyChargeRequest {

	private long eventId;
	private long userId;
	private BigDecimal amount;
	private Currency currency;
	private ChargeType eventType;
	private Date date;
	
	public ApplyChargeRequest() {
		
	}
	
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public ChargeType getEventType() {
		return eventType;
	}
	public void setEventType(ChargeType eventType) {
		this.eventType = eventType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
