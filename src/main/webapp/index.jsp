<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo6.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="login">登录</a>
<br/>
<a href="Test3.jsp">测试</a>
<br/>

<h1>算术运算符</h1>


<%
    //pageContext.setAttribute( "name", "jack");
    request. setAttribute("name","tom");
    session. setAttribute("name","lucy");
    application. setAttribute("name","lisi");
%>

<h1>获取值</h1>

${pageScope.get("name")} <br>

${requestScope.get("name")}<br>

${sessionScope.get("name")} <br>

${applicationScope.get("name")} <br>



<h1>获取值</h1>

${pageScope.get("name")} <br>
${requestScope.get("name")} <br>
${sessionScope.get("name")} <br>
${applicationScope.get("name")} <br>

${name}

<%--<%--%>
<%--    User user = new User();--%>
<%--    user.setAge(10);--%>
<%--    user.setName("jack");--%>
<%--    user.setBirthday("1999-10-10");--%>
<%--    request.setAttribute("user",user);--%>
<%--%>--%>


<h1>获取集合对象的值</h1>
${requestScope.get("user").age}<br>
${user.name} <br>
${user.birthday} <br>
<%--${user.birthAge} <br>--%>

<%
    ArrayList<Object> list = new ArrayList<>();
    list.add("hello");
    list.add("java");
    list.add("world");

    request.setAttribute("stringList",list);
%>
<h1>获取集合list中的值</h1>

${requestScope.get("stringList")[1]}<br>
${stringList[0]}<br>
${stringList[2]}<br>

<%
    HashMap<String,String> map = new HashMap<>();
    map.put("one","hello");
    map.put("two","world");
    map.put("three","java");

    request.setAttribute("map",map);
%>

<h1>获取map数据</h1>
${requestScope.get("map").one}<br>
${map.two}<br>
${map["three"]}<br>

<h1>空运算符</h1>

<%
    String s = "";
    request.setAttribute("str",s);

    ArrayList<Object> list1 = new ArrayList<>();
    request.setAttribute("list1",list1);
%>

${empty str}
${not empty str}

${pageContext.request.contextPath}
<a href="${pageContext.request.contextPath}/ServletDownload"></a>





</body>
</html>
