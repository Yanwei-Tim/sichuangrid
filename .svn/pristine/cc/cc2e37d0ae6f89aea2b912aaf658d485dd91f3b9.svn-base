<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
		<@s.include value="/common/orgSelectedComponent.jsp"/>	
		<span class="lineBetween "></span>
		<@pop.JugePermissionTag ename='addFamilyInfo'>
			<a id="addFamilyInfo"  href="javascript:void(0)"><span>新增</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename='searchFamilyInfo'>
			<a id="searchFamilyInfo"  href="javascript:void(0)"><span>查询</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename='deleteFamilyInfo'>
			<a id="deleteFamilyInfo"  href="javascript:void(0)"><span>删除</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename='importFamilyInfo'>
			<a id="importFamilyInfo"  href="javascript:void(0)"><span>导入</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename='logoutFamilyInfo,cancelLogoutFamilyInfo'>
			<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</@pop.JugePermissionTag>
		<div class="nav-sub-buttons">
		<@pop.JugePermissionTag ename='logoutFamilyInfo'>
			<a id="logoutFamilyInfo"  href="javascript:void(0)"><span>注销</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename='cancelLogoutFamilyInfo'>
			<a id="cancelLogoutFamilyInfo"  href="javascript:void(0)"><span>取消注销</span></a>
		</@pop.JugePermissionTag>
		</div>
		<a id="refresh"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="familyInfoList"></table>
			<div id="familyInfoListPager"></div>
		</div>
		<div id="familyInfoDialog"></div>
		<div id="importFamilyInfoDialog"></div>
		<div id="searchFamilyInfoDialog"></div>
</div>
<script type="text/javascript">
<@pop.formatterProperty name="alarmCenter" domainName="@com.tianque.domain.property.PropertyTypes@ALARMCENTER" />
<@pop.formatterProperty name="policeRoom" domainName="@com.tianque.domain.property.PropertyTypes@POLICEROOM" />

