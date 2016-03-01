<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>

<div class="content">
<div class="ui-corner-all" id="nav">
<div class="btnbanner btnbannerData">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<select id="searchType" class="basic-input">
	<option value="0" selected>本级</option>
	<option value="1">本级及下辖</option>
</select>

<div class="ui-widget autosearch">
	<input class="basic-input" type="text" value="请输入企业名称" name="searchText" id="searchText" maxlength="18" class="searchtxt"
		 style="width:180px;" onblur="value=(this.value=='')?'请输入企业名称':this.value;" onfocus="value=(this.value=='请输入企业名称')?'':this.value;" />
	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
</div>
</div>
<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
<span class="lineBetween "></span>
<pop:JugePermissionTag
	ename="addLeadingEnterprise">
	<a id=addLeadingEnterprise href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>新增</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="delLeadingEnterprise">
	<a id="delLeadingEnterprise" href="javascript:void(0)"><span><strong
		class="ui-ico-sc"></strong>批量删除</span></a>
</pop:JugePermissionTag> 

<a id="viewLeadingEnterprise" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	
</div>
<div style="clear: both;"></div>
<div style="">
<table id="leadingEnterpriseList">
</table>
<div id="leadingEnterpriseListPager"></div>
</div>
</div>

<div id="leadingEnterpriseDialog"></div>
<div id="addleadingEnterpriseDialog"></div>

<script type="text/javascript">
var dialogWidth=850;
var dialogHeight=600;
var currentOrgId=getCurrentOrgId();
<pop:formatterProperty name="economicCharacter" domainName="@com.tianque.domain.property.PropertyTypes@ECONOMICCHARACTER" />
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateLeadingEnterprise'><a href='javascript:;' onclick='updateOperator("+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='delLeadingEnterprise'><a href='javascript:;' onclick='deleteOperator("+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}

function onOrgChanged(){
    var	initParam = {
		"organization.id":getCurrentOrgId(),
		"searchType":$("#searchType").val()
	}
	$("#leadingEnterpriseList").setGridParam({
		url:'${path}/baseInfo/leadingEnterpriseManage/findLeadingEnterpriseForPageInfo.action',
		datatype:'json',
		page:1
	});
	$("#leadingEnterpriseList").setPostData(initParam);
	$("#leadingEnterpriseList").trigger("reloadGrid");
}
	
	
$(function(){
	$("#leadingEnterpriseList").jqGridFunction({
		datatype: "local",
		mytype:"post",
	    colModel:[
				{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
				{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,align:'center'},
				{name:"organization.orgName",label:"所属组织机构",sortable:false,width:180},
				{name:"enterpriseName",label:'企业名称',sortable:false,width:160,align:'center'},
		        {name:"chargePerson",label:'负责人',sortable:false,width:100,align:'center'},
		        {name:"phoneNumber",label:'联系电话',sortable:false,width:100,align:'center'},
		        {name:"address",label:"地址",sortable:true,width:200,align:'center'},
		        {name:"registeredCapital",label:"注册资金",sortable:true,width:150,align:'center'},
		        {name:"registeredDate",label:"注册日期",sortable:true,width:140,align:'center'},
		        {name:"economicCharacter",label:"经济性质",sortable:false,width:100,align:'center',formatter:economicCharacterFormatter},
		        {name:"licenseNumber",label:"营业执照",sortable:false,width:100,align:'center'},
		        {name:"bodyCode",label:"组织机构代码",sortable:false,width:100,align:'center'}
		],
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
		onSelectRow:selectRow
	});
	jQuery("#leadingEnterpriseList").jqGrid('setFrozenColumns');
	onOrgChanged();
	
	$("#searchType").change(function(){
		onOrgChanged();
	});
	
	$("#reload").click(function(){
		onOrgChanged();
		$("#searchText").attr("value","请输入企业名称");
	});
	
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","请输入企业名称");
	});
	
	$("#fastSearchButton").click(function(){
		var text = $("#searchText").val();
		if(text=='请输入企业名称'){
			return;
		}
		 var	initParam = {
					"organization.id":getCurrentOrgId(),
					"searchType":$("#searchType").val(),
					"leadingEnterprise.enterpriseName":text
				}
				$("#leadingEnterpriseList").setGridParam({
					url:'${path}/baseInfo/leadingEnterpriseManage/findLeadingEnterpriseForPageInfo.action',
					datatype:'json',
					page:1
				});
				$("#leadingEnterpriseList").setPostData(initParam);
				$("#leadingEnterpriseList").trigger("reloadGrid");
	});
	
	$("#addLeadingEnterprise").click(function(){
		$("#addleadingEnterpriseDialog").createDialog({
			width: 600,
			height: 400,
			title:'新增龙头企业信息',
			url:'${path}/baseInfo/leadingEnterpriseManage/dispath.action?organization.id='+getCurrentOrgId()+'&mode=add',
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});		
	});
	
	
	$("#delLeadingEnterprise").click(function(){
		var rowIds = $("#leadingEnterpriseList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除，数据无法恢复",
			okFunc: function(){
				$.ajax({
					url:PATH+"/baseInfo/leadingEnterpriseManage/deleteLeadingEnterpriseByIds.action",
					type:"post",
					data:{
						"ids":rowIds+""
					},
					success:function(data){
						$("#leadingEnterpriseList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功删除该企业信息!"});
				    }
			    });
		   }
	 	});
	});
	
	$("#viewLeadingEnterprise").click(function(){
		var rowIds = $("#leadingEnterpriseList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请至少选择一条数据进行查看！"});
			 return;
		}
		if(rowIds.length >1){
			 $.messageBox({level:"warn",message:"只能选择一条数据进行查看！"});
			 return;
		}
		$("#addleadingEnterpriseDialog").createDialog({
			width: 600,
			height: 400,
			title:'查看企业信息',
			url:'${path}/baseInfo/leadingEnterpriseManage/dispath.action?leadingEnterprise.id='+rowIds+'&mode=view',
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});		
	});
});

function deleteOperator(id){
	if(id==null || id==""){
		$.messageBox({level:"warn",message:"请至少选择一条记录进行删除！"});
		 return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除，数据无法恢复",
		okFunc: function(){
			$.ajax({
				url:PATH+"/baseInfo/leadingEnterpriseManage/deleteLeadingEnterpriseByIds.action",
				type:"post",
				data:{
					"ids":id+""
				},
				success:function(data){
					$("#leadingEnterpriseList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除该企业信息!"});
			    }
		    });
	   }
 	});
}

function updateOperator(id){
	if(id==null || id==""){
		$.messageBox({level:"warn",message:"请至少选择一条记录进行修改！"});
		 return;
	}
	$("#addleadingEnterpriseDialog").createDialog({
		width: 600,
		height: 400,
		title:'修改企业信息',
		url:'${path}/baseInfo/leadingEnterpriseManage/dispath.action?leadingEnterprise.id='+id+'&mode=edit',
		buttons: {
			"保存" : function(event){
	   			$("#maintainForm").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});		
}

function selectRow(){
	var count = $("#leadingEnterpriseList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("leadingEnterpriseList");
	if(selectedCounts == count){
		jqGridMultiSelectState("leadingEnterpriseList", true);
	}else{
		jqGridMultiSelectState("leadingEnterpriseList", false);
	}
}
	
</script>