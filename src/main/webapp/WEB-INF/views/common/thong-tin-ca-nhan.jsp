<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/resources/includes/header.jsp" %>

<div class="container">
    <tg:dialog-message message="${message}"/>
    <!-- Navigation -->
    <nav class="navigation active">
        <c:if test="${role == 'MANAGER'}">
            <tg:quanly-nav/>
        </c:if>
        <c:if test="${role == 'EMPLOYEE'}">
            <tg:nhanvien-nav/>
        </c:if>
    </nav>
    <div class="main active">

        <!-- Tool bar -->
        <section class="topbar">
            <tg:topbar/>
        </section>
        <%--Content --%>
        <div class="main__content">
            <section class="manage__container manage__container--information">
                <%--INFORMATION--%>
                <div id="information" class="card manage">
                    <div class="manage__header">
                        <h5 class="header__title">Thông tin cá nhân</h5>
                    </div>
                    <div class="manage__table">
                        <form:form
                                class="table__body form custom-scroll-bar form-infor"
                                action="information.htm?update#infor" method="POST"
                                modelAttribute="userInfo"
                        >
                            <form:input type="hidden" path="employeeId" />
                            <div class="form__item">
                                <label>Tên
                                    <form:input
                                            path="name"
                                            class="form__input"
                                            placeholder="tên..."
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Số điện thoại
                                    <form:input
                                            type="number"
                                            path="phone"
                                            class="form__input"
                                            placeholder="số điện thoại..."
                                    />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Ngày sinh
                                    <form:input
                                            path="dob"
                                            type="date"
                                            class="form__input"
                                            placeholder="ngày sinh..."
                                    />
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
                                <button type="submit" class="btn--confirm btn--customize">Xác nhận</button>
                                <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                            </div>
                        </form:form>
                    </div>
                </div>
                <%--END INFORMATION--%>

                <%--ACCOUNTS--%>
                <div id="inforaccounts" class="card manage">
                    <div class="manage__header">
                        <h5 class="header__title">Tài khoản</h5>
                    </div>
                    <div class="manage__table">
                        <div class="table__head">
                            <span>Email</span>
                            <span>Vai trò</span>
                        </div>
                        <div class="table__body custom-scroll-bar">
                            <c:forEach items="${accountModelList}" var="account">
                                <div class="table__item table__item--inforaccounts" data-control="inforaccounts">
                                    <span>${account.email}</span>
                                    <span>${roleModelMap.get(account.roleId).roleName.roleName}</span>
                                    <a href="information/${account.email}.htm?change-password#inforaccounts">
                                        <span class="table__item--edit">
                                            <ion-icon name="key-outline"></ion-icon>
                                        </span>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <%--END ACCOUNTS--%>

                <%--FORM CHANGE PASSWORD--%>
                <div class="backdrop">
                    <dialog class="modal">
                        <form:form
                                class="form form--inforaccounts"
                                action="information.htm?change-password#inforaccounts"
                                method="POST"
                                modelAttribute="changeAccount"
                        >
                            <h5 class="form__title">Đổi mật khẩu</h5>
                            <div class="form__item">
                                <label>Email
                                    <form:input
                                            path="email"
                                            class="form__input"
                                            required="required"
                                            readonly="true"
                                    />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Mật khẩu cũ
                                    <form:input
                                            path="oldPassword"
                                            class="form__input"
                                            required="required"
                                            type="password"
                                    />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Mật khẩu mới
                                    <form:input
                                            path="newPassword"
                                            type="password"
                                            class="form__input"
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item--action">
                                <button type="submit" class="btn--confirm btn--customize">Xác nhận</button>
                                <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                            </div>
                        </form:form>
                        <%--END FORM CHANGE PASSWORD--%>
                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp" %>
