package dao;

import entity.DetailInCoupon;
import entity.DetailTransCoupon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailTransCouponDAO extends DAO<TransCouponDAO>{


    public boolean addListDetail(List<DetailTransCoupon> detailTransCouponList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (DetailTransCoupon d: detailTransCouponList) {
                session.save(d);
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
    public boolean deleteById(TransCouponDAO transCouponDAO) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<TransCouponDAO> list) {
        return false;
    }

    @Override
    public TransCouponDAO findById(TransCouponDAO transCouponDAO) {
        return null;
    }
}
