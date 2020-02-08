package billy.commands.account;

import javax.inject.Inject;

import billy.aggregates.account.Account;
import billy.aggregates.account.Payment;
import billy.commands.CommandHandler;
import billy.events.EventRepository;

public class ApplyPaymentCommandHandler implements CommandHandler<ApplyPaymentCommand, Void> {

	@Inject
	private EventRepository eventRepository;
	
	@Override
	public Void handle(ApplyPaymentCommand command) {
		Account account = eventRepository.get(Account.class, command.getUserId());
		account.addPayment(new Payment(command.getAmount(), command.getCurrency()));
		return null;
	}

}
