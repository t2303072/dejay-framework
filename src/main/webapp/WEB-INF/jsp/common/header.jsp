<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<script src="https://kit.fontawesome.com/795059fbe9.js" crossorigin="anonymous"></script>
    <!-- Begin Wrapper -->
<div id="wrapper">
    <!-- Begin Header -->
    <div class="header">
        <h2 class="header-child left">드제이</h2>
        <h2 class="header-child">결재 관리 시스템</h2>
        <a href="/login" class="header-child right"><h3>로그인</h3></a>
    </div>
</div>
<style>
    .header {
        border: 1px solid black;
        width : 100%;
        height : 10vh;
        justify-content : space-between;
        display : flex;
    }

    .header-child {
        width: calc(100% / 3);
        padding: 20px;
        box-sizing: border-box;
        text-align : center;
    }

    .right{
        text-align: right;
    }

    .left{
        text-align:left;
    }
</style>
