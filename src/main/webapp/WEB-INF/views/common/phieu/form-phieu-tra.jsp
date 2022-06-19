<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<form:form--%>
<%--        class="form form--incoupons"--%>
<%--        action="${link}#incoupons"--%>
<%--        method="POST"--%>
<%--        modelAttribute="inCouponModel"--%>
<%-->--%>
<%--    <h5 class="form__title">Phiếu nhập</h5>--%>
<%--    <form:input type="hidden" path="couponId" />--%>
<%--    <form:input--%>
<%--            type="hidden" path="employeeModel.employeeId"--%>
<%--            value="${requestScope.userInfo.employeeId}"--%>
<%--    />--%>
<%--    <div class="form__item">--%>
<%--        <label>Nhà cung cấp--%>
<%--            <div class="select">--%>
<%--                <form:select--%>
<%--                        items="${supplierList}"--%>
<%--                        itemLabel="name"--%>
<%--                        itemValue="supplierId"--%>
<%--                        path="supplierModel.supplierId"--%>
<%--                />--%>
<%--            </div>--%>
<%--        </label>--%>
<%--    </div>--%>
<%--    <div class="form__item">--%>
<%--        <label>Ghi chú--%>
<%--            <form:textarea--%>
<%--                    path="note"--%>
<%--                    class="form__input"--%>
<%--                    placeholder="ghi chú..."--%>
<%--                    rows="3"--%>
<%--            />--%>
<%--        </label>--%>
<%--    </div>--%>
<%--    <div class="form__item" style="margin-left: 8px; flex: 1">--%>
<%--        <label>--%>
<%--            <h5 class="header__title">Vật tư</h5>--%>
<%--            <ul class="list__account custom-scroll-bar" style="height: 100%; overflow-y: auto">--%>
<%--                <c:forEach items="${sessionScope.supplies}" var="supply" varStatus="status">--%>
<%--                    <li class="card supplies">--%>
<%--                        <div class="supplies__info">--%>
<%--                            <form:checkbox--%>
<%--                                    path="detailInCouponModelList[${status.index}].supplyModel.supplyId"--%>
<%--                                    value="${supply.supplyId}"--%>
<%--                            />--%>
<%--                            <span>${supply.name}</span>--%>
<%--                        </div>--%>
<%--                        <div class="supplies__quantity">--%>
<%--                            <form:input--%>
<%--                                    placeholder="số lượng..."--%>
<%--                                    type="number" path="detailInCouponModelList[${status.index}].quantity"--%>
<%--                                    value="${supply.quantity}" max="${supply.quantity}"--%>
<%--                                    min="1" step="1"--%>
<%--                            />--%>
<%--                            <form:input--%>
<%--                                    placeholder="đơn giá..."--%>
<%--                                    min="1"--%>
<%--                                    type="number"--%>
<%--                                    path="detailInCouponModelList[${status.index}].price"--%>
<%--                            />--%>
<%--                        </div>--%>
<%--                    </li>--%>
<%--                </c:forEach>--%>
<%--            </ul>--%>
<%--        </label>--%>
<%--    </div>--%>
<%--    <div class="form__item--action">--%>
<%--        <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>--%>
<%--        <button type="reset" class="btn--cancel btn--customize">Huỷ</button>--%>
<%--    </div>--%>
<%--</form:form>--%>
