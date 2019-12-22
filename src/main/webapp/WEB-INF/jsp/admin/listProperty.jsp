<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/tmall_ssm/dist/css/main.css"/>

    <script type="text/javascript" src="/tmall_ssm/dist/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/tmall_ssm/dist/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/tmall_ssm/dist/js/loading.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
</head>
<script>

</script>
<body>
<ol class="breadcrumb">
    <li><a href="admin_category_list">所有分类</a></li>
    <li><a href="admin_property_list?cid=${c.id}">${c.name}</a></li>
    <li class="active">属性管理</li>
</ol>
<table class="easyui-datagrid dg" title="分类属性管理">
    <div id="tb" style="padding: 2px 5px;">
        <a href="property_add?cid=${c.id}&name=${c.name}" class="easyui-linkbutton" iconCls="icon-add" >添加属性</a>
    </div>
    <thead>
    <tr>
        <th field="ck" checkbox="true"></th>
        <th data-options="field:'ID'">ID</th>
        <th data-options="field:'属性名称'">属性名称</th>
        <th data-options="field:'编辑'">编辑</th>
        <th data-options="field:'删除'">删除</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ps}" var="p">

        <tr>
            <td></td>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td><a href="admin_property_edit?id=${p.id}"><span
                    class="glyphicon glyphicon-edit"></span></a></td>
            <td><a deleteLink="true"
                   href="admin_property_delete?id=${p.id}"><span
                    class="     glyphicon glyphicon-trash"></span></a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<div class="pageDiv" style="text-align: center">
    <%@include file="../include/admin/adminPage.jsp" %>
</div>
</body>

</html>

