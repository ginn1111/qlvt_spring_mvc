<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form:form
        class="form form--excoupons"
        action="${link}#excoupons"
        method="POST"
        modelAttribute="exCouponModel"
>
    <h5 class="form__title">Phiếu xuất</h5>
    <c:if test="${constructionList == null || constructionList.size() == 0}">
        <span>Không có công trình nào!</span>
    </c:if>
    <c:if test="${workerList == null || workerList.size() == 0}">
        <span>Không có công nhân nào!</span>
    </c:if>
    <c:if test="${constructionList.size() > 0 && workerList.size() > 0}">
        <form:input type="hidden" path="couponId"/>
        <form:input type="hidden" path="date"/>
        <form:input type="hidden" path="employeeModel.employeeId" value="${userInfo.employeeId}"/>
        <c:set value="${btnTitle == 'Sửa'}" var="disabled"/>
        <div class="form__item">
            <label>Công nhân
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
        <div class="form__item">
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
                            <li class="card supplies supplies--checked">
                                <div class="supplies__info">
                                    <form:checkbox
                                            path="detailExCouponModelList[${status.index}].supplyModel.supplyId"
                                            value="${supply.supplyId}"
                                    />
                                    <span>${supply.name}</span>
                                </div>
                                <div class="supplies__quantity">
                                    <label>
                                        <form:input
                                                placeholder="số lượng..."
                                                type="number" path="detailExCouponModelList[${status.index}].quantity"
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
                    <c:if test="${detailExCouponModelList.size() == 0}">
                        <h4>Không có vật tư nào >_<...</h4>
                    </c:if>
                    <ul class="list__account custom-scroll-bar" style="height: 100%; overflow-y: auto">
                        <c:forEach items="${detailExCouponModelList}" var="detail" varStatus="status">
                            <li class="card card--hover supplies supplies--des">
                                <span>${detail.supplyModel.name}</span>
                                <span>${detail.quantity} ${detail.supplyModel.unit}</span>
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
    </c:if>
</form:form>
