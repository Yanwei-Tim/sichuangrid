<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("moduleName",request.getParameter("moduleName"));%>
<%request.setAttribute("lowerCaseModuleName",request.getParameter("moduleName").substring(0,1).toLowerCase()+request.getParameter("moduleName").substring(1));%>
<%request.setAttribute("upperCaseModuleName",request.getParameter("moduleName").toUpperCase());%>
<%request.setAttribute("moduleCName",request.getParameter("moduleCName"));%>
<%request.setAttribute("supervisorPerson",request.getParameter("supervisorPerson"));%>
<%
int startRowNum=6;//startRowNum 是从哪一行读取excel 文件的实体数据
request.setAttribute("newStartRow",request.getParameter("newStartRow")==null?startRowNum:request.getParameter("newStartRow"));
%>

<div class="content" >
	<input type="hidden" value="${lowerCaseModuleName }" id="lowerCaseModuleName"/>
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名或身份证号码" id="searchByCondition" maxlength="18" style="width: 150px;" onblur="value=(this.value=='')?'请输入姓名或身份证号码':this.value;" onfocus="value=(this.value=='请输入姓名或身份证号码')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="search${moduleName}">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
        <span class="lineBetween"></span>

		<pop:JugePermissionTag ename="add${moduleName}">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<!--
		<pop:JugePermissionTag ename="update${moduleName}">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="view${moduleName}">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		 -->
		<pop:JugePermissionTag ename="delete${moduleName}">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="import${moduleName},down${moduleName}">
		<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="import${moduleName}">
				<a id="import" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>Excel导入</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="down${moduleName}">
				<a id="export" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出Excel</span></a>
			</pop:JugePermissionTag>
		</div>
		<pop:JugePermissionTag ename="cancelAttended${moduleName},attended${moduleName},cancelDeath${moduleName}">
		<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="cancelAttended${moduleName}">
				<a id="cancelEmphasise" href="javascript:void(0)"><span><strong class="ui-ico-CancelAttention"></strong>取消关注</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="attended${moduleName}">
				<a id="reEmphasise" href="javascript:void(0)"><span><strong class="ui-ico-refocusOn"></strong>重新关注</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="cancelDeath${moduleName}">
				<a id="cancelDeath"  href="javascript:void(0)" <pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">style='display: none;'</pop:GlobalSettingTag>><span><strong class="ui-ico-refocusOn"></strong>取消死亡</span></a>
			</pop:JugePermissionTag>
		</div>
		<a id="reload"  href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<a id="superviseMember" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理服务成员</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<a id="superviseMembers" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>批量添加服务人员</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="serviceRecordManagement">
			<a id="superviseRecord" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理服务记录</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="interview${moduleName}RecordManagement">
			<a id="interview" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理走访记录</span></a>
		</pop:JugePermissionTag>
		
		<s:if test="#parameters.moduleName[0].equals('SuperiorVisit')">
		<pop:JugePermissionTag ename="searchSuperiorVisitHistoryManagement">
			<a id="superviseHistory" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理上访历史</span></a>
		</pop:JugePermissionTag>
		</s:if>
		<pop:JugePermissionTag ename="toPositiveInfoForRectificativePerson">
		<s:if test='"RectificativePerson".equals(#attr.moduleName)'>
			<a id="toPositiveInfo" href="javascript:void(0)"><span><strong class="ui-ico-xg"></strong>转为刑释人员</span></a>
		</s:if>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="transfer${moduleName}">
			<a id="transfer"  href="javascript:void(0)"><span>转移</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="serviceSupervisionMeasuresMentalPatient">
		<s:if test='"MentalPatient".equals(#attr.moduleName)'>
			<a id="serviceSupervisionMeasures"  href="javascript:void(0)"><span>服务监管措施</span></a>
		</s:if>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="serviceSupervisionMeasuresDruggy">
		<s:if test='"Druggy".equals(#attr.moduleName)'>
			<a id="serviceSupervisionMeasures"  href="javascript:void(0)"><span>服务监管措施</span></a>
		</s:if>
		</pop:JugePermissionTag>
		<s:if test="#parameters.moduleName[0].equals('IdleYouth')">
		<div style="float: right;white-space:nowrap;">
			<select id="isSearch" name="" class="S_object">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@IDLEYOUTH_STAFF_TYPE" defaultValue="${population.staffType.id}" />
			</select>
		</div>
		</s:if>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;" class="click_load">
	    <a href="javascript:;" class="click_btn">点击加载数据</a>
		<table id="${lowerCaseModuleName}List"> </table>
		<div id="${lowerCaseModuleName}ListPager"></div>
	</div>
	<div id="${lowerCaseModuleName}Dialog"></div>
	<div id="noticeDialog"></div>
	<div id="${lowerCaseModuleName}MaintanceDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="toPositiveInfoDialog"></div>
	<div id="scanHeaderImage"></div>
	<div id="supervisorMaintanceDialog"></div>
