package billy.resources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("")
public class StaticResource {
	@Context 
	private ServletContext ctx;
	
	@GET
	@Produces("text/html")
	public Response index() throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(ctx.getRealPath(""), "index.html")), StandardCharsets.UTF_8);
		content = content.replace("{{basePath}}", ctx.getContextPath() + "/");
		return Response.ok(content).build();
	}
	
	@GET
	@Path("/{resource}")
	public Response get(@PathParam("resource") String resource) {
		return Response.ok(getFile(resource)).build();
	}
	
	public File getFile(String resource) {
		return Paths.get(ctx.getRealPath(""), resource).normalize().toFile();
	}
}
