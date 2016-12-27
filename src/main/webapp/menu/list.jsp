<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>权限管理系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${ctxStatic}/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${ctxStatic}/plugins/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${ctxStatic}/plugins/other/ionicons.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="${ctxStatic}/plugins/datatables/dataTables.bootstrap.css">
  <!-- ztree -->
  <link rel="stylesheet" href="${ctxStatic}/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <!-- Alert -->
  <link rel="stylesheet" href="${ctxStatic}/plugins/Alert/Alert.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${ctxStatic}/dist/css/admincore.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${ctxStatic}/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">
    /*ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:600px;height:360px;overflow-y:scroll;overflow-x:auto;}*/
  </style>
  <script type="text/javascript">var rooturl = '${ctxroot}'; </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
  <!-- Site wrapper -->
  <div class="wrapper">

    <header class="main-header">
      <!-- Logo -->
      <a href="" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"> <b>A</b>
          LT
        </span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"> <b>Admin</b>
          LTE
        </span>
      </a>
      <!-- Header Navbar: style can be found in header.less -->
      <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">
            <!-- Messages: style can be found in dropdown.less-->

            <!-- Notifications: style can be found in dropdown.less -->

            <!-- Tasks: style can be found in dropdown.less -->

            <!-- User Account: style can be found in dropdown.less -->
            <li class="dropdown user user-menu">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <img src="${ctxStatic}/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                <span class="hidden-xs">${sessionScope.cache_user.turename}</span>
              </a>
              <ul class="dropdown-menu">
                <!-- User image -->
                <li class="user-header">
                  <img src="${ctxStatic}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                  <p>
                    ${sessionScope.cache_user.turename} - Web Developer
                    <small>Member since Nov. 2012</small>
                  </p>
                </li>
                <!-- Menu Body -->
                <li class="user-body">
                  <div class="row">
                    <div class="col-xs-4 text-center">
                      <a href="#">Followers</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Friends</a>
                    </div>
                  </div>
                  <!-- /.row --> </li>
                <!-- Menu Footer-->
                <li class="user-footer">
                  <div class="pull-left">
                    <a href="${ctxroot}/user/userinfo" class="btn btn-default btn-flat">个人信息</a>
                  </div>
                  <div class="pull-right">
                    <a href="${ctxroot}/logout" class="btn btn-default btn-flat">登出</a>
                  </div>
                </li>
              </ul>
            </li>
            <!-- Control Sidebar Toggle Button --> </ul>
        </div>
      </nav>
    </header>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <aside class="main-sidebar">
      <!-- sidebar: style can be found in sidebar.less -->
      <section class="sidebar">

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <%@ include file="../common/nav.jsp"%></section>
      <!-- /.sidebar --> </aside>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>菜单管理</h1>
        <ol class="breadcrumb">
          <li>
            <a href="#"> <i class="fa fa-dashboard"></i>
              Home
            </a>
          </li>
          <li>
            <a href="#">Examples</a>
          </li>
          <li class="active">Blank page</li>
        </ol>
      </section>

      <!-- Main content -->
      <section class="content">
        <div class="row">
          <div class="col-md-12">

            <!-- 提示框 START -->
            <c:if test="${requestScope.errormsg != null}">
              <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4> <i class="icon fa fa-ban"></i>
                  发生错误!
                </h4>
                ${requestScope.errormsg}
              </div>
            </c:if>
            <c:if test="${requestScope.infomsg != null}">
              <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4>
                  <i class="icon fa fa-check"></i>
                  ${requestScope.infomsg}
                </h4>
              </div>
            </c:if>
            <!-- 提示框 END -->
            <div class="box">
              <div class="box-header with-border">
                <h3 class="box-title">菜单列表</h3>
                <div class="box-tools pull-right">
                  <!-- <a href="${ctxroot}/menu/form" class="btn btn-default btn-sm">添加菜单</a> -->
                </div>
              </div>
              <!-- /.box-header -->
              <div class="box-body">
                <table id="treeTable1" class="table table-bordered table-hover">
                  <thead>
                    <tr>
                      <th>名称</th>
                      <th>链接</th>
                      <th style="width: 80px;">排序</th>
                      <th style="width: 60px;">类型</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${menulist}" var="menu" varStatus="sn">
                      <tr id="${menu.menuid}" <c:if test="${menu.parentId ne 0}" >pId="${menu.parentId}"</c:if>
                      >
                      <td>${menu.mname}</td>
                      <td>${menu.href}</td>
                      <td>${menu.sort}</td>
                      <td>
                        <c:if test="${menu.mtype eq 0}"><span class="label" style="background-color:#87CEFA;color: #444">菜单</span></c:if>
                        <c:if test="${menu.mtype eq 1}"><span class="label" style="background-color:#DCDCDC;color: #444">按钮</span></c:if>
                        <c:if test="${menu.mtype eq 2}"><span class="label" style="background-color:#444;color: #DCDCDC">顶级</span></c:if>
                      </td>
                      <td>
                        <div class="btn-group">
                          <c:if test="${menu.mtype ne 2}">
                            <a href="${ctxroot}/menu/form/${menu.menuid}-0" class="btn btn-default btn-xs">修改</a>
                            <button type="button" class="btn btn-default btn-xs" onclick="del(${menu.menuid})">删除</button>
                          </c:if>
                            <a href="${ctxroot}/menu/form/${menu.menuid}-1" class="btn btn-default btn-xs">添加下级功能</a>
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>

              </table>
            </div>
          </div>
          <!-- /.box --> </div>
      </div>

    </section>
    <!-- /.content --> </div>
  <!-- /.content-wrapper -->

  <%@ include file="/common/footer.jsp"%>

  <!-- Control Sidebar -->

  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="${ctxStatic}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${ctxStatic}/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${ctxStatic}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${ctxStatic}/plugins/fastclick/fastclick.js"></script>
<!-- DataTables -->
<script src="${ctxStatic}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctxStatic}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- ztree -->
<script type="text/javascript" src="${ctxStatic}/plugins/ztree/js/jquery.ztree.all.min.js"></script>
<!-- Alert -->
<script type="text/javascript" src="${ctxStatic}/plugins/Alert/Alert.js"></script>
<!-- treetable -->
<script src="${ctxStatic}/plugins/treetable/script/treeTable/jquery.treeTable.js"></script>
<!-- AdminLTE App -->
<script src="${ctxStatic}/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctxStatic}/dist/js/demo.js"></script>
<script src="${ctxStatic}/qxms/js/menu.js"></script>
<script type="text/javascript">
    menu_active('10002,10009');

  $(function(){
    var option = {
        theme:'vsStyle',
        expandLevel : 4
        // beforeExpand : function($treeTable, id) {
        //     //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
        //     if ($('.' + id, $treeTable).length) { return; }
        //     //这里的html可以是ajax请求
        //     var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
        //              + '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';

        //     $treeTable.addChilds(html);
        // },
        // onSelect : function($treeTable, id) {
        //     window.console && console.log('onSelect:' + id);
        // }

    };
    $('#treeTable1').treeTable(option);
});

function del(menuid){
  var purl = rooturl + "/menu/delete";
  $.post(purl, {"menuid":menuid}, function(data){
        if(data == '1'){
          Alert("删除成功",refresh);
        }else{
          Alert("删除失败",refresh);
        }
  });
};
var refresh = function(){
  window.location.href = rooturl + "/menu";
};
  </script>
</body>
</html>