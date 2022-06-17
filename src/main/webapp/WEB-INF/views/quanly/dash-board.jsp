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

        <!-- Content -->
        <div class="main__content">
            <div class="dash-board">
                <div class="card tickets">
                    <div class="overall">
                        <div class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfInCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="add-circle-outline"></ion-icon>
                                </div>
                                <span>Phiếu nhập</span>
                            </div>
                        </div>
                        <div class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfExCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="remove-circle-outline"></ion-icon>
                                </div>
                                <span>Phiếu xuất</span>
                                </div>
                            </div>
                        <div class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfBorrowedCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="clipboard-outline"></ion-icon>
                                </div>
                                <span>Phiếu mượn</span>
                            </div>
                        </div>
                        <div class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfPayedCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="checkmark-circle-outline"></ion-icon>
                                </div>
                                <span>Phiếu trả</span>
                                </div>
                            </div>
                        <div class="card--hover card detail">
                            <h3 class="detail__number">
                                ${numberOfTransCP}
                            </h3>
                            <div class="detail__des">
                                <div class="detail__icon">
                                    <ion-icon name="move-outline"></ion-icon>
                                </div>
                                <span>Phiếu xuất kho</span>
                                </div>
                            </div>
                    </div>
                    <div class="info-container">
                        <div class="chart-container">
                            <canvas id="my-chart" style="max-height: 80vh; width: 100%"></canvas>
                        </div>
                        <div class="borrowed-tickets">
                            <div class="card--hover card ticket" style="--i:200ms">
                                <span>PM001</span>
                                <span>Nguyễn Văn A</span>
                                <span>20/06/2022</span>
                            </div>
                            <div class="card--hover card ticket" style="--i:400ms">
                                <span>PM001</span>
                                <span>Nguyễn Văn A</span>
                                <span>20/06/2022</span>
                            </div>
                            <div class="card--hover card ticket" style="--i: 600ms">
                                <span>PM001</span>
                                <span>Nguyễn Văn A</span>
                                <span>20/06/2022</span>
                            </div>
                            <div class="card--hover card ticket" style="--i: 800ms">
                                <span>PM001</span>
                                <span>Nguyễn Văn A</span>
                                <span>20/06/2022</span>
                            </div>
                            <div class="card--hover card ticket" style="--i: 1000ms">
                                <span>PM001</span>
                                <span>Nguyễn Văn A</span>
                                <span>20/06/2022</span>
                            </div>
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
