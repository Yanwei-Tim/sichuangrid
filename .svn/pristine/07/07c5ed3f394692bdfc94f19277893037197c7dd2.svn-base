<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<style type="text/css">
	.mySelect{width:80px;}
	.mySelect1{width:180px;}
	.myCheckBox{margin-left:10px;}
	.myDivTop{margin-top:5px;}
</style>

<div class="content myDivTop">
	<div>
		<div>
			<input name="typeValue" value="0" type="radio" checked="checked"> 月 
			<select id="yearPie" style="margin-left:22px;" class="mySelect"></select> 年 
			<select id="monthPie" class="mySelect" >
			</select> 月 
		</div>
		<div class="myDivTop">
			<input name="typeValuePie" value="1" type="radio"> 季度
			<select id="yearPie1" style="margin-left:10px;" class="mySelect">
			</select> 年 
			<select id="quarterPie" class="mySelect">
			</select>  
		</div>
		<div class="myDivTop">
			<input name="typeValuePie" value="2" type="radio"> 年度 
			<select style="margin-left:10px;" id="yearPie2" class="mySelect1">
				<option value="0">全年</option>
				<option value="1">上半年</option>
				<option value="2">下半年</option>
			</select>
		</div>
		<div style="margin-top:10px;" id="taskTypePie">
			任务类别：
		</div>
		<div style="margin-top:10px;" id="taskTypeChildPie">
			子任务类别：
		</div>
		<div style="margin-top:10px;" id="taskTypeDetailedPie">
			详细类别：
		</div>
		<div style="margin-top:10px;" class="btnbanner" id="reportStatusPie">
			任务状态：
			<input type="radio" name="statusValuePie" value="0" style="margin-left:12px;" checked="checked"/> 上报
			<input type="radio" name="statusValuePie" value="1" class="myCheckBox"/> 签收 
			<a href="javascript:search();" style="margin-left:12px;top:-4px;" ><span>搜索</span></a>
		</div>
		<div style="margin-top:10px;" class="btnbanner" id="druggyReportStatusPie">
			任务状态：
			<input type="radio" value="0" name="statusValueDisplayPie" style="margin-left:12px;" checked="checked"/> 上报
			<input type="radio" value="2" name="statusValueDisplayPie" class="myCheckBox"/> 派出所签收 
			<input type="radio" value="3" name="statusValueDisplayPie" class="myCheckBox"/> 卫生所签收 
			<a href="javascript:search();" style="margin-left:12px;top:-4px;" ><span>搜索</span></a>
		</div>
	</div>
</div>
<div id="taskListinfoPie" class="SigmaReport" style="height:400px;width:100%;"></div>
<script type="text/javascript">
var listType;//子任务类别结果集
var detailedPoliceType;//民警详细任务类别结果集
var detailedUnusualType;//异常详细任务类别结果集
var statusBoolean = true;//任务状态判断，true为不是派出所、卫生所、false为是
function getQuarter(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForQuarter.action?currenYear="+$("#yearPie1").val(),
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#quarterPie").append("<option  value='"+responseData[i]+"'>第"+responseData[i]+"季度</option>"); 
			}
		}
	});
}

function getMonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#yearPie").val(),
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#monthPie").append("<option  value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}

//加载任务类别
function checkBoxType(){
	var bo = true;//判断循环第一次
	var checkValue = "";
	<@s.iterator value="@com.tianque.plugin.statistics.constants.TypeConstants@typeMapName">
		if(bo){
			bo = false;
			checkValue += "<input name='checkNamePie' id='<@s.property value="key"/>Pie' value='<@s.property value="key"/>' type='checkbox'  style='margin-left:12px;'  checked='checked'/> <@s.property value="value"/>";
		}else{
			checkValue += "<input name='checkNamePie' id='<@s.property value="key"/>' value='<@s.property value="key"/>' type='checkbox' class='myCheckBox'/> <@s.property value="value"/>";
		}
	</@s.iterator>
	$("#taskTypePie").append(checkValue);
	checkBoxIsChecked();//加载复选框点击事件
}

