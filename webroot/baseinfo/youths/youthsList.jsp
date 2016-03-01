<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="keyType" value="<s:property value="#parameters.keyType"/>" />
<%@page import="com.tianque.baseInfo.youths.constants.YouthsType" %>
<%
String keyType = request.getParameter("keyType");
String moduleCName = "";
String moduleName = "";
String enameModuleName="";
if(keyType != null && !"".equals(keyType)){
	if(YouthsType.YOUNGSTERS.equals(keyType)) {
		moduleCName = YouthsType.YOUNGSTERS_NAME;
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1);
		enameModuleName = moduleName;
	} else if(YouthsType.YOUNGPIONEER.equals(keyType)) {
		moduleCName = YouthsType.YOUNGPIONEER_NAME;
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1);
		enameModuleName = moduleName;
	}else if(YouthsType.LEAGUEMEMBER.equals(keyType)) {
		moduleCName = YouthsType.LEAGUEMEMBER_NAME;
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1);
		enameModuleName = moduleName;
	}
}
request.setAttribute("moduleName",moduleName);
request.setAttribute("moduleCName",moduleCName);
request.setAttribute("keyType",keyType);
request.setAttribute("enameModuleName",enameModuleName);
%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="${moduleName }" name="moduleName"/>
</jsp:include>
<input type="hidden" id="keyTypes" value="${keyType }" />
<div class="content" >
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名或身份证号码" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名或身份证号码':this.value;" onfocus="value=(this.value=='请输入姓名或身份证号码')?'':this.value;"/>
            	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="search${enameModuleName}">
			<a id="search" href="javascript:;" class="advancedSearchButton"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
        <span class="lineBetween "></span>
		<pop:JugePermissionTag ename="down${enameModuleName}">
			<a id="export" href="javascript:;"><span>导出</span></a>
		</pop:JugePermissionTag>
	   	<a id="reload" href="javascript:;"><span>刷新</span></a>
	</div>

	<div style="clear: both;"></div>
	<div style="width:100%;" class="click_load">
	    <a href="javascript:;" class="click_btn">点击加载数据</a>
		<table id="youthsList"> </table>
		<div id="youthsListPager"></div>
	</div>
	<div id="youthsDialog"></div>
</div>

<div style="display: none;">
	<pop:JugePermissionTag ename="youngstersManagement,youngpioneerManagement,leaguememberManagement">
		<span id="title">${ moduleCName}</span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />

var title=$("#title").html();
var notExecute=new Array();
var dialogWidth=800;
var dialogHeight=600;

function onOrgChanged(orgId, isgrid){
	var initParam = {
		"organizationId": orgId,
		"searchYouthsVo.keyType": $("#keyTypes").val(),
		"searchYouthsVo.logOut":0,
		"searchYouthsVo.isDeath":false
	}
	$("#youthsList").setGridParam({
		url:'${path}/baseinfo/youthsManage/findYouthsList.action',
		datatype:'json',
		page:1
	});
	$("#youthsList").setPostData(initParam);
	$("#youthsList").trigger("reloadGrid");
}
function printChoose(rowid,url){
	$("#printOptionsDialog").createDialog({
		width: 300,
		height: 200,
		title:'选择打印内容',
		modal : true,
		url:PATH+'/baseinfo/commonPopulation/printTabChooseDlg.jsp',
		buttons: {
			"确定" : function(){
				print(rowid,url);
	   		},
		   "关闭" : function(){	
		        $(this).dialog("close");
		   }
		}
	});
}

var printTitleStr='';
function print(rowid,url){
	printTitleStr=title;
	if(rowid==null){
 		return;
	}
	$("#youthsDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'打印'+title+'信息',
		modal : true,
		url:url+'?mode=print&id='+rowid,
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
function viewDialog(event,rowNum){
	if(rowNum==null){
		return;
	}
	var rowData =  $("#youthsList").getRowData(rowNum);
	var rowid=rowData.encryptId;
	var actualPopulationType = rowData.actualPopulationType;
	var url;
	if(actualPopulationType =='<s:property value="@com.tianque.core.util.BaseInfoTables@HOUSEHOLDSTAFF_KEY"/>'){
		url=PATH+'/baseinfo/householdStaff/dispathByEncrypt.action';
	}else if(actualPopulationType =='<s:property value="@com.tianque.core.util.BaseInfoTables@FLOATINGPOPULATION_KEY"/>'){
		url=PATH+'/baseinfo/floatingPopulationManage/dispathByEncrypt.action';
	}else if(actualPopulationType =='<s:property value="@com.tianque.core.util.BaseInfoTables@UNSETTEDPOPULATION_KEY"/>'){
		url=PATH+'/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action';
	}		
	$("#youthsDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'查看'+title+'信息',
		url:url+'?mode=view&id='+rowid,
		buttons: {
			"打印" : function(){
				printChoose(rowid,url);
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
function callback(){
    TQ.youthsList()
}

$.cacheScript({
    url:'/resource/getScript/baseinfo/youths/youthsList.js',
    callback: callback
})

</script>