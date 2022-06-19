<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form:form
        class="form form--trcoupons"
        action="${link}#trcoupons"
        method="POST"
        modelAttribute="trCouponModel"
>
    <h5 class="form__title">Phiếu chuyển kho</h5>
    <form:input type="hidden" path="couponId" />
    <form:input type="hidden" path="date" />
    <form:input
            type="hidden" path="employeeModel.employeeId"
            value="${requestScope.userInfo.employeeId}"
    />
    <c:set value="${btnTitle == 'Sửa'}" var="disabled"/>
    <div class="form__item">
        <label>Kho
            <div class="select ${disabled ? 'disabled' : ''}">
                <form:select
                        items="${warehouseList}"
                        itemLabel="name"
                        itemValue="warehouseId"
                        path="warehouseModel.warehouseId"
                        disabled="${disabled}"
                />
            </div>
        </label>
    </div>
    <c:if test="${btnTitle == 'Sửa'}">
        <div class="form__item">
            <label>Trạng thái
                <div class="select">
                    <form:select
                            items="${couponStatusList}"
                            itemLabel="status"
                            itemValue="id"
                            path="couponStatusModel.id"
                    />
                </div>
            </label>
        </div>
    </c:if>
    <div class="form__item">
        <label>Ghi chú
            <form:textarea
                    path="note"
                    class="form__input"
                    placeholder="ghi chú..."
                    rows="3"
            />
        </label>
    </div>
    <div class="form__item" style="margin-left: 8px; flex: 1">
        <label>
            <h5 class="header__title">Vật tư</h5>
            <c:if test="${btnTitle == 'Thêm'}">
                <ul class="list__account custom-scroll-bar" style="height: 100%; overflow-y: auto">
                    <c:forEach items="${sessionScope.supplies}" var="supply" varStatus="status">
                        <li class="card supplies">
                            <div class="supplies__info">
                                <form:checkbox
                                        path="detailTransCouponModelList[${status.index}].supplyModel.supplyId"
                                        value="${supply.supplyId}"
                                />
                                <span>${supply.name}</span>
                            </div>
                            <div class="supplies__quantity">
                                <form:input
                                        placeholder="số lượng..."
                                        type="number" path="detailTransCouponModelList[${status.index}].quantity"
                                        value="${supply.quantity}" max="${supply.quantity}"
                                        min="1" step="1"
                                />
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${btnTitle == 'Sửa'}" >
                <c:if test="${detailTransCouponModelList.size() == 0}">
                    <h4>Không có vật tư nào >_<...</h4>
                </c:if>
                <ul class="list__account custom-scroll-bar" style="height: 100%; overflow-y: auto">
                    <c:forEach items="${detailTransCouponModelList}" var="detail" varStatus="status">
                        <li class="card card--hover supplies supplies--des">
                            <span>${detail.supplyModel.name}</span>
                            <span>${detail.quantity}</span>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </label>
    </div>
    <div class="form__item--action">
        <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
        <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
    </div>
</form:form>
