<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="content">
	<div  class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="userState">
				<select class="basic-input"  id="displayLevel${primaryMembers.isHaveJob}" name="displayLevel"  style="background:white ">
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>" >仅显示本级</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>" selected="selected">全部</option>
				</select>
			</div>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名" id="searchPrimaryMembersVo_condition${primaryMembers.isHaveJob}" maxlength="10" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名':this.value;" onfocus="value=(this.value=='请输入姓名')?'':this.value;"/>
				<button id="refreshSearchKey${primaryMembers.isHaveJob}" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a href="javascript:;" id="fastSearchButton${primaryMembers.isHaveJob}"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchPrimaryMembers">
			<a id="search${primaryMembers.isHaveJob}" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
		</div>
		<span class="lineBetween "></span>
		<c:if test="${primaryMembers.isHaveJob==0 }">
		<pop:JugePermissionTag ename="addPrimaryMembers">
		<a id="add${primaryMembers.isHaveJob}" href="javascript:void(0)"><span>新增</span></a>
		</pop:JugePermissionTag>
		</c:if>
		<pop:JugePermissionTag ename="updatePrimaryMembers">
		<a id="update${primaryMembers.isHaveJob}"  href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePrimaryMembers">
		<a id="delete${primaryMembers.isHaveJob}"  href="javascript:void(0)"><span>批量删除</span></a>
		</pop:JugePermissionTag>
		<c:if test="${primaryMembers.isHaveJob ==0 }">
		<pop:JugePermissionTag ename="combinePrimaryMembers">
			<a id="combinePrimaryMembers"  href="javascript:void(0)"><span>检测查重</span></a>
		</pop:JugePermissionTag>
		</c:if>
		<a id="reload${primaryMembers.isHaveJob}" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width:100%;" >	
		<table id="primaryMembers${primaryMembers.isHaveJob}List"></table>
		<div id="primaryMembers${primaryMembers.isHaveJob}ListPager"></div>
	</div>
	<div id="primaryMembersDialog${primaryMembers.isHaveJob}"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="primaryMembersManagement">
		<span id="title"><s:property value="#request.name"/></span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="primaryOrgId" domainName="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
var dialogWidth=820;
var dialogHeight=610;
var currentOrgId=getCurrentOrgId();

