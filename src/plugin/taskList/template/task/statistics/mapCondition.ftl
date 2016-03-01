<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="search-condition-form" title="地图分析条件设置" class="taskListContent">
		 
		<div class="dateSelect">
			<span class="taskTit" style="margin-left: 18px;">时间：</span>
			<select  id="timeColumn" class="mySelect">
				<option value="0">月</option>
				<#--<option value="1">季度</option>
				<option value="2">年</option>-->
			</select>
			<span id="yearAndMonthColumn">
				<select id="yearColumn"  class="mySelect"></select> 
				<em class="taskTit">年 </em>
				<select id="monthColumn" class="mySelect" ></select>
			</span>
			<#--<span id="yearAndQuarterColumn">
				<select id="yearQuarterColumn" class="mySelect">
				</select> 年 
				<select id="quarterColumn" class="mySelect"></select> 
			</span>
			<span id="yearAndyearTypeColumn">
				<select id="searchYearColumn"  class="mySelect">
				</select> 年 
				<select id="yearType" class="mySelect">
					<option value="0">上半年</option>
					<option value="1">下半年</option>
					<option value="2">全年</option>
				</select>
			</span>-->
		</div>
		<div style="margin-top:10px;" class="btnbanner" id="reportStatusPie">
			<span class="taskTit">任务状态：</span>
			<input type="radio" id="sign" name="statusValuePie" value="1" checked="checked" /> <em class="taskTit">签收 </em>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" id="report" name="statusValuePie" value="0"  class="myCheckBox"/> <em class="taskTit">上报</em>
		</div>
   		 
</div>
<script>
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
<#--
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
}-->

function getYear(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#yearColumn").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
				<#--$("#yearQuarterColumn").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
				$("#searchYearColumn").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); -->
			}
			getmonth();
			<#--getQuarter();-->
		}
	});
}

$(document).ready(function(){
	<#--$("#yearAndQuarterColumn").hide();
	$("#yearAndyearTypeColumn").hide();-->
	
	//判断记录选中任务状态的值，设置任务状态默认值
	if(statusBySet == false){
		$("#sign").removeAttr("checked");
		$("#report").attr("checked",true);
	}
	getYear();
	
	<#--$("#yearQuarterColumn").change(function(){
		$("#quarterColumn").empty();
		getQuarter();
	});-->

	$("#yearColumn").change(function(){
		$("#monthColumn").empty();
		getmonth();
	});
	
	<#--$("#timeColumn").change(function(){
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
	});-->
});

</script>