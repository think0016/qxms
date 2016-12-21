<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
        <ul class="sidebar-menu">
          <li class="header">MAIN NAVIGATION</li>

          <li class="treeview">
            <a href="#"> <i class="fa fa-folder"></i>
              <span>用户中心</span>
              <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
              </span>
            </a>
            <ul class="treeview-menu">
              <li>
                <a href="${ctxroot}/user">
                  <i class="fa fa-circle-o"></i>
                  用户管理
                </a>
              </li>
              <li>
                <a href="${ctxroot}/user/pwform">
                  <i class="fa fa-circle-o"></i>
                  密码修改
                </a>
              </li>
            </ul>
          </li>

          <li class="">
            <a href="${ctxroot}/department">
              <i class="fa fa-circle-o"></i>
              <span>组织机构</span>
            </a>
          </li>
          <li>
            <a href="${ctxroot}/role">
              <i class="fa fa-circle-o"></i>
              <span>角色管理</span>
            </a>
          </li>
          <li>
            <a href="${ctxroot}/menu">
              <i class="fa fa-circle-o"></i>
              <span>菜单管理</span>
            </a>
          </li>

        </ul>