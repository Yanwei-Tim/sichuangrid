<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div>
	<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="PrintDlg"></div>
<script type="text/javascript">
	var fitColumns=true;
		var columns = [
			{name:"orgname",caption:"区域",mode:"string"}
			<@pop.JugePermissionTag ename="druggyTaskReportForm">
				,
				{name:"general",caption:"吸毒人员",children:[
					{name:"druggySum",caption:"网格员发送",width:60,mode:"string"},
					{name:"druggyVisit",caption:"已签收",width:60,mode:"string"}
				]}
			</@pop.JugePermissionTag>
				,
				{name:"general",caption:"严重精神障碍患者",children:[
					{name:"mentalPatientSum",caption:"网格员发送",width:60,mode:"string"},
					<@pop.JugePermissionTag ename="mentalPatientJusticeTaskReportForm">
					{name:"mentalPatientJusticeVisit",caption:"卫生所签收",width:60,mode:"string"},
					</@pop.JugePermissionTag>
					<@pop.JugePermissionTag ename="mentalPatientPoliceTaskReportForm">
					{name:"mentalPatientPoliceVisit",caption:"派出所签收",width:60,mode:"string"
					</@pop.JugePermissionTag>}
				]}
			<@pop.JugePermissionTag ename="termerRecordReportForm">
				,
				{name:"general",caption:"社区服刑人员",children:[
					{name:"rectificativeSum",caption:"网格员发送",width:60,mode:"string"},
					{name:"rectificativeVisit",caption:"已签收",width:60,mode:"string"}
				]}
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="positiveInfoRecordReportForm">
				,
				{name:"general",caption:"刑释人员",children:[
					{name:"positiveSum",caption:"网格员发送",width:60,mode:"string"},
					{name:"positiveVisit",caption:"已签收",width:60,mode:"string"}
				]}
			</@pop.JugePermissionTag>
		];


var grid = null;


function onOrgChanged(orgId){
    var orgId=getCurrentOrgId();
	$.ajax({
		dataType:"json",
		url:'${path }/plugin/taskListManage/common/getSpecialGroupSumAndVisitList.action?orgId='+orgId,
		success:function(data){
			grid.bindData(data);
		}
	})
}

$(document).ready(function(){
	//$("#content").hide();
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns,null,null,"特殊人群统计表",null,null);
	//setTimeout('onOrgChanged()',350);
	onOrgChanged();
	
	$(".print").click(function(){
		
		var url = '${path}/task/reportForm/taskListPrint.ftl?parentOrgId='+getCurrentOrgId()+"&moduleName="+document.title;
		$("#PrintDlg").createDialog({
			width: 1200,
			height:490,
			title:document.title,
			url:url,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#PrintDlg").dialog("close");
			   }
			}
		});
	});
	
	function print(){
		$("#Print").printArea();
		$("#PrintDlg").dialog("close");
	}
})
	

</script>

