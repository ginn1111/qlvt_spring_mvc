package dao;

import entity.Category;
import entity.Supply;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplyDAO extends DAO<Supply>{


    public List<Supply> getList() {
        String query = "FROM Supply AS E Where E.status = true";
        return super.getList(query);
    }


    @Override
    public boolean deleteById(Supply supply) {

        return false;
    }

    @Override
    public boolean deleteByListId(List<Supply> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (Supply supply:
                    list) {
                session.delete(supply);
            }
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

    @Override
    public Supply findById(Supply supply) {
        return super.sessionFactory.getCurrentSession().get(Supply.class, supply.getSupplyId());
    }
}