$(document).ready(function(){
	//是否进入了网格
	if(isGrid()){
		$("#displayLevel${primaryMembers.isHaveJob}").hide();
	}
	//是否在任
	var isHaveJob='${primaryMembers.isHaveJob}';
	
	if(isHaveJob == 1){
		$("#add${primaryMembers.isHaveJob},#update${primaryMembers.isHaveJob},#combinePrimaryMembers").hide();
	}
	
	//服务成员列表
	$("#primaryMembers${primaryMembers.isHaveJob}List").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colNames:['id','encryptId','orgId','操作','姓名','性别','身份证号','年龄','手机','电话','所在组织','所属区域(网格)'],
		colModel:[
			{name:"id",index:"id",key:true,sortable:true,hidden:true},
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			{name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true},
			{name:"operation",index:"id",sortable:false,formatter:operateMemberFormatter,width:120,align:"center"},
			{name:'name',index:"name",width:100,sortable:true,formatter:viewMemberFormatter},
			{name:'gender',index:'gender',formatter:genderFormatter,width:80,sortable:true,align:"center"},
			{name:'idcardNo',index:"idcardNo",width:150,sortable:true},
			{name:'years',index:"years",formatter:yearsFormatter,width:50,sortable:true,align:"center"},
			{name:'mobile',index:"mobile",width:100,sortable:true},
			{name:'telephone',index:'telephone',sortable:true,width:110,align:"center"},
			{name:'primaryOrgName',index:'primaryOrgName',width:80,sortable:false,align:"center"},
			{name:"org.orgName",index:"orgName",sortable:true,width:350}
		],
		multiselect:true,
		showColModelButton:true,
		sortname:'pm.id',
		ondblClickRow:viewPrimaryMember,
		height:$(".ui-layout-center").height()-200
	});
	
	$("#primaryMembers${primaryMembers.isHaveJob}List").jqGrid('setFrozenColumns');
	    loadMembers();//列表加载
	
	$("#displayLevel${primaryMembers.isHaveJob}").change(function(){
		loadMembers(); //下拉加载...
	});
	$("#refreshSearchKey${primaryMembers.isHaveJob}").click(function(event){
		$("#searchPrimaryMembersVo_condition${primaryMembers.isHaveJob}").attr("value","请输入姓名");
	});
	$("#fastSearchButton${primaryMembers.isHaveJob}").click(function(){
		loadMembers(); //快速搜索
	});
	
	//查看成员详细信息
	function viewMemberFormatter(el,options,rowData){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPrimaryMembers'> onclick='viewPrimaryMember("+rowData.id+")' </pop:JugePermissionTag>   >"+rowData.name+"</a>";
	}
	//获取成员列表行数
	function countSelectedIds(){
		var selectedIds = $("#primaryMembers${primaryMembers.isHaveJob}List").jqGrid("getGridParam", "selarrrow");
		return null == selectedIds ? 0 :selectedIds.length;
	}
	//修改成员
	$("#update${primaryMembers.isHaveJob}").click(function(){
		updateOperatorByButtons();
	});

	//删除按钮事件
	$("#delete${primaryMembers.isHaveJob}").click(function(){
		var selectedIds = $("#primaryMembers${primaryMembers.isHaveJob}List").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null){
			$.messageBox({level:'warn',message:"请选择一条成员信息进行删除！"});
	 		return;
		}
		deleteOperator(selectedIds);
	});
	//新增按钮事件
	$("#add${primaryMembers.isHaveJob}").click(function(event){
		$("#primaryMembersDialog${primaryMembers.isHaveJob}").createDialog({
			title:"新增成员",
			width: dialogWidth,
			height: dialogHeight,
			url:'${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=add&dailogName=primaryMembersDialog&orgId='+currentOrgId+'&displayLevel='+$("#displayLevel${primaryMembers.isHaveJob}").val()+'&primaryMembers.isHaveJob='+${primaryMembers.isHaveJob},
			buttons: {
				"保存" : function(event){
					$("#primaryMembersForm").submit();
				},
				"取消" : function(event){
					$(this).dialog("close");
				}
			}
		});
	});
	
	//高级搜索对话框
	$("#search${primaryMembers.isHaveJob}").click(function(){
		$("#primaryMembersDialog${primaryMembers.isHaveJob}").createDialog({
			title:"成员信息查询-请输入查询条件",
			width: 600,
			height: 400,
			url:'${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=search&primaryMemberVo.org.id='+currentOrgId+'&primaryMembers.isHaveJob='+'${primaryMembers.isHaveJob}'+'&primaryMembers.displayLevel='+$("#displayLevel${primaryMembers.isHaveJob}").val(),
			buttons: {
				"查询" : function(event){
					var isSubmit = isCanSubmit();
					if(isSubmit){
						loadData($("#serachPrimaryMember_form").serializeArray());
						$(this).dialog("close");
					}else{
						$.messageBox({message : "组织名称不存在, 请重新选择!",level : "error"});
					}
				},
				"关闭" : function(){
		        	$(this).dialog("close");
				}
			}

		});
	});
	
	//刷新按钮事件
	$("#reload${primaryMembers.isHaveJob}").click(function(){
		$("#searchPrimaryMembersVo_condition${primaryMembers.isHaveJob}").val('请输入姓名');
		loadMembers();
	});
	//加载成员
	function loadMembers() {
		var condition = $("#searchPrimaryMembersVo_condition${primaryMembers.isHaveJob}").val();
		var listParam = null;
		listParam = {
			"primaryMemberVo.isHaveJob":'${primaryMembers.isHaveJob}',
			'primaryMemberVo.org.Id':getCurrentOrgId(),
			"primaryMemberVo.displayLevel":$("#displayLevel${primaryMembers.isHaveJob}").val(),
			"primaryMemberVo.isPrimaryMember":'<s:property value="@com.tianque.baseInfo.primaryOrg.primaryMembers.constant.PrimaryMemberType@ISPRIMARYMEMBER"/>'
		};
		if('请输入姓名' != condition) {
			$.extend(listParam,{'primaryMemberVo.fastSearchKeyWords':condition});
		}
		loadData(listParam);
		$("#primaryMember${primaryMember.isHaveJob}List").trigger("reloadGrid");
		$("#primaryMember${primaryMember.isHaveJob}List").jqGrid('setFrozenColumns');
	}
	
	
	//加载服务成员数据
	function loadData(listParam) {
		$("#primaryMembers${primaryMembers.isHaveJob}List").setGridParam({
			url:'${path}/primaryOrg/primaryMembersManage/findPrimaryMembers.action',
			datatype: "json",
			page:1
		});
		$("#primaryMembers${primaryMembers.isHaveJob}List").setPostData(listParam);
		$("#primaryMembers${primaryMembers.isHaveJob}List").trigger("reloadGrid");
	}
});
//修改成员信息
function updateOperator(selectedId){
	var rowData =  $("#primaryMembers${primaryMembers.isHaveJob}List").getRowData(selectedId);
	$("#primaryMembersDialog${primaryMembers.isHaveJob}").createDialog({
		title:'修改成员信息',
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=edit&selectedId='+rowData.encryptId+'&displayLevel='+$("#displayLevel${primaryMembers.isHaveJob}").val()+'&primaryMembers.isHaveJob='+${primaryMembers.isHaveJob},
		buttons: {
			"保存" : function(event){
	   			$("#primaryMembersForm").submit();
	   			$(this).dialog("close");
   			},
   			"关闭" : function(){
		        	$(this).dialog("close");
				}
		}
	});
}

