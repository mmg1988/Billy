package billy.events.account;

import java.math.BigDecimal;
import java.util.Date;

import billy.events.Event;

public class AccountCreatedEvent extends Event {

	private BigDecimal balance;
	
	protected AccountCreatedEvent() {
	}

	public AccountCreatedEvent(long aggregateId, Date timestamp, int version,
			BigDecimal balance) {
		super(aggregateId, timestamp, version);
		this.balance = balance;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
