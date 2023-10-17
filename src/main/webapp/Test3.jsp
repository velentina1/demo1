<%--
  Created by IntelliJ IDEA.
  User: wxl
  Date: 2023/10/16
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo6.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>jstlTest</title>
</head>
<body>
<%  User user1 = new User("A",30,"2000-03-12");
    User user2 = new User("B",40,"2001-03-20");
    User user3 = new User("C",16,"2002-07-15");
    ArrayList<User> users = new ArrayList<>();
    users.add(user1);
    users.add(user2);
    users.add(user3);
    request.setAttribute("usersinfo",users);

%>

<table border="1px" cellspacing="0" cellpadding="5px"
       width="500px" align="center" bgcolor = "#cacaca">
    <tr>
        <th>
            编号
        </th>

        <th>
            姓名
        </th>
        <th>
            年龄
        </th>
        <th>
            生日
        </th>
    </tr>


    <c:forEach items="${usersinfo}" var="str" varStatus="a" >
        <%--这里的var和varStatus的值是自己定义的--%>

        <tr>
            <td>
                    ${a.count}
            </td>
            <td>
                    ${str.name}
            </td>
            <td>
                    ${str.age}
            </td>
            <td>
                    ${str.birthday}
            </td>
        </tr>

    </c:forEach>
</table>


</body>
</html>