//加载民警详细类别
function police(){
	var dataLength = detailedPoliceType.length;
	var bo = true;//判断循环第一次
	var checkValue = "";
	$("#taskTypeDetailedPie").html("详细类别： ");
	for(var i = 0;i<dataLength;i++){
		if(bo){
			bo = false;
			checkValue += "<input name='workingSituationPie' id='"+detailedPoliceType[i].id+"'  value='"+detailedPoliceType[i].id+"' type='checkbox' style='margin-left:12px;'/> "+detailedPoliceType[i].displayName+"";
		}else{
			checkValue += "<input name='workingSituationPie' id='"+detailedPoliceType[i].id+"'  value='"+detailedPoliceType[i].id+"' type='checkbox' class='myCheckBox'/> "+detailedPoliceType[i].displayName+"";
		}
	}
	$("#taskTypeDetailedPie").append(checkValue);
	clickPolice();
}

//初始化异常情形详细类别复选框点击事件
function clickUnusual(){
	$("[name = 'exceptionalSituationRecordPie']").click(function(){
		var clickValue = false;
		$("[name = 'exceptionalSituationRecordPie']").each(function(){
			if($(this).attr("checked")){
				clickValue = true;
			}
		});
		if(clickValue){
			checkBoxByForIsCheck("floatingCheckPie","exceptionalSituationRecord");
		}else{
			checkBoxByForIsNotCheck("floatingCheckPie");
		}
	});
}

//初始化民警..详细类别复选框点击事件
function clickPolice(){
	$("[name = 'workingSituationPie']").click(function(){
		var clickValue = false;
		$("[name = 'workingSituationPie']").each(function(){
			if($(this).attr("checked")){
				clickValue = true;
			}
		});
		if(clickValue){
			checkBoxByForIsCheck("floatingCheckPie","workingSituation");
		}else{
			checkBoxByForIsNotCheck("floatingCheckPie");
		}
	});
}

//加载异常情形详细类别
function unusual(){
	var dataLength = detailedUnusualType.length;
	var bo = true;//判断循环第一次
	var checkValue = "";
	$("#taskTypeDetailedPie").html("详细类别： ");
	for(var i = 0;i<dataLength;i++){
		if(bo){
			bo = false;
			checkValue += "<input name='exceptionalSituationRecordPie'  value='"+detailedUnusualType[i].id+"' type='checkbox' style='margin-left:12px;'/> "+detailedUnusualType[i].displayName+"";
		}else{
			checkValue += "<input name='exceptionalSituationRecordPie'  value='"+detailedUnusualType[i].id+"' type='checkbox' class='myCheckBox'/> "+detailedUnusualType[i].displayName+"";
		}
	}
	$("#taskTypeDetailedPie").append(checkValue);
	clickUnusual();
}


function lastType(val){
	var num = 1;
	$("[name='floatingCheckPie']").each(function(){
			if($(this).attr("checked")){
				num++;
			}
	});
	if(num <= 2){
		if(val == 0){//等于0为民警
			police();
		}else{
			unusual();
		}
		$("#taskTypeDetailedPie").show();
	}else{
		$("#taskTypeDetailedPie").hide();
	}
	
}

function cancelLastType(){
	var num = 1;
	var eachValue = "";
	$("[name='floatingCheckPie']").each(function(){
		if($(this).attr("checked")){
			num++;
			eachValue = $(this).val();
		}
	});
	if(num == 2){
		if(eachValue == "workingSituation"){
			police();
			$("#taskTypeDetailedPie").show();
		}else if(eachValue == "exceptionalSituationRecord"){
			unusual();
			$("#taskTypeDetailedPie").show();
		}		
	}
}

