<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统登录 - 超市订单管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style1.css"/>
    <style>
        .info {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<!-- partial:index.partial.html -->
<div class="login-box">
    <h2>超市订单管理系统</h2>
    <form action="${pageContext.request.contextPath }/login.do" name="actionForm" id="myForm" method="post">
        <div type="text" class="info">${error}</div>
        <br>
        <div class="user-box">
            <input id="userCode" name="userCode" type="text" name="" required="">
            <label>用户名</label>
        </div>
        <div class="user-box">
            <input id="userPassword" name="userPassword" type="password" name="" required="">
            <label>密码</label>
        </div>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" id="btnA">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            进入系统
        </a>
    </form>
</div>
<!-- partial -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script>
    $(function () {
        $("#btnA").click(function () {
            $("#myForm").submit();
        });
    });

    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            document.getElementById("btnA").click();
        }
    });
</script>
</body>
</html>
