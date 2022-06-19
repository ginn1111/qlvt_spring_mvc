<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="manage__table ">
    <div class="table__head table__head--brcoupons">
        <span>Mã phiếu</span><span>Nhân viên lập</span>
        <span>Công nhân mượn</span><span>Thời gian lập</span>
        <span>Ngày trả</span> <span>Công trình</span>
        <span>Trạng thái</span> <span>Ghi chú</span>
    </div>
    <form:form class="table__body custom-scroll-bar form-brcoupons"
               action="phieu/phieu-muon.htm?delete#brcoupons" method="POST"
               modelAttribute="deletedBrCPIdList">
        <c:if test="${brCouponModelList == null || brCouponModelList.size() == 0}">
            <h3 style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 30px;">Không có phiếu nào >_<...</h3>
        </c:if>
        <c:if test="${brCouponModelList != null && brCouponModelList.size() != 0}">
            <c:forEach items="${deletedBrCPIdList.list}" varStatus="status">
                <div class="table__item table__item--brcoupons" data-control="brcoupons">
                    <c:set var="brCoupon" value="${brCouponModelList.get(status.index)}" />
                    <form:checkbox
                            value="${brCoupon.couponId}"
                            path="list[${status.index}]" />
                    <span>${brCoupon.couponId}</span>
                    <span>${brCoupon.employeeModel.name}</span>
                    <span>${brCoupon.workerModel.name}</span>
                    <span><fmt:formatDate value="${brCoupon.borrowedDate}" pattern="dd/MM/yyyy"/></span>
                    <span><fmt:formatDate value="${brCoupon.payedDate}" pattern="dd/MM/yyyy"/></span>
                    <span>${brCoupon.constructionModel.name}</span>
                    <span>${brCoupon.couponStatusModel.status}</span>
                    <span>${brCoupon.note}</span>
                    <a href="phieu/phieu-muon/${brCoupon.couponId}.htm?update#brcoupons">
                        <span class="table__item--edit">
                            <ion-icon name="pencil-outline"></ion-icon>
                        </span>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </form:form>
</div>
