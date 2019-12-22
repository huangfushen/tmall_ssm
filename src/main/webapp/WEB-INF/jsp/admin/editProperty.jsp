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
<title>编辑属性</title>

<script>
    $(function () {
        $("#editForm").submit(function () {
            if (!checkEmpty("name", "属性名称"))
                return false;
            return true;
        });
    });
</script>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">编辑属性</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_property_update">
                <table class="addTable" style="border-collapse:separate; border-spacing:10px;">
                    <tr>
                        <td>属性名称</td>
                        <td><input id="name" name="name" type="text" value="${p.name}"
                                   class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${p.id}">
                            <input type="hidden" name="cid" value="${p.category.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>