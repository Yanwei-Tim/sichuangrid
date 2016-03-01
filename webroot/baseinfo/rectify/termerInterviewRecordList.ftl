<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<#--<@s.include value="/common/orgSelectedComponent.jsp"/>-->
		    <@s.include value="/common/orgSelectedTaskListComponent.jsp"/>
			<div class="ui-widget autosearch">
			    <input class="basic-input" type="text" value="请输入帮扶人员姓名" name="termerRecordVo.fastSearchCondition" id="searchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入帮扶人员姓名':this.value;" onfocus="value=(this.value=='请输入帮扶人员姓名')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
			<a href="javascript:;" id="fastSearchButton1"><span>搜索</span></a>
		  <#-- <@pop.JugePermissionTag ename="searchTermerRecord">
				<a id="search" href="javascript:void(0)"><span><strong
						class="ui-ico-cx"></strong>高级搜索</span></a>
				<span class="lineBetween"></span>
			</@pop.JugePermissionTag>-->
			<span class="lineBetween"></span>
		<@pop.JugePermissionTag ename="addTermerRecord">
			<a id="add1" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong>新增</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="deleteTermerRecord">
			<a id="delete1" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong>批量删除</span></a>
		</@pop.JugePermissionTag>
		<a id="reload1" href="javascript:void(0)"><span><strong
				class="ui-ico-refresh"></strong>刷新</span></a>
	
	</div>
	<input type="hidden" name="" id="termerInfoId" value="${(id)!}"/>
	<div style="width: 100%;" class="click_load">
		<table id="termerRecordList"></table>
		<div id="termerRecordListPager"></div>
	</div>
	<div id="termerRecordDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#termerRecordList").jqGridFunction({
		datatype: "local",
		multiselect:true,
		colModel:[
			{name:"id",index:"id",sortable:false,hidden:true},
		    {name:'createUserName',index:"createUserName",label:'走访人',sortable:false,width:100,align:"center"},
		    {name:'createDate',index:"createDate",label:'走访时间',sortable:true,width:150},
		    {name:"helpPeople",index:"helpPeople",label:'帮扶人员',width:100,sortable:false,hidden:false},
		    {name:'hasException',label:'有无异常',sortable:false,align:"center", width:150,hidden:true,formatter:hasExceptionFormatter},
		    {name:'exceptionSituationInfo',label:'异常情况',sortable:false,width:240}
		],
		ondblClickRow: viewTermerRecord
	});
	$("#termerRecordList").jqGrid('setFrozenColumns');
	//新增按钮事件
	$("#add1").click(function(event){
	var termerInfoId=$("#termerInfoId").val();
		if(!isConfigTaskGrid()){
			$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
			return;
		}
		$("#termerRecordDialog").createDialog({
			title:"新增社区服刑人员记录",
			width: 600,
			height: 450,
			url:"${path}/plugin/taskListManage/termerRecord/dispatch.action?addFlag=true&mode=add&termerInfoId="+termerInfoId,
			buttons: {
				"保存" : function(event){
		   			$("#termerRecordForm").submit();
				},
				"关闭" : function(event){
		   			$(this).dialog("close");
				}
			}
		});
	});
	//刷新按钮事件绑定
	$("#reload1").click(function(event){
		$("#searchText").val("请输入帮扶人员姓名");
		getTermerRecordList();
	});
	
	$("#refreshSearchKey").click(function(){
		$("#searchText").val("请输入帮扶人员姓名");
	});
	
	$("#fastSearchButton1").click(function(event){
		var fastSearchCondition = $("#searchText").val();
		if(fastSearchCondition == "请输入帮扶人员姓名"){
		
		}else {
			var postData={
				"termerRecordVo.organization.id":selectConfigTaskOrg(),
				"termerRecordVo.fastSearchCondition":fastSearchCondition,
				"termerRecordVo.termerId":$("#termerInfoId").val()
			};
			if(isConfigTaskSelect()){
				$.extend(postData,{"termerRecordVo.mode":"gridConfigTask","termerRecordVo.funOrgId": $("#funOrgId").val()})
			}
			$("#termerRecordList").setPostData(postData);
			$("#termerRecordList").trigger("reloadGrid");
		}
	});
	
		getTermerRecordList();

	
	$("#delete1").click(function(){
		var ids = $("#termerRecordList").jqGrid("getGridParam", "selarrrow");
		if(ids.length < 1){
			$.messageBox({level:'warn',message:"没有选中数据，无法对社区服刑人员记录进行删除操作！"});
		}else {
			deleteTermerRecordOperator(ids);
		}
	});
});

	//列表显示（包括快速过滤）
	function getTermerRecordList(){
		$("#termerRecordList").setGridParam({
			url:"${path}/plugin/taskListManage/termerRecord/findInterViewTermer.action",
			datatype: "json",
			page:1,
			mytype:"post"
		});
		var postData={
			"termerRecordVo.organization.id":selectConfigTaskOrg(),
			"termerRecordVo.termerId":$("#termerInfoId").val()
		};
		if(isConfigTaskSelect()){
			$.extend(postData,{"termerRecordVo.mode":"gridConfigTask","termerRecordVo.funOrgId": $("#funOrgId").val()})
		}
		$("#termerRecordList").setPostData(postData);
		$("#termerRecordList").trigger("reloadGrid");
	}
	
	//删除服务记录
	function deleteTermerRecordOperator(selectedIds){
		var flag1 = false;
		var flag2= false;
		for(var i=0;i<selectedIds.length;i++){
			var termerRecord =  $("#termerRecordList").getRowData(selectedIds[i]);
			if(termerRecord.internalId>USER_ORG_LEVEL){
				flag1 = true;
			}
			if(termerRecord.status == '是'){
				flag2 = true;
			}
		}
		if(flag1){
			$.messageBox({level:'warn',message:"选中的社区服刑人员记录层级高于当前登录层级，无法对该社区服刑人员记录进行删除操作！"});
			return;
		}
	    if(flag2){
			$.messageBox({level:'warn',message:"选中的社区服刑人员记录已签收，无法对该社区服刑人员记录进行删除操作！"});
			return;
		}
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function(){
				$.ajax({
					url:"${path}/plugin/taskListManage/termerRecord/deleteTermerRecords.action?ids="+selectedIds,
					success:function(data){
						if(data>0){
						    $.messageBox({message:"成功删社区服刑人员记录!"});
							$("#termerRecordList").trigger("reloadGrid");
						}else{
							$.messageBox({
								message:"删除社区服刑人员记录出错!",
								level:"warn"
							});
						}
					}
				});
			}
		});
	}
	
	//高级搜索
	function searchTermerRecords()
	{
		$("#termerRecordList").setGridParam({
			url:"${path}/plugin/taskListManage/termerRecord/findInterViewTermer.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchTermerRecordForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
	 		if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}
		}
		var postData={
			"termerRecordVo.organization.id":selectConfigTaskOrg(),
			"termerRecordVo.termerId":$("#termerInfoId").val()
		};
		if(isConfigTaskSelect()){
			$.extend(postData,{"termerRecordVo.mode":"gridConfigTask","termerRecordVo.funOrgId": $("#funOrgId").val()})
		}
		$("#termerRecordList").setPostData(
			$.extend(dataJson,postData));
		$("#termerRecordList").trigger("reloadGrid");
	}

   function refreshList(searchText){
	var orgId=selectConfigTaskOrg();
	$("#termerRecordList").setGridParam({
		url:"${path}/plugin/taskListManage/termerRecord/findInterViewTermer.action",
		datatype: "json",
		page:1
	});
	var postData={
		"termerRecordVo.organization.id":selectConfigTaskOrg(),
		"termerRecordVo.termerId":$("#termerInfoId").val()
	};
	if(isConfigTaskSelect()){
		$.extend(postData,{"termerRecordVo.mode":"gridConfigTask","termerRecordVo.funOrgId": $("#funOrgId").val()})
	}
	$("#termerRecordList").setPostData(postData);
	$("#termerRecordList").trigger("reloadGrid");
  } 

	function viewTermerRecord(selectedId) {
		$("#termerRecordDialog").createDialog({
			width: 550,
			height: 400,
			title: '查看社区矫正人员走访信息',
			url:"${path}/plugin/taskListManage/termerRecord/viewInterViewTermer.action?mode=view&id="+selectedId,
			buttons: {
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	
	
	function operaterFormatter(el, options, rowData){
		if(rowData.status == 0){
			return "<@pop.JugePermissionTag ename='deleteTermerRecord'><a href='javascript:' onclick='deleteTermerRecordOperator("+rowData.id+")'><span>删除</span></a></@pop.JugePermissionTag>";
		}else {
			return "无";
		}
	}
	
	function hasExceptionFormatter(el, options, rowData){
		if(rowData.hasException == 1){
			return "有";
		}else {
			return "无";
		}
	}

</script>
