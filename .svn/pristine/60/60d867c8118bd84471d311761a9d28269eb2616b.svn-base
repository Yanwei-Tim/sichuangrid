<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="HouseFamilyInfo" name="moduleName"/>
</jsp:include>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp">
				   <jsp:param  name="plateName" value="familyAndHouse"/>   
				</jsp:include>	
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入户口号" name="searchText" id="searchText" maxlength="20" class="searchtxt" style="width:200px" onblur="value=(this.value=='')?'请输入户口号':this.value;" onfocus="value=(this.value=='请输入户口号')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a href="javascript:;" id="fastSearch"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchHouseFamilyInfo">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cakan"></strong>高级搜索</span></a>
			</pop:JugePermissionTag>
			<span class="lineBetween"></span>
			<pop:JugePermissionTag ename="deleteHouseFamilyInfo">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-delete"></strong>批量删除</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="changeAccNo">
    		<a id="changeAccNo" href="javascript:void(0)"><span><strong class="ui-ico-delete"></strong>重置户口号</span></a>
    		</pop:JugePermissionTag>
    		<pop:JugePermissionTag ename="changeHouseHold">
    		<a id="changeHouseHold" href="javascript:void(0)"><span><strong class="ui-ico-delete"></strong>更换户主</span></a>
    		</pop:JugePermissionTag>
    		<pop:JugePermissionTag ename="maintainHouseFamilyMembers">
    		<a id="maintain" href="javascript:void(0)"><span><strong class="ui-ico-delete"></strong>管理维护成员</span></a>
    		</pop:JugePermissionTag>
			<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		</div>
	</div>
	<div style="width: 100%;" class="click_load">
	 	<a href="javascript:;" class="click_btn">点击加载数据</a>
		<table id="houseFamilyList"></table>
		<div id="houseFamilyListPager"></div>
	</div>
	<div id="houseFamilyDialog"></div>
	<div id="houseHoldStaffViewDialog"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="houseFamilyInfo">
		<span id="title"><s:property value="#request.name" /></span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
var org=getCurrentOrgId();
var title=$("#title").html();
var run = new Array();
var notRun = new Array();
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />
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
	printTitleStr=title;
	if(rowid==null){
 		return;
	}
	$("#houseHoldStaffViewDialog").createDialog({
		width: 800,
		height: 600,
		title:'打印'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/commonPopulation/printTabPreviewDlg.jsp',
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
	$("#houseFamilyDialog").createDialog({
		width: 800,
		height: 600,
		title:'打印'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/commonPopulation/printTabPreviewDlg.jsp',
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
function onOrgChanged(orgId){
	var initParam = {
		"orgId": orgId
	}
	$("#houseFamilyList").setGridParam({
		url:PATH+'/baseinfo/houseFamily/findHouseFamilyByOrgId.action',
		datatype: "json",
		page:1
	});
	$("#houseFamilyList").setPostData(initParam);
	$("#houseFamilyList").trigger("reloadGrid");
}	
function viewFun(rowId){
	if(rowId == null){
 		return;
	}
	var rowData = $("#houseFamilyList").getRowData(rowId);
	var hid = rowData['censusRegisterFamily.encryptId'];
	var orgId = getCurrentOrgId();
	$("#houseFamilyDialog").createDialog({
		width: 800,
		height: 600,
		title:'查看'+title,
		modal : true,
		//url:'${path}/baseinfo/houseFamily/dispath.action?houseFamily.id='+rowId+'&orgId='+orgId,
		url:'${path}/baseinfo/houseFamily/findHouseFamilyByEncryptId.action?houseFamily.id='+hid+'&orgId='+orgId,
		buttons: {
			"打印" : function(){
				printByUrl('${path}/baseinfo/houseFamily/findHouseFamilyById.action?houseFamily.id='+hid+'&orgId='+orgId+'&mode=print');
		  	},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function callback(){
    var dfop = {
    	hasViewHouseFamilyInfo: '<pop:JugePermissionTag ename="viewHouseFamilyInfo">true</pop:JugePermissionTag>',
       	hasDeleteHouseFamilyInfo: '<pop:JugePermissionTag ename="deleteHouseFamilyInfo">true</pop:JugePermissionTag>',
       	hasViewModuleName:'<pop:JugePermissionTag ename="view${moduleName}">true</pop:JugePermissionTag>',
    	cityOrgLevel:'<s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>',
       	provinceOrgLevel:'<s:property value="@com.tianque.domain.property.OrganizationLevel@PROVINCE"/>'
    }
    TQ.houseFamilyList(dfop)
}
$.cacheScript({
	url:'/resource/getScript/baseinfo/familyInfo/houseFamily/houseFamilyList.js',
	callback: callback
})
</script>