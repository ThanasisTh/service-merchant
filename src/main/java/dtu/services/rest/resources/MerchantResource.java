package dtu.services.rest.resources;

import core.MerchantPool;
import dtupay.DtuPayMerchantRepresentation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api/merchant")
public class MerchantResource
{
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") String id)
    {
        DtuPayMerchantRepresentation merchant = MerchantPool.get(id);
        if (merchant == null)
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
        else
        {
            return Response.status(Response.Status.OK).entity(merchant).build();
        }
    }


    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()
    {
        if (MerchantPool.getAll().isEmpty())
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
        else
        {
            List<DtuPayMerchantRepresentation> merchants = new ArrayList<>(MerchantPool.getAll());
            return Response.status(Response.Status.OK).entity(merchants).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(DtuPayMerchantRepresentation m)
    {
        if (MerchantPool.create(m))
        {
            DtuPayMerchantRepresentation merchant = MerchantPool.get(m.getUuid());
            return Response.status(Response.Status.CREATED).entity(merchant).build();
        }
        else
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(DtuPayMerchantRepresentation m)
    {
        if (MerchantPool.update(m))
        {
            DtuPayMerchantRepresentation merchant = MerchantPool.get(m.getUuid());
            return Response.status(Response.Status.ACCEPTED).entity(merchant).build();
        }
        else
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String cpr)
    {
        if (!MerchantPool.delete(cpr))
        {
            return Response.status(Response.Status.NOT_FOUND).entity(false).build();
        }
        else
        {
            return Response.status(Response.Status.OK).entity(true).build();
        }
    }
}
