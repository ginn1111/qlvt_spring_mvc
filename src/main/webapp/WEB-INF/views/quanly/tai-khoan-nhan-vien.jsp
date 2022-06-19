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
                <div class="dash-board">
                    <div class="card">
                        <h3>Update soon...</h3>
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
