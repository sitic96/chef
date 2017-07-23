import DBUtils.RecipeManager;

import java.math.BigInteger;

/**
 * Created by sitora on 15.07.17.
 */
public class Main {

    public static void main(final String[] args) throws Exception {

        BigInteger bigInteger = BigInteger.valueOf(1);
        RecipeManager.class.newInstance().getRecipeById(bigInteger);
    }
}