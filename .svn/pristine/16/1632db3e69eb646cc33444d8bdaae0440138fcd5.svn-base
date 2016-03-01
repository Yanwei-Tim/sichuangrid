<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content">
	<#--<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			 <@s.include value="/common/orgSelectedComponent.jsp"/>
		    <@s.include value="/common/orgSelectedTaskListComponent.jsp"/>
		</div>
	</div>-->
	<input type="hidden" name="" id="termerId" value="${(id)!}" />
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
		colModel:[
			{name:"id",index:"id",sortable:false,hidden:true},
		    {name:'createUserName',index:"createUserName",label:'走访人',sortable:false,width:100,align:"center"},
		    {name:'createDate',index:"createDate",label:'走访时间',sortable:true,width:150},
		    {name:"helpPeople",index:"helpPeople",label:'帮扶人员',width:100,sortable:false,hidden:false},
		    {name:'hasException',label:'有无异常',sortable:false,align:"center", width:150,hidden:true,formatter:hasExceptionFormatter},
		    {name:'exceptionSituationInfo',label:'异常情况',sortable:false,width:190}
		],
	});
	$("#termerRecordList").jqGrid('setFrozenColumns');
		getTermerRecordList();
	//列表显示（包括快速过滤）
	function getTermerRecordList(){
		$("#termerRecordList").setGridParam({
			url:"${path}/plugin/taskListManage/termerRecord/findInterViewTermer.action",
			datatype: "json",
			page:1,
			mytype:"post"
		});
		var postData={
			"termerRecordVo.organization.id":getCurrentOrgId(),
			"termerRecordVo.termerId":$("#termerId").val()
		};
		<#--if(isConfigTaskSelect()){
			$.extend(postData,{"termerRecordVo.mode":"gridConfigTask","termerRecordVo.funOrgId": $("#funOrgId").val()})
		}-->
		$("#termerRecordList").setPostData(postData);
		$("#termerRecordList").trigger("reloadGrid");
	}
 });
	function hasExceptionFormatter(el, options, rowData){
		if(rowData.hasException == 1){
			return "有";
		}else {
			return "无";
		}
	}
</script>
