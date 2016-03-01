<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>

<%-- 		<pop:JugePermissionTag ename="searchGridMemeber"> --%>
<%-- 		<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a> --%>
<%-- 		</pop:JugePermissionTag> --%>
		
		<pop:JugePermissionTag ename="addGridMemeber">
		<a id="addGridMemeber" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="deleteGridMemeber">
		<a id="deleteGridMemeber" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="activateGridMemeber">
		<a id="activateGridMemeber" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>激活</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="importGridMemeber">
		<a id="importGridMemeber" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>导入</span></a>
		</pop:JugePermissionTag>
		
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="gridMemeberList">
		</table>
		<div id="gridMemeberListPager"></div>
	</div>
	<div id="gridMemeberDialog"></div>
	<div id="addGridMemeberDialog"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="positionType" domainName="@com.tianque.domain.property.PropertyTypes@GRID_POSITIONTYPE" />
<pop:formatterProperty name="education" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
var dialogWidth=850;
var dialogHeight=600;
var currentOrgId=getCurrentOrgId();

function screeningFourteamsFunction(){
	 var	initParam = {
				"gridTeam.organization.id":currentOrgId,
				"organization.id":currentOrgId,
				"gridTeam.teamType.id":$("#screeningFourteams").val()
			}

			$("#gridMemeberList").setGridParam({
				url:'${path}/baseinfo/gridTeamManage/findGridTeamForPageResult.action',
				datatype:'json',
				page:1
			});
			$("#gridMemeberList").setPostData(initParam);
			$("#gridMemeberList").trigger("reloadGrid");
}
function selectRow(){
	var count = $("#gridMemeberList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("gridMemeberList");
	if(selectedCounts == count){
		jqGridMultiSelectState("gridMemeberList", true);
	}else{
		jqGridMultiSelectState("gridMemeberList", false);
	}
}   

function onOrgChanged(orgId){
    var	initParam = {
		"gridTeam.organization.id":currentOrgId,
		"organization.id":currentOrgId
	}

	$("#gridMemeberList").setGridParam({
		url:'${path}/baseinfo/gridTeamManage/findGridTeamForPageResult.action',
		datatype:'json',
		page:1
	});
	$("#gridMemeberList").setPostData(initParam);
	$("#gridMemeberList").trigger("reloadGrid");
}

function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateGridMemeber'><a href='javascript:;' onclick='updateGridTeam("+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteGridMemeber'><a href='javascript:;' onclick='deleteGridTeam("+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}

function viewFormatter(el,options,rowData){
	return "<a href='javascript:viewGridTeam(\""+rowData.id+"\")'><span>"+rowData.memeberName+"</span></a>";
}
	
	
$(function(){
	$("#gridMemeberList").jqGridFunction({
		datatype: "local",
		mytype:"post",
	    colModel:[
				{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
				{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,align:'center'},
				{name:"organization.orgName", index:'orgCode',sortable:true,label:'所属网格',width:100,align:'center'},
				{name:"memeberName", index:'memeberName',label:'姓名',formatter:viewFormatter,width:80,align:'center'},
				{name:"idCardNo", index:'idCardNo',label:'身份证号码',sortable:true,width:150,align:'center'},
				{name:"gender", index:'gender',label:'性别',formatter:genderFormatter,width:80,align:'center'},
				{name:"birthDate", index:'birthDate',label:'出生年月',width:100,align:'center'},
				{name:"phoneNumber", index:'phoneNumber',label:'联系电话',width:100,align:'center'},
				{name:"positionType", index:'positionType',label:'专职/兼职',formatter:positionTypeFormatter,width:100,align:'center'},
				{name:"politicalBackground", index:'politicalBackground',label:'政治面貌',formatter:politicalBackgroundFormatter,width:100,align:'center'},
				{name:"education", index:'education',label:'文化程度',formatter:educationFormatter,width:100,align:'center'},
				{name:"isActivated", index:'isActivated',label:'是否激活',formatter:isActivatedFormatter,width:100,align:'center'},
				{name:"activatedTime", index:'activatedTime',label:'激活时间',sortable:true,width:150,align:'center'},
				{name:"registeredDate", index:'registeredDate',label:'登记时间',sortable:true,width:100,align:'center'}
		],
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
	  	onSelectRow:function(){selectRow();},
		ondblClickRow:function (rowid){
			viewGridTeam(rowid);
		},
	});
	jQuery("#gridMemeberList").jqGrid('setFrozenColumns');
	onOrgChanged(getCurrentOrgId());
	
	
	//导入
	$("#importGridMemeber").click(function(event){
			$("#addGridMemeberDialog").createDialog({
				width: 400,
				height: 230,
				title:"数据导入",
				url:"${path}/common/import.jsp?isNew=1&dataType=gridTeam&dialog=addGridMemeberDialog&startRow=6&templates=GRIDTEAM&listName=gridMemeberList&module="+currentOrgId,
				buttons:{
					"导入" : function(event){
						$("#mForm").submit();
					},
				   	"关闭" : function(){
				   		$(this).dialog("close");
				   		$("#gridMemeberList").trigger("reloadGrid");
				   	}
				},
				shouldEmptyHtml:false
			});
		});
	
	
	$("#addGridMemeber").click(function(){
		
		if(!(isVillage()||isTown()||isDistrict())){
			 $.messageBox({level:"warn",message:"请选择社区、街道、区县层级进行新增！"});
			 return;
		}
		
		$("#gridMemeberDialog").createDialog({
			width: 800,
			height: 500,
			title:'新增网格员',
			url:'${path}/baseinfo/gridTeamManage/dispatchOperate.action?mode=add&organization.id='+currentOrgId,
			buttons: {
		   		"保存" : function(event){
		   			fullOrgId();
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});		
	});
	
	$("#activateGridMemeber").click(function(){
		var rowIds = $("#gridMemeberList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length !=1){
			 $.messageBox({level:"warn",message:"请选择一条记录，再进行激活！"});
			 return;
		}
		var gridMemeber = $("#gridMemeberList").getRowData(rowIds[0]);
		if(gridMemeber.isActivated=="已激活"){
			$.messageBox({level:"warn",message:"该条信息已激活,请勿重复激活!"});
			 return;
		}
		$("#gridMemeberDialog").createDialog({
			width: 800,
			height: 500,
			title:'激活网格员',
			url:'${path}/baseinfo/gridTeamManage/dispatchOperate.action?mode=edit&gridTeam.id='+rowIds,
			buttons: {
		   		"激活" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});		
	});
	
	$("#reload").click(function(){
		$("#screeningFourteams").find("option:first").attr("selected",true); 
		onOrgChanged(currentOrgId);
	});
	
	
	$("#deleteGridMemeber").click(function(){
		var rowIds = $("#gridMemeberList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteGridTeam(rowIds);
	});
	
});


function viewGridTeam(id){
	$("#gridMemeberDialog").createDialog({
		width: 800,
		height: 500,
		title:'查看网格成员',
		url:'${path}/baseinfo/gridTeamManage/dispatchOperate.action?mode=view&gridTeam.id='+id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});	
}

function updateGridTeam(selectIds){
	if(selectIds==null || selectIds==''){
		 $.messageBox({level:"warn",message:"请选择一条记录进行修改"});
		 return;
	}
	$("#gridMemeberDialog").createDialog({
		width: 800,
		height: 500,
		title:'修改网格成员',
		url:'${path}/baseinfo/gridTeamManage/dispatchOperate.action?mode=edit&gridTeam.id='+selectIds,
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
function deleteGridTeam(selectIds){
	if(selectIds==null || selectIds==''){
		 $.messageBox({level:"warn",message:"请至少选择一条记录进行删除"});
		 return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除，数据无法恢复",
		okFunc: function(){
			$.ajax({
				url:'${path}/baseinfo/gridTeamManage/deleteGridTeamByIds.action',
				type:"post",
				data:{
					"ids":selectIds+""
				},
				success:function(data){
					$("#gridMemeberList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除该信息!"});
			    }
		    });
	   }
 	});
}
function isActivatedFormatter(cellvalue){
	return cellvalue==true ? "已激活":"未激活";
}
</script>