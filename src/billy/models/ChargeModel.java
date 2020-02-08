package billy.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import billy.domain.ChargeType;
import billy.domain.Currency;

public class ChargeModel {

	private int period;
	private long userId;
	private long eventId;
	private BigDecimal amount;
	private Currency currency;
	private ChargeType type;
	private Date eventDate;
	private List<PaymentModel> payments = new ArrayList<>();
	
	public ChargeModel(long userId, long eventId, BigDecimal amount, Currency currency, ChargeType type, Date eventDate) {
		this.userId = userId;
		this.eventId = eventId;
		this.amount = amount;
		this.currency = currency;
		this.type = type;
		this.eventDate = eventDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(eventDate);
		this.period = calendar.get(Calendar.YEAR) * 100 + calendar.get(Calendar.MONTH) + 1;
	}
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
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
	
	public List<PaymentModel> getPayments() {
		return payments;
	}
	
	public void setPayments(List<PaymentModel> payments) {
		this.payments = payments;
	}
}
