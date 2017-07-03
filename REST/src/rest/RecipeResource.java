package rest;

import db.DBWorker;
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
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("{id}")
    public Response getRecipe(@PathParam("id") Integer id) {
        try {
            return Response.status(Response.Status.OK).entity(DBWorker.readByIdRecipe("Recipe", "Recipes", id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getAllRecipes() {
        try {
            GenericEntity<List<Recipe>> entity = new GenericEntity<List<Recipe>>(DBWorker.getAllRecipes("Recipes", "Recipe")) {
            };
            return Response.status(Response.Status.OK).entity(entity).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response addNewRecipe(Recipe recipe) {
        try {
            DBWorker.addNewRecipe(recipe.getName(), recipe.getCategory(), recipe.getText());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response removeRecipe(Recipe recipe) {
        try {
            DBWorker.removeRecipe(recipe.getId());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response updateCategory(Recipe recipe) {
        try {
            DBWorker.updateRecipe(recipe.getId(), recipe.getName(), recipe.getText(), recipe.getCategory());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/getbyingredients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipesByIngredients(List<String> ingredients) {
        try {
            GenericEntity<List<Recipe>> genericEntity = new GenericEntity<List<Recipe>>(DBWorker.gerRecipesByIngredients(ingredients)) {

            };
            return Response.ok(genericEntity).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
