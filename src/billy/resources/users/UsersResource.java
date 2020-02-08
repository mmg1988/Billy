package billy.resources.users;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users/{id}")
public class UsersResource {

	private AccountRepository accountRepository;
	
	public Response get(@PathParam("id") long userId) {
		return Response.ok(accountRepository.get(userId)).build();
	}
}
