package billy.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InvoiceModel {

	private int period;
	private long userId;
	private List<ChargeModel> charges = new ArrayList<>();
	private List<PaymentModel> payments = new ArrayList<>();
	private BigDecimal amount;
	private BigDecimal paymentAmount;
	
	protected InvoiceModel() { }

	public InvoiceModel(long userId, int period, List<ChargeModel> charges, List<PaymentModel> payments) {
		super();
		this.period = period;
		this.userId = userId;
		this.charges = charges;
		this.payments = payments;
		this.amount = charges.stream().map(c -> c.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
		this.paymentAmount = payments.stream().map(c -> c.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
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

	public List<ChargeModel> getCharges() {
		return charges;
	}

	public void setCharges(List<ChargeModel> charges) {
		this.charges = charges;
	}

	public List<PaymentModel> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentModel> payments) {
		this.payments = payments;
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
