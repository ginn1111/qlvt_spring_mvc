<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/includes/header.jsp"%>

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

                <%--COUPONS --%>
                <div id="coupons" class="card manage">
                    <div class="manage__header">
                        <a class="header__title header__title--coupon hovered" data-uri="phieu-nhap" href="phieu/phieu-nhap.htm">Phiếu nhập</a>
                        <a class="header__title header__title--coupon" data-uri="phieu-xuat" href="phieu/phieu-xuat.htm">Phiếu xuất</a>
                        <a class="header__title header__title--coupon" data-uri="phieu-muon" href="phieu/phieu-muon.htm">Phiếu mượn</a>
                        <a class="header__title header__title--coupon" data-uri="phieu-tra" href="phieu/phieu-tra.htm">Phiếu trả</a>
                        <a class="header__title header__title--coupon" data-uri="phieu-chuyen-kho" href="phieu/phieu-chuyen-kho.htm">Phiếu chuyển kho</a>
                        <div class="header__controller">
                            <c:choose>
                                <c:when test="${couponType == 'PHIEUNHAP'}">
                                    <a href="phieu/phieu-nhap.htm?new#incoupons"
                                       class="btn--customize btn--add" data-control="incoupons"
                                    >
                                </c:when>
                                <c:when test="${couponType == 'PHIEUMUON'}">
                                    <a href="phieu/phieu-muon.htm?new#brcoupons"
                                       class="btn--customize btn--add" data-control="brcoupons"
                                    >
                                </c:when>
                                <c:when test="${couponType == 'PHIEUXUAT'}">
                                    <a href="phieu/phieu-xuat.htm?new#excoupons"
                                       class="btn--customize btn--add" data-control="excoupons"
                                    >
                                </c:when>
                                <c:when test="${couponType == 'PHIEUTRA'}">
                                    <a class="btn--customize btn--add btn--payed-coupon" data-control="pycoupons">
                                </c:when>
                                <c:when test="${couponType == 'PHIEUCHUYENKHO'}">
                                    <a href="phieu/phieu-chuyen-kho.htm?new#trcoupons"
                                       class="btn--customize btn--add" data-control="trcoupons"
                                    >
                                </c:when>
                            </c:choose>
                                    Tạo
                                    </a>
                                <%--                            <button class="btn--customize btn--remove btn--remove-incoupons" disabled>Xoá</button>--%>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${couponType == 'PHIEUXUAT'}">
                            <%@ include file="/WEB-INF/views/common/phieu/phieu-xuat.jsp" %>
                        </c:when>
                        <c:when test="${couponType == 'PHIEUMUON'}">
                            <%@ include file="/WEB-INF/views/common/phieu/phieu-muon.jsp" %>
                        </c:when>
                        <c:when test="${couponType == 'PHIEUTRA'}">
                            <%@ include file="/WEB-INF/views/common/phieu/phieu-tra.jsp" %>
                        </c:when>
                        <c:when test="${couponType == 'PHIEUCHUYENKHO'}">
                            <%@ include file="/WEB-INF/views/common/phieu/phieu-chuyen-kho.jsp" %>
                        </c:when>
                        <c:when test="${couponType == 'PHIEUNHAP'}">
                            <%@ include file="/WEB-INF/views/common/phieu/phieu-nhap.jsp" %>
                        </c:when>
                    </c:choose>
                </div>
                <%--END COUPONS--%>

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
                        <c:choose>
                            <c:when test="${couponType == 'PHIEUXUAT'}">
                                <%@ include file="/WEB-INF/views/common/phieu/form-phieu-xuat.jsp" %>
                            </c:when>
                            <c:when test="${couponType == 'PHIEUMUON'}">
                                <%@ include file="/WEB-INF/views/common/phieu/form-phieu-muon.jsp" %>
                            </c:when>
                            <c:when test="${couponType == 'PHIEUTRA'}">
                                <%@ include file="/WEB-INF/views/common/phieu/form-phieu-tra.jsp" %>
                            </c:when>
                            <c:when test="${couponType == 'PHIEUCHUYENKHO'}">
                                <%@ include file="/WEB-INF/views/common/phieu/form-phieu-chuyen-kho.jsp" %>
                            </c:when>
                            <c:when test="${couponType == 'PHIEUNHAP'}">
                                <%@ include file="/WEB-INF/views/common/phieu/form-phieu-nhap.jsp" %>
                            </c:when>
                        </c:choose>
                        <form
                                class="form form--pycoupons--tmp"
                                action="phieu/phieu-tra.htm?new#pycoupons"
                                method="POST"
                                modelAttribute="pyCouponModel"
                        >
                            <h5 class="form__title">Trả cho phiếu mượn</h5>
                            <c:if test="${borrowedCouponList.size() == 0}">
                               <span>Không có phiếu mượn nào để trả</span>
                            </c:if>
                            <c:if test="${borrowedCouponList.size() != 0}">
                                <div class="form__item form__item--pycoupons">
                                    <label>Mã phiếu mượn
                                        <div class="select">
                                            <select name="brCouponId">
                                                <c:forEach items="${borrowedCouponList}" var="brCoupon">
                                                   <option value="${brCoupon.couponId}">${brCoupon.couponId}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </label>
                                </div>
                                <div class="form__item--action">
                                    <button type="submit" class="btn--confirm btn--customize">Xác nhận</button>
                                    <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                                </div>
                            </c:if>
                        </form>
                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