//修改成员信息
function updateOperatorByButtons(){
	var selectedId = getSelectedIds();
	if(selectedId==null || selectedId==""){
		$.messageBox({level:'warn',message:"请选择一条数据进行操作！"});
 		return;
	}
	if(selectedId.length>1){
		$.messageBox({level:'warn',message:"只能选择一个成员维护对象！"});
 		return;
	} 
	var rowData =  $("#primaryMembers${primaryMembers.isHaveJob}List").getRowData(selectedId);
	$("#primaryMembersDialog${primaryMembers.isHaveJob}").createDialog({
		title:'修改成员信息',
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=edit&selectedId='+rowData.encryptId+'&displayLevel='+$("#displayLevel${primaryMembers.isHaveJob}").val()+'&primaryMembers.isHaveJob='+${primaryMembers.isHaveJob},
		buttons: {
			"保存" : function(event){
	   			$("#primaryMembersForm").submit();
   			},
   			"关闭" : function(){
		        	$(this).dialog("close");
				}
		}
	});
}
//删除成员
function deleteOperator(selectedId){
	var selectedIds=deleteOperatorByEncrypt('primaryMembers${primaryMembers.isHaveJob}',selectedId,'encryptId');
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/primaryOrg/primaryMembersManage/deletePrimaryMembersByIds.action',
				type:"post",
				data:{
					"ids":selectedIds+""
				},
				success:function(data){
					if(data!=true && data!="true"){
						$(this).dialog("close");
					}else{
						$.messageBox({message:"成员信息删除成功"});	
						$("#primaryMembers${primaryMembers.isHaveJob}List").trigger("reloadGrid");
					}
				}
			});
		}
	});
}

//合并重复服务成员数据
$("#combinePrimaryMembers").click(function(){
	var selectedId = getSelectedIds();
	if(selectedId==null || selectedId==""){
		$.messageBox({level:'warn',message:"请选择一条数据进行操作！"});
 		return;
	}
	if(selectedId.length>1){
		$.messageBox({level:'warn',message:"只能选择一个成员维护对象！"});
 		return;
	} 
	var rowData=  $("#primaryMembers${primaryMembers.isHaveJob}List").getRowData(selectedId);
	$("#primaryMembersDialog${primaryMembers.isHaveJob}").createDialog({
		width: 850,
		height: 600,
		title:'合并成员库成员信息',
		url:'${path}/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=combine&id='+rowData.encryptId,
		buttons: {
			"合并" : function(){
				if(getTableNum("combinePrimaryMembersList") == 0){
					$.messageBox({level:'warn',message:"没有需要合并的数据!"});
 					return;
				}
				var selectedId = $("#combinePrimaryMembersList").jqGrid("getGridParam", "selarrrow");
				if(null == selectedId ||""==selectedId){
					$.messageBox({level:'warn',message:"请选择需要合并的成员!"});
					return;
				}
				$("#isSubmit").val("true");
				$("#combineForm").submit();
	   		},
	   		"关闭" : function(){
	   			$("#primaryMembers${primaryMembers.isHaveJob}List").trigger("reloadGrid");
	        	$(this).dialog("close");
			}
		}
	});
});

