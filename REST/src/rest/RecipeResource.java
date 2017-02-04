package rest;

import db.DBWorker;
import entity.Entity;
import entity.Recipe;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sitora on 04.02.17.
 */
@Path("/recipes")
public class RecipeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Entity getRecipe(@PathParam("id") Integer id) {
        return DBWorker.readById("Recipe", "Recipes", id);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllRecipes() {
        GenericEntity<List<Recipe>> entity = new GenericEntity<List<Recipe>>(DBWorker.getAllRecipes("Recipes", "Recipe")) {
        };
        return Response.ok(entity).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNewRecipe(Recipe recipe) {
        DBWorker.addNewRecipe(recipe.getName(), recipe.getCategory(), recipe.getText());
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeRecipe(Recipe recipe) {
        DBWorker.removeRecipe(recipe.getId());
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCategory(Recipe recipe) {
        DBWorker.updateRecipe(recipe.getId(), recipe.getName(), recipe.getText(), recipe.getCategory());
    }
}
