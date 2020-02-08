package billy.resources.users;

import java.math.BigDecimal;

import billy.models.AccountModel;

public class AccountRepository {

	public AccountModel get(long userId) {
		return new AccountModel();
	}
	
	public void updateBalance(long userId, BigDecimal balance) {
		return;
	}
}
