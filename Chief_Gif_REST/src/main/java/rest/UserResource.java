package rest;

import DBUtils.UserManager;
import data.User;

import javax.ws.rs.*;
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
    public Response getUserByLogin(@PathParam("login") String login) {
        try {
            return Response.status(Response.Status.OK).entity(UserManager.class.newInstance().getUserByLogin(login)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    @Path("{id}")
//    public Response getUserById(@PathParam("id") Integer id) {
//        try {
//            return Response.status(Response.Status.OK).entity(UserManager.class.newInstance().getUserById(id)).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response addNewUser(User user) {
        try {
            UserManager.class.newInstance().add(user);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response removeUser(User user) {
        try {
            UserManager.class.newInstance().remove(user);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response addNewIngredient(User user) {
        try {
            UserManager.class.newInstance().update(user);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/likes")
    public Response getLikes() {
        try {
            return Response.status(Response.Status.OK).entity(UserManager.class.newInstance().getLikes(1)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
