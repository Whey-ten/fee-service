package sk.fmfi.resource;

import sk.fmfi.model.Fee;
import sk.fmfi.resource.dto.FeeDTO;
import sk.fmfi.service.FeeService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fee")
public class FeeResource {

    private final FeeService feeService;

    @Inject
    public FeeResource(FeeService feeService) {
        this.feeService = feeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response getFee(@QueryParam("acno") String acno) {
        List<Fee> fees;
        if (acno != null) {
            fees = feeService.getFeesForAcno(acno);
        } else {
            fees = feeService.getAllFees();
        }
        return Response.ok(fees.toString()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response createFee(FeeDTO feeDTO) {
        feeService.createFee(feeDTO.getTransactionId(), feeDTO.getAcno(), feeDTO.getAmount());
        return Response.ok().build();
    }

}
