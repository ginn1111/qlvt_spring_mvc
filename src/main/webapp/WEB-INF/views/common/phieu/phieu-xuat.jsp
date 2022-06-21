<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="manage__table manage--excoupons">
    <div class="table__head">
        <span>Mã phiếu</span><span>Nhân viên lập</span>
        <span>Công nhân nhận phiếu</span><span>Thời gian lập</span>
        <span>Công trình</span>
        <span>Trạng thái</span>
        <span>Ghi chú</span>
    </div>
    <form:form class="table__body custom-scroll-bar form-incoupons"
               action="phieu/phieu-xuat.htm?delete#excoupons" method="POST"
               modelAttribute="deletedExCPIdList">
        <c:if test="${exCouponModelList == null || exCouponModelList.size() == 0}">
            <h3 style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 30px;">
                Không có phiếu nào!</h3>
        </c:if>
        <c:if test="${exCouponModelList != null && exCouponModelList.size() != 0}">
            <c:forEach items="${deletedExCPIdList.list}" varStatus="status">
                <div class="table__item table__item--excoupons" data-control="excoupons">
                    <c:set value="${exCouponModelList.get(status.index)}" var="exCoupon" />
                    <form:checkbox value="${exCoupon.couponId}" path="list[${status.index}]"/>
                    <span>${exCoupon.couponId}</span>
                    <span>${exCoupon.employeeModel.name}</span>
                    <span>${exCoupon.workerModel.name}</span>
                    <span>
                        <fmt:formatDate
                            value="${exCoupon.date}"
                            pattern="dd/MM/yyyy"
                        />
                    </span>
                    <span>${exCoupon.constructionModel.name}</span>
                    <span>${exCoupon.couponStatusModel.status}</span>
                    <span>${exCoupon.note}</span>
                    <a href="phieu/phieu-xuat/${exCoupon.couponId}.htm?update#excoupons">
                        <span class="table__item--edit">
                            <ion-icon name="pencil-outline"></ion-icon>
                        </span>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </form:form>
</div>