<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="content" >
    <div class="ui-corner-all" id="nav">
        <div  style="height:25px;line-height:25px;">
        	<div class="grid_8 form-left">
        	<jsp:include page="/common/orgSelectedComponent.jsp"/>
<%--         		<jsp:include page="/issue/orgSelectedIssue.jsp"/> --%>
        	</div>
	        <div class="grid_4 form-right" style=" display:none">
                <label style="line-height:25px;padding-right:10px;">类型：</label>
            </div>
	        <div class="grid_8 form-left">
		        <select name="reoprtDateType" id="reoprtDateType">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@REPORT_DATE_TYPE" notNull="true"/>
				</select>
			</div>
            <div class="grid_4 form-right" style=" display:none">
                <label style="line-height:25px;padding-right:10px;">年度：</label>
            </div>
            <div class="grid_8 form-left">
	            <select name="statYear" id="statYear">
	                <s:iterator begin="minYear" end="maxYear" var="newStatYear">
	                    <option value="${newStatYear }" <s:if test='maxYear==#newStatYear'>selected='selected'</s:if>>${newStatYear }</option>
	                </s:iterator>
	            </select>
            </div>
            <div class="grid_4 form-right" style=" display:none">
                <label style="line-height:25px;padding:0 10px;">月份：</label>
            </div>
            <div class="grid_8 form-left">
	            <select name="statMonth" id="statMonth" onchange="onMonthChange()">
	            </select>
	            
            </div>
            <div class="grid_8 form-left">&nbsp;</div>
            <a id="search" href="javascript:void(0)"><span>查询</span></a>
            <pop:JugePermissionTag ename="addAdministrativeRegradedPoint,addFunctionalRegradedPoint">
                <a id="add" href="javascript:void(0)"><span>添加打分记录</span></a>
            </pop:JugePermissionTag>
            <pop:JugePermissionTag ename="viewAdministrativeRegradedPoints,viewFunctionalRegradedPoints">
                <a id="view" href="javascript:void(0)"><span>查看详情</span></a>
            </pop:JugePermissionTag>
<%--             <pop:JugePermissionTag ename="auditAdministrativeRegradedPoint,auditFunctionalRegradedPoint"> --%>
<%--                 <a id="audit" href="javascript:void(0)"><span>审核</span></a> --%>
<%--             </pop:JugePermissionTag> --%>
            <a id="reload" href="javascript:void(0)"><span>刷新</span></a>
            <a id="print" href="javascript:void(0)"><span>打印</span></a>
            <pop:JugePermissionTag ename="downAdministrativeRegradedPoints,downFunctionalRegradedPoints">
            	<a id="export" href="javascript:void(0)"><span>导出</span></a>
            </pop:JugePermissionTag>
            <s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
            <a id="createTable" href="javascript:void(0)"><span>生成统计数据</span></a>
            </s:if>
        </div>
    </div>
    <div style="width:100%">
        <table id="statRegradedPointsList"></table>
        <div id="statRegradedPointsListPager"></div>
    </div>
    <div id="statRegradedPointsDialog"></div>
</div>
<script type="text/javascript">
function onMonthChange(){
	if($("#statYear").val() >= ${nowYear} && $("#statMonth").val() >= ${nowMonth})
        $("#audit").buttonDisable();
}

function callback(){
    var dfop = {
		nowYearValue:'${nowYear}',
		nowMonthValue:'${nowMonth}',
		internalIdValue:'${internalId}'
    		
    }
    TQ.statRegradedPoints(dfop)
}
$.cacheScript({
    url:'/resource/getScript/statAnalyse/statRegradedPoints/statRegradedPoints.js',
    callback: callback
})
</script>