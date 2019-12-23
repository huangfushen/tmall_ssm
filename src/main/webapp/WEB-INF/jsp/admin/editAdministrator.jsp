<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
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

<title>编辑产品</title>

<script>
    $(function() {
        $("#editForm").submit(function() {
            if (!checkEmpty("name", "用户名称"))
                return false;
            if (!checkEmpty("email", "邮箱"))
                return false;
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
            <li class="active">分配角色</li>
        </ol>
        <div class="panel-body">
            <form method="post" id="editForm" action="updateAdminitrator">
                <table class="editTable" style="border-collapse:separate; border-spacing:10px;">
                    <tr>
                        <td>用户名称</td>
                        <td><input id="name" name="name" type="text"
                                   class="form-control" value="${administrator.name}"></td>
                    </tr>
                    <tr>
                        <td>邮箱</td>
                        <td><input id="email" name="email" type="text" value="${administrator.email}"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input id="password" name="password" type="text" value="******"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>角色</td>
                        <td><div class="checkbox">
                            <c:forEach items="${rs}" var="r">
                                <c:set var="hasRole" value="false" />
                                <c:forEach items="${currentRoles}" var="currentRole">
                                    <c:if test="${r.id==currentRole.id}">
                                        <c:set var="hasRole" value="true" />
                                    </c:if>
                                </c:forEach>
                                <input type="checkbox"  ${hasRole?"checked='checked'":"" } name="roleIds" value="${r.id}"> ${r.name}<br>
                            </c:forEach>
                        </div>
                        </td>
                    </tr>

                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${administrator.id}">
                            <button type="submit" class="btn btn-success">提 交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>