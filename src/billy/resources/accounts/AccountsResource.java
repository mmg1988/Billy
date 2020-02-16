package billy.resources.accounts;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users/{id}")
public class AccountsResource {

	@Inject
	private AccountRepository accountRepository;
	
	@GET
	public Response get(@PathParam("id") long id) {
		return Response.ok(accountRepository.get(id)).build();
	}
}
