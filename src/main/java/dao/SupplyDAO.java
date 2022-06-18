package dao;

import entity.Supplier;
import entity.Supply;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplyDAO extends DAO<Supply> {

    public List<Supply> getList() {
        String query = "FROM Supply";
        return getList(query);
    }

    @Override
    public boolean deleteById(Supply supply) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Supply> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Supply supplyTmp;

        try {
            for (Supply sup: list) {
                supplyTmp = session.get(Supply.class, sup.getSupplyId());
                supplyTmp.setStatus(false);
                session.update(supplyTmp);
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
        return sessionFactory.getCurrentSession().get(Supply.class, supply.getSupplyId());
    }
}
