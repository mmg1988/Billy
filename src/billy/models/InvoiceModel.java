package billy.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceModel {

	private long id;
	private int period;
	private long userId;
	private List<ChargeModel> charges = new ArrayList<>();
	private List<PaymentModel> payments = new ArrayList<>();
	private BigDecimal amount;

	public InvoiceModel(int period, long userId, List<ChargeModel> charges, List<PaymentModel> payments) {
		super();
		this.period = period;
		this.userId = userId;
		this.charges = charges;
		this.payments = payments;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
	
}
