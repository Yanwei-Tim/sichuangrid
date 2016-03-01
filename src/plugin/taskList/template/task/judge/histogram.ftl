<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<style type="text/css">
	.mySelect{width:80px;}
	.mySelect1{width:180px;}
	.myCheckBox{margin-left:10px;}
	.myDivTop{margin-top:5px;}
</style>

<div class="content myDivTop" >
	<div>
		<div>
			<input name="typeValue" value="0" type="radio" checked="checked"> 月 
			<select id="year" style="margin-left:22px;" class="mySelect"></select> 年 
			<select id="month" class="mySelect" >
			</select> 月 
		</div>
		<div class="myDivTop">
			<input name="typeValue" value="1" type="radio"> 季度
			<select id="year1" style="margin-left:10px;" class="mySelect">
			</select> 年 
			<select id="quarter" class="mySelect">
			</select>  
		</div>
		<div class="myDivTop">
			<input name="typeValue" value="2" type="radio"> 年度 
			<select style="margin-left:10px;" id="year2" class="mySelect1">
				<option value="0">全年</option>
				<option value="1">上半年</option>
				<option value="2">下半年</option>
			</select>
		</div>
		<div style="margin-top:10px;" id="taskType">
			任务类别：
		</div>
		<div style="margin-top:10px;" id="taskTypeChild">
			子任务类别：
		</div>
		<div style="margin-top:10px;" id="taskTypeDetailed">
			详细类别：
		</div>
		<div style="margin-top:10px;" class="btnbanner" id="reportStatus">
			任务状态：
			<input type="checkbox" name="statusValue" value="0" style="margin-left:12px;" checked="checked"/> 上报
			<input type="checkbox" name="statusValue" value="1" class="myCheckBox"/> 签收 
			<a href="javascript:search();" style="margin-left:12px;top:-4px;" ><span>搜索</span></a>
		</div>
		<div style="margin-top:10px;" class="btnbanner" id="druggyReportStatus">
			任务状态：
			<input type="checkbox" value="0" name="statusValueDisplay" style="margin-left:12px;" checked="checked"/> 上报
			<input type="checkbox" value="2" name="statusValueDisplay" class="myCheckBox"/> 派出所签收 
			<input type="checkbox" value="3" name="statusValueDisplay" class="myCheckBox"/> 卫生所签收 
			<a href="javascript:search();" style="margin-left:12px;top:-4px;" ><span>搜索</span></a>
		</div>
	</div>
</div>

<script type="text/javascript">
var listType;//子任务类别结果集
var detailedPoliceType;//民警详细任务类别结果集
var detailedUnusualType;//异常详细任务类别结果集
var statusBoolean = true;//任务状态判断，true为不是派出所、卫生所、false为是
var taskStatusChangeType = true;//判断任务状态是否可多选   true 为可多选  false 不可
function getQuarter(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForQuarter.action?currenYear="+$("#year1").val(),
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#quarter").append("<option  value='"+responseData[i]+"'>第"+responseData[i]+"季度</option>"); 
			}
		}
	});
}

function getMonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#year").val(),
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#month").append("<option  value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}

//改变任务状态选中值
function taskStatusChange(){
	var changeLength = 1;
	if(statusBoolean){//任务状态判断，true为不是派出所、卫生所、false为是
		$("input[name = 'statusValue']:checked").each(function(){
			changeLength++;
			if(changeLength > 2){
				$(this).removeAttr("checked");
			}
		});
		if(changeLength > 2){
			
		}
	}else{ 
		$("input[name = 'statusValueDisplay']:checked").each(function(){
			changeLength++;
			if(changeLength > 2){
				$(this).removeAttr("checked");
			}
		});
	}
}

//加载任务类别
function checkBoxType(){
	var bo = true;//判断循环第一次
	var checkValue = "";
	<@s.iterator value="@com.tianque.plugin.statistics.constants.TypeConstants@typeMapName">
		if(bo){
			bo = false;
			checkValue += "<input name='checkName' id='<@s.property value="key"/>' value='<@s.property value="key"/>' type='checkbox'  style='margin-left:12px;'  checked='checked'/> <@s.property value="value"/>";
		}else{
			checkValue += "<input name='checkName' id='<@s.property value="key"/>' value='<@s.property value="key"/>' type='checkbox' class='myCheckBox'/> <@s.property value="value"/>";
		}
	</@s.iterator>
	$("#taskType").append(checkValue);
	checkBoxIsChecked();//加载复选框点击事件
}

