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
	<@pop.JugePermissionTag ename="export">
		<a id="export"  href="javascript:void(0)" style="margin-top:4px;"><span>导出</span></a>
	</@pop.JugePermissionTag>
	
</div>

<div style="width:100%">
	<table id="fansStatisticList"></table>
	<div id="fansStatisticListPager"></div>
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
	
	
	var org=getCurrentOrgId();
	function onOrgChanged(orgId,isgrid){
		var initParam = {
			"statisticAnalysisVo.org.id":orgId,
			"statisticAnalysisVo.startDate":$("#startDate").val(),
			"statisticAnalysisVo.endDate":$("#endDate").val()
		}
		$("#fansStatisticList").setGridParam({
			url:'${path}/statisticAnalysis/findFansStatisticAnalysis.action',
			datatype: "json",
			page:1
		});
		$("#fansStatisticList").setPostData(initParam);
		$("#fansStatisticList").trigger("reloadGrid");
	}
	
	var colModel=[
			{name:'nickName',label:'备注名',sortable:false,index:'nickName',align:'center'},
			{name:'fanName',label:'微信昵称',index:'fanName',sortable:false,align:'center'},
			{name:'groupName',label:'所属单位分组',index:'groupName', sortable:false,align:'center'},
			{name:'reportNum',label:'事件上报数量',index:'reportNum',sortable:false,align:'center'},
			{name:'acceptNum',label:'受理事件数量',sortable:true,index:'acceptNum',align:'center'},
			//{name:'issueNumber',label:'有效事件数',index:'issueNumber', sortable:true,align:'center'},
			{name:'forwardingNum',label:'转发事件数量',index:'forwardingNum', sortable:true,align:'center'}
		]
	$("#fansStatisticList").jqGridFunction({
		url:'${path}/statisticAnalysis/findFansStatisticAnalysis.action',
		datatype: "json",
		postData:{"statisticAnalysisVo.org.id":getCurrentOrgId(),"statisticAnalysisVo.startDate":$("#startDate").val(),"statisticAnalysisVo.endDate":$("#endDate").val()},
		colModel: colModel,
		showColModelButton:true,
		multiselect:false,
		showColModelButton:false
	});
    $("#fansStatisticList").trigger("reloadGrid");
	
	$("#search").click(function(){
    	var postData = $("#fansStatisticList").getPostData();
    	
    	postData["statisticAnalysisVo.org.id"]= getCurrentOrgId();
	 	postData["statisticAnalysisVo.startDate"]=$("#startDate").val();
	 	postData["statisticAnalysisVo.endDate"]=$("#endDate").val();
	 	
	 	$("#fansStatisticList").trigger("reloadGrid");
    });
 
	
	$("#export").click(function(){
		if ($("#fansStatisticList").getGridParam("records")>0){
				$("#recordStatisticalDialog").createDialog({
					width: 250,
					height: 200,
					title:'导出微信单位人员统计信息',
					url:PATH+'/common/exportExcel.jsp',
					postData:{
						gridName:'fansStatisticList',
						downloadUrl:'${path}/statisticAnalysis/downFansStatisticAnalysis.action'
						},
					buttons: {
			   			"导出" : function(event){
							$("#exceldownload").submit();
			        		$(this).dialog("close");
		   				},
			   			"关闭" : function(){
			        		$(this).dialog("close");
			   			}
					},
					shouldEmptyHtml:false
				});
			}else{
				$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
			}
	});
	
});

</script>