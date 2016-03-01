<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<div class="content">
<div class="ui-corner-all" id="nav">
	<pop:JugePermissionTag ename="addSpecialCareGroups">
		<a id="add" href="javascript:;"><span>新增</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="updateSpecialCareGroups">
		<a id="update" href="javascript:void(0)"><span>修改</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="viewSpecialCareGroups">
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteSpecialCareGroups">
		<a id="delete" href="javascript:void(0)"><span>删除</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="getSpecialCareGroups">
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
	</pop:JugePermissionTag>
	<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	<pop:JugePermissionTag ename="importSpecialCareGroups">
		<a id="import" href="javascript:void(0)"><span>导入</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="downloadSpecialCareGroups">
		<a id="export" href="javascript:void(0)"><span>导出</span></a>
  	</pop:JugePermissionTag>
</div>
</div>


<div style="width:100%;" >
	<table id="specialCareGroupsList"> </table>
	<div id="specialCareGroupsListPager"></div>
</div>
<div id="specialCareGroupsDialog"></div>

<div style="display: none;"><pop:JugePermissionTag
	ename="specialCareGroupsManagement">
	<span id="title"><s:property value="#request.name" /></span>
</pop:JugePermissionTag></div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="specialCareType" domainName="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE" />
<pop:formatterProperty name="labourCapacity" domainName="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" />
<pop:formatterProperty name="viability" domainName="@com.tianque.domain.property.PropertyTypes@VIABILITY" />
<pop:formatterProperty name="employmentStatus" domainName="@com.tianque.domain.property.PropertyTypes@EMPLOYMENT_STATUS" />
<pop:formatterProperty name="supportStatus" domainName="@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS" />

var title=$("#title").html();
var dialogWidth=800;
var dialogHeight=450;
var isgridBol = false;
function onOrgChanged(orgId,isgrid){
	org=orgId;
	if (isgrid){
		$("#add").buttonEnable();
	}else{
		$("#add").buttonDisable();
	}
	isgridBol = isgrid;
	$("#specialCareGroupsList").setGridParam({
		url:'${path}/baseinfo/specialCareGroupsManage/findSpecialCareGroups.action',
		datatype: "json",
		page:1
	});
	$("#specialCareGroupsList").setPostData({
    	"orgId":org
   	});
	$("#specialCareGroupsList").trigger("reloadGrid");
}

