<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="manage__table ">
    <div class="table__head table__head--pycoupons">
        <span>Mã phiếu</span><span>Nhân viên lập</span>
        <span>Công nhân trả</span><span>Mã phiếu mượn</span>
        <span>Ngày trả</span>
        <span>Trạng thái</span> <span>Ghi chú</span>
    </div>
    <form:form class="table__body custom-scroll-bar form-pycoupons"
               action="phieu/phieu-tra.htm?delete#pycoupons" method="POST"
               modelAttribute="deletedPyCPIdList">
        <c:if test="${pyCouponModelList == null || pyCouponModelList.size() == 0}">
            <h3 style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 30px;">Không có phiếu nào >_<...</h3>
        </c:if>
        <c:if test="${pyCouponModelList != null && pyCouponModelList.size() != 0}">
            <c:forEach items="${deletedPyCPIdList.list}" varStatus="status">
                <div class="table__item table__item--pycoupons" data-control="pycoupons">
                    <c:set var="pyCoupon" value="${pyCouponModelList.get(status.index)}" />
                    <form:checkbox
                            value="${pyCoupon.couponId}"
                            path="list[${status.index}]" />
                    <span>${pyCoupon.couponId}</span>
                    <span>${pyCoupon.employeeModel.name}</span>
                    <span>${pyCoupon.workerModel.name}</span>
                    <span>${pyCoupon.borrowedCouponModel.couponId}</span>
                    <span><fmt:formatDate value="${pyCoupon.payedDate}" pattern="dd/MM/yyyy"/></span>
                    <span>${pyCoupon.couponStatusModel.status}</span>
                    <span>${pyCoupon.note}</span>
                    <a href="phieu/phieu-tra/${pyCoupon.couponId}.htm?update#pycoupons">
                        <span class="table__item--edit">
                            <ion-icon name="pencil-outline"></ion-icon>
                        </span>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </form:form>
</div>
