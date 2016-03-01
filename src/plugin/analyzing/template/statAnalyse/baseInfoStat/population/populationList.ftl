<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div id="nav" class="ui-corner-all">
		<@s.if test="#parameters.populationType == null">
			<select id="type" style="float:right;">
			<option value="1">各区域人口数量统计表1</option>
			<option value="2">各层级人口数量统计表2</option>
			</select>
		</@s.if>
		<@s.else>
			<input id="type" type="hidden" value="1" />
		</@s.else>
		<select name="queryYear" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="month" onchange="">
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
<div id="populationSituationDlg"></div>
<script type="text/javascript">
if($("#currentOrgId").attr("orglevelinternalid") <= <@pop.static value='@com.tianque.domain.property.OrganizationLevel@DISTRICT' />){
	$("#type option:last").remove();
}
var grid = null;
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
}
function onOrgChanged(){
	var populationType='<@s.property value="#parameters.populationType[0]"/>';
	switch($("#type").val()){
	case '1':
		getStatisticsPopulationList(null,populationType);
		break;
	case '2':
		getStatisticsPopulationList(2,populationType);
		break;
	}
	enableOrDisableColumn(1);
}

function getStatisticsPopulationList(orgLevelDistance,populationType){
	var url = "/baseInfoStat/statisticsPopulation/getStatisticsPopulationList.action?orgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&populationType="+populationType;
	if(orgLevelDistance != null){
		url = url + "&orgLevelDistance="+orgLevelDistance;
	}
	$.ajax({
			url:url,
			success:function(data){
				if(data == null || (data[0]!= null &&data[0].org == null)){
						$.messageBox({
							message:"查询的月份没有数据生成",
							level: "error"
						});
						return;
					}
				grid.bindData(data);
			}
		})
}

$(document).ready(function(){

	if(document.title!='总况'){
		$("#createStatistic").show();
	}

	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#year").val());
	$("#isNowMonth").val($("#month").val());
	$.gridboxHeight();
	var context = {};
	var columns = [
			{name:"org.orgName",caption:"区域",width:120,mode:"string"},
			{name:"general",caption:"总况",children:[
				{name:"populationDetailDatas[index].name",caption:"类型",width:150,mode:"string"},
				{name:"populationDetailDatas[index].amount",caption:"数量",width:80,mode:"string",format:"#",showLink:"populationDetailDatas[index].showLink",href:"shwoInfoInDialog(row.data.populationDetailDatas[index].name, row.data.org.id)"},
				{name:"populationDetailDatas[index].amountPercentage",caption:"百分比",width:80,mode:"number",align:'center',format:"#.00%"}
			]}
		];
	grid = new SigmaReport("gridbox",context,columns);
	onOrgChanged();
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216)});
	//$("#gridbox").css({"overflow": "auto", "height": document.documentElement.clientHeight - $(".systemHeader").height()-250});

	$("#createStatistic").click(function(){
		var populationType='<@s.property value="#parameters.populationType[0]"/>';
		$.ajax({
			url:'/baseInfoStat/statisticsPopulation/createStatisticsPopulationList.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&populationType="+populationType,

			success:function(responseData){

				$.messageBox({message:"报表生成成功"});
			}
		});

	});


	$("#searchList").click(function(){
		onOrgChanged();
	});
	onTypeChanged();
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	$(".print").click(function(){
		var populationType='<@s.property value="#parameters.populationType[0]"/>';
		var url = "${path}/statAnalyse/baseInfoStat/population/populationSituationPrint.ftl?parentOrgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&populationType="+populationType;
		if($("#type").val() == '2'){
			url = url + "&orgLevelDistance=2";
		}
		$("#populationSituationDlg").createDialog({
			width:680,
			height:550,
			title:"打印人员总况",
			url:url,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#populationSituationDlg").dialog("close");
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
	$("#personSituationGridbox").printArea();
	$("#populationSituationDlg").dialog("close");
}


</script>