$(document).ready(function(){
	$("#view").click(function(event){
		var selectedId = $("#specialCareGroupsList").getGridParam("selrow");
	});
	$("#specialCareGroupsList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"id", index:"id", hidden:true},
	        {name:"name", index:'name',   label:'姓名', width:80 },
	        {name:'idCardNo', sortable:false, label:'身份证号码', width:160},
	        {name:'gender', sortable:false, formatter:genderFormatter, label:'性别', align:'center', width:50},
	        {name:'birthday', sortable:false, label:'出生日期', width:90},
	        {name:'currentAddress', sortable:false, label:'常住地址', width:60},
	        {name:'telephone', sortable:false, label:'联系电话', width:120},
	        {name:'mobileNumber', sortable:false, label:'联系手机', width:100},

	        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:200},

	        {name:'specialCareNo', sortable:false, label:'优抚证号', width:60},
	        {name:'specialCareType', sortable:false, formatter:specialCareTypeFormatter, label:'优抚类型', width:60},
	        {name:'labourCapacity', sortable:false, formatter:labourCapacityFormatter, label:'劳动能力', width:60},
	        {name:'viability', sortable:false, formatter:viabilityFormatter, label:'生活能力', width:60},
	        {name:'employmentStatus', sortable:false, formatter:employmentStatusFormatter, label:'就业情况', width:60},
	        {name:'supportStatus', sortable:false, formatter:supportStatusFormatter, label:'供养情况', width:60},

	        {name:'monthsExpenses', sortable:false, label:'月生活费', width:160},
	        {name:'remark', sortable:false, label:'备注', width:160, hidden:true}
		],
		ondblClickRow: viewSpecialCareGroupsInfo,
		loadComplete: afterLoad,
		onSelectRow: selectRow
	});

	$("#view").click(function(event){
		var selectedId = $("#specialCareGroupsList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		viewSpecialCareGroupsInfo(selectedId);
	});

	$("#add").click(function(event){
			if(!isgridBol){
				return false;
			}
			$("#specialCareGroupsDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:'新增优抚对象信息',
				url:'${path}/baseinfo/specialCareGroupsManage/dispatch.action?mode=add&orgId='+getCurrentOrgId(),
				buttons: {
					"保存并关闭" : function(event){
			   			$("#maintainForm").submit();
			   			$("#isSubmit").val("true");
		   			},
		   			"保存并继续" : function(event){
			   			$("#maintainForm").submit();
			   			$("#isSubmit").val("false");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
	});
	$("#update").click(function(){
		var selectedId = $("#specialCareGroupsList").getGridParam("selrow");
		if(selectedId == null){
			return;
		}
		$("#specialCareGroupsDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'修改'+title+'信息',
			modal:true,
			url:'${path}/baseinfo/specialCareGroupsManage/dispatch.action?mode=edit&specialCareGroups.id='+selectedId+"&orgId="+getCurrentOrgId(),
			buttons: {
				"保存" : function(event){
	   			$("#maintainForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#search").click(function(event){
		$("#specialCareGroupsDialog").createDialog({
			width: dialogWidth,
			height: 300,
			title: '查询'+title+'信息',
			url: '${path}/baseinfo/specialCareGroups/searchSpecialCareGroupsCondition.jsp',
			buttons: {
				"查询" : function(event){
				searchSpecialCareGroups();
				$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#delete").click(function(){
		var specialCareGroups = $("#specialCareGroupsList").getGridParam("selrow");
		if(specialCareGroups == null){
			return;
		}
		$.confirm({
				title:"确认删除",
				message:"确定要删除吗?一经删除将无法恢复",
				okFunc: function(){
					$.ajax({
						url:"${path}/baseinfo/specialCareGroupsManage/deleteSpecialCareGroups.action?specialCareGroupsIds="+specialCareGroups,
						success:function(data){
							$("#specialCareGroupsList").delRowData(specialCareGroups);
							$("#specialCareGroupsList").trigger("reloadGrid");
						    $.messageBox({message:"已经成功删除该"+title+"信息!"});
						    disableButtons();
						    checkExport();
					    }
				    });
			   }
		 });
	});
	$("#import").click(function(event){
		$("#specialCareGroupsDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?dataType=specialCareGroups&dialog=specialCareGroupsDialog&startRow=6&templates=<s:propertyvalue="@com.tianque.datatransfer.ImportTemplatesSetting@SPECIALCAREGROUPS_KEY"/>',
			buttons:{
					"导入" : function(event){
				      $("#mForm").submit();
				   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			},
			shouldEmptyHtml:false
		});
		$("#specialCareGroupsList").trigger("reloadGrid");
	});
	$("#export").click(function(event){
		if ($("#specialCareGroupsList").getGridParam("records")>0){
			$("#specialCareGroupsDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出'+title+'信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'specialCareGroupsList',
					downloadUrl:'${path}/baseinfo/searchSpecialCareGroups/downloadSpecialCareGroups.action'
				},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
		}
	});
	if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	$("#reload").click(function(event){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
});
function afterLoad(){
	disableButtons();
	checkExport();
}
function viewSpecialCareGroupsInfo(rowid){
	if(rowid==null){
 		return;
	}
	var specialCareGroups =  $("#specialCareGroupsList").getRowData(rowid);
	$("#specialCareGroupsDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看'+title+'信息',
		modal:true,
		url:'${path}/baseinfo/specialCareGroupsManage/dispatch.action?mode=view&specialCareGroups.id='+specialCareGroups.id,
		buttons: {
			  "打印" : function(){
			  	print(rowid);
	   		},
		   	  "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function print(rowid){
	if(rowid==null){
 		return;
	}
	var specialCareGroups =  $("#specialCareGroupsList").getRowData(rowid);
	$("#specialCareGroupsDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'打印'+title+'信息',
		url:'${path}/baseinfo/specialCareGroupsManage/dispatch.action?mode=print&specialCareGroups.id='+specialCareGroups.id,
		buttons: {
			  "确定" : function(){
				$("#specialCareGroupsPrint").printArea();
	        	$(this).dialog("close");
	   		},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function disableButtons(){
	$("#update").buttonDisable();
	$("#delete").buttonDisable();
	$("#view").buttonDisable();
}
function checkExport(){
	if($("#specialCareGroupsList").getGridParam("records")>0 || $("#specialCareGroupsList").getGridParam("selrow")!=null){
		$("#export").buttonEnable();
	}else{
		$("#export").buttonDisable();

	}
}
function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("specialCareGroupsList");
	var count = $("#specialCareGroupsList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("specialCareGroupsList", true);
	}else{
		jqGridMultiSelectState("specialCareGroupsList", false);
	}
		$("#view").buttonEnable();
		$("#update").buttonEnable();
		$("#delete").buttonEnable();

}

function getSelectedIds(){
	var selectedIds = $("#specialCareGroupsList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
function searchSpecialCareGroups() {
	var conditionName = $("#conditionName").val();
	var conditionIdCardNo = $("#conditionIdCardNo").val();
	var conditionGenderId = $("#conditionGenderId").val();
	var conditionBirthdayStart = $("#conditionBirthdayStart").val();
	var conditionBirthdayEnd = $("#conditionBirthdayEnd").val();
	var conditionCurrentAddress = $("#conditionCurrentAddress").val();
	var conditionTelephone = $("#conditionTelephone").val();
	var conditionMobileNumber = $("#conditionMobileNumber").val();

	var conditionSpecialCareNo = $("#conditionSpecialCareNo").val();
	var conditionSpecialCareTypeId = $("#conditionSpecialCareTypeId").val();
	var conditionLabourCapacityId = $("#conditionLabourCapacityId").val();
	var conditionViabilityId = $("#conditionViabilityId").val();
	var conditionEmploymentStatusId = $("#conditionEmploymentStatusId").val();
	var conditionSupportStatusId = $("#conditionSupportStatusId").val();

	var conditionMinMonthsExpenses = $("#conditionMinMonthsExpenses").val();
	var conditionMaxMonthsExpenses = $("#conditionMaxMonthsExpenses").val();
	var conditionRemark = $("#conditionRemark").val();


	$("#specialCareGroupsList").setGridParam({
		url: '${path}/baseinfo/searchSpecialCareGroups/searchSpecialCareGroups.action',
		datatype: "json",
		page:1
	});
	$("#specialCareGroupsList").setPostData({
		"organizationId":getCurrentOrgId(),
		"searchSpecialCareGroupsCondition.name":conditionName,
		"searchSpecialCareGroupsCondition.idCardNo":conditionIdCardNo,
		"searchSpecialCareGroupsCondition.genderId":conditionGenderId,
		"searchSpecialCareGroupsCondition.birthdayStart":conditionBirthdayStart,
		"searchSpecialCareGroupsCondition.birthdayEnd":conditionBirthdayEnd,
		"searchSpecialCareGroupsCondition.currentAddress":conditionCurrentAddress,
		"searchSpecialCareGroupsCondition.telephone":conditionTelephone,
		"searchSpecialCareGroupsCondition.mobileNumber":conditionMobileNumber,
		"searchSpecialCareGroupsCondition.specialCareNo":conditionSpecialCareNo,
		"searchSpecialCareGroupsCondition.specialCareTypeId":conditionSpecialCareTypeId,
		"searchSpecialCareGroupsCondition.labourCapacityId":conditionLabourCapacityId,
		"searchSpecialCareGroupsCondition.viabilityId":conditionViabilityId,
		"searchSpecialCareGroupsCondition.employmentStatusId":conditionEmploymentStatusId,
		"searchSpecialCareGroupsCondition.supportStatusId":conditionSupportStatusId,

		"searchSpecialCareGroupsCondition.minMonthsExpenses":conditionMinMonthsExpenses,
		"searchSpecialCareGroupsCondition.maxMonthsExpenses":conditionMaxMonthsExpenses
	});
	$("#specialCareGroupsList").trigger("reloadGrid");
}
</script>