<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
		<s:select name="quickSearchType" list="@com.tianque.search.vo.QuickSearchType@DEFAULT_PERSON_QUICKSEARCH_TYPE" listKey="code" />
		<input type="text" value="" name="quickSearchCondition" id="quickSearchCondition" maxlength="18" class="searchtxt" />
	</div>
	<a href="javascript:;" id="quickSearch"><span>检索</span></a>
</div>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addPrisonReleasedPersonal">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updatePrisonReleasedPersonal">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewPrisonReleasedPersonal">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePrisonReleasedPersonal">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchPrisonReleasedPersonal">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="importPrisonReleasedPersonal">
			<a id="import" href="javascript:void(0)"><span>导入</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="exportPrisonReleasedPersonal">
			<a id="export" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		
 		<pop:JugePermissionTag ename="transferPrisonReleasedPersonal"> 
			<a id="transfer" href="javascript:void(0)"><span>转移</span></a> 
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="cancelIntrestingPrisonReleasedPersonal">
	   		<a id="cancelInt" href="javascript:void(0)"><span>取消关注</span></a>
   		</pop:JugePermissionTag>
   		<pop:JugePermissionTag ename="reIntrestingPrisonReleasedPersonal">
   			<a id="emphasis" href="javascript:void(0)"><span>重新关注</span></a>
		</pop:JugePermissionTag>
		<div style="float: right;white-space:nowrap;">
			<s:select name="quickFilterType" list="@com.tianque.search.vo.QuickFilterType@DEFAULT_IMPORTANTPERSON_FILTER_TYPE" listKey="code" />
		</div>
	</div>
	<div style="width: 100%;">
		<table id="prisonReleasedPersonalList"></table>
		<div id="prisonReleasedPersonalListPager"></div>
	</div>
	<div id="prisonReleasedPersonalDialog"></div>
</div>

<div style="display: none;">
	<pop:JugePermissionTag	ename="prisonReleasedPersonalManagement">
		<span id="title"><s:property value="#request.name" /></span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		jQuery("#prisonReleasedPersonalList").jqGridFunction({
			datatype: "local",
		   	colModel:[
			   	       {name:"id",index:"id",hidden:true,frozen:true},
			   	       {name:"idCardNo",index:'idCardNo',width:100,frozen:true,frozen:true},
				       {name:"name",index:'name', width:100,frozen:true},
				       {name:'idCardNo',index:'idCardNo', width:150,sortable:false,frozen:true},
				       {name:'gender',sortable:false,formatter : genderFormatter,width:40},
				       {name:'currentlyAddress',index:'currentlyAddress',sortable:false,width:200},
				       {name:'organization.orgName',index:'orgInternalCode',width:250,sortable:false},
				       {name:"province", formatter:householdRegisterFormatter,width:200},
				       {name:'imprisonmentDate',index:'imprisonmentDate',width:80},
				       {name:'isRepeat',index:'isRepeat',formatter : rendIsRepeat,width:80},
				       {name:'releaseDate',index:'releaseDate',width:150},
				       {name:'resettlementDate',index:'resettlementDate',hidden:true},
				       {name:'noResettlementReason',index:'noResettlementReason',sortable:false,hidden:true},
				       {name:'schooling',sortable:false,formatter : schoolingFormatter,hidden:true},
				       {name:'imprisonmentDate',index:'imprisonmentDate',sortable:false,hidden:true},
				       {name:'caseReason',index:'caseReason',sortable:false,hidden:true},
				       {name:'laborEduAddress',index:'laborEduAddress',sortable:false,hidden:true},
				       {name:'noResettlementReason',index:'noResettlementReason',sortable:false,hidden:true},
				       {name:'agoProfession',sortable:false,formatter : agoProfessionFormatter,hidden:true},
				       {name:'nativePlaceAddress',sortable:false,index:'nativePlaceAddress',hidden:true},
				       {name:'nativePoliceStation',sortable:false,index:'nativePoliceStation',hidden:true},
				       {name:"positiveInfosType",index:'positiveInfosType',hidden:true,formatter:positiveInfosTypeFormatter},
				       {name:"telephone",index:'telephone',hidden:true},
				       {name:"mobileNumber",index:'mobileNumber',hidden:true},
				       {name:"birthday",index:'birthday', hidden:true},
				       {name:"backDate",index:'backDate', hidden:true},
				       {name:"isExpired",index:'isExpired',hidden:true,hidedlg:true},
				       {name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100}
			   	],
		});
	});
	
