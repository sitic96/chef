package DBUtils.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ResourceAlreadyExistException extends WebApplicationException {

    public ResourceAlreadyExistException() {
        super(Response.status(409).build());
    }

    public ResourceAlreadyExistException(String message) {
        super(Response.status(409).entity(message).build());
    }

}
