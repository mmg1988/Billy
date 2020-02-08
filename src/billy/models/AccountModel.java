package billy.models;

import java.math.BigDecimal;

public class AccountModel {
	
	private long id;
	private BigDecimal balance;
	
	public AccountModel() {
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
