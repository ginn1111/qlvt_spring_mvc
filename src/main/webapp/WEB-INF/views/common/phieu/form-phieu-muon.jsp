<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form:form
        class="form form--brcoupons"
        action="${link}#brcoupons"
        method="POST"
        modelAttribute="brCouponModel"
>
    <h5 class="form__title">Phiếu mượn</h5>
    <form:input type="hidden" path="couponId" />
    <form:input type="hidden" path="borrowedDate" />
    <form:input
            type="hidden" path="employeeModel.employeeId"
            value="${requestScope.userInfo.employeeId}"
    />
    <c:set value="${btnTitle == 'Sửa'}" var="disabled"/>
    <div class="form__item">
        <label>Công nhân mượn
            <div class="select ${disabled ? 'disabled' : ''}">
                <form:select
                        items="${workerList}"
                        itemLabel="name"
                        itemValue="workerId"
                        path="workerModel.workerId"
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
    <div class="form__item form__item--brcoupons">
        <label>Công trình
            <div class="select ${disabled ? 'disabled' : ''}">
                <form:select
                        items="${constructionList}"
                        itemLabel="name"
                        itemValue="constructionId"
                        path="constructionModel.constructionId"
                        disabled="${disabled}"
                />
            </div>
        </label>
        <label>Ngày trả
            <form:input
                    path="payedDate"
                    type="date"
                    class="form__input"
                    format="yyyy-MM-dd"
                    maxlength="${requestScope.dateNow}"

            />
        </label>
    </div>
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
        <div style="font-size: 14px; width: 100%">
            <h5 class="header__title">Vật tư</h5>
            <c:if test="${btnTitle == 'Thêm'}">
                <ul class="list__account custom-scroll-bar" style="height: 100%; overflow-y: auto">
                    <c:forEach items="${sessionScope.supplies}" var="supply" varStatus="status">
                        <li class="card supplies supplies--checked">
                            <div class="supplies__info">
                                <form:checkbox
                                        path="detailBorrowedCouponModelList[${status.index}].supplyModel.supplyId"
                                        value="${supply.supplyId}"
                                />
                                <span>${supply.name}</span>
                            </div>
                            <div class="supplies__quantity">
                                <label>
                                    Số lượng:
                                    <form:input
                                            placeholder="số lượng..."
                                            type="number" path="detailBorrowedCouponModelList[${status.index}].quantity"
                                            value="${supply.quantity}" max="${supply.quantity}"
                                            min="1" step="1"
                                    />
                                    <span>${supply.unit}</span>
                                </label>
                            </div>
                            <div class="supplies__icon">
                                <ion-icon name="attach-outline"></ion-icon>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${btnTitle == 'Sửa'}" >
                <c:if test="${detailBorrowedCouponModelList.size() == 0}">
                    <h4>Không có vật tư nào >_<...</h4>
                </c:if>
                <ul class="list__account custom-scroll-bar" style="height: 100%; overflow-y: auto">
                    <c:forEach items="${detailBorrowedCouponModelList}" var="detail" varStatus="status">
                        <li class="card card--hover supplies supplies--des">
                            <span>${detail.supplyModel.name}</span>
                            <span>${detail.quantity} ${detail.supplyModel.unit}</span>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
    <div class="form__item--action">
        <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
        <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
    </div>
</form:form>
