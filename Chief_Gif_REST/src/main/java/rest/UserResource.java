package rest;

import DBUtils.UserManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sitora on 19.07.17.
 */

@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("{login}")
    public Response getRecipe(@PathParam("login") String id) {
        try {
            return Response.status(Response.Status.OK).entity(UserManager.class.newInstance().get(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
