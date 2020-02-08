package billy.commands.account;

import java.math.BigDecimal;

import billy.commands.Command;
import billy.domain.Currency;

public class ApplyPaymentCommand implements Command<Void> {

	private long userId;
	private BigDecimal amount;
	private Currency currency;
	
	public ApplyPaymentCommand(long userId, BigDecimal amount, Currency currency) {
		this.userId = userId;
		this.amount = amount;
		this.currency = currency;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
}
