<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/common/orgSelectedComponent.jsp"/>
<div id="chartsTabs" style="overflow:auto;">

<div id="gridbox" class="SigmaReport">
</div>
</div>
<div id="pieDialogNew"></div>




<script type="text/javascript">
function dealTeamPie(rowData){
	window._rowData = rowData;
	$("#pieDialog").createDialog({
		width:600,
		height:550,
		title:"基层组织队伍统计图表",
		url:"/statAnalyse/primaryOrganizationStat/teamCountPie.jsp"
	});

}

function dealMemberCount(rowData){
	window._rowData = rowData;
	$("#pieDialog").createDialog({
		width:600,
		height:550,
		title:"基层组织人员统计图表",
		url:"/statAnalyse/primaryOrganizationStat/memberCountPie.jsp"
	});

}
function delay(){
}
var grid = null;
function onOrgChanged(){
	$.ajax({
		url:'${path}/baseInfoStat/searchPrimaryOrganizationDataColumn/getDataColumn.action?parentOrgId='+getCurrentOrgId(),
		success:function(data){
			if(data == null || (data[0]!= null &&data[0].org == null)){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			grid.bindData(data);
		}
	})
}


$(document).ready(function(){
	setTimeout(delay,800);
	var context = {};
	var columns = [
			{name:"org.orgName",caption:"区域",width:100,mode:"string"},
			{name:"general",caption:"综治组织",children:[
   			    {name:"partyCommitteeCount",caption:"队伍数量",width:101,mode:"string",align:'center'},
   			    {name:"partyCommitteeMemberCount",caption:"人数",width:50,mode:"number",format:"#",align:'center'}
   			]},
   			{name:"general",caption:"基层党组织",children:[
   			    {name:"partyTeamCount",caption:"队伍数量",width:101,mode:"string",align:'center'},
   			    {name:"partyTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#",align:'center'}
			]},
   			{name:"general",caption:"基层自治组织",children:[
   			    {name:"autonomyTeamCount",caption:"队伍数量",width:101,mode:"string",align:'center'},
   			    {name:"autonmoyTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#",align:'center'}
   			]},
   			{name:"general",caption:"群防群治队伍",children:[
   			    {name:"masseDutyCount",caption:"队伍数量",width:101,mode:"string",align:'center'},
   			    {name:"masseDutyMemberCount",caption:"人数",width:50,mode:"number",format:"#",align:'center'}
   			]},
   			{name:"general",caption:"社会志愿者队伍",children:[
   			    {name:"postulantTeamCount",caption:"队伍数量",width:101,mode:"string",align:'center'},
   			    {name:"postulantTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#",align:'center'}
   			]}
   			<#--,
   			{name:"general",caption:"专项工作领导小组",children:[
			    {name:"leaderGroupTeamCount",caption:"队伍数量",width:100,mode:"string"},
			    {name:"leaderGroupTeamMemberCount",caption:"人数",width:50,mode:"number",format:"#"}
      		]}-->
		];
	grid = new SigmaReport("gridbox",context,columns);
	onOrgChanged();

	$("#chartsTabs").height(
		$(".ui-layout-center").outerHeight() - $("#thisCrumbs").outerHeight() - 50
	);
	//柱图 饼图
	$(".print").click(function(){
		$("#pieDialogNew").createDialog({
			width:860,
			height:400,
			title:"打印基层组织",
			url:'${path}/statAnalyse/primaryOrganizationStat/print.jsp?parentOrgId='+getCurrentOrgId(),
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#pieDialogNew").dialog("close");
			   }
			}
		});
	});

})
function print(){
	$("#zongkuangPrint").printArea();
	$("#pieDialogNew").dialog("close");
}
</script>


