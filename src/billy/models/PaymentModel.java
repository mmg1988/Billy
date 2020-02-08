package billy.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import billy.domain.Currency;

public class PaymentModel {

	private int period;
	private long userId;
	private BigDecimal amount;
	private Currency currency;
	private Date timestamp;
	private List<ChargeModel> charges = new ArrayList<>();
	
	public PaymentModel(long userId, BigDecimal amount, Currency currency, Date timestamp, List<ChargeModel> charges) {
		this.userId = userId;
		this.amount = amount;
		this.currency = currency;
		this.timestamp = timestamp;
		this.charges = charges;
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
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public List<ChargeModel> getCharges() {
		return charges;
	}
	
	public void setCharges(List<ChargeModel> charges) {
		this.charges = charges;
	}
	
}
