<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24" style="width:99.7%;height:340px;overflow: auto;" >
	<div class="ui-corner-all" id="nav">
		<a id="searchButton" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>查询</span></a>
		<a id="showButton" href="javascript:void(0)" style="display:none"><span><strong class="ui-ico-refresh"></strong>返回显示查询条件</span></a>
		<div class="btnbanner btnbannerData" style="width:200px">
			<select id="selectSendObjectId" class="basic-input" >
				<option value=''>请选发送对象</option>
				<c:forEach items="${smsSendObjectList }" var="obj">
						<option value='<s:property value="#obj.id"/>'><s:property value="#obj.name"/></option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	
	<div id="selectSendObjectListDiv" style="width: 97%;margin-left:10px;">
		<table id="selectSendObjectList"></table>
		<div id="selectSendObjectListPager"></div>
	</div>
	
	<div id="searchFormDiv" style="width: 97%;margin-left:10px;height:280px;overflow: auto;display:none;border: 1px #cccccc solid;">
		<form id="selectSendObjectForm" method="post" action="${path}/smsUplinkManage/addSendSMSJobSql.action">
			<div id="loadSearchDiv"></div>
		</form>
	</div>

		
</div>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#selectSendObjectList").jqGridFunction({
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:'orgId.orgName',index:"orgId",label:'所属网格',sortable:true,width:300},
	  		{name:"name",index:"name",label:'姓名',sortable:true,width:200},
	        {name:"mobile",index:"mobile",label:'联系手机',sortable:true,width:200}
		],
		sortname:"mobile",
		viewrecords: true,
		height:220,
		scrollrow:true
	});
	
	$("#searchButton").click(function(){
		var sendId= $("#selectSendObjectId").val();
		if(!sendId){
			$.messageBox({
				message:"发送对象不能为空！",
				level: "error"
			});
			return ;
		}
		$("#selectSendObjectListDiv").show();
		$("#searchFormDiv").hide();
		var data=$("#selectSendObjectForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
			dataJson[data[i].name]=data[i].value;
		}
		jQuery.extend(dataJson, {"map.smsSendId":sendId});
		$("#selectSendObjectList").setGridParam({
			url:"${path}/smsUplinkManage/findSmsSendPeopleInfoPager.action",
			datatype: "json",
			page:1
		});
		$("#selectSendObjectList").setPostData(dataJson);
		$("#selectSendObjectList").trigger("reloadGrid");
		
		$("#searchButton").hide();
		$("#showButton").show();
	});
	
	$("#showButton").click(function(){
		$("#showButton").hide();
		$("#searchButton").show();
		$("#selectSendObjectListDiv").hide();
		$("#searchFormDiv").show();
	});
	
	$("#selectSendObjectId").change(function(){
		$("#showButton").hide();
		$("#searchButton").show();
		if(!$(this).val()){
			$("#loadSearchDiv").html("");
			$("#selectSendObjectList").clearGridData();
			$("#selectSendObjectListDiv").show();
			$("#searchFormDiv").hide();
			return ;
		}else{
			$("#selectSendObjectListDiv").hide();
			$("#searchFormDiv").show();
		}
		$.ajax({
			url:'${path}/smsUplinkManage/getSmsSendObjectTemplateById.action?id='+$(this).val(),
			success:function(data){
				if(null == data || "null" == data){
					$.messageBox({
						message:$("#selectSendObjectId").find("option:selected").text()+"查询条件不存在，请联系管理员！",
						level: "error"
					});
					$("#loadSearchDiv").html("");
				}else{
					$("#loadSearchDiv").html(data);
				}
			}
		});
		
	});

});

</script>


