<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/common/orgSelectedComponent.jsp"/>

<div class="container container_24" id="nav">
	<div class="grid_2 lable-right">
		<label>开始日期：</label>
	</div>
	<div class="grid_3 form-left">
		<input type="text" name="" class="form-txt" readonly id="startDate" value="<@s.date name="" format="yyyy-MM-dd"/>" />
	</div>
	<div class="grid_2 lable-right">
		<label>截止日期：</label>
	</div>
	<div class="grid_3 form-left">
		<input type="text" name="" class="form-txt" readonly id="endDate" value="<@s.date name="" format="yyyy-MM-dd"/>" />
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;  
	<a id="search" href="javascript:void(0)" style="margin-top:4px;"><span><strong class="ui-ico-cx"></strong>查询</span></a>
	<@pop.JugePermissionTag ename="exportWechatGroupStatistic">
		<a id="export"  href="javascript:void(0)" style="margin-top:4px;"><span>导出</span></a>
	</@pop.JugePermissionTag>
	
</div>
<div class="container_24">
	<div style="clear: both;"></div>
	<div id="gridbox" class="SigmaReport" style="overflow-x:auto;overflow-y:scroll;position:relative;height:420px;align:left"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var currentDate = new Date();
	$('#startDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		maxDate:'+0d',
	   	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endDate").datepicker("option", "minDate",date);
			}
		}
	});

	$("#startDate").datepicker("setDate",currentDate);

	$('#endDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
	    maxDate:'+0d',
	    onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#startDate").datepicker("option", "maxDate",date);
			}
		}
	});
	$("#endDate").datepicker("setDate",currentDate);
	
	
	
	var context = {};
   	var columns = [		
		{name:"weChatUserName",caption:"公众号名称(所属层级)",width:100,mode:"string",align:"center"},                                                   
		{name:"statisticList[index].groupName",caption:"群组",width:100,mode:"string",align:"center"},   
		{name:"statisticList[index].reportNum",caption:"事件上报数量",width:100,mode:"number",align:"center",format:"#"},   
		{name:"statisticList[index].acceptNum",caption:"受理事件数量",width:100,mode:"number",align:"center",format:"#"},
		//{name:"statisticList[index].availabilityNum",caption:"有效事件数",width:100,mode:"number",align:"center",format:"#"},
		{name:"statisticList[index].forwardingNum",caption:"转发事件数量",width:100,mode:"number",align:"center",format:"#"}
   	];
   	search();
   	
   	$("#search").click(function(){
   		search();
   	});
   	
   	//查询（包括初始情况的列表显示）
	function search(){
		grid = new SigmaReport("gridbox",context,columns,null,null,"微信公众账号使用情况统计分析",null);
		$.ajax({
			async: true,
			url: '${path}/statisticAnalysis/findStatisticAnalysis.action?statisticAnalysisVo.org.id='+getCurrentOrgId()+"&statisticAnalysisVo.startDate="+$("#startDate").val()+"&statisticAnalysisVo.endDate="+$("#endDate").val(),
			success:function(responseData){
				if(responseData == null){
					$.messageBox({
						message:"查询出错",
						level: "error"
					});
					return;
				}
				grid.bindData(responseData);
				
				//加底色
				for(var i=0;i<responseData.length;i++){
					var detail = responseData[i].statisticList;
					
					for(var j=0;j<detail.length;j++){
						if($("#tBody_gridbox").find("td[id='cell@"+[i]+"@statisticList[index].groupName']").eq([j]).text()=="合计"){
							$("#tBody_gridbox").find("td[id='cell@"+[i]+"@statisticList[index].groupName']").eq([j]).css("color","red");
							$("#tBody_gridbox").find("td[id='cell@"+[i]+"@statisticList[index].groupName']").eq([j]).parent().css("background-color","#ffe4e1");
						}
					}
					
				}
			}
		});
	}
	
	$("#export").click(function(){
		var url="${path}/statisticAnalysis/downStatisticAnalysis.action?statisticAnalysisVo.org.id="+getCurrentOrgId()+"&statisticAnalysisVo.startDate="+$("#startDate").val()+"&statisticAnalysisVo.endDate="+$("#endDate").val();
		downloadFile(url);
	});
	
	function downloadFile(url){  
	    var elemIF = document.createElement("iframe");  
	    elemIF.src = url;  
	    elemIF.style.display = "none";  
	    document.body.appendChild(elemIF);  
	}
	
});

</script>