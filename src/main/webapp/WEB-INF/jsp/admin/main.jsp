<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>天猫后台管理系统</title>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/main.css"/>

    <script type="text/javascript" src="/tmall_ssm/dist/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/tmall_ssm/dist/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/tmall_ssm/dist/js/loading.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#tab").tabs({
                onSelect: function (title) {
                    var childs = $(".left-list-item");
                    for (var i = 0; i < childs.length; i++) {
                        $(childs[i]).removeClass("left-list-item-selected");
                        if ($(childs[i]).attr("title") == title) {
                            $(childs[i]).addClass("left-list-item-selected");
                        }
                    }
                }
            });
            $(".left-list-item").on("click", function () {
                var title = $(this).attr("title");
                var url = $(this).attr("url");
                if ($("#tab").tabs("exists", title)) {
                    $("#tab").tabs("select", title);
                } else {
                    var content = "<iframe scrolling='auto' frameborder='0' src='" + url + "' width='100%' height='100%' ></iframe>";
                    var icons = {
                        "分类管理": "icon-student",
                        "用户管理": "icon-teacher",
                        "订单管理": "icon-course",
                        "系统管理员": "icon-teaching",
                        "角色管理": "icon-tip",
                        "权限管理": "icon-lock"
                    };
                    $("#tab").tabs("add", {
                        title: title,
                        iconCls: icons[title],
                        content: content,
                        closable: true

                    });
                }

            });

        });
    </script>
</head>
<div id='loading'
     style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:rgb(247,248,248);text-align:center;padding-top: 20%;">
</div>
<body class="easyui-layout" id="b1">

<div region="north" class="header">
    <img alt="logo" src="/tmall_ssm/img/site/tmall_admin.png">
    <div style="float: right; padding-right: 50px;padding-top: 15px">
        <a href="doLogout" class="btn btn-default">您好${subject.principal},退出</a><br>
    </div>

</div>
<div region="south" class="footer">Copyright &copy;2019</div>
<div region="west" class="left" title="菜单管理">
    <div class="left-list">

        <div class="left-list-item" url="admin_category_list" title="分类管理">
            <a class="easyui-linkbutton" iconCls="icon-menu" plain="true">分类管理</a>
        </div>
        <div class="left-list-item" url="admin_user_list" title="用户管理">
            <a class="easyui-linkbutton" iconCls="icon-teacher" plain="true">用户管理</a>
        </div>
        <div class="left-list-item" url="admin_order_list" title="订单管理">
            <a class="easyui-linkbutton" iconCls="icon-course" plain="true">订单管理</a>
        </div>
        <div class="left-list-item" url="administrator_list" title="系统管理员">
            <a class="easyui-linkbutton" iconCls="icon-teaching" plain="true">系统管理员</a>
        </div>
        <div class="left-list-item" url="role_list" title="角色管理">
            <a class="easyui-linkbutton" iconCls="icon-tip" plain="true">角色管理</a>
        </div>
        <div class="left-list-item" url="permission_list" title="权限管理">
            <a class="easyui-linkbutton" iconCls="icon-lock" plain="true">权限管理</a>
        </div>

    </div>

</div>
<div region="center" class="right">
    <div id="tab" fit="true">
        <div title="主页" iconCls="icon-home">
            <h1 style="text-align: center;"> welcome to tmall admin system</h1>
        </div>
    </div>
</div>

</body>

</html>