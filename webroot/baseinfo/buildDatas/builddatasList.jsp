<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-corner-all" id="nav">
	<div>
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
		<div class="userState">
			<select id="isBind" class="basic-input">
				<option value="0" selected="selected">全部</option>
				<option value="1" >已绑定</option>
					<option value="2">未绑定</option>
			</select>
		</div>
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
					<input class="basic-input" type="text" value="请输入楼宇名称或地址" id="searchByCondition" maxlength="18" class="searchtxt" style="width:180px;" onblur="value=(this.value=='')?'请输入楼宇名称或地址':this.value;" onfocus="value=(this.value=='请输入楼宇名称或地址')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<pop:JugePermissionTag ename="searchBuilddatasByNameAndAddress">
			<a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchBuilddatas">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span> </a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addBuilddatas">
			<a id="add" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong>新增</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteBuilddatas">
			<a id="delete" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong>批量删除</span> </a>
		</pop:JugePermissionTag>
		<!--
		<pop:JugePermissionTag ename="updateBuilddatas">
			<a id="update" href="javascript:void(0)"><span><strong
					class="ui-ico-xg"></strong>修改</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewBuilddatas">
			<a id="view" href="javascript:void(0)"><span><strong
					class="ui-ico-cakan"></strong>查看</span> </a>
		</pop:JugePermissionTag>
		-->
		<pop:JugePermissionTag ename="importBuilddatas">
				<a id="importBuilddatas" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>Excel导入</span></a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="houseManage">
			<a id="house" href="javascript:void(0)"><span><strong
					class="ui-ico-cx"></strong>住房管理</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="generatedLayout">
			<a id="layout" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>布局管理</span></a>
		</pop:JugePermissionTag>

	</div>
</div>
<div style="clear: both;"></div>
<div style="width: 100%">
	<table id="builddatasList">
	</table>
	<div id="builddatasListPager"></div>
</div>
<div id="builddatasMaintanceDialog"></div>
<div id="builddatasDialog"></div>
<div id="gisBuilddatasDialog"></div>
<div id="generatedLayoutDialog"></div>
<div id="buildLayoutDialog"></div>
<div id="HouseMaintanceDialog"></div>
<div id="bindhouseDialog"></div>
<div id="bindMapDialog"></div>
<div id="noticeDialog"></div>
<div style="display: none;">
	<pop:JugePermissionTag ename="BuilddatasManagement">
		<span id="title"><s:property value="#request.name" /> </span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
<pop:formatterProperty name="buildingstructures" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@BUILDDATAS_TYPE" />
var dialogWidth=700;
var dialogHeight=480;
//var title=$("#title").html();
var title="楼宇信息";
var bindid;
var gisType="2D";
var useOrg = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getDepartmentNo()'/>";
var bindDialog;

function onOrgChanged(org) {
		$("#builddatasList").setGridParam(
			{
				url :PATH+'/builddatasManage/findBuilddatasByOrgInternalCode.action',
				datatype : "json",
				page : 1
			});
		$("#builddatasList").setPostData({
			"isBind":$("#isBind").val(),
			"organization.id" : getCurrentOrgId()
		});
		$("#builddatasList").trigger("reloadGrid");
	}

function updateOperator(event,selectedId){
	if(selectedId==null){
		return;
	}
	var builddata=$("#builddatasList").getRowData(selectedId);
	$("#builddatasMaintanceDialog").createDialog({
		width: 600,
		height: 320,
		title: '修改'+title,
		url:PATH+'/builddatasManage/dispatchByEncrypt.action?mode=edit&flag=true&builddatas.id=' + builddata.encryptId+"&gisType="+gisType,
		buttons: {
			"保存" : function(){
				$("#maintainForm").submit();
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

function deleteOperator(event,selectedId){
	if(selectedId==null){
		$.messageBox({level:'warn',message:'没有选中任何记录！'});
		return ;
	}
	var encryptIds=deleteOperatorByEncrypt("builddatas",selectedId,"encryptId");
	$.confirm({
		title:"确认删除该"+title,
		message:"该"+title+"楼宇内的住房将变为无房，无法恢复"+title+"吗？",
		width: 400,
		okFunc: function(){deleteBuilddatas(encryptIds);}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}

var printLoadUrlStr='';
function printByUrl(urlStr){
	printLoadUrlStr=urlStr;
	printTitleStr=title;
	$("#builddatasMaintanceDialog").createDialog({
		width : 780,
		height : 380,
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
function generatedLayout(layoutUrl,titleStr,selectedId){
	$("#buildLayoutDialog").createDialog({
        width: 960,
        height: 600,
        title:'布局管理',
        url:layoutUrl,
        buttons: {
      	  "删除" : function(){
				$.confirm({
					title:"确认删除该楼宇布局",
					message:"该楼宇布局删除后，将不可恢复，您确定要删除该楼宇布局吗？",
					width: 400,
					okFunc: function(){deleteLayout(selectedId);}
				});
			 },
           "关闭" : function(){
              $(this).dialog("close");
              $("#builddatasList").setSelection(selectedId,true);
           }
        },
        shouldEmptyHtml:false
   });
}
function deleteLayout(buildingId){
	$.ajax({
		url:PATH+"/builddatasManage/deleteLayoutAndUpdateBuilddatasByBuildingId.action?builddatas.id="+buildingId,
		data:{
			
		},
		success:function(data){
			if(data==null || (data!=true && data!="true")){
	        	$.messageBox({
					message:"删除布局失败",
					level: "error"
				 });
	            return;
			}
			$("#buildLayoutDialog").dialog("close");
			$.messageBox({message:"删除楼宇布局成功"});
			onOrgChanged(getCurrentOrgId());
		}
	});
}
function callback(){
    var dfop = {
       	hasViewBuilddatas: '<pop:JugePermissionTag ename="viewBuilddatas">true</pop:JugePermissionTag>',
       	hasUpdateBuilddatas:'<pop:JugePermissionTag ename="updateBuilddatas">true</pop:JugePermissionTag>',
       	hasDeleteBuilddatas:'<pop:JugePermissionTag ename="deleteBuilddatas">true</pop:JugePermissionTag>',
       	importDataType:'<s:property value="@com.tianque.datatransfer.DataTransferConstants@BUILDDATAS_DATA"/>',
       	importTemplates:'<s:property value="@com.tianque.datatransfer.ImportTemplatesSetting@BUILDDATAS_KEY"/>'
    }
    TQ.builddatasList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/baseinfo/buildDatas/builddatasList.js',
    callback: callback
})

</script>