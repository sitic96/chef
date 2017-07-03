package rest;

import db.DBWorker;
import entity.Ingredient;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sitora on 05.02.17.
 */

@Path("/ingredient")
public class IngredientResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("{id}")
    public Response getIngredient(@PathParam("id") Integer id) {
        try {
            return Response.status(Response.Status.OK).entity(DBWorker.readByIdIngredient("Ingredient", "Ingredients", id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getAllIngredients() {
        GenericEntity<List<Ingredient>> entity = new GenericEntity<List<Ingredient>>(DBWorker.getAllIngredients("Ingredients", "Ingredient")) {
        };
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response addNewIngredient(Ingredient ingredient) {
        try {
            DBWorker.addNewIngredient(ingredient.getName());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response removeIngredient(Ingredient ingredient) {
        try {
            DBWorker.removeIngredient(ingredient.getId());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response updateIngredient(Ingredient ingredient) {
        try {
            DBWorker.updateIngredient(ingredient.getId(), ingredient.getName());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
