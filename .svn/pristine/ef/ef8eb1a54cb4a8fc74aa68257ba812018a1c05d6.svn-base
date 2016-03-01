<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />

<div class="content" >
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入房屋准确地址、编号或房主姓名" id="searchByCondition" maxlength="18" class="searchtxt" style="width:195px;" onblur="value=(this.value=='')?'请输入房屋准确地址、编号或房主姓名':this.value;" onfocus="value=(this.value=='请输入房屋准确地址、编号或房主姓名')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<pop:JugePermissionTag ename="searchRentalHouse">
		<a href="javascript:;" id="searchHouseByConditionButton"><span>搜索</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchRentalHouse">
		<a id="searchRentalHouse" href="javascript:void(0)"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addRentalHouse">
		<a id="addRentalHouse" href="javascript:void(0)"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteRentalHouse">
		<a id="deleteRentalHouse" href="javascript:void(0)"><span>批量删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="importRentalHouse,downloadRentalHouse">
		<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="importRentalHouse">
			<a id="importRentalHouse" href="javascript:void(0)"><span>Excel导入</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="downloadRentalHouse">
			<a id="exportRentalHouse" href="javascript:void(0)"><span>导出Excel</span></a>
			</pop:JugePermissionTag>
		</div>
		<pop:JugePermissionTag ename="isEmphasisRentalHouse,cancelIsEmphasis">
		<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
 			<pop:JugePermissionTag ename="isEmphasisRentalHouse">
			<a id="isEmphasisRentalHouse" href="javascript:void(0)"><span>注销</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="cancelIsEmphasis">
			<a id="cancelIsEmphasis" href="javascript:void(0)"><span>取消注销</span></a>
			</pop:JugePermissionTag>
		</div>
		<a id="mantanceRentalHousePopulation" href="javascript:void(0)"><span>住户管理</span></a>
		<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<a id="superviseServiceTeamMember" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理治安负责人</span></a>
		</pop:JugePermissionTag>	
		<pop:JugePermissionTag ename="serviceRecordManagement">
			<a id="superviseRecord" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理巡场情况</span></a>
		</pop:JugePermissionTag>
		<a id="percentBtn" class="nav-dropdownBtn persentArea">
			<span id="rentalHousePeopleProportionSpan"></span>
			<b class="nav-dropdownBtn-arr">
				<b class="nav-ico-dArr"></b>
			</b>
			<div id="persetDown" class="persetDown hidden">
				<div class="linkArea"></div>
				<ul>
					<li>人房关联比例：<strong id="rentalHousePeopleProportion"></strong></li>
				</ul>
			</div>
		</a>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
<%--  		<pop:JugePermissionTag ename="transferRentalHouse">  --%>
<%-- 		<a id="transfer" href="javascript:void(0)"><span>转移</span></a> --%>
<%-- 		</pop:JugePermissionTag> --%>
		
	</div>
	<div style="clear: both;"></div>
	<div style="width:100%;" class="click_load">
		<a href="javascript:;" class="click_btn">点击加载数据</a>
		<table id="rentalHouseList"></table>
		<div id="rentalHouseListPager"></div>
	</div>
	<div id="moveDataDialog"></div>
	<div id="rentalHouseMaintanceDialog"></div>
	<div id="noticeDialog"></div>
	<div id="rentalHouseDialog"></div>
	<div id="personInChargeDialog"></div>
	<div id="floorpersonDialog"></div>
	<div id="actualAppendPopulationDialog"></div>
	<div id="scanHeaderImage"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="rentalHouseManagement">
	<span id="title"><s:property value="#request.name"/></span>
	</pop:JugePermissionTag>
</div>

