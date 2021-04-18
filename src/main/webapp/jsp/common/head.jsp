<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css"/>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市订单管理系统</h1>
    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> ${userSession.userName }</span> , 欢迎你！</p>
        <a href="${pageContext.request.contextPath }/jsp/logout.do">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2021年1月1日 11:11  星期一</span>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <%--  fixed --%>

                <%--   1 是管理员             --%>
                <%--   2 是采购员             --%>
                <%--   3 是销售员             --%>
                <c:if test="${userSession!= null }">

                    <c:if test="${userSession.userRole==3||userSession.userRole==1}">
                        <%--   订单管理 --%>
                        <li><a style='background: url("../images/zd.png") 0  center no-repeat;'
                               href="${pageContext.request.contextPath }/jsp/bill.do?method=query">进货销售管理</a></li>
                    </c:if>

                    <c:if test="${userSession.userRole==2||userSession.userRole==1}">
                        <%--    商品信息管理--%>
                        <li><a style='background: url("../images/gys.png") 0  center no-repeat;'
                               href="${pageContext.request.contextPath }/jsp/provider.do?method=query">商品信息管理</a></li>
                        <%--               need to fix fixlater --%>
                        <%--    库存管理--%>
                        <li><a style='background: url("../images/kucun.png") 0  center no-repeat;'
                               href="${pageContext.request.contextPath }/jsp/repo.do?method=query">库存管理</a></li>
                        <%--    need to fix --%>
                    </c:if>
                    <c:if test="${userSession.userRole==1}">
                        <%--    用户管理--%>
                        <li><a style='background: url("../images/yh.png") 0  center no-repeat;'
                               href="${pageContext.request.contextPath }/jsp/user.do?method=query">用户管理</a></li>
                    </c:if>
                    <%--    密码修改--%>
                    <li><a style='background: url("../images/mm.png") 0  center no-repeat;'
                           href="${pageContext.request.contextPath }/jsp/pwdmodify.jsp">密码修改</a></li>
                    <%--    退出系统--%>
                    <li><a style='background: url("../images/tc.png") 0  center no-repeat;'
                           href="${pageContext.request.contextPath }/jsp/logout.do">退出系统</a></li>
                    <%--                fixed--%>
                </c:if>
            </ul>
        </nav>
    </div>
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
    <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>