//加载流动人口子类别点击事件
function checkBoxChildIsChecked(){
	$("[name='floatingCheckPie']").click(function(){
		var childCheck = false;
		//判断是否存在选中状态
		$("[name='floatingCheckPie']").each(function(){
			if($(this).attr("checked")){
				childCheck = true;
			}
		});
		if($(this).val() == "workingSituation"){//民警带领..
			if($(this).attr("checked")){
				lastType(0);
			}else{
				$("#taskTypeDetailedPie").hide();
				checkBoxByForIsNotCheck("floatingCheckPie");
				cancelLastType();
			}
		}else if($(this).val() == "exceptionalSituationRecord"){//异常情况..
			if($(this).attr("checked")){
				lastType(1);
			}else{
				checkBoxByForIsNotCheck("floatingCheckPie");
				$("#taskTypeDetailedPie").hide();
				cancelLastType();
			}
		}else{
			if($(this).attr("checked")){
				$("#taskTypeDetailedPie").hide();
			}else{
				cancelLastType();
			}
		}
		if(childCheck){
			checkBoxByForIsCheck("checkNamePie","floating");
		}else{
			checkBoxByForIsNotCheck("checkNamePie");
		}
	});
}

//加载治安隐患子类别点击事件
function checkBoxChildHiddendangerIsChecked(){
	$("[name='hiddendangerPie']").click(function(){
		var childCheck = false;
		$("[name='hiddendangerPie']").each(function(){
			if($(this).attr("checked")){
				childCheck = true;
			}
		});
		if(childCheck){
			checkBoxByForIsCheck("checkNamePie","hiddendanger");
		}else{
			checkBoxByForIsNotCheck("checkNamePie");
		}
	});
}

//加载流动人口子类别
function checkBoxTypeByFloatingChild(){
	var bo = true;//判断循环第一次
	var checkValue = "";
	<@s.iterator value="@com.tianque.plugin.statistics.constants.TypeConstants@typeMapByName">
		if(bo){
			bo = false;
			checkValue += "<input name='floatingCheckPie' id='<@s.property value="key"/>' value='<@s.property value="key"/>' type='checkbox' /> <@s.property value="value"/>";
		}else{
			checkValue += "<input name='floatingCheckPie' id='<@s.property value="key"/>' value='<@s.property value="key"/>' type='checkbox' class='myCheckBox'/> <@s.property value="value"/>";
		}
	</@s.iterator>
	$("#taskTypeChildPie").append(checkValue);
	checkBoxChildIsChecked();
}

//加载发现治安隐患子类别
function checkBoxTypeByHiddendangerChild(){
	var dataLength = listType.length;
	var bo = true;//判断循环第一次
	var checkValue = "";
	for(var i = 0;i<dataLength;i++){
		if(bo){
			bo = false;
			checkValue += "<input name='hiddendangerPie'  value='"+listType[i].id+"' type='checkbox' /> "+listType[i].displayName+"";
		}else{
			checkValue += "<input name='hiddendangerPie'  value='"+listType[i].id+"' type='checkbox' class='myCheckBox'/> "+listType[i].displayName+"";
		}
	}
	$("#taskTypeChildPie").append(checkValue);
	checkBoxChildHiddendangerIsChecked();
}


function firstType(val){
	var num = 1;
	$("[name='checkNamePie']").each(function(){
		if($(this).attr("checked")){
			num++;
		}
	});
	if(num <= 2){
		if(val == 0){//等于0为流动人口
			checkBoxTypeByFloatingChild();
		}else{
			checkBoxTypeByHiddendangerChild();
		}
		$("#taskTypeChildPie").show();
	}else{
		$("#taskTypeChildPie").hide();
	}
}


function cancelFirstType(){
	var num = 1;
	var eachValue = "";
	$("[name='checkNamePie']").each(function(){
		if($(this).attr("checked")){
			num++;
			eachValue = $(this).val();
		}
	});
	if(num == 2){
		if(eachValue == "floating"){
			$("#taskTypeChildPie").html("子任务类别： ");
			checkBoxTypeByFloatingChild();
			$("#taskTypeChildPie").show();
		}else if(eachValue == "hiddendanger"){
			$("#taskTypeChildPie").html("子任务类别： ");
			checkBoxTypeByHiddendangerChild();
			$("#taskTypeChildPie").show();
		}		
	}
}

