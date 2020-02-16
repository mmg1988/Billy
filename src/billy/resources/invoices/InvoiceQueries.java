package billy.resources.invoices;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import billy.models.ChargeModel;
import billy.models.InvoiceModel;
import billy.models.PaymentModel;
import billy.resources.charges.ChargeRepository;
import billy.resources.charges.ChargesSummary;
import billy.resources.payments.PaymentRepository;
import billy.resources.payments.PaymentsSummary;

public class InvoiceQueries {

	@Inject
	private ChargeRepository chargeRepository;
	
	@Inject
	private PaymentRepository paymentRepository;
	
	public List<InvoiceSummary> getAll(long userId) {
		List<ChargesSummary> charges = chargeRepository.getCountByPeriod(userId);
		List<PaymentsSummary> payments = paymentRepository.getCountByPeriod(userId);
		
		return charges.stream().map(c -> {
			PaymentsSummary paymentsSummary = payments.stream().filter(p -> p.getPeriod() == c.getPeriod())
					.findFirst()
					.orElse(new PaymentsSummary(userId, c.getPeriod(), 0, BigDecimal.ZERO));
			return new InvoiceSummary(userId, c.getPeriod(), c.getCount(), paymentsSummary.getCount(), c.getAmount(), paymentsSummary.getAmount());
		}).collect(Collectors.toList());
	}
	
	public InvoiceModel getPeriod(long userId, int period) {
		List<ChargeModel> charges = chargeRepository.getByPeriod(userId, period);
		List<PaymentModel> payments = paymentRepository.getByCharges(userId, charges.stream().map(c -> c.getId()).collect(Collectors.toList()));
		
		return new InvoiceModel(userId, period, charges, payments);
	}
}
