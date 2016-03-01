<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<link href="/resource/css/pacificUnionFounding.css" type="text/css" rel="stylesheet">
<script type="text/javascript" >
$(function(){
	$("#pacificUnionDiv").height($(".ui-layout-center").height()-75);
	var orgLevelId = '';
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
		}
	});
	
	$("#csearch").click(function(){
		var url = "${path}/issues/pacificUnionFoundingManage/findPacificUnionFoundings.action?parentOrgId="
			+$("#searchOrgId").val()+"&year="+$("#cyear").val();
		if($("#orgSelector").val()==null||$("#orgSelector").val()==''){
			$("#pacificUnionDiv").load("${path}/issue/pacificUnionFounding/emptyHighVillageList.jsp");
		} else {
			$("#pacificUnionDiv").load(url);
		}
	});
	if(USER_ORG_LEVEL==orgLevelId){
		var userOrgInternalCode = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgInternalCode()'/>";
		var userOrgName = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgName()' escape='false'/>";
		
		$("#searchOrgId").val(USER_ORG_ID);
		$("#searchOrgInternalCode").val(userOrgInternalCode);
		$("#orgSelector").val(userOrgName);
		$("#searchOrgLevelId").val(USER_ORG_LEVEL);
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
				$("#searchOrgLevelId").val(node.attributes.orgLevelInternalId);
			}
		});
	}
	$("#csearch").click();
});
</script>
<div class="content">
	<input id="searchOrgId" name="searchOrgId" type="hidden" />
	<input id="searchOrgInternalCode" name="searchOrgInternalCode" type="hidden" />
	<input id="searchOrgLevelId" name="searchOrgLevelId" type="hidden" />
	<div class="grid_2 form-left">
	区域：
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="orgSelector"  class="form-txt" />
 	</div>
 	<div class="ui-corner-all" id="nav">
 		<select name="searchYear" id="cyear" onchange="" style="float:left;">
        </select>
	    <label style="float:left;padding:0 10px;line-height:25px;">年</label>
	    <a id="csearch" href="javascript:void(0)"><span>查询</span></a>
 	</div>
 	<div style="clear: both;"></div>
	<div style="width: 100%;overflow:auto;" id="pacificUnionDiv">
		
	</div>
</div>
