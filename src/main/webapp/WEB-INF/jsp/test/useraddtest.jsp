<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/24
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<fm:form modelAttribute="user" action="${pageContext.request.contextPath}/useraddtest">
    <table>
        <tr>
            <td>用户名：</td>
            <td>
                <fm:input path="userCode"/><fm:errors path="userCode"/>
            </td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>
                <fm:input path="userPassword"/><fm:errors path="userPassword"/>
            </td>
        </tr>
        <tr>
            <td>登录：</td>
            <td>
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
</fm:form>
<div>
    <form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file"><input type="submit" value="提交">
    </form>
</div>
<body>

</body>
</html>
