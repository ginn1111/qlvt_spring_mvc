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
		<div class="user__settings">
			<ion-icon name="settings-outline"></ion-icon>
			<div style="display: none" class="card user__settings__panel">
				<ul>
					<li> <a href="information.htm">
						<ion-icon name="information-outline"></ion-icon>
						Thông tin</a></li>
					<li> <a href="logout">
						<ion-icon name="log-out-outline"></ion-icon>
						Đăng xuất</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
