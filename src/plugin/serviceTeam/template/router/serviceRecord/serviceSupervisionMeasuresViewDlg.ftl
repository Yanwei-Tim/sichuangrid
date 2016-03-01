<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceRecord</@s.param>
</@s.include>
<div class="content">
	<input type="hidden" id="name" value="${(name)!}">
	<div style="width: 100%;">
		<table id="serviceSupervisionMeasuresViewList"></table>
		<div id="serviceSupervisionMeasuresViewListPager"></div>
	</div>
	<div id="serviceRecordDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#serviceSupervisionMeasuresViewList").jqGridFunction({
		//url:'${path}/baseinfo/mentalPatientManage/serviceSupervisionMeasuresPatientList.action?population.id=${(id)!}&serviceSupervisionMeasures.type=${(module)!}',
		datatype: "local",
		multiselect:true,
		height:360,
		colModel:[
			{name:"id",index:"id",sortable:false,hidden:true},
		    {name:'upTime',index:"upTime",label:'时间',sortable:true,width:100,align:"center",formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
		    {name:'supervisepersonnel',index:"supervisepersonnel",label:'监管人员',sortable:true,width:120},
		    {name:'superviseinfo',index:'superviseinfo',label:'监管措施',sortable:false,width:460,align:"center"}
		],
		page:1,
		mytype:"post"
	});
	$("#serviceSupervisionMeasuresViewList").jqGrid('setFrozenColumns');
	getServiceRecordList();
});
	//列表显示（包括快速过滤）
	function getServiceRecordList(){
		$("#serviceSupervisionMeasuresViewList").setGridParam({
			url:'${path}/baseinfo/mentalPatientManage/serviceSupervisionMeasuresPatientList.action?population.id=${(id)!}&serviceSupervisionMeasures.type=${module}',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		$("#serviceSupervisionMeasuresViewList").trigger("reloadGrid"); 
	}
</script>