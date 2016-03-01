<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all contentNav" id="nav">
	<div class="btnbanner btnbannerData">
		<div class="btnbanner">
		 	<#-- <@s.include value="/common/orgSelectedComponent.jsp"/>-->
		    <@s.include value="/common/orgSelectedTaskListComponent.jsp"/>
	    </div>
		<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入帮扶人员姓名" id="searchByCondition1" maxlength="18" style="width: 150px;" onblur="value=(this.value=='')?'请输入帮扶人员姓名':this.value;" onfocus="value=(this.value=='请输入帮扶人员姓名')?'':this.value;">
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton1"><span>搜索</span></a>
		 <#--<@pop.JugePermissionTag ename='searchDruggyTask'>
		<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</@pop.JugePermissionTag>-->		
		<span class="lineBetween "></span>
		<@pop.JugePermissionTag ename='addDruggyTask'>
			<a id="addDruggyTask"  href="javascript:void(0)"><span>新增</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename='refreshDruggyTask'>
		<a id="refresh1"  href="javascript:void(0)"><span>刷新</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename='deleteDruggyTask'>
		<a id="delete1" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong>批量删除</span></a>
		</@pop.JugePermissionTag>
	</div>
		<div style="clear: both;"></div>
		<input type="hidden" name="" id="druggyId" value="${(id)!}" />
		<div style="width: 100%">
			<table id="druggyTaskList"></table>
			<div id="druggyTaskListPager"></div>
		</div>
		<div id="druggyTaskDialog"></div>
		<div id="importFamilyInfoDialog"></div>
		<div id="searchFamilyInfoDialog"></div>
		<div id="addTaskListReplyDlg"></div>
</div>
<script type="text/javascript">

function nameFormatter(el,options,rowData){
	var logOut = rowData.logOut;
	if(logOut=''||logOut == 0 || logOut =='0'){
		return "<@pop.JugePermissionTag ename='viewDruggyTask'><a href='javascript:;' onclick='viewDruggyTask("+rowData.id+");'><span>"+rowData.name+"</span></a></@pop.JugePermissionTag>";
	}else{
		return "<@pop.JugePermissionTag ename='viewDruggyTask'><a href='javascript:;' onclick='viewDruggyTask("+rowData.id+");' style='color:#888888;' ><span>"+rowData.name+"</span></a></@pop.JugePermissionTag>";
	}
}

