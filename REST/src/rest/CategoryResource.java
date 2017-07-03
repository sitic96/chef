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
    public Response getCategory(@PathParam("id") Integer id) {
        try {
            return Response.status(Response.Status.OK).entity(DBWorker.readByIdСategory("Category", "Categories", id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.OK).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getAllCategories() {
        try {
            GenericEntity<List<Category>> entity = new GenericEntity<List<Category>>(DBWorker.getAllCategories("Categories", "Category")) {
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
    public Response addNewCategory(Category category) {
        try {
            DBWorker.addNewCategory(category.getName());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response removeCategory(Category category) {
        try {
            DBWorker.removeCategory(category.getId());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response updateCategory(Category category) {
        try {
            DBWorker.updateCategory(category.getId(), category.getName());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
