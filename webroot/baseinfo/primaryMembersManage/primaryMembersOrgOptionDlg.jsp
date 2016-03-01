<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#textContent a.search{display:inline-block; *display:inline; *zoom:1; cursor:pointer; background:url(/resource/system/images/button-bg.png) left bottom no-repeat; padding-left:15px; height:24px; line-height:24px; margin:0 5px 10px 0; position:relative; color:#333;}
#textContent a.search span{display:inline-block; *display:inline; *zoom:1; background:url(/resource/system/images/button-bg.png) right bottom no-repeat; padding-right:15px; vertical-align:top; *vertical-align:middle;}
#textContent a.search:hover{background-position:left top; color:#fff;}
#textContent a.search:hover span{background-position:right top;}
.newGrid_16 {
	height: 20px;
	line-height: 20px;
}
</style>
<div id="primaryMembersOrgOption" class="container container_24">
	<input type="hidden" id="internalId" name="internalId" value="">
	<input type="hidden" id="optionOrgOperateIds" name="optionOrgOperateIds" value="${primaryMembers.primaryOrgIds}">
	<input type="hidden" id="optionOrgOperateNames" name="optionOrgOperateNames" value="${primaryMembers.primaryOrgNames}">
	<form action="" method="post" id="primaryMembersOrgOptionForm">
		<div class="grid_8">
			<label class="form-lb1">组织类型</label>
		</div>
		<div class="grid_16">
			<label class="form-lb1">组织选择</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8">
 			<select name="orgTeamClazz" id="orgTeamClazz" class='form-txt'  style="width: 96.5%;height: 280px"  multiple="multiple">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" defaultValue="" />
			</select>
		</div>
		<div class="grid_16 newGrid_16">	
			<table id="primaryMembersOrgOptionList"></table>
			<div id="primaryMembersOrgOptionListPager" style="height:28px;"></div>
		</div>
	</form>
	<div id="primaryMembersOrgOptionDialog"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="teamType2" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />
<pop:formatterProperty name="teamType3" domainName="@com.tianque.domain.property.PropertyTypes@MASSEDUTY_TYPE" />
<pop:formatterProperty name="teamType4" domainName="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY_TYPE" />
<pop:formatterProperty name="teamType5" domainName="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE" />
<pop:formatterProperty name="teamType8" domainName="@com.tianque.domain.property.PropertyTypes@BASICLEVELPARTY_TYPE" />
<pop:formatterProperty name="teamType9" domainName="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTY_TYPE" />
<pop:formatterProperty name="teamType10" domainName="@com.tianque.domain.property.PropertyTypes@GOVERNMENTDEPARTMENT_TYPE" />
<pop:formatterProperty name="teamType11" domainName="@com.tianque.domain.property.PropertyTypes@MASSORGANIZATION_TYPE" />
<pop:formatterProperty name="teamType0" domainName="@com.tianque.domain.property.PropertyTypes@PERMARY_ORGANIZATION_TYPE" />

