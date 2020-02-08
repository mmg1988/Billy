package billy.resources.charges;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import billy.commands.CommandDispatcher;
import billy.commands.account.ApplyChargeCommand;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/charges")
public class ChargesResource {

	private CommandDispatcher dispatcher;
	
	@POST
	public Response post(ApplyChargeRequest request) {
		dispatcher.dispatch(new ApplyChargeCommand(request.getEventId(), 
				request.getAmount(), 
				request.getCurrency(),
				request.getUserId(),
				request.getEventType(),
				request.getDate()));
		
		return Response.noContent().build();
	}
}
