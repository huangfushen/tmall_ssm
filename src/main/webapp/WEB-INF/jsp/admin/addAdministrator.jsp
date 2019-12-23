<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/easyui.css"/>
<link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/icon.css"/>
<link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/main.css"/>
<%@include file="../include/admin/adminHeader.jsp"%>
<script type="text/javascript" src="/tmall_ssm/dist/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/tmall_ssm/dist/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/tmall_ssm/dist/js/loading.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">

<title>添加系统管理员</title>

<script>
    $(function () {
        $("#addForm").submit(function () {
            if (!checkEmpty("name", "用户名称"))
                return false;
            return true;
        });
        $("#addForm").submit(function () {
            if (!checkEmpty("email", "邮箱"))
                return false;
            return true;
        });
        $("#addForm").submit(function () {
            if (!checkEmpty("password", "密码"))
                return false;
            return true;
        });

    });
</script>

<div class="workingArea">
    <div class="panel panel-warning editDiv">
        <ol class="breadcrumb">
            <li><a href="administrator_list">系统管理员</a></li>
            <li class="active">添加系统管理员</li>
        </ol>
        <div class="panel-body">
            <form method="post" id="addForm" action="addAdministrator">
                <table class="addTable" style="border-collapse:separate; border-spacing:10px;">
                    <tr>
                        <td>用户名称</td>
                        <td><input id="name" name="name" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>邮箱</td>
                        <td><input id="email" name="email" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input id="password" name="password" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>角色</td>
                        <td><div class="checkbox">
                            <c:forEach items="${rs}" var="r">
                                <c:set var="hasRole" value="false" />
                                    <input  type="checkbox"  name="roleIds" value="${r.id}"> ${r.name}<br>
                            </c:forEach>
                        </div>
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">添 加</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>