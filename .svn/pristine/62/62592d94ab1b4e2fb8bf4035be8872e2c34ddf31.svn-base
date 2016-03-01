<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<% String serviceTeamId = request.getParameter("serviceTeamId"); %>
<div class="container container_24">
<s:action name="ajaxOrganization" var="findById" namespace="/sysadmin/orgManage" executeResult="false">
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getOrganization().id"></s:param>
</s:action>
<form id="maintainServiceObjectForm" method="post">
<pop:token />
<input type="hidden" id="currOrgId" name="searchServiceObjectVo.orgId" value="<s:property value="#findById.organization.id"/>" />
<input type="hidden" id="currOrgCode" name="searchServiceObjectVo.orgInternalCode" value="<s:property value="#findById.organization.orgInternalCode"/>" />
<input id="autonomyTeamOrgId" type="hidden" name="serviceManageTeam.organization.id"
	value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
<input id="serviceTeamId" name="searchServiceObjectVo.serviceTeamId" type="hidden" value="<%=serviceTeamId %>"/>
<div class="grid_4 lable-right"><label class="form-lb1">所属区域：</label></div>
<div class="grid_18">
<input type="text" id="OrgName" name="searchServiceObjectVo.orgName"
				value="<s:property value="#findById.organization.orgName"/>" />
</div>
<div class="grid_4 lable-right"><label class="form-lbl">对象类型：
</label></div>
<div class="grid_7"><select name="searchServiceObjectVo.populationBigType"
	id="populationBigType" class="form-select {isSelect:true,messages:{isSelect:'请选择类型'}}"
	<s:if test='"view".equals(mode)'>disabled="disabled"</s:if>>
	<!-- <option value='-1'>请选择</option> -->
	<option value=<s:property value="@com.tianque.core.util.BaseInfoTables@IMPORTANTPERSONNEL_KEY"/>>特殊人群</option>
	<option value=<s:property value="@com.tianque.core.util.BaseInfoTables@BIRTH_KEY"/>>计生</option>
	<option value=<s:property value="@com.tianque.core.util.BaseInfoTables@CIVIL_KEY"/>>民政</option>
</select></div>
<div class="grid_4 lable-right"><label class="form-lbl">对象子类型：
</label></div>
<div class="grid_7"><select name="searchServiceObjectVo.populationType"
	id="populationType" class="form-select {isSelect:true,messages:{isSelect:'请选择类型'}}"
	<s:if test='"view".equals(mode)'>disabled="disabled"</s:if>>
	<!-- <option value='-1'>请选择</option> -->
</select></div>
<div class='clearLine'></div>
<div class="grid_4 lable-right"><label class="form-lb1">姓名：</label></div>
<div class="grid_18"><input type="text"
	id="name" maxlength="10"
	name="searchServiceObjectVo.name" style="width: 97%"
	value="${searchServiceObjectVo.name}" title="请输入姓名"
	class='form-txt {maxlength:10,messages:{maxlength:$.format("组织名称最多输入{0}个字符")}}' />
</div>
<div class="grid_4 lable-right"><label class="form-lb1">身份证号码：</label></div>
<div class="grid_18"><input type="text"
	id="idCardNumber" maxlength="18"
	name="searchServiceObjectVo.idCardNumber" style="width: 97%"
	value="${searchServiceObjectVo.idCardNumber}" title="请输入身份证号码"
	class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:18,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("身份证号码至少需要输入{0}个字符"),maxlength:$.format("身份证号码最多需要输入{0}个字符")}}' />
</div>
<div class='clearLine'></div>
<div style="margin-left: 425px"><input type="button"  id='superSearch'  value='查询' style="width: 50px;height: 28px;"/><input type="button"  id='superReload'  value='刷新' style="width: 50px;height: 28px;margin-left: 8px;"/>
</div>
<div class='clearLine'></div>
</form>

<div class='clearLine'></div>
<div style="width: 100%;margin-top: 6px">
		<table id="PopulationForAddServiceObjectList"> </table>
		<div id="PopulationForAddServiceObjectListPager"></div>
</div>
</div>
<script>
	function onOrgChanged(){
	}
