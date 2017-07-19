import DBUtils.UserManager;
import data.User;

/**
 * Created by sitora on 15.07.17.
 */
public class Main {

    public static void main(final String[] args) throws Exception {

        User user = UserManager.class.newInstance().getUserByLogin("admin");
    }
}