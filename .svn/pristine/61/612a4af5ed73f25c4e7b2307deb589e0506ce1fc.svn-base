<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />


<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP" />
<pop:formatterProperty name="subType" domainName="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" />
var dialogWidth=810;
var dialogHeight=640;
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='viewNewSocietyOrganizations("+rowData.id+");'><span>查看</span></a> ";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewNewSocietyOrganizations'> onclick='viewNewSocietyOrganizations("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.name+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewNewSocietyOrganizations'> onclick='viewNewSocietyOrganizations("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.name+"</a>";
}
function getLocationName(rowData){
	return $(rowData.name).text();
}

function viewNewSocietyOrganizations(id){
	if(id==null){
 		return;
	}
	var rowData = $("#newSocietyOrganizationsList").getRowData(id);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	$("#newSocietyOrganizationsDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看社会组织信息",
		url:'${path}/baseinfo/newSocietyOrganizationsManage/dispatchOperate.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName=治安负责人&mode=view&locationType='+$("#_locationType_").val()+'&location.id='+id+'&orgId=' + getOrgIdForStat(),
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

$(document).ready(function(){
	jQuery("#newSocietyOrganizationsList").jqGridFunction({
		datatype: "local",
		mtype:'post',
		colModel:[
			{name:"id",index:'id',sortable:false,hidden:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
	 		{name:"name",index:'name',label:"组织名称",sortable:true,formatter:nameFont,width:200},
	 		{name:"address",index:'address',sortable:true,label:"住所",width:200},
	 		{name:"legalPerson",index:'legalPerson',label:"法定代表人",sortable:true,width:120},
			{name:"type",index:'type',label:"组织类别",formatter:typeFormatter,sortable:true,width:120},
			{name:'subType',index:'subType',label:'组织子类别',width:120,sortable:true,formatter:subTypeFormatter},
			{name:"mainResponsibilities",label:"主要职责",width:100},
			{name:"validityStartDate",index:'validityStartDate',label:"有效期开始日期",align:'center',sortable:true,width:120},
			{name:"validityEndDate",index:'validityEndDate',label:"有效期结束日期",align:'center',sortable:true,width:120},
			{name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			{name:'updateDate',sortable:true, label:'数据更新时间',align:'center', width:140},
			{name:'organization.orgName',label:"所属区域",align:'center',sortable:false,width:180, hidden:true},
			{name:"legalPersonTelephone",index:'legalPersonTelephone',label:"固定电话",align:'center',sortable:true,width:120,hidden:true},
			{name:"legalPersonMobileNumber",index:'legalPersonMobileNumber',label:"联系手机",align:'center',sortable:true,width:120,hidden:true},
			{name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100},
			{name:"chargeUnit",index:'chargeUnit',label:"业务主管单位",sortable:true,width:120,hidden:true},
			{name:"registrationNumber",index:'registrationNumber',label:"登记证号码",sortable:true,width:120,hidden:true},
			{name:"personNum",index:'personNum',label:"成员数",sortable:true,width:90,hidden:true},
			{name:"partyNum",index:'partyNum',label:"党员人数",sortable:true,width:90,hidden:true},
			{name:"introduction",index:'introduction',label:"简 介",sortable:true,width:120,hidden:true},
			{name:"honor",index:'honor',label:"获得荣誉",sortable:true,width:120,hidden:true},
			{name:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80},
			{name:"remark",sortable:true,label:"备注",hidden:true,width:100}
		],
		height:425,
	  	multiselect:true,
	    loadComplete: afterLoad,
		ondblClickRow: viewNewSocietyOrganizations
	});
	if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
		onOrgChangedForStat(getOrgIdForStat(),isGrid());
	}
});
	
	function onOrgChangedForStat(orgId,isgrid){
		var initParam = {
				"organizationId": orgId,
		    	"location.isEmphasis":false
		}

		$("#newSocietyOrganizationsList").setGridParam({
			url:"${path}/baseinfo/newSocietyOrganizationsManage/newSocietyOrganizationsList.action",
			datatype: "json",
			page:1
		});
		$("#newSocietyOrganizationsList").setPostData(initParam);
		$("#newSocietyOrganizationsList").trigger("reloadGrid");
	}
	
	function afterLoad(){
		isEmphasisFormatter();
	}
	
	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#newSocietyOrganizationsList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#newSocietyOrganizationsList").getRowData(idCollection[i]);
				if(ent.isEmphasis=="true"){
				$("#newSocietyOrganizationsList tr[id="+idCollection[i]+"]").css('color','#778899');
			}
		}
	}
	
	function getOrgIdForStat(){
		var orgIdForStat = $("#orgIdForStat").val();
		if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
			return getCurrentOrgId();
		}else{
			return orgIdForStat;
		}
	}

</script>
<div class="content">
	<div style="width: 100%;">
	    <table id="newSocietyOrganizationsList" ></table>
	    <div id="newSocietyOrganizationsListPager"></div>
	</div>
	<div id="newSocietyOrganizationsDialog"></div>
</div>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@NEWSOCIETYORGANIZATIONS_KEY"/>'/>
<input type="hidden" id="_supervisorName_" value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@ACTUALCOMPANY_KEY)" escape="false"/>'/>
<input type="hidden" id="_superviceRecordName_" value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@ACTUALCOMPANY_KEY)" escape="false"/>'/>
