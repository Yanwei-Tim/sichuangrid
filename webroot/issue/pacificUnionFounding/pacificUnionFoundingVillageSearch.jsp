<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<link href="resource/css/pacificUnionFounding.css" type="text/css" rel="stylesheet">
<script type="text/javascript" >
$(function(){
	var orgLevelId = '';
	$("#pacificUnionDiv").height($(".ui-layout-center").height()-75);
	$.each($("#orgMenuList li>a"),function(i,n){
		if($(n).attr("class")=="cur")
			orgLevelId = $(n).attr("orglevel");
	});
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#cyear").change(function(){
		$("#cmonth").empty();
		getmonth();
	});
	$("#csearch").click(function(){
		if($("#orgSelector").val()==null||$("#orgSelector").val()==''){
			$("#pacificUnionDiv").load("${path}/issue/pacificUnionFounding/emptyVillageList.jsp");
			return false;
		} 
		if($("#searchOrgLevelInternalId").val()!='<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>'){
			alert("请选择社区");
			return false;
		}
		var url = "${path }/peaceVillageManage/getPeaceVillageListBySearchVo.action?searchPeaceVillageVo.organization.id="+$("#searchOrgId").val()
				+"&searchPeaceVillageVo.organization.orgInternalCode="+$("#searchOrgInternalCode").val()
				+"&searchPeaceVillageVo.year="+$("#cyear").val()+"&searchPeaceVillageVo.month="+$("#cmonth").val();
		$("#pacificUnionDiv").load(url);
	});
	if(USER_ORG_LEVEL==orgLevelId){
		var userOrgInternalCode = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgInternalCode()'/>";
		var userOrgName = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgName()' escape='false'/>";
		
		$("#searchOrgId").val(USER_ORG_ID);
		$("#searchOrgInternalCode").val(userOrgInternalCode);
		$("#searchOrgLevelInternalId").val(USER_ORG_LEVEL);
		$("#orgSelector").val(userOrgName);
		$("#orgSelector").attr("readonly","readonly");
	}else{
		var orgTree=$("#orgSelector").treeSelect({
			nodeUrl:'/sysadmin/orgManage/ajaxOrgsForExtTreeByLevel.action?orgLevelInternalId='+orgLevelId,
			allOrg:false,
			isRootSelected:false,
			emptyText:'',
			rootId:USER_ORG_ID
		});
		orgTree.on("click",function(node,e) {
			if(node != null){
				$("#searchOrgId").val(node.attributes.id);
				$("#searchOrgInternalCode").val(node.attributes.orgInternalCode);
				$("#searchOrgLevelInternalId").val(node.attributes.orgLevelInternalId);
			}
		});
	}
	$("#csearch").click();
	
	
});
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
</script>
<div class="content">
	<input id="searchOrgId" name="searchOrgId" type="hidden" />
	<input id="searchOrgInternalCode" name="searchOrgInternalCode" type="hidden" />
	<input id="searchOrgLevelInternalId" name="searchOrgLevelInternalId" type="hidden" />
	<div class="grid_2 form-left">
	区域：
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="orgSelector"  class="form-txt" style="width:150px;"/>
 	</div>
 	<div class="ui-corner-all" id="nav">
 		<select name="searchYear" id="cyear" onchange="" style="float:left;margin-left: 5px;"></select>
	    <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="searchMonth" id="cmonth" onchange=""></select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
	    <a id="csearch" href="javascript:void(0)"><span>查询</span></a>
 	</div>
 	<div style="clear: both;"></div>
	<div style="width: 100%;overflow:auto;" id="pacificUnionDiv">

	</div>
</div>
