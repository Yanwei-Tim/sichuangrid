<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
#pList tr.jqgrow td {
  white-space: normal !important;
  height:auto;
  vertical-align:text-top;
  padding-top:2px;
 }
</style>
<input type="hidden" id="id_search" name="id_search" value="<s:property value="#parameters.id"/>"/>
<input type="hidden" id="populationName" name="populationName" value="${populationName}"/>
<input type="hidden" id="populationType_search" name="populationType" value="<s:property value="@com.tianque.core.util.NewBaseInfoTables@COMPANYPLACEKEY"/>"/>
<input type="hidden" id="populationId_search" name="populationId" value="<s:property value="#parameters.id"/>"/>
<div class="content">
	<div style="width: 100%;">
		<table id="pList"></table>
		<div id="pListPager"></div>
	</div>
</div>

<script type="text/javascript">
var s_cid = $("#id_search").val();
$.ajax({
	async:false,
	url:"${path}/baseinfo/companyPlaceManage/getCompanyPlaceName.action",
	type:"post",
	data:{
		"companyPlace.cid":s_cid
		},
	success:function(data){
		if(data!= false && data!="false" ){
				$("#populationName").val(stringFormatter(data.name));
			}else{
			$.messageBox({message:"查询超时，请刷新页面！",level:"error"});
		}
	}
});

function stringFormatter(str){
	if(str==undefined || str=='undefined'){
		return "";
	}
	return str;
}

$(document).ready(function(){
	    $("#pList").jqGridFunction({
		url:"${path}/systemOperateLogManage/findSystemOperateLogsPagerBySearchVo.action",
		postData: {
	        "systemOperateLogVo.dataKeyword": $("#id_search").val(),
	        "systemOperateLogVo.searchType": $("#populationType_search").val(),
	        "systemOperateLogVo.dataId" : $("#populationId_search").val()
        },
		datatype: "json",
		colModel:[
	    	{name:"id", index:"id", hidden:true},
	    	{name:"operateDate", index:"operateDate",label:"时间",width:130},
	    	{name:"operatePerson", index:"operatePerson",label:"操作账号",width:135,align:"center",formatter:operationUserFormatter},
	    	{name:"businessType", index:"businessType",label:"操作详情",width:458,formatter:operationDetailsFormatter},
	    	{name:"operateType", index:"operateType",label:"操作类型",width:300,hidedlg:true,hidden:true},
	    	{name:"dataOrgId.orgName", index:"dataOrgId.orgName", width:200, label:"数据所属网格",hidedlg:true,hidden:true},
			{name:"dataBeforeOrgId.orgName", index:"dataBeforeOrgId.orgName", width:160, label:"数据操作后所属网格",hidedlg:true,hidden:true},
			{name:"operateSource", index:"operateSource", width:160, label:"操作源",hidedlg:true,hidden:true}
  		],
  		page:1,
  		width: <s:property value="#parameters.width"/>,
		height: <s:property value="#parameters.height"/>
	});
});

function operationUserFormatter(el,options,rowData){
	if(rowData.operatePerson=='admin'){
		return "系统消息";
	}
	return rowData.operatePerson;
}

function operationType(operateType){
	if(operateType=='1'){
	   return "【<font color='blue'>新增</font>】";
	}
	if(operateType=='2'){
		   return "【<font color='blue'>修改</font>】";
		}
	if(operateType=='3'){
		   return "【<font color='blue'>删除</font>】";
		}
	if(operateType=='8'){
		   return "【<font color='blue'>导入</font>】";
		}
	if(operateType=='12'){
		   return "【<font color='blue'>转移</font>】";
		}
	if(operateType=='14'){
		   return "【<font color='blue'>取消关注</font>】";
		}
	if(operateType=='15'){
		   return "【<font color='blue'>重新关注</font>】";
	}
	if(operateType=='16'){
		   return "【<font color='blue'>添加业务_安全生产重点</font>】";
	}
	if(operateType=='17'){
		   return "【<font color='blue'>添加业务_消防安全重点</font>】";
	}
	if(operateType=='18'){
		   return "【<font color='blue'>添加业务_治安重点</font>】";
	}
	if(operateType=='19'){
		   return "【<font color='blue'>添加业务_污染源</font>】";
	}
	if(operateType=='20'){
		   return "【<font color='blue'>修改业务_安全生产重点</font>】";
	}
	if(operateType=='21'){
		   return "【<font color='blue'>修改业务_消防安全重点</font>】";
	}
	if(operateType=='22'){
		   return "【<font color='blue'>修改业务_治安重点</font>】";
	}
	if(operateType=='23'){
		   return "【<font color='blue'>修改业务_污染源</font>】";
	}
	return "";
}
function operationDetailsFormatter(el,options,rowData){
	//alert(JSON.stringify(rowData));
	var result = "";
	result = "【"+$("#populationName").val()+"】";
	 
	if(rowData.dataOrgId != null){
		result = result + "在【"+rowData.dataOrgId.orgName+"】的";
		result = result +"【"+ rowData.operateSource +"】模块中";
	}
	result = result + operationType(rowData.operateType);
	if(rowData.operateType =="12"){ //如果是转移就在后面加上转移后的地址
		result = result + "到【"+rowData.dataBeforeOrgId.orgName+"】";
	}
	if(rowData.operateType == '2'){
		result = result + '，查看<a href="javascript:void(0)" onclick="viewCompareData('+rowData.id+')"><span style="color:blue;">详情</span></a>'
	}
	if(rowData.operateType == '23' || rowData.operateType == '22' || rowData.operateType == '21' || rowData.operateType == '20'){
		result = result + '，查看<a href="javascript:void(0)" onclick="viewCompareData('+rowData.id+')"><span style="color:blue;">详情</span></a>'
	}
	return result;
}

function viewCompareData(systemoOperateLogId){
	$("#compareDataDialog").createDialog({
		width:550,
		height:400,
		zIndex:9999,
		title:'数据对比',
		url:'${path}/systemOperateLogManage/viewCompareData.action?systemoOperateLogId='+systemoOperateLogId+"&modulKey=CompanyPlace",
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
				$("#systemLogList").trigger("reloadGrid");
			}
		}
	});
}
</script>