<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<div class="content">
<!--	<div class="ui-corner-all" id="nav">-->
<!--   		<pop:JugePermissionTag ename="addOverseaPerson">-->
<!--			<a id="add" href="javascript:;"><span>新增</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--   		<pop:JugePermissionTag ename="updateOverseaPerson">-->
<!--			<a id="update" href="javascript:void(0)"><span>修改</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--   		<pop:JugePermissionTag ename="viewOverseaPerson">-->
<!--			<a id="view" href="javascript:void(0)"><span>查看</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--   		<pop:JugePermissionTag ename="deleteOverseaPerson">-->
<!--			<a id="delete" href="javascript:void(0)"><span>删除</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--   		<pop:JugePermissionTag ename="searchOverseaPerson">-->
<!--			<a id="search"  href="javascript:void(0)"><span>查询</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--		<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>-->
<!--   		<pop:JugePermissionTag ename="importOverseaPerson">-->
<!--			<a id="import" href="javascript:void(0)"><span>导入</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--   		<pop:JugePermissionTag ename="downOverseaPerson">-->
<!--			<a id="export" href="javascript:void(0)"><span>导出</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--   		<pop:JugePermissionTag ename="shiftOverseaPerson">-->
<!--			<a id="shift" href="javascript:void(0)"><span>转移</span></a>-->
<!--		</pop:JugePermissionTag>-->
<!--   		<pop:JugePermissionTag ename="abolishOverseaPerson">-->
<!--   			<a id="logOut" href="javascript:void(0)"><span>取消关注</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--   		<pop:JugePermissionTag ename="anewOverseaPerson">-->
<!--   			<a id="cancelLogOut" href="javascript:void(0)"><span>重新关注</span></a>-->
<!--   		</pop:JugePermissionTag>-->
<!--   		<div style="float: right;white-space:nowrap;">-->
<!--				<select id="isLock" name="user.lock">-->
<!--						<option value="-1">全部</option>-->
<!--	 					<option value="0" selected="selected">现在关注</option>-->
<!--	 					<option value="1">曾经关注</option>-->
<!--				</select>-->
<!--			</div>-->
<!--	</div>-->
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="overseaPersonnelList"> </table>
		<div id="overseaPersonnelListPager"></div>
	</div>
	<div id="overseaPersonnelMaintanceDialog"></div>
<div id="helpPrecordDialog"></div>
</div>

<div style="display: none;">
	<span id="title">境外人员</span>
</div>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>

<script type="text/javascript">
var dialogWidth=810;
var dialogHeight=500;
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="certificateType" domainName="@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE" />
<pop:formatterProperty name="profession" domainName="@com.tianque.domain.property.PropertyTypes@PROFESSION_TYPE" />

var isgridBol = false;
var title=$("#title").html();


$(document).ready(function(){
	
	function operateFormatter(el, options, rowData){
		return "<a href='javascript:viewDialog("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
	}

	$("#overseaPersonnelList").jqGridFunction({
		datatype: "local",
		colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
	        {name:"操作",index:'id',width:50,formatter:operateFormatter,sortable:false,frozen:true},
	        {name:"englishName",index:'englishName',label:'英文名',width:80,formatter:nameFont,frozen:true},
	        {name:"gender",label:'性别',width:50,align:'center',formatter : genderFormatter },
	        {name:'certificateType',label:'证件种类',width:100,formatter : certificateTypeFormatter},
	        {name:'certificateNo',label:'证件号码',width:160,formatter:certificateNoFont,frozen:true},
	        {name:'name',label:'姓名',width:100,hidden:true},
	        {name:'currentAddress',label:'常住地址',width:150},
	        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:330,hidden:true},
	        {name:"nationality",label:'国籍',width:80},
	        {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	        {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
	        {name:'birthday', sortable:false, label:'出生日期', width:90,hidden:true},
	   		{name:'telephone',label:'固定电话',sortable:false, width:120,hidden:true},
	   		{name:'mobileNumber',label:'联系手机',index:"mobileNumber",sortable:false, width:100,hidden:true},
      		{name:'workUnit',label:'工作单位或就读学校',sortable:false, width:160,hidden:true},
      		{name:'arrivalTime',label:'抵达时间',sortable:false, width:160,hidden:true},
      		{name:'leaveTime',label:'离开时间',sortable:false, width:160,hidden:true},
      		{name:'profession',label:'职业',sortable:false, width:160,hidden:true,align:'center',formatter : professionFormatter},
	        {name:"logOut",sortable:false,hidden:true,hidedlg:true,width:100}
		],
		width:860,
	  	height:430,
	  	multiselect:true,
	    loadComplete: isEmphasisFormatter,

		<pop:JugePermissionTag ename="viewOverseaPerson">
		ondblClickRow: viewDialog,
		</pop:JugePermissionTag>
	    onSelectRow:selectRow
	});
	if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			searchOverseaPersonnel();
		}else{
			fastSearchfun(getOrgIdForStat());
		}
	}
});
function getOrgIdForStat(){
	var orgIdForStat = $("#orgIdForStat").val();
	if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
		return getCurrentOrgId();
	}else{
		return orgIdForStat;
	}
}

