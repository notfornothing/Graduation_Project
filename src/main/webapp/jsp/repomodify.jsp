<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>仓库管理界面 >> 仓库修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath }/jsp/repo.do">
            <input type="hidden" name="method" value="modifysave">
            <input type="hidden" name="id" value="${secret.id}">
            <input type="hidden" name="secretProviderCode" value="${secretProviderCode}">
            <input type="hidden" name="billid" value="${billid}">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div class="">
                <label for="billCode">供应商编码：</label>
                <input type="text" name="billCode" id="providerCode" value="${secret.providerCode }"
                       readonly="readonly">
            </div>
            <div>
                <label>供应商：</label>
                <input type="text" value="${secret.providerName}" id="pid" readonly="readonly"/>
            </div>
            <div class="">
                <label for="billCode">供应商电话：</label>
                <input type="text" name="billCode" id="billCode" value="${secret.providerPhone }" readonly="readonly">
            </div>
            <div>
                <label for="productName">商品名称：</label>
                <input type="text" name="productName" id="productName" value="${secret.productName }"
                       readonly="readonly">
                <font color="red"></font>
            </div>
            <div>
                <label for="productCount">商品总量：</label>
                <input type="text" name="total" id="total" value="${secret.total}">
                <font color="red"></font>
            </div>
            <div>
                <label for="productUnit">单位：</label>
                <input type="text" name="unit" id="unit" value="${secret.unit}">
                <font color="red"></font>
            </div>
            <div class="providerAddBtn">
                <input type="button" name="save" id="save" value="保存">
                <input type="button" id="back" name="back" value="返回">
            </div>
        </form>
    </div>

</div>
</section>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/repomodify.js"></script>