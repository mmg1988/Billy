package billy.resources.invoices;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InvoiceSummary {

	private long userId;
	private int period;
	private int chargesCount;
	private int paymentsCount;
	private BigDecimal amount;
	private BigDecimal paymentAmount;
	
	public InvoiceSummary() { }

	public InvoiceSummary(long userId, int period, int chargesCount, int paymentsCount, BigDecimal amount, BigDecimal paymentAmount) {
		super();
		this.userId = userId;
		this.period = period;
		this.chargesCount = chargesCount;
		this.paymentsCount = paymentsCount;
		this.amount = amount;
		this.paymentAmount = paymentAmount;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public void setPeriod(int period) {
		this.period = period;
	}
	
	public int getChargesCount() {
		return chargesCount;
	}
	
	public void setChargesCount(int chargesCount) {
		this.chargesCount = chargesCount;
	}
	
	public int getPaymentsCount() {
		return paymentsCount;
	}
	
	public void setPaymentsCount(int paymentsCount) {
		this.paymentsCount = paymentsCount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
}
