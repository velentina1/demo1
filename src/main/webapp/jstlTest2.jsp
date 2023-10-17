<%--
  Created by IntelliJ IDEA.
  User: wxl
  Date: 2023/10/16
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo6.User" %>
<%@ page import="java.util.Date" %>


<html>
<head>
    <title>jstlTest</title>
</head>
<body>
<%
    List list = new ArrayList();
    list.add(new User("小明",22,new Date()));
    list.add(new User("小红",23,new Date()));
    list.add(new User("小蓝",25,new Date()));
    request.setAttribute("ls",list);
%>

<table border="1" width="500" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>生日</th>
    </tr>
    <%--    数据行  --%>
    <c:forEach items="${ls}" var="user" varStatus="s">

        <c:if test="${s.count % 2 ==0}">
            <tr bgcolor="#adff2f">
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birStr}</td>
            </tr>
        </c:if>

        <c:if test="${s.count % 2 !=0}">
            <tr bgcolor="#ffc0cb">
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birStr}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>

</body>
</html>
