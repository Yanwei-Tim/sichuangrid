<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container_24">
<div class='clearLine'></div>
<div class='grid_24'>
	<b>现有成员</b>
</div>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="mainTeamMemberList"></table>
		<div id="mainTeamMemberListPager"></div>
	</div>
	<div id="TeamMemberMaintanceDialog"></div>
</div>
<div class='clearLine'></div>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
	    <input type="text" value="请输入姓名或身份证号码" name="searchText" id="searchText" 
	    maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名或身份证号码':this.value;"
	    onfocus="value=(this.value=='请输入姓名或身份证号码')?'':this.value;"/>
		<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<a id="fastSearchButton" href="javascript:;"><span>检索</span></a>
	<a id="appendMember" href="javascript:;"><span>添加</span></a>
	<pop:JugePermissionTag ename="addServiceTeamMember">
		<a id="addMember" href="javascript:;"><span>新增</span></a>
	</pop:JugePermissionTag>
</div>
<div class='clearLine'></div>
<div class='grid_24'>
	<b>可选成员</b>
</div>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="appendTeamMemberList"></table>
		<div id="appendTeamMemberListPager"></div>
	</div>
</div>
<input type="hidden" id="_teamId_" value="${teamId}"/>
<input type="hidden" id="_orgId" value="${organizationId}"/>
<input type="hidden" id="_teamClazzId" value="${teamClazzId}"/>
<input id="position_in_team" type="hidden" name="positionInTeam" value="" />
</div>

<script type="text/javascript">
var dialogWidth=650;
var dialogHeight=360;