var teamTypeDomainName2  = '<s:property value="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" escape="false"/>';
var teamTypeDomainName3  = '<s:property value="@com.tianque.domain.property.PropertyTypes@MASSEDUTY_TYPE" escape="false"/>';
var teamTypeDomainName4  = '<s:property value="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY_TYPE" escape="false"/>';
var teamTypeDomainName5  = '<s:property value="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE" escape="false"/>';
var teamTypeDomainName8  = '<s:property value="@com.tianque.domain.property.PropertyTypes@BASICLEVELPARTY_TYPE" escape="false"/>';
var teamTypeDomainName9  = '<s:property value="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTY_TYPE" escape="false"/>';
var teamTypeDomainName10  = '<s:property value="@com.tianque.domain.property.PropertyTypes@GOVERNMENTDEPARTMENT_TYPE" escape="false"/>';
var teamTypeDomainName11  = '<s:property value="@com.tianque.domain.property.PropertyTypes@MASSORGANIZATION_TYPE" escape="false"/>';
var teamTypeDomainName0  = '<s:property value="@com.tianque.domain.property.PropertyTypes@PERMARY_ORGANIZATION_TYPE" escape="false"/>';
var orgTeamClazz=new Array("基层党委","部门党委","政府部门","群团组织","基层自治组织","群防群治队伍","社会志愿者队伍")
$("#primaryMembersOrgOptionList").jqGridFunction({
	mtype:'post',
	datatype: "local",
	height:240,
	colModel:[
		{name:"id",index:"id",key:true,sortable:true,hidden:true},
		{name:'primaryOrgType',index:'primaryOrgType',label:'组织类型',formatter:massedutyTteamTypeFormatter,width:120,align:"center"},
		{name:'primaryOrgName',index:'primaryOrgName',label:'组织名称',width:260,align:"center"},
		{name:'isFourLevelPlatform',index:'isFourLevelPlatform',sortable:true,hidden:true},
		{name:'isSynchronize',index:'isSynchronize',sortable:true,hidden:true}
	],
	multiselect:true,
	showColModelButton:false,
	sortname:'id',
	onSelectRow:function(id){
		orgOperate(id);
	},
	gridComplete:function(){//列表默认选中的行
		var orgTeamClazz = $("#orgTeamClazz").val();
	 	if($("#optionOrgOperateIds").val()!=null && $("#optionOrgOperateIds").val()!=""){
	 		var selectIds = $("#optionOrgOperateIds").val();
	 		if(selectIds.indexOf(",")==-1){
	 			selectId = selectIds.split("-");
	 			if(orgTeamClazz=="fourLevelPlatform" && selectId[0]=="1"){
	 				jQuery("#primaryMembersOrgOptionList").jqGrid('setSelection',selectId[1],true);
	 			}else if(orgTeamClazz!="fourLevelPlatform" && selectId[0]=="0"){
	 				jQuery("#primaryMembersOrgOptionList").jqGrid('setSelection',selectId[1],true);
	 			}
	 			
	 		}else{
	 			selectIds = selectIds.split(",");
	 			for(var i=0;i<selectIds.length;i++){
	 				selectId = selectIds[i].split("-");
		 			if(orgTeamClazz=="fourLevelPlatform" && selectId[0]=="1"){
		 				jQuery("#primaryMembersOrgOptionList").jqGrid('setSelection',selectId[1],true);
		 			}else if(orgTeamClazz!="fourLevelPlatform" && selectId[0]=="0"){
		 				jQuery("#primaryMembersOrgOptionList").jqGrid('setSelection',selectId[1],true);
		 			}
		 		}
	 		}	
	 	}
	}
});
//$("#primaryMembersOrgOptionListPager_center .ui-pg-table").css({"margin-top":"-4px","margin-left":"-44px"});
//$("#primaryMembersOrgOptionListPager_right .ui-paging-info").css({"margin-top":"-14px","margin-left":"-66px"});
$("#primaryMembersOrgOptionListPager_right .ui-paging-info").css({"margin-left":"-66px"});
function loadTeamClassData() {
	var teamTypeDomainName = eval("teamTypeDomainName"+$("#internalId").val());
	var orgTeamClazz = $("#orgTeamClazz").val();
	$("#primaryMembersOrgOptionList").setGridParam({
		url:'${path}/primaryOrg/primaryMembersManage/findPrimaryOrganizations.action?orgId='+$("#orgId").val()+'&displayLevel='+$("#displayLevel").val()+'&internalId='+$("#internalId").val()+'&orgTeamClazz='+orgTeamClazz+'&teamTypeDomainName='+teamTypeDomainName,
		datatype: "json",
		page:1
	});
	$("#primaryMembersOrgOptionList").trigger("reloadGrid");
}
$("#orgTeamClazz option").each(function(){
	var flg = 0;
	for (i=0;i<orgTeamClazz.length;i++){
		if(orgTeamClazz[i]==$(this).text()){
			flg = 1;
		}
	}
	if(flg==0){
		$(this).remove();
	}
});
$("#orgTeamClazz").append("<option value='fourLevelPlatform'>四级体系建设</option>");

