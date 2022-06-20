package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class DAO<T> {

    @Autowired
    protected SessionFactory sessionFactory;
    public List<T> getList(String query) {
        List<T> list = sessionFactory
                .getCurrentSession().createQuery(query).list();
        return list;
    }


    public boolean addNew(T t){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(t);
            transaction.commit();
        } catch(Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public abstract boolean deleteById(T t);

    public boolean update(T t){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(t);
            transaction.commit();
        } catch(Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public abstract boolean deleteByListId(List<T> list);

    public abstract T findById(T t);
}
