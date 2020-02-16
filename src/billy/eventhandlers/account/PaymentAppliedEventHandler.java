package billy.eventhandlers.account;

import org.glassfish.hk2.api.ServiceLocator;

import com.google.common.eventbus.Subscribe;

import billy.events.account.PaymentAppliedEvent;
import billy.models.PaymentModel;
import billy.resources.accounts.AccountRepository;
import billy.resources.payments.PaymentRepository;

public class PaymentAppliedEventHandler {
	
	private AccountRepository accountRepository;
	private PaymentRepository paymentRepository;
	
	public PaymentAppliedEventHandler(ServiceLocator serviceLocator) {
		this.accountRepository = serviceLocator.createAndInitialize(AccountRepository.class);;
		this.paymentRepository = serviceLocator.createAndInitialize(PaymentRepository.class);;
	}
	
	@Subscribe
	public void handle(PaymentAppliedEvent event) {
		paymentRepository.add(new PaymentModel(
				event.getAggregateId(),
				event.getAmount(),
				event.getCurrency(),
				event.getTimestamp(),
				event.getPaidCharges()));
		
		accountRepository.updateBalance(event.getAggregateId(), event.getBalance());
	}
}
