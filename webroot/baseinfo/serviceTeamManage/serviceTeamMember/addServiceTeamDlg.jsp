<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%request.setAttribute("baseId", request.getParameter("baseId")); %>

<div id="addTeamPosition" class="container container_24">
	<form action="${path}/baseinfo/serviceTeamMemberManage/addServiceTeamMemberDetails.action" method="post" id="addTeamPositionForm">
	<pop:token />
		<input id="baseId" type="hidden" name="serviceTeamMemberBase.id" value="${baseId}" />
		<input id="position_in_team" type="hidden" name="positionInTeam" value="" />
		</form>
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
			    <input type="text" value="快速搜索组织名称" id="_teamName" maxlength="24" class="basic-input" onblur="value=(this.value=='')?'快速搜索组织名称':this.value;" onfocus="value=(this.value=='快速搜索组织名称')?'':this.value;" style="width:300;"/>
			</div>
			<a href="javascript:;" id="_fastSearch" class="search"><span>检索</span></a>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="content">
			<table id="unholdServiceTeamList"></table>
			<div id="unholdServiceTeamListPager"></div>
		</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="teamClazz" domainName="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />

function positionFormatter(el,options,rowData){
	var content = '<select name="position" id="_position_'+rowData.id+'">';
	content = content.concat("<option value='0' selected='selected'>请选择</option>");
	$.ajax({
		url:"${path}/baseinfo/serviceTeamMemberManage/findPropertyDicts.action?teamClazzId="+rowData.teamClazz.id,
		cache: false,
		async: false,
		success:function(dicts){
			if(null != dicts) {
				$.each(dicts, function(ind, val){
					content = content.concat("<option value="+val.id+" >"+val.displayName+"</option>");
				});
			}
		}
	});
	content = content.concat("</select>");
	return content;
}

function fetchPositionInTeam(){
	var selectedIds = $("#unholdServiceTeamList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds.length==0){
		return false;
	}
	var positionInTeam = '';
	var flag=true;
	$.each(selectedIds, function(ind, val){
		if($("#_position_"+val).val()==0){
			flag=false;
			return false;
		}
		positionInTeam = positionInTeam.concat(val+'_'+$("#_position_"+val).val());
		positionInTeam = positionInTeam.concat(":");
	});
	positionInTeam = positionInTeam.substr(0, positionInTeam.length-1);
	$("#position_in_team").val(positionInTeam);
	return flag;	
}


$(document).ready(function() {
	$("#unholdServiceTeamList").jqGridFunction({
		datatype: "local",
		height:150,
		colNames:['id','所属团队','团队类别','团队名称','职务'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true,key:true},
	        {name:'teamClazz',formatter:teamClazzFormatter,width:150},
	        {name:'teamType',formatter:teamTypeFormatter,width:150},
	        {name:'name', width:150},
	        {name:'position',formatter:positionFormatter,sortable:false,width:120}
		],
		showColModelButton:false,
		multiselect:true
	});
	
	
	$("#_fastSearch").click(function(){
		var condition = $("#_teamName").val();
		var listParam = null;
		if('快速搜索组织名称' != condition) {
			listParam = {
				'searchServiceManageTeamVo.orgScope':'<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>',
				'searchServiceManageTeamVo.orgId':getCurrentOrgId(),
				'searchServiceManageTeamVo.operation':'exclude',
				'searchServiceManageTeamVo.teamName':condition,
				'searchServiceManageTeamVo.baseId':"${baseId}"
			};
		}else listParam = {
				'searchServiceManageTeamVo.orgScope':'<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>',
				'searchServiceManageTeamVo.orgId':getCurrentOrgId(),
				'searchServiceManageTeamVo.operation':'exclude',
				'searchServiceManageTeamVo.baseId':"${baseId}"
			};
		loadData(listParam, 'unholdServiceTeamList');
	});
	
	function loadData(listParam, divList){
		$("#"+divList).setGridParam({
			url:'${path}/baseinfo/serviceTeamManage/pageServiceTeams.action',
			datatype: "json",
			page:1
		});
		$("#"+divList).setPostData(listParam);
		$("#"+divList).trigger("reloadGrid");
	}
	
	$("#addTeamPositionForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		if(!fetchPositionInTeam()){
			$.messageBox({
				message : "请选中团队并选择职务再进行添加！",
				level : "warn"
			});
			return ;
		}
		$(form).ajaxSubmit({
			 success : function(data) {
				if (!data.id) {
					$.messageBox({
						message : data,
						level : "error"
					});
					return;
				}
				$.messageBox({message:"团队职务添加成功！"});
               	$("#_serviceTeamMemberDialog").dialog("close");
				$("#serviceTeamMemberList").trigger("reloadGrid");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{},
		messages:{}
	});
});
</script>