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
            <section class="manage__container manage__container--construction-and-warehouse">

                <%--CONSTRUCTION --%>
                <div id="constructions" class="card manage manage--construction-and-warehouse">
                    <div class="manage__header">
                        <h5 class="header__title">Công trình</h5>
                        <form
                                action="kho-va-cong-trinh/cong-trinh.htm?search"
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
                                    placeholder="tên công trình..."
                            />
                        </form>
                        <div class="header__controller">
                            <a href="kho-va-cong-trinh/cong-trinh.htm?new#constructions"
                               class="btn--customize btn--add"
                               data-control="constructions"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn--remove-constructions" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table manage--constructions">
                        <div class="table__head">
                            <span>Mã công trình</span><span>Tên công trình</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-constructions"
                                   action="kho-va-cong-trinh/cong-trinh.htm?delete#constructions" method="POST"
                                   modelAttribute="deletedConstructionIdList">
                            <c:if test="${constructionModelList == null || constructionModelList.size() == 0}">
                                    <h3 class="nothing">Không có công trình nào >_<...</h3>
                            </c:if>
                            <c:if test="${constructionModelList != null && constructionModelList.size() != 0}">
                                <c:forEach items="${deletedConstructionIdList.list}" varStatus="status">
                                    <div class="table__item table__item--constructions"
                                         data-control="constructions">
                                        <form:checkbox
                                                value="${constructionModelList.get(status.index).constructionId}"
                                                path="list[${status.index}]"/>
                                        <span>${constructionModelList.get(status.index).constructionId}</span>
                                        <span>${constructionModelList.get(status.index).name}</span>
                                        <a href="kho-va-cong-trinh/cong-trinh/${constructionModelList.get(status.index).constructionId}.htm?update#constructions">
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
                <%--END CONSTRUCTION--%>

                <%--WAREHOUSE --%>
                <div id="warehouses" class="card manage manage--construction-and-warehouse">
                    <div class="manage__header">
                        <h5 class="header__title">Kho</h5>
                        <form action="kho-va-cong-trinh/kho.htm?search"
                              method="POST"
                              class="search-box">
                            <ion-icon name="search-outline" class="search__icon"></ion-icon>
                            <input
                                    required="required"
                                    name="data"
                                    type="search"
                                    placeholder="tên kho..."
                            />
                        </form>
                        <div class="header__controller">
                            <a href="kho-va-cong-trinh/kho.htm?new#warehouses"
                               class="btn--customize btn--add"
                               data-control="warehouses"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn--remove-warehouses" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table manage__table--warehouses">
                        <div class="table__head">
                            <span>Mã kho</span><span>Tên kho</span><span>Địa chỉ</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-warehouses"
                                   action="kho-va-cong-trinh/kho.htm?delete#warehouses" method="POST"
                                   modelAttribute="deletedWarehouseIdList">
                            <c:if test="${warehouseModelList == null || warehouseModelList.size() == 0}">
                                    <h3 class="nothing">Không có kho nào >_<...</h3>
                            </c:if>
                            <c:if test="${warehouseModelList != null && warehouseModelList.size() != 0}">
                                <c:forEach items="${deletedWarehouseIdList.list}" varStatus="status">
                                    <div class="table__item table__item--warehouses"
                                         data-control="warehouses">
                                        <form:checkbox
                                                value="${warehouseModelList.get(status.index).warehouseId}"
                                                path="list[${status.index}]"/>
                                        <span>${warehouseModelList.get(status.index).warehouseId}</span>
                                        <span>${warehouseModelList.get(status.index).name}</span>
                                        <span>${warehouseModelList.get(status.index).address}</span>
                                        <a href="kho-va-cong-trinh/kho/${warehouseModelList.get(status.index).warehouseId}.htm?update#warehouses">
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
                <%--END WAREHOUSE--%>

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
                        <%--FORM CONSTRUCTIONS--%>
                        <form:form
                                class="form form--constructions"
                                action="${link}#constructions"
                                method="POST"
                                modelAttribute="constructionModel"
                        >
                            <h5 class="form__title">Công trình</h5>
                            <form:input type="hidden" path="constructionId"/>
                            <div class="form__item form__item--employee">
                                <label>Tên
                                    <form:input
                                            path="name"
                                            class="form__input"
                                            placeholder="tên công trình..."
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item--action">
                                <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
                                <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                            </div>
                        </form:form>
                        <%--END FORM CONSTRUCTIONS--%>

                        <%--FORM WAREHOUSES--%>
                        <form:form
                                class="form form--warehouses"
                                action="${link}#warehouses"
                                method="POST"
                                modelAttribute="warehouseModel"
                        >
                            <h5 class="form__title">Kho</h5>
                            <form:input type="hidden" path="warehouseId"/>
                            <div class="form__item form__item--employee">
                                <label>Tên kho
                                    <form:input
                                            path="name"
                                            class="form__input"
                                            placeholder="tên công trình..."
                                            required="required"
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
                        <%--END FORM WAREHOUSES--%>
                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp" %>
