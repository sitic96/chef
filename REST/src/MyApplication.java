import org.glassfish.jersey.server.ResourceConfig;
import rest.CategoryResource;

import javax.ws.rs.ApplicationPath;

/**
 * Created by sitora on 28.01.17.
 */
//@ApplicationPath("/*")
//public class MyApplication extends ResourceConfig {
//    public MyApplication(){
//        register(CategoryResource.class).
//                register(RecipeResource.class);
//
//    }
//
////    @Override
////    public Set<Class<?>> getClasses() {
////        Set<Class<?>> s = new HashSet<Class<?>>();
////        s.add(CategoryResource.class);
////        s.add(RecipeResource.class);
////        return s;
////    }
//}
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        register(CategoryResource.class);
    }
}
