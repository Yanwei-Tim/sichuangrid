<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<script type="text/javascript">
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:viewDangerousChemicalsUnit("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
}
function nameFormatter(el,options,rowData){
	return "<a href='javascript:viewDangerousChemicalsUnit("+rowData.id+")'><U><font color='#6633FF'>"+rowData.unitName+"</font></U></a>";
}
function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#dangerousChemicalsUnitList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#dangerousChemicalsUnitList").getRowData(idCollection[i]);
			if(ent.isEmphasis==true || ent.isEmphasis=="true"){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}
//关注程度显示效果
function attentionExtentColor(el,options,rowData){
	var displayName=attentionExtentFormatter(el,options,rowData);
	if(displayName=='undefined'||displayName=='')
		return '';
	else if(displayName=='严重')
		return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
	else if(displayName=='中等')
		return '<span>中等：<span style="color:#ffcc00;">███</span></span>';
	else if(displayName=='一般')
		return '<span>一般：<span style="color:#99cc00;">█</span></span>';
	else
		return '';
}

$(document).ready(function(){
	
	$("#dangerousChemicalsUnitList").jqGridFunction({
		datatype: "local",
		mtype:'post',
		colModel:[
			{name:"id", index:"id", hidden:true},
			{name:"encryptId", index:"id",frozen:true, hidden:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:50,frozen:true,sortable:false,align:'center' },
	    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
			{name:"unitName", index:"unitName",label:"单位名称",formatter:nameFormatter,width:180,sortable:true},
			{name:"unitAddress",index:"unitAddress",label:'单位地址', width:200,sortable:true},
			{name:"superintendent", label:"负责人",index:"superintendent",width:100,sortable:true},
			{name:"unitType",label:"单位类别", index:"unitType", width:100,sortable:true},
			{name:"commodityType",label:"货物类别", width:100,sortable:false,sortable:true},
			
			{name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
			{name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格",hidden:true},
			{name:"telephone",label:"联系电话", width:140,sortable:true,hidden:true},
			{name:"copyScope",label:"副本许可范围", width:100,sortable:true,hidden:true},
			{name:"storageDevice",label:"存储设备", width:100,sortable:true,hidden:true},
			{name:"isEmphasis",sortable:false,label:"是否注销",hidden:true,hidedlg:true,width:100},
			{name:"logOutReason",label:"注销原因", width:100,sortable:true,hidden:true},
			{name:"logOutTime",label:"注销时间", width:100,sortable:false,hidden:true},
			{name:"remark",sortable:false,label:"备注",hidden:true,width:100}
		],
		height:425,
	  	multiselect:true,
	  	loadComplete: afterLoad,
	  	loadComplete:function(data){if(isEmphasisFormatter){isEmphasisFormatter(data);}}
		<pop:JugePermissionTag ename="viewDangerousChemicalsUnit">
	    	,ondblClickRow: viewDangerousChemicalsUnit
		</pop:JugePermissionTag>
	});

	if (getCurrentOrgId() !=null && getCurrentOrgId()!=""){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			searchDangerousChemicalsUnit();
		}else{
			search(getCurrentOrgId());
		}
	}
	function afterLoad(){
		isEmphasisFormatter();
	}

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#dangerousChemicalsUnitList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#dangerousChemicalsUnitList").getRowData(idCollection[i]);
				if(ent.isEmphasis=="true"){
				$("#dangerousChemicalsUnitList tr[id="+idCollection[i]+"]").css('color','#778899');
			}
		}
	}
	function search(orgId){
		var conditions = $("#searchText").val();
		//fateson add	
		var keyValue =	$("#searchByPlaceName").val();
		if(conditions){
			
		}else{
			//是领导试图中查找
			conditions=keyValue;
		}
		if(conditions == '请输入危险化学品单位名称'){
			var	initParam = {
					 "organizationId":orgId,
					 "searchDangerousChemicalsUnitVo.organization.id":orgId,
					 "searchDangerousChemicalsUnitVo.isEmphasis":"false"
			}
		}else{
				var	initParam = {
						 "organizationId":orgId,
						 "searchDangerousChemicalsUnitVo.unitName":conditions,
						 "searchDangerousChemicalsUnitVo.organization.id":orgId,
						 "searchDangerousChemicalsUnitVo.isEmphasis":"false"
				}
			}
		
		$("#dangerousChemicalsUnitList").setGridParam({
			url:"${path}/baseinfo/searchDangerousChemicalsUnit/fastSearch.action",
			datatype: "json",
			page:1
		});
		$("#dangerousChemicalsUnitList").setPostData(initParam);
		$("#dangerousChemicalsUnitList").trigger("reloadGrid");
	}

	function searchDangerousChemicalsUnit(){
		$("#dangerousChemicalsUnitList").setGridParam({
			url:"${path}/baseinfo/searchDangerousChemicalsUnit/findDangerousChemicalsUnitsByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"organizationId":getCurrentOrgId()},$("#searchDangerousChemicalsUnitForm").serializeObject());
		if($("#isLock").val()!=""){
			postData = $.extend(postData,{"searchDangerousChemicalsUnitVo.isEmphasis":$("#isLock").val()});
		}
		$("#dangerousChemicalsUnitList").setPostData(postData);
	    $("#dangerousChemicalsUnitList").trigger("reloadGrid");
	    $("#dangerousChemicalsUnitDialog").dialog('close');
	}
	
});
var dialogWidth=800;
var dialogHeight=640;

function viewDangerousChemicalsUnit(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#dangerousChemicalsUnitList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#viewDangerousChemicalsUnitDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看危险化学品单位信息",
		url:'${path}/baseinfo/dangerousChemicalsUnitManage/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('治安负责人')+'&mode=view&locationType='+$("#_locationType_").val()+'&location.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

</script>
<div style="width: 100%;">
<table id="dangerousChemicalsUnitList">
</table>
<div id="dangerousChemicalsUnitListPager"></div>
</div>
<div id="viewDangerousChemicalsUnitDialog"></div>

<div style="display:none;">
	<span id="title"><s:property value="#request.name"/></span>
	<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@DANGEROUSCHEMICALSUNIT_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@DANGEROUSCHEMICALSUNIT_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@DANGEROUSCHEMICALSUNIT_KEY)" escape="false"/>'/>
</div>

