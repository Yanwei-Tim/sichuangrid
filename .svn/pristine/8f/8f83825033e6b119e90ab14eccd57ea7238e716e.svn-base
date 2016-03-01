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
					{name:"hiddenDangerSum",caption:"网格员发送",width:60,mode:"string"},
					{name:"hiddenDangerVisit",caption:"已签收",width:40,mode:"string"},
				    {name:"explosion",caption:"易涉暴",width:40,mode:"string"},
					{name:"gunAndExplosion",caption:"涉枪涉爆",width:60,mode:"string"},
				    {name:"makeDruggy",caption:"涉制毒",width:40,mode:"string"},
					{name:"drinkDruggy",caption:"涉吸毒",width:40,mode:"string"},
					{name:"cultActive",caption:"邪教活动",width:60,mode:"string"},
					{name:"fake",caption:"制假贩假",width:60,mode:"string"},
					{name:"yellow",caption:"涉黄",width:30,mode:"string"},
					{name:"drink",caption:"涉赌",width:30,mode:"string"},
					{name:"pyramidSelling",caption:"传销",width:30,mode:"string"},
					{name:"fire",caption:"火灾隐患",width:60,mode:"string"},
					{name:"receiveGood",caption:"收赃",width:30,mode:"string"},
					{name:"destroyGood",caption:"销赃",width:30,mode:"string"},
					{name:"noGuard",caption:"无守楼护院人员",width:80,mode:"string"},
					{name:"otherException",caption:"其他异常事件",width:70,mode:"string"}
		];


var grid = null;


function onOrgChanged(orgId){
    var orgId=getCurrentOrgId();
	$.ajax({
		dataType:"json",
		url:'${path }/plugin/taskListManage/common/getHiddenDangerVisitList.action?orgId='+orgId,
		success:function(data){
			grid.bindData(data);
		}
	})
}

$(document).ready(function(){
	//$("#content").hide();
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns,null,null,"发现治安隐患",null,null);
	$("#title_gridbox").html("发现治安隐患统计表<a href='javascript:;' class='print' title='打印'></a>");
	//setTimeout('onOrgChanged()',350);
	onOrgChanged();
	
	$(".print").click(function(){
		
		var url = '${path}/task/reportForm/hiddenDangerTaskPrint.ftl?parentOrgId='+getCurrentOrgId()+"&moduleName="+document.title;
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