//加载民警详细类别
function police(){
	var dataLength = detailedPoliceType.length;
	var bo = true;//判断循环第一次
	var checkValue = "";
	$("#taskTypeDetailed").html("详细类别： ");
	for(var i = 0;i<dataLength;i++){
		if(bo){
			bo = false;
			checkValue += "<input name='workingSituation' id='"+detailedPoliceType[i].id+"'  value='"+detailedPoliceType[i].id+"' type='checkbox' style='margin-left:12px;'/> "+detailedPoliceType[i].displayName+"";
		}else{
			checkValue += "<input name='workingSituation' id='"+detailedPoliceType[i].id+"'  value='"+detailedPoliceType[i].id+"' type='checkbox' class='myCheckBox'/> "+detailedPoliceType[i].displayName+"";
		}
	}
	$("#taskTypeDetailed").append(checkValue);
	clickPolice();
}

//初始化异常情形详细类别复选框点击事件
function clickUnusual(){
	$("[name = 'exceptionalSituationRecord']").click(function(){
		var clickValue = false;
		var num = 1;
		$("[name = 'exceptionalSituationRecord']").each(function(){
			if($(this).attr("checked")){
				clickValue = true;
				num++;
			}
		});
		setNumByStatus(num);
		if(clickValue){
			checkBoxByForIsCheck("floatingCheck","exceptionalSituationRecord");
		}else{
			checkBoxByForIsNotCheck("floatingCheck");
		}
	});
}

//初始化民警..详细类别复选框点击事件
function clickPolice(){
	$("[name = 'workingSituation']").click(function(){
		var clickValue = false;
		var num = 1;
		$("[name = 'workingSituation']").each(function(){
			if($(this).attr("checked")){
				num++;
				clickValue = true;
			}
		});
		setNumByStatus(num);
		if(clickValue){
			checkBoxByForIsCheck("floatingCheck","workingSituation");
		}else{
			checkBoxByForIsNotCheck("floatingCheck");
		}
	});
}

//加载异常情形详细类别
function unusual(){
	var dataLength = detailedUnusualType.length;
	var bo = true;//判断循环第一次
	var checkValue = "";
	$("#taskTypeDetailed").html("详细类别： ");
	for(var i = 0;i<dataLength;i++){
		if(bo){
			bo = false;
			checkValue += "<input name='exceptionalSituationRecord'  value='"+detailedUnusualType[i].id+"' type='checkbox' style='margin-left:12px;'/> "+detailedUnusualType[i].displayName+"";
		}else{
			checkValue += "<input name='exceptionalSituationRecord'  value='"+detailedUnusualType[i].id+"' type='checkbox' class='myCheckBox'/> "+detailedUnusualType[i].displayName+"";
		}
	}
	$("#taskTypeDetailed").append(checkValue);
	clickUnusual();
}


