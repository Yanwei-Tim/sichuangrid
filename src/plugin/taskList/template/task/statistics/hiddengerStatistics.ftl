<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<style type="text/css">
	.mySelect{width:80px;}
	.mySelect1{width:180px;}
	.myCheckBox{margin-left:10px;}
	.myDivTop{margin-top:5px;}
</style>

<div id="content" class="content myDivTop taskListContent">
	<div class="ui-corner-all" id="nav">
		<div class="areaSelect">
			<span class="taskTit">区域选择：</span>
			<@s.include value="/common/orgSelectedComponent.jsp"/>  
		</div>
		<div class="dateSelect">
			<span class="taskTit">时间：</span>
			<select  id="timeColumn" class="mySelect">
				<option value="0">月</option>
				<option value="1">季度</option>
				<option value="2">年</option>
			</select>
			<span id="yearAndMonthColumn">
				<select id="yearColumn"  class="mySelect"></select>
				<em class="taskTit">年 </em>  
				<select id="monthColumn" class="mySelect" ></select>
			</span>
			<span id="yearAndQuarterColumn">
				<select id="yearQuarterColumn" class="mySelect"></select>
				<em class="taskTit">年 </em> 
				<select id="quarterColumn" class="mySelect"></select> 
			</span>
			<span id="yearAndyearTypeColumn">
				<select id="searchYearColumn"  class="mySelect"></select>
				<em class="taskTit">年 </em>  
				<select id="yearType" class="mySelect">
					<option value="0">上半年</option>
					<option value="1">下半年</option>
					<option value="2">全年</option>
				</select>
			</span>
		</div>
		<!-- dateSelect -->
		<div class="taskBtn">
			<a href="javascript:;" style="margin-left:12px;top:-4px;" id="columnSearch" ><span class="taskTit">搜索</span></a>
		</div>
		<!-- taskBtn -->
	</div>
	<!-- nav -->

	<div class="taskSearchCondition">
		<div style="margin-top:10px;" id="taskTypeColumn">
			<span class="taskTit">任务类别：</span>
			<input name='selectTypes' id='pandect'  value='pandect' type='checkbox'  checked/>
			<em class='taskTit'>总况 </em>
		</div>
		 
		<div style="margin-top:10px;" class="btnbanner" id="reportStatusPie">
			<span class="taskTit">任务状态：</span>
			<input type="checkbox" id="sign" name="statusValuePie" value="1" checked="checked" /> <em class="taskTit">签收 </em>  
			<input type="checkbox" id="report" name="statusValuePie" value="0" class="myCheckBox"/> <em class="taskTit">上报</em>
		</div>
	</div>
	<div class="zt_tabs_style">
		<div id="chartsTabs">
			<ul>
				<li id="chartsTabs1"><a href="${path }/task/statistics/policeStatisticsColumn.ftl?type=<@s.property value="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE"/>">区域分布图</a></li>
				<li id="chartsTabs2"><a href="${path }/task/statistics/policeStatisticsPie.ftl?type=<@s.property value="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE"/>">类型分布图</a></li>
				<li id="chartsTabs3"><a href="${path }/task/statistics/policeStatisticsTrend.ftl?type=<@s.property value="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE"/>">趋势图</a></li>
				<#--<li id="chartsTabs4"><a href="${path }/task/statistics/hiddengerStatisticsList.ftl">治安隐患报表</a></li>-->
			</ul>
		</div>
	</div>
</div> 


<script type="text/javascript">
function clickPropertyDicts(){
	$("[name='selectTypes']").click(function(){
		if($(this).val() != "pandect"){
			$("#pandect").removeAttr("checked");
			changeCheckBox();
		}else{
			changeCheckBox(0);
		}
		});
}
function dealPropertyDicts(){
	$.ajax({
		async: false,
		type: "GET",
		url: "${path}/sysadmin/propertyManage/findPropertyDictByDomainName.action",
		data:{"propertyDomain.domainName":"治安隐患异常类型"},
		success:function(responseData){
			var bo = true;//判断循环第一次
			var checkValue = "";
			for(var i = 0;i<responseData.length;i++){
				if(bo){
					bo = false;
					checkValue += "<input name='selectTypes' id='"+responseData[i].id+"'  value='"+responseData[i].id+"' type='checkbox' style='margin-left:10px;'/> <em class='taskTit'>"+responseData[i].displayName+" </em>";
				}else{
					checkValue += "<input name='selectTypes' id='"+responseData[i].id+"'  value='"+responseData[i].id+"' type='checkbox' class='myCheckBox'/> <em class='taskTit'>"+responseData[i].displayName+" </em>";
				}
			}
			$("#taskTypeColumn").append(checkValue); 
			clickPropertyDicts();
		}
	});
}

