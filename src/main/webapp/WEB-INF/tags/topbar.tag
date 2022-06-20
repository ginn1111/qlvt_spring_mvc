<%@ tag language="java" pageEncoding="UTF-8" %>
<div class="toggle">
	<ion-icon name="menu-outline"></ion-icon>
</div>
<div>
	<h5>Xin Chào
		${role == 'MANAGER' ? 'quản lý' : 'nhân viên'}
		${userInfo.name}
	</h5>
</div>
<div class="user">
	<div class="user__infor">
		<ion-icon name="person-outline"></ion-icon>
		<span class="user__role">${userInfo.name}</span>
	</div>
<%--	<div class="user__refesh">--%>
<%--		<ion-icon name="reload-outline"></ion-icon>--%>
<%--	</div>--%>
</div>
