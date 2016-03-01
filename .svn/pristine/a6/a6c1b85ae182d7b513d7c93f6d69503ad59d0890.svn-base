<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="cloakroom" domainName="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
var dialogWidth=800;
var dialogHeight=600;

function getLocationName(rowData){
	return rowData.placeName;
}

function viewPublicPlace(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#publicPlaceList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#viewPublicPlaceDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看公共场所信息",
		url:'${path}/baseinfo/publicPlaceManage/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('治安负责人')+'&mode=view&locationType='+$("#_locationType_").val()+'&location.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:viewPublicPlace("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
}
function nameFormatter(el,options,rowData){
	return "<a href='javascript:viewPublicPlace("+rowData.id+")'><U><font color='#6633FF'>"+rowData.placeName+"</font></U></a>";
}
function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#publicPlaceList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#publicPlaceList").getRowData(idCollection[i]);
			if(ent.isEmphasis==true || ent.isEmphasis=="true"){
				$("#publicPlaceList tr[id="+idCollection[i]+"]").css('color','#778899');
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
	$("#publicPlaceList").jqGridFunction({
		mtype:'post',
		datatype:'local',	
		colModel:[
          {name:"id", index:"id",hidden:true, frozen :true},
          {name:"encryptId", index:"id",hidden:true, frozen :true},
      	  {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
      	  {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
          {name:"placeName",index:"placeName",label:"场所名称",formatter:nameFormatter,width:180,sortable:false},
          {name:"placeAddress",index:"placeAddress",label:"场所地址",width:200,sortable:false},
          {name:"manager",index:"manager",label:"负责人",sortable:false,width:100,hidden:false},
          {name:"category",index:"category",label:"公共场所类别",sortable:false,width:100},
          {name:"registrationNumber",index:"registrationNumber",label:"备案登记号码",sortable:false,width:100,hidden:true},
          {name:"openingDate",index:"licenseDate",label:"开业时间",sortable:false,width:100},
          
          {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
          {name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
          {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
          {name:'updateDate', sortable:false, label:'数据更新时间', width:160,align:"center"},
          {name:"licenseDate",index:"licenseDate",label:"领取执照时间",width:100,sortable:false,hidden:true},
          {name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格",hidden:true},
          {name:"placeLevel",index:"placeLevel",label:"场所等级",sortable:false,width:100,hidden:true},
          {name:"buildingStructure",index:"buildingStructure",label:"建筑物结构",sortable:false,width:100,hidden:true},
          {name:"totalArea",index:"totalArea",label:"总面积(<font size='2'>m</font><font size='1'><sup>2</sup></font>)",sortable:false,width:100,hidden:true},
          {name:"operationArea",index:"operationArea",label:"营业面积(<font size='2'>m</font><font size='1'><sup>2</sup></font>)",sortable:false,width:100,hidden:true},
          {name:"cloakroom",index:"cloakroom",formatter:cloakroomFormatter,label:"小件寄存处",sortable:false,width:100,hidden:true},
          {name:"vouchedPeopleCount",index:"vouchedPeopleCount",label:"核定人数(人)",sortable:false,width:100,hidden:true},
          {name:"privateRoomCount",index:"privateRoomCount",label:"包间数(间)",sortable:false,width:100,hidden:true},
          {name:"surrounding",index:"surrounding",label:"周围环境",sortable:false,width:100,hidden:true},
          {name:"passageway",index:"passageway",label:"通道口",sortable:false,width:100,hidden:true},
          {name:"innerStructure",index:"innerStructure",label:"内部结构",sortable:false,width:100,hidden:true},
          {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:false,width:100,hidden:true},
          {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:false,width:100,hidden:true},
          {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100,hidden:true},
          {name:"remark",index:"remark",label:"备注",sortable:false,width:100,hidden:true}  
		],
		height:425,
		multiselect:true,
		loadComplete:isEmphasisFormatter
		<pop:JugePermissionTag ename="viewPublicPlace">
        	,ondblClickRow: viewPublicPlace
		</pop:JugePermissionTag>
	});

	if (getCurrentOrgId() !=null && getCurrentOrgId()!=""){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			searchPublicPlace();
		}else{
			search(getCurrentOrgId());
		}
	}

	function searchPublicPlace(){
		$("#publicPlaceList").setGridParam({
			url:"${path}/baseinfo/searchPublicPlace/findPublicPlacesByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"organizationId":getCurrentOrgId()},$("#searchPublicPlaceForm").serializeObject());
		if($("#isLock").val()!=""){
			postData = $.extend(postData,{"searchPublicPlaceVo.isEmphasis":$("#isLock").val()});
		}
		$("#publicPlaceList").setPostData(postData);
	    $("#publicPlaceList").trigger("reloadGrid");
	    $("#publicPlaceDialog").dialog("close"); 
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
					 "searchPublicPlaceVo.organization.id":orgId,
					 "searchPublicPlaceVo.isEmphasis":0
					 
			}
		}else{
				var	initParam = {
						 "organizationId":orgId,
						 "searchPublicPlaceVo.unitName":conditions,
						 "searchPublicPlaceVo.organization.id":orgId,
						 "searchPublicPlaceVo.isEmphasis":0
						 
				}
			}
		
		$("#publicPlaceList").setGridParam({
			url:"${path}/baseinfo/searchPublicPlace/fastSearch.action",
			datatype: "json",
			page:1
		});
		$("#publicPlaceList").setPostData(initParam);
		$("#publicPlaceList").trigger("reloadGrid");
	}

});

</script>

<div style="width: 100%;">
<table id="publicPlaceList">
</table>
<div id="publicPlaceListPager"></div>
</div>
<div id="viewPublicPlaceDialog"></div>

<div style="display:none;">
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@PUBLICPLACE_KEY)" escape="false"/>'/>
</div>