$(document).ready(function(){
	onOrgChanged();
	var tree=$("#OrgName").treeSelect({
		inputName:"searchServiceObjectVo.orgId",
		listWidth:260,
		maxHeight:300,
		rootId:'<s:if test="#findById.organization.orgType.internalId==@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"><s:property value="#findById.organization.parentOrg.id"/></s:if><s:else><s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/></s:else>',
		onSelect:function(){
			
		}
	});
	loadPopulationType();
	$("#populationBigType").change(function(){
		loadPopulationType();
	 });
	function loadPopulationType(){
		var key = $("#populationBigType").val();
		//if($(this).find("option:selected").text()!="请选择"){
			$("#populationType").empty();
			$.ajax({
				async: false,
				url: "${path}/baseinfo/serviceTeam/searchServiceObject/findPopulationTypeBypopulationBigType.action",
				data:{
					"key":key
				},
				success:function(list){
					//$("#populationType").append("<option value='-1'>请选择</option>");
					for(var j = 0 ;j<=list.length;j++){
						var map = list[j];
					for(var i in map){ 
						$("#populationType").append("<option value='"+i+"'>"+map[i]+"</option>");
					}
					}
				}
			});
		/*}else{
			$("#populationType").empty();
			$("#populationType").append("<option value='-1'>请选择</option>");
		}*/
	}
	jQuery.validator.addMethod("isSelect", function(value, element){
	    if(value == null  || value ==-1){
            return false;
		  }
		return true;
	});
	
	<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	$("#PopulationForAddServiceObjectList").jqGridFunction({
		datatype: "local",
   		colModel:[
			{name:"populationId",index:"populationId",frozen:true,hidden:true},    
     		{name:"sid",index:"sid",hidden:true,sortable:false,frozen:true},
      		{name:"name",index:'name',label:'姓名',width:100,sortable:false},
      		{name:"gender",label:'性别',formatter:genderFormatter,sortable:false,width:60,align:'center'},
      		{name:'idCardNo',index:'idCardNo',label:'身份证号码',sortable:false,width:180},
      		{name:'attentionPopulationTypeCname',label:'详细类型',index:"attentionPopulationTypeCname",sortable:false, width:180},
 	  		{name:'populationType',label:'详细类型',index:"populationType",sortable:false,hidden:true,frozen:true}
		],
		height:128,
    	multiselect:true,
    	rowNum:5,
    	rowList:[5,10,15,20,25,30,50,80,100]
	});
	
	$("#superSearch").click(function(){
	   	var data=$("#maintainServiceObjectForm").serializeArray();
	   	var dataJson={};
		for(i=0;i<data.length;i++){
			 if (dataJson[data[i].name]) {
				           dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				            dataJson[data[i].name] = data[i].value;
			}
		}
		$("#PopulationForAddServiceObjectList").setGridParam({
		    url:"${path}/baseinfo/serviceTeam/searchServiceObject/findPopulationListForAddServiceObject.action",
			datatype: "json",
			page:1
		});
		$("#PopulationForAddServiceObjectList").setPostData(dataJson);
	    $("#PopulationForAddServiceObjectList").trigger("reloadGrid");
	});
	$("#superReload").click(function(){
		 $("#name").val("");
		 $("#idCardNumber").val("");
		 $("#PopulationForAddServiceObjectList").clearGridData();
	});
	
});

function addServiceObject(){
	var selectedIds = $("#PopulationForAddServiceObjectList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds=="" || selectedIds==null){
		$.messageBox({message:"没有选择人员用于新增！"});
		return;
	}
	$.ajax({
		async:false,
		url: "${path}/baseinfo/serviceTeam/serviceObject/addServiceObject.action?selectedIds="+selectedIds,
		data:{
			"serviceObject.populationType":$("#populationType").val(),
			"serviceObject.serviceTeamId":$("#serviceTeamId").val()
		},
		success:function(datas){
			if(datas){
				$.messageBox({message:"成功新增服务对象！"});
				$("#serviceObjectDialog").dialog("close");
				$("#serviceObjectList").trigger("reloadGrid");
			}else{
				$.messageBox({message:"新增服务对象失败！"});
			}
		}
	});
}

</script>