import org.glassfish.jersey.server.ResourceConfig;
import rest.CategoryResource;
import rest.IngredientResource;
import rest.RecipeResource;

import javax.ws.rs.ApplicationPath;

/**
 * Created by sitora on 28.01.17.
 */

@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        register(CategoryResource.class)
                .register(RecipeResource.class)
                .register(IngredientResource.class);
    }
}
