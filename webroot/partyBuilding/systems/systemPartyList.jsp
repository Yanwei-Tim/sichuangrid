<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入党组织名称" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入党组织名称':this.value;" onfocus="value=(this.value=='请输入党组织名称')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="add${param.type }">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="update${param.type }">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="delete${param.type }">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="view${param.type }">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="manage${param.type }Member">
			<a id="manageSystemPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护成员</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="systemPartyList"></table>
		<div id="systemPartyListPager"></div>
	</div>
	<div id="systemPartyDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 700;
var dialogHeight = 400;

function formatterTitle() {
	if('${param.partyOrgType}'=='<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@BUSINESS_ORG"/>') {
		return "事业单位党组织";
	} else if('${param.partyOrgType}'=='<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@COLLECTIVE_ORG"/>') {
		return "国有(集体党组织)";
	} else if('${param.partyOrgType}'=='<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@TWO_NEW_PARTY"/>') {
		return "两新组织党组织";
	}
	return "";
}

function loadSystemPartyData(conditions) {
	var initParam = {
			"systemParty.partyOrgType":'${param.partyOrgType}'
		}
	if(typeof(conditions) == 'undefined'||conditions==null){
	} else {
		$.extend(initParam,{
			"systemParty.partyName":conditions
		})
	}
	
	$("#systemPartyList").setGridParam({
		url:"${path}/partyBuildng/systemPartyManage/findSystemPartysForPageByCondition.action",
		datatype: "json",
		page:1
	});
	$("#systemPartyList").setPostData(initParam);
	$("#systemPartyList").trigger("reloadGrid");
}

function search(){
	var conditions = $("#searchText").val();
	if(conditions == '请输入党组织名称') return;
	loadSystemPartyData(conditions);
}

function nameFormatter(el, options, rowData){
	var contact=rowData.contact
	if(typeof(contact) == 'undefined'||contact==null)
		contact="";
	return "<a href='javascript:viewOperator("+rowData.id+")'><U><font color='#6633FF'>"+contact+"</font></U></a>";
}

$(function(){
	$("#systemPartyList").jqGridFunction({
		datatype: "local",
		height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-100,
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	        {name:"partyName",sortable:true,label:'组织名称',width:150},
	        {name:"contact",sortable:true,label:'联系人',width:100,formatter:nameFormatter},
	        {name:"telephone",sortable:true,label:'联系电话',width:100},
	        {name:"mobile",sortable:false,label:'联系手机',width:180},
	        {name:"partyMemeberSum",sortable:false,label:'党员数',width:100},
	        {name:"remark",sortable:true,label:'备注',width:345}
		],
		multiselect: true,
	    ondblClickRow: viewOperator
	});
	loadSystemPartyData();
	
	$("#fastSearchButton").click(function(){
		search();
	});
	$("#searchText").focus(function(){
        this.select();
	 });
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","请输入党组织名称");
	});
	
	$("#add").click(function(){
		$("#systemPartyDialog").createDialog({
			title:"新增"+formatterTitle()+"信息",
			width: dialogWidth,
			height: dialogHeight,
			url:"${path}/partyBuildng/systemPartyManage/dispatch.action?mode=add&systemParty.partyOrgType=${param.partyOrgType}",
			buttons: {
	    		   "保存" : function(){
	    		        $("#maintainForm").submit(); 
	    		   },
	    		   "关闭" : function(){
	    		        $(this).dialog("close"); 
	    		   }
	    		}
		});
	});
	
	$("#update").click(function(){
		var selectedIds = $("#systemPartyList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"}); 
			return;
		}
		updateOperator(selectedId);
	});
	
	$("#delete").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		var partyOrgArray="";
		for(var i=0;i<allValue.length;i++){
			var rowData = $("#systemPartyList").getRowData(allValue[i]);
			partyOrgArray = partyOrgArray+rowData.partyName;
			if(i!=allValue.length-1){
				partyOrgArray=partyOrgArray+",";
			}
		}
		$.ajax({
			async : true,
			url : "${path}/partyBuildng/memberManage/exsistedMember.action",
			data: {"orgId":getCurrentOrgId(),"partyOrg":partyOrgArray},
			success : function(data) {
				if(data>0){
					$.messageBox({level:'warn',message:"您选择的组织下已经存在党员，不能删除！"});
					 return;
				}
				deleteOperator(allValue);
			}
		 });
	});
	
	$("#view").click(function(){
		if($("#view").attr("disabled")){
			return ;
		}
		var selectedIds = $("#systemPartyList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		viewOperator(selectedId);
	});
	
	$("#manageSystemPartyMember").click(function(event){
		var selectedIds = $("#systemPartyList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行党员维护！"});
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行党员维护！"});
	 		return;
		}
		var rowData = $("#systemPartyList").getRowData(selectedId);
		$("#systemPartyDialog").createDialog({
			width:950,
			height:580,
			title:"维护"+formatterTitle()+"党员",
 	 		url:"${path}/partyBuilding/members/memberForTownList.jsp?partyModule=${param.type}&partyOrgType=${param.partyOrgType}&partyOrg="+encodeURI(encodeURI(rowData.partyName)),
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			},
			close:function(){
				$("#systemPartyList").trigger("reloadGrid");
			}
		});
	});
	
	$("#reload").click(function(){
		$("#searchTextSystemParty").attr("value","请输入党组织名称");
		loadSystemPartyData();
	});
});

function viewOperator(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#systemPartyList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#systemPartyDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:"查看"+formatterTitle()+"信息",
 		url:'${path}/partyBuildng/systemPartyManage/dispatchByEncrypt.action?mode=view&systemParty.partyOrgType=${param.partyOrgType}&systemParty.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function updateOperator(selectedId){
	if(selectedId==null){
		return ;
	}
	var ent = $("#systemPartyList").getRowData(selectedId);
	$("#systemPartyDialog").createDialog({
		title:"修改"+formatterTitle()+"信息",
		width: dialogWidth,
		height: dialogHeight,
		url:"${path}/partyBuildng/systemPartyManage/dispatchByEncrypt.action?mode=edit&systemParty.partyOrgType=${param.partyOrgType}&systemParty.id="+ent.encryptId,
		buttons: {
    		   "保存" : function(){
    		        $("#maintainForm").submit(); 
    		   },
    		   "关闭" : function(){
    		        $(this).dialog("close"); 
    		   }
    		}
	});
}

function deleteOperator(allValue){
	if(allValue==null){
		return ;
	}
	var encryptIds=deleteOperatorByEncrypt("systemParty",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'${path}/partyBuildng/systemPartyManage/deleteSystemPartyByIdsByEncrypt.action',
				type:'post',
				data:{
					"systemParty.partyOrgType":${param.partyOrgType},
					"ids":encryptIds
					},
				success:function(data){
					if(data!=null) {
						$.messageBox({message:"已经成功删除该"+formatterTitle()+"信息!"});
						$("#systemPartyList").trigger("reloadGrid");
					} else {
						$.messageBox({message:data,level:"error"});
					}
				}
			});
		}
	});
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#systemPartyList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}

function getSelectedIds(){
	var selectedIds = $("#systemPartyList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
</script>