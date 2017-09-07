package DBUtils;

import data.Recipe;
import data.User;
import data.UsersLikes;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by sitora on 18.07.17.
 */
public class UserManager {

    public User getUserByLogin(String userName) {
        Session session = Connector.getConnector().getSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("user_name", userName))
                    .uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public User getUserById(BigInteger id) {
        Session session = Connector.getConnector().getSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("user_id", id))
                    .uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Recipe> getLikes(Integer userId) {
        Session session = Connector.getConnector().getSession();
        String sql = "select * from \"Recipe\" where id in (select recipe_id from users_likes where user_id = " + userId + ")";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Recipe.class);
        //query.setParameter("user_id", userId);
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
        return this.saveHibernateEntity(user);
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
        user.setUser_name(userName);
        user.setProfilePicture(url);

        return user;
    }

    private boolean saveHibernateEntity(@NotNull Object o) {
        Session session = Connector.getConnector().getSession();
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean userLiked(Integer userId, Integer postId) {
        UsersLikes usersLikes = new UsersLikes();
        usersLikes.setUserId(userId);
        usersLikes.setRecipeId(postId);

        return this.saveHibernateEntity(usersLikes);
    }

    public boolean login(@NotNull String login, String password) {
        Session session = Connector.getConnector().getSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("user_name", login)).add(Restrictions.eq("password", password.toString()))
                    .uniqueResult();
            if (user == null) {
                return false;
            }
            return true;
        } finally {
            session.close();
        }
    }
}
