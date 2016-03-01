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

var orgId=getCurrentOrgId();
var title="吸毒人员信息";
$(document).ready(function(){
   var postData={
			"druggyTask.organization.id":orgId,
			"druggyTask.druggyId":$("#druggyId").val()
        };
	<#--if(isConfigTaskSelect()){
		$.extend(postData,{"druggyTask.mode":"gridConfigTask","druggyTask.funOrgId": $("#funOrgId").val()})
	}-->
	$("#druggyTaskList").jqGridFunction({
		url:'${path}/baseInfo/druggyTaskManage/searchInterViewDruggy.action',
		datatype: "json",
		postData:postData,
	   	colModel:[
	   	          {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
                  {name:"createUserName",index:"createUserName",label:'走访人',sortable:false,hidden:false},
                  {name:"createDate",index:"createDate",label:'走访时间',sortable:false,hidden:false,frozen:false},
                  {name:"helpPeople",index:"helpPeople",label:'帮扶人员',sortable:false,hidden:false},
                  {name:'hasException',label:'有无异常',sortable:false,align:"center", width:150,hidden:true,formatter:hasExceptionFormatter},
                  {name:"exception",index:"exception",label:'异常情况',sortable:false,hidden:false,frozen:false,width:120}
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