function positionFormatter(el,options,rowData){
	var content = '<select name="position" id="_position_'+rowData.id+'">';
	content = content.concat("<option value='0' selected='selected'>请选择</option>");
	$.ajax({
		url:"${path}/baseinfo/serviceTeamMemberManage/findPropertyDicts.action?teamClazzId="+$("#_teamClazzId").val(),
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
	var selectedIds = $("#appendTeamMemberList").jqGrid("getGridParam", "selarrrow");
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

function positionFormat(el,options,rowData){
	var content = '';
	if(rowData["position"] && rowData["position"].id){
		$.ajax({
			url:"${path}/baseinfo/serviceTeamMemberManage/findPropertyDicts.action?teamClazzId="+$("#_teamClazzId").val(),
			cache: false,
			async: false,
			success:function(dicts){
				if(null != dicts) {
					$.each(dicts, function(ind, val){
						if(val.id == rowData["position"].id){
							content = val.displayName;
						}
					});
				}
			}
		});
	}else{
		content=rowData["position"];
	}
	return content;
}

function operationFormatter(el,options,rowData){
	return '<a href="javascript:emitRelation('+rowData.baseID+')"><span>移除</span></a>';
}

function emitRelation(id) {
	if($("#_teamId_").val().length > 0) {
		$.ajax({
			async: false,
			url:"${path}/baseinfo/serviceTeamMemberManage/emitRelation.action",
		   	data:{
		   		"searchServiceTeamMemberVo.baseId":id,
				"searchServiceTeamMemberVo.teamId":$('#_teamId_').val()
	        },
			success:function(responseData){
				if(responseData==true){ 
					$("#mainTeamMemberList").delRowData(id);
				}else{
					$.messageBox({level:'error',message:responseData});
				}
			}
		});
	}
	$("#mainTeamMemberList").trigger("reloadGrid");
	$("#appendTeamMemberList").trigger("reloadGrid");
}

$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});

$(document).ready(function(){
	<pop:formatterProperty name="serviceTeamMemberBase.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	function genderFormat(cellvalue,options,rowData){
		if(cellvalue && cellvalue.id){
			if(genderDate[cellvalue.id]){
				return genderDate[cellvalue.id];
			}else{
				return "&nbsp;"
			}
		}else{
			return cellvalue;
		}						
	}
	
	$("#mainTeamMemberList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['id','baseId','orgId','姓名','身份证号码','性别','职务','联系手机','固定电话','备注','操作'],
	   	colModel:[
	   	    {name:"id",index:"id",hidden:true},
	   	    {name:"baseID",index:"baseID",hidden:true},
	        {name:"serviceTeamMemberBase.orgId",index:"orgId",hidden:true},
	        {name:'serviceTeamMemberBase.name',index:"name",width:100,align:"center"},
	        {name:'serviceTeamMemberBase.idCardNo',index:'idCardNo',width:150,align:"center"},
	        {name:'serviceTeamMemberBase.gender',index:'gender',width:50,formatter:genderFormat,align:"center"},
	        {name:"position",index:"position",width:100,formatter:positionFormat,align:"center"},
	        {name:'serviceTeamMemberBase.mobile',width:100,index:'mobile',sortable:false,width:150,align:"center"},
	        {name:'serviceTeamMemberBase.homePhone',index:'homePhone',sortable:false,width:150,hidden:true},
	        {name:'serviceTeamMemberBase.remark',index:'remark',sortable:false,width:200,hidden:true},
	        {name:'',index:'',formatter:operationFormatter,sortable:false,width:100,align:"center"}
		],
		height:120,
		showColModelButton:false
	});
	jQuery("#mainTeamMemberList").jqGrid('setFrozenColumns');
	
	$("#mainTeamMemberList").setGridParam({
		url:"${path}/baseinfo/serviceTeamMemberManage/findServiceTeamMemberInTeam.action",
		datatype: "json",
		page:1
	});
	$("#mainTeamMemberList").setPostData({
		"onDuty":"true",
		"teamId":"${teamId}",
		"organizationId":$("#_orgId").val()
   	});
	$("#mainTeamMemberList").trigger("reloadGrid");


	$("#appendTeamMemberList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['id','orgId','姓名','身份证号码','性别','职务','联系手机','固定电话'],
	   	colModel:[
	   	    {name:"id",index:"id",hidden:true},
	        {name:"orgId",index:"orgId",hidden:true},
	        {name:'name',index:"name",width:100,align:"center"},
	        {name:'idCardNo',index:'idCardNo',width:150,align:"center"},
	        {name:'gender',index:'gender',width:50,align:"center",formatter:genderFormat},
	        {name:'position',index:'position',formatter:positionFormatter,align:"center"},
	        {name:'mobile',width:100,index:'mobile',sortable:false,width:150,align:"center"},
	        {name:'homePhone',index:'homePhone',sortable:false,width:150,hidden:true}
		],
		height:120,
		multiselect:true,
		showColModelButton:false
	});
	jQuery("#appendTeamMemberList").jqGrid('setFrozenColumns');
	
	$("#appendTeamMemberList").setGridParam({
		url:"${path}/baseinfo/serviceTeamMemberManage/findServiceTeamMemberNotInTeam.action",
		datatype: "json",
		page:1
	});
	$("#appendTeamMemberList").setPostData({
		"searchServiceTeamMemberVo.teamId":"${teamId}",
		"searchServiceTeamMemberVo.orgId":$("#_orgId").val()
   	});
	$("#appendTeamMemberList").trigger("reloadGrid");

	$("#addMember").click(function(event){
		$("#TeamMemberMaintanceDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:'新增成员信息',
			url:'${path}/baseinfo/serviceTeamMemberManage/dispatchOperate.action?mode=add&dailogName=TeamMemberMaintanceDialog&organizationId='+$("#currentOrgId").val()+'&teamId='+$("#_teamId_").val(),
			buttons: {
					"保存并关闭" : function(event){
			   			$("#maintainForm").submit();
			   			$("#_isSubmit").val("true");
		   			},
		   			"保存并继续" : function(event){
			   			$("#maintainForm").submit();
			   			$("#_isSubmit").val("false");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
			}
		});
	});

	$("#appendMember").click(function(){
		var selectedIds = $("#appendTeamMemberList").jqGrid("getGridParam", "selarrrow");
		var data="";
		$.each(selectedIds, function(ind, val){
			if(null != $("#_position_"+val).find(":selected").val() && $("#_position_"+val).find(":selected").val() != "0") {
				var dict = $("#appendTeamMemberList").getRowData(val);
				dict.position = {"id":$("#_position_"+val).find(":selected").val()};
				var p={"id":dict.id,"position":dict.position,"serviceTeamMemberBase.orgId":dict.orgId,
						"serviceTeamMemberBase.name":dict.name,"serviceTeamMemberBase.idCardNo":dict.idCardNo,
						"serviceTeamMemberBase.gender":dict.gender,"serviceTeamMemberBase.mobile":dict.mobile,
						"serviceTeamMemberBase.homePhone":dict.homePhone,"baseID":dict.id};
				$("#mainTeamMemberList").addRowData(dict.id,p);
				data = data+","+dict.id+"-" + dict["position"].id;
			}
		});
		if(!fetchPositionInTeam()){
			$.messageBox({
				message : "请选中成员并选择职务再进行添加！",
				level : "warn"
			});
			return ;
		}
		$.ajax({
			async: false,
			url:"${path}/baseinfo/serviceTeamMemberManage/addServiceTeamMember.action",
		   	data:{
				"positionInTeam":data.substr(1),
				"teamId":"${teamId}"
	        },
			success:function(responseData){
	        	$("#appendTeamMemberList").trigger("reloadGrid");
			}
		});
	});

	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#appendTeamMemberList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#appendTeamMemberList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	
	$("#fastSearchButton").click(function(){
		search($("#_teamId_").val());
	});
	function search(teamId){
		var conditions = $("#searchText").val();
		if(conditions == '请输入姓名或身份证号码') return;
		$("#appendTeamMemberList").setPostData({
			"searchServiceTeamMemberVo.teamId":"${teamId}",
			"searchServiceTeamMemberVo.orgId":$("#_orgId").val(),
			 "searchServiceTeamMemberVo.cardOrName":$("#searchText").val()
	   	});
		$("#appendTeamMemberList").trigger("reloadGrid");
	}

});

</script>