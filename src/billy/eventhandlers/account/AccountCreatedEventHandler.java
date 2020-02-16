package billy.eventhandlers.account;

import org.glassfish.hk2.api.ServiceLocator;

import com.google.common.eventbus.Subscribe;

import billy.events.account.AccountCreatedEvent;
import billy.models.AccountModel;
import billy.resources.accounts.AccountRepository;

public class AccountCreatedEventHandler {

	private AccountRepository accountRepository;
	
	public AccountCreatedEventHandler(ServiceLocator serviceLocator) {
		this.accountRepository = serviceLocator.createAndInitialize(AccountRepository.class);
	}
	
	@Subscribe
	public void handle(AccountCreatedEvent event) {
		accountRepository.add(new AccountModel(event.getAggregateId(), event.getBalance()));
	}
}
