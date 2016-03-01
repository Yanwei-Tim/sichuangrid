<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:if test='location.id == null || location.id == ""'>未获取到实有单位ID。</s:if>
<s:else>
<form id="practitionersMaintainForm" action="${path}/baseinfo/actualCompanyManage/maintainPractitioners.action" method="post">
	<input type="hidden" name="location.id" value="${location.id}">
	<input type="hidden" name="locationIds" id="locationIds" value="${locationIds}">
</form>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div style="float: left; margin-right: 5px; padding-top: 2px;">
			<input name="selectOrgName" id="claimOrgSelectorAddpractitioners" type="text" class="form-txt">
			<input id="orgIdValueAddpractitioners" type="hidden"/></div>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名" name="searchAddPractitionersText" id="searchAddPractitionersText" maxlength="18" class="searchtxt"
					 style="width:180px;" onblur="value=(this.value=='')?'请输入姓名':this.value;" onfocus="value=(this.value=='请输入姓名')?'':this.value;" />
				<button id="refreshSearchAddPractitionersKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a href="javascript:;" id="fastSearchAddPractitionersButton"><span>搜索</span></a>
			<a href="javascript:;" id="refreshAddPractitioners"><span><strong class="ui-ico-xz"></strong>刷新</span></a>
		</div>
	</div>
</div>
<div style="width: 100%;">
	<table id="addpractitionersList"></table>
	<div id="addpractitionersListPager"></div>
</div>
<script type="text/javascript">
var orgSelectorAddpractitioners = $("#claimOrgSelectorAddpractitioners").treeSelect({
	rootId: USER_ORG_ID,
	width: 200,
	inputName:"orgIdValueAddpractitioners"
});
$.addClick(orgSelectorAddpractitioners,getOrgValueAddpractitioners);
	
function getOrgValueAddpractitioners(){
	var selectOrgId=$.getSelectedNode(orgSelectorAddpractitioners).attributes.orgInternalCode;
	if (selectOrgId!=null){
		$("#orgIdValueAddpractitioners").val(selectOrgId);
	}
}
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
function selectPractitioners(id, isSelect){
	if(isSelect == false || isSelect == 'false'){
		var ids = id.toString().split(',');
		for(var i = 0; i < ids.length; i++){
			$("#locationIds").val($("#locationIds").val().replace(ids[i]+",", ''));
		}
	}else{
		var _locationIds = $("#locationIds").val();
		var _selectedIds = $("#addpractitionersList").jqGrid("getGridParam", "selarrrow");
		for(var i=0; i < _selectedIds.length; i++){
			if(_locationIds.indexOf(_selectedIds[i] + ",") == -1){
				_locationIds += _selectedIds[i] + ",";
			}
		}
		$("#locationIds").val(_locationIds);
	}
}
$(function(){
	$("#practitionersMaintainForm").formValidate({
		submitHandler: function(form) {
			if($("#locationIds").val() == ""){
             	$.messageBox({ message:"请至少选择一条数据", level: "warn"});
             	return;
			}
         	$(form).ajaxSubmit({
         		async : false,
         		success : function(data){
               	 	$.messageBox({ message: "保存成功！"});
               	 	$("#refreshPractitioners").click();
               	 	$("#practitionersDialog").dialog("close");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      	alert("提交错误");
	      	   	}
      	  	});
		},
		ignore:':hidden',
		rules:{
		},
		messages:{
		}
	});
	$("#addpractitionersList").jqGridFunction({
		mtype:'post',
		height: 390,
		datatype: "local",
		multiselect:true,
		onSelectAll: function(p1, p2){selectPractitioners(p1, p2);},
		onSelectRow: function(p1, p2){selectPractitioners(p1, p2);},
		loadComplete: function (){
			var _locationIds = $("#locationIds").val().split(',');
			for(var i = 0; i < _locationIds.length ; i++){
				$("#addpractitionersList").setSelection(_locationIds[i], true);
			}
		},
		colModel:[
	        {name:"remark",index:"id",hidden:true,hidedlg:true},
	        {name:"id",hidden:true},
	        {name:"name", label:'姓名',width:120,sortable:false,align:'center' },
		    {name:"gender",label:'性别',width:100,sortable:false,formatter: genderFormatter,align:'center'},
		    {name:"idCardNo",sortable:false,label:"身份证号码",align:'center',width:280}
		]
	});
	function searchPractitioners(){
		$("#addpractitionersList").setGridParam({
			url:"${path}/baseinfo/actualCompanyManage/findActualCompanysAddPractitionersForList.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#addpractitionersList").setPostData({"location.id": '${location.id}', 'location.orgInternalCode': $("#orgIdValueAddpractitioners").val(), 'location.corporateRepresentative': $("#searchAddPractitionersText").val() == '请输入姓名' ? '' : $("#searchAddPractitionersText").val()});
	    $("#addpractitionersList").trigger("reloadGrid");
	}
	searchPractitioners();
	$("#refreshAddPractitioners").click(function (){$("#searchAddPractitionersText").val('请输入姓名');searchPractitioners();});
	$("#fastSearchAddPractitionersButton").click(function (){searchPractitioners();});
	$("#refreshSearchAddPractitionersKey").click(function (){$("#searchAddPractitionersText").val('请输入姓名');});
});
</script></s:else>