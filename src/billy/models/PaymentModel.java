package billy.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import billy.domain.Currency;
import billy.domain.InvoiceUtils;
import io.swagger.annotations.ApiModel;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel()
public class PaymentModel {

	private int period;
	private long userId;
	private BigDecimal amount;
	private Currency currency;
	private Date timestamp;
	private List<UUID> charges = new ArrayList<>();
	
	protected PaymentModel() { }
	
	public PaymentModel(long userId, BigDecimal amount, Currency currency, Date timestamp, List<UUID> charges) {
		this.userId = userId;
		this.amount = amount;
		this.currency = currency;
		this.timestamp = timestamp;
		this.charges = charges;
		this.period = InvoiceUtils.getPeriod(timestamp);
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
	
	public List<UUID> getCharges() {
		return charges;
	}
	
	public void setCharges(List<UUID> charges) {
		this.charges = charges;
	}
	
}