</script>
<!-- 
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="agoProfession" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="positiveInfosType" domainName="@com.tianque.domain.property.PropertyTypes@POSITIVEINFO" />
$("#update").buttonDisable();
$("#delete").buttonDisable();
$("#view").buttonDisable();
var org=getCurrentOrgId();
var isgridBol = false;
var title=$("#title").html();
function rendIsRepeat(el, options, rowData){
	if(1==rowData.isRepeat){
			return "是";
		}else{
			return "否";
		}
	}
function search(orgId){
	var condition = $("#searchByCondition").val();
	$("#positiveInfoList").setGridParam({
		url:'${path}/baseinfo/searchPositiveInfos/searchFastPositiveInfo.action',
		datatype: "json",
		page:1
	});
	if("idCard"==$("#conditionMark").val()){
		$("#positiveInfoList").setPostData({
	    	"orgId":orgId,
	    	"searchPositiveInfosVo.isEmphasis":$("#isLock").val(),
	    	"searchPositiveInfosVo.idCardNo":condition
	   	});
	}else{
		$("#positiveInfoList").setPostData({
	    	"orgId":orgId,
	    	"searchPositiveInfosVo.isEmphasis":$("#isLock").val(),
	    	"searchPositiveInfosVo.name":condition
	   	});
	}
	
	$("#positiveInfoList").trigger("reloadGrid");
}

function onOrgChanged(orgId,isgrid){
	org=orgId;
	if (isgrid){
		$("#add").buttonEnable();
	}else{
		$("#add").buttonDisable();
	}
	isgridBol = isgrid;
	$("#positiveInfoList").setGridParam({
		url:'${path}/baseinfo/positiveInfo/findPositiveInfoByOrgId.action',
		datatype: "json",
		page:1
	});
	$("#positiveInfoList").setPostData({
    	"orgId":org,
    	"positiveInfo.isEmphasis":$("#isLock").val()
   	});
	$("#positiveInfoList").trigger("reloadGrid");
}

function householdRegisterFormatter(el, options, rowData){
	var str = "";
	if(rowData.province != null)
		str += rowData.province;
	if(rowData.city != null)
		str += rowData.city
	if(rowData.district != null)
		str += rowData.district;
	return str;
}

var dialogWidth=800;
var dialogHeight=600;

