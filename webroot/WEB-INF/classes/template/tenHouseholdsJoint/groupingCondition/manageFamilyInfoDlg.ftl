<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content">
<input type="hidden" id="teamId" value="${selectedId}"/>
<input type="hidden" id="organizationId" value="${organizationId}"/>
<div class="btnbanner btnbannerData">
<div class="ui-widget autosearch">
	    <input class="basic-input" type="text" value="请输入用户姓名" name="searchText" id="searchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入用户姓名':this.value;" onfocus="value=(this.value=='请输入用户姓名')?'':this.value;"/>
		<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
</div>
	<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
	 <span class="lineBetween "></span>
		<@pop.JugePermissionTag ename='addFamilyInfo'>
			<a id="addFamilyInfo"  href="javascript:void(0)"><span>新增</span></a>
		</@pop.JugePermissionTag>
		<!--<@pop.JugePermissionTag ename='updateFamilyInfo'>
			<a id="updateFamilyInfo"  href="javascript:void(0)"><span>修改</span></a>
		</@pop.JugePermissionTag>-->
		<@pop.JugePermissionTag ename='deleteFamilyInfo'>
			<a id="deleteFamilyInfo"  href="javascript:void(0)"><span>删除</span></a>
		</@pop.JugePermissionTag>
		<a  href="javascript:void(0)" id="refreshFamily"><span>刷新</span></a>
	</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="manageFamilyInfoList"></table>
			<div id="manageFamilyInfoListPager"></div>
		</div>
		<div id="manageFamilyInfoDialog"></div>
</div>
<script type="text/javascript">
<@pop.formatterProperty name="alarmCenter" domainName="@com.tianque.domain.property.PropertyTypes@ALARMCENTER" />
<@pop.formatterProperty name="policeRoom" domainName="@com.tianque.domain.property.PropertyTypes@POLICEROOM" />

$("#importFamilyInfo").click(function(){

});
$(document).ready(function(){
	var orgId=$("#organizationId").val();
	$("#manageFamilyInfoList").jqGridFunction({
		url:'${path}/tenHouseholdsJoint/familyCondition/searchFamilyInByCondition.action',
		datatype: "json",
		width:800,
		height:350,
		postData:{
			"familyInfo.organization.id":$("#organizationId").val(),
			"familyInfo.teamId":$("#teamId").val(),
			"familyInfo.logOut":0
		},
	   	colModel:[
			{name:"id", index:"id", hidden:true,frozen:true},
			{name:'name',index:'name',label:'用户姓名',sortable:true,width:100},
			{name:'certificateNumber',index:'certificateNumber',sortable:true,label:'用户证件号',width:140},
			{name:'helpLine',index:'helpLine',label:'求助电话',sortable:true,width:120},
			{name:'familyAddress',index:'familyAddress',label:'住址',sortable:true,width:120},
			{name:'organization.orgName',index:'orgId',label:'所属区域',sortable:true,width:120},
			{name:'alarmCenter',index:'alarmCenter',label:'所属接警中心',sortable:true,width:120,formatter:alarmCenterFormatter},
			{name:'teamName',index:'teamName',label:'所属分组',sortable:true,width:120}
		],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:true,
		ondblClickRow: doubleClickTable,
		onSelectRow:function(){}
	});
	
	
	$("#addFamilyInfo").click(function(){
	var orgId;
	if($("#organizationId").val()!=null){
		orgId = $("#organizationId").val();
	}else{
		$.messageBox({level:"warn",message:"新增失败，没有获得对应分组的组织信息"});
		 return;
	}
		$("#manageFamilyInfoDialog").createDialog({
				width: 900,
				height: 600,
				title: '新增用户',
				url:'${path}/tenHouseholdsJoint/familyCondition/dispatch.action?mode=add&operateSource=familyTeam&organizationId='+ orgId+'&familyInfo.organization.id'+$("#organizationId").val()+'&teamId='+$("#teamId").val(),
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
	
	
})

$("#updateFamilyInfo").click(function(e){
	var selectedId = $("#manageFamilyInfoList").jqGrid("getGridParam", "selarrrow");
	if(selectedId==null||selectedId.length>1){
	 $.messageBox({level:"warn",message:"请选择一条记录，再进行修改！"});
		 return;
	}
	updateOperator(event,selectedId);
});

$("#refreshFamily").click(function(){
		var initParam = {
			"familyInfo.teamId": $("#teamId").val(),
			"familyInfo.logOut":0,
			"familyInfo.organization.id":$("#organizationId").val()
		}
		 $("#manageFamilyInfoList").setGridParam({
			url:"${path}/tenHouseholdsJoint/familyCondition/searchFamilyInByCondition.action",
			datatype: "json",
			page:1
			 });
		 $("#manageFamilyInfoList").setPostData(initParam);
		 $("#manageFamilyInfoList").trigger("reloadGrid");
		 $("#searchText").attr("value","请输入用户姓名");
});

$("#refreshSearchKey").click(function(event){
		$("#searchText").attr("value","请输入用户姓名");
});

$("#fastSearchButton").click(function(){
	var conditions = $("#searchText").val();
		if(conditions == '请输入用户姓名') return;
		var initParam = {
			"familyInfo.teamId": $("#teamId").val(),
			"familyInfo.logOut":0,
			"familyInfo.name":conditions,
			"familyInfo.organization.id":$("#organizationId").val()
		}
	
		 $("#manageFamilyInfoList").setGridParam({
			url:"${path}/tenHouseholdsJoint/familyCondition/searchFamilyInByCondition.action",
			datatype: "json",
			page:1
			 });
		 $("#manageFamilyInfoList").setPostData(initParam);
		 $("#manageFamilyInfoList").trigger("reloadGrid");
});
	
$("#deleteFamilyInfo").click(function(e){
	var selectedId = $("#manageFamilyInfoList").jqGrid("getGridParam", "selarrrow");
	if(selectedId==null || selectedId.length==0){
		 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
		 return;
	}
	deleteOperator(event,selectedId);
});


function updateOperator(event,selectedId){
	if(selectedId==null || selectedId.length==0){
		 $.messageBox({level:"warn",message:"请选择一条数据进行操作"});
		 return;
	}else if(selectedId.length>1){
		$.messageBox({level:"warn",message:"只能选择一条数据进行操作"});
		 return;
	}
	var builddata=$("#manageFamilyInfoList").getRowData(selectedId);
	$("#manageFamilyInfoDialog").createDialog({
		width: 900,
		height:600,
		title: '修改用户',
		url:'${path}/tenHouseholdsJoint/familyCondition/dispatch.action?mode=edit&organizationId='+ $("#organizationId").val()+'&id='+selectedId,
		buttons: {
			"保存" : function(){
				$("#maintainForm").submit();
				$(this).dialog("close");
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
	$.confirm({
		title:"确认删除该用户",
		message:"该用户删除后，将不可恢复，您确定要删除该用户吗？",
		width: 400,
		okFunc: function(){deleteFamilyInfo(selectedId);}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}

function deleteFamilyInfo(allValue) {
	$.ajax({
		url :'${path}/tenHouseholdsJoint/familyCondition/deleteFamilyInfo.action',
		type: "post",
		data:{
			"idList":allValue+""
		},
		success : function(data) {
			if (data ==true || data=='true' ) {
				$("#manageFamilyInfoList").trigger("reloadGrid");
				$.messageBox({
					message : "删除用户资料成功！"
				});
			} else {
				$.messageBox({
					message : data,
					lever : "error"
				});
				return;
			}
		}
	});
}
</script>