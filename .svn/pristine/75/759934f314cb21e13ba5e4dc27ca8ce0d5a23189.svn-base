<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="UnsettledPopulation" name="moduleName"/>
</jsp:include>
<div class="content">
	<div class="ui-corner-all" id="nav">

        <div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>

            <div class="ui-widget autosearch">
                <input class="basic-input" type="text" value="请输入姓名或身份证号码" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名或身份证号码':this.value;" onfocus="value=(this.value=='请输入姓名或身份证号码')?'':this.value;" />
            	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
            <a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
        </div>
   		<pop:JugePermissionTag ename="searchUnsettledPopulation">
			<a id="search"  href="javascript:void(0)"><span>高级搜索</span></a>
   		</pop:JugePermissionTag>
        <span class="lineBetween"></span>
   		<pop:JugePermissionTag ename="addUnsettledPopulation">
			<a id="add" href="javascript:;"><span>新增</span></a>
   		</pop:JugePermissionTag>
   		<pop:JugePermissionTag ename="deleteUnsettledPopulation">
			<a id="delete" href="javascript:void(0)"><span>批量删除</span></a>
   		</pop:JugePermissionTag>
   		<pop:JugePermissionTag ename="importUnsettledPopulation,downUnsettledPopulation">
   		<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
   		</pop:JugePermissionTag>
		
		<div class="nav-sub-buttons">
	   		<pop:JugePermissionTag ename="importUnsettledPopulation">
				<a id="import" href="javascript:void(0)"><span>Excel导入</span></a>
	   		</pop:JugePermissionTag>
	   		<pop:JugePermissionTag ename="downUnsettledPopulation">
				<a id="export" href="javascript:void(0)"><span>导出Excel</span></a>
	   		</pop:JugePermissionTag>
	   	</div>
	   	<pop:JugePermissionTag ename="isLogOutUnsettledPopulation,cancelLogOutUnsettledPopulation,cancelDeathUnsettledPopulation">
		<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="isLogOutUnsettledPopulation">
	   			<a id="isLogOut" href="javascript:void(0)"><span>注销</span></a>
	   		</pop:JugePermissionTag>
	   		<pop:JugePermissionTag ename="cancelLogOutUnsettledPopulation">
	   			<a id="cancelLogOut" href="javascript:void(0)"><span>取消注销</span></a>
	   		</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="cancelDeathUnsettledPopulation">
	   			<a id="cancelDeath" href="javascript:void(0)"><span>取消死亡</span></a>
	   		</pop:JugePermissionTag>
		</div>
			<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>
			<pop:JugePermissionTag ename="settlePopulation">
				<a id="settle" href="javascript:void(0)"><span>落户</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="transferUnsettledPopulation"> 
				<a id="transfer"  href="javascript:void(0)"   ><span>转移</span></a> 
			</pop:JugePermissionTag>
			<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
				<a id="updateIdCardNo" href="javascript:void(0)"><span>修改身份证号码</span></a>
			</s:if>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="unsettledPopulationList"> </table>
		<div id="unsettledPopulationListPager"></div>
	</div>
	<div id="unsettledPopulationMaintanceDialog"></div>
	<div id="unsettledPopulationDialog"></div>
<div id="helpPrecordDialog"></div>
<div id="scanHeaderImage"></div>
</div>

<div style="display: none;">
	<span id="title">未落户人口</span>
