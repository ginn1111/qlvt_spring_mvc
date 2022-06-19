package dao;

import entity.DetailExCoupon;
import entity.DetailTransCoupon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailExCouponDAO extends DAO<DetailExCoupon> {

    public boolean addListDetail(List<DetailExCoupon> detailExCouponList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (DetailExCoupon d: detailExCouponList) {
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
    public boolean deleteById(DetailExCoupon detailExCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<DetailExCoupon> list) {
        return false;
    }

    @Override
    public DetailExCoupon findById(DetailExCoupon detailExCoupon) {
        return null;
    }
}
