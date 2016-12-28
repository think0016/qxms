<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- User Account: style can be found in dropdown.less -->
<li class="dropdown user user-menu"><a href="#"
	class="dropdown-toggle" data-toggle="dropdown"> <img
		src="${ctxStatic}/dist/img/user2-160x160.jpg" class="user-image"
		alt="User Image"> <span class="hidden-xs">${sessionScope.cache_user.turename}</span>
</a>
	<ul class="dropdown-menu">
		<!-- User image -->
		<li class="user-header"><img
			src="${ctxStatic}/dist/img/user2-160x160.jpg" class="img-circle"
			alt="User Image">

			<p>
				${sessionScope.cache_user.turename}
			</p></li>
		<!-- Menu Body -->
		<!-- Menu Footer-->
		<li class="user-footer">
			<div class="pull-left">
				<a href="${ctxroot}/user/userinfo" class="btn btn-default btn-flat">个人信息</a>
			</div>
			<div class="pull-right">
				<a href="${ctxroot}/logout" class="btn btn-default btn-flat">退出登录</a>
			</div>
		</li>
	</ul></li>