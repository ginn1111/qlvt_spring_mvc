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
                        <div class="chart-container">
                            <canvas id="my-chart" style="max-height: 80vh; width: 100%"></canvas>
                        </div>
                        <div class="borrowed-tickets">
                            <c:if test="${top5BrCouponMaturityInMonth.size() == 0}">
                               <h4>Các phiếu mượn đã được trả!</h4>
                            </c:if>
                            <c:if test="${top5BrCouponMaturityInMonth.size() != 0}">
                                <span>Top 5 các phiếu mượn gần tới hạn</span>
                                <c:forEach items="${top5BrCouponMaturityInMonth}" var="maturityBrCP">
                                    <div class="card--hover card ticket" style="--i:200ms">
                                        <span>${maturityBrCP.couponId}</span>
                                        <span>Công nhân: ${maturityBrCP.employeeModel.name}</span>
                                        <span>Ngày trả: <fmt:formatDate value="${maturityBrCP.payedDate}" pattern="dd-MM-yyyy"/></span>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- FROM -->
        <div class="backdrop">
            <dialog class="modal">
            </dialog>
        </div>
    </div>
</div>
<%@include file="/resources/includes/footer.jsp"%>
