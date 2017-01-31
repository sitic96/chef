package rest; /**
 * Created by sitora on 23.01.17.
 */

import db.DBWorker;
import entity.Entity;
import wrappers.Wrapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/categories")
public class CategoryResource {

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("{id}")
    public Entity getCategory(@PathParam("id") Integer id) {
        return DBWorker.readById("Category", "Categories", id);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Wrapper getAllCategories() {
        Wrapper categoryWrapper = new Wrapper(DBWorker.getAll("Categories", "Category"));
        //categoryWrapper.setCategories(DBWorker.getAll("Categories", "Category"));
//        GenericEntity<List<Entity>> entity = new GenericEntity<List<Entity>>(DBWorker.getAll("Categories", "Category")) {
//        };

        //return Response.ok(entity).build();
//       return DBWorker.getAll("Categories", "Category");
        return categoryWrapper;
    }
//
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public String getCategory(@DefaultValue("2") @QueryParam("step") int step) {
//        return "Hello " + step;
//    }
}
