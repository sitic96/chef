package DBUtils;

import data.Recipe;
import data.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by sitora on 18.07.17.
 */
public class UserManager {

    public User getUserByLogin(String userName) {
        Session session = Connector.getConnector().getSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("name", userName))
                    .uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public User getUserById(Integer id) {
        Session session = Connector.getConnector().getSession();
        try {
            return session.load(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    public List<Recipe> getLikes(Integer userId){
        Session session = Connector.getConnector().getSession();
        String sql = "select * from \"Recipe\" where id in (select recipe_id from users_likes where user_id = :user_id)";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Recipe.class);
        query.setParameter("user_id", 1);
        List results = query.list();

        return results;
    }

    public boolean add(String userName, String url) {
        return this.addUser(loadUser(userName, url));
    }

    public boolean add(User user) {
        return this.addUser(user);
    }

    public boolean remove(String userName, String url) {
        return this.removeUser(loadUser(userName, url));
    }

    public boolean remove(User user) {
        return this.removeUser(user);
    }

    public boolean update(String userName, String url) {
        return this.updateUser(loadUser(userName, url));
    }

    public boolean update(User user) {
        return this.updateUser(user);
    }

    private boolean updateUser(User user) {
        Session session = Connector.getConnector().getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return false;
        }
    }

    private boolean addUser(User user) {
        Session session = Connector.getConnector().getSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return false;
        }
    }

    private boolean removeUser(User user) {
        Session session = Connector.getConnector().getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.remove(user);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return false;
        }
    }

    private User loadUser(String userName, String url) {
        User user = new User();
        user.setName(userName);
        user.setProfilePicture(url);

        return user;
    }
}