function mentalPatientTask(){
	var num = 1;
	var eachValue = "";
	$("[name='checkNamePie']").each(function(){
		if($(this).attr("checked")){
			num++;
			eachValue = $(this).val();
		}
	});
	if(num == 2){
		if(eachValue == "mentalPatientTask"){
			statusBoolean = false;
			$("#druggyReportStatusPie").show();
			$("#reportStatusPie").hide();
		}
	}else{
		statusBoolean = true;
		$("#druggyReportStatusPie").hide();
		$("#reportStatusPie").show();
	}
}

//复选框点击事件
function checkBoxIsChecked(){
	$("[name='checkNamePie']").click(function(){
		if($(this).val() != "pandect"){
			$("#pandectPie").removeAttr("checked");
		}else{
			$("[name='checkNamePie']").each(function(){
				if($(this).val() != "pandect"){
					$(this).removeAttr("checked");
				}
			});
			$("#druggyReportStatusPie").hide();
			statusBoolean = true;
			$("#reportStatusPie").show();
			$("#taskTypeChildPie").hide();
			return;
		}
		if($(this).val() == "floating"){//流动人口
			$("#taskTypeChildPie").html("子任务类别： ");
			if($(this).attr("checked")){//选中事件
				firstType(0);
			}else{
				checkBoxByForIsNotCheck("checkNamePie");
				$("#taskTypeChildPie").hide();
				$("#taskTypeDetailedPie").hide();
				cancelFirstType();
			}
		}else if($(this).val() == "hiddendanger"){//发现治安隐患
			$("#taskTypeChildPie").html("子任务类别： ");
			if($(this).attr("checked")){//选中事件
				firstType(1);
			}else{
				checkBoxByForIsNotCheck("checkNamePie");
				$("#taskTypeChildPie").hide();
				cancelFirstType();
			}
		}else{
			if($(this).attr("checked")){
				$("#taskTypeChildPie").hide();
			}else{
				cancelFirstType();
			}
		}
		mentalPatientTask();
	});
}

//点击事件循环(floating、hiddendanger)选中
function checkBoxByForIsCheck(name,val){ 
	$("[name='"+name+"']").each(function(){
		if($(this).val() != val){
			$(this).removeAttr("checked");
			$(this).attr("disabled",true);
		}
	});
}

//点击事件循环(floating、hiddendanger)未选中
function checkBoxByForIsNotCheck(name){
	$("[name='"+name+"']").each(function(){
		if(!$(this).attr("checked")){
			$(this).removeAttr("disabled");
		}
	});
}

$(document).ready(function(){
	$("#taskTypeChildPie").hide();
	$("#taskTypeDetailedPie").hide();
	$("#druggyReportStatusPie").hide();
	
	//加载年
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#yearPie").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
				$("#yearPie1").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getQuarter();//加载季度
			getMonth();//加载月份
		}
	});
	//加载治安隐患子类别
	$.ajax({
		async: false,
		type: "GET",
		url: "${path}/sysadmin/propertyManage/findPropertyDictByDomainName.action",
		data:{"propertyDomain.domainName":"治安隐患异常类型"},
		success:function(responseData){
			listType = responseData;
		}
	});
	
	//加载流动人口子类别下的民警...详细任务类别
	$.ajax({
		async: false,
		type: "GET",
		url: "${path}/sysadmin/propertyManage/findPropertyDictByDomainName.action",
		data:{"propertyDomain.domainName":"民警带领下工作内容"},
		success:function(responseData){
			detailedPoliceType = responseData;
		}
	});
	
	//加载流动人口子类别下的异常报告详细任务类别
	$.ajax({
		async: false,
		type: "GET",
		url: "${path}/sysadmin/propertyManage/findPropertyDictByDomainName.action",
		data:{"propertyDomain.domainName":"宣传核查异常情况"},
		success:function(responseData){
			detailedUnusualType = responseData;
		}
	});
	checkBoxType();
});

