<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<#assign moduleName=moduleName />
<#assign lowerCaseModuleName=moduleName[0]?lower_case+moduleName?substring(1) />
<#assign upperCaseModuleName=moduleName?upper_case />
<#assign moduleCName=moduleCName />
<!--
<%
int startRowNum=6;//startRowNum 是从哪一行读取excel 文件的实体数据
request.setAttribute("newStartRow",request.getParameter("newStartRow")==null?startRowNum:request.getParameter("newStartRow"));
%> -->
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
		    <span class="lineBetween firstDOM"></span>
            <div class="userState">
			<select id="searchType" class="basic-input">
					<option value="0" selected="selected">全部</option>
 					<option value="1">查看重复数据</option>
 					<option value="2">最近一月未认领数据</option>
 					<option value="3">最近三月未认领数据</option>
 					<option value="4">最近半年未认领数据</option>
 					<option value="5">最近一年未认领数据</option>
 					<option value="6">一年前未认领数据</option>
 					<option value="7">未认领数据</option>
 					<option value="8">已认领数据</option>
 					<option value="9">最近一月认领数据</option>
 					<option value="10">最近三月认领数据</option>
 					<option value="11">最近半年认领数据</option>
 					<option value="12">最近一年认领数据</option>
 					<option value="13">一年前认领数据</option>
			</select>
		</div>
		</div>
		<span class="lineBetween "></span>
		
		<@s.if test='#parameters.mode[0].equals("claimList")'>
			<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input type="text" class="basic-input" value="请输入常住地址" name="searchCurrentlyAddress" id="searchCurrentlyAddress" class="searchtxt" maxlength="18" onblur="value=(this.value=='')?'请输入常住地址':this.value;" onfocus="value=(this.value=='请输入常住地址')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
			</div>
	      	<a id="search" href="javascript:void(0)"><span>搜索</span></a>
	      	<span class="lineBetween "></span>
	      	<!--  
		  	<@pop.JugePermissionTag ename="update${moduleName}">
				<a id="update" href="javascript:void(0)"><span>修改</span></a>
		  	</@pop.JugePermissionTag>
		  	-->
		  	<@pop.JugePermissionTag ename="claim${moduleName}">
				<a id="claim" href="javascript:void(0)"><span>认领</span></a>
		  	</@pop.JugePermissionTag>
		  	<@pop.JugePermissionTag ename="unClaim${moduleName}">
				<a id="unClaim" href="javascript:void(0)"><span>撤销认领</span></a>
		  	</@pop.JugePermissionTag>
		</@s.if>
		<!--  
		<@pop.JugePermissionTag ename="view${moduleName}">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</@pop.JugePermissionTag>
		-->
		<@s.else>
		<@pop.JugePermissionTag ename="delete${moduleName}">
			<a id="delete" href="javascript:void(0)"><span>批量删除</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="import${moduleName}">
			<a id="import" href="javascript:void(0)"><span>导入</span></a>
		</@pop.JugePermissionTag>
		</@s.else>
		<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="${lowerCaseModuleName}List"> </table>
		<div id="${lowerCaseModuleName}ListPager"></div>
	</div>
	<div id="${lowerCaseModuleName}Dialog"></div>
	<div id="noticeDialog"></div>
	<div id="${lowerCaseModuleName}MaintanceDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
	<div id="resultListDialog">
		<input type="hidden" id="claimOrgIdForResultList" />
		<div id="claimTabs" style="width:100%;">
			<ul>
				<li><a href="#successMsgDiv">认领成功</a></li>
				<li><a href="#claimErrorMsgDiv">认领失败</a></li>
			</ul>
			<div id="successMsgDiv">
				<table id="successMsg"></table>
			</div>
			<div id="claimErrorMsgDiv">
				<table id="claimErrorMsg"></table>
			</div>
		</div>
    </div>

