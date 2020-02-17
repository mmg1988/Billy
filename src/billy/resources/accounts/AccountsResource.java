package billy.resources.accounts;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import billy.models.AccountModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users/{id}")
@Api(value = "Usuarios")
public class AccountsResource {

	@Inject
	private AccountRepository accountRepository;
	
	@GET
	@ApiOperation(value = "Obtener el estado de un usuario", response = AccountModel.class)
	public Response get(@PathParam("id") long id) {
		return Response.ok(accountRepository.get(id)).build();
	}
}
