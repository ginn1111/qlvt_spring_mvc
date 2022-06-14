<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 14/06/2022
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${tests}" var="t">
        <h1>
            ${t.id}
        </h1>
        <span>${t.password}</span>
    </c:forEach>
</body>
</html>
