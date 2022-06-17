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
                <%--WORKER--%>
                <div id="workers" class="card manage">
                    <div class="manage__header">
                        <h5 class="header__title">Công nhân</h5>
                        <form
                                action="cong-nhan.htm?search"
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
                            <a href="cong-nhan.htm?new#workers"
                               class="btn--customize btn--add"
                               data-control="workers"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn--remove-workers" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table">
                        <div class="table__head">
                            <span>Mã công nhân</span><span>Tên</span><span>Ngày sinh</span>
                            <span>Địa chỉ</span><span>Số điện thoại</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-workers"
                                   action="cong-nhan.htm?delete#workers" method="POST"
                                   modelAttribute="deletedIdWorkerList">
                            <c:if test="${workerModelList == null || workerModelList.size() == 0}">
                                <h3 style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 30px;">Không có công nhân nào >_<...</h3>
                            </c:if>
                            <c:if test="${workerModelList != null && workerModelList.size() != 0}">
                                <c:forEach items="${deletedIdWorkerList.list}" varStatus="status">
                                    <div class="table__item table__item--workers"
                                         data-control="workers">
                                        <form:checkbox
                                                value="${workerModelList.get(status.index).workerId}"
                                                path="list[${status.index}]" />
                                        <span>${workerModelList.get(status.index).workerId}</span>
                                        <span>${workerModelList.get(status.index).name}</span>
                                        <span><fmt:formatDate value="${workerModelList.get(status.index).dob}" pattern="dd/MM/yyyy"/></span>
                                        <span>${workerModelList.get(status.index).address}</span>
                                        <c:set var="tmp" value="${workerModelList.get(status.index).phone}" />
                                        <c:set var="phone" value="${fn:trim(tmp)}" />
                                        <span>
                                            ${fn:substring(phone, 0, 3)}
                                            ${fn:substring(phone, 3, 6)}
                                            ${fn:substring(phone, 6, 10)}
                                        </span>
                                        <a href="cong-nhan/${workerModelList.get(status.index).workerId}.htm?update#workers">
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
                <%--END WORKER--%>

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
                        <%--FORM WORKER--%>
                        <form:form
                                class="form form--workers"
                                action="${link}#workers"
                                method="POST"
                                modelAttribute="workerModel"
                        >
                            <h5 class="form__title">Công nhân</h5>
                            <form:input type="hidden" path="workerId" />
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
                        <%--END FORM WORKER--%>
                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