//查看服务成员基本信息
function viewPrimaryMember(selectId){
	if(selectId==null || selectId==""){
		$.messageBox({level:'warn',message:"请选择一条需要查看的数据"});
 		return;
	}
	var rowData=  $("#primaryMembers${primaryMembers.isHaveJob}List").getRowData(selectId);
	$("#primaryMembersDialog${primaryMembers.isHaveJob}").createDialog({
		title:"查看成员基本信息",
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=view&selectedId='+rowData.encryptId,
		buttons: {
			"关闭" : function(){
	        $(this).dialog("close");
	  		 }
		}
	});
}
	
function yearsFormatter(el,options,rowData){
	if(rowData.years==0 || rowData.years==undefined){
		return "";
	}else{
		return rowData.years;
	}
}

function operateFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updatePrimaryMembers'><a href='javascript:' onclick='updateOperator("+rowData.id+")'><span>修改</span></a></pop:JugePermissionTag><pop:JugePermissionTag ename='deletePrimaryMembers'> | <a href='javascript:;' onclick='deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}	
//获得列表数据数量
function getTableNum(tableid){
  var num=$("#"+tableid+" tbody tr").size();
  return num-1;
}

//获取选中列的ID
function getSelectedIds(){
	var selectedIds = $("#primaryMembers${primaryMembers.isHaveJob}List").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
 
//操作
function operateMemberFormatter(el,options,rowData){
	var havajobStr = "", _updateStr = "" ,isHaveJob = "";
	if(${primaryMembers.isHaveJob}=="0"){
		havajobStr="卸任";
		isHaveJob=1;
		_updateStr = "<pop:JugePermissionTag ename='updatePrimaryMembers'><a id='update${primaryMembers.isHaveJob}'  href='javascript:void(0)' onclick='updateOperator("+rowData.id+")'><span>修改</span></a> </pop:JugePermissionTag>| ";
	}else{
		havajobStr="任职";
		isHaveJob=0;
	}
	return _updateStr+" <pop:JugePermissionTag ename='deletePrimaryMembers'><a id='delete${primaryMembers.isHaveJob}'  href='javascript:void(0)'  onclick='deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>| <pop:JugePermissionTag ename='holdPrimaryMembers'> <a href='javascript:;' onclick='havajobMemberOperator(event,"+rowData.id+","+isHaveJob+",\""+havajobStr+"\")'><span>"+havajobStr+"</span></a> </pop:JugePermissionTag>";
}

function havajobMemberOperator(event,selectedIds,isHaveJob,havajobStr){
	if(selectedIds==null || selectedIds==""){
		$.messageBox({level:'warn',message:"请选择需要查看的数据"});
 		return;
	}
	if(selectedIds.length>1){
		$.messageBox({level:'warn',message:"您只能选择一条数据进行查看"});
 		return;
	} 
	var rowData=  $("#primaryMembers${primaryMembers.isHaveJob}List").getRowData(selectedIds);
	$.confirm({
		title:"确认"+havajobStr,
		message:"确定要"+havajobStr+"吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/primaryOrg/primaryMembersManage/havajobPrimaryMemberByEncrypt.action?id='+rowData.encryptId+'&isHaveJob='+isHaveJob,
				success:function(data){
					if(data.id){
					    $.messageBox({message:"成功"+havajobStr+"该成员!"});
						$("#primaryMembers${primaryMembers.isHaveJob}List").trigger("reloadGrid");
					}else{
						$.messageBox({message:data,level: "error"});
					}
				}
			});
		}
	});
}
</script>