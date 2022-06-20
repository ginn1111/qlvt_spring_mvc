<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

                <%--SUPPLIER --%>
                <div id="suppliers" class="card manage">
                    <div class="manage__header">
                        <h5 class="header__title">Nhà cung cấp</h5>
                        <form
                                action="nha-cung-cap.htm?search"
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
                                    placeholder="tên nhà cung cấp..."
                            />
                        </form>
                        <div class="header__controller">
                            <a href="nha-cung-cap.htm?new#suppliers"
                               class="btn--customize btn--add"
                               data-control="suppliers"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn--remove-suppliers" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table manage--suppliers">
                        <div class="table__head">
                            <span>Mã nhà cung cấp</span><span>Tên nhà cung cấp</span>
                            <span>Địa chỉ</span><span>Số điện thoại</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-suppliers"
                                   action="nha-cung-cap.htm?delete#suppliers" method="POST"
                                   modelAttribute="deletedSupplierIdList">
                            <c:if test="${supplierModelList == null || supplierModelList.size() == 0}">
                                    <h3 class="nothing">Không có nhà cung cấp nào >_<...</h3>
                            </c:if>
                            <c:if test="${supplierModelList != null && supplierModelList.size() != 0}">
                                <c:forEach items="${deletedSupplierIdList.list}" varStatus="status">
                                    <div class="table__item table__item--suppliers"
                                         data-control="suppliers">
                                        <form:checkbox
                                                value="${supplierModelList.get(status.index).supplierId}"
                                                path="list[${status.index}]" />
                                        <span>${supplierModelList.get(status.index).supplierId}</span>
                                        <span>${supplierModelList.get(status.index).name}</span>
                                        <span>${supplierModelList.get(status.index).address}</span>
                                        <c:set var="tmp" value="${supplierModelList.get(status.index).phone}" />
                                        <c:set var="phone" value="${fn:trim(tmp)}" />
                                        <span>
                                            ${fn:substring(phone, 0, 3)}
                                            ${fn:substring(phone, 3, 6)}
                                            ${fn:substring(phone, 6, 10)}
                                        </span>
                                        <a href="nha-cung-cap/${supplierModelList.get(status.index).supplierId}.htm?update#suppliers">
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
                <%--END SUPPLIERS--%>

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
                    <dialog class="modal">
                        <%--FORM SUPPLIERS--%>
                        <form:form
                                class="form form--suppliers"
                                action="${link}#suppliers"
                                method="POST"
                                modelAttribute="supplierModel"
                        >
                            <h5 class="form__title">Nhà cung cấp</h5>
                            <form:input type="hidden" path="supplierId" />
                            <div class="form__item form__item--employee">
                                <label>Tên
                                    <form:input
                                            path="name"
                                            class="form__input"
                                            placeholder="Tên nhà cung cấp..."
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item form__item--employee">
                                <label>Số điện thoại
                                    <form:input
                                            type="number"
                                            path="phone"
                                            class="form__input"
                                            placeholder="số điện thoại..." />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Địa chỉ
                                    <form:textarea
                                            path="address"
                                            class="form__input"
                                            placeholder="địa chỉ..."
                                            rows="3"
                                    />
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
