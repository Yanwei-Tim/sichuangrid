<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp"/>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@NEWECONOMICORGANIZATIONS_KEY"/>'/>
<div class="content">
	
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="newEconomicOrganizationsList">
		</table>
		<div id="newEconomicOrganizationsListPager"></div>
	</div>
	<div id="newEconomicOrganizationsDialog"></div>
	<div id="scanHeaderImage"></div>
</div>

<div style="display: none;">
	<pop:JugePermissionTag ename="newEconomicOrganizationsManagement">
		<span id="title"><s:property value="#request.name" />
		</span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
var dialogWidth=810;
var dialogHeight=600;
var title=$("#title").html().trim();
var currentOrgId=getOrgIdForStat();
var notExecute=new Array();
function onOrgChanged(orgId, isgrid){
    var	initParam = {
		"organizationId": orgId,
		"searchNewEconomicOrganizationsVo.isEmphasis":0
	}

	$("#newEconomicOrganizationsList").setGridParam({
		url:'${path}/baseinfo/newEconomicOrganizationsManage/findNewEconomicOrganizations.action',
		datatype:'json',
		page:1
	});
	$("#newEconomicOrganizationsList").setPostData(initParam);
	$("#newEconomicOrganizationsList").trigger("reloadGrid");
}

$(document).ready(function(){

	<pop:formatterProperty name="style" domainName="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE" />

	function search(orgId){
		var condition = $("#searchByCondition").val();
		if(condition == '请输入组织名称或营业执照号码') return;
		var initParam = {
				"organizationId": orgId,
				"searchNewEconomicOrganizationsVo.isEmphasis":0,
				"searchNewEconomicOrganizationsVo.fastSearch":condition
			}
		
		$("#newEconomicOrganizationsList").setGridParam({
			url:'${path}/baseinfo/newEconomicOrganizationsManage/searchNewEconomicOrganizations.action',
			datatype: "json",
			page:1
		});
		$("#newEconomicOrganizationsList").setPostData(initParam);
		$("#newEconomicOrganizationsList").trigger("reloadGrid");
	}

	function licenseNumberFont(el,options,rowData){
		if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
			return "<font color='#778899'>"+rowData.licenseNumber+"</font>";
		}else{
			return "<font color='#000'>"+rowData.licenseNumber+"</font>";
		}
	}
    function dbClickViewNewEconomicOrganizationsInfo(rowId){
    	var rowData = $("#newEconomicOrganizationsList").getRowData(rowId);
		var id = rowData.id;
		viewNewEconomicOrganizations(id);
    }
	$("#add").click(function(event){
		if(!isGrid()){
			$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行新增！"});
			return;
		}

		$("#newEconomicOrganizationsDialog").createTabDialog({
				model :"add",
				title:'新增'+title,
				width: dialogWidth,
				height: dialogHeight,
				tabs:[
					{title:'基本信息',url:'${path}/baseinfo/newEconomicOrganizationsManage/dispath.action?mode=add&dailogName=newEconomicOrganizationsDialog'}
				],
				close : function(){
					$("#newEconomicOrganizationsList").trigger("reloadGrid");
				}
			});



	  });
	$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","请输入检索条件");});
	$("#update").click(function(){
		var selectedId = getSelectedIds();
		if(selectedId.length >1 ||selectedId.length==0){
			 return;
		}
		updateOperator(event,selectedId);
	});

	$("#isEmphasis").change(function(){
		onOrgChanged(getOrgIdForStat(),isGrid());
	});
	$("#searchByConditionButton").click(function(){
		search(getOrgIdForStat());

	});
	$("#view").click(function(event){
		var allValue = getSelectedIds();
		if(allValue.length >1 || allValue.length==0){
			 return;
		}
		viewNewEconomicOrganizations(allValue);
	});
	$("#delete").click(function(event){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(event,allValue);
	});

	$("#reload").click(function(event){
		$("#conditionMark").val("name");
		$("#searchByCondition").attr("value","请输入组织名称或营业执照号码");
		onOrgChanged(getOrgIdForStat(),isGrid());
	});

	function operatorFormatter(el,options,rowData){
		return "<a href='javascript:;' onclick='viewNewEconomicOrganizations("+rowData.id+");'><span>查看</span></a> ";
	}
	function nameFont(el,options,rowData){
		if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
			return "<a href='javascript:;' <pop:JugePermissionTag ename='viewNewEconomicOrganizations'> onclick='viewNewEconomicOrganizations("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.name+"</font></a>";
		}
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewNewEconomicOrganizations'>  onclick='viewNewEconomicOrganizations("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.name+"</a>";
	}

	$("#newEconomicOrganizationsList").jqGridFunction({
		datatype: "local",
		mytype:"post",
	   	colModel:[
	        {name:"id", index:"id",sortable:false, hidden:true,frozen:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
	        {name:"name", sortable:true, label:'名称',formatter:nameFont,width:200,frozen:true },
	        {name:"residence", sortable:true, label:'住所', width:200 },
	        {name:'chief',  sortable:true, label:'负责人', width:100},
	        {name:'style',formatter:styleFormatter, sortable:true, label:'类别', width:150},
	        {name:'licenseNumber',sortable:true, label:'营业执照号码', formatter:licenseNumberFont, width:130,frozen:true},
	        {name:'validityStartDate',sortable:true, label:'有效期开始日期',align:'center', width:110},
	        {name:'validityEndDate', sortable:true, label:'有效期结束日期',align:'center', width:110},
	        {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	        {name:'updateDate', sortable:true, label:'数据更新时间',align:'center', width:140},
	        {name:'organization.orgName',sortable:false,index:'organization.orgName',label:'所属网格',width:150,hidden:true},
	        {name:'area',  sortable:true, label:'面积(<font size="2">m</font><font size="1"><sup>2</sup></font>)',width:100,hidden:true},
	        {name:'employeeNumber', sortable:true, label:'从业人数', width:90,hidden:true},
	        {name:'partyMemberNumber', sortable:true, label:'党员人数', width:90,hidden:true},
	        {name:'foxNumber', sortable:true, label:'传真', width:120,hidden:true},
	        {name:'telephone', sortable:true, label:'固定电话',align:'center', width:120,hidden:true},
	        {name:'mobileNumber', sortable:true, label:'联系手机',align:'center', width:100,hidden:true},
	        {name:'isEmphasis',sortable:false,hidden:true,hidedlg:true,width:80}

		],
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
		<pop:JugePermissionTag ename="viewNewEconomicOrganizations">
		ondblClickRow:dbClickViewNewEconomicOrganizationsInfo,
		</pop:JugePermissionTag>
		loadComplete: afterLoad,
		onSelectRow: selectRow
	});
	jQuery("#newEconomicOrganizationsList").jqGrid('setFrozenColumns');
	$("#newEconomicOrganizationsList").closest(".ui-jqgrid-bdiv").css({ "height" : "435" });
	if (getOrgIdForStat()!=null && getOrgIdForStat()!=""){
		onOrgChanged(getOrgIdForStat(),isGrid());
	}
	$("#search").click(function(event){
		$("#newEconomicOrganizationsDialog").createDialog({
			width:650,
			height:350,
			title: title+'查询-请输入查询条件',
			url: '${path}/baseinfo/twoNewOrganization/newEconomicOrganizations/searchnewEconomicOrganizationsCondition.jsp',
			buttons: {
				"查询" : function(event){
					searchnewEconomicOrganizations();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#isLogOut").click(function(){
		if($(this).attr("disabled")=="disabled"){
			return;
		}
		var allValue = getSelectedIds();
		var logOut=new Array();
		var temp=new Array();
		if(null==allValue||allValue.length<=0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行取消关注！"});
			return;
			}
		for(var i=0;i<allValue.length;i++){
			var row=$("#newEconomicOrganizationsList").getRowData(allValue[i]);
			if(row.isEmphasis==0||row.isEmphasis=="0"){
				logOut.push(allValue[i]);
				}else{
					temp.push(allValue[i]);
					}
			}
		allValue=logOut;
		if(allValue==null||allValue.length==0){
			$.messageBox({level:'warn',message:"没有可取消关注的数据！"});
			return;
		}
		$("#newEconomicOrganizationsDialog").createDialog({
				width:450,
				height:210,
				title:'取消关注提示',
				modal:true,
				url:'${path}/baseinfo/twoNewOrganization/newEconomicOrganizations/emphasiseConditionDlg.jsp?locationIds='+allValue+'&isEmphasis=1&dailogName=newEconomicOrganizations&temp='+temp,
				buttons: {
				   "保存" : function(event){
					   $("#emphasisForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});

	});

	$("#cancelLogOut").click(function(){
		var allValue = getSelectedIds();
		var cancelLogOut=new Array();
		var temp=new Array();
		if($(this).attr("disabled")=="disabled"){
			return;
		}
		if(null==allValue||allValue.length<=0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再取消注销！"});
			return;
			}
		for(var i=0;i<allValue.length;i++){
			var row=$("#newEconomicOrganizationsList").getRowData(allValue[i]);
			if(row.isEmphasis==1||row.isEmphasis=="1"){
				cancelLogOut.push(allValue[i]);
				}else{
					temp.push(allValue[i]);
					}
			}
		allValue=cancelLogOut;
		if(allValue==null||allValue.length==0){
			$.messageBox({level:'warn',message:"没有可取消注销的数据！"});
			return;
		}
		$.confirm({
				title:"确认取消注销",
				message:"确定要取消注销吗?",
				okFunc: function(){
					$.ajax({
						url:"${path}/baseinfo/newEconomicOrganizationsManage/updateLogOutOfNewEconomicOrganizationss.action?newEconomicOrganizations.isEmphasis=0&newEconomicOrganizationsIds="+allValue,
						success:function(datas){
								for(var i = 0;i<datas.length;i++){
									var responseData = datas[i];
									if($("#isEmphasis").val()==1){
					 					$("#newEconomicOrganizationsList").delRowData(responseData.id+"");
				 					}else{
					 					$("#newEconomicOrganizationsList").setRowData(responseData.id,responseData);
					 					$("#"+responseData.id+"").css('color','#000000');
					 					$("#newEconomicOrganizationsList").setSelection(responseData.id);
						 			}
								}
							if(null==temp || temp.length==0){
								$.messageBox({message:"新经济组织取消注销成功！"});
							}else{
								$.messageBox({level:'warn',message:"除选中的红色数据外,其余新经济组织已取消注销成功！"});
							}
							notExecute=temp;
							$("#newEconomicOrganizationsList").trigger("reloadGrid");
							disableButtons();
							checkExport();
						}
				});
			}
		});
	});

	$("#import").click(function(event){
		$("#newEconomicOrganizationsDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?isNew=1&dataType=newEconomicOrganizations&dialog=newEconomicOrganizationsDialog&startRow=6&templates=NEWECONOMICORGANIZATIONS&listName=newEconomicOrganizationsList',
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
		if ($("#newEconomicOrganizationsList").getGridParam("records")>0){
			$("#newEconomicOrganizationsDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出'+title+'信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'newEconomicOrganizationsList',
					downloadUrl:'${path}/baseinfo/newEconomicOrganizationsManage/downloadNewEconomicOrganizations.action'
					},
				buttons: {
		   			"导出" : function(event){
		   				exceldownloadSubmitForm();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
			return;
		}
	});
	
	$("#superviseServiceTeamMember").click(function(event){
		var selectedIds = $("#newEconomicOrganizationsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length>1){
			$.messageBox({level : 'warn',message:"同时只能操作一条记录！"});
			 return;
		}
		if(selectedIds==null ||selectedIds =="" || selectedIds.length < 1){
			$.messageBox({level : 'warn',message:"请先选择一条记录，再进行操作！"});
			 return;
		}
		var selectedId = selectedIds;
		var rowData=$("#newEconomicOrganizationsList").getRowData(selectedId);
		if(rowData.isEmphasis == 1){
			$.messageBox({level:'warn',message:"该条数据已注销!"});
			return;
		}
		var locationName = getLocationName(rowData);
		$("#newEconomicOrganizationsListDialog").createDialog({
			width:860,
			height:600,
			title:'新经济组织治安负责人情况',
			url:'/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocation.action?dailogName=newEconomicOrganizationsDialog&module=newEconomicOrganizations&id='+selectedId,
			postData:{"name":locationName},		
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			},
			close: function(event, ui) {$("#${lowerCaseModuleName}List").trigger("reloadGrid");}
		});
	});
	$("#superviseRecord").click(function(event){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行操作！"});
			 return;
		}
		var rowData = $("#newEconomicOrganizationsList").getRowData(selectedId);
		if(rowData.isEmphasis == 1){
			$.messageBox({level:'warn',message:"该条数据已注销!"});
			return;
		}
		var locationName = getLocationName(rowData);
		$("#newEconomicOrganizationsDialog").createDialog({
			width:900,
			height:560,
			title:'新经济组织巡场情况',
			url:'/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?dailogName=newEconomicOrganizationsDialog&populationType=NEWECONOMICORGANIZATIONS&name='+encodeURIComponent(locationName)+'&id='+selectedId+'&showRecordType=1',
			postData:{"location.name":locationName},
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			},
			close: function(event, ui) {$("#${lowerCaseModuleName}List").trigger("reloadGrid");}
		});
	});
	$("#transfer").click(function(event){
		if($("#shift").attr("disabled")){
			return ;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行转移！"});
			return;
		}
		var rowData_Popu=$("#newEconomicOrganizationsList").getRowData(selectedId);
		if(rowData_Popu.isEmphasis==1){
				$.messageBox({level:'warn',message:"所选记录中存在已取消关注（或已注销、死亡）记录，无法转移！"});
				 return;
		}
		$("#newEconomicOrganizationsListDialog").createDialog({
			width: 500,
			height: 300,
			title:"转移新经济组织信息",
			url:'${path}/transferManage/transferDispatch.action?orgId='+getOrgIdForStat()+"&ids="+selectedId+"&type=newEconomicOrganizations",
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
	
	
});

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#newEconomicOrganizationsList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getLocationName(rowData){
	return $(rowData.name).text();
}

function afterLoad(){
	logOutFormatter();
	disableButtons();
	checkExport();
	changeColor();
}

function changeColor(){
	if(notExecute==null||notExecute.length==0){
		return;
	}
	for(var i=0;i<notExecute.length;i++){
		var rowData=$("#newEconomicOrganizationsList").getRowData(notExecute[i]);
			//"<a href='javascript:viewFloatingPopulationInfo("+rowData.id+")'><font color='red'>"+rowData.name+"</font></a>";
			//"<a href='javascript:viewFloatingPopulationInfo("+rowData.id+")'><font color='red'>"+rowData.idCardNo+"</font></a>";
		$("#"+notExecute[i]).css('background','red');
		//$("#"+notExecute[i]+"a").css('color','red');
		$("#newEconomicOrganizationsList").setSelection(notExecute[i])
	}
	notExecute.splice(0,notExecute.length);
}

function logOutFormatter(){
	var idCollection = new Array();
	idCollection=$("#newEconomicOrganizationsList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#newEconomicOrganizationsList").getRowData(idCollection[i]);
			if(ent.isEmphasis==1||ent.isEmphasis=='1'){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
	}
}

function checkExport(){
}
function disableButtons(){
}
function selectRow(){
	var count = $("#newEconomicOrganizationsList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("newEconomicOrganizationsList");
	if(selectedCounts == count){
		jqGridMultiSelectState("newEconomicOrganizationsList", true);
	}else{
		jqGridMultiSelectState("newEconomicOrganizationsList", false);
	}
}

function viewNewEconomicOrganizations(rowid){
	if(rowid==null){
 		return;
	}
	var newEconomicOrganizations =  $("#newEconomicOrganizationsList").getRowData(rowid);
	$("#newEconomicOrganizationsDialog").createDialog({
		width: dialogWidth,
		height: 600,
		title:'查看'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/newEconomicOrganizationsManage/dispath.action?mode=view&newEconomicOrganizations.id='+newEconomicOrganizations.id,
		buttons: {
			"打印" : function(){
				printChoose(rowid);
	  	   	},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function printChoose(rowid){
	$("#printOptionsDialog").createDialog({
		width: 300,
		height: 200,
		title:'选择打印内容',
		modal : true,
		url:'${path}/baseinfo/commonPopulation/printTabChooseDlg.jsp',
		buttons: {
			"确定" : function(){
				print(rowid);
	   		},
		   "关闭" : function(){	
		        $(this).dialog("close");
		   }
		}
	});
}

var printTitleStr='';
function print(rowid){
	var rowData =  $("#newEconomicOrganizationsList").getRowData(rowid);
	printTitleStr=title+' — '+getLocationName(rowData);
	$("#newEconomicOrganizationsDialog").createDialog({
		width: dialogWidth,
		height: 600,
		title:'打印'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/newEconomicOrganizationsManage/dispath.action?mode=print&newEconomicOrganizations.id='+rowData.id,
		buttons: {
			  "打印" : function(){
				$("#printSpaceDiv").printArea();
	        	$(this).dialog("close");
	   		},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

/*
function print(rowid){
	if(rowid==null){
 		return;
	}
	var newEconomicOrganizations =  $("#newEconomicOrganizationsList").getRowData(rowid);
	$("#newEconomicOrganizationsDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'打印'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/newEconomicOrganizationsManage/dispatch.action?mode=print&newEconomicOrganizations.id='+newEconomicOrganizations.id,
		buttons: {
			  "确定" : function(){
				$("#newEconomicOrganizationsPrint").printArea();
	        	$(this).dialog("close");
	   		},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
*/


function searchnewEconomicOrganizations() {
	var data=$("#searchNewEconomicOrganizations").serializeArray();
	var dataJson={};
	for(i=0;i<data.length;i++){
		dataJson[data[i].name]=data[i].value;
	}
	dataJson["organizationId"]=getOrgIdForStat();
	dataJson["orgId"]=getOrgIdForStat();
	dataJson["searchNewEconomicOrganizationsVo.isEmphasis"]=$("#isEmphasis").val();
	$("#newEconomicOrganizationsList").setGridParam({
		url:'${path}/baseinfo/newEconomicOrganizationsManage/searchNewEconomicOrganizations.action',
		datatype: "json",
		mtype:'post',
		page:1
	});
	$("#newEconomicOrganizationsList").setPostData(dataJson);
	$("#newEconomicOrganizationsList").trigger("reloadGrid");
}


function getSelectedIds(){
	var selectedIds='';
	var selectedIds = $("#newEconomicOrganizationsList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

function updateOperator(event,selectedId){
	var ent =  $("#newEconomicOrganizationsList").getRowData(selectedId);
	if(ent.isEmphasis==1||ent.isEmphasis=='1'){
		 $.messageBox({level:'warn',message:"该"+title+"信息已经注销，不能修改!"});
		 return;
	}
	var newEconomicOrganizations =  $("#newEconomicOrganizationsList").getRowData(selectedId);

	$("#newEconomicOrganizationsDialog").createTabDialog({
					postData:{
						id : newEconomicOrganizations.id,
						mode:'edit'
					},
					title:"修改"+title,
					width: dialogWidth,
					height: dialogHeight,
					tabs:[
						{title:'基本信息',url:'${path}/baseinfo/newEconomicOrganizationsManage/dispath.action?mode=edit'}
					],
					close : function(){
						$("#newEconomicOrganizationsList").trigger("reloadGrid");
					}
				});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}

function deleteOperator(event,allValue){
	$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function(){
				$.ajax({
					url:'${path}/baseinfo/newEconomicOrganizationsManage/deleteNewEconomicOrganizations.action?newEconomicOrganizationsIds='+allValue,
					success:function(data){
						var datas=data.split(",");
						for(var i = 0;i<datas.length;i++){
							var did = datas[i];
				 				$("#newEconomicOrganizationsList").delRowData(did);
						}
					    $.messageBox({message:"已经成功删除该"+title+"信息!"});
					    disableButtons();
						checkExport();
						$("#newEconomicOrganizationsList").trigger("reloadGrid");
				    }
			    });
		   }
	 });
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
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