<script>
var claimOrgIdForGlobal = 0;
var isFromResultList = false;
function onOrgChanged(){
	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}/dataManage/${lowerCaseModuleName}Manage/${lowerCaseModuleName}List.action",
		datatype: "json",
		page:1
	});
	var initParam = {};
	var modes="";
	<@s.if test='#parameters.mode[0].equals("claimList")'>
	modes="claimList";
	</@s.if>
	<@s.else>
    modes="importList";
    </@s.else>
	if("请输入常住地址"==$("#searchCurrentlyAddress").val()){
		initParam = {
				"searchType":$("#searchType").val(),
				"mode":modes,
		    	"searchCurrentlyAddress":""
		    	
		};
	}else{
		initParam = {
				"searchType":$("#searchType").val(),
				"mode":modes,
		    	"searchCurrentlyAddress":$("#searchCurrentlyAddress").val()
		};
	}
	$("#${lowerCaseModuleName}List").setPostData(initParam);
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
}

$(document).ready(function(){
	$("#resultListDialog").hide();
	jQuery("#${lowerCaseModuleName}List").jqGrid('setFrozenColumns');
    
	$("#${lowerCaseModuleName}List").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
		colNames: gridOption.colNames,
	  	multiselect:true,
	  	onSelectAll:function(aRowids,status){ },
      	onSelectRow: selectRow,
      	loadComplete: afterLoad,
	  	<@pop.JugePermissionTag ename="view${moduleName}">
        	ondblClickRow: function(data, iRow,iCol,event){
        	    if(doubleClickTable){
            	   doubleClickTable(event,data);
            	}
        	}
	  	</@pop.JugePermissionTag>
	});
	onOrgChanged();
	$("#searchType").change(function(){
		onOrgChanged();
	});
	$("#reload").click(function(){
		$("#searchCurrentlyAddress").attr("value","请输入常住地址");
		$("#searchType").attr("value",0);
		onOrgChanged();
	});
	$("#search").click(function(){
		onOrgChanged();
	});
	$("#delete").click(function(event){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(event,allValue);
	});

	$("#view").click(function(){
		var selectedCounts = getActualjqGridMultiSelectCount("${lowerCaseModuleName}List");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedCounts>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
			return;
		}
	    doubleClickTable(event,selectedId);
		
	});
	
	
	$("#refreshSearchKey").click(function(){
		$("#searchCurrentlyAddress").attr("value","请输入常住地址");
	});
	
	$("#import").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:"${path}/dataManage/common/import.ftl?isNew=1&dataType=${lowerCaseModuleName}&dialog=noticeDialog&startRow=6&templates=${upperCaseModuleName}&listName=${lowerCaseModuleName}List",
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
	
	$("#update").click(function(){
		var selectedCounts = getActualjqGridMultiSelectCount("${lowerCaseModuleName}List");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedCounts>1){
			$.messageBox({level:'warn',message:"请选择一条未认领记录，再进行修改！"});
			 return;
		}
		var ent =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(ent.claimState==0||ent.claimState=='0'){
			 $.messageBox({level:'warn',message:"该${moduleCName}信息已经认领，不能修改!"});
			 return;
		}
		updateTempById(event,selectedId);
	});

	$("#claim").click(function(){
		var allValue =getSelectedIds();
		if(allValue.length ==0||allValue==""){
			$.messageBox({level:'warn',message:"请选择一条或多条未认领记录，再进行认领！"});
			 return;
		}
		if (!isAllUnClaim()) {
			$.messageBox({level:'warn',message:"所选记录中有已认领记录！请选择一条或多条未认领记录，再进行认领！"});
			return;
		}
		if(isRepeat(allValue)) {
			$.messageBox({level:'warn',message:"所选记录中有重复记录！请先删除重复记录，再进行认领！"});
			return;
		}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width: 300,
			height: 300,
			title:"认领${moduleCName}信息",
			url:'${path}/dataManage/${lowerCaseModuleName}Manage/claimDispatch.action?populationIds='+allValue,
			buttons: {
				"确定" : function(event){
			      	$("#maintainClaimForm").submit();
			   	},
			   	"关闭" : function(){
			        $(this).dialog("close");
			   	}
			}
		});
	});

	function isRepeat(allValue){
		for(var i =0;i<allValue.length;i++){
			for(var j =i+1;j<allValue.length;j++){
				if($("#${lowerCaseModuleName}List").getRowData(allValue[i]).idCardNo.replace(/<[^>]+>/g,"")==$("#${lowerCaseModuleName}List").getRowData(allValue[j]).idCardNo.replace(/<[^>]+>/g,"")){
					return true;
				}
			}
		}
		 return false;
	}
	$("#unClaim").click(function (){
		var allValue = getActualjqGridMultiSelectIds("${lowerCaseModuleName}List");
		if(allValue.length ==0||allValue==""){
			$.messageBox({level:'warn',message:"请选择一条或多条已认领记录，再进行撤销认领！"});
			 return;
		}
		if (!isAllClaim()) {
			$.messageBox({level:'warn',message:"所选记录中有未认领记录！请选择一条或多条已认领记录，再进行撤销认领！"});
			return;
		}
		$.confirm({
			title:"确认撤销认领",
			message:"确认撤销认领吗？一经撤销认领，基础数据相对应数据会被删除！",
			okFunc: function() {
				$.ajax({
					url:'${path}/dataManage/${lowerCaseModuleName}Manage/update${moduleName}ForUnClaim.action?populationIds='+allValue,
					success:function(responseData){
						$("#${lowerCaseModuleName}").trigger("reloadGrid");
					    $.messageBox({message:"已经成功撤销认领${moduleCName}信息!"});
					    onOrgChanged();
					}
				});
		    }
	    });
	});
	
});

