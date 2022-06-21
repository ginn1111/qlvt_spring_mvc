package dao;

import entity.DetailPayedCoupon;
import entity.DetailPayedCoupon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailPayedCouponDAO extends DAO<DetailPayedCoupon> {

    public boolean addListDetail(List<DetailPayedCoupon> detailPayedCouponList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (DetailPayedCoupon d: detailPayedCouponList) {
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
    public boolean deleteById(DetailPayedCoupon detailPayedCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<DetailPayedCoupon> list) {
        return false;
    }

    @Override
    public DetailPayedCoupon findById(DetailPayedCoupon detailPayedCoupon) {
        return null;
    }
}
