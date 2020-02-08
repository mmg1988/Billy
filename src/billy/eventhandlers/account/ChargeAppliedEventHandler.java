package billy.eventhandlers.account;

import com.google.common.eventbus.Subscribe;

import billy.events.account.ChargeAppliedEvent;
import billy.models.ChargeModel;
import billy.resources.charges.ChargeRepository;

public class ChargeAppliedEventHandler {
	
	private ChargeRepository chargeRepository;
	
	public ChargeAppliedEventHandler(ChargeRepository chargeRepository) {
		this.chargeRepository = chargeRepository;
	}
	
	@Subscribe
	public void handle(ChargeAppliedEvent event) {
		chargeRepository.add(new ChargeModel(
				event.getAggregateId(),
				event.getEventId(), 
				event.getAmount(),
				event.getCurrency(),
				event.getType(),
				event.getEventDate()));
	}
}
