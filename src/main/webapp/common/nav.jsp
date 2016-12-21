<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<ul class="sidebar-menu">
	<li class="header">MAIN NAVIGATION</li>
	<c:set var="cacheuid" value="${cache_user.uid}" />
	<c:set var="m1" value="0" />
	<c:set var="m2" value="0" />
	<c:set var="m3" value="0" />
	<c:set var="menuList" value="${fns:getMenuList(cacheuid)}" />
	<!-- MENU START -->
	
	<c:forEach items="${menuList}" var="menu" varStatus="idxStatus1">
		<c:if test="${menu.parentId eq '1'}">
		<li id="menu-${menu.menuid}" class="treeview">

		  <a href="#"> <i class="fa fa-cog"></i>
		    <span>${menu.mname}</span>
		    <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
		    </span>
		  </a>
		  <c:set var="m1" value="0" />
		  <c:forEach items="${menuList}" var="menu2" varStatus="idxStatus2">
		  	<c:if test="${menu.menuid eq menu2.parentId}">
		  	 <c:if test="${m1 eq 0}">
		 	 <ul class="treeview-menu">
		 	 </c:if>
		 	 <!-- 第二层菜单开始 -->
		 	     <li id="menu-${menu2.menuid}">
			      <a href="${ctxroot}${menu2.href}">
			        <i class="fa fa-circle-o"></i>
			        ${menu2.mname}
			      </a>			    
			    <c:set var="m2" value="0" />
			    <c:forEach items="${menuList}" var="menu3" varStatus="idxStatus3">
				    <c:if test="${menu2.menuid eq menu3.parentId}">
				  	 <c:if test="${m2 eq 0}">
				 	 <ul class="treeview-menu">
				 	 </c:if>
				 	 <!-- 第三层菜单开始 -->
			 	    <li id="menu-${menu3.menuid}">
				      <a href="${ctxroot}${menu3.href}">
				        <i class="fa fa-certificate"></i>
				        ${menu3.mname}
				      </a>
				    </li>
				 	 <c:set var="m2" value="1" />
				 	</c:if>
			    </c:forEach>
			  	<c:if test="${m2 eq 1}">
			 	</ul>
			 	</c:if>
			 	</li>
		 	 <c:set var="m1" value="1" />
			</c:if>
		  </c:forEach>
		  	<c:if test="${m1 eq 1}">
		 	</ul>
		 	</c:if>
		</li>
		</c:if>
	</c:forEach>

	<!-- MENU END -->
</ul>