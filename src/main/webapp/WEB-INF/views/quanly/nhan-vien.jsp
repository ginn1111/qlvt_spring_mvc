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
                                        <a href="quanly/nhan-vien/${listNhanVien.get(status.index).maNhanVien}.htm?update#employees">
                                            <span class="table__item--edit">
                                                <ion-icon name="pencil-outline"></ion-icon>
                                            </span>
                                        </a>
                                        <a href="quanly/nhan-vien/${listNhanVien.get(status.index).maNhanVien}.htm?accounts#employees">
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
                            modelAttribute="nhanvien"
                        >
                        <h5 class="form__title">Nhân viên</h5>
                        <form:input type="hidden" path="maNhanVien" />
                        <div class="form__item form__item--employee">
                            <label>Tên
                                <form:input
                                    path="ten"
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
                                    path="SDT"
                                    class="form__input"
                                    placeholder="số điện thoại..." />
                            </label>
                        </div>
                        <div class="form__item form__item--employee">
                            <label>Ngày sinh
                                <form:input
                                    type="date"
                                    path="ngaySinh"
                                    class="form__input"
                                    format="yyyy-MM-dd"
                                />
                            </label>
                        </div>
                        <div class="form__item">
                            <label>Địa chỉ
                                <form:textarea
                                    path="diaChi"
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
                            modelAttribute="taikhoan"
                        >
                            <h5 class="form__title">Thêm tài khoản cho nhân viên</h5>
                            <form:input type="hidden" path="maNV" value="${sessionScope.nhanvien.maNhanVien}"/>
                            <div class="form__item">
                                <label>
                                    ${sessionScope.nhanvien.ten}
                                </label>
                            </div>
                            <div class="form__item">
                                <label>
                                    Email
                                    <form:input class="form__input" path="email" type="email" placeholder="email..."/>
                                </label>
                            </div>
                            <div class="form__item">
                                <label>
                                    Mật khẩu
                                    <form:input class="form__input" path="matkhau" type="password" placeholder="mật khẩu..."/>
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Vai trò
                                    <div class="select">
                                        <form:select
                                            items="${listVaiTro}"
                                            itemLabel="tenVaiTro"
                                            itemValue="maVaiTro"
                                            path="maVaiTro"
                                        />
                                    </div>
                                </label>
                            </div>
                            <div class="form__item accounts-container">
                               <span>Các tài khoản của nhân viên</span>
                                <ul class="list__account custom-scroll-bar">
                                    <li class="card card--hover account">
                                        <span>tk01@gmail.com</span>
                                        <span>Quản lý</span>
                                    </li>
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
