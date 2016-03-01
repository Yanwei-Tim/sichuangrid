<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="${path}/extend/amchart/swfobject.js"></script>
	
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
		<div class="ui-corner-all" id="nav">
        <label style="float:left;padding:0 10px;line-height:25px;">开始年份：</label>
        <select name="minYear" id="minYear" onchange="onChangeMinYear()" style="float:left;">
            <option value="0"></option>
            <s:iterator begin="minYear" end="maxYear" var="newMinYear">
                <option value="${newMinYear }">${newMinYear }</option>
            </s:iterator>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">开始月份：</label>
        <select style="float:left;" name="minMonth" id="minMonth" onchange="onChangeMinMonth()">
            <option value="0"></option> 
            <s:iterator begin="1" end="12" var="newMinMonth">
                <option value="${newMinMonth }">${newMinMonth }</option>
            </s:iterator>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">结束年份：</label>
        <select style="float:left;" name="maxYear" id="maxYear"  onchange="onChangeMaxYear()">
            <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;</option> 
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">结束月份：</label>
        <select style="float:left;" name="maxMonth" id="maxMonth" >
            <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;</option> 
        </select>
   		<a id="search" href="javascript:void(0)"><span>查询</span></a>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="generatedDataLocaltion">
			<a id="replaceBaseInfoStat" href="javascript:void(0)"><span>生成数据</span></a>
		</pop:JugePermissionTag>
		</div>
<div class="content" style="overflow:hidden;overflow-y:auto;position:relative;">
<div class="container_24">
	<div class="grid_5 heightAuto" style="width:40%;float:left">
		<div>
			<table id="placeStatisticList"></table>
		</div>
	</div>
	<div class="grid_17 lable-right heightAuto" style="width:60%;float:left" id="showGraph">
	</div>
