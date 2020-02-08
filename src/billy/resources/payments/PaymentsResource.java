package billy.resources.payments;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import billy.commands.CommandDispatcher;
import billy.commands.account.ApplyPaymentCommand;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/payments")
public class PaymentsResource {

	private CommandDispatcher dispatcher;
	
	@POST
	public Response post(ApplyPaymentRequest request) {
		dispatcher.dispatch(new ApplyPaymentCommand(request.getUserId(),
				request.getAmount(),
				request.getCurrency()));
		
		return Response.noContent().build();
	}
}
