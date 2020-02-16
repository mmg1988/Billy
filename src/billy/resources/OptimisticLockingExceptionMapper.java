package billy.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import billy.events.OptimisticLockingException;

@Provider
public class OptimisticLockingExceptionMapper implements ExceptionMapper<OptimisticLockingException> {

	@Override
	public Response toResponse(OptimisticLockingException exception) {
		return Response.status(Status.CONFLICT).build();
	}

}
