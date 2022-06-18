package request_bean;

import model.DetailInCouponModel;

import java.util.List;

public class DetailInCouponModelList {
    List<DetailInCouponModel> list;

    public List<DetailInCouponModel> getList() {
        return list;
    }

    public void setList(List<DetailInCouponModel> list) {
        this.list = list;
    }

    public DetailInCouponModelList() {
    }

    public DetailInCouponModelList(List<DetailInCouponModel> list) {
        this.list = list;
    }
}