function isAllClaim(){
	var selectedIds = $("#${lowerCaseModuleName}List").getGridParam("selarrrow");
	for(var i = 0; i < selectedIds.length; i++){
		if($("#${lowerCaseModuleName}List").getRowData(selectedIds[i]).claimState == '未认领'){
			return false;
		}
	}
	return true;
}

function isAllUnClaim(){
	var selectedIds = $("#${lowerCaseModuleName}List").getGridParam("selarrrow");
	for(var i = 0; i < selectedIds.length; i++){
		if($("#${lowerCaseModuleName}List").getRowData(selectedIds[i]).claimState == '已认领'){
			return false;
		}
	}
	return true;
}

function viewDialog(event,id){
	$("#${lowerCaseModuleName}Dialog").createDialog({
		width:dialogWidth,
		height:580,
		title:"查看${moduleCName}信息",
		url:'${path}/dataManage/${lowerCaseModuleName}Manage/dispatchOperate.action?mode=view&population.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}

function doubleClickTable(event,selectedId){
	if(selectedId==null){
		 return;
	}
	viewDialog(event,selectedId);
}

function afterLoad(){
	disableButtons();
}

function disableButtons(){
	
}

function selectRow(){
	
}

function getSelectedIds(){
	var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
//重复数据修改
function dispatchOperateOrClaimById(selectedId,idcardNo,claimOrgId){
	$("#${lowerCaseModuleName}Dialog").createDialog({
		width:1000,
		height:550,
		title:"修改${moduleCName}",
		url:"${path}/dataManage/${lowerCaseModuleName}Manage/dispatchOperateOrClaimById.action?mode=edit&population.id="+selectedId+"&idcardNo="+idcardNo+"&claimOrgId="+claimOrgId,
		buttons :{
			"保存并认领" : function(){
				$("#maintainForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
//修改
function updateTempById(event,id){
	$("#${lowerCaseModuleName}Dialog").createDialog({
		width: 800,
		height: 600,
		title:"修改${moduleCName}",
		url:'${path}/dataManage/${lowerCaseModuleName}Manage/dispatchOperate.action?population.id='+id+'&mode=edit',
		buttons: {
		   "保存并认领" : function(event){
		        $("#maintainForm").submit();
		   },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
//删除
function deleteOperator(event,allValue){
	if(!isAllUnClaim()){
		$.messageBox({level:'warn',message:"所选记录中有已认领记录！请选择一条或多条未认领记录，再进行删除！"});
		 return;
	}
	var str="确定要删除吗?一经删除将无法恢复";
	$.confirm({
		title:"确认删除",
		message:str,
		okFunc: function() {
			$.ajax({
				url:"${path}/dataManage/${lowerCaseModuleName}Manage/delete${moduleName}ByIds.action?populationIds="+allValue,
				success:function(data){
				    $.messageBox({message:"已经成功删除该${moduleCName}信息!"});
				    disableButtons();
					$("#${lowerCaseModuleName}List").trigger("reloadGrid");
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
}
</script>