//搜索按钮点击方法
function search(){
	var firstType = [];//任务类别值
	var nextType = [];//任务子类别值
	var lastType = [];//详细类别值
	var statusValue = [];//任务状态值
	var typeValue = 0 ;//时间查询类别(按 月0、季度1、年份2)查询
	var yearFirst = "";//查询条件第一行年
	var monthFirst = "";//查询条件第一行月
	var quarterNext = "";//查询条件第二行季度
	var yearLast = "";//查询条件第三行年
	var statusValueChange = 0;//任务状态最终值
	//获取时间查询类别
	$("input[name = 'typeValuePie']:checked").each(function(){
		typeValue = $(this).val();
	});
	if(typeValue == 0){//根据时间类别获取相应的值
		yearFirst = $("#yearPie").val();
		monthFirst = $("#monthPie").val();
	}else if(typeValue == 1){
		yearFirst = $("#yearPie1").val();
		quarterNext = $("#quarterPie").val();
	}else{
		yearLast = $("#yearPie2").val();
	}
	//获取任务类别值
	$("input[name = 'checkNamePie']:checked").each(function(){
		firstType.push($(this).val());
	});
	//获取任务子类别值
	$("input[name = 'floatingCheckPie']:checked").each(function(){
		nextType.push($(this).val());
	});
	if(nextType.length == 0){//判断是选择了流动人口，还是治安隐患
		$("input[name = 'hiddendangerPie']:checked").each(function(){
			nextType.push($(this).val());
		});
	}
	
	//获取详细类别值
	$("input[name = 'exceptionalSituationRecordPie']:checked").each(function(){
		lastType.push($(this).val());
	});
	if(lastType.length == 0){//判断是选择了民警带领或异常情况
		$("input[name = 'workingSituationPie']:checked").each(function(){
			lastType.push($(this).val());
		});
	}
	
	//获取任务状态值
	if(statusBoolean){//判断获取任务状态的隐藏或显示的值
		$("input[name = 'statusValuePie']:checked").each(function(){
			statusValue.push($(this).val());
		});
		if(statusValue.length == 1){
			if(statusValue[0] == 0){
				statusValueChange = 0;
			}else{
				statusValueChange = 1;
			}
		}else if(statusValue.length == 2){
			statusValueChange = 3;
		}
	}else{
		$("input[name = 'statusValueDisplayPie']:checked").each(function(){
			statusValue.push($(this).val());
		});
		if(statusValue.length == 1){
			if(statusValue[0] == 0){
				statusValueChange = 0;
			}else if(statusValue[0] == 2){//派出所签收 
				statusValueChange = 4;
			}else{//卫生所签收
				statusValueChange = 5;
			}
		}else if(statusValue.length == 2){
			if(statusValue[0] == 2 && statusValue[1] == 3){//派出所 卫生所选中
				statusValueChange = 6;
			}else if(statusValue[0] == 0 && statusValue[1] == 2){//上报 派出所签收选中
				statusValueChange =  7;
			}else if(statusValue[0] == 0 && statusValue[1] == 3){//上报 卫生所签收选中
				statusValueChange = 8;
			}	
		}else if(statusValue.length == 3){//派出所 卫生所 上报全选
			statusValueChange = 9;
		}
	}
	if(statusValue.length == 0){//如果任务状态一个没选中 赋值为空
		statusValueChange = null;
	}
	$("#taskListinfoPie").pieChart({
		url: "${path}/plugin/statistics/generalSituationManage/getTaskListStatisticsPie.action?generalStituationSearchVo.dateSearchType="+typeValue+"&generalStituationSearchVo.year="+yearFirst+"&generalStituationSearchVo.month="+monthFirst+"&generalStituationSearchVo.quarter="+quarterNext+"&generalStituationSearchVo.particularYear="+yearLast+"&generalStituationSearchVo.basicTypeStr="+firstType+"&generalStituationSearchVo.detailTypeStr="+nextType+"&generalStituationSearchVo.subType="+lastType+"&generalStituationSearchVo.isSign="+statusValueChange+"&generalStituationSearchVo.orgStr=187",
		moduleName:"任务清单饼图统计"
	});
}

//对应季度的年的改变事件
$("#yearPie1").change(function(){
	$("#quarterPie").empty();
	getQuarter();
});

//对应月份的年的改变事件
$("#yearPie").change(function(){
	$("#monthPie").empty();
	getMonth();
});
</script>