<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<script type="text/javascript" src="${resource_path }/resource/external/jquery.PrintArea.js"></script>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入房屋编号或房屋准确地址" id="searchByCondition" maxlength="18" class="searchtxt" style="width:180px;" onblur="value=(this.value=='')?'请输入房屋编号或房屋准确地址':this.value;" onfocus="value=(this.value=='请输入房屋编号或房屋准确地址')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<pop:JugePermissionTag ename="searchActualHouse">
		<a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchActualHouse">
		<a id="searchActualHouse" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addActualHouse">
		<a id="addActualHouse" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteActualHouse">
		<a id="deleteActualHouse" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="importActualHouse,downloadActualHouse">
		<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="importActualHouse">
			<a id="importActualHouse" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>Excel导入</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="downloadActualHouse">
			<a id="exportActualHouse" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出Excel</span></a>
			</pop:JugePermissionTag>
		</div>
		<pop:JugePermissionTag ename="mantanceLivinginHousePopulation">
		<a id="mantanceLivinginHousePopulation" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>住户管理</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="transferActualHouse">
			<a id="transfer" href="javascript:void(0)"><span>转移</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="combineHouse">
			<a id="combineHouse" href="javascript:void(0)"><span>合并</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="generatedQrcodeByHouse">
			<a id="generatedQrcodeByHouse" href="javascript:void(0)"><span>生成二维码</span></a>
		</pop:JugePermissionTag>
		<a id="percentBtn" class="nav-dropdownBtn persentArea">
			<span id="housePeopleProportionSpan"></span>
			<b class="nav-dropdownBtn-arr">
				<b class="nav-ico-dArr"></b>
			</b>
			<div id="persetDown" class="persetDown hidden">
				<div class="linkArea"></div>
				<ul>
					<li>人房关联比例：<strong id="housePeopleProportion"></strong></li>
				</ul>
			</div>
		</a>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
		   <a id="moveToMongodb" href="javascript:void(0)"><span>迁移mongodb</span></a>
		   <a id="deleteMongodb" href="javascript:void(0)"><span>删除mongodb多余数据</span></a>
		</s:if>	
		<div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;" class="click_load">
	    <a href="javascript:;" class="click_btn">点击加载数据</a>
		<table id="actualHouseList"></table>
		<div id="actualHouseListPager"></div>
	</div>
	<div id="moveDataDialog"></div>
	<div id="actualHouseMaintanceDialog"></div>
	<div id="noticeDialog"></div>
	<div id="actualHouseDialog"></div>
	<div id="mergeActualHouseDialog"></div>
	<div id="megerHouseInfoDialog"></div>
	<div id="scanHeaderImage"></div>
	<div id="qrcodeDialog"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="actualHouseManagement">
	<span id="title"><s:property value="#request.name" /></span>
	</pop:JugePermissionTag>
</div>

<!-- 获取建筑物用途的DomainName -->
<input id="buildingUsesDomainName" type="hidden" value="<s:property value='@com.tianque.domain.property.PropertyTypes@BUILDING_USES'/>">
<!-- 获取建筑物用途的住宅displayName -->
<input id="buildingUsesDisplayName" type="hidden" value="<s:property value='@com.tianque.baseInfo.companyPlace.constacts.ModulTypes@RESIDENTIAL'/>">
<!-- 获取实有房屋类别 -->
<input id="actualhouseType" type="hidden" value="<s:property value='@com.tianque.core.util.NewBaseInfoTables@ACTUALHOUSE_KEY'/>">
<script type="text/javascript">

<pop:JugePermissionTag ename="actualHouseLeaderView"></pop:JugePermissionTag>
<pop:formatterProperty name="houseStructure" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
<pop:formatterProperty name="houseSource" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_SOURCE" />
<pop:formatterProperty name="houseUses" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" />
var dialogWidth=760;
var dialogHeight=510;
var currentOrgId=getCurrentOrgId();
var buildingUsesPropertyId = 0;

function getBuildingUses(){
	$.ajax({
		type: "POST",
		url:PATH+'/sysadmin/propertyManage/findPropertyDictByDomainName.action',
		data: {'propertyDomain.domainName':$("#buildingUsesDomainName").val()},  
		dataType: "json", 
		success:function(data){
			if(data != null && data.length > 0){
				var buildingUsesDisplayName = $("#buildingUsesDisplayName").val();
				for(var i = 0; i < data.length; i++){
					if(data[i].displayName == buildingUsesDisplayName){
						buildingUsesPropertyId = data[i].id;
						return;
					}
				}
			}
		}
	});
}
function housePeopleProportion(){
	$.ajax({
		type: "POST",
		url:PATH+'/baseinfo/actualHouseManage/getHouseCountAndProportionCount.action',
		data: {
			"orgId":getCurrentOrgId(),
			"houseType":$("#actualhouseType").val()
		},  
		dataType: "json", 
		success:function(data){
			$("#housePeopleProportionSpan").text(data);
			$("#housePeopleProportion").text(data);
		}
	});
}

function onOrgChanged(orgId,isgrid){
	currentOrgId=orgId;
	$("#actualHouseList").setGridParam({
		url:PATH+'/baseinfo/actualHouseManage/houseInfoList.action',
		datatype: "json",
		page:1
	});
	$("#actualHouseList").setPostData({
		"orgId":function(){return currentOrgId;}
	});
	$("#actualHouseList").trigger("reloadGrid");
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
	printTitleStr='房屋信息';
	var houseInfo =  $("#actualHouseList").getRowData(rowid);
	$("#actualHouseMaintanceDialog").createDialog({
		width: 800,
		height: 580,
		title:'打印房屋信息',
		modal : true,
		url:PATH+'/baseinfo/actualHouseManage/dispatchOperateByEncrypt.action?mode=print&houseId='+houseInfo.encryptId,
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

function viewActualHousePopulation(rowid){
	if(rowid==null){
 		return;
	}
	var houseInfo =  $("#actualHouseList").getRowData(rowid);
	$("#actualHouseMaintanceDialog").createDialog({
		width: 820,
		height: 530,
		title:'查看居住人员信息',
		modal : true,
		url:PATH+'/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt.action?houseInfo.organization.id='+houseInfo['organization.id']+'&houseInfo.id='+houseInfo.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function callback(){
    var dfop = {
       	hasViewActualHouse: '<pop:JugePermissionTag ename="viewActualHouse">true</pop:JugePermissionTag>',
       	hasUpdateActualHouse:'<pop:JugePermissionTag ename="updateActualHouse">true</pop:JugePermissionTag>',
       	hasDeleteActualHouse:'<pop:JugePermissionTag ename="deleteActualHouse">true</pop:JugePermissionTag>',
       	moduleCName:'${moduleCName}'
    }
    TQ.actualHouseList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/baseinfo/houseInfo/actualHouse/actualHouseList.js',
    callback: callback
})
</script>