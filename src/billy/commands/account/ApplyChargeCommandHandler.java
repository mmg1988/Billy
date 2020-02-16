package billy.commands.account;

import java.util.UUID;

import javax.inject.Inject;

import com.google.common.eventbus.Subscribe;

import billy.aggregates.account.Account;
import billy.aggregates.account.Charge;
import billy.commands.CommandHandler;
import billy.events.EventRepository;

public class ApplyChargeCommandHandler implements CommandHandler<ApplyChargeCommand, Void> {

	private EventRepository eventRepository;
	
	@Inject
	public ApplyChargeCommandHandler(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@Override
	@Subscribe
	public Void handle(ApplyChargeCommand command) {
		Account account = eventRepository.get(Account.class, command.getUserId());
		account.addCharge(new Charge(
				UUID.randomUUID(),
				command.getEventId(),
				command.getAmount(),
				command.getCurrency(),
				command.getType(),
				command.getEventDate()));

		eventRepository.save(account);
		return null;
	}

}