//根据任务类别判断任务状态选中
function changeCheckBox(val){
	var num = 1;
	var first = true;
	$("[name='selectTypes']:checked").each(function(){
		if(num > 1 && first == true){
			statusClick();
			first = false;
		}
		if(val == 0){
			if($(this).val() != "pandect"){
				$(this).removeAttr("checked");
			}
		}
		num++;
	});
}

//判断任务状态选中状态
function statusClick(val){
	if(val != "" && val != undefined){
		$("input[name='statusValuePie']:checked").each(function(){
			if($(this).val() != val){
				$(this).removeAttr("checked");
			}
		});
	}else{
		var num = 1;
		$("input[name='statusValuePie']:checked").each(function(){
			if(num > 1){
				$("#report").removeAttr("checked");
			}
			num++;
		});
	}
}

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#yearColumn").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#monthColumn").append("<option value='"+responseData[i]+"'>"+responseData[i]+"   月</option>");
			}
		}
	});
}
function getQuarter(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForQuarter.action?currenYear="+$("#yearQuarterColumn").val(),
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#quarterColumn").append("<option  value='"+responseData[i]+"'>第 "+responseData[i]+" 季度</option>"); 
			}
		}
	});
}

function getYear(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#yearColumn").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
				$("#yearQuarterColumn").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
				$("#searchYearColumn").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
			getQuarter();
		}
	});
}

$(document).ready(function(){
	$("#yearAndQuarterColumn").hide();
	$("#yearAndyearTypeColumn").hide();
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
	$.sigmaReportLayout();
	$.loadingComp("close");
	getYear();
	$("#yearQuarterColumn").change(function(){
		$("#quarterColumn").empty();
		getQuarter();
	});

	$("#yearColumn").change(function(){
		$("#monthColumn").empty();
		getmonth();
	});
	dealPropertyDicts();
	
	//时间选择点击
	$("#timeColumn").change(function(){
		var thisValue = $(this).val();
		if(thisValue == 0){
			$("#yearAndMonthColumn").show();
			$("#yearAndQuarterColumn").hide();
			$("#yearAndyearTypeColumn").hide();
		}else if(thisValue == 1){
			$("#yearAndMonthColumn").hide();
			$("#yearAndQuarterColumn").show();
			$("#yearAndyearTypeColumn").hide();
		}else{
			$("#yearAndMonthColumn").hide();
			$("#yearAndQuarterColumn").hide();
			$("#yearAndyearTypeColumn").show();
		}
	});
	
	//任务状态单选判断
	$("input[name='statusValuePie']").click(function(){
		if($(this).attr("checked")){//选中状态
			var clickNum = 1; 
			var statusVal = $(this).val();//获取当前任务状态选中的值
			if($("#chartsTabs2").attr("aria-selected") == "true" || $("#chartsTabs3").attr("aria-selected") == "true"){
				statusClick(statusVal)
			}
			$("[name='selectTypes']:checked").each(function(){
				if($(this).val() == "pandect" || clickNum > 1){
					statusClick(statusVal);
					return;
				}
				clickNum++;
			});
		}
	});
	
	$("#columnSearch").click(function(){
		var tabs1 = $("#chartsTabs1").attr("aria-selected");
		var tabs2 = $("#chartsTabs2").attr("aria-selected");
		var tabs3 = $("#chartsTabs3").attr("aria-selected");
		
		var statusValuePie = false;//判断任务状态任务是否有选中
		var selectTypes = false;//判断任务任务类别是否有选中
		$("input[name = 'selectTypes']:checked").each(function(){
			selectTypes = true;
		});
		$("input[name = 'statusValuePie']:checked").each(function(){
			statusValuePie = true;
		});
		if(selectTypes == false){
			$.messageBox({level:'warn',message:"请选择任务类别！"});
			return;
		}
		if(statusValuePie == false){
			$.messageBox({level:'warn',message:"请选择任务状态！"});
			return;
		}
		if(tabs1 == "true"){
			commonStatisticsOnchange();
		}
		if(tabs2 == "true"){
			commonStatisticsOnchangePie();
		}
		if(tabs3 == "true"){
			commonStatisticsOnchangeTrend();
		}
		
	});
});


if(isGrid()){
	$("#chartsTabs").tabs("select", 2);
	$("#chartsTabs").tabs("disable", 0);
	$("#chartsTabs").tabs("disable", 1);
}else{
	$("#chartsTabs").tabs("enable", 0);
	$("#chartsTabs").tabs("enable", 1);
}
</script>