package billy.aggregates.account;

import java.math.BigDecimal;
import billy.domain.Currency;

public class Payment {

	private BigDecimal amount;
	private Currency currency;

	public Payment(BigDecimal amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
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
