<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<style>
.tablelist .title {
width: 5%;
}
</style>

<%
request.setAttribute("ids",request.getParameter("ids"));
request.setAttribute("isPlaning",request.getParameter("isPlaning"));
%>
<form id="checkSummaryFrom" method="post" action="${path }/baseinfo/villageReportSummaryManage/checkReportStatus.action">
<input type="hidden" name="ids" value="${ids }"/>
<input type="hidden" name="checkStatus" id="checkStatus" value=""/>
</form>
<div class="zt_tabs_style">
	<div id="chartsTabs">
		<ul> 
			<c:if test="${isPlaning==0 }">
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=0">基本信息</a></li>
			 </c:if>
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=1&isPlaning=${isPlaning}">新村建设信息</a></li>
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=2&isPlaning=${isPlaning}">产业发展信息</a></li>
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=3&isPlaning=${isPlaning}">基础设施建设</a></li>
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=4&isPlaning=${isPlaning}">公共服务</a></li>
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=5&isPlaning=${isPlaning}">环境整治</a></li>
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=6&isPlaning=${isPlaning}">基层组织建设</a></li>
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=7&isPlaning=${isPlaning}">资金投入</a></li>
			<li><a href="${path }/baseinfo/villageReportSummaryManage/dispatchOpearteForMaintain.action?ids=${ids}&dataType=8&isPlaning=${isPlaning}">农民人均可支配收入</a></li>
		</ul>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
	// 列表信息 和 柱图 饼图   外层容器计算高度
	$.sigmaReportLayout();
	$.loadingComp("close");
	
		$("#checkSummaryFrom").formValidate({
			checkSummaryFrom: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data==true || data=='true'){
							 $.messageBox({message:"信息审核成功!"});
						}else{
							 $.messageBox({message:data,level:'error'});
						}
						$("#villageReportSummaryList").trigger("reloadGrid");
						$("#reportSummaryCheckDialog").dialog("close");
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			},
			messages:{
			}
		});
})


function enableOrDisableColumn(i){
	if(isGrid()){
		$("#chartsTabs").tabs("select", 2);
		$("#chartsTabs").tabs("disable", 0);
		$("#chartsTabs").tabs("disable", 1);
	}else{
		$("#chartsTabs").tabs("enable", 0);
		$("#chartsTabs").tabs("enable", 1);
	}
}
</script>