function lastType(val){
	var num = 1;
	$("[name='floatingCheck']").each(function(){
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
		$("#taskTypeDetailed").show();
	}else{
		$("#taskTypeDetailed").hide();
	}
	
}

function cancelLastType(){
	var num = 1;
	var eachValue = "";
	$("[name='floatingCheck']").each(function(){
		if($(this).attr("checked")){
			num++;
			eachValue = $(this).val();
		}
	});
	if(num == 2){
		if(eachValue == "workingSituation"){
			police();
			$("#taskTypeDetailed").show();
		}else if(eachValue == "exceptionalSituationRecord"){
			unusual();
			$("#taskTypeDetailed").show();
		}		
	}
}

//根据传入的num判断任务状态是否多选
function setNumByStatus(num){
	if(num > 2){
		taskStatusChangeType = false;
		taskStatusChange();
	}else{
		taskStatusChangeType = true;	
	}
}

//加载流动人口子类别点击事件
function checkBoxChildIsChecked(){
	$("[name='floatingCheck']").click(function(){
		var childCheck = false;
		var num = 1;
		//判断是否存在选中状态
		$("[name='floatingCheck']").each(function(){
			if($(this).attr("checked")){
				num++;
				childCheck = true;
			}
		});
		setNumByStatus(num);
		if($(this).val() == "workingSituation"){//民警带领..
			if($(this).attr("checked")){
				lastType(0);
			}else{
				$("#taskTypeDetailed").hide();
				checkBoxByForIsNotCheck("floatingCheck");
				cancelLastType();
			}
		}else if($(this).val() == "exceptionalSituationRecord"){//异常情况..
			if($(this).attr("checked")){
				lastType(1);
			}else{
				checkBoxByForIsNotCheck("floatingCheck");
				$("#taskTypeDetailed").hide();
				cancelLastType();
			}
		}else{
			if($(this).attr("checked")){
				$("#taskTypeDetailed").hide();
			}else{
				cancelLastType();
			}
		}
		if(childCheck){
			checkBoxByForIsCheck("checkName","floating");
		}else{
			checkBoxByForIsNotCheck("checkName");
		}
	});
}

//加载治安隐患子类别点击事件
function checkBoxChildHiddendangerIsChecked(){
	$("[name='hiddendanger']").click(function(){
		var childCheck = false;
		var num = 1;
		$("[name='hiddendanger']").each(function(){
			if($(this).attr("checked")){
				num++;
				childCheck = true;
			}
		});
		setNumByStatus(num);
		if(childCheck){
			checkBoxByForIsCheck("checkName","hiddendanger");
		}else{
			checkBoxByForIsNotCheck("checkName");
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
			checkValue += "<input name='floatingCheck' id='<@s.property value="key"/>' value='<@s.property value="key"/>' type='checkbox' /> <@s.property value="value"/>";
		}else{
			checkValue += "<input name='floatingCheck' id='<@s.property value="key"/>' value='<@s.property value="key"/>' type='checkbox' class='myCheckBox'/> <@s.property value="value"/>";
		}
	</@s.iterator>
	$("#taskTypeChild").append(checkValue);
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
			checkValue += "<input name='hiddendanger'  value='"+listType[i].id+"' type='checkbox' /> "+listType[i].displayName+"";
		}else{
			checkValue += "<input name='hiddendanger'  value='"+listType[i].id+"' type='checkbox' class='myCheckBox'/> "+listType[i].displayName+"";
		}
	}
	$("#taskTypeChild").append(checkValue);
	checkBoxChildHiddendangerIsChecked();
}


function firstType(val){
	var num = 1;
	$("[name='checkName']").each(function(){
		if($(this).attr("checked")){
			num++;
		}
	});
	if(num <= 2){
		if(val != 2){
			if(val == 0){//等于0为流动人口
				checkBoxTypeByFloatingChild();
			}else{
				checkBoxTypeByHiddendangerChild();
			}
			$("#taskTypeChild").show();
		}
	}else{
		$("#taskTypeChild").hide();
	}
	setNumByStatus(num);
}


function cancelFirstType(){
	var num = 1;
	var eachValue = "";
	$("[name='checkName']").each(function(){
		if($(this).attr("checked")){
			num++;
			eachValue = $(this).val();
		}
	});
	if(num == 2){
		taskStatusChangeType = true;
		if(eachValue == "floating"){
			$("#taskTypeChild").html("子任务类别： ");
			checkBoxTypeByFloatingChild();
			$("#taskTypeChild").show();
		}else if(eachValue == "hiddendanger"){
			$("#taskTypeChild").html("子任务类别： ");
			checkBoxTypeByHiddendangerChild();
			$("#taskTypeChild").show();
		}		
	}
	setNumByStatus(num);
}

function mentalPatientTask(){
	var num = 1;
	var eachValue = "";
	$("[name='checkName']").each(function(){
		if($(this).attr("checked")){
			num++;
			eachValue = $(this).val();
		}
	});
	if(num == 2){
		if(eachValue == "mentalPatientTask"){
			statusBoolean = false;
			$("#druggyReportStatus").show();
			$("#reportStatus").hide();
		}
	}else{
		statusBoolean = true;
		$("#druggyReportStatus").hide();
		$("#reportStatus").show();
	}
}

//复选框点击事件
function checkBoxIsChecked(){
	$("[name='checkName']").click(function(){
		if($(this).val() != "pandect"){
			$("#pandect").removeAttr("checked");
		}else{
			$("[name='checkName']").each(function(){
				if($(this).val() != "pandect"){
					$(this).removeAttr("checked");
				}
			});
			$("#druggyReportStatus").hide();
			statusBoolean = true;
			$("#reportStatus").show();
			$("#taskTypeChild").hide();
			return;
		}
		if($(this).val() == "floating"){//流动人口
			$("#taskTypeChild").html("子任务类别： ");
			if($(this).attr("checked")){//选中事件
				firstType(0);
			}else{
				checkBoxByForIsNotCheck("checkName");
				$("#taskTypeChild").hide();
				$("#taskTypeDetailed").hide();
				cancelFirstType();
			}
		}else if($(this).val() == "hiddendanger"){//发现治安隐患
			$("#taskTypeChild").html("子任务类别： ");
			if($(this).attr("checked")){//选中事件
				firstType(1);
			}else{
				checkBoxByForIsNotCheck("checkName");
				$("#taskTypeChild").hide();
				cancelFirstType();
			}
		}else{
			if($(this).attr("checked")){
				$("#taskTypeChild").hide();
				firstType(2);
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
	$("#taskTypeChild").hide();
	$("#taskTypeDetailed").hide();
	$("#druggyReportStatus").hide();
	
	//加载年
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
				$("#year1").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
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
	
	//任务状态点击 普通
	$("input[name = 'statusValue']").click(function(){
		if(!taskStatusChangeType){//是否可多选
			var statusValue = $(this).val();
			$("input[name = 'statusValue']:checked").each(function(){
				if($(this).val() != statusValue){
					$(this).removeAttr("checked");
				}				
			});	
		}
	});
	
	//任务状态点击  派出所签收  卫生所签收
	$("input[name = 'statusValueDisplay']").click(function(){
		if(!taskStatusChangeType){//是否可多选
			var statusValue = $(this).val();
			$("input[name = 'statusValueDisplay']:checked").each(function(){
				if($(this).val() != statusValue){
					$(this).removeAttr("checked");
				}				
			});	
		}
	});
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
	$("input[name = 'typeValue']:checked").each(function(){
		typeValue = $(this).val();
	});
	if(typeValue == 0){//根据时间类别获取相应的值
		yearFirst = $("#year").val();
		monthFirst = $("#month").val();
	}else if(typeValue == 1){
		yearFirst = $("#year1").val();
		quarterNext = $("#quarter").val();
	}else{
		yearLast = $("#year2").val();
	}
	//获取任务类别值
	$("input[name = 'checkName']:checked").each(function(){
		firstType.push($(this).val());
	});
	//获取任务子类别值
	$("input[name = 'floatingCheck']:checked").each(function(){
		nextType.push($(this).val());
	});
	if(nextType.length == 0){//判断是选择了流动人口，还是治安隐患
		$("input[name = 'hiddendanger']:checked").each(function(){
			nextType.push($(this).val());
		});
	}
	
	//获取详细类别值
	$("input[name = 'exceptionalSituationRecord']:checked").each(function(){
		lastType.push($(this).val());
	});
	if(lastType.length == 0){//判断是选择了民警带领或异常情况
		$("input[name = 'workingSituation']:checked").each(function(){
			lastType.push($(this).val());
		});
	}
	
	//获取任务状态值
	if(statusBoolean){//判断获取任务状态的隐藏或显示的值
		$("input[name = 'statusValue']:checked").each(function(){
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
		$("input[name = 'statusValueDisplay']:checked").each(function(){
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
	$.ajax({
		url: "${path}/plugin/statistics/generalSituationManage/getStatisticInfoByConditions.action",
		type: "get",
		data:{
			"generalStituationSearchVo.dateSearchType" : typeValue,
			"generalStituationSearchVo.year": yearFirst,
			"generalStituationSearchVo.month" : monthFirst,
			"generalStituationSearchVo.quarter" : quarterNext,
			"generalStituationSearchVo.particularYear" : yearLast,
			"generalStituationSearchVo.basicType" : firstType,
			"generalStituationSearchVo.detailType" : nextType,
			"generalStituationSearchVo.subType" : lastType,
			"generalStituationSearchVo.isSign" : statusValueChange
		},
		traditional:true,
		success:function(returnJson){
			alert(returnJson)
		}
	});
}

//对应季度的年的改变事件
$("#year1").change(function(){
	$("#quarter").empty();
	getQuarter();
});

//对应月份的年的改变事件
$("#year").change(function(){
	$("#month").empty();
	getMonth();
});
</script>