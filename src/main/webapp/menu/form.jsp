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
    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
  </style>
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
        <%@ include file="../common/nav.jsp"%></section>
      <!-- /.sidebar --> </aside>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>菜单管理</h1>
      </section>

      <!-- Main content -->
      <section class="content">
        <div class="row">
          <div class="col-md-1"></div>
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
            <form action="${ctxroot}/menu/savemenu" class="form-horizontal"  method="post" id="form1">
              <input type="hidden" name="pmenuid" value="${pmenuid}">
              <input type="hidden" name="menuid" value="${menu.menuid}">
              <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title">${subtitle}</h3>
                </div>
                <div class="box-body">
                  <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">上级菜单：</label>

                    <div class="col-sm-7">
                      <input type="text" id="pmenuname" name="" class="form-control" id="input1" value="${pmenuname}" disabled></div>
                    <div class="col-sm-3">
                      <button type="button" class="btn btn-info pull-left" onclick="showMenu(); return false;">选择</button>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">名称：</label>

                    <div class="col-sm-8">
                      <input type="text" name="mname" class="form-control" id="input1" placeholder="菜单名称" value="${menu.mname}"></div>
                    <div class="col-sm-2"></div>
                  </div>
                  <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">链接：</label>

                    <div class="col-sm-8">
                      <input type="text" name="href" class="form-control" id="input1" placeholder="链接" value="${menu.href}"></div>
                    <div class="col-sm-2"></div>
                  </div>
                  <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">排序：</label>

                    <div class="col-sm-8">
                      <input type="number" name="sort" class="form-control" id="input1" placeholder="(0-255)" value="${menu.sort}"></div>
                    <div class="col-sm-2"></div>
                  </div>
                  <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">类型：</label>

                    <div class="col-sm-8">
                      <input type="radio" name="mtype" value="0" checked="">
                      菜单&nbsp;&nbsp;&nbsp;
                      <input type="radio" name="mtype" value="1">按钮</div>
                    <div class="col-sm-2"></div>
                  </div>
                  <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">备注：</label>

                    <div class="col-sm-8">
                      <textarea id="input9" name="remarks" class="form-control" rows="3" >${menu.remarks}</textarea>
                    </div>
                    <div class="col-sm-2"></div>
                  </div>

                </div>
              <div class="box-footer">
                <div class="col-sm-9">                  
                  <a href="${ctxroot}/menu" class="btn btn-default pull-right">返回</a>
                  <!-- <button type="submit" class="btn btn-info pull-right" onclick="">保存</button> -->
                </div>
                <div class="col-sm-1"><button type="button" class="btn btn-info pull-right" onclick="saveform();">保存</button></div>
                <div class="col-sm-2"></div>
              </div>
              </div>
            </form>
          </div>
          <div class="col-md-1"></div>
        </div>

        <!-- 下拉菜单START -->
        <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
          <ul id="treeDemo" class="ztree" style=""></ul>
        </div>
        <!-- 下拉菜单END --> </section>
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
    menu_active('10002,10009');

    var mtype = '${menu.mtype}';
    //添加默认值
    var menuid = $("input[name='menuid']").val();
    var pmenuid = $("input[name='pmenuid']").val();
    if(menuid != null && menuid != ''){
      //$("input[name='mtype']").val(mtype);
      $("input[name='mtype']:eq("+mtype+")").attr("checked","checked");
    }
    if(pmenuid == null || pmenuid == ''){
      $("input[name='pmenuid']").val(1);
      $("#pmenuname").val('顶级菜单');
    }

    var setting = {
      view: {
        selectedMulti:false
        //dblClickExpand: false
      },
      data: {
        simpleData: {
          enable: true
        }
      },
      callback: {
        //beforeClick: beforeClick,
        onClick: onClick,
        onDblClick : hideMenu
      }
    };

    var zNodes =${treejson};

    function beforeClick(treeId, treeNode) {
      var check = (treeNode && !treeNode.isParent);
      if (!check) alert("只能选择城市...");
      return check;
    }
    
    function onClick(e, treeId, treeNode) {
      // var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
      // nodes = zTree.getSelectedNodes(),
      // v = "";
      // nodes.sort(function compare(a,b){return a.id-b.id;});
      // for (var i=0, l=nodes.length; i<l; i++) {
      //   v += nodes[i].name + ",";
      // }
      // if (v.length > 0 ) v = v.substring(0, v.length-1);
      var cityObj = $("#citySel");
      cityObj.attr("value", treeNode.name);
      $("input[name='pmenuid']").val(treeNode.id);
    }

    function showMenu() {
      var cityObj = $("#citySel");
      var cityOffset = $("#citySel").offset();
      $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

      $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
      $("#menuContent").fadeOut("fast");
      $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
      if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
      }
    }

    $(document).ready(function(){
      var tree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);

      var nodes = tree.getNodesByParam("level", 0);
      for(var i=0; i<nodes.length; i++) {
        tree.expandNode(nodes[i], true, true, false);
      }
    });

    //表单提交
    function saveform(){
      //验证表单
      var pmenuid = $("input[name='pmenuid']").val();
      var mname = $("input[name='mname']").val();


      if(pmenuid == ''){
        alert("上级菜单不能为空");
      }else if(mname == ''){
        alert("名称不能为空");
      }else{
        $('#form1')[0].submit();  
      }
      
    };

  </script></body>
</html>