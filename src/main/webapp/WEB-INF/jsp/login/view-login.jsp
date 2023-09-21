<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <!-- Header Layout include -->
    <c:import url="../common/header.jsp"></c:import>
    <style>
        .main_wrapper {
            text-align:center;
        }
    </style>
</head>
<body>
<div class="main_wrapper">
    <h3>로그인</h3>
    <form id="loginForm">
        <div>
            <label for="memberId">ID: </label>
            <input id="memberId" name="memberId" />
        </div>
        <div>
            <label for="password">PASSWORD: </label>
            <input type="password" id="password" name="password"/>
        </div>
        <br>
        <input type="button" value="login" onclick="login()"/>
        <input type="button" value="회원가입" onclick="location.href='/member/sign-up'"/>
    </form>

    <div id="result"></div>
</div>

<c:import url="../common/footer.jsp"></c:import>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<script>

    function login(){
        let memberId = document.getElementById("memberId").value;
        let password = document.getElementById("password").value;

        // 아이디 비밀번호 유효성 검사
        if(!memberId || !password){
            document.getElementById("result").innerHTML = "사용자 아이디 또는 비밀번호를 입력해주세요.";
            return;
        }

        let member = {
            memberName: memberId,
            password: password
        }
        console.log(member.memberName+ "  " + member.password);
        // 로그인을 위하여 JSON
        $.ajax({
            type : "POST",
            url  : "/login",
            contentType: "application/json",
            async:false,
            data: JSON.stringify(member),
            success: function(result){
                console.log(result);
            }
        });


    }

</script>
</body>
</html>