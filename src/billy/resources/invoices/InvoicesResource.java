package billy.resources.invoices;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import billy.models.InvoiceModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users/{id}/invoices")
@Api(value = "Facturas")
public class InvoicesResource {

	@Inject
	private InvoiceQueries invoiceQueries;
	
	@GET
	@ApiOperation(value = "Obtener facturas de un usuario", response = InvoiceSummary.class, responseContainer = "Array")
	public Response get(@PathParam("id") long id) {
		return Response.ok(invoiceQueries.getAll(id)).build();
	}
	
	@GET
	@Path("/{period}")
	@ApiOperation(value = "Obtener factura por usuario y periodo", response = InvoiceModel.class)
	public Response get(@PathParam("id") long id, @PathParam("period") int period) {
		return Response.ok(invoiceQueries.getPeriod(id, period)).build();
	}
}
