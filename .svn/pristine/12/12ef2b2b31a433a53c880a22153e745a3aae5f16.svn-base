<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP" />
</script>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=640;

function operatorFormatter(el,options,rowData){
	return "<a href='javascript:doubleClickTable("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
}

var gridOption = {
		colModel:[
			{name:"id",index:'id',hidden:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:50,frozen:true,sortable:false,align:'center' },
	 		{name:"name",index:'name',label:"组织名称",width:100},
	 		{name:"address",index:'address',label:"住所",align:'center',width:150},
	 		{name:"legalPerson",index:'legalPerson',label:"法定代表人",align:'center',sortable:false,width:120},
			{name:"type",index:'type',label:"组织类别",align:'center',formatter:typeFormatter,width:120},
			{name:'subtype',index:'subtype',label:'组织子类别',width:120},
			{name:"validityStartDate",index:'validityStartDate',label:"有效期开始日期",align:'center',sortable:false,width:120},
			{name:"validityEndDate",index:'validityEndDate',label:"有效期结束日期",align:'center',sortable:false,width:120},
			{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
			{name:'organization.orgName',label:"所属区域",align:'center',sortable:false,width:180, hidden:true},
			{name:"legalPersonTelephone",index:'legalPersonTelephone',label:"固定电话",align:'center',sortable:false,width:120,hidden:true},
			{name:"legalPersonMobileNumber",index:'legalPersonMobileNumber',label:"联系手机",align:'center',sortable:false,width:120,hidden:true},
			{name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100},
			{name:"chargeUnit",index:'chargeUnit',label:"业务主管单位",align:'center',sortable:false,width:120,hidden:true},
			{name:"registrationNumber",index:'registrationNumber',label:"登记证号码",align:'center',sortable:false,width:120,hidden:true},
			{name:"personNum",index:'personNum',label:"成员数",align:'center',sortable:false,width:120,hidden:true},
			{name:"partyNum",index:'partyNum',label:"党员人数",align:'center',sortable:false,width:120,hidden:true},
			{name:"introduction",index:'introduction',label:"简 介",align:'center',sortable:false,width:120,hidden:true},
			{name:"honor",index:'honor',label:"获得荣誉",align:'center',sortable:false,width:120,hidden:true},
			{name:"remark",sortable:false,label:"备注",hidden:true,width:100}
		]
	};

function doubleClickTable(selectedId){
	var rowData = $("#newSociety").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	$("#newSocietyDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看${moduleCName}信息",
		url:"${path}/baseinfo/newSocietyOrganizationsManage/dispatchOperate.action?mode=view&location.id="+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
 }

$(function(){
	
	//var name='<s:property value="#parameters.unitName"/>';
	var key='<s:property value="#parameters.key"/>';
	var param={};
	if("请输入组织名称"!=$("#searchText").val()){
		param={
				"organizationId":getCurrentOrgId(),
				"searchNewSocietyOrganizationsVo.organization.id":getCurrentOrgId(),
				"searchNewSocietyOrganizationsVo.unitName":$("#searchText").val()
				};
	}else{
		param={
				"organizationId":getCurrentOrgId(),
				"searchNewSocietyOrganizationsVo.organization.id":getCurrentOrgId()
				};
		}
	
	

	function logOutFormatter(){
		var idCollection = new Array();
		idCollection=$("#newSociety").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#newSociety").getRowData(idCollection[i]);
				if(ent.isEmphasis==true || ent.isEmphasis=="true"){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
		}
	}
	
	 jQuery("#newSociety").jqGridFunction({
			datatype: "local",
		 	colModel:gridOption.colModel,
		 	height:430,
		 	loadComplete: logOutFormatter,
			autowidth:true,
		   	multiselect:true,
		   	altclass:true
		   	<pop:JugePermissionTag ename="viewNewSocietyOrganizations">
			,ondblClickRow: doubleClickTable
			</pop:JugePermissionTag>
		  });
	  jQuery("#newSociety").jqGrid('setFrozenColumns');
	  if(key!=null && key.length>0){
			var data=$("#searchNewSocietyOrganizationsForm").serializeArray();
			var dataJson={};
			for(i=0;i<data.length;i++){
				 if (dataJson[data[i].name]) {
					           dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
				} else {
					            dataJson[data[i].name] = data[i].value;
				}
			}
		   setGridParamData("${path}/baseinfo/searchNewSocietyOrganizations/findNewSocietyOrganizationssByQueryCondition.action");
		   $("#newSociety").setPostData($.extend({"organizationId":getCurrentOrgId()},dataJson)); 
	 	   $("#newSociety").trigger("reloadGrid");
	 	   $("#newSocietystatisticsDialog").dialog("close");
		}else{
			   setGridParamData("${path}/baseinfo/searchNewSocietyOrganizations/fastSearch.action");  
			   $("#newSociety").setPostData(param); 
			   $("#newSociety").trigger("reloadGrid");
		}
});


function  setGridParamData(url){
	  $("#newSociety").setGridParam({
			url:url,
			datatype: "json",
			page:1
		});
}
</script>

<div style="width: 100%">
<table id="newSociety">
</table>
<div id="newSocietyPager"></div>
</div>
<div id="newSocietyDialog"></div>