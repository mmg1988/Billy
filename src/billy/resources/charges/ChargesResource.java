package billy.resources.charges;

import java.util.Arrays;

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
import billy.commands.account.ApplyChargeCommand;
import billy.domain.ChargeType;
import billy.models.ChargeModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/charges")
@Api(value = "Cargos")
public class ChargesResource {

	@Inject
	private CommandDispatcher dispatcher;
	
	@Inject
	private ChargeRepository chargeRepository;
	
	@GET
	@Path("/{userId}")
	@ApiOperation(value = "Obtener los cargos de un usuario", response = ChargeModel.class, responseContainer = "Array")
	public Response get(@PathParam("userId") long userId) {
		return Response.ok(chargeRepository.getByUserId(userId)).build();
	}
	
	@POST
	@ApiOperation(value = "Agregar cargo a un usuario")
	public Response post(ApplyChargeRequest request) {
		dispatcher.dispatch(new ApplyChargeCommand(request.getEventId(), 
				request.getAmount(), 
				request.getCurrency(),
				request.getUserId(),
				Arrays.stream(ChargeType.values())
					.filter(v -> v.getType().equalsIgnoreCase(request.getEventType()))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("unknown value: " + request.getEventType())),
				request.getDate()));
		
		return Response.noContent().build();
	}
}
