<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/resources/includes/header.jsp"%>

<div class="container">
    <tg:dialog-message message="${message}" />
    <!-- Navigation -->
    <nav class="navigation active">
        <tg:navigation prefix="quanly"
                       link1="./nhan-vien.htm" link2="./tai-khoan.htm" link3="./phieu.htm" link4="./cong-nhan.htm"
                       link5="./nha-cung-cap.htm" link6="./kho-va-cong-trinh.htm" link7="danh-muc-va-khu-vuc.htm"
                       uri1="nhan-vien" uri2="tai-khoan" uri3="phieu" uri4="cong-nhan" uri5="nha-cung-cap"
                       uri6="kho-va-cong-trinh" uri7="danh-muc-va-khu-vuc"
                       name1="Nhân viên" name2="Tài khoản" name3="Phiếu" name4="Công nhân" name5="Nhà cung cấp"
                       name6="Công trình và kho" name7="Danh mục và khu vực"
                       icon1="person-outline" icon2="key-outline" icon3="book-outline" icon4="bicycle-outline"
                       icon5="file-tray-stacked-outline" icon6="podium-outline" icon7="library-outline"
        />
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
                    <div class="manage__table manage--incoupons">
                        <div class="table__head">
                            <span>Mã phiếu</span><span>Nhân viên lập</span>
                            <span>Nhà cung cấp</span><span>Thời gian lập</span>
                            <span>Tổng tiền</span><span>Ghi chú</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-incoupons"
                                   action="phieu/phieu-nhap.htm?delete#incoupons" method="POST"
                                   modelAttribute="deletedInCPIdList">
                            <c:if test="${inCouponModelList == null || inCouponModelList.size() == 0}">
                                <h3 style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 30px;">Không có phiếu nào >_<...</h3>
                            </c:if>
                            <c:if test="${inCouponModelList != null && inCouponModelList.size() != 0}">
                                <c:forEach items="${deletedInCPIdList.list}" varStatus="status">
                                    <div class="table__item table__item--incoupons"
                                         data-control="incoupons">
                                        <form:checkbox
                                                value="${inCouponModelList.get(status.index).couponId}"
                                                path="list[${status.index}]" />
                                        <span>${inCouponModelList.get(status.index).couponId}</span>
                                        <span>${inCouponModelList.get(status.index).employeeModel.name}</span>
                                        <span>${inCouponModelList.get(status.index).supplierModel.name}</span>
                                        <span><fmt:formatDate value="${inCouponModelList.get(status.index).date}" pattern="dd/MM/yyyy"/></span>
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
                            <form:input type="hidden" path="employeeModel.employeeId" value="${userInfo.employeeId}"/>
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
<%--                                <div class="form__item" style="margin-left: 8px; flex: 1">--%>
<%--                                    <label>--%>
<%--                                    <div class="manage__header">--%>
<%--                                        <h5 class="header__title">Vật tư</h5>--%>
<%--                                        <form--%>
<%--                                                action="phieu/phieu-nhap.htm?search"--%>
<%--                                                method="POST"--%>
<%--                                                class="search-box">--%>
<%--                                            <ion-icon--%>
<%--                                                    name="search-outline"--%>
<%--                                                    class="search__icon"--%>
<%--                                            ></ion-icon>--%>
<%--                                            <input--%>
<%--                                                    required="required"--%>
<%--                                                    name="data"--%>
<%--                                                    type="search"--%>
<%--                                                    placeholder="Tên..."--%>
<%--                                            />--%>
<%--                                        </form>--%>
<%--                                    </div>--%>
<%--                                    </label>--%>
<%--                                </div>--%>
                            <div class="form__item--action">
                                <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
                                <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                            </div>
                        </form:form>
                        <%--END FORM SUPPLIERS--%>

<%--                            <ul class="list__account custom-scroll-bar" style="height: 100%; overflow-y: auto">--%>
<%--                                <c:forEach items="${detailInCouponModelList.list}" var="detail" varStatus="status">--%>
<%--                                    &lt;%&ndash;                                        <c:forEach items="${sessionScope.supplies}" var="supply" varStatus="status">&ndash;%&gt;--%>
<%--                                    <li class="card supplies" style="flex-wrap: nowrap">--%>
<%--                                        <span>test</span>--%>
<%--                                            &lt;%&ndash;                                                <c:set var="supply" value="${sessionScope.supplies.get(status)}"/>&ndash;%&gt;--%>
<%--                                        <form:checkbox path="detail.supplyId"--%>
<%--                                                       value="1"/>--%>
<%--                                        <span style="flex: 4">Test</span>--%>
<%--                                        <form:input type="number" path="detail.quantity"--%>
<%--                                                    value="1" max="1" min="1" step="1" />--%>
<%--                                        <form:input type="number" path="detail.price" />--%>
<%--                                        <span style="flex: 3; text-align: end">Test</span>--%>
<%--                                    </li>--%>
<%--                                </c:forEach>--%>
<%--                            </ul>--%>
                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