$("#orgTeamClazz").change(function(){
	if($("#orgTeamClazz").val().length>1){
		$.messageBox({level:'error',message:"请选择一个组织类型"});
		return;
	}
	var name = $("#orgTeamClazz").find("option:selected").text();
		if(name=='基层自治组织'){
			$("#internalId").val("2");
		}else if(name=='群防群治队伍'){
			$("#internalId").val("3");
		}else if(name=='社会志愿者队伍'){
			$("#internalId").val("4");
		}else if(name=='专项工作领导小组'){
			$("#internalId").val("5");
		}else if(name=='基层党委'){
			$("#internalId").val("8");
		}else if(name=='部门党委'){
			$("#internalId").val("9");
		}else if(name=='政府部门'){
			$("#internalId").val("10");
		}else if(name=='群团组织'){
			$("#internalId").val("11");
		}else if(name=='综治组织'){
			$("#internalId").val("0");
		}
		if($("#internalId").val()==2 ){
			if(!isCountryDownOrganization()){
				$.messageBox({level:"warn",message:"请先选择村社区及以下层级进行组织选择！"});
				return;
			}
		}else if($("#internalId").val()==3 || $("#internalId").val()==4){
			if(!($("#currentOrgId").attr("orgLevelInternalId")<=<s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>)){
				$.messageBox({level:"warn",message:"请先选择市县及以下层级进行组织选择！"});
				return;
			}
		}
		if($("#orgTeamClazz").val()=="fourLevelPlatform"){
			$("#internalId").val("-1");
			if(!($("#currentOrgId").attr("orgLevelInternalId")<=<s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>)){
				$.messageBox({level:"warn",message:"请先选择县(区)及以下层级进行新增！"});
				return;
			}
		}
	loadTeamClassData();
}); 
function massedutyTteamTypeFormatter(el, options, rowData){
	if(rowData.isFourLevelPlatform==1){
		return rowData.primaryOrgType;
	}else if($("#orgTeamClazz").find("option:selected").text()=='群防群治队伍' && rowData.isSynchronize>0){
		return	"<span>平安志愿者</span>";
	}else{
		return teamTypeFormatter(el, options, rowData);
	}
}
function teamTypeFormatter(el,options,rowData){
	var teamTypeData = "teamType" + $("#internalId").val()+"Data";
	if(rowData.primaryOrgType){
		return this[teamTypeData][rowData.primaryOrgType];
	}else{
			return "&nbsp;"
	}
}
$("#cb_primaryMembersOrgOptionList").click(function(){
	var rowIds = jQuery("#primaryMembersOrgOptionList").jqGrid('getDataIDs');
	for(var i=0;i<rowIds.length;i++){
		orgOperate(rowIds[i]);
	}
});

