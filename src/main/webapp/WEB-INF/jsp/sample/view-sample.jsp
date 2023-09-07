<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>View Sample</title>
<%--    <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">--%>
    <style>
        table {
            width: 100%;
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>

    <form:form action="/jsp/addRedirect" onsubmit="valueSetter()" modelAttribute="sample">
        <form:input id="seq" path="seq"/>
        <form:input id="name" path="name"/>
        <form:input id="email" path="email"/>
        <input type="submit" value="submit">
    </form:form>

<script>
    let valueSetter = () => {
        document.getElementById("seq").value = "1111";
        document.getElementById("name").value = "new name";
        document.getElementById("email").value = "new email";
    }
</script>
</body>
</html>