import org.glassfish.jersey.server.ResourceConfig;
import rest.UserResource;

import javax.ws.rs.ApplicationPath;

/**
 * Created by sitora on 19.07.17.
 */
@ApplicationPath("rest_gif")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        register(UserResource.class);
    }
}
