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
            if (!checkEmpty("name", "权限名称"))
                return false;
            return true;
        });
        $("#addForm").submit(function () {
            if (!checkEmpty("desc_", "权限描述"))
                return false;
            return true;
        });
        $("#addForm").submit(function () {
            if (!checkEmpty("url", "url"))
                return false;
            return true;
        });

    });

</script>

<div class="workingArea">
    <div class="panel panel-warning editDiv">
        <ol class="breadcrumb">
            <li><a href="permission_list">权限管理</a></li>
            <li class="active">权限编辑</li>
        </ol>
        <div class="panel-body">
            <form method="post" id="addForm" action="updatePermission">
                <table class="addTable" style="border-collapse:separate; border-spacing:10px;">
                    <tr>
                        <td>权限名称</td>
                        <td><input id="name" name="name" type="text" value="${permission.name}"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>权限描述</td>
                        <td><input id="desc_" name="desc_" type="text" value="${permission.desc_}"
                                   class="form-control"></td>
                    </tr>

                    <tr>
                        <td>url</td>
                        <td><input id="url" name="url" type="text" value="${permission.url}"
                                   class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${permission.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>