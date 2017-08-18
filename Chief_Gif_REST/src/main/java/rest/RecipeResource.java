package rest;

import DBUtils.RecipeManager;
import data.CompleteRecipe;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

/**
 * Created by sitora on 22.07.17.
 */
@Path("/recipes")
public class RecipeResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("byid/{id}")
    public Response getRecipeById(@PathParam("id") BigInteger id) {
        try {
            CompleteRecipe recipe = RecipeManager.class.newInstance().getRecipeById(id);
            return Response.status(Response.Status.OK).entity(recipe).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
