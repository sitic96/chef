package rest;

import db.DBWorker;
import entity.Entity;
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
    @Produces(MediaType.APPLICATION_XML)
    @Path("{id}")
    public Entity getIngredient(@PathParam("id") Integer id) {
        return DBWorker.readById("Ingredient", "Ingredients", id);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllIngredients() {
        GenericEntity<List<Ingredient>> entity = new GenericEntity<List<Ingredient>>(DBWorker.getAllIngredients("Ingredients", "Ingredient")) {
        };
        return Response.ok(entity).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNewIngredient(Ingredient ingredient) {
        DBWorker.addNewIngredient(ingredient.getName());
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeIngredient(Ingredient ingredient) {
        DBWorker.removeIngredient(ingredient.getId());
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateIngredient(Ingredient ingredient) {
        DBWorker.updateIngredient(ingredient.getId(), ingredient.getName());
    }
}