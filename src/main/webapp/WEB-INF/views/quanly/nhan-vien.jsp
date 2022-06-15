<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/resources/includes/header.jsp"%>

<div class="container">
    <tg:dialog-message message="${message}" />
    <!-- Navigation -->
    <nav class="navigation active">
        <tg:navigation prefix="quanly"
                       link1="./nhan-vien.htm" link2="./tai-khoan.htm" link3="./phieu.htm" link4="./cong-nhan.htm"
                       link5="./nha-cung-cap.htm" link6="./cong-trinh-va-kho.htm" link7="danh-muc-va-khu-vuc.htm"
                       uri1="nhan-vien" uri2="tai-khoan" uri3="phieu" uri4="cong-nhan" uri5="nha-cung-cap"
                       uri6="cong-trinh-va-kho" uri7="danh-muc-va-khu-vuc"
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
                <!-- EMPLOYEES -->
                <div id="employees" class="card manage manage--employees">
                    <div class="manage__header">
                        <h5 class="header__title">Nhân viên</h5>
                        <form
                                action="manager/manage/employees/search.htm"
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
                            <a href="manager/manage/employees.htm?new#employees"
                               class="btn--customize btn--add"
                               data-control="employees"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn-remove-employees" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table">
                        <div class="table__head">
                            <span>Mã nhân viên</span><span>Tên</span><span>Ngày sinh</span>
                            <span>Địa chỉ</span><span>Số điện thoại</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-employees"
                                   action="manager/manage/employees.htm?delete#employees" method="POST"
                                   modelAttribute="listNhanVienMuonXoa">
                            <c:if test="${listNhanVien == null || listNhanVien.size() == 0}">
                               <h3 style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 30px;">Không có nhân viên nào >_<...</h3>
                            </c:if>
                            <c:if test="${listNhanVien != null && listNhanVien.size() != 0}">
                                <c:forEach items="${listNhanVienMuonXoa.list}" varStatus="status">
                                    <div class="table__item table__item--employees"
                                         data-control="employees">
                                        <form:checkbox
                                                value="${listNhanVien.get(status.index).maNhanVien}"
                                                path="list[${status.index}]" />
                                        <span>${listNhanVien.get(status.index).maNhanVien}</span>
                                        <span>${listNhanVien.get(status.index).ten}</span>
                                        <span><fmt:formatDate value="${listNhanVien.get(status.index).ngaySinh}" pattern="dd/MM/yyyy"/></span>
                                        <span>${listNhanVien.get(status.index).diaChi}</span>
                                        <c:set var="tmp" value="${listNhanVien.get(status.index).SDT}" />
                                        <c:set var="phone" value="${fn:trim(tmp)}" />
                                        <span>
                                            ${fn:substring(phone, 0, 3)}
                                            ${fn:substring(phone, 3, 6)}
                                            ${fn:substring(phone, 6, 10)}
                                        </span>
                                        <a
                                                href="manager/manage/employees/${listNhanVien.get(status.index).maNhanVien}.htm?edit#employees">
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
                <!-- EMPLOYEES END -->

                <!-- CONFIRM DIALOG -->
                <dialog class="dialog">
                    <h5 class="dialog__title">Notify</h5>
                    <span class="dialog__message">Are you sure about that?</span>
                    <div class="dialog__control">
                        <button class="btn--customize btn-confirm btn--warning">Delete</button>
                        <button class="btn--customize btn-cancel btn--safe">Cancel</button>
                    </div>
                </dialog>
                <div class="backdrop">
                    <dialog class="modal">

                        <!-- CHANGE PASSWORD FOR EMP -->
                        <form class="form form--change-password" action="manager/manage/employees/change-password.htm#employees" method="POST">
                            <h5 class="form__title">Change password</h5>
                            <div class="form__item">
                                <label>
                                    ${employee.idEmployee} - ${employee.fullNameAndPosition}
                                </label>
                            </div>
                            <div class="form__item">
                                <label>
                                    New password
                                    <input class="form__input" name="new-password" type="password" placeholder="new password..."/>
                                </label>
                            </div>
                            <div class="form__item--action">
                                <button type="submit" class="btn-confirm btn--customize">Change</button>
                                <button type="reset" class="btn-cancel btn--customize">Cancel</button>
                            </div>
                        </form>
                        <!-- CHANGE PASSWORD FOR EMP END-->

                        <!-- FORM EMPLOYEES -->
>
                        <!-- FORM EMPLOYEES END-->
                    </dialog>
                </div>
            </section>
        </div>

        <div class="backdrop">
            <dialog class="modal">
            </dialog>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
