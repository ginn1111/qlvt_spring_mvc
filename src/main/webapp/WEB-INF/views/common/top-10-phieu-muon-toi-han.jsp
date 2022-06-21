<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${top10BrCouponMaturityInMonth.size() == 0}">
    <h4>Các phiếu mượn đã được trả!</h4>
</c:if>
<c:if test="${top10BrCouponMaturityInMonth.size() != 0}">
    <span>Top 10 các phiếu mượn gần tới hạn</span>
    <c:forEach items="${top10BrCouponMaturityInMonth}" var="maturityBrCP" varStatus="status">
        <div class="card--hover card ticket" style="--i:${(status.index + 1) * 300}ms">
            <span>${maturityBrCP.couponId}</span>
            <span>Công nhân: ${maturityBrCP.workerModel.name}</span>
            <span>Ngày trả: <fmt:formatDate value="${maturityBrCP.payedDate}" pattern="dd-MM-yyyy"/></span>
        </div>
    </c:forEach>
</c:if>
