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
			{name:"orgname",caption:"区域",mode:"string"},
			    {name:"general",caption:"宣传核查",children:[
				{name:"general",caption:"",children:[
					{name:"policeSum",caption:"已发送",width:57,mode:"string"},
					{name:"policeVisit",caption:"已签收",width:57,mode:"string"}
				]}]},
				{name:"general",caption:"民警带领开展工作",children:[
				{name:"general",caption:"",children:[
				    {name:"publicSum",caption:"已发送",width:57,mode:"string"},
					{name:"publicVisit",caption:"已签收",width:57,mode:"string"}
					
				]}]},
				{name:"general",caption:"异常情况报告",children:[
				{name:"general",caption:"大量聚集",children:[
				    {name:"gatherSum",caption:"已发送",width:57,mode:"string"},
					{name:"gatherVisit",caption:"已签收",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"异常气味",children:[
				    {name:"smellSum",caption:"已发送",width:57,mode:"string"},
					{name:"smellVisit",caption:"已签收",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"异常声音",children:[
				    {name:"noiseSum",caption:"已发送",width:57,mode:"string"},
					{name:"noiseVisit",caption:"已签收",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"无身份证",children:[
				    {name:"noIdcardSum",caption:"已发送",width:57,mode:"string"},
					{name:"noIdcardVisit",caption:"已签收",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"群租房人员来往复杂",children:[
				    {name:"groupLiveSum",caption:"已发送",width:57,mode:"string"},
					{name:"groupLiveVisit",caption:"已签收",width:57,mode:"string"}
					
				]},
				{name:"general",caption:"已搬走",children:[
				    {name:"liveSum",caption:"已发送",width:50,mode:"string"},
					{name:"liveVisit",caption:"已签收",width:50,mode:"string"}
					
				]}]},
				{name:"general",caption:"总数",children:[
				{name:"general",caption:"",children:[
				    {name:"floatingPopulationSum",caption:"已发送",width:45,mode:"string"},
					{name:"floatingPopulationVisit",caption:"已签收",width:45,mode:"string"}
				]}]}
			
		];


var grid = null;

function changeTable(){
   $("table td[caption='宣传核查']").attr("rowspan","2");
   $("table td[caption='民警带领开展工作']").attr("rowspan","2");
   $("table td[caption='大量聚集']").prev().remove();
   $("table td[caption='大量聚集']").prev().remove();
   $("table td[caption='总数']").attr("rowspan","2");
   $("table td[caption='已搬走']").next().remove();
   
   $("table td[caption='宣传核查']").attr("style","text-align:center;padding-top:9px;");
   $("table td[caption='民警带领开展工作']").attr("style","text-align:center;padding:9px;");
   $("table td[caption='总数']").attr("style","text-align:center;padding:9px;");
   $("table td[caption='区域']").attr("style","text-align:center;padding:20px;");
}

function onOrgChanged(orgId){
    var orgId=getCurrentOrgId();
	$.ajax({
		dataType:"json",
		url:'${path }/plugin/taskListManage/common/getFloatingPopulationVisitList.action?orgId='+orgId,
		success:function(data){
			grid.bindData(data);
		}
	})
}

$(document).ready(function(){
	//$("#content").hide();
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns,null,null,"",null,null);
	$("#title_gridbox").html("流动人口统计表<a href='javascript:;' class='print' title='打印'></a>");
	//setTimeout('onOrgChanged()',350);
	onOrgChanged();
	changeTable();
	
	$(".print").click(function(){
		
		var url = '${path}/task/reportForm/floatingPopulationTaskPrint.ftl?parentOrgId='+getCurrentOrgId()+"&moduleName="+document.title;
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

