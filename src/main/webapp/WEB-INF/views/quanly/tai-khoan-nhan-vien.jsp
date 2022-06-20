<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/resources/includes/header.jsp"%>

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

                <%--ACCOUNT--%>
                    <div id="empaccounts" class="card manage">
                        <div class="manage__header">
                            <h5 class="header__title">Tài khoản nhân viên</h5>
                            <form
                                    action="quanly/tai-khoan-nhan-vien.htm?search"
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
                                        placeholder="email, nhân viên..."
                                />
                            </form>
                            <div class="header__controller">
                                <button class="btn--customize btn--remove btn--remove-empaccounts" disabled>Xoá</button>
                            </div>
                        </div>
                        <div class="manage__table">
                            <div class="table__head table__head--empaccounts">
                                <span>Email</span><span>Nhân viên</span> <span>Vai trò</span>
                            </div>
                            <form:form class="table__body custom-scroll-bar form-empaccounts"
                                       action="quanly/tai-khoan-nhan-vien.htm?delete#empaccounts" method="POST"
                                       modelAttribute="deletedAccountIdList">
                                <c:if test="${accountModelList == null || accountModelList.size() == 0}">
                                    <h3 class="nothing">Không có tài khoản nào >_<...</h3>
                                </c:if>
                                <c:if test="${accountModelList != null && accountModelList.size() != 0}">
                                    <c:forEach items="${deletedAccountIdList.list}" varStatus="status">
                                        <div class="table__item table__item--empaccounts" data-control="empaccounts">
                                            <c:set var="accountModel" value="${accountModelList.get(status.index)}" />
                                            <form:checkbox value="${accountModel.email}" path="list[${status.index}]" />
                                            <span>${accountModel.email}</span>
                                            <span>${accountModel.employeeModel.name}</span>
                                            <span>${roleModelMap.get(accountModel.roleId).roleName.roleName}</span>
                                            <span class="table__item--delete"> <ion-icon
                                                name="trash-outline"></ion-icon>
                                            </span>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </form:form>
                        </div>
                    </div>
                <%--END ACCOUNT--%>

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
                        <%--FORM ACCOUNT--%>

                        <%--END ACCOUNT--%>
                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
