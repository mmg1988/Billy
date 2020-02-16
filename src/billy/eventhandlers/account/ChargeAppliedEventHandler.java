package billy.eventhandlers.account;

import org.glassfish.hk2.api.ServiceLocator;

import com.google.common.eventbus.Subscribe;

import billy.events.account.ChargeAppliedEvent;
import billy.models.ChargeModel;
import billy.resources.accounts.AccountRepository;
import billy.resources.charges.ChargeRepository;

public class ChargeAppliedEventHandler {

	private AccountRepository accountRepository;
	private ChargeRepository chargeRepository;
	
	public ChargeAppliedEventHandler(ServiceLocator serviceLocator) {
		this.accountRepository = serviceLocator.createAndInitialize(AccountRepository.class);
		this.chargeRepository = serviceLocator.createAndInitialize(ChargeRepository.class);
	}
	
	@Subscribe
	public void handle(ChargeAppliedEvent event) {
		chargeRepository.add(new ChargeModel(
				event.getId(),
				event.getAggregateId(),
				event.getEventId(), 
				event.getAmount(),
				event.getCurrency(),
				event.getType(),
				event.getEventDate()));

		accountRepository.updateBalance(event.getAggregateId(), event.getBalance());
	}
}