$("#importFamilyInfo").click(function(){

});
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateFamilyInfo'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteFamilyInfo'><a href='javascript:;' onclick='deleteOperator(event,"+(rowData.id)+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFormatter(el,options,rowData){
	var logOut = rowData.logOut;
	if(logOut=''||logOut == 0 || logOut =='0'){
		return "<@pop.JugePermissionTag ename='viewFamilyInfo'><a href='javascript:;' onclick='viewFamilyInfo("+rowData.id+");'><span>"+rowData.name+"</span></a></@pop.JugePermissionTag>";
	}else{
		return "<@pop.JugePermissionTag ename='viewFamilyInfo'><a href='javascript:;' onclick='viewFamilyInfo("+rowData.id+");' style='color:#888888;' ><span>"+rowData.name+"</span></a></@pop.JugePermissionTag>";
	}
}
var orgId=getCurrentOrgId();
$(document).ready(function(){
	$("#familyInfoList").jqGridFunction({
		url:'${path}/tenHouseholdsJoint/familyCondition/queryFamilyInfoList.action',
		datatype: "json",
		postData:{
			"organizationId":orgId
		},
	   	colModel:[
			{name:"id", index:"id", hidden:true,frozen:true},
			{name:"operation",label:'操作',formatter:operatorFormatter,frozen:true,width:90,sortable:false,align:"center"},
			{name:'name',index:'name',label:'用户姓名',formatter:nameFormatter,sortable:true,width:100},
			{name:'certificateNumber',index:'certificateNumber',sortable:true,label:'用户证件号',width:140},
			{name:'helpLine',index:'helpLine',label:'求助电话',sortable:true,width:120},
			{name:'familyAddress',index:'familyAddress',label:'住址',sortable:true,width:120},
			{name:'organization.orgName',index:'orgId',label:'所属区域',sortable:true,width:120},
			{name:'alarmCenter',index:'alarmCenter',label:'所属接警中心',sortable:true,width:120,formatter:alarmCenterFormatter},
			{name:'teamName',index:'teamName',label:'所属分组',sortable:true,width:120},
			{name:'logOut',index:'logOut',label:'是否关注',hidden:true,sortable:true,width:120}
		],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:true,
		<@pop.JugePermissionTag ename="viewFamilyInfo">
		ondblClickRow:viewFamilyInfo,
		</@pop.JugePermissionTag>
		onSelectRow:function(){}
	});
	
	$("#logoutFamilyInfo").click(function(){
		if($(this).attr("disabled")=="disabled"){
			return;
		}
		var selectedId = $("#familyInfoList").jqGrid("getGridParam", "selarrrow");
		var logoutId=new Array();
		var temp=new Array();
		if(selectedId==null || selectedId.length==0){
			 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行注销！"});
			 return;
		}
		for(var i=0;i<selectedId.length;i++){
			var rowData = $("#familyInfoList").getRowData(selectedId[i]);
			if(rowData.logOut==''||rowData.logOut==0 || rowData.logOut=="0"){
				logoutId.push(selectedId[i]);
				}else{
					temp.push(selectedId[i]);
					}
			}
		selectedId=logoutId;
		if(selectedId==null||selectedId.length==0){
			$.messageBox({level:'warn',message:"没有可注销的数据！"});
			return;
		}
		$.confirm({
			title:"确认注销",
			message:"您确定要注销该用户吗？",
			width: 400,
			okFunc: function(){
				$.ajax({
					url :'${path}/tenHouseholdsJoint/familyCondition/updateFamilyLogout.action',
					type: "post",
					data:{
						"idList":selectedId+"",
						"logoutStatus":1
					},
					success : function(data) {
						if (data ==true || data=='true' ) {
							$.messageBox({
								message : "注销用户资料成功！"
							});
						} else {
							$.messageBox({
								message : data,
								lever : "error"
							});
							return;
						}
						$("#familyInfoList").trigger("reloadGrid");
					}
				});
			}
		});
	});
	
	$("#cancelLogoutFamilyInfo").click(function(){
		if($(this).attr("disabled")=="disabled"){
			return;
		}
		var selectedId = $("#familyInfoList").jqGrid("getGridParam", "selarrrow");
		var cancelLogoutId=new Array();
		var temp=new Array();
		if(selectedId==null || selectedId.length==0){
			 $.messageBox({level:"warn",message:"请选择一条或多条记录，再取消注销！"});
			 return;
		}
		for(var i=0;i<selectedId.length;i++){
			var rowData = $("#familyInfoList").getRowData(selectedId[i]);
			if(rowData.logOut==1 || rowData.logOut=="1"){
				cancelLogoutId.push(selectedId[i]);
			}else{
				temp.push(selectedId[i]);
				}
			}
		selectedId=cancelLogoutId;
		if(selectedId==null||selectedId.length==0){
			$.messageBox({level:'warn',message:"没有可取消注销的数据！"});
			return;
		}
		$.confirm({
			title:"确认取消注销",
			message:"您确定要取消注销吗？",
			width: 400,
			okFunc: function(){
				$.ajax({
					url :'${path}/tenHouseholdsJoint/familyCondition/updateFamilyLogout.action',
					type: "post",
					data:{
						"idList":selectedId+"",
						"logoutStatus":0
					},
					success : function(data) {
						if (data ==true || data=='true' ) {
							$.messageBox({
								message : "取消注销用户资料成功！"
							});
						} else {
							$.messageBox({
								message : data,
								lever : "error"
							});
							return;
						}
						$("#familyInfoList").trigger("reloadGrid");
					}
				});
			}
		});
	});

	$("#refresh").click(function(event){
		onOrgChanged();
	});
	
	$("#searchFamilyInfo").click(function(){
		$("#searchFamilyInfoDialog").createDialog({
				width: 500,
				height: 300,
				title: '高级查询',
				url:'${path}/tenHouseholdsJoint/familyCondition/dispatch.action?mode=search&organizationId='+ getCurrentOrgId(),
				buttons: {
					"查询" : function(){
						$("#searchMaintainForm").submit();
						$("#familyInfoList").trigger("reloadGrid");
						$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	});
	
	$("#addFamilyInfo").click(function(){
		if(!isGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
		$("#familyInfoDialog").createDialog({
				width: 900,
				height: 620,
				title: '新增用户资料',
				url:'${path}/tenHouseholdsJoint/familyCondition/dispatch.action?mode=add&operateSource=familyInfo&organizationId='+ getCurrentOrgId()+'&familyInfo.organization.id'+getCurrentOrgId(),
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
	var selectedId = $("#familyInfoList").jqGrid("getGridParam", "selarrrow");
	if(selectedId==null||selectedId.length>1){
	 $.messageBox({level:"warn",message:"请选择一条记录，再进行修改！"});
		 return;
	}
	updateOperator(event,selectedId);
});

$("#deleteFamilyInfo").click(function(e){
	var selectedId = $("#familyInfoList").jqGrid("getGridParam", "selarrrow");
	if(selectedId==null || selectedId.length==0){
		 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
		 return;
	}
	deleteOperator(event,selectedId);
});

$("#importFamilyInfo").click(function(){
	$("#importFamilyInfoDialog").createDialog({
				width: 400,
				height: 230,
				title:'数据导入',
				url:PATH+'/common/import.jsp?isNew=1&dataType=importTenHouseHoldsFamily&dialog=importFamilyInfoDialog&startRow=6&templates=IMPORTTENHOUSEHOLDSFAMILY&listName=familyInfoList',
				buttons:{
						"导入" : function(event){
					      $("#mForm").submit();
					   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				},
				shouldEmptyHtml:false
	});
});

function updateOperator(event,selectedId){
	if(selectedId==null){
		return;
	}
	var builddata=$("#familyInfoList").getRowData(selectedId);
	$("#familyInfoDialog").createDialog({
		width: 900,
		height:620,
		title: '修改用户资料',
		url:'${path}/tenHouseholdsJoint/familyCondition/dispatch.action?mode=edit&operateSource=familyInfo&organizationId='+ getCurrentOrgId()+'&id='+selectedId,
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
				$("#familyInfoList").trigger("reloadGrid");
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
function viewFamilyInfo(rowid){
	if(rowid==null){
 		return;
	}
	$("#familyInfoDialog").createDialog({
		width: 900,
		height: 380,
		title:'查看用户资料',
		modal : true,
		url:'${path}/tenHouseholdsJoint/familyCondition/dispatch.action?mode=view&id='+rowid+'&organizationId='+getCurrentOrgId(),
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function onOrgChanged(){
		$("#familyInfoList").setGridParam({
			url:'${path}/tenHouseholdsJoint/familyCondition/queryFamilyInfoList.action',
			datatype: "json",
			page:1
		});
		$("#familyInfoList").setPostData({
			"organizationId":orgId
		});
		$("#familyInfoList").trigger("reloadGrid");
}
</script>