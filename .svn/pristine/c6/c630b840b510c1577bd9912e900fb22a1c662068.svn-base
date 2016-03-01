<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<script type="text/javascript">
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
var dialogWidth=800;
var dialogHeight=600;

function placeAddressColorFormatter(el,options,rowData){
if(rowData.placeAddress !=null && rowData.placeAddress !="null"){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<font color='#778899'>"+rowData.placeAddress+"</font>";
	}
	return "<font color='#000'>"+rowData.placeAddress+"</font>";
}else{
	return "";
}
}
function getLocationName(rowData){
	return rowData.placeName;
}
function viewInternetBar(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#internetBarList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#viewInternetBarDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看网吧信息",
		url:'${path}/baseinfo/internetBarManage/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('治安负责人')+'&mode=view&locationType='+$("#_locationType_").val()+'&location.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:viewInternetBar("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
}
function nameFormatter(el,options,rowData){
	return "<a href='javascript:viewInternetBar("+rowData.id+")'><U><font color='#6633FF'>"+rowData.placeName+"</font></U></a>";
}
function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#internetBarList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#internetBarList").getRowData(idCollection[i]);
			if(ent.isEmphasis==true || ent.isEmphasis=="true"){
				$("#internetBarList tr[id="+idCollection[i]+"]").css('color','#778899');
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
	$("#internetBarList").jqGridFunction({
		mtype:'post',
		datatype:'local',
		colModel:[
          {name:"id", index:"id",hidden:true, frozen :true},
          {name:"encryptId", index:"id",hidden:true, frozen :true},
      	  {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:50,frozen:true,sortable:false,align:'center' },
      	  {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
          {name:"placeName",index:"placeName",label:"单位名称",formatter:nameFormatter,width:180,sortable:false,},
          {name:"placeAddress",index:"placeAddress",label:"单位地址",width:200,sortable:false,formatter:placeAddressColorFormatter},
          {name:"manager",index:"manager",label:"负责人",sortable:false,width:100,hidden:false},
          {name:"stationPolice",index:"stationPolice",label:"所在地派出所名称",hidden:true,sortable:false,width:150},
          {name:"totalComputerNum",index:"totalComputerNum",label:"电脑台数",sortable:false,width:100},
          
          {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
		  {name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
		  {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},		
          {name:'updateDate', sortable:false, label:'数据更新时间', width:160,align:"center"},
          {name:"mobile",index:"mobile",label:"联系方式",width:120,sortable:false,hidden:true},
          {name:"netCultureLicenceNo",index:"netCultureLicenceNo",label:"网络文化经营许可证号",sortable:false,width:150,hidden:true},
          {name:"netSecurityAuditNo",index:"netSecurityAuditNo",label:"网络安全审核意见书号",sortable:false,width:150,hidden:true},
          {name:"fireAuditDocumentNo",index:"fireAuditDocumentNo",label:"消防审核意见书号",sortable:false,width:150,hidden:true},
          {name:"netAccessProviders",index:"netAccessProviders",label:"宽带接入服务商",sortable:false,width:100,hidden:true},
          {name:"internetAccessType",index:"internetAccessType",label:"接入方式",sortable:false,width:100,hidden:true},
          {name:"useIP", index:"useIP", width:200,label:"现使用IP",hidden:true},
          {name:"barCode", index:"barCode", width:100,label:"网吧编号",hidden:true},
          {name:"operationArea",index:"operationArea",label:"营业面积(<font size='2'>m</font><font size='1'><sup>2</sup></font>)",sortable:false,width:100,hidden:true},
          {name:"tempNetCardNum",index:"tempNetCardNum",label:"临时上网卡数量",sortable:false,width:100,hidden:true},
          {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:false,width:100,hidden:true},
          {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:false,width:100,hidden:true},
          {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
          {name:"remark",index:"remark",label:"备注",sortable:false,width:100,hidden:true}  
		],
		height:425,
		multiselect:true,
		loadComplete:isEmphasisFormatter
		<pop:JugePermissionTag ename="viewInternetBar">
        	,ondblClickRow: viewInternetBar
		</pop:JugePermissionTag>
		});

	if (getCurrentOrgId() !=null && getCurrentOrgId()!=""){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			searchInternetBar();
		}else{
			search(getCurrentOrgId());
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
		if(conditions == '请输入网吧名称'){
			var	initParam = {
					 "organizationId":orgId,
					 "searchInternetBarVo.unitName":conditions,
					 "searchInternetBarVo.organization.id":orgId,
					 "searchInternetBarVo.isEmphasis":0
			}
		}else{
			var	initParam = {
					 "organizationId":orgId,
					 "searchInternetBarVo.unitName":conditions,
					 "searchInternetBarVo.organization.id":orgId,
					 "searchInternetBarVo.isEmphasis":0
			}
		}
		
		$("#internetBarList").setGridParam({
			url:"${path}/baseinfo/searchInternetBar/fastSearch.action",
			datatype: "json",
			page:1
		});
		$("#internetBarList").setPostData(initParam);
		$("#internetBarList").trigger("reloadGrid");
	}

	function searchInternetBar(){
		$("#internetBarList").setGridParam({
			url:"${path}/baseinfo/searchInternetBar/findInternetBarsByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"organizationId":getCurrentOrgId()},$("#searchInternetBarForm").serializeObject());
		if($("#isLock").val()!=""){
			postData = $.extend(postData,{"searchInternetBarVo.isEmphasis":$("#isLock").val()});
		}
		$("#internetBarList").setPostData(postData);
	    $("#internetBarList").trigger("reloadGrid");
	    $("#internetBarDialog").dialog('close');
	}


	
});

</script>

<div style="width: 100%;">
<table id="internetBarList">
</table>
<div id="internetBarListPager"></div>
</div>
<div id="viewInternetBarDialog"></div>

<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@INTERNETBAR_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@INTERNETBAR_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@INTERNETBAR_KEY)" escape="false"/>'/>
</div>
