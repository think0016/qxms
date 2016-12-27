<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
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
                    <a href="logout" class="btn btn-default btn-flat">登出</a>
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
        <h1>主页</h1>
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
            <div class="box">
              <div class="box-header with-border">
                <h3 class="box-title">Bordered Table</h3>
              </div>
              <!-- /.box-header -->
              <div class="box-body">
                <table id="treeTable1" class="table table-bordered">
                  <tr>
                    <th >#</th>
                    <th>Task</th>
                    <th>Progress</th>
                    <th>Label</th>
                  </tr>
                  <tr id="1" >
                    <td>1.</td>
                    <td>aa</td>
                    <td>aa</td>
                    <td>aa</td>
                  </tr>
                  <tr id="2" pId="1">
                    <td>2.</td>
                    <td>aa</td>
                    <td>aa</td>
                    <td>aa</td>
                  </tr>
                  <tr id="3" pId="1">
                    <td>3.</td>
                    <td>aa</td>
                    <td>aa</td>
                    <td>aa</td>
                  </tr>
                  <tr id="4">
                    <td>4.</td>
                    <td>aa</td>
                    <td>aa</td>
                    <td>aa</td>
                  </tr>
                  <tr id="5" pId="1">
                    <td>5.</td>
                    <td>aa</td>
                    <td>aa</td>
                    <td>aa</td>
                  </tr>
                  <tr id="6">
                    <td>6.</td>
                    <td>aa</td>
                    <td>aa</td>
                    <td>aa</td>
                  </tr>
                </table>
              </div>
              <!-- /.box-body -->
              <div class="box-footer clearfix">
                <!--                 <ul class="pagination pagination-sm no-margin pull-right">
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
              -->
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
<!-- treetable -->
<script src="${ctxStatic}/plugins/treetable/script/treeTable/jquery.treeTable.js"></script>
<!-- AdminLTE App -->
<script src="${ctxStatic}/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctxStatic}/dist/js/demo.js"></script>
<script type="text/javascript">
  $(function(){
    var option = {
        theme:'vsStyle',
        // expandLevel : 2,
        beforeExpand : function($treeTable, id) {
            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
            if ($('.' + id, $treeTable).length) { return; }
            //这里的html可以是ajax请求
            var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
                     + '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';

            $treeTable.addChilds(html);
        },
        onSelect : function($treeTable, id) {
            window.console && console.log('onSelect:' + id);
        }

    };
    $('#treeTable1').treeTable(option);
});
</script>
</body>
</html>