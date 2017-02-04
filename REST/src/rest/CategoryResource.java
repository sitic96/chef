package rest; /**
 * Created by sitora on 23.01.17.
 */

import db.DBWorker;
import entity.Category;
import entity.Entity;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Entity getCategory(@PathParam("id") Integer id) {
        return DBWorker.readById("Category", "Categories", id);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllCategories() {
        GenericEntity<List<Category>> entity = new GenericEntity<List<Category>>(DBWorker.getAllCategories("Categories", "Category")) {
        };
        return Response.ok(entity).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNewCategory(Category category) {
        DBWorker.addNewCategory(category.getName());
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeCategory(Category category) {
        DBWorker.removeCategory(category.getId());
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCategory(Category category) {
        DBWorker.updateCategory(category.getId(), category.getName());
    }
}
