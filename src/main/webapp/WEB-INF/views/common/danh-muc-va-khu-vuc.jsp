<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/resources/includes/header.jsp" %>

<div class="container">
    <tg:dialog-message message="${message}"/>
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
            <tg:topbar/>
        </section>
        <%--Content --%>
        <div class="main__content">
            <section class="manage__container manage__container--categories-and-sectors">

                <%--CATEGORY --%>
                <div id="categories" class="card manage manage--categories-and-sectors">
                    <div class="manage__header">
                        <h5 style="width: 30%" class="header__title">Danh mục</h5>
                        <form
                                action="danh-muc-va-khu-vuc/danh-muc.htm?search"
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
                                    placeholder="danh mục, khu vực..."
                            />
                        </form>
                        <div class="header__controller">
                            <a href="danh-muc-va-khu-vuc/danh-muc.htm?new#categories"
                               class="btn--customize btn--add"
                               data-control="categories"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn--remove-categories" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table manage--categories">
                        <div class="table__head">
                            <span>Mã danh mục</span><span>Tên danh mục</span>
                            <span>Khu vực</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-categories"
                                   action="danh-muc-va-khu-vuc/danh-muc.htm?delete#categories" method="POST"
                                   modelAttribute="deletedCategoryIdList">
                            <c:if test="${categoryModelList == null || categoryModelList.size() == 0}">
                                    <h3 class="nothing">Không có danh mục nào >_<...</h3>
                            </c:if>
                            <c:if test="${categoryModelList != null && categoryModelList.size() != 0}">
                                <c:forEach items="${deletedCategoryIdList.list}" varStatus="status">
                                    <div class="table__item table__item--categories"
                                         data-control="categories">
                                        <c:set var="category" value="${categoryModelList.get(status.index)}"/>
                                        <form:checkbox value="${category.categoryId}" path="list[${status.index}]"/>
                                        <span>${category.categoryId}</span>
                                        <span>${category.name}</span>
                                        <span>${category.sectorModel.name}</span>
                                        <a href="danh-muc-va-khu-vuc/danh-muc/${category.categoryId}.htm?update#categories">
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
                <%--END CATEGORIES--%>

                <%--SECTORS --%>
                <div id="sectors" class="card manage manage--categories-and-sectors">
                    <div class="manage__header">
                        <h5 class="header__title">Khu vực</h5>
                        <form action="danh-muc-va-khu-vuc/khu-vuc.htm?search"
                              method="POST"
                              class="search-box">
                            <ion-icon name="search-outline" class="search__icon"></ion-icon>
                            <input
                                    required="required"
                                    name="data"
                                    type="search"
                                    placeholder="khu vực..."
                            />
                        </form>
                        <div class="header__controller">
                            <a href="danh-muc-va-khu-vuc/khu-vuc.htm?new#sectors"
                               class="btn--customize btn--add"
                               data-control="sectors"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn--remove-sectors" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table manage__table--sectors">
                        <div class="table__head">
                            <span>Mã khu vực</span><span>Tên khu vực</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-sectors"
                                   action="danh-muc-va-khu-vuc/khu-vuc.htm?delete#sectors" method="POST"
                                   modelAttribute="deletedSectorIdList">
                            <c:if test="${sectorModelList == null || sectorModelList.size() == 0}">
                                <h3 class="nothing"> Không có khu vực nào >_<...</h3>
                            </c:if>
                            <c:if test="${sectorModelList != null && sectorModelList.size() != 0}">
                                <c:forEach items="${deletedSectorIdList.list}" varStatus="status">
                                    <div class="table__item table__item--sectors"
                                         data-control="sectors">
                                        <c:set value="${sectorModelList.get(status.index)}" var="sector" />
                                        <form:checkbox value="${sector.sectorId}" path="list[${status.index}]"/>
                                        <span>${sector.sectorId}</span>
                                        <span>${sector.name}</span>
                                        <a href="danh-muc-va-khu-vuc/khu-vuc/${sector.sectorId}.htm?update#sectors">
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
                <%--END SECTORS--%>

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
                        <%--FORM CATEGORIES--%>
                        <form:form
                                class="form form--categories"
                                action="${link}#categories"
                                method="POST"
                                modelAttribute="categoryModel"
                        >
                            <h5 class="form__title">Danh mục</h5>
                            <c:if test="${sectorModelList == null || sectorModelList.size() == 0}">
                               <span>Không có khu vực nào!</span>
                            </c:if>
                                <c:if test="${sectorModelList.size() > 0}">
                                <form:input type="hidden" path="categoryId"/>
                                <div class="form__item form__item--employee">
                                    <label>Tên danh mục
                                        <form:input
                                                path="name"
                                                class="form__input"
                                                placeholder="tên danh mục..."
                                                required="required"
                                        />
                                    </label>
                                </div>
                                <div class="form__item">
                                    <label>Khu vực
                                        <div class="select">
                                            <form:select
                                                items="${sectorModelList}"
                                                itemLabel="name"
                                                itemValue="sectorId"
                                                path="sectorModel.sectorId"
                                            />
                                        </div>
                                    </label>
                                </div>
                                <div class="form__item--action">
                                    <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
                                    <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                                </div>
                            </c:if>
                        </form:form>
                        <%--END FORM CATEGORIES--%>

                        <%--FORM SECTORS--%>
                        <form:form
                                class="form form--sectors"
                                action="${link}#sectors"
                                method="POST"
                                modelAttribute="sectorModel"
                        >
                            <h5 class="form__title">Khu vực</h5>
                            <form:input type="hidden" path="sectorId"/>
                            <div class="form__item form__item--employee">
                                <label>Tên khu vực
                                    <form:input
                                            path="name"
                                            class="form__input"
                                            placeholder="tên khu vực..."
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item--action">
                                <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
                                <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                            </div>
                        </form:form>
                        <%--END FORM SECTORS--%>
                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp" %>