<!-- 获取建筑物用途的DomainName -->
<input id="buildingUsesDomainName" type="hidden" value="<s:property value='@com.tianque.domain.property.PropertyTypes@BUILDING_USES'/>">
<!-- 获取建筑物用途的住宅displayName -->
<input id="buildingUsesDisplayName" type="hidden" value="<s:property value='@com.tianque.baseInfo.companyPlace.constacts.ModulTypes@RESIDENTIAL'/>">
<!-- 获取实有房屋类别 -->
<input id="rentalhouseType" type="hidden" value="<s:property value='@com.tianque.core.util.NewBaseInfoTables@RENTALHOUSE_KEY'/>">
<div id="rentalhousePeopleDlg"></div>
<script type="text/javascript">
<pop:JugePermissionTag ename="rentalHouseLeaderView">
</pop:JugePermissionTag>
<pop:formatterProperty name="usage" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" />
<pop:formatterProperty name="hiddenDangerLevel" domainName="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" />
var dialogWidth=760;
var dialogHeight=510;
var notExecute=new Array();
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
			"houseType":$("#rentalhouseType").val()
		},  
		dataType: "json", 
		success:function(data){
			$("#rentalHousePeopleProportionSpan").text(data);
			$("#rentalHousePeopleProportion").text(data);
		}
	});
}

function updateOperator(event,selectedId){
	var houseInfo =  $("#rentalHouseList").getRowData(selectedId);
	if(houseInfo.isEmphasis==true||houseInfo.isEmphasis=='true'){
		 $.messageBox({level : 'warn',message:"该出租房信息已经注销，不能修改!"});
		 return;
	}
	$("#rentalHouseMaintanceDialog").createTabDialog({
		postData:{
			id : houseInfo.encryptId,
			mode:'edit',
			type:'RENTALHOUSE',
			operateSource:'rentalHouse'
		},
		title:'修改出租房',
		width: 800,
		height: 600,
		tabs:[
			{title:'房屋信息',url:PATH+'/baseinfo/actualHouseManage/prepareDispatchByEncrypt.action?dailogName=rentalHouseMaintanceDialog&mode=edit&houseId='+houseInfo.encryptId},
			{title:'出租房',url:PATH+'/baseinfo/rentalHouseManage/prepareDispatchByEncrypt.action?dailogName=rentalHouseMaintanceDialog&mode=edit&houseId='+houseInfo.encryptId}
		],
		close : function(){
			$("#rentalHouseList").trigger("reloadGrid");
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
function deleteOperator(event,allValue){
	if(allValue==null){
		$.messageBox({level:'warn',message:'没有选中任何记录！'});
		return ;
	}
	var encryptIds=deleteOperatorByEncrypt("rentalHouse",allValue,"encryptId");
				$.confirm({
					title:"确认删除",
					message:"确定要删除吗?一经删除将无法恢复",
					okFunc: function(){
						$.ajax({
							url:PATH+"/baseinfo/rentalHouseManage/deleteHouseInfo.action",
							type:"post",
							data:{
								"houseIds":encryptIds
							},
							success:function(data){
								$("#rentalHouseList").trigger("reloadGrid");
							    $.messageBox({message:"已经成功删除出租房信息!"});
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
	printTitleStr='出租房';
	var houseInfo = $("#rentalHouseList").getRowData(rowid);
	$("#rentalHouseMaintanceDialog").createDialog({
		width: 800,
		height: 560,
		title:'打印出租房信息',
		modal : true,
		url:PATH+'/baseinfo/rentalHouseManage/dispatchOperate.action?mode=print&houseInfo.id='+rowid,
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
	var houseInfo =  $("#rentalHouseList").getRowData(rowid);
	$("#actualHouseMaintanceDialog").createDialog({
		width: 820,
		height: 530,
		title:'查看居住人员信息',
		modal : true,
		url:PATH+'/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt.action?houseInfo.organization.id='+houseInfo['organization.id']+'&houseInfo.id='+houseInfo.houseInfoEncryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function callback(){
    var dfop = {
       	hasViewRentalHouse:'<pop:JugePermissionTag ename="viewRentalHouse">true</pop:JugePermissionTag>',
       	hasUpdateRentalHouse:'<pop:JugePermissionTag ename="updateRentalHouse">true</pop:JugePermissionTag>',
       	hasDeleteRentalHouse:'<pop:JugePermissionTag ename="deleteRentalHouse">true</pop:JugePermissionTag>',
       	moduleCName:'${moduleCName}'
    }
    TQ.rentalHouseList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/baseinfo/houseInfo/rentalhouse/rentalHouseList.js',
    callback: callback
})
</script>