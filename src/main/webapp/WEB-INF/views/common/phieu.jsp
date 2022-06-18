<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/resources/includes/header.jsp"%>

<div class="container">
    <tg:dialog-message message="${message}" />
    <!-- Navigation -->
    <nav class="navigation active">
        <c:if test="${role == 'MANAGER'}">
            <tg:quanly-nav />
        </c:if>
        <c:if test="${role == 'EMPLOYEE'}">
            <tg:nhanvien-nav />
        </c:if>
    </nav>
    <div class="main active">

        <!-- Tool bar -->
        <section class="topbar">
            <tg:topbar />
        </section>
        <%--Content --%>
        <div class="main__content">
            <section class="manage__container">

                <%--IN COUPON --%>
                <div id="incoupons" class="card manage">
                    <div class="manage__header">
                        <h5 class="header__title">Phiếu nhập</h5>
                        <form
                                action="phieu/phieu-nhap.htm?search"
                                method="POST"
                                class="search-box">
                            <ion-icon
                                    name="search-outline"
                                    class="search__icon"
                            ></ion-icon>
                            <input
                                    required="required"
                                    name="data"
                                    type="search"
                                    placeholder="Tên..."
                            />
                        </form>
                        <div class="header__controller">
                            <a href="phieu/phieu-nhap.htm?new#incoupons"
                               class="btn--customize btn--add"
                               data-control="incoupons"
                            >
                                Tạo
                            </a>
                            <button class="btn--customize btn--remove btn--remove-incoupons" disabled>Xoá</button>
                        </div>
                    </div>
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
                                        <span class="table__item--delete"> <ion-icon
                                                name="trash-outline"></ion-icon>
                                        </span>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </form:form>
                    </div>
                </div>
                <%--END IN COUPONS--%>

                <!-- CONFIRM DIALOG -->
                <dialog class="dialog">
                    <h5 class="dialog__title">Thông báo!</h5>
                    <span class="dialog__message">Xoá sẽ không khôi phục lại được, bạn có muốn xoá?</span>
                    <div class="dialog__control">
                        <button class="btn--customize btn--confirm btn--warning">Xoá</button>
                        <button class="btn--customize btn--cancel btn--safe">Huỷ</button>
                    </div>
                </dialog>

                <%--END CONFIRM DIALOG--%>
                <div class="backdrop">
                    <dialog class="modal modal--coupon" style="width: 40%">
                        <%--FORM IN COUPONS--%>
                        <form:form
                                class="form form--incoupons"
                                action="${link}#incoupons"
                                method="POST"
                                modelAttribute="inCouponModel"
                        >
                            <h5 class="form__title">Phiếu nhập</h5>
                            <form:input type="hidden" path="couponId" />
                            <form:input
                                type="hidden" path="employeeModel.employeeId"
                                value="${requestScope.userInfo.employeeId}"
                            />
                                <div class="form__item">
                                    <label>Nhà cung cấp
                                        <div class="select">
                                            <form:select
                                                items="${supplierList}"
                                                itemLabel="name"
                                                itemValue="supplierId"
                                                path="supplierModel.supplierId"
                                            />
                                        </div>
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
                                    <label>
                                        <h5 class="header__title">Vật tư</h5>
                                        <ul class="list__account custom-scroll-bar" style="height: 100%; overflow-y: auto">
                                            <c:forEach items="${sessionScope.supplies}" var="supply" varStatus="status">
                                                <li class="card supplies">
                                                    <div class="supplies__info">
                                                        <form:checkbox
                                                            path="detailInCouponModelList[${status.index}].supplyModel.supplyId"
                                                            value="${supply.supplyId}"
                                                        />
                                                        <span>${supply.name}</span>
                                                    </div>
                                                    <div class="supplies__quantity">
                                                        <form:input
                                                            placeholder="số lượng..."
                                                            type="number" path="detailInCouponModelList[${status.index}].quantity"
                                                            value="${supply.quantity}" max="${supply.quantity}"
                                                            min="1" step="1"
                                                        />
                                                        <form:input
                                                            placeholder="đơn giá..."
                                                            min="1"
                                                            type="number"
                                                            path="detailInCouponModelList[${status.index}].price"
                                                        />
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </label>
                                </div>
                            <div class="form__item--action">
                                <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
                                <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                            </div>
                        </form:form>
                        <%--END FORM SUPPLIERS--%>


                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
