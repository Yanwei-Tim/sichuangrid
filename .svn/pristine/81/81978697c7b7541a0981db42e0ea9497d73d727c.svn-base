<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=500;
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
var notDeleteIds=new Array();

var org=getCurrentOrgId();
var title = $("#title").html();


function printChoose(){
	$("#printOptionsDialog").createDialog({
		width: 300,
		height: 200,
		title:'选择打印内容',
		modal : true,
		url:PATH+'/baseinfo/commonPopulation/printTabChooseDlg.jsp',
		buttons: {
			"确定" : function(){
				print();
	   		},
		   "关闭" : function(){	
		        $(this).dialog("close");
		   }
		}
	});
}

var printTitleStr='';
function print(){
	printTitleStr=title;
	$("#groupFamilyPopulationDialog").createDialog({
		width: 800,
		height: 600,
		title:'打印'+title+'信息',
		modal : true,
		url:PATH+"/baseinfo/commonPopulation/printTabPreviewDlg.jsp",
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

var printLoadUrlStr='';
function printByUrl(urlStr){
	printLoadUrlStr=urlStr;
	printTitleStr=title;
	$("#groupFamilyDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'打印'+title+'信息',
		modal : true,
		url:PATH+'/baseinfo/commonPopulation/printTabPreviewDlg.jsp',
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
	if(null == rowid){
 		return;
	}
	var groupFamily =  $("#groupFamilyList").getRowData(rowid);
	$("#groupFamilyPopulationDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看'+title+'信息',
		url:PATH+'/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=view&isHiddenPersonnelTrack=1&id='+groupFamily.encryptPopulationId,
		buttons: {
			"打印" : function(){
				printChoose(rowid);
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
    var dfop = {
    	population_type_household_staff: '<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>',
    	population_type_floating_population: '<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>',
    	population_type_unsettled_population: '<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>',
    	population_type_oversea_staff: '<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>',
       	hasViewGroupFamilyInfo: '<pop:JugePermissionTag ename="viewGroupFamilyInfo">true</pop:JugePermissionTag>',
       	hasDeleteGroupFamilyInfo: '<pop:JugePermissionTag ename="deleteGroupFamilyInfo">true</pop:JugePermissionTag>',
       	baseinfotables_overseapersonnel_key:'<s:property value="@com.tianque.core.util.BaseInfoTables@OVERSEAPERSONNEL_KEY"/>',
       	baseinfotables_unsettedpopulation_key:'<s:property value="@com.tianque.core.util.BaseInfoTables@UNSETTEDPOPULATION_KEY"/>',
       	baseinfotables_floatingpopulation_key:'<s:property value="@com.tianque.core.util.BaseInfoTables@FLOATINGPOPULATION_KEY"/>',
       	baseinfotables_householdstaff_key:'<s:property value="@com.tianque.core.util.BaseInfoTables@HOUSEHOLDSTAFF_KEY"/>',
       	cityOrgLevel:'<s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>',
       	provinceOrgLevel:'<s:property value="@com.tianque.domain.property.OrganizationLevel@PROVINCE"/>'
    }
    TQ.groupFamilyList(dfop)
}
$.cacheScript({
	url:'/resource/getScript/baseinfo/familyInfo/groupFamily/groupFamilyList.js',
	callback: callback
})
</script>

<div class="content">
	<div class="ui-corner-all" id="nav">

        <div class="btnbanner btnbannerData">
            <div class="ui-widget autosearch">
					<jsp:include page="/common/orgSelectedComponent.jsp">
				   <jsp:param  name="plateName" value="familyAndHouse"/>   
				</jsp:include>		
                <input class="basic-input" type="text" value="请输入家长证件号或家长姓名或家庭编号" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width:240px" onblur="value=(this.value=='')?'请输入家长证件号或家长姓名或家庭编号':this.value;" onfocus="value=(this.value=='请输入家长证件号或家长姓名或家庭编号')?'':this.value;"/>
            	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
            <a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
        </div>

	   	<pop:JugePermissionTag ename="searchGroupFamilyInfo">
	   		<a id="search" href="javascript:void(0)"><span><strong
					class="ui-ico-cakan"></strong>高级搜索</span></a>
	     </pop:JugePermissionTag>
        <span class="lineBetween"></span>
        <pop:JugePermissionTag ename="deleteGroupFamilyInfo">
	   		<a id="delete" href="javascript:void(0)"><span><strong
					class="ui-ico-del"></strong>批量删除</span></a>
	     </pop:JugePermissionTag>
	     <pop:JugePermissionTag ename="resetFamilyAccount">
	   		<a id="reset" href="javascript:void(0)"><span><strong
					></strong>重置家庭编号</span></a>
	     </pop:JugePermissionTag>
	     <pop:JugePermissionTag ename="changeFamilyMaster">
	   		<a id="change" href="javascript:void(0)"><span><strong
					></strong>更换家长</span></a>
	     </pop:JugePermissionTag>
	     <pop:JugePermissionTag ename="manageFamilyMembers">
	   		<a id="manage" href="javascript:void(0)"><span><strong
					></strong>管理维护成员</span></a>
	     </pop:JugePermissionTag>
	     <a id="reload" href="javascript:void(0)"><span><strong
				class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="width: 100%;" class="click_load">
		<a href="javascript:;" class="click_btn">点击加载数据</a>
		<table id="groupFamilyList"></table>
		<div id="groupFamilyListPager"></div>
	</div>
	<div id="groupFamilyDialog"></div>
	<div id="groupFamilyPopulationDialog"></div>
	<div id="resetFamilyAccountDlg"></div>
	<div id="changeFamilyMasterDlg"></div>
	<div id="manageFamilyMembersDlg"></div>
</div>

<div style="display: none;">
	<pop:JugePermissionTag ename="groupFamilyInfo">
		<span id="title"><s:property value="#request.name" /></span>
	</pop:JugePermissionTag>
</div>