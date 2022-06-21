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
        <!-- Toolbar -->

        <section class="topbar">
            <tg:topbar />
        </section>

        <!-- Content -->
        <div class="main__content">
            <div class="dash-board">
                <div class="card tickets">
                   <h5 class="tickets__title">Số lượng các loại phiếu được lập trong tháng</h5>
                    <div class="overall">
                        <a href="phieu/phieu-nhap.htm" class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfInCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="add-circle-outline"></ion-icon>
                                </div>
                                <span>Phiếu nhập</span>
                            </div>
                        </a>
                        <a href="phieu/phieu-xuat.htm" class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfExCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="remove-circle-outline"></ion-icon>
                                </div>
                                <span>Phiếu xuất</span>
                            </div>
                        </a>
                        <a href="phieu/phieu-muon.htm" class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfBorrowedCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="clipboard-outline"></ion-icon>
                                </div>
                                <span>Phiếu mượn</span>
                            </div>
                        </a>
                        <a href="phieu/phieu-tra.htm" class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfPayedCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="checkmark-circle-outline"></ion-icon>
                                </div>
                                <span>Phiếu trả</span>
                                </div>
                            </a>
                        <a href="phieu/phieu-chuyen-kho.htm" class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfTransCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="move-outline"></ion-icon>
                                </div>
                                <span>Phiếu chuyển kho</span>
                                </div>
                            </a>
                    </div>
                    <div class="info-container">
                        <div style="display: flex; flex-flow: column nowrap; width: 60%">
                            <div class="chart-container chart-container__in-port">
                                <%@ include file="/WEB-INF/views/common/chart-in-port.jsp" %>
                            </div>
                            <div class="chart-container chart-container__ex-port">
                                <%@ include file="/WEB-INF/views/common/chart-ex-port.jsp" %>
                            </div>
                        </div>
                        <div class="borrowed-tickets">
                            <%@ include file="/WEB-INF/views/common/top-10-phieu-muon-toi-han.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
