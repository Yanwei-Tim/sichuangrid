<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@PUBLICCOMPLEXPLACES_TYPE" />
<pop:formatterProperty name="detailedType" domainName="@com.tianque.domain.property.PropertyTypes@PUBLICCOMPLEXPLACES_DETAILEDTYPE" />
<pop:formatterProperty name="cloakroom" domainName="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
var dialogWidth=800;
var dialogHeight=600;

function getLocationName(rowData){
	return rowData.placeName;
}

function viewPublicComplexPlaces(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#publicComplexPlacesList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#viewPublicComplexPlacesDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看公共复杂场所信息",
		url:'${path}/baseinfo/publicComplexPlacesManage/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('治安负责人')+'&mode=view&locationType='+$("#_locationType_").val()+'&location.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:viewPublicComplexPlaces("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPublicComplexPlaces'> onclick='viewPublicComplexPlaces("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.placeName+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPublicComplexPlaces'> onclick='viewPublicComplexPlaces("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.placeName+"</a>";
}
function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#publicComplexPlacesList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#publicComplexPlacesList").getRowData(idCollection[i]);
			if(ent.isEmphasis==true || ent.isEmphasis=="true"){
				$("#publicComplexPlacesList tr[id="+idCollection[i]+"]").css('color','#778899');
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
function placeAddressColorFormatter(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<font color='#778899'>"+rowData.placeAddress+"</font>";
	}
	return "<font color='#000'>"+rowData.placeAddress+"</font>";
}
$(document).ready(function(){
	$("#publicComplexPlacesList").jqGridFunction({
		mtype:'post',
		datatype:'local',	
		colModel:[
          {name:"id", index:"id",sortable:false,hidden:true, frozen :true},
          {name:"encryptId", index:"id",sortable:false,hidden:true, frozen :true},
	      	  {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
	      	  {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
	          {name:"placeName-Font",index:"placeName",label:"场所名称",width:200,sortable:true,frozen:true,formatter:nameFont},
	          {name:"placeName",index:"placeName",label:"场所名称",width:100,sortable:true,frozen:true,hidden:true},
	          {name:"placeAddress",index:"placeAddress",label:"场所地址",width:200,sortable:true,frozen:true,formatter:placeAddressColorFormatter},
	          {name:"manager",index:"manager",label:"负责人",sortable:true,width:100,hidden:false},
	          {name:"type",index:"type",label:"公共复杂场所类型",sortable:true,width:100,formatter:typeFormatter},
	          {name:"detailedType",index:"detailedType",label:"公共复杂场所细类",sortable:true,width:100,formatter:detailedTypeFormatter},
	          {name:"managerMobile",index:"managerMobile",label:"负责人手机",sortable:true,width:100,hidden:true},
	          {name:"managerTelephone",index:"managerTelephone",label:"负责人电话",sortable:true,width:100,align:'center'},
	          
	          {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			  {name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			  {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	          {name:'updateDate', sortable:true, label:'数据更新时间', width:140},
	          {name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格",sortable:false,hidden:true},
	          {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:true,width:100,hidden:true},
	          {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:true,width:100,hidden:true,align:'center'},
	          {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100,hidden:true},
	          {name:"hiddenCases",index:"hiddenCases",label:"存在隐患",sortable:true,width:200,hidden:true},
	          {name:"hiddenRectification",index:"hiddenRectification",label:"隐患整改情况",sortable:true,width:200,hidden:true},
	          {name:"remark",index:"remark",label:"备注",sortable:true,width:200,hidden:true}
		],
		height:425,
		multiselect:true,
		loadComplete:isEmphasisFormatter
		<pop:JugePermissionTag ename="viewPublicComplexPlaces">
        	,ondblClickRow: viewPublicComplexPlaces
		</pop:JugePermissionTag>
	});

	if (getCurrentOrgId() !=null && getCurrentOrgId()!=""){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			searchPublicComplexPlaces();
		}else{
			search(getCurrentOrgId());
		}
	}

	function searchPublicComplexPlaces(){
		$("#publicComplexPlacesList").setGridParam({
			url:"${path}/baseinfo/searchPublicComplexPlaces/findPublicComplexPlacessByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"organizationId":getCurrentOrgId()},$("#searchPublicComplexPlacesForm").serializeObject());
		if($("#isLock").val()!=""){
			postData = $.extend(postData,{"searchPublicComplexPlacesVo.isEmphasis":$("#isLock").val()});
		}
		$("#publicComplexPlacesList").setPostData(postData);
	    $("#publicComplexPlacesList").trigger("reloadGrid");
	    $("#publicComplexPlacesDialog").dialog("close"); 
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
		if(conditions == '请输入公共场所名称'){
			var	initParam = {
					 "organizationId":orgId,
					 "searchPublicComplexPlacesVo.organization.id":orgId,
					 "searchPublicComplexPlacesVo.isEmphasis":0
					 
			}
		}else{
				var	initParam = {
						 "organizationId":orgId,
						 "searchPublicComplexPlacesVo.unitName":conditions,
						 "searchPublicComplexPlacesVo.organization.id":orgId,
						 "searchPublicComplexPlacesVo.isEmphasis":0
						 
				}
			}
		
		$("#publicComplexPlacesList").setGridParam({
			url:"${path}/baseinfo/searchPublicComplexPlaces/fastSearch.action",
			datatype: "json",
			page:1
		});
		$("#publicComplexPlacesList").setPostData(initParam);
		$("#publicComplexPlacesList").trigger("reloadGrid");
	}

});

</script>

<div style="width: 100%;">
<table id="publicComplexPlacesList">
</table>
<div id="publicComplexPlacesListPager"></div>
</div>
<div id="viewPublicComplexPlacesDialog"></div>

<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY)" escape="false"/>'/>
</div>

