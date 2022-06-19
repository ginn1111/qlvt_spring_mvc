<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="manage__table ">
    <div class="table__head table__head--incoupons">
        <span>Mã phiếu</span><span>Nhân viên lập</span>
        <span>Nhà cung cấp</span><span>Thời gian lập</span>
        <span>Trạng thái</span><span>Tổng tiền</span>
        <span>Ghi chú</span>
    </div>
    <form:form class="table__body custom-scroll-bar form-incoupons"
               action="phieu/phieu-nhap.htm?delete#incoupons" method="POST"
               modelAttribute="deletedInCPIdList">
        <c:if test="${inCouponModelList == null || inCouponModelList.size() == 0}">
            <h3 style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 30px;">Không có phiếu nào >_<...</h3>
        </c:if>
        <c:if test="${inCouponModelList != null && inCouponModelList.size() != 0}">
            <c:forEach items="${deletedInCPIdList.list}" varStatus="status">
                <div class="table__item table__item--incoupons" data-control="incoupons">
                    <form:checkbox
                            value="${inCouponModelList.get(status.index).couponId}"
                            path="list[${status.index}]" />
                    <span>${inCouponModelList.get(status.index).couponId}</span>
                    <span>${inCouponModelList.get(status.index).employeeModel.name}</span>
                    <span>${inCouponModelList.get(status.index).supplierModel.name}</span>
                    <span><fmt:formatDate value="${inCouponModelList.get(status.index).date}" pattern="dd/MM/yyyy"/></span>
                    <span>${inCouponModelList.get(status.index).couponStatusModel.status}</span>
                    <fmt:setLocale value = "vi_VN" scope="session"/>
                    <span><fmt:formatNumber value="${inCouponModelList.get(status.index).total}" type="currency"/></span>
                    <span>${inCouponModelList.get(status.index).note}</span>
                    <a href="phieu/phieu-nhap/${inCouponModelList.get(status.index).couponId}.htm?update#incoupons">
                        <span class="table__item--edit">
                            <ion-icon name="pencil-outline"></ion-icon>
                        </span>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </form:form>
</div>
