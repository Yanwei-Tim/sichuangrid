<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
} 
$(document).ready(function(){
	
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	}); 
	 $("#year").change(function(){
			$("#month").empty();
			getmonth();
	});
	
	$("#searchType").change(function(){
		$("#accountDetailsList").setGridParam({
			url:"${path}/sysadmin/accountLoginDetailsManage/getAccountLoginDetailsList.action",
			datatype: "json",
			page:1
		});
		$("#accountDetailsList").setPostData({
			"year":$("#year").val(),
			"month":$("#month").val(),
			"searchType":$("#searchType").val(),
			"orgId":getCurrentOrgId()
	   	});
		$("#accountDetailsList").trigger("reloadGrid");
	});
	
	jQuery("#accountDetailsList").jqGridFunction({
	   	url:'${path}/sysadmin/accountLoginDetailsManage/getAccountLoginDetailsList.action',
	   	postData: {
	   		"year":$("#year").val(),
			"month":$("#month").val(),
			"searchType":$("#searchType").val(),
			"orgId":getCurrentOrgId()
        },
		datatype: "json",
	   	colModel:[
	   	    {name:'organization.id',index:'organization.id',sortable:false,hidden:true,frozen:true,hidedlg:true},
	   		{name:'organization.orgName',label:'所在组织机构',width:380,index:'organization.orgName', sortable:false},
	   		{name:'userName',label:'用户名',sortable:true,align:'center',width:120},
	   		{name:'name',label:'中文名',sortable:true,align:'center',width:120},
	   		{name:'currentWorkDay',label:'每月工作天数',sortable:true,align:'center',width:100},
	   		{name:'loginDay',label:'登录天数',sortable:true,align:'center',width:100},
	   		{name:'lastLoginTime',label:'最后登录时间',sortable:true,align:'center',width:200}
	   	]
	});
	
	$("#reload").click(function(){
		$("#accountDetailsList").trigger("reloadGrid");
	});
	
	$("#createAccountLoginDetails").click(function(){
			var nowDate=new Date(); 
			var nowYear=nowDate.getFullYear();
			var nowMonth=nowDate.getMonth()+1;
			if($("#year").val()==nowYear && $("#month").val()==nowMonth){
				$.messageBox({level:'warn',message:"不能生成当月报表"});
				return;
			}
			$.ajax({
				url:'${path}/sysadmin/accountLoginDetailsManage/createAccountDetails.action?year='+$("#year").val()+"&month="+$("#month").val()+"&orgId="+getCurrentOrgId()+"&searchType="+$("#searchType").val(),
				success:function(responseData){
					if(responseData=='true' || responseData==true ){
						$.messageBox({message:"报表生成成功"});
						$("#accountDetailsList").trigger("reloadGrid");
					}else{
						$.messageBox({level:'error',message:responseData});
					}
				}
			});
	});
	
	$("#detailsExport").click(function(){
		if ($("#accountDetailsList").getGridParam("records")>0){
			$("#accountDetailsDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出账号登录详情',
				url:PATH+'/common/exportExcel.jsp',
				postData:{
					gridName:'accountDetailsList',
					downloadUrl:PATH+'/sysadmin/accountLoginDetailsManage/downloadAccountLoginDetails.action'
					},
				buttons: {
		   			"导出" : function(event){
		   				exceldownloadSubmitForm();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
		}
	});
	
	$("#searchList").click(function(){
		$("#accountDetailsList").setGridParam({
			url:"${path}/sysadmin/accountLoginDetailsManage/getAccountLoginDetailsList.action",
			datatype: "json",
			page:1
		});
		$("#accountDetailsList").setPostData({
			"year":$("#year").val(),
			"month":$("#month").val(),
			"searchType":$("#searchType").val(),
			"orgId":getCurrentOrgId(),
			"sidx":"orgCode",
			"sord":"desc"
	   	});
		$("#accountDetailsList").trigger("reloadGrid");
	});
	
});

</script>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>
		<select class="basic-input" id="searchType" name="searchType">
			<option value="0" selected="selected">本级</option>
			<option value="1">直属下辖</option>
			<option value="2">全部</option>
		</select>
		<select name="year" id="year" onchange="">
        </select>
        <label style="padding:0 10px;line-height:25px;">年</label>
        <select name="month" id="month" onchange="">
        </select>
        
        <label style="padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>
		
		<pop:JugePermissionTag ename="detailsExport">
				<a id="detailsExport" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
			<a id="createAccountLoginDetails" hidden="true" href="javascript:void(0)"><span>生成报表</span></a>
		</s:if>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%">
		<table id="accountDetailsList"></table>
		<div id="accountDetailsListPager"></div>
	</div>
	
	<div id="accountDetailsDialog"></div>
</div>