</div>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=600;
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER"  />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="certificateType" domainName="@com.tianque.domain.property.PropertyTypes@CERTIFICATEHOLD_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="unSettedReason" domainName="@com.tianque.domain.property.PropertyTypes@UNSETTEDREASON"/>
var isgridBol = false;
var title=$("#title").html();
var notExecute=new Array();
var token = "<s:property value='@com.tianque.core.util.CreateTokenUtil@getToken()'/>";
function onOrgChanged(orgId, isgrid){
	isgridBol = isgrid;
	var initParam = {
		"orgId": orgId,
		"logOut":0
	}
	$("#unsettledPopulationList").setGridParam({
		url:PATH+'/baseinfo/unsettledPopulationManage/findUnsettledPopulations.action',
		datatype: "json",
		page:1
	});
	$("#unsettledPopulationList").setPostData(initParam);
	$("#unsettledPopulationList").trigger("reloadGrid");
}
function updateOperator(event,selectedId){
	var ent =  $("#unsettledPopulationList").getRowData(selectedId);
	if(ent.logOut==1||ent.logOut=='1'){
		 $.messageBox({level : 'warn',message:"该"+title+"信息已经注销，不能修改!"});
		 return;
	}
	$("#unsettledPopulationMaintanceDialog").createTabDialog({
		title:"修改未落户人口",
		width: dialogWidth,
		height:dialogHeight,
		postData:{
			id : selectedId,
			mode:'edit',
			imageType:"population",
			type:"unsettledPopulation"
		},
		tabs:[
		   {title:'基本信息',url:'/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action?mode=edit&dailogName=unsettledPopulationMaintanceDialog&unsettledPopulation.id='+ent.encryptId,buttons:{save:true}}
		   <pop:JugePermissionTag ename="groupFamilyInfo">
		   ,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?mode=edit&dailogName=unsettledPopulationMaintanceDialog&population.id='+ent.encryptId+'&populationType=<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>',buttons:{prev:true,save:true}}
		   </pop:JugePermissionTag>
		],
		close : function(){
			$("#unsettledPopulationList").trigger("reloadGrid");
		}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function deleteOperator(event,value){
	var str = "确定删除吗？一经删除无法恢复 ";
	var encryptIds = deleteOperatorByEncrypt("unsettledPopulation",value,"encryptId");
	$.confirm({
			title:"确认删除",
			message:str,
			okFunc: function() {
				$.dialogLoading("open");
				$.ajax({
					url:PATH+"/baseinfo/unsettledPopulationManage/deleteUnsettledPopulation.action",
					type:"post",
					data:{
						"struts.token":token,
						"unsettledPopulationIds":encryptIds
					},
					success:function(data){
						$.dialogLoading("close");
						if(data!=null&&data!=true&&data!='true'){
							  $.messageBox({message:data,level:"error"});
							  return;
						}
						try{for(var ids=0;ids<value.toString().split(',').length;ids++){
							$("#unsettledPopulationList").delRowData(value.toString().split(',')[ids]);
						}}catch(e){}
						$("#unsettledPopulationList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功删除该"+title+"信息!"});
					    disableButtons();
					    checkExport();
				}
			});
		}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
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

var printTitleStr='';
function print(rowid){
	printTitleStr=title;
	if(rowid==null){
 		return;
	}
	var unsettledPopulation =  $("#unsettledPopulationList").getRowData(rowid);
	$("#unsettledPopulationMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'打印'+title+'信息',
		modal : true,
		url:PATH+'/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action?mode=print&id='+unsettledPopulation.encryptId,
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
function viewDialog(event,rowid){
	if(rowid==null){
			return;
	}
	var unsettledPopulation =  $("#unsettledPopulationList").getRowData(rowid);
	$("#unsettledPopulationMaintanceDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'查看'+title+'信息',
		url:PATH+'/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action?mode=view&id='+unsettledPopulation.encryptId,
		buttons: {
			"打印" : function(){
				printChoose(unsettledPopulation.id);
  	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function checkExport(){
	
}

function disableButtons(){

}

function callback(){
    var dfop = {
    	templates: '<s:property value="@com.tianque.datatransfer.ImportTemplatesSetting@UNSETTLED_POPULATION"/>'
    	,hasViewUnsettledPopulation: '<pop:JugePermissionTag ename="viewUnsettledPopulation">true</pop:JugePermissionTag>'
    	,actualPopulationType: '<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>'
       	,addTabs:[
				   {title:'基本信息',url:'/baseinfo/unsettledPopulationManage/dispatchOperate.action?dailogName=unsettledPopulationMaintanceDialog'}
				   <pop:JugePermissionTag ename="groupFamilyInfo">
				   ,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?dailogName=unsettledPopulationMaintanceDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>'}
				   </pop:JugePermissionTag>
				]
    	,settleTabs: function (idCardNo){return [
       				{title:'基本信息',url:'/baseinfo/householdStaff/dispath.action?dailogName=unsettledPopulationMaintanceDialog&mode=add&actionName=householdStaffBaseInfo&population.idCardNo='+ idCardNo}
      				<pop:JugePermissionTag ename="groupFamilyInfo">
      				,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?mode=add&dailogName=unsettledPopulationMaintanceDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>'}
      				</pop:JugePermissionTag>
      				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION" value="true">
      				,{title:'业务信息',url:'/baseinfo/commonPopulation/populationSpecializedInfo.jsp?mode=add&dailogName=unsettledPopulationMaintanceDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>'}
      				</pop:GlobalSettingTag>
    	];}
    }
   	TQ.unsettledPopulationList(dfop)
}
$.cacheScript({
	url:'/resource/getScript/baseinfo/unsettledPopulation/unsettledPopulationList.js',
	callback: callback
})
</script>