$(document).ready(function(){
		jQuery("#positiveInfoList").jqGridFunction({
			datatype: "local",
		 	colNames:['id','操作','姓名','身份证号码', '性别','常住地址','所属网格','户籍地','原判刑期','是否累犯',
		 			 	'刑释日期','安置日期','未安置原因','文化程度','原判刑期','案由','劳教(服刑)场所',
		 			 	'未安置原因','原职业','户籍地详址','户籍派出所','刑释解教类型','联系电话','联系手机','出生日期','解教日期','是否过期','是否关注'],
		   	colModel:[
		   	       {name:"id",index:"id",hidden:true,frozen:true},
		   	       {name:"idCardNo",index:'idCardNo',width:100,frozen:true,frozen:true},
			       {name:"name",index:'name', width:100,frozen:true},
			       {name:'idCardNo',index:'idCardNo', width:150,sortable:false,frozen:true},
			       {name:'gender',sortable:false,formatter : genderFormatter,width:40},
			       {name:'currentlyAddress',index:'currentlyAddress',sortable:false,width:200},
			       {name:'organization.orgName',index:'orgInternalCode',width:250,sortable:false},
			       {name:"province", formatter:householdRegisterFormatter,width:200},
			       {name:'imprisonmentDate',index:'imprisonmentDate',width:80},
			       {name:'isRepeat',index:'isRepeat',formatter : rendIsRepeat,width:80},
			       {name:'releaseDate',index:'releaseDate',width:150},
			       {name:'resettlementDate',index:'resettlementDate',hidden:true},
			       {name:'noResettlementReason',index:'noResettlementReason',sortable:false,hidden:true},
			       {name:'schooling',sortable:false,formatter : schoolingFormatter,hidden:true},
			       {name:'imprisonmentDate',index:'imprisonmentDate',sortable:false,hidden:true},
			       {name:'caseReason',index:'caseReason',sortable:false,hidden:true},
			       {name:'laborEduAddress',index:'laborEduAddress',sortable:false,hidden:true},
			       {name:'noResettlementReason',index:'noResettlementReason',sortable:false,hidden:true},
			       {name:'agoProfession',sortable:false,formatter : agoProfessionFormatter,hidden:true},
			       {name:'nativePlaceAddress',sortable:false,index:'nativePlaceAddress',hidden:true},
			       {name:'nativePoliceStation',sortable:false,index:'nativePoliceStation',hidden:true},
			       {name:"positiveInfosType",index:'positiveInfosType',hidden:true,formatter:positiveInfosTypeFormatter},
			       {name:"telephone",index:'telephone',hidden:true},
			       {name:"mobileNumber",index:'mobileNumber',hidden:true},
			       {name:"birthday",index:'birthday', hidden:true},
			       {name:"backDate",index:'backDate', hidden:true},
			       {name:"isExpired",index:'isExpired',hidden:true,hidedlg:true},
			       {name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100}
		   	],
		   	multiselect:true,
		   	onSelectAll:function(aRowids,status){ if(status) {
		   		var selectedIds = $("#positiveInfoList").jqGrid("getGridParam", "selarrrow");
		   		if(selectedIds.length ==2){
				   		$("#view").buttonEnable();
						$("#update").buttonEnable();
					}
		   		if(selectedIds.length != 0){
				   	$("#delete").buttonEnable();
				   	if(isGrid()){
						$("#shift").buttonEnable();
				   	}
			   	}
		   		if(selectedIds.length >2){
	   				$("#view").buttonDisable();
					$("#update").buttonDisable();
					$("#emphasis").buttonDisable();
					$("#isEmphasis").buttonDisable();
	   			}

		   	}else{$("#delete").buttonDisable();
		   	$("#shift").buttonDisable();
		   	$("#view").buttonDisable();
			$("#update").buttonDisable();
			$("#emphasis").buttonDisable();
			$("#isEmphasis").buttonDisable();
		   	}},
		   	altclass:true,
		   	loadComplete: afterLoad,
		   	<pop:JugePermissionTag ename="viewPositiveInfo">
		    ondblClickRow: doubleClickTable,
		    </pop:JugePermissionTag>
		    onSelectRow:selectRow
			});
		if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}

	$("#search").click(function(){
		$("#positiveInfoDialog").createDialog({
			width: 720,
			height: 500,
			title:title+'查询-输入查询条件',
			url:'${path}/baseinfo/searchPositiveInfos/prepareSearchPositiveInfos.action?orgId='+org,
			buttons: {
				"查询" : function(event){
				refreshPositiveInfoGrid();
				   $(this).dialog("close");
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});

	$("#isEmphasis").click(function(){
		var selectedId = getSelectedIdLast();
		if(selectedId == null){
			return;
		}
		$.ajax({
			url:"${path}/baseinfo/positiveInfo/updateIsEmphasisById.action",
			data:{
				"positiveInfo.id":selectedId,
				"positiveInfo.isEmphasis":1
			},
			success:function(responseData){
				if($("#isLock").val()=="-1"){
					$("#positiveInfoList").setCell(responseData.id,28,'1',null,null);
					$("#"+responseData.id+"").css('color','#778899');
					 $("#positiveInfoList").setSelection(responseData.id);
				}else{
					$("#positiveInfoList").delRowData(responseData.id);
				}
				$.messageBox({message:"取消关注成功！"});
				disableButtons();
				checkExport();
			}
		});
	});

	$("#emphasis").click(function(){
		var selectedId = getSelectedIdLast();
		if(selectedId == null){
			return;
		}
		$.ajax({
			url:"${path}/baseinfo/positiveInfo/updateIsEmphasisById.action",
			data:{
				"positiveInfo.id":selectedId,
				"positiveInfo.isEmphasis":0
			},
			success:function(responseData){
				if($("#isLock").val()=="-1"){
					$("#positiveInfoList").setCell(responseData.id,28,'0',null,null);
					$("#"+responseData.id+"").css('color','black');
					 $("#positiveInfoList").setSelection(responseData.id);
				}else{
					$("#positiveInfoList").delRowData(responseData.id);
				}
				$.messageBox({message:"重新关注成功！"});
				disableButtons();
				checkExport();
			}
		});
	});
	
	$("#add").click(function(event){
		if (!isgridBol){
			return;
		}
		$("#positiveInfoDialog").createActualPopulationDialog({
			width: dialogWidth,
			height: dialogHeight,
			model :"add",
			title:"新增刑释人员",
			data:[
				   {title:'基本信息',url:'/baseinfo/positiveInfo/dispath.action?mode=add&dailogName=positiveInfoDialog',buttons:{next:true}},
				   {title:'业务属性',url:'/baseinfo/positiveInfo/goBussinessPage.action?mode=add&dailogName=positiveInfoDialog',buttons:{prev:true,save:true,saveContinue:true}}

			]
		});

		});

	$("#shift").click(function(event){
		if (!isgridBol){
			return;
		}
		$("#positiveInfoDialog").createDialog({
			width: 300,
			height: 300,
			title:"转移"+title+"信息",
			url:'${path}/baseinfo/positiveInfo/shiftDispatch.action?orgId='+org+'&positiveInfoId='+$("#positiveInfoList").jqGrid("getGridParam", "selarrrow"),
			buttons: {
				"保存" : function(event){
			      $("#maintainShiftForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});

	$("#update").click(function(event){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
		  return;
		}
		$("#positiveInfoDialog").createActualPopulationDialog({
			width: dialogWidth,
			height: dialogHeight,
			model :"edit",
			title:"修改刑释人员",
			data:[
				   {title:'基本信息',url:'/baseinfo/positiveInfo/dispath.action?mode=edit&dailogName=positiveInfoDialog&population.id='+selectedId,buttons:{next:true}},
				   {title:'业务属性',url:'/baseinfo/positiveInfo/goBussinessPage.action?mode=edit&dailogName=positiveInfoDialog&population.id='+selectedId,buttons:{prev:true,save:true,saveContinue:true}}

			]
		});
		
	});
	$("#delete").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0||allValue==""){
			 return;
		}
		var str="确定要删除吗?一经删除将无法恢复";
		$.confirm({
				title:"确认删除",
				message:str,
				okFunc: function() {
					$.ajax({
						url:"${path}/baseinfo/positiveInfo/deletePositiveInfo.action?positiveInfoId="+allValue,
						success:function(data){
							//for(var ids=0;ids<data.length;ids++){
							//	$("#positiveInfoList").delRowData(data[ids]);
							//}
							//$("input[role='checkbox']").attr("checked",false);
							$("#positiveInfoList").trigger("reloadGrid");
						    $.messageBox({message:"已经成功删除该"+title+"信息!"});
						    disableButtons();
						    checkExport();
					}
				});
			}
		});
	});

	$("#import").click(function(event){
		$("#positiveInfoDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?dataType=positiveinfo&dialog=positiveInfoDialog&startRow=6&templates=POSITIVEINFO',
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
	});

	$("#export").click(function(event){
		if ($("#positiveInfoList").getGridParam("records")>0){
			$("#positiveInfoDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出'+title+'信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'positiveInfoList',
					downloadUrl:'${path}/baseinfo/searchPositiveInfos/downloadPostiveInfoPersonnel.action'
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

	$("#isLock").change(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#searchByConditionButton").click(function(){
		search(getCurrentOrgId());
		
	});

$("#view").click(function(event){
	var selectedId = getSelectedIdLast();
	if(selectedId==null){
 		return;
	}
	doubleClickTable(selectedId);
	});

$("#reload").click(function(){
	$("#searchByCondition").attr("value","");
	$("#positiveInfoList").setGridParam({
		url:'${path}/baseinfo/positiveInfo/findPositiveInfoByOrgId.action',
		datatype: "json"
	});
	$("#positiveInfoList").setPostData({
    	"orgId":org,
    	"page":1,
    	"positiveInfos.isEmphasis":$("#isLock").val()
   	});
	$("#positiveInfoList").trigger("reloadGrid");
	});

});
function checkExport(){
	if($("#positiveInfoList").getGridParam("records")>0 || $("#positiveInfoList").getGridParam("selrow")!=null){
		$("#export").buttonEnable();
	}else{
		$("#export").buttonDisable();
	}
}

function deleteRole(){
	var selectedId = getSelectedIds();
	if(selectedId==null){
		 return;
	}
	//var positiveInfo =  $("#positiveInfoList").getRowData(selectedId);
	$.ajax({
		url:'${path}/baseinfo/positiveInfo/deletePositiveInfo.action?positiveInfos.id='+ selectedId,
		success:function(data){
			if(data == true){
				$("#positiveInfoList").delRowData(positiveInfo.id);
				$.messageBox({ message:"成功删除"+title+"信息!"});
				checkExport();
				return;
			}else{
				$.messageBox({ message:"该人员信息已被别的模块所使用，不能删除!",level: "error"	});
			}
        }
	});
}
function doubleClickTable(rowid){
		var positiveInfo =  $("#positiveInfoList").getRowData(rowid);
		$("#positiveInfoDialog").createDialog({
			width: 800,
			height: 600,
			title:'查看'+title+'信息',
			modal : true,
			url:'${path}/baseinfo/positiveInfo/dispath.action?mode=view&population.id='+positiveInfo.id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
}

function print(rowid){
	$("#positiveInfoDialog").createDialog({
		width: 800,
		height: 605,
		title:'打印'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/positiveInfo/getPositiveInfoById.action?mode=print&positiveInfos.id='+rowid,
		buttons: {
			 "确定" : function(){
			$("#positiveInfoPrint").printArea();
        	$(this).dialog("close");
	   		},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}


function refreshPositiveInfoGrid() {
	var query_orgInternalCode=$('#query_orgInternalCode').val();
	var idCardNo=$("#positiveInfo\\.idCardNo").val();
	var name=$("#positiveInfo\\.name").val();
	var type=$("#type").val();
	var mobileNumber=$("#positiveInfo\\.mobileNumber").val();
	var caseReason=$("#positiveInfo\\.caseReason").val();
	var imprisonmentDate=$("#positiveInfo\\.imprisonmentDate").val();
	var agoProfession=$("#positiveInfo\\.agoProfession\\.id").val();
	var laborEduAddress=$("#positiveInfo\\.laborEduAddress").val();
	var releaseOrBackDate=$("#positiveInfo\\.releaseOrBackDate").val();
	var endReleaseOrBackDate=$("#positiveInfo\\.endReleaseOrBackDate").val();
	var resettlementDate=$("#positiveInfo\\.resettlementDate").val();
	var endResettlementDate=$("#positiveInfo\\.endResettlementDate").val();

	var crimeDate=$("#positiveInfo\\.crimeDate").val();
	var endCrimeDate=$("#positiveInfo\\.endCrimeDate").val();

	var gender=$("#positiveInfo\\.gender\\.id").val();
	var birthday=$("#positiveInfo\\.birthday").val();
	var endBirthday=$("#positiveInfo\\.endBirthday").val();

	var career=$("#positiveInfo\\.career\\.id").val();

	var workUnit=$("#positiveInfo\\.workUnit").val();
	
	var province=$("#province").val();
	var district=$("#district").val();
	var city=$("#city").val();

	var nativePlaceAddress=$("#positiveInfo\\.nativePlaceAddress").val();
	var currentlyAddress=$("#positiveInfo\\.currentlyAddress").val()==undefined?"":$("#positiveInfo\\.currentlyAddress").val();

	$("#positiveInfoList").setGridParam({
		url:'${path}/baseinfo/searchPositiveInfos/searchPositiveInfo.action',
		datatype : 'json',
		page:1
	});
	$("#positiveInfoList").setPostData({
		"orgId":org,
		"searchPositiveInfosVo.orgInternalCode":query_orgInternalCode,
		"searchPositiveInfosVo.idCardNo":idCardNo,
		"searchPositiveInfosVo.name":name,
		"searchPositiveInfosVo.positiveInfosType.id":type,
		"searchPositiveInfosVo.mobileNumber":mobileNumber,
		"searchPositiveInfosVo.caseReason":caseReason,
		"searchPositiveInfosVo.imprisonmentDate":imprisonmentDate,
		"searchPositiveInfosVo.agoProfession.id":agoProfession,
		"searchPositiveInfosVo.laborEduAddress":laborEduAddress,
		"searchPositiveInfosVo.releaseOrBackDate":releaseOrBackDate,
		"searchPositiveInfosVo.endReleaseOrBackDate":endReleaseOrBackDate,
		"searchPositiveInfosVo.resettlementDate":resettlementDate,
		"searchPositiveInfosVo.crimeDate":crimeDate,
		"searchPositiveInfosVo.endCrimeDate":endCrimeDate,
		"searchPositiveInfosVo.gender.id":gender,
		"searchPositiveInfosVo.birthday":birthday,
		"searchPositiveInfosVo.endBirthday":endBirthday,
		"searchPositiveInfosVo.career":career,
		"searchPositiveInfosVo.workUnit":workUnit,
		"searchPositiveInfosVo.province":province,
		"searchPositiveInfosVo.district":district,
		"searchPositiveInfosVo.city":city,
		"searchPositiveInfosVo.nativePlaceAddress":nativePlaceAddress,
		"searchPositiveInfosVo.currentlyAddress":currentlyAddress,
		
		"searchPositiveInfosVo.isEmphasis":$("#isLock").val()
		});
	$("#positiveInfoList").trigger("reloadGrid");
}

function afterLoad(){
	isEmphasisFormatter();
	disableButtons();
	checkExport();
}

function disableButtons(){
	$("#update").buttonDisable();
	$("#view").buttonDisable();
	$("#delete").buttonDisable();
	$("#shift").buttonDisable();
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#positiveInfoList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("positiveInfoList");
	var count = $("#positiveInfoList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("positiveInfoList", true);
	}else{
		jqGridMultiSelectState("positiveInfoList", false);
	}
	if(selectedCounts==1){
		$("#view").buttonEnable();
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
	}else{
		$("#view").buttonDisable();
		$("#update").buttonDisable();
	}
	if(isGrid()){
		$("#shift").buttonEnable();
	}else{
		$("#shift").buttonDisable();
	}
	if(selectedCounts==0){
		$("#shift").buttonDisable();
		$("#delete").buttonDisable();
	}
}

function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#positiveInfoList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#positiveInfoList").getRowData(idCollection[i]);
			if(ent.isEmphasis==1){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}
function getSelectedIds(){
	var selectedIds = getActualjqGridMultiSelectIds("positiveInfoList");
	return selectedIds;
}


</script>
 -->
