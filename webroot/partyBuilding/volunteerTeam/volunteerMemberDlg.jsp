<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%
	request.setAttribute("orgId",request.getParameter("orgId"));
	request.setAttribute("teamId",request.getParameter("teamId"));
%>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名或身份证号码" name="searchText" id="searchAddTextMember" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入姓名或身份证号码':this.value;" onfocus="value=(this.value=='请输入姓名或身份证号码')?'':this.value;" />
				<button id="refreshSearchKeyMember1" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButtonMemberEdit"><span>搜索</span></a>
		<a id="reloadMemberEdit" href="javascript:void(0);"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="memberEditList"></table>
		<div id="memberEditListPager"></div>
	</div>
	<form id="maintainForm" action="/volunteerMemberManage/addVolunteerMember.action">
		<pop:token />
		<input type="hidden" name="volunteerMember.orgId" value="${orgId }">
		<input type="hidden" name="volunteerMember.teamId" value="${teamId }">
		<input type="hidden" id="memberIds" name="memberIds" value="">
	</form>
</div>
<script type="text/javascript">
var autoHeight=100;
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />

function addressFormatter(el, options, rowData){
	if(rowData.province==null){
		return '';
	}
	if(rowData.city==null){
		return rowData.province;
	}
	if(rowData.district==null){
		return rowData.province+'-'+rowData.city;
	}
	return rowData.province+'-'+rowData.city+'-'+rowData.district;
}

$(function (){
	$("#memberEditList").jqGridFunction({
		datatype: "local",
		height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-130,
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"idCardNo",sortable:true,label:'身份证号',width:150},
	        {name:"name",sortable:true,label:'姓名',width:100},
	        {name:"gender",sortable:true,label:'性别',width:100,formatter:genderFormatter},
	        {name:"address",sortable:false,label:'户籍地',width:180,formatter:addressFormatter},
	        {name:"nativePlaceAddress",sortable:false,label:'现居地址',width:180}
		],
		multiselect: true,
		onSelectRow:getMemberSelectedIds,
		onSelectAll:getMemberSelectedIds
	});
	
	$("#fastSearchButtonMemberEdit").click(function(){
		var conditions = $("#searchAddTextMember").val();
		if(conditions == '请输入姓名或身份证号码') return;
		searchAddMemberData({
			"searchVo.teamId":"${teamId}",
			"searchVo.orgId":"${orgId}",
			"searchVo.searchValue":conditions
		});
	});
	$("#searchAddTextMember").focus(function(){
        this.select();
	 });
	$("#refreshSearchKeyMember").click(function(){
		$("#searchAddTextMember").attr("value","请输入姓名或身份证号码");
	});
	
	$("#reloadMemberEdit").click(function(){
		$("#searchAddTextMember").attr("value","请输入姓名或身份证号码");
		searchAddMemberData({
			"searchVo.teamId":"${teamId}",
			"searchVo.orgId":"${orgId}"
		});
	}).click();

	function searchAddMemberData(postData) {
		$("#memberEditList").setGridParam({
			url:"${path}/volunteerMemberManage/findMembersByOrgCodeAndTeamId.action",
			datatype: "json",
			page:1
		});
		$("#memberEditList").setPostData(postData);
		$("#memberEditList").trigger("reloadGrid");
	}


	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			if(validateSelected()) {
				$.messageBox({level:"warn",message:"请至少选择一条数据!"});
				return;
			}
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
						$.messageBox({level: "error",message:data});
						return;
					}
					$.messageBox({message:"党员志愿者队伍成员新增成功!"});
					//$("#searchTextMember").attr("value","请输入姓名或身份证号码");
					$("#reloadMember").click();
					//$("#memberList").trigger("reloadGrid");
					$("#maintainForm").parents(".ui-dialog-content").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		}
	});
});

function validateSelected(){
	var selectedIds = $("#memberEditList").jqGrid("getGridParam", "selarrrow");
	return selectedIds==null||selectedIds.length==0;
}

function getMemberSelectedIds(){
	var selectedIds = $("#memberEditList").jqGrid("getGridParam", "selarrrow");
	$("#memberIds").val(selectedIds);
}
</script>