var orgId=selectConfigTaskOrg();
var title="吸毒人员信息";
$(document).ready(function(){
   var postData={
			"druggyTask.organization.id":orgId,
			"druggyTask.druggyId":$("#druggyId").val()
        };
	if(isConfigTaskSelect()){
		$.extend(postData,{"druggyTask.mode":"gridConfigTask","druggyTask.funOrgId": $("#funOrgId").val()})
	}
	$("#druggyTaskList").jqGridFunction({
		url:'${path}/baseInfo/druggyTaskManage/searchInterViewDruggy.action',
		datatype: "json",
		postData:postData,
	   	colModel:[
	   	          {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
                  {name:"createUserName",index:"createUserName",label:'走访人',sortable:false,hidden:false,frozen:false},
                  {name:"createDate",index:"createDate",label:'走访时间',sortable:false,hidden:false,frozen:false},
                  {name:"helpPeople",index:"helpPeople",label:'帮扶人员',sortable:false,hidden:false},
                  {name:'hasException',label:'有无异常',sortable:false,align:"center", width:150,hidden:true,formatter:hasExceptionFormatter},
                  {name:"exception",index:"exception",label:'异常情况',sortable:false,hidden:false,frozen:false}
		   ],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:true,
		ondblClickRow:viewDruggyTask,
		onSelectRow:function(){}
	});

	$("#refresh1").click(function(event){
	$("#searchByCondition1").attr("value","请输入帮扶人员姓名");
		onOrgChanged();
	});
	
	$("#fastSearchButton1").click(function(e){
	var orgId=selectConfigTaskOrg();
	fastSearch(orgId);
	});
	
	$("#refreshSearchKey").click(function(){
		$("#searchByCondition1").attr("value","请输入帮扶人员姓名");
	});
	
	function hasExceptionFormatter(el, options, rowData){
		if(rowData.hasException == 1){
			return "有";
		}else {
			return "无";
		}
	}
	$("#search").click(function(){
		$("#searchFamilyInfoDialog").createDialog({
				width: 500,
				height: 350,
				title: '高级查询',
				url:'${path}baseInfo/druggyTaskManage/dispatch.action?mode=search',
				buttons: {
					"查询" : function(){
						searchDruggyTask();
						$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	});
	
	$("#delete1").click(function(){
	    var selectedId = $("#druggyTaskList").jqGrid("getGridParam", "selarrrow");
	    if(selectedId.length==0){
	       $.messageBox({level:"warn",message:"请至少选择一条记录进行删除！"});
			return;
	    }
	    $.confirm({
		title:"确认删除该"+title,
		message:"该"+title+"删除后，将不可恢复，您确定要删除该"+title+"吗？",
		width: 400,
		okFunc: function(){
		$.ajax({
		url : "${path}/baseInfo/druggyTaskManage/deleteDruggyTask.action?ids="+selectedId,
		success : function(data) {
			onOrgChanged(selectConfigTaskOrg(),isConfigTaskGrid());
		  }
	     });
		}
	    });
	    var evt = event || window.event;
	    if (window.event) {
	    evt.cancelBubble=true;
	    } else {
	    evt.stopPropagation();
	    }
	})
	
	$("#addDruggyTask").click(function(){
		if(!isConfigTaskGrid()){
			$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
		 return;
		}
		var druggyInfoId=$("#druggyId").val();
		$("#druggyTaskDialog").createDialog({
				width: 600,
				height: 520,
				title: '新增吸毒人员记录',
				url:'${path}/baseInfo/druggyTaskManage/dispatch.action?addFlag=true&mode=add&orgId='+selectConfigTaskOrg()+'&druggyInfoId='+druggyInfoId,
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

function updateOperator(selectedId){
	if(selectedId==null){
		return;
	}
	var builddata=$("#druggyTaskList").getRowData(selectedId);
	$("#druggyTaskDialog").createDialog({
		width: 600,
		height:470,
		title: '签收',
		url:'${path}/baseInfo/druggyTaskManage/dispatch.action?mode=sign&orgId='+ selectConfigTaskOrg()+'&druggyTaskId='+selectedId,
		buttons: {
			"签收" : function(){
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

function viewDruggyTask(rowid){
	if(rowid==null){
 		return;
	}
	$("#druggyTaskDialog").createDialog({
		width: 550,
		height: 400,
		title:'查看吸毒人员走访信息',
		modal : true,
		url:'${path}/baseInfo/druggyTaskManage/viewInterViewDruggy.action?id='+rowid,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

//高级搜索
	function searchDruggyTask()
	{   
	 var initParam = {
		"druggyTask.organization.id":selectConfigTaskOrg(),
		"druggyTask.name": $("#conditionName").val(),
		"druggyTask.place": $("#conditionPlace").val(),
		"druggyTask.conditionStartDate":$("#conditionStartDate").val(),
		"druggyTask.conditionEndDate":$("#conditionEndDate").val(),
		"druggyTask.conditionSignStartDate":$("#conditionSignStartDate").val(),
		"druggyTask.conditionSignEndDate":$("#conditionSignEndDate").val(),
		"druggyTask.status":$("#status").val(),
		"druggyTask.hasReplay":$("#hasReplay").val(),
		"druggyTask.hasException":$("#hasException").val()
	}
	if(isConfigTaskSelect()){
		$.extend(initParam,{"druggyTask.mode":"gridConfigTask","druggyTask.funOrgId": $("#funOrgId").val()})
	}
	$("#druggyTaskList").setGridParam({
		url:"${path}/baseInfo/druggyTaskManage/searchInterViewDruggy.action",
		datatype: "json",
		page:1
	});
	    $("#druggyTaskList").setPostData(initParam);
		$("#druggyTaskList").trigger("reloadGrid");
	}
	
	
 function refreshList(searchText){
	var orgId=selectConfigTaskOrg();
	$("#druggyTaskList").setGridParam({
		url:'${path}/baseInfo/druggyTaskManage/searchInterViewDruggy.action',
		datatype: "json",
		page:1
	});
   	var postData={
		"druggyTask.organization.id":orgId,
		"druggyTask.druggyId":$("#druggyId").val()
        };
	if(isConfigTaskSelect()){
		$.extend(postData,{"druggyTask.mode":"gridConfigTask","druggyTask.funOrgId": $("#funOrgId").val()})
	}
	$("#druggyTaskList").setPostData(postData);
	$("#druggyTaskList").trigger("reloadGrid");
} 

function fastSearch(orgId){
	var condition = $("#searchByCondition1").val();

	if(condition == '请输入帮扶人员姓名') return;
	var initParam = {
	"druggyTask.organization.id":orgId,
	"druggyTask.fastSearchKeyWords":condition,
	"druggyTask.druggyId":$("#druggyId").val()
	}
	if(isConfigTaskSelect()){
		$.extend(initParam,{"druggyTask.mode":"gridConfigTask","druggyTask.funOrgId": $("#funOrgId").val()})
	}
	$("#druggyTaskList").setGridParam({
		url:'${path}/baseInfo/druggyTaskManage/searchInterViewDruggy.action',
		datatype: "json",
		page:1
	});
	$("#druggyTaskList").setPostData(initParam);
	$("#druggyTaskList").trigger("reloadGrid");
}
function onOrgChanged(){
    var orgId = selectConfigTaskOrg();
		$("#druggyTaskList").setGridParam({
			url:'${path}/baseInfo/druggyTaskManage/searchInterViewDruggy.action',
			datatype: "json",
			page:1
		});
  var postData={
			"druggyTask.organization.id":orgId,
			"druggyTask.druggyId":$("#druggyId").val()
        };
	if(isConfigTaskSelect()){
		$.extend(postData,{"druggyTask.mode":"gridConfigTask","druggyTask.funOrgId": $("#funOrgId").val()})
	}
	    $("#druggyTaskList").setPostData(postData);
		$("#druggyTaskList").trigger("reloadGrid");
}

</script>