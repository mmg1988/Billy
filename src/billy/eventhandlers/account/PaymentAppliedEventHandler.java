package billy.eventhandlers.account;

import java.util.ArrayList;

import com.google.common.eventbus.Subscribe;

import billy.events.account.ChargeAppliedEvent;
import billy.events.account.PaymentAppliedEvent;
import billy.models.ChargeModel;
import billy.models.PaymentModel;
import billy.resources.charges.ChargeRepository;
import billy.resources.payments.PaymentRepository;

public class PaymentAppliedEventHandler {
	
	private ChargeRepository chargeRepository;
	private PaymentRepository paymentRepository;
	
	public PaymentAppliedEventHandler(ChargeRepository chargeRepository, PaymentRepository paymentRepository) {
		this.chargeRepository = chargeRepository;
		this.paymentRepository = paymentRepository;
	}
	
	@Subscribe
	public void handle(PaymentAppliedEvent event) {
		paymentRepository.add(new PaymentModel(
				event.getAggregateId(),
				event.getAmount(),
				event.getCurrency(),
				event.getTimestamp(),
				new ArrayList<ChargeModel>()));
	}
}