function orgOperate(rowId){
	var rowData = $("#primaryMembersOrgOptionList").jqGrid("getRowData",rowId);
	var optionOrgOperateIds = $("#optionOrgOperateIds").val();
	var optionOrgOperateNames= $("#optionOrgOperateNames").val();
	var optionOrgIsFourLevelIds = rowData.isFourLevelPlatform +"-"+ rowData.id;
	var primaryOrgNameForView = rowData.primaryOrgName;
	var orgTeamClazzSelected = $("#orgTeamClazz").find("option:selected").text();
	if((orgTeamClazzSelected=='群防群治队伍'||orgTeamClazzSelected=='社会志愿者队伍')&&rowData.isSynchronize>0){
		primaryOrgNameForView= primaryOrgNameForView+"(同步群防群治)";
	}
	
	if($("#jqg_primaryMembersOrgOptionList_"+rowData.id).attr("checked")=="checked"){
		if(optionOrgOperateIds==null || optionOrgOperateIds==""){
			$("#optionOrgOperateIds").val(optionOrgIsFourLevelIds);
			$("#optionOrgOperateNames").val(primaryOrgNameForView)
		}else if(optionOrgOperateIds.indexOf(",")==-1){
			if(optionOrgOperateIds!=optionOrgIsFourLevelIds){
				optionOrgOperateIds = optionOrgOperateIds+","+optionOrgIsFourLevelIds;
				optionOrgOperateNames = optionOrgOperateNames+","+primaryOrgNameForView;
				$("#optionOrgOperateIds").val(optionOrgOperateIds);
				$("#optionOrgOperateNames").val(optionOrgOperateNames);
			}
		}else{
			var flg = 0;
			optionOrgOperateIds = optionOrgOperateIds.split(",");
			for(var i=0;i<optionOrgOperateIds.length;i++){
	 			if(optionOrgOperateIds[i]==optionOrgIsFourLevelIds){
	 				flg = 1;
	 			}
	 		}
	 		if(flg==0){
	 			optionOrgOperateIds = optionOrgOperateIds+","+optionOrgIsFourLevelIds;
	 			optionOrgOperateNames = optionOrgOperateNames+","+primaryOrgNameForView;
				$("#optionOrgOperateIds").val(optionOrgOperateIds);
				$("#optionOrgOperateNames").val(optionOrgOperateNames);
	 		}
		}		
	}else{
		if(optionOrgOperateIds.indexOf(",")==-1){
			if(optionOrgOperateIds==optionOrgIsFourLevelIds){
				$("#optionOrgOperateIds").val("");
				$("#optionOrgOperateNames").val("");
			}
		}else{
			optionOrgOperateIds = optionOrgOperateIds.split(",");
			optionOrgOperateNames = optionOrgOperateNames.split(",");
			for(var i=0;i<optionOrgOperateIds.length;i++){
				if(i==0 && optionOrgOperateIds[i]==optionOrgIsFourLevelIds){
					var tempOptionIds = optionOrgOperateIds[1];
					var tempteamClazz = optionOrgOperateNames[1];
					for(var j=2;j<optionOrgOperateIds.length;j++){
						tempOptionIds = tempOptionIds+","+optionOrgOperateIds[j];
						tempteamClazz = tempteamClazz+","+optionOrgOperateNames[j];
					}
					$("#optionOrgOperateIds").val(tempOptionIds);
					$("#optionOrgOperateNames").val(tempteamClazz);
				}else if(i==optionOrgOperateIds.length-1 && optionOrgOperateIds[i]==optionOrgIsFourLevelIds){
					var tempOptionIds = optionOrgOperateIds[0];
					var tempteamClazz = optionOrgOperateNames[0];
					for(var j=1;j<optionOrgOperateIds.length-1;j++){
						tempOptionIds = tempOptionIds+","+optionOrgOperateIds[j];
						tempteamClazz = tempteamClazz+","+optionOrgOperateNames[j];
					}
					$("#optionOrgOperateIds").val(tempOptionIds);
					$("#optionOrgOperateNames").val(tempteamClazz);
	 			}else if(optionOrgOperateIds[i]==optionOrgIsFourLevelIds){
	 				var tempOptionIds = optionOrgOperateIds[0];
					var tempteamClazz = optionOrgOperateNames[0];
					for(var j=1;j<i;j++){
						tempOptionIds = tempOptionIds+","+optionOrgOperateIds[j];
						tempteamClazz = tempteamClazz+","+optionOrgOperateNames[j];
					}
					for(var j=i+1;j<optionOrgOperateIds.length;j++){
						tempOptionIds = tempOptionIds+","+optionOrgOperateIds[j];
						tempteamClazz = tempteamClazz+","+optionOrgOperateNames[j];
					}
					$("#optionOrgOperateIds").val(tempOptionIds);
					$("#optionOrgOperateNames").val(tempteamClazz);
	 			}
	 		}
		}		
	}
	
}
</script>