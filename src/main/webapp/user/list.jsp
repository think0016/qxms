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
  <script type="text/javascript">
    var rooturl = '${ctxroot}'; 
  </script>
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
                <span class="hidden-xs">${sessionScope.user.turename}</span>
              </a>
              <ul class="dropdown-menu">
                <!-- User image -->
                <li class="user-header">
                  <img src="${ctxStatic}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                  <p>
                    ${sessionScope.user.turename} - Web Developer
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
                    <a href="#" class="btn btn-default btn-flat">个人信息</a>
                  </div>
                  <div class="pull-right">
                    <a href="${ctxroot}/logout" class="btn btn-default btn-flat">登出</a>
                  </div>
                </li>
              </ul>
            </li>
            <!-- Control Sidebar Toggle Button --> 
            </ul>
        </div>
      </nav>
    </header>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <aside class="main-sidebar">
      <!-- sidebar: style can be found in sidebar.less -->
      <section class="sidebar">

        <!-- sidebar menu: : style can be found in sidebar.less -->
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
                <a href="#">
                  <i class="fa fa-circle-o"></i>
                  用户管理
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fa fa-circle-o"></i>
                  密码修改
                </a>
              </li>
            </ul>
          </li>

          <li class="">
            <a href="#">
              <i class="fa fa-circle-o"></i>
              <span>组织机构</span>
            </a>
          </li>
          <li>
            <a href="#">
              <i class="fa fa-circle-o"></i>
              <span>角色管理</span>
            </a>
          </li>
          <li>
            <a href="#">
              <i class="fa fa-circle-o"></i>
              <span>功能管理</span>
            </a>
          </li>

        </ul>
      </section>
      <!-- /.sidebar --> </aside>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>用户管理</h1>
        <ol class="breadcrumb">
          <li>
            <a href="#">
              <i class="fa fa-dashboard"></i>
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
          <div class="col-md-2">

            <div class="box">
              <div class="box-body">
                <ul id="treeDemo" class="ztree"></ul>
              </div>
            </div>
          </div>
          <div class="col-md-10">

              <!-- 提示框 START -->
              <c:if test="${requestScope.errormsg != null}">
                <div class="alert alert-danger alert-dismissible">
                  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                  <h4><i class="icon fa fa-ban"></i> 发生错误!</h4>
                  ${requestScope.errormsg}
                </div>
              </c:if>
              <c:if test="${requestScope.infomsg != null}">
                <div class="alert alert-success alert-dismissible">
                  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                  <h4><i class="icon fa fa-check"></i> ${requestScope.infomsg}</h4>
                </div>
              </c:if>
              <!-- 提示框 END -->
            <div class="box">
              <div class="box-header with-border">
                <h3 class="box-title">用户列表</h3>
                <div class="box-tools pull-right">
                  <a href="${ctxroot}/user/form" class="btn btn-default btn-sm">添加用户</a>
                </div>
              </div>
              <!-- /.box-header -->
              <div class="box-body">
                <table id="userlist" class="table table-bordered">
                  <thead>
                    <tr>
                      <th>序号</th>
                      <th>登录名</th>
                      <th>姓名</th>
                      <th>最近登录时间</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${userlist}" var="user" varStatus="sn">
                      <tr>
                        <td>${sn.index+1}</td>
                        <td>${user.loginName}</td>
                        <td>${user.turename}</td>
                        <td>${user.logindate}</td>
                        <td>
                          <div class="btn-group">
                            <a href="#" class="btn btn-xs btn-default">查看</a>
                            <a href="${ctxroot}/user/form/${user.uid}" class="btn btn-xs btn-default" onclick="">修改</a>
                          </div>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
              <!-- /.box-body -->
              <!--               <div class="box-footer clearfix">
              <ul class="pagination pagination-sm no-margin pull-right">
                <li>
                  <a href="#">&laquo;</a>
                </li>
                <li>
                  <a href="#">1</a>
                </li>
                <li>
                  <a href="#">2</a>
                </li>
                <li>
                  <a href="#">3</a>
                </li>
                <li>
                  <a href="#">&raquo;</a>
                </li>
              </ul>
            </div>
            -->
          </div>
          <!-- /.box --> </div>
      </div>

    </section>
    <!-- /.content --> </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b>
      2.3.7
    </div> <strong>Copyright &copy; 2014-2016
      <a href="http://almsaeedstudio.com">Almsaeed Studio</a>
      .</strong> 
    All rights
    reserved.
  </footer>

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
<!-- AdminLTE App -->
<script src="${ctxStatic}/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctxStatic}/dist/js/demo.js"></script>
<script type="text/javascript">

    $(document).ready(function(){
      var did = ${did};

      var setting = {
        view:{
          selectedMulti:false
        },
        data: {
          simpleData: {
            enable: true
          }
        },
        callback: {
          onClick: selectdepartment
        }
      };

      var zNodes = ${treejson}; 
      var tree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
      var nodes = tree.getNodesByParam("level", 0);
      for(var i=0; i<nodes.length; i++) {
        tree.expandNode(nodes[i], true, true, false);
      }

      //默认选中
      var node = tree.getNodeByParam("id", did, null);
      tree.selectNode(node);

    });

      //datatable 数据表格
    $('#userlist').DataTable({
      "paging": true,
      "pageLength": 20,
      "lengthChange": false,
      "searching": false,
      "ordering": false,
      "info": true,
      "autoWidth": false,
      "language": {
        "lengthMenu": "每页 _MENU_ 条记录",
        "zeroRecords": "没有找到记录",
        "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
        "infoEmpty": "无记录",
        "infoFiltered": "(从 _MAX_ 条记录过滤)",
        "oPaginate": {
          "sFirst": "首页",
          "sPrevious": "上页",
          "sNext": "下页",
          "sLast": "末页"
        }
      }
    });

    function selectdepartment(event, treeId, treeNode, clickFlag){
      //console.log(treeNode.id);
      did = treeNode.id
      url = rooturl + "/user/list/"+did;
      window.location.href = url;
    };
  </script>
</body>
</html>