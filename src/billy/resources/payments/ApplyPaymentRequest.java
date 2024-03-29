package billy.resources.payments;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import billy.domain.Currency;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplyPaymentRequest {

	private long userId;
	private BigDecimal amount;
	private Currency currency;
	
	public ApplyPaymentRequest() {
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
}
