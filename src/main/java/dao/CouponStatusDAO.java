package dao;

import entity.CouponStatus;
import model.CouponStatusModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponStatusDAO extends DAO<CouponStatus> {

    public List<CouponStatus> getList() {
        String query = "FROM CouponStatus";
        return getList(query);
    }
    @Override
    public boolean deleteById(CouponStatus couponStatus) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<CouponStatus> list) {
        return false;
    }

    @Override
    public CouponStatus findById(CouponStatus couponStatus) {
        return null;
    }
}
