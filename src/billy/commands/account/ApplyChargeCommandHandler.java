package billy.commands.account;

import javax.inject.Inject;

import billy.aggregates.account.Account;
import billy.aggregates.account.Charge;
import billy.commands.CommandHandler;
import billy.events.EventRepository;

public class ApplyChargeCommandHandler implements CommandHandler<ApplyChargeCommand, Void> {

	@Inject
	private EventRepository eventRepository;
	
	@Override
	public Void handle(ApplyChargeCommand command) {
		Account account = eventRepository.get(Account.class, command.getUserId());
		account.addCharge(new Charge(command.getEventId(),
				command.getAmount(),
				command.getCurrency(),
				command.getType(),
				command.getEventDate()));
		return null;
	}

}
