<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <script type="text/javascript" src="/tmall_ssm/dist/js/bootstrap.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="workingArea">
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="default">
                <td>id</td>
                <td>权限名称</td>
                <td>权限描述</td>
                <td>权限对应的路径</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ps}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.desc_}</td>
                    <td>${p.url}</td>
                    <td><a href="editPermission?id=${p.id}">编辑</a>
                        <a href="deletePermission?id=${p.id}">删除</a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<%--
    <div class="pageDiv" style="text-align: center">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>--%>


</div>
</body>
</html>


