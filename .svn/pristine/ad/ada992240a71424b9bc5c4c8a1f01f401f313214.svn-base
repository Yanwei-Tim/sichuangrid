<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/common/orgSelectedComponent.jsp"/>

<style type="text/css">
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
</style>
<div id="nav" class="ui-corner-all">
		<select id="type" style="float:right;">
			<option value="1">各层级用户登录情况数量统计表1</option>
			<option value="2">各层级用户登录情况数量统计表2</option>
		</select>
         <div class="grid_4 form-right">
             <label style="float:left;line-height:25px;padding-right:10px;">年度：</label>
         </div>
         <div class="grid_8 form-left">
          <select name="statYear" id="statYear">
          </select>
         </div>
         <div class="grid_4 form-right">
             <label style="float:left;line-height:25px;padding:0 10px;">月份：</label>
         </div>
         <div class="grid_8 form-left">
          <select name="statMonth" id="statMonth" onchange="onMonthChange()">
          </select>
         </div>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
</div>
<div class="container_24">
	<div style="clear: both;"></div>
	<div id="gridbox" class="SigmaReport" style="overflow-x:hidden;overflow-y:auto !important;position:relative;height: 440px;width:100%"></div>
</div>
<div id="MainDialog"></div>
<script type="text/javascript">
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#statYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#statMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
}
$(document).ready(function(){
	if($("#currentOrgId").attr("orglevelinternalid") <= <@s.property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT' />){
		$("#type option:last").remove();
	}

	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#statYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	});
	
	
	if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
		onOrgChanged();
	}

	$("#reload").click(function(event){
	    $("#statYear").val(${(nowYear)!?c});
	    $("#statMonth").val(${nowMonth})
		onOrgChanged();
	});

	$("#search").click(function(event){
		if(!$("#statYear").attr("disabled") && $("#statMonth").attr("disabled")){
			onOrgChanged();
			return ;
		}
		if(!$("#statYear").attr("disabled") && $("#statMonth").attr("disabled")){
	    	$.notice({
				level:'warn',
				message:'请选择完整的查询时间'
			});
			return ;
		}
		onOrgChanged();
	});	
	onTypeChanged();
	$("#statYear").change(function(){
		$("#statMonth").empty();
		getmonth();
	});
	
});

function onTypeChanged() {
	$("#type").change(function(){
		onOrgChanged();
	});
}
function reloadReportDate(){
	$.ajax({
		async: false,
		url: '${path}/baseinfo/loginManage/findLoginManageForListPage.action',
		data:{
			"loginManage.year":$("#statYear").val(),
			"loginManage.month":$("#statMonth").val(),
			"loginManage.organization.id":getCurrentOrgId(),
			"type":$("#type").val()
		},
		success:function(responseData){
			rebuildeGrid(responseData);
		}
	});
}
var blueRender = new function(){
	setTimeout('this.paint',100);
	this.paint = function(grid,row,col){
		var data = row.getCellValue(col);
		if(data===null || data==="" || data===undefined) date= "0";
		if(row.getCellValueByColName('workday_month')==row.getCellValueByColName('loggedday_month'))
			return "<span style='color:green;font-weight:bold;'>"+data+"</span>";
		else
			return ""+data+"";
	}
}

var redRender = new function(){
	setTimeout('this.paint',100);
	this.paint = function(grid,row,col){
		var data = row.getCellValue(col);
		if(data===null || data==="" || data===undefined) date= "0";
		if(data<3)
			return "<span style='color:red;font-weight:bold;'>"+data+"</span>";
		else
			return ""+data+"";
	}
}

function rebuildeGrid(reportData){
   	var context = {};
   	var columns = [		
   			{name:"bigTitle",caption:reportData.bigTitle,children:[
				{name:"orgName",caption:"组织结构名称",width:100,mode:"string"}, 
				{name:"allLoginCount",caption:"总账号数",width:85,mode:"number",format:"#",align:"center"},
				{name:"threeMonthsLoginCount",caption:"1-3个月未登录",width:70,mode:"number",format:"#",align:"center"},
				{name:"outThreeMonthsLoginCount",caption:"3个月以上未登录",width:70,mode:"number",format:"#",align:"center"},	
				{name:"nerverLoginCount",caption:"从未登录",width:70,mode:"number",format:"#",align:"center"}
   			]}			
   		];
	grid = new SigmaReport("gridbox",context,columns);
	grid.registerRender("blueRender",blueRender);
	grid.registerRender("redRender",redRender);
	grid.bindData(reportData.objectDataList);
}

function onOrgChanged(){
	reloadReportDate();
	toPrintPage();
	
}
function toPrintPage(){
	$(".print").bind("click",function(){
		$("#MainDialog").createDialog({
			width:920,
			height:550,
			title:"打印登陆统计",
			url:'${path}/statAnalyse/orgLoginStanals/loginManagePrint.ftl?orgId='+getCurrentOrgId()+'&nowYear='+$("#statYear").val()+'&nowMonth='+$("#statMonth").val()+'&type='+$("#type").val(),
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#MainDialog").dialog("close");
			   }
			}
		});
	});
}

function getStatDate(){
	var statMonth;
	if($("#statMonth").val()<10){
    	statMonth="0"+$("#statMonth").val();
    }else{
        statMonth=$("#statMonth").val();
    }
    return $("#statYear").val() + "" + statMonth;
}
function onMonthChange(){
	if($("#statYear").val() >= ${(nowYear)!?c} && $("#statMonth").val() >= ${nowMonth})
        $("#audit").buttonDisable();
}

function print(){
	$("#loginManagePrint").printArea();
	$("#MainDialog").dialog("close");
}
</script>