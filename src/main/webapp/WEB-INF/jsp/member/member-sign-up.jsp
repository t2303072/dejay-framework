<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<c:set var="contextPath" value="${pageContext.request.contextPath}" />--%>
<html>
<header>
    <c:import url="../common/header.jsp"></c:import>
</header>
<aside>
    <body>
    <h3>회원가입 폼</h3>
    <br>
    <form:form action="/login" method="post" onsubmit="valueSetter()" modelAttribute="member">
        <div>
            <form:label path="memberId" for="memberId">아이디: </form:label>
            <form:input id="memberId" path="memberId"/>
        </div>
        <div>
            <form:label path="password" for="password">패스워드: </form:label>
            <form:password id="password" path="password"/>
        </div>
        <div>
            <form:label path="email" for="email">이메일: </form:label>
            <form:input id="email" path="email"/>
        </div>
        <br>
        <input type="submit" value="로그인">
        <input type="button" value="회원가입" onclick="location.href='/member/sign-up'">
    </form:form>
    <script>

    </script>
    </body>
</aside>
</html>