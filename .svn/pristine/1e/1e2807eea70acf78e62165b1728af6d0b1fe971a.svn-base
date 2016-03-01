<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">

<pop:formatterProperty name="population.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="population.schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />

$(document).ready(function(){
	jQuery("#groupFamilyMemberList").jqGridFunction({
		datatype: "local",
	 	colNames:['id','人员姓名','证件号码','性别','出生日期','联系手机','文化程度','常住地址'],
	 	height:$("#groupFamilyDialog").height()-100,
	   	colModel:[
		   {name:'id',index:'id',hidden:true,key:true,frozen:true},
   	       {name:'population.name',width:80,frozen:true,sortable:false},
	       {name:'population.idCardNo',width:120,frozen:true,sortable:false},
	       {name:'population.gender',formatter:genderFormatter,width:40,frozen:true,sortable:false},
	       {name:"population.birthday",width:80,sortable:false},
	       {name:'population.mobileNumber',width:100,sortable:false},
	       {name:'population.schooling',formatter:schoolingFormatter,width:80,sortable:false},
	       {name:'population.currentAddress',width:200,sortable:false}
	   	],
	   	showColModelButton:false,
	   	ondblClickRow: function(rowId){
	   		viewPopulationInfo(rowId);
	   	}
	});
	jQuery("#groupFamilyMemberList").jqGrid('setFrozenColumns');
	showList();
	
	function viewPopulationInfo(rowId) {
		var actionUrl;
		var pid = rowId.substr(0, rowId.indexOf("_"));
		var type = rowId.substr(rowId.indexOf("_")+1);
		if(type == '<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>'){
			actionUrl = '${path}/baseinfo/householdStaff/dispath.action?mode=view&population.id='+pid;
		}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>'){
			actionUrl = '${path}/baseinfo/floatingPopulationManage/dispath.action?mode=view&population.id='+pid;
		}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>'){
			actionUrl = '${path}/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=view&unsettledPopulation.id='+pid;
		}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>') {
			actionUrl = '${path}/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+pid;
		}else return;
		$("#groupFamilyPopulationDialog").createDialog({
			width: 800,
			height: 600,
			title:'查看家庭成员信息',
			modal : true,
			url:actionUrl,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
});

function showList() {
	var initParam = {
		"groupFamily.id": '<s:property value="#parameters.familyId"/>'
	};
	$("#groupFamilyMemberList").setGridParam({
		url:'${path}/baseinfo/familyInfo/findFamilyMembersByFamilyId.action',
		datatype: "json",
		page:1
	});
	$("#groupFamilyMemberList").setPostData(initParam);
	$("#groupFamilyMemberList").trigger("reloadGrid");
}
</script>

<div class="content">
	<div>
		<table id="groupFamilyMemberList"></table>
		<div id="groupFamilyMemberListPager"></div>
	</div>
</div>