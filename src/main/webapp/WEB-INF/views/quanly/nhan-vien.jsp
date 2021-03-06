<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/resources/includes/header.jsp"%>

<div class="container">
    <tg:dialog-message message="${message}" />
    <!-- Navigation -->
    <nav class="navigation active">
        <tg:quanly-nav />
    </nav>
    <div class="main active">

        <!-- Tool bar -->
        <section class="topbar">
            <tg:topbar />
        </section>
        <%--Content --%>
        <div class="main__content">
            <section class="manage__container">
                <!-- EMPLOYEES -->
                <div id="employees" class="card manage">
                    <div class="manage__header">
                        <h5 class="header__title">Nhân viên</h5>
                        <form
                                action="quanly/nhan-vien.htm?search"
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
                                    placeholder="tên nhân viên..."
                            />
                        </form>
                        <div class="header__controller">
                            <a href="quanly/nhan-vien.htm?new#employees"
                               class="btn--customize btn--add"
                               data-control="employees"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn--remove-employees" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table">
                        <div class="table__head">
                            <span>Mã nhân viên</span><span>Tên</span><span>Ngày sinh</span>
                            <span>Địa chỉ</span><span>Số điện thoại</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-employees"
                                   action="quanly/nhan-vien.htm?delete#employees" method="POST"
                                   modelAttribute="deletedIdEmployeeList">
                            <c:if test="${employeeModelList == null || employeeModelList.size() == 0}">
                                   <h3 class="nothing">Không có nhân viên nào >_<...</h3>
                            </c:if>
                            <c:if test="${employeeModelList != null && employeeModelList.size() != 0}">
                                <c:forEach items="${deletedIdEmployeeList.list}" varStatus="status">
                                    <div class="table__item table__item--employees"
                                         data-control="employees">
                                        <form:checkbox
                                                value="${employeeModelList.get(status.index).employeeId}"
                                                path="list[${status.index}]" />
                                        <span>${employeeModelList.get(status.index).employeeId}</span>
                                        <span>${employeeModelList.get(status.index).name}</span>
                                        <span><fmt:formatDate value="${employeeModelList.get(status.index).dob}" pattern="dd/MM/yyyy"/></span>
                                        <span>${employeeModelList.get(status.index).address}</span>
                                        <c:set var="tmp" value="${employeeModelList.get(status.index).phone}" />
                                        <c:set var="phone" value="${fn:trim(tmp)}" />
                                        <span>
                                            ${fn:substring(phone, 0, 3)}
                                            ${fn:substring(phone, 3, 6)}
                                            ${fn:substring(phone, 6, 10)}
                                        </span>
                                        <a href="quanly/nhan-vien/${employeeModelList.get(status.index).employeeId}.htm?update#employees">
                                            <span class="table__item--edit">
                                                <ion-icon name="pencil-outline"></ion-icon>
                                            </span>
                                        </a>
                                        <a href="quanly/nhan-vien/${employeeModelList.get(status.index).employeeId}.htm?accounts#employees">
                                            <span class="table__item--accounts" data-control="accounts">
                                                <ion-icon name="key"></ion-icon>
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
                <!-- EMPLOYEES END -->

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

                    <%--FORM NHAN VIEN--%>
                        <form:form
                            class="form form--employees"
                            action="${link}#employees"
                            method="POST"
                            modelAttribute="employeeModel"
                        >
                        <h5 class="form__title">Nhân viên</h5>
                        <form:input type="hidden" path="employeeId" />
                        <div class="form__item form__item--employee">
                            <label>Tên
                                <form:input
                                    path="name"
                                    class="form__input"
                                    placeholder="Tên..."
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
                        <div class="form__item form__item--employee">
                            <label>Ngày sinh
                                <form:input
                                    type="date"
                                    path="dob"
                                    class="form__input"
                                    format="yyyy-MM-dd"
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
                            <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
                            <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                        </div>
                    </form:form>
                    <%--END FORM NHAN VIEN--%>

                    <%--FORM THEM TAI KHOAN--%>
                        <form:form
                            class="form form--accounts"
                            action="quanly/nhan-vien.htm?accounts#employees"
                            method="POST"
                            modelAttribute="accountModel"
                        >
                            <h5 class="form__title">Thêm tài khoản cho nhân viên</h5>
                            <form:input type="hidden" path="employeeModel.employeeId" value="${sessionScope.employeeModel.employeeId}"/>
                            <div class="form__item">
                                <label>
                                    ${sessionScope.employeeModel.name}
                                </label>
                            </div>
                            <div class="form__item">
                                <label>
                                    Email
                                    <form:input
                                            class="form__input"
                                            path="email"
                                            type="email"
                                            required="required"
                                            placeholder="email..."/>
                                </label>
                            </div>
                            <div class="form__item">
                                <label>
                                    Mật khẩu
                                    <form:input
                                            class="form__input"
                                            path="password"
                                            type="password"
                                            placeholder="mật khẩu..."
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Vai trò
                                    <div class="select">
                                        <form:select
                                            items="${roleList}"
                                            itemLabel="roleName.roleName"
                                            itemValue="roleId"
                                            path="roleId"
                                        />
                                    </div>
                                </label>
                            </div>
                            <div class="form__item accounts-container">
                                <ul class="list__account custom-scroll-bar" style="overflow-y: auto">
                                    <c:forEach items="${sessionScope.accountModelList}" var="account">
                                        <li class="card card--hover account supplies supplies--des">
                                            <span>${account.email}</span>
                                            <span>${accountMapRole.get(account.roleId).roleName.roleName}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="form__item--action">
                                <button type="submit" class="btn--confirm btn--customize">Thêm</button>
                                <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                            </div>
                        </form:form>
                    <%--END FORM THEM TAI KHOAN--%>

                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
