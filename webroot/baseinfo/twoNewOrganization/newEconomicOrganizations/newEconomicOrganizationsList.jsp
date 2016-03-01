<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="NEWECONOMICORGANIZATIONS" name="moduleName"/>
</jsp:include>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@NEWECONOMICORGANIZATIONS_KEY"/>'/>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input type="text" class="basic-input" value="请输入组织名称或营业执照号码" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入组织名称或营业执照号码':this.value;" onfocus="value=(this.value=='请输入组织名称或营业执照号码'||this.value=='请输入检索条件')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="getNewEconomicOrganizations">
			<a id="search" href="javascript:void(0)"><span><strong
					class="ui-ico-cx"></strong>高级搜索</span>
			</a>
		</pop:JugePermissionTag>
<span class="lineBetween "></span>

		<pop:JugePermissionTag ename="addNewEconomicOrganizations">
			<a id="add" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong>新增</span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteNewEconomicOrganizations">
			<a id="delete" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong>批量删除</span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="importNewEconomicOrganizations,downloadNewEconomicOrganizations">
			<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
	    <div class="nav-sub-buttons">
		<pop:JugePermissionTag ename="importNewEconomicOrganizations">
			<a id="import" href="javascript:void(0)"><span><strong
					class="ui-ico-dr"></strong>Excel导入</span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downloadNewEconomicOrganizations">
			<a id="export" href="javascript:void(0)"><span><strong
					class="ui-ico-dc"></strong>导出Excel</span>
			</a>
		</pop:JugePermissionTag>
	</div>
	<pop:JugePermissionTag ename="isLogOutNewEconomicOrganizations,cancelNewEconomicOrganizations">
	<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
	</pop:JugePermissionTag>
	<div class="nav-sub-buttons">
		<pop:JugePermissionTag ename="isLogOutNewEconomicOrganizations">
			<a id="isLogOut" href="javascript:void(0)"><span><strong
					class="ui-ico-refocusOn"></strong>取消关注</span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="cancelNewEconomicOrganizations">
			<a id="cancelLogOut" href="javascript:void(0)"><span><strong
					class="ui-ico-CancelAttention"></strong>重新关注</span>
			</a>
		</pop:JugePermissionTag>
	</div>
		<a id="reload" href="javascript:void(0)"><span><strong
				class="ui-ico-refresh"></strong>刷新</span>
		</a>
	<pop:JugePermissionTag ename="serviceTeamMemberManagement">
		<a id="superviseServiceTeamMember" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理治安负责人</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="serviceRecordManagement">
		<a id="superviseRecord" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理巡场情况</span></a>
	</pop:JugePermissionTag>
	    <pop:JugePermissionTag ename="transferNewEconomicOrganizations">
		<a id="transfer" href="javascript:void(0)"><span><strong
			class="ui-ico-transfer"></strong>转移</span></a>
		</pop:JugePermissionTag>
	</div>
	

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
var currentOrgId=getCurrentOrgId();
var notExecute=new Array();

<pop:formatterProperty name="style" domainName="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE" />

function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateNewEconomicOrganizations'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteNewEconomicOrganizations'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewNewEconomicOrganizations'> onclick='viewNewEconomicOrganizations("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.name+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewNewEconomicOrganizations'>  onclick='viewNewEconomicOrganizations("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.name+"</a>";
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
						id : newEconomicOrganizations.encryptId,
						mode:'edit'
					},
					title:"修改"+title,
					width: dialogWidth,
					height: dialogHeight,
					tabs:[
						{title:'基本信息',url:PATH+'/baseinfo/newEconomicOrganizationsManage/dispathByEncrypt.action?mode=edit'}
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
	var allValue=deleteOperatorByEncrypt("newEconomicOrganizations",allValue,"encryptId");
	$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function(){
				$.ajax({
					url:PATH+'/baseinfo/newEconomicOrganizationsManage/deleteNewEconomicOrganizations.action',
					type:"post",
					data:{
						"newEconomicOrganizationsIds":allValue
					},
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

function printChoose(rowid){
	$("#printOptionsDialog").createDialog({
		width: 300,
		height: 200,
		title:'选择打印内容',
		modal : true,
		url:PATH+'/baseinfo/commonPopulation/printTabChooseDlg.jsp',
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
		url:PATH+'/baseinfo/newEconomicOrganizationsManage/dispathByEncrypt.action?mode=view&id='+newEconomicOrganizations.encryptId,
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

var printTitleStr='';
function print(rowid){
	var rowData =  $("#newEconomicOrganizationsList").getRowData(rowid);
	printTitleStr=title+' — '+getLocationName(rowData);
	var encryptId = rowData.encryptId;
	$("#newEconomicOrganizationsDialog").createDialog({
		width: dialogWidth,
		height: 600,
		title:'打印'+title+'信息',
		modal : true,
		url:PATH+'/baseinfo/newEconomicOrganizationsManage/dispathByEncrypt.action?mode=print&newEconomicOrganizations.id='+encryptId,
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

function getLocationName(rowData){
	return $(rowData.name).text();
}
function callback(){
    var dfop = {
    	hasViewNewEconomicOrganizations:'<pop:JugePermissionTag ename="viewNewEconomicOrganizations">true</pop:JugePermissionTag>'
    	
    
    }
    TQ.newEconomicOrganizationsList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/baseinfo/twoNewOrganization/newEconomicOrganizations/newEconomicOrganizationsList.js',
    callback: callback
})
	
</script>