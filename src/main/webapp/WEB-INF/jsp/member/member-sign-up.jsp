<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<c:set var="contextPath" value="${pageContext.request.contextPath}" />--%>
<html>
<header>
    <c:import url="../common/header.jsp"></c:import>
</header>
<body>
    <h3>회원가입 폼</h3>
    <br>
    <form:form action="/sign-up" method="post" onsubmit="valueSetter()" modelAttribute="member">
        <h3>계정 정보 입력</h3>
        <div id="user-info-wrapper">
            <div id="id-wrapper">
                <form:label path="memberId" for="memberId">아이디 * </form:label>
                <form:input id="memberId" path="memberId"/>
            </div>

            <div id="password-wrapper">
                <form:label path="password" for="password">비밀번호 * </form:label>
                <form:password id="password" path="password"/>
            </div>

            <div id="password-check-wrapper">
                <label>비밀번호 확인 * </label>
                <input type="password" id="check-password"/>
            </div>
        </div>
        <br>

        <div id="member-info-wrapper">
            <h3>회원 정보 입력</h3>
            <div>
                <form:label path="memberName" for="memberName">이름 * </form:label>
                <form:input id="memberName" path="memberName"/>
            </div>

            <div>
                <form:label path="email">이메일 * </form:label>
                <form:input id="email" path="email"/>
            </div>

            <div>
                <label>조직</label>
                <select name="department" id="dept">
                    <option value="재무회계팀">재무회계팀</option>
                    <option value="인사총무팀">인사총무팀</option>
                    <option value="제안기획팀">제안기획팀</option>
                    <option value="전략사업본부">전략사업본부</option>
                    <option value="시스템 개발본부">시스템 개발본부</option>
                </select>
            </div>

            <div>
                <label>직위</label>
                <select>
                    <option value="대표이사">대표이사</option>
                    <option value="이사">이사</option>
                    <option value="본부장">본부장</option>
                    <option value="수석">수석</option>
                    <option value="책임">책임</option>
                </select>
            </div>

            <div>
                <label>직책</label>
                <select>
                    <option value="대표">대표</option>
                    <option value="팀장">팀장</option>
                    <option value="팀원">팀원</option>
                </select>
            </div>
        </div>
        <input type="button" value="취소" onclick="location.href='/login'">
        <input type="submit" value="회원가입 요청">

    </form:form>
    <script>


    </script>
</body>
</html>