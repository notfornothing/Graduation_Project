<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <style>
        /* 设置页面宽度，不能直接设置body，设置container */
        .container {
            width: 500px;
            margin-top: 250px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>你好,访问失败,可能是权限不足.请联系管理员</h1>
    <h2><a href="javascript:window.history.back(-1);">返回</a></h2>
</div>
</body>
</html>