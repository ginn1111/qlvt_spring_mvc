<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="manage__table ">
    <div class="table__head table__head--trcoupons">
        <span>Mã phiếu</span><span>Nhân viên lập</span>
        <span>Kho</span><span>Thời gian lập</span>
        <span>Trạng thái</span> <span>Ghi chú</span>
    </div>
    <form:form class="table__body custom-scroll-bar form-trcoupons"
               action="phieu/phieu-chuyen-kho.htm?delete#trcoupons" method="POST"
               modelAttribute="deletedTrCPIdList">
        <c:if test="${trCouponModelList == null || trCouponModelList.size() == 0}">
            <h3 style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 30px;">Không có phiếu nào >_<...</h3>
        </c:if>
        <c:if test="${trCouponModelList != null && trCouponModelList.size() != 0}">
            <c:forEach items="${deletedTrCPIdList.list}" varStatus="status">
                <div class="table__item table__item--trcoupons" data-control="trcoupons">
                    <c:set var="trCoupon" value="${trCouponModelList.get(status.index)}" />
                    <form:checkbox
                            value="${trCoupon.couponId}"
                            path="list[${status.index}]" />
                    <span>${trCoupon.couponId}</span>
                    <span>${trCoupon.employeeModel.name}</span>
                    <span>${trCoupon.warehouseModel.name}</span>
                    <span><fmt:formatDate value="${trCoupon.date}" pattern="dd/MM/yyyy"/></span>
                    <span>${trCoupon.couponStatusModel.status}</span>
                    <span>${trCoupon.note}</span>
                    <a href="phieu/phieu-chuyen-kho/${trCoupon.couponId}.htm?update#trcoupons">
                        <span class="table__item--edit">
                            <ion-icon name="pencil-outline"></ion-icon>
                        </span>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </form:form>
</div>