function searchOverseaPersonnel(){
	$("#overseaPersonnelList").setGridParam({
		url:"${path}/baseinfo/overseaPersonnelSearch/searchOverseaPersonnel.action",
		datatype: "json",
		page:1,
		mtype:"post"
	});
	$("#overseaPersonnelList").setPostData($.extend({"searchOverseaPersonnelVo.logOut":$("#isLock").val(),"orgId":getCurrentOrgId()},$("#searchOverseaPersonnelForm").serializeObject()));
    $("#overseaPersonnelList").trigger("reloadGrid");
    $("#OVERSEAPERSONNELstatisticsDialog").dialog("close");
}




function viewDialog(id){
	var overseaPersonnel =  $("#overseaPersonnelList").getRowData(id);
	$("#viewOverseaPersonnelMaintanceDialog").createDialog({
		width: dialogWidth,
		height: 450,
		title:'查看'+title+'信息',
		url:'${path}/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+overseaPersonnel.encryptId,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}


function getSelectedIds(){
	var selectedIds = $("#overseaPersonnelList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#overseaPersonnelList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function certificateNoFont(el,options,rowData){
	if(rowData.logOut==1){
		return "<font color='#778899'>"+rowData.certificateNo+"</font>";
	}
	return "<font color='#000'>"+rowData.certificateNo+"</font>";
}
function nameFont(el,options,rowData){
	if(rowData.death == true || rowData.death == "true"){
		return "<a href='javascript:viewDialog("+rowData.id+")'><font color='red'>"+rowData.englishName+"</font></a>";
	}
	if(rowData.logOut==1||rowData.logOut=='1'){
		return "<a href='javascript:viewDialog("+rowData.id+")'><font color='#778899'>"+rowData.englishName+"</font></a>";
	}
	return "<a href='javascript:viewDialog("+rowData.id+")'><font color='#6633FF'>"+rowData.englishName+"</font></a>";
}

function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#overseaPersonnelList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#overseaPersonnelList").getRowData(idCollection[i]);
			if(ent.logOut==1 || ent.logOut=='1'){
			$("#overseaPersonnelList tr[id="+idCollection[i]+"]").css('color','#778899');
		}
	}
}
function viewOverseaPersonnel(){
	var selectedId = $("#overseaPersonnelList").getGridParam("selrow");
	if(selectedId==null){
 		return;
	}
	$("#overseaPersonnelMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看'+title+'信息',
		url:'${path}/baseinfo/overseaPersonnelManage/dispatchOperate.action?mode=view&overseaPersonnel.id='+selectedId,
		buttons: {
			"打印" : function(){
        	print();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function print(){
	var selectedId = $("#overseaPersonnelList").getGridParam("selrow");
	if(selectedId==null){
 		return;
	}
	$("#overseaPersonnelMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight+20,
		title:'打印'+title+'信息',
		url:'${path}/baseinfo/overseaPersonnelManage/dispatchOperate.action?mode=print&overseaPersonnel.id='+selectedId,
		buttons: {
			"确定" : function(){
			$("#overseaPersonnelPrint").printArea();
        	$(this).dialog("close");
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}



function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("overseaPersonnelList");
	var count = $("#overseaPersonnelList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("overseaPersonnelList", true);
	}else{
		jqGridMultiSelectState("overseaPersonnelList", false);
	}
	if(selectedCounts==1){
		$("#view").buttonEnable();
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
		var selectedId = $("#overseaPersonnelList").getGridParam("selrow");
		var druggy =  $("#overseaPersonnelList").getRowData(selectedId);
		if(druggy.logOut!=1){
			$("#logOut").buttonEnable();
			$("#cancelLogOut").buttonDisable();
		}else{
			$("#cancelLogOut").buttonEnable();
			$("#logOut").buttonDisable();
		}
	}else{
		$("#view").buttonDisable();
		$("#update").buttonDisable();
		$("#cancelLogOut").buttonDisable();
		$("#logOut").buttonDisable();
	}
	if(isGrid()){
		$("#shift").buttonEnable();
	}else{
		$("#shift").buttonDisable();
	}
	if(selectedCounts==0){
		$("#delete").buttonDisable();
		$("#shift").buttonDisable();
	}
}
function getIdCardNo(){
	var idCardNo = "";
	$("#conditionIdCardNo").length > 0?idCardNo = $("#conditionIdCardNo").val():idCardNo = $("#searchByIdcard").val();
	return idCardNo;
}

function getSelectedIds(){
	var selectedIds = $("#overseaPersonnelList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}


function fastSearchfun(orgId){
	var condition = $("#searchByCondition").val();
	if(condition == '请输入英文名或证件号码') {
		var initParam = {
				"orgId": orgId,
				"searchOverseaPersonnelVo.logOut":0,
				"searchOverseaPersonnelVo.fastSearchKeyWords":""
		}
	}else{
		var initParam = {
				"orgId": orgId,
				"searchOverseaPersonnelVo.logOut":0,
				"searchOverseaPersonnelVo.fastSearchKeyWords":condition
		}
	}
	$("#overseaPersonnelList").setGridParam({
		url:'${path}/baseinfo/overseaPersonnelSearch/fastSearchOverseaPersonnel.action',
		datatype: "json",
		page:1
	});
	$("#overseaPersonnelList").setPostData(initParam);
	$("#overseaPersonnelList").trigger("reloadGrid");
}
</script>