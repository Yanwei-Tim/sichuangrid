<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="OverseaPerson" name="moduleName"/>
</jsp:include>

<div class="content">
	<div class="ui-corner-all" id="nav">
        <div class="btnbanner btnbannerData">
        	<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
	            <input class="basic-input" type="text" value="请输入英文名或证件号码" name="searchByCondition" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入英文名或证件号码':this.value;" onfocus="value=(this.value=='请输入英文名或证件号码')?'':this.value;"/>
	            <button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
            <a href="javascript:;" id="fastSearch"><span>搜索</span></a>
        </div>

   		<pop:JugePermissionTag ename="searchOverseaPerson">
			<a id="search"  href="javascript:void(0)"><span>高级搜索</span></a>
   		</pop:JugePermissionTag>
        <span class="lineBetween firstDOM"></span>
   		<pop:JugePermissionTag ename="addOverseaPerson">
			<a id="add" href="javascript:;"><span>新增</span></a>
   		</pop:JugePermissionTag>
   		<pop:JugePermissionTag ename="deleteOverseaPerson">
			<a id="delete" href="javascript:void(0)"><span>批量删除</span></a>
   		</pop:JugePermissionTag>
   		
   		<pop:JugePermissionTag ename="importOverseaPerson,downOverseaPerson">
		<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</pop:JugePermissionTag>
		<div class="nav-sub-buttons">
	   		<pop:JugePermissionTag ename="importOverseaPerson">
				<a id="import" href="javascript:void(0)"><span>Excel导入</span></a>
	   		</pop:JugePermissionTag>
	   		<pop:JugePermissionTag ename="downOverseaPerson">
				<a id="export" href="javascript:void(0)"><span>导出Excel</span></a>
	   		</pop:JugePermissionTag>
   		</div>
   		<pop:JugePermissionTag ename="abolishOverseaPerson,anewOverseaPerson">
	   			<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
	   		</pop:JugePermissionTag>
		
		<div class="nav-sub-buttons">
	   		<pop:JugePermissionTag ename="abolishOverseaPerson">
	   			<a id="logOut" href="javascript:void(0)"><span>注销</span></a>
	   		</pop:JugePermissionTag>
	   		<pop:JugePermissionTag ename="anewOverseaPerson">
	   			<a id="cancelLogOut" href="javascript:void(0)"><span>取消注销</span></a>
	   		</pop:JugePermissionTag>
	   	</div>
   		<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>
   		
  		<pop:JugePermissionTag ename="transferOverseaPerson"> 
		<a id="transfer"  href="javascript:void(0)"><span>转移</span></a> 
		</pop:JugePermissionTag> 
		
    </div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="overseaPersonnelList"> </table>
		<div id="overseaPersonnelListPager"></div>
	</div>
	<div id="overseaPersonnelMaintanceDialog"></div>
	<div id="overseaPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
</div>

<div style="display: none;">
	<span id="title">境外人员</span>
</div>
<script type="text/javascript">

var dialogWidth=800;
var dialogHeight=600;
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="certificateType" domainName="@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE" />
<pop:formatterProperty name="profession" domainName="@com.tianque.domain.property.PropertyTypes@PROFESSION_TYPE" />
var token = "<s:property value='@com.tianque.core.util.CreateTokenUtil@getToken()'/>";
var isgridBol = false;
var title=$("#title").html();
var notExecute=new Array();
function onOrgChanged(orgId, isgrid){
	isgridBol = isgrid;
	$("#overseaPersonnelList").setGridParam({
		url:'${path}/baseinfo/overseaPersonnelManage/overseaPersonnelList.action',
		datatype: "json",
		page:1
	});
	$("#overseaPersonnelList").setPostData({
    	"orgId":orgId,
    	"overseaPersonnel.logOut":0
   	});
	$("#overseaPersonnelList").trigger("reloadGrid");
}
function updateOperator(event,selectedId){
	var ent =  $("#overseaPersonnelList").getRowData(selectedId);
	if(ent.logOut==1||ent.logOut=='1'){
		 $.messageBox({level : 'warn',message:"该境外人员信息已经注销，不能修改!"});
		 return;
	}
	$("#overseaPersonnelMaintanceDialog").createTabDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:"修改境外人员",
		postData:{
			id : ent.encryptId,
			mode:'edit',
			imageType:"population",
			type:"overseaStaff"
		},
		tabs:[
		   {title:'基本信息',url:'/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?dailogName=overseaPersonnelMaintanceDialog'}
		   //{title:'住房信息',url:'/baseinfo/houseInfoForPopulation/dispathHouseInfoForPopulation.action?dailogName=overseaPersonnelMaintanceDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>'}
		   <pop:JugePermissionTag ename="groupFamilyInfo">
		   ,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?dailogName=overseaPersonnelMaintanceDialog&population.id='+ent.encryptId+'&populationType=<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>'}
		   </pop:JugePermissionTag>
		],
		close : function(){
			$("#overseaPersonnelList").trigger("reloadGrid");
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
	var str="确定要删除吗?一经删除将无法恢复";
	var encryptIds = deleteOperatorByEncrypt("overseaPersonnel",value,"encryptId");
	$.confirm({
			title:"确认删除",
			message:str,
			okFunc: function() {
				$.dialogLoading("open");
				$.ajax({
					url:"${path}/baseinfo/overseaPersonnelManage/deleteOverseaPersonnel.action",
					type:"post",
					data:{
						"struts.token":token,
						"deleteIds":encryptIds
					},
					success:function(data){
						$.dialogLoading("close");
						if(data!=null&&data!=true&&data!='true'){
							  $.messageBox({message:data,level:"error"});
							  return;
						}
						try{for(var ids=0;ids<value.toString().split(',').length;ids++){
							$("#overseaPersonnelList").delRowData(value.toString().split(',')[ids]);
						}}catch(e){}
						$("#overseaPersonnelList").delRowData(value);
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
	var overseaPersonnel =  $("#overseaPersonnelList").getRowData(rowid);
	$("#overseaPersonnelMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'打印'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=print&isHiddenPersonnelTrack=1&id='+overseaPersonnel.encryptId,
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
	var overseaPersonnel =  $("#overseaPersonnelList").getRowData(rowid);
	$("#overseaPersonnelMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看'+title+'信息',
		url:'${path}/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=view&isHiddenPersonnelTrack=1&id='+overseaPersonnel.encryptId,
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

//a 按钮不可用
function disableButtons(){

}
//b 检查导出按钮是否应该可用
function checkExport(){

}

function callback(){
    var dfop = {
    	templates: '<s:property value="@com.tianque.datatransfer.ImportTemplatesSetting@OVERSEAPERSONNEL_KEY"/>',
    	hasViewOverseaPerson: '<pop:JugePermissionTag ename="viewOverseaPerson">true</pop:JugePermissionTag>',
       	addTabs: [
			   {title:'基本信息',url:'/baseinfo/overseaPersonnelManage/dispatch.action?dailogName=overseaPersonnelMaintanceDialog'}
			   //{title:'住房信息',url:'/baseinfo/houseInfoForPopulation/dispathHouseInfoForPopulation.action?dailogName=overseaPersonnelMaintanceDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>'}
			   <pop:JugePermissionTag ename="groupFamilyInfo">
			   ,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?dailogName=overseaPersonnelMaintanceDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>'}
			   </pop:JugePermissionTag>
			]
    }
    TQ.overseaPersonnelListFunction(dfop)
}
$.cacheScript({
	url:'/resource/getScript/baseinfo/overseaPersonnel/overseaPersonnelList.js',
	callback: callback
})

</script>

