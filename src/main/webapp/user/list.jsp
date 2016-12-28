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
  <script type="text/javascript">var rooturl = '${ctxroot}'; </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
  <!-- Site wrapper -->
  <div class="wrapper">

    <header class="main-header">
      <!-- Logo -->
      <%@ include file="/common/logo.jsp"%>
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
            <%@ include file="/common/userAccount.jsp"%>
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
        <%@ include file="/common/nav.jsp"%></section>
      <!-- /.sidebar --> </aside>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>用户中心</h1>
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
                <h3 class="box-title">用户列表</h3>
                <div class="box-tools pull-right">
                  <button type="button" class="btn btn-default btn-sm" onclick="adduser();">添加新用户</button>
                </div>
              </div>
              <!-- /.box-header -->
              <div class="box-body">
                <table id="userlist" class="table table-bordered">
                  <thead>
                    <tr>
                      <th>编号</th>
                      <th>登录名</th>
                      <th>姓名</th>
                      <th>所属部门</th>
                      <th>最近登录时间</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
<!--                     <c:forEach items="${userlist}" var="user" varStatus="sn">
                      <tr>
                        <td>${sn.index+1}</td>
                        <td>${user.loginName}</td>
                        <td>${user.turename}</td>
                        <td>
                          <fmt:formatDate value="${user.logindate}"  pattern="yyyy/MM/dd HH:mm:ss" />
                        </td>
                        <td>
                          <div class="btn-group">
                            <a href="${ctxroot}/user/form/${user.uid}" class="btn btn-xs btn-default" onclick="">修改</a>
                            <button class="btn btn-xs btn-default" onclick="del(${user.uid});">删除</button>
                          </div>
                        </td>
                      </tr>
                    </c:forEach> -->
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
<!-- AdminLTE App -->
<script src="${ctxStatic}/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctxStatic}/dist/js/demo.js"></script>
<script src="${ctxStatic}/qxms/js/menu.js"></script>
<script type="text/javascript">
  menu_active('10002,10003,10011');

  var did = ${did};
  $(document).ready(function() {

    var setting = {
      view: {
        selectedMulti: false
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
    for (var i = 0; i < nodes.length; i++) {
      tree.expandNode(nodes[i], true, true, false);
    }

    //默认选中
    var node = tree.getNodeByParam("id", did, null);
    tree.selectNode(node);

  });

  var table = "";
  createtable("0");
  function createtable(did) {
    var url = rooturl + "/user/getjsonlist";
    //datatable 数据表格
    //先Destroy后建立
    if(table !=""){
      $('#userlist').dataTable().fnDestroy();
    }
    table = $('#userlist').DataTable({
      "ajax": {
        "url": url,
        "data":{
          "did": did
        }
      },
      "columnDefs": [ {
            "targets": -1,
            "data": null,
            "defaultContent": "<div class=\"btn-group\"><button class='update btn btn-default btn-xs'>修改</button><button class='del btn btn-default btn-xs'>删除</button></div>"
        } ],
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
    $('#userlist tbody').on( 'click', '.update', function () {
        var data = table.row( $(this).parents('tr') ).data();
            url = rooturl + "/user/form/"+data[0]+"-"+did;
            window.location.href = url;
    } );
    $('#userlist tbody').on( 'click', '.del', function () {
        var data = table.row( $(this).parents('tr') ).data();
        del(data[0]);
    } );
  }


  function selectdepartment(event, treeId, treeNode, clickFlag) {
    // console.log(treeNode.id);

    did = treeNode.id;
    // url = rooturl + "/user/list/"+did;
    // window.location.href = url;
    createtable(did);

  };

  function del(uid) {
    var purl = rooturl + "/user/delete";
    $.post(purl, {
      "uid": uid
    }, function(data) {
      if (data == '1') {
        Alert("删除成功", createtable(did));
      } else if(data == '2'){
        Alert("超级管理员角色用户不可删除", createtable(did));
      }else{
        Alert("删除失败", createtable(did));
      }
    });
  };

  function adduser(){
    url = rooturl + "/user/form/0-"+did;
    window.location.href = url;
  }

  // var refresh = function() {
  //   window.location.href = rooturl + "/user";
  // }
</script>
</body>
</html>