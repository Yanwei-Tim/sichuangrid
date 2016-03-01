<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div id="nav" class="ui-corner-all">
			<select id="type" style="float:right;">
				<option value="1">网格化服务管理工作情况通报表1</option>
				<option value="0">网格化服务管理工作情况通报表2</option>
			</select>
		<select name="queryYear" id="year" onchange="" style="float:left;">
			<option value="2015">2015</option>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="month" onchange="">
        	<option value="5">5</option>
        	<option value="4">4</option>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>
		<@s.if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
		<a id="createStatistic" hidden="true" href="javascript:void(0)"><span>生成报表</span></a>
		</@s.if>
</div>
<div class="content">
	<div id="gridbox" class="SigmaReport" style="position:relative;overflow:hidden;overflow-y:auto;"></div>
	<div id="number"> </div>
</div>
<div id="userActivateReportDlg"></div>
<script type="text/javascript">
var grid = null;
var sortName="";
var sortColumn="";
<#--
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
}-->
function onOrgChanged(){
	switch($("#type").val()){
	case '0':
		getUserActivateReportList(0);
		break;
	case '1':
		getUserActivateReportList(1);
		break;
	}
}

function getUserActivateReportList(orgLevelDistance){
	var url = "/userActivateReportManageNew/getUserActivateReportList.action?year="+$("#year").val()+"&month="+$("#month").val();
	if(orgLevelDistance != null){
		url = url + "&orgLevelDistance="+orgLevelDistance;
	}
	$.ajax({
			url:url,
			success:function(data){
				if(data == null || (data[0]!= null &&data[0].organization == null)){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
				grid.bindData(data);
			}
		})
}

$(document).ready(function(){
	<#--$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#year").val());
	$("#isNowMonth").val($("#month").val());-->
	$.gridboxHeight();
	var context = {};
	var columns = [
			{name:"organization.orgName",caption:"区域",width:80,mode:"string"},
			{name:"general",caption:"城乡总量",children:[
				{name:"general",caption:"街道（乡镇）",children:[
				{name:"townCount",caption:"数量",width:60,mode:"string",format:"#"}
				]},
				{name:"general",caption:"社区",children:[
				{name:"communityCount",caption:"数量",width:60,mode:"string",format:"#"}
				]},
				{name:"general",caption:"村",children:[
				{name:"villageCount",caption:"数量",width:60,mode:"string",format:"#"}
				]}
			]},
			{name:"general",caption:"已开展工作情况",children:[
				{name:"general",caption:"街道（乡镇）",children:[
				{name:"townActivateCount",caption:"数量",width:60,mode:"string",format:"#"},
				{name:"townAccountPercentage",caption:"百分比",width:60,mode:"string",format:"#"},
				{name:"townMonthCoverageRate",caption:"月活跃度",width:60,mode:"string",format:"#"}
				]},
				{name:"general",caption:"社区",children:[
				{name:"communityActivateCount",caption:"数量",width:60,mode:"string",format:"#"},
				{name:"communityAccountPercentage",caption:"百分比",width:60,mode:"string",format:"#"}
				]},
				{name:"general",caption:"村",children:[
				{name:"villageActivateCount",caption:"数量",width:60,mode:"string",format:"#"},
				{name:"villageAcountPercentage",caption:"百分比",width:60,mode:"string",format:"#"}
				]},
				{name:"general",caption:"社区（村）活跃度",children:[
				{name:"communityWeekCoverageRate",caption:"周活跃度",width:60,mode:"string",format:"#"},
				{name:"communityMonthCoverageRate",caption:"月活跃度",width:60,mode:"string",format:"#"}
				]}
			]},
			{name:"agencyOfOpinionCount",caption:"本月社情民意收集数量",width:40,mode:"string",format:"#"},
			{name:"issueDealCount",caption:"本月事件处理总量",width:40,mode:"string",format:"#"}
		];
		var sortFun = function(name,caption,sort){
		if(name==null || name==""){
			$.messageBox({message: "排序字段未获得，请重新选择",level:"warn"});
			return;
		}
		sortName = name;
		sortColumn=sort;
		var url = "/userActivateReportManageNew/userActivateReportSort.action?year="+$("#year").val()+"&month="+$("#month").val()+"&sortName="+name+"&sort="+sort+"&orgLevelDistance="+$("#type").val();
		$.ajax({
			url:url,
			success:function(data){
				if(data == null){
					$.messageBox({
						message: "帐号覆盖统计排序失败",
						level: "error"
					});
					return;
				}
				grid.bindData(data);
			}
			
		});
	};
	grid = new SigmaReport("gridbox",context,columns,null,null,null,null,sortFun);
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.clientHeight - $(".systemHeader").height()-120});
	$("#title_gridbox").children().remove();
	$("#title_gridbox").html("网格化服务管理工作情况通报表（一）<a href='javascript:;' class='print' title='打印'></a>");
	

	onOrgChanged()
	$("#createStatistic").click(function(){
	var nowDate=new Date(); 
	var nowYear=nowDate.getFullYear();
	var nowMonth=nowDate.getMonth()+1;
		if($("#year").val()==nowYear && $("#month").val()==nowMonth){
			$.messageBox({level:'warn',message:"不能生成当月报表"});
			return;
		}
		$.ajax({
			url:"${path}/userActivateReportManageNew/createUserActivateReportList.action?year="+$("#year").val()+"&month="+$("#month").val(),

			success:function(responseData){
				if(responseData=='true' || responseData==true ){
					$.messageBox({message:"报表生成成功"});
				}else{
					$.messageBox({level:'error',message:responseData});
				}
			}
		});

	});


	$("#searchList").click(function(){
		onOrgChanged();
	});
	onTypeChanged();
	<#--$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});-->

$(".print").click(function(){
		var url = "${path}/statAnalyseNew/userActivateReport/userActivateReportListPrint.ftl?year="+$("#year").val()+"&month="+$("#month").val()+"&sortName="+sortName+"&sort="+sortColumn+"&orgLevelDistance="+$("#type").val();
		$("#userActivateReportDlg").createDialog({
			width:950,
			height:550,
			title:"帐号覆盖统计",
			url:url,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#userActivateReportDlg").dialog("close");
			   }
			}
		});
	});

});

function onTypeChanged() {
	$("#type").change(function(){
		onOrgChanged();
	});
}
function print(){
	$("#userActivateReportPrint").printArea();
	$("#userActivateReportDlg").dialog("close");
}
</script>