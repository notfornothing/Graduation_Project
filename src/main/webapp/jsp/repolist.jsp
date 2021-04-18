<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>仓库管理界面</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/repo.do">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>供应商编码：</span>
            <input name="queryProviderCode" type="text" value="${queryProviderCode}">

            <span>供应商：</span>
            <select name="queryProviderName">
                <c:if test="${providerList != null }">
                    <option value="">--请选择--</option>
                    <c:forEach var="provider" items="${providerList}">
                        <option
                                <c:if test="${provider.proName == queryProviderName }">selected="selected"</c:if>
                                value="${provider.proName}">${provider.proName}</option>
                    </c:forEach>
                </c:if>
            </select>

            <span>商品名称：</span>
            <input name="queryProductName" type="text" value="${queryProductName}">

            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath }/jsp/billadd.jsp">添加库存</a>
        </form>
    </div>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">供应商编码</th>
            <th width="20%">供应商名称</th>
            <th width="10%">供应商电话</th>
            <th width="10%">商品名称</th>
            <th width="10%">总量</th>
            <th width="10%">单位</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="secret" items="${secretList}" varStatus="status">
            <c:if test="${secret.total>500}">
                <tr>
            </c:if>
            <c:if test="${secret.total<=500}">
                <tr style="background: #d43f3a">
            </c:if>
            <td>
                <span>${secret.providerCode }</span>
            </td>
            <td>
                <span>${secret.providerName }</span>
            </td>
            <td>
                <span>${secret.providerPhone}</span>
            </td>
            <td>
                <span>${secret.productName}</span>
            </td>
            <td>
                <span>${secret.total}</span>
            </td>
            <td>
                <span>${secret.unit}</span>
            </td>
            <td>
                    <span><a class="modifyBill" href="javascript:;" billid=${secret.id }  secretProviderCode=${secret.providerCode} billcc=${secret.id} ><img
                            src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
                <span><a class="deleteBill" href="javascript:;" billid=${secret.id } billcc=${secret.productName}><img
                        src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
            </td>
            </tr>
        </c:forEach>
    </table>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该仓库订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/repolist.js"></script>