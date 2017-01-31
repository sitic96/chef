package db;

import entity.Entity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.xml.ws.WebServiceException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sitora on 26.01.17.
 */
public class DBWorker {

    public static Entity readById(String objName, String table, Integer id) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Entity>> h = new BeanListHandler<>((Class<Entity>) Class.forName("entity." + objName));
            List<Entity> categories = run.query("SELECT * FROM " + table + " where id = " + id, h);
            return categories.get(0);

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

    public static List<Entity> getAll(String table, String objName) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Entity>> h = new BeanListHandler<>((Class<Entity>) Class.forName("entity." + objName));
            List<Entity> categories = run.query("SELECT * FROM " + table, h);
            return categories;

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

}
