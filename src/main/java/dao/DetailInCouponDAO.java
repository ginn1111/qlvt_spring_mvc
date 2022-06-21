package dao;

import entity.DetailInCoupon;
import model.DetailInCouponModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public class DetailInCouponDAO extends DAO<DetailInCoupon> {
    @Override
    public boolean deleteById(DetailInCoupon detailInCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<DetailInCoupon> list) {
        return false;
    }

    @Override
    public DetailInCoupon findById(DetailInCoupon detailInCoupon) {
        return null;
    }

    public boolean addListDetail(List<DetailInCoupon> detailInCouponList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (DetailInCoupon d: detailInCouponList) {
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
}
