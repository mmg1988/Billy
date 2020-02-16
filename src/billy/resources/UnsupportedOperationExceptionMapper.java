package billy.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnsupportedOperationExceptionMapper implements ExceptionMapper<UnsupportedOperationException> {

	@Override
	public Response toResponse(UnsupportedOperationException exception) {
		return Response.status(Status.BAD_REQUEST).build();
	}

}
