<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

                <%--SUPPLY--%>
                <div id="supplies" class="card manage">
                    <div class="manage__header">
                        <h5 class="header__title">Vật tư</h5>
                        <form
                                action="vat-tu.htm?search"
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
                                    placeholder="tên vật tư hoặc danh mục..."
                            />
                        </form>
                        <div class="header__controller">
                            <a href="vat-tu.htm?new#supplies"
                               class="btn--customize btn--add"
                               data-control="supplies"
                            >
                                Thêm
                            </a>
                            <button class="btn--customize btn--remove btn--remove-supplies" disabled>Xoá</button>
                        </div>
                    </div>
                    <div class="manage__table">
                        <div class="table__head table__head--supplies">
                            <span>Mã vật tư</span><span>Tên vật tư</span>
                            <span>Đơn vị</span><span>Danh mục</span>
                            <span>Nhà sản xuất</span><span>Số lượng</span>
                        </div>
                        <form:form class="table__body custom-scroll-bar form-supplies"
                                   action="vat-tu.htm?delete#supplies" method="POST"
                                   modelAttribute="deletedSupplyIdList">
                            <c:if test="${supplyModelList == null || supplyModelList.size() == 0}">
                                    <h3 class="nothing">Không có vật tư nào >_<...</h3>
                            </c:if>
                            <c:if test="${supplyModelList != null && supplyModelList.size() != 0}">
                                <c:forEach items="${deletedSupplyIdList.list}" varStatus="status">
                                    <div class="table__item table__item--supplies"
                                         data-control="supplies">
                                        <c:set var="supplyModel" value="${supplyModelList.get(status.index)}" />
                                        <form:checkbox
                                                value="${supplyModel.supplyId}"
                                                path="list[${status.index}]" />
                                        <span>${supplyModel.supplyId}</span>
                                        <span>${supplyModel.name}</span>
                                        <span>${supplyModel.unit}</span>
                                        <span>${supplyModel.categoryModel.name}</span>
                                        <span>${supplyModel.producer}</span>
                                        <span>${supplyModel.quantity}</span>
                                        <a href="vat-tu/${supplyModel.supplyId}.htm?update#supplies">
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
                <%--END SUPPLIES--%>

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
                        <%--FORM SUPPLIES--%>
                        <form:form
                                class="form form--supplies"
                                action="${link}#supplies"
                                method="POST"
                                modelAttribute="supplyModel"
                        >
                            <h5 class="form__title">Vật tư</h5>
                            <form:input type="hidden" path="supplyId" />
                            <div class="form__item">
                                <label>Tên vật tư
                                    <form:input
                                            path="name"
                                            class="form__input"
                                            placeholder="tên..."
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Nhà sản xuất
                                    <form:input
                                            path="producer"
                                            class="form__input"
                                            placeholder="nhà sản xuất..."
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Danh mục
                                    <div class="select">
                                        <form:select
                                                items="${categoryModelList}"
                                                itemLabel="name"
                                                itemValue="categoryId"
                                                path="categoryModel.categoryId"
                                        />
                                    </div>
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Đơn vị
                                    <form:input
                                            path="unit"
                                            class="form__input"
                                            placeholder="đơn vị..."
                                            required="required"
                                    />
                                </label>
                            </div>
                            <div class="form__item">
                                <label>Số lượng
                                    <form:input
                                            path="quantity"
                                            class="form__input"
                                            required="required"
                                            placeholder="số lượng..."
                                    />
                                </label>
                            </div>
                            <div class="form__item--action">
                                <button type="submit" class="btn--confirm btn--customize">${btnTitle}</button>
                                <button type="reset" class="btn--cancel btn--customize">Huỷ</button>
                            </div>
                        </form:form>
                        <%--END FORM SUPPLIES--%>
                    </dialog>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