</div>
		<div id="placeStatisticDialog"></div>
		<div id="noticeDialog"></div>
		<div id="promptDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var minyears;
    var minmonths;
    var maxyears = "<s:property value='maxYear'/>";
    var maxmonths = "<s:property value='maxMonth'/>";
    if(maxmonths == 1){
        minyears = maxyears-1;
        minmonths = 12;
    }else{
        minyears = maxyears;
        minmonths = maxmonths-1;
    }
    $("#minYear").val(minyears);
    $("#minMonth").val(minmonths);
    $("#maxYear").append("<option value='"+maxyears+"' selected>"+maxyears+"</option>");
    <s:iterator begin="maxMonth-1" end="12" var="newMinMonth">
        $("#maxMonth").append("<option value='${newMinMonth }'>"+${newMinMonth }+"</option>");
    </s:iterator>
    $("#maxMonth").val(minmonths);
	
	$(".content",$("#contentDiv")).height($(".ui-layout-center").height()-$(".submenu").height()-$("#nav").height()-20);
	jQuery("#placeStatisticList").jqGridFunction({
		datatype:'local',
	    sortname:'id',
	    rowNum:-1,
	    width:308,
	    height:600,
		colModel:[
			{name:'id',key:true,hidden:true},
			{name:'statisticType',index:'statisticType',label:'统计类型',sortable:false},
			{name:'countNum',index:'countNum',label:'总数',width:65,sortable:false},
			{name:'scale',index:'scale',label:'总比',width:78,sortable:false},
			{name:'startDate',index:'startDate',label:'开始时间',sortable:false,hidden:true},
			{name:'endDate',index:'startDate',label:'结束时间',sortable:false,hidden:true}
		],
		ondblClickRow:showPic
	});

	$("#search").click(function(event){
		if(!$("#minYear").attr("disabled") && $("#minMonth").attr("disabled") && $("#maxYear").attr("disabled") && $("#maxMonth").attr("disabled")){
			searchPopulationStatistic();
			return ;
		}
		if($("#minYear").attr("disabled") || $("#minMonth").attr("disabled") || $("#maxYear").attr("disabled") || $("#maxMonth").attr("disabled")){
			alert("请选择完整的查询时间");
			return ;
		}
		searchPopulationStatistic();
		}
	);

	$("#refresh").click(function(event){
		var minyears;
        var minmonths;
        var maxyears = "<s:property value='maxYear'/>";
        var maxmonths = "<s:property value='maxMonth'/>";
        if(maxmonths == 1){
            minyears = maxyears-1;
            minmonths = 12;
        }else{
            minyears = maxyears;
            minmonths = maxmonths-1;
        }
        $("#minYear").val(minyears);
        $("#minMonth").val(minmonths);
        $("#maxYear").html("<option value='0' selected></option>");
        $("#maxYear").append("<option value='"+maxyears+"' selected>"+maxyears+"</option>");
        $("#maxMonth").html("<option value='0' selected></option>");
        <s:iterator begin="maxMonth-1" end="12" var="newMinMonth">
            $("#maxMonth").append("<option value='${newMinMonth }'>"+${newMinMonth }+"</option>");
        </s:iterator>
        $("#maxMonth").val(minmonths);
		onOrgChanged(getCurrentOrgId(),isGrid());
	});	
	
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
	    onOrgChanged(getCurrentOrgId(),isGrid());
	}

	$("#replaceBaseInfoStat").click(function(event){
		$("#placeStatisticDialog").createDialog({
			width: 400,
			height: 150,
			title: "统计分析基础信息重新生成",
			url: "${path}/baseInfoStatistics/dispatch.action?mode=replace",
			buttons: {
		   		"生成" : function(event){
					replacePopulationStatistic();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
});

function onOrgChanged(orgId,isgrid){
	var minYear = $("#minYear").val()==0?0:$("#minYear").val();
    var minMonth = $("#minMonth").val()==0?0:$("#minMonth").val();
    var maxYear = $("#maxYear").val()==0?0:$("#maxYear").val();
    var maxMonth = $("#maxMonth").val()==0?0:$("#maxMonth").val();
	$("#placeStatisticList").setGridParam({
		url:'${path}/baseInfoStatistics/findPlaceList.action',
		datatype: "json"
	});
	$("#placeStatisticList").setPostData({
		"page":1,
		"orgId":orgId,
        "minYear":minYear,
        "minMonth":minMonth,
        "maxYear":maxYear,
        "maxMonth":maxMonth
	});
	$("#placeStatisticList").trigger("reloadGrid");

	$.ajax({
		async:false,
		url:"${path}/baseInfoStatistics/showEmphasisPlace.action?mode=returnToGraph",
		data:{
			"orgId":orgId,
	        "minYear":minYear,
	        "minMonth":minMonth,
	        "maxYear":maxYear,
	        "maxMonth":maxMonth
		},
		success:function(responseData){
			$("#showGraph").html("<div id='flashcontents'>"
			+"<strong>You need to upgrade your Flash Player</strong>"
			+"</div>"
		
			+"<script type='text/javascript'>"	
			+"var so = new SWFObject('../extend/amchart/pie/ampie.swf', 'ampie', '100%', '260', '8', '#FFFFFF');"
			+"so.addVariable('path', '../extend/amchart/pie/');"
			+"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/pie/day_settings.jsp?showTitle="+responseData.title_key1+"&chartDataList="+responseData.data_key1+"'));"
			+"so.addVariable('data_file', encodeURIComponent('../extend/amchart/pie/day_data.jsp?showTitle="+responseData.title_key1+"&chartDataList="+responseData.data_key1+"'));"
			+"so.addParam('wmode','transparent');"
			+"so.write('flashcontents');"
			+"<\/script>"

		    +"<div id='flashcontent1'>"
		    +"<strong>You need to upgrade your Flash Player</strong>"
		    +"</div>"

		    +"<script type='text/javascript'>"	
		    +"var so = new SWFObject('../extend/amchart/line/amline.swf', 'amline', '100%', '380', '8', '#FFFFFF');"
		    +"so.addVariable('path', '../extend/amchart/line/');"
		    +"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/line/day_settings.jsp?showTitle="+responseData.title_key2+"&chartDataList="+responseData.data_key2+"'));"
		    +"so.addVariable('data_file', encodeURIComponent('../extend/amchart/line/day_data.jsp?showTitle="+responseData.title_key2+"&chartDataList="+responseData.data_key2+"'));"
		    +"so.addParam('wmode','transparent');"
		    +"so.write('flashcontent1');"
			+"<\/script>"
			);
		}
	});
}

function searchPopulationStatistic(){
	var minYear = $("#minYear").val();
	var minMonth = $("#minMonth").val();

	var maxYear = $("#maxYear").val();
	var maxMonth = $("#maxMonth").val();
	var orgId = getCurrentOrgId();
	$("#placeStatisticList").setGridParam({
		url:'${path}/baseInfoStatistics/findPlaceList.action',
		datatype: "json"
	});
	$("#placeStatisticList").setPostData({
		"page":1,
		"orgId":orgId,
		"minYear":minYear,
		"minMonth":minMonth,
		"maxYear":maxYear,
		"maxMonth":maxMonth
	});
	$("#placeStatisticList").trigger("reloadGrid");

	$.ajax({
		async:false,
		url:"${path}/baseInfoStatistics/showEmphasisPlace.action?mode=returnToGraph",
		data:{
			"orgId":orgId,
			"minYear":minYear,
			"minMonth":minMonth,
			"maxYear":maxYear,
			"maxMonth":maxMonth
		},
		success:function(responseData){
			$("#showGraph").html("<div id='flashcontents'>"
			+"<strong>You need to upgrade your Flash Player</strong>"
			+"</div>"
		
			+"<script type='text/javascript'>"	
			+"var so = new SWFObject('../extend/amchart/pie/ampie.swf', 'ampie', '100%', '260', '8', '#FFFFFF');"
			+"so.addVariable('path', '../extend/amchart/pie/');"
			+"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/pie/day_settings.jsp?showTitle="+responseData.title_key1+"&chartDataList="+responseData.data_key1+"'));"
			+"so.addVariable('data_file', encodeURIComponent('../extend/amchart/pie/day_data.jsp?showTitle="+responseData.title_key1+"&chartDataList="+responseData.data_key1+"'));"
			+"so.addParam('wmode','transparent');"
			+"so.write('flashcontents');"
			+"<\/script>"

		    +"<div id='flashcontent1'>"
		    +"<strong>You need to upgrade your Flash Player</strong>"
		    +"</div>"

		    +"<script type='text/javascript'>"	
		    +"var so = new SWFObject('../extend/amchart/line/amline.swf', 'amline', '100%', '380', '8', '#FFFFFF');"
		    +"so.addVariable('path', '../extend/amchart/line/');"
		    +"so.addVariable('settings_file', encodeURIComponent('../extend/amchart/line/day_settings.jsp?showTitle="+responseData.title_key2+"&chartDataList="+responseData.data_key2+"'));"
		    +"so.addVariable('data_file', encodeURIComponent('../extend/amchart/line/day_data.jsp?showTitle="+responseData.title_key2+"&chartDataList="+responseData.data_key2+"'));"
		    +"so.addParam('wmode','transparent');"
		    +"so.write('flashcontent1');"
			+"<\/script>"
			);
		}
	});
}

function showPic(){
	var id = $("#placeStatisticList").getGridParam("selrow");           
	var ret = $("#placeStatisticList").getRowData(id);
	var baseInfoTableKey;
	var isDanger = "";
	if(id == 1){
		baseInfoTableKey = "LETTINGHOUSE";
	}else if(id == 2){
		baseInfoTableKey = "ENTERPRISE";
	}else if(id == 3){
		baseInfoTableKey = "SCHOOL";
	}else if(id == 4){
		baseInfoTableKey = "HOSPITAL";
	}else if(id == 5){
		baseInfoTableKey = "COMMONCOMPLEXPLACE";
	}else if(id == 6){
		baseInfoTableKey = "SPECIALTRADE";
	}else if(id == 7){
		baseInfoTableKey = "NEWSOCIETYFEDERATION";
	}else if(id == 8){
		baseInfoTableKey = "OTHERLOCALE";
	}
	$("#placeStatisticDialog").createDialog({
		width: 800,
		height: 360,
		title:ret.statisticType,
		url:'${path}/baseInfoStatistics/baseInfoPie.action?baseInfoTableKey='+baseInfoTableKey+'&orgId='+getCurrentOrgId()+isDanger+"&startDate="+ret.startDate+"&endDate="+ret.endDate,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function onChangeMinYear(){
	if($("#minYear").val() > 0){
		$("#minMonth").removeAttr("disabled");
	}else{
		$("#minMonth").attr("disabled","disabled");
		$("#minMonth").val(0);
	}
	if($("#minMonth").val()>0){
		$("#minMonth").val(0);
	}
	$("#maxYear").attr("disabled","disabled");
	$("#maxYear").val(0);
	$("#maxMonth").attr("disabled","disabled");
	$("#maxMonth").val(0);
}

function onChangeMinMonth(){
	if($("#minMonth").val() > 0){
		var minYear = $("#minYear").val();
		var maxYear = ${maxYear};
		$("#maxYear").removeAttr("disabled");
		
		$('#maxYear option').remove();
		$("<option value='0'></option>").appendTo("#maxYear");
		for(var i = minYear ; i<=maxYear;i++){
			$("<option value='"+i+"'>"+i+"</option>").appendTo("#maxYear");
		}
	}else{
		$("#maxYear").attr("disabled","disabled");
		$("#maxYear").val(0);
	}
	$("#maxMonth").attr("disabled","disabled");
	$("#maxMonth").val(0);
}

function onChangeMaxYear(){
	if($("#maxYear").val() > 0){
		$("#maxMonth").removeAttr("disabled");
		if($("#maxYear").val() == $("#minYear").val()){
			var minMonth = $("#minMonth").val();
			$('#maxMonth option').remove();
			$("<option value='0'></option>").appendTo("#maxMonth");
			for(var i = minMonth ; i<=12;i++){
				$("<option value='"+i+"'>"+i+"</option>").appendTo("#maxMonth");
			}
		}else{
			$('#maxMonth option').remove();
			$("<option value='0'></option>").appendTo("#maxMonth");
			for(var i = 1 ; i<=12;i++){
				$("<option value='"+i+"'>"+i+"</option>").appendTo("#maxMonth");
			}
		}
	}else{
		$("#maxMonth").attr("disabled","disabled");
		$("#maxMonth").val(0);
	}
}

function replacePopulationStatistic(){
	if($("#replaceYear").val() == 0 || $("#replaceMonth").val() == 0){
		$.messageBox({message:"请输入重新生成的年份和月份"});
		return ;
	}
	var orgId = getCurrentOrgId();
	$.ajax({
		async:false,
		url:"${path}/baseInfoStatistics/replacePlaceBaseInfoStat.action",
		data:{
			"baseInfoStat.year":$("#replaceYear").val(),
			"baseInfoStat.month":$("#replaceMonth").val(),
			"orgId":orgId
		},
		success:function(responseData){
			$.messageBox({message:"已重新生成数据"});
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
	});
}
</script>