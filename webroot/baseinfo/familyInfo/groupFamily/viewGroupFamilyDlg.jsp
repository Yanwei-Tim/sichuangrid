<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
 <div class="container container_24">
     <input type="hidden" id="" value="" >
     <input type="hidden" id="groupFamilyId" name="groupFamily.id" value="${groupFamily.id }">
     <s:include value='/baseinfo/familyInfo/groupFamily/viewGroupFamily.jsp'></s:include>
     <br>
    
		<div>
			<table id="groupFamilyMemberList"></table>
			<div id="groupFamilyMemberListPager"></div>
		</div>
    
 </div>
 <script type="text/javascript">

<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />

function nameFont(el,options,rowData){
	return '<a href="javascript:viewDetail(\''+rowData.id+'\')"><span>'+rowData.population.name+'</span></a>';
}
function viewDetail(id){
	viewPopulationInfo(id);
} 
function viewPopulationInfo(rowId) {
	var actionUrl;
	var rowData = $("#groupFamilyMemberList").getRowData(rowId)
	var pid = rowData['population.encryptId'];
	var type = rowId.substr(rowId.indexOf("_")+1);
	if(type == '<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>'){
		actionUrl = '${path}/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&population.id='+pid;
	}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>'){
		actionUrl = '${path}/baseinfo/floatingPopulationManage/dispathByEncrypt.action?mode=view&population.id='+pid;
	}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>'){
		actionUrl = '${path}/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action?mode=view&unsettledPopulation.id='+pid;
	}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>') {
		actionUrl = '${path}/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+pid;
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
$(document).ready(function(){
	jQuery("#groupFamilyMemberList").jqGridFunction({
		datatype: "local",
	 	colNames:['id','encryptId','人员姓名','与家长的关系','证件号码','性别','出生日期','联系手机','文化程度','常住地址'],
	 	height:$("#groupFamilyDialog").height()-190,
	   	colModel:[
		   {name:'id',index:'id',frozen:true,hidden:true,key:true},
		   {name:'population.encryptId',frozen:true,hidden:true,key:true},
   	       {name:'population.name',width:80,frozen:true,formatter:nameFont,sortable:false},
   	       {name:"familyRelation.displayName",width:80,sortable:false},
	       {name:'population.idCardNo',width:150,frozen:true,sortable:false},
	       {name:'population.gender',formatter:genderFormatter,width:40,align:'center',frozen:true,sortable:false},
	       {name:"population.birthday",width:80,sortable:false,align:'center'},
	       {name:'population.mobileNumber',width:100,sortable:false,align:'center'},
	       {name:'population.schooling',formatter:schoolingFormatter,width:80,sortable:false},
	       {name:'population.currentAddress',width:200,sortable:false}
	   	],
	   	showColModelButton:false,
	   	ondblClickRow: function(rowId){
	   		viewPopulationInfo(rowId);
	   	}
	});
	showList();
	
});

function showList() {
	var initParam = {
		"groupFamily.id": $("#groupFamilyId").val()
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
