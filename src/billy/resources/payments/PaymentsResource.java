package billy.resources.payments;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import billy.commands.CommandDispatcher;
import billy.commands.account.ApplyPaymentCommand;
import billy.models.PaymentModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/payments")
@Api(value = "Pagos")
public class PaymentsResource {

	@Inject
	private CommandDispatcher dispatcher;
	
	@Inject
	private PaymentRepository paymentRepository;
	
	@GET
	@Path("/{userId}")
	@ApiOperation(value = "Obtener los pagos de un usuario", response = PaymentModel.class, responseContainer = "Array")
	public Response get(@PathParam("userId") long userId) {
		return Response.ok(paymentRepository.getByUserId(userId)).build();
	}
	
	@POST
	@ApiOperation(value = "Agregar pago a un usuario")
	public Response post(ApplyPaymentRequest request) {
		dispatcher.dispatch(new ApplyPaymentCommand(request.getUserId(),
				request.getAmount(),
				request.getCurrency()));
		
		return Response.noContent().build();
	}
}