</div>
<script>
var getSelectedIdWhenUpdate;
var notExecute=new Array();

var token = "<s:property value='@com.tianque.core.util.CreateTokenUtil@getToken()'/>";
function onOrgChanged(orgId,isgrid){
	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}/baseinfo/${lowerCaseModuleName}Manage/${lowerCaseModuleName}List.action",
		datatype: "json",
		page:1
	});
	$("#${lowerCaseModuleName}List").setPostData({
		"organizationId":orgId,
    	"population.isEmphasis":0
   	});
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
}

$(function(){
	$("#export").click(function(event){
		if($("#${lowerCaseModuleName}List").getGridParam("records")>0){
			console.log("${lowerCaseModuleName}List");
			var postData = $("#${lowerCaseModuleName}List").getPostData();
			//$("#${lowerCaseModuleName}List").setPostData($.extend(postData,{"search${moduleName}Vo.isEmphasis":$("#isLock").val()}));
			$("#${lowerCaseModuleName}MaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出${moduleCName}信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'${lowerCaseModuleName}List',
					downloadUrl:'${path}/baseinfo/search${moduleName}/download${moduleName}.action'
				},
				buttons: {
		   			"导出" : function(event){
						//$("#exceldownload").submit();
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
			$.messageBox({level : 'warn',message:"列表中没有数据，不能导出！"});
			return;
		}
	});
});
function viewDialog(event,id){
	var rowData = $("#${lowerCaseModuleName}List").getRowData(id);
	$("#${lowerCaseModuleName}Dialog").createDialog({
		width:dialogWidth,
		height:580,
		title:"查看${moduleCName}信息",
		url:'${path}/baseinfo/${lowerCaseModuleName}Manage/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&mode=view&id='+rowData.encryptId+'&populationType='+$("#_populationType_").val(),
		buttons: {
			"打印" : function(){
				printChoose(id);
	  	   	},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}

function printChoose(id){
	$("#printOptionsDialog").createDialog({
		width: 300,
		height: 200,
		title:'选择打印内容',
		modal : true,
		url:'${path}/baseinfo/commonPopulation/printTabChooseDlg.jsp',
		buttons: {
			"确定" : function(){
				print(id);
	            //$("#printOptionsDialog").dialog("close");
	   		},
		   "关闭" : function(){	
		        $(this).dialog("close");
		   }
		}
	});
}

var printTitleStr='';
function print(id){
	var rowData = $("#${lowerCaseModuleName}List").getRowData(id)
	printTitleStr='${moduleCName}';
	$("#${lowerCaseModuleName}Dialog").createDialog({
		width: dialogWidth,
		height:580,
		title:'打印${moduleCName}信息',
		modal : true,
		url:'${path}/baseinfo/${lowerCaseModuleName}Manage/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&mode=print&id='+rowData.encryptId+'&populationType='+$("#_populationType_").val(),
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


function deleteOperator(event,allValue){
	var str="确定要删除吗?一经删除将无法恢复";
	var encryptIds = deleteOperatorByEncrypt("${lowerCaseModuleName}",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:str,
		okFunc: function() {
			$.dialogLoading("open");
			$.ajax({
				url:"${path}/baseinfo/${lowerCaseModuleName}Manage/delete${moduleName}ByIds.action",
				type:"post",
				data:{
					"struts.token":token,
					"populationIds":encryptIds
				},
				success:function(data){
					$.dialogLoading("close");
					if(data!=null&&data!=true&&data!='true'){
						  $.messageBox({message:data,level:"error"});
						  return;
					}
					try{for(var ids=0;ids<allValue.toString().split(',').length;ids++){
						$("#${lowerCaseModuleName}List").delRowData(allValue.toString().split(',')[ids]);
					}}catch(e){}
				    $.messageBox({message:"已经成功删除该${moduleCName}信息!"});
				    disableButtons();
				    checkExport();
					$("#${lowerCaseModuleName}List").trigger("reloadGrid");
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
function updateOperator(event,selectedId){
	getSelectedIdWhenUpdate=selectedId;
	
	var ent =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
	var objectNames=$(ent.name).text();
	if(ent.isEmphasis==1||ent.isEmphasis=='1'){
		 $.messageBox({level : 'warn',message:"该${moduleCName}信息已经取消关注，不能修改!"});
		 return;
	}
	$("#${lowerCaseModuleName}Dialog").createTabDialog({
		title:"修改${moduleCName}",
		postData:{
			type:'${lowerCaseModuleName}',
			id : ent.encryptId,
			mode:'edit',
			imageType:"population"
		},
		tabs:[
			{title:'基本信息',url:'/baseinfo/${lowerCaseModuleName}Manage/dispatchOperateByEncrypt.action?mode=edit&dailogName=${lowerCaseModuleName}Dialog'}
			,{title:'业务信息',url:'/baseinfo/${lowerCaseModuleName}Manage/dispatch${moduleName}Business.action?mode=edit&dailogName=${lowerCaseModuleName}Dialog'}
			/* ,{title:'服务人员',url:'/supervisorManage/supervisorInfoManage/dispatchSupervisorInfo.action?dailogName=${lowerCaseModuleName}Dialog&population.id='+selectedId+'&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&population.populationType='+$("#_populationType_").val(),buttons:{prev:true,<pop:JugePermissionTag ename="serviceRecordManagement">next:true,</pop:JugePermissionTag>save:function(){$("#${lowerCaseModuleName}Dialog").dialog("close")}}}
			,{title:'服务记录',url:'/baseinfo/serviceRecordManage/dispatch.action?population.id='+selectedId+'&population.attentionPopulationType='+$("#_populationType_").val(),buttons:{save:function(){$("#${lowerCaseModuleName}Dialog").dialog("close")}}} */
			/* <pop:JugePermissionTag ename="serviceTeamMemberManagement">
				,{title:'服务人员',url:'/plugin/serviceTeam/router/routerManage/maintainServiceMemberForPopulation.action?dailogName=${lowerCaseModuleName}Dialog&module=${lowerCaseModuleName}'}
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="serviceRecordManagement">
				,{title:'服务记录',url:'/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?dailogName=${lowerCaseModuleName}Dialog&populationType='+$("#_populationType_").val()+'&name='+objectNames}
			</pop:JugePermissionTag> */
			
		],
		close : function(){
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function checkExport(){
}
function disableButtons(){
  	setDeleteButtonEnabled(false);
  	setOtherButtonEnabled(false);
  	toggleEmphasisButton();
}
function setDeleteButtonEnabled(enabled){

}

function toggleEmphasisButton(){
	var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");

}

function setOtherButtonEnabled(enabled){

}
function search${moduleName}(){
	$("#${lowerCaseModuleName}List").setGridParam({
		url:PATH+"/baseinfo/search${moduleName}/find${moduleName}sByQueryCondition.action",
		datatype: "json",
		page:1,
		mtype:"post"
	});
	//var data=$("#search${moduleName}Form").serializeObject({
		//excludeWhenNull:["searchMentalPatientVo.isTreat"]
    // });
	var data=$("#search${moduleName}Form").serializeArray();
	var dataJson={};
	for(i=0;i<data.length;i++){
		 if (dataJson[data[i].name]) {
           dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
		} else {
            dataJson[data[i].name] = data[i].value;
		}
		//dataJson[data[i].name]=data[i].value;
	}

	$("#${lowerCaseModuleName}List").setPostData($.extend({"search${moduleName}Vo.isEmphasis":$("#isLock").val(),"organizationId":getCurrentOrgId()},dataJson));
    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
}
function search(orgId){
	var conditions = $("#searchByCondition").val();
	if(conditions == '请输入姓名或身份证号码') return;
	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}/baseinfo/search${moduleName}/fastSearch.action",
		datatype: "json",
		page:1
	});
	 $("#${lowerCaseModuleName}List").setPostData({
		 "organizationId":orgId,
		 "search${moduleName}Vo.isEmphasis":0,
		 "search${moduleName}Vo.fastSearchKeyWords":conditions
     });
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
}


function callback(){
	var dfop={
		hasViewModuleName: '<pop:JugePermissionTag ename="view${moduleName}">true</pop:JugePermissionTag>',
		newStartRow: '${newStartRow}',
		attrModuleName: '${attr.moduleName}',
		upperCaseModuleName: "${upperCaseModuleName}",
		isIdleYouth: '<s:if test="#parameters.moduleName[0].equals('IdleYouth')">true</s:if>',
		commonPopulationLowerCaseModuleName:'${lowerCaseModuleName}',
		commonPopulationModuleName:'${moduleName}',
		commonPopulationModuleCName:'${moduleCName}',
		emphasis_dependent_population_state_true:'<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_DEPENDENT_POPULATION_STATE" value="true">true</pop:GlobalSettingTag>',
		emphasis_dependent_population_state_false:'<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@EMPHASIS_DEPENDENT_POPULATION_STATE" value="false">true</pop:GlobalSettingTag>',
		business_dependent_actual_population:'_<pop:GetGlobalSettingValueTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION"/>'
	}
	TQ.commonPopulationList(dfop)
}

$.cacheScript({
url:'/resource/getScript/baseinfo/commonPopulation/commonPopulationList.js',
callback: callback
})
</script>