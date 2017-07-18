package DBUtils;

import data.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by sitora on 18.07.17.
 */
public class UserManager {

    public User get(String userName) {
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

    public boolean add(String userName, String url) {
        return this.addUser(loadUser(userName, url));
    }

    public boolean remove(String userName, String url) {
        return this.removeUser(loadUser(userName, url));
    }

    public boolean update(String userName, String url) {
        return this.updateUser(loadUser(userName, url));
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
