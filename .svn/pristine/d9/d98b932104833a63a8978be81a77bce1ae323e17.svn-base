<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="jqsubgrid" style="position:relative;overflow:hidden;zoom:1;height:auto;">
	<div class='clearLine'></div>
	<div style="width: 100%;">
		<table id="partyOrgMemberList"></table>
	</div>
	<div id="populationDialog"></div>
	<input type="hidden" id="partyOrgId" value="${partyOrgId} "/>
	<input type="hidden" id="baseUrl" value="${path} "/>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
$(document).ready(function(){
	$("#partyOrgMemberList").jqGridFunction({
		url:'${path}/baseinfo/partyOrgInfoManage/findPartyOrgMemberInfos.action',
		postData:{
			"partyOrgId":function(){return $("#partyOrgId").val();}
			},
		datatype: "local",
		height:200,
   		colModel:[
        	{name:"id",index:"id",hidden:true,frozen:true},
  			{name:" ",label:"",width:30,frozen:true,formatter:viewPartMemberDetail},
        	{name:'name',index:"姓名",label:"姓名",width:120,frozen:true},
        	{name:'gender',index:"gender",label:"性别",formatter:genderFormatter,width:80,frozen:true},
        	{name:"idCardNo",label:"身份证号码",formatter:idCardNoFont,width:180,frozen:true},
        	{name:"currentAddress",label:"常住地址",width:180,frozen:true},
        	{name:"province",label:"户籍地",width:180,frozen:true},
        	{name:"joinPartyDate",index:"joinPartyDate",label:"入党日期",width:180,frozen:true}
		]
	});

  	jQuery("#partyOrgMemberList").jqGrid('setFrozenColumns');
	$("#partyOrgMemberList").setGridParam({
		datatype: "json",
		page:1
	});
	$("#partyOrgMemberList").trigger("reloadGrid");

});

function viewPartMemberDetail(el,option,rowData){
	return "<a href='javascript:void(0)' onclick=doubleClickTable("+rowData.id+")><span><strong class='ui-ico-cakan'></strong>查看</span></a>";
}

function doubleClickTable(selectedId){
	var partyMemberInfo=$("#partyOrgMemberList").getRowData(selectedId);
	$("#populationDialog").createDialog({
		width:dialogWidth,
		height:580,
		title:"查看党员信息",
		url:"${path}/baseinfo/partyMemberInfoManage/dispatchOperate.action?mode=view&population.id="+selectedId,
		buttons: {
		   "关闭" : function() {
		        $(this).dialog("close");
		   }
		}
	});
}

</script>