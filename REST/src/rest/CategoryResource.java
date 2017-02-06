package rest; /**
 * Created by sitora on 23.01.17.
 */

import db.DBWorker;
import entity.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("{id}")
    public Category getCategory(@PathParam("id") Integer id) {
        Category c = DBWorker.readByIdСategory("Category", "Categories", id);
        return c;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getAllCategories() {
        GenericEntity<List<Category>> entity = new GenericEntity<List<Category>>(DBWorker.getAllCategories("Categories", "Category")) {
        };
        return Response.ok(entity).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void addNewCategory(Category category) {
        DBWorker.addNewCategory(category.getName());
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void removeCategory(Category category) {
        DBWorker.removeCategory(category.getId());
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void updateCategory(Category category) {
        DBWorker.updateCategory(category.getId(), category.getName());
    }
}
