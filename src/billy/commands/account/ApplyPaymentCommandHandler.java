package billy.commands.account;

import javax.inject.Inject;

import com.google.common.eventbus.Subscribe;

import billy.aggregates.account.Account;
import billy.aggregates.account.Payment;
import billy.commands.CommandHandler;
import billy.events.EventRepository;

public class ApplyPaymentCommandHandler implements CommandHandler<ApplyPaymentCommand, Void> {

	private EventRepository eventRepository;
	
	@Inject
	public ApplyPaymentCommandHandler(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@Override
	@Subscribe
	public Void handle(ApplyPaymentCommand command) {
		Account account = eventRepository.get(Account.class, command.getUserId());
		account.addPayment(new Payment(command.getAmount(), command.getCurrency()));
		
		eventRepository.save(account);
		return null;
	}

}
