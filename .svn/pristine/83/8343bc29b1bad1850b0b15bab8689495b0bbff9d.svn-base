<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>

<script type="text/javascript">


$(function(){
	var initParam={
			"organizationId":getCurrentOrgId(),
			 "searchNewEconomicOrganizationsVo.isEmphasis":0
			};
	if('请输入组织名称或营业执照号码'!=$("#searchText").val()){
		initParam={
				 "organizationId":getCurrentOrgId(),
				 "searchNewEconomicOrganizationsVo.fastSearch":$("#searchText").val(),
				 "searchNewEconomicOrganizationsVo.isEmphasis":0
				};
	}
	var key='<s:property value="#parameters.key"/>';
	<pop:formatterProperty name="style" domainName="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE" />

	function operateFormatter_newEc(el, options, rowData){
		return "<a href='javascript:doubleClickTable("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
	}

	function logOutFormatter(){
		var idCollection = new Array();
		idCollection=$("#newEconomic").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#newEconomic").getRowData(idCollection[i]);
				if(ent.isEmphasis==1||ent.isEmphasis=='1'){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
		}
	}
	
	function licenseNumberFont(el,options,rowData){
		if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
			return "<font color='#778899'>"+rowData.licenseNumber+"</font>";
		}else{
			return "<font color='#000'>"+rowData.licenseNumber+"</font>";
		}
	}

	function nameFont(el,options,rowData){
		if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
			return "<font color='#778899'>"+rowData.name+"</font>";
		}else{
			return "<font color'#000'>"+rowData.name+"</font>";
		}
	}
	 jQuery("#newEconomic").jqGridFunction({
			datatype: "local",
			colModel:[
		        {name:"id", index:"id", hidden:true,frozen:true},
		        {name:"encryptId",index:'id',frozen:true,hidden:true},
		    	{name:"operator", index:'id',label:'操作',formatter:operateFormatter_newEc,width:50,frozen:true,sortable:false,align:'center' },
		        {name:"name", sortable:true, label:'名称',formatter:nameFont,width:80,frozen:true },
		        {name:"residence", sortable:false, label:'住所', width:80 },
		        {name:'chief',  sortable:true, label:'负责人', width:100},
		        {name:'style',formatter:styleFormatter, sortable:false, label:'类别', width:100},
		        {name:'licenseNumber',sortable:true, label:'营业执照号码', formatter:licenseNumberFont, width:130,frozen:true},
		        {name:'validityStartDate',sortable:true, label:'有效期开始日期', width:100},
		        {name:'validityEndDate', sortable:true, label:'有效期结束日期', width:100},
		        {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
				{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
		        {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
		        {name:'organization.orgName',sortable:false,index:'organization.orgName',label:'所属网格',width:350,hidden:true},
		        {name:'area',  sortable:true, label:'面积(<font size="2">m</font><font size="1"><sup>2</sup></font>)',width:100,hidden:true},
		        {name:'employeeNumber', sortable:true, label:'从业人数', width:100,hidden:true},
		        {name:'partyMemberNumber', sortable:true, label:'党员人数', width:100,hidden:true},
		        {name:'foxNumber', sortable:false, label:'传真', width:100,hidden:true},
		        {name:'telephone', sortable:true, label:'固定电话', width:100,hidden:true},
		        {name:'mobileNumber', sortable:true, label:'联系手机', width:100,hidden:true},
		        {name:'isEmphasis',sortable:false,hidden:true,hidedlg:true,width:80}
		       
			],
		 	height:430,	
			autowidth:true,
		   	multiselect:true,
		   	altclass:true,
		   	loadComplete: logOutFormatter,
		 	<pop:JugePermissionTag ename="viewNewEconomicOrganizations">
		   	ondblClickRow: doubleClickTable,
			</pop:JugePermissionTag>
		  });
     jQuery("#newEconomic").jqGrid('setFrozenColumns');
    
     if(key!=null && key.length>0){
    		var data=$("#searchNewEconomicOrganizations").serializeArray();
			var dataJson={};
			for(i=0;i<data.length;i++){
				 if (dataJson[data[i].name]) {
					           dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
				} else {
					            dataJson[data[i].name] = data[i].value;
				}
			}
		 setGridParamData("${path}/baseinfo/newEconomicOrganizationsManage/searchNewEconomicOrganizations.action");
		 $("#newEconomic").setPostData($.extend({"organizationId":getCurrentOrgId()},dataJson)); 
	 	$("#newEconomic").trigger("reloadGrid");
	 	$("#newEconomicstatisticsDialog").dialog("close");
     }else{
    	  setGridParamData("${path}/baseinfo/newEconomicOrganizationsManage/searchNewEconomicOrganizations.action");
    	  $("#newEconomic").setPostData(initParam);
  	 	$("#newEconomic").trigger("reloadGrid");
     }
});

function  setGridParamData(url){
	  $("#newEconomic").setGridParam({
			url:url,
			datatype: "json",
			page:1
		});
}

function doubleClickTable(selectedId){
	var rowData = $("#newEconomic").getRowData(selectedId);
	var encryptId = rowData.encryptId;
	if(encryptId==null){
		 return;
	}
	$("#newEconomicDialog").createDialog({
		width:800,
		height:600,
		title:"查看${moduleCName}信息",
		url:"${path}/baseinfo/newEconomicOrganizationsManage/dispathByEncrypt.action?mode=view&newEconomicOrganizations.id="+encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
 }
</script>
<div style="width: 100%">
<table id="newEconomic">
</table>
<div id="newEconomicPager"></div>
</div>
<div id="newEconomicDialog"></div>