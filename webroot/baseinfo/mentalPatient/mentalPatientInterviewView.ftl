<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content">
	<#--<div class="ui-corner-all contentNav" id="nav">
	 <div class="btnbanner btnbannerData">
		<div class="btnbanner">
		<@s.include value="/common/orgSelectedComponent.jsp"/>
		    <@s.include value="/common/orgSelectedTaskListComponent.jsp"/>
	    </div>
	    </div>
	    </div>-->
		<div style="clear: both;"></div>
		<input type="hidden" name="" id="mentalPatientId" value="${(id)!}" />
		<div style="width: 100%">
			<table id="mentalPatientTaskList"></table>
			<div id="mentalPatientTaskListPager"></div>
		</div>
		<div id="mentalPatientTaskDialog"></div>
		<div id="importFamilyInfoDialog"></div>
		<div id="searchFamilyInfoDialog"></div>
		<div id="addTaskListReplyDlg"></div>
</div>
<script type="text/javascript">

var orgId=getCurrentOrgId();
var title="严重精神障碍患者信息";
$(document).ready(function(){
	var postData={
		"mentalPatientTask.organization.id":getCurrentOrgId(),
	    "mentalPatientTask.mentalPatientId":$("#mentalPatientId").val()
	};
	<#--if(isConfigTaskSelect()){
		$.extend(postData,{"mentalPatientTask.mode":"gridConfigTask","mentalPatientTask.funOrgId": $("#funOrgId").val()})
	}-->
	$("#mentalPatientTaskList").jqGridFunction({
		url:'${path}/baseInfo/mentalPatientTaskManage/getInterViewMentalPatientList.action',
		datatype: "json",
		postData:postData,
	   	colModel:[
	   	          {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
	   	          {name:"createUserName",index:"createUserName",label:'走访人',sortable:false,hidden:false},
	   	          {name:"createDate",index:"createDate",label:'走访时间',sortable:false,hidden:false,frozen:false},
                  {name:"helpPeople",index:"helpPeople",label:'帮扶人员',sortable:false,hidden:false},
                  {name:'hasException',label:'有无异常',sortable:false,align:"center", width:150,hidden:true,formatter:hasExceptionFormatter},
                  {name:"exception",index:"exception",label:'异常详情',sortable:false,hidden:false,frozen:false}
		   ],
		onSelectAll:function(aRowids,status){},
		showColModelButton:true,
		onSelectRow:function(){}
	});
	
	function hasExceptionFormatter(el, options, rowData){
		if(rowData.hasException == 1){
			return "有";
		}else {
			return "无";
		}
	}	
})
</script>