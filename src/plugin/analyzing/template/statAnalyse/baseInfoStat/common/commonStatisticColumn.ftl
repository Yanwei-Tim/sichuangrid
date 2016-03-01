<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<style type="text/css">
	.SigmaReport{overflow:hidden;overflow-x:auto;width:99%;position:relative;}
</style>

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="cyear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;"  id="cmonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="csearch" href="javascript:void(0)"><span>查询</span></a>
		<div class="ui-slider-input" id="R0"></div>
		<div class="ui-slider-input" id="R1"></div> 
		<!--<input id="R0" type="range" min="0" max="45" value="15"/> <span id="R0-value" class="value"></span>-->
	    <!--<input id="R1" type="range" min="0" max="45" value="15"/> <span id="R1-value" class="value"></span>-->
</div>
<div id="<@s.property value='#parameters.type'/>Column" class="SigmaReport"></div>

<script type="text/javascript">
function disAbleOrDisableColumnForPositiveinfo(){
	if("<@s.property value='#parameters.type'/>"=="positiveInfo"){
		$("#chartsTabs").tabs("disable", 1);
		$("#chartsTabs").tabs("disable", 2);
	}
}

disAbleOrDisableColumnForPositiveinfo();
var chartCount;
function onOrgChanged(){
	if(!isGrid()){
	chartCount=$("#<@s.property value='#parameters.type'/>Column").columnChart({
		url: "${path}/baseInfo/statisticManage/getStatisticColumn.action?type=<@s.property value='#parameters.type'/>&orgId="+getCurrentOrgId()+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		moduleName:document.title,
		textx:-150,
		height: document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216),
		quantity:'个数',
		isShowSum:isShowSum("<@s.property value='#parameters.type'/>")
	});
	}
	//chartCount.series[2].hide();
	//chartCount.series[4].hide();

	if(document.title=='总况'){
		enableOrDisableColumn(1);
	}else {
		if("<@s.property value='#parameters.type'/>"!="positiveInfo"){
	 	 	enableOrDisableColumn(2);
	  	}
	}

}
$(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#cyear").val());
	$("#isNowMonth").val($("#cmonth").val());
	$("#csearch").click(function(){
		onOrgChanged();
	});
	$("#cyear").change(function(){
		$("#cmonth").empty();
		getmonth();
	});
	
	$.gridboxHeight();
	if(isGrid()){
		if("newEconomic"=="<@s.property value='#parameters.type'/>" || "newSociety"=="<@s.property value='#parameters.type'/>"){
			enableOrDisableColumn(2);
		}else{
			enableOrDisableColumn(1);
		}
	}else {
	    onOrgChanged();
	}
});


function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}


$("#R1").slider({
	min: 0,
	max: 50,
	value: 0,
	slide: function( event, ui ) {
		chartCount.options.chart.options3d.alpha =ui.value;
        showValues();
        chartCount.redraw(false);
	}
});
$("#R0").slider({ min: 0,
				  max: 50,
				  value: 0,
				  slide: function( event, ui ) {
				 	chartCount.options.chart.options3d.beta = ui.value;
			        showValues();
			        chartCount.redraw(false);
	}
});

function showValues() {
        $('#R0-value').html(chartCount.options.chart.options3d.alpha);
        $('#R1-value').html(chartCount.options.chart.options3d.beta);
    }
    
function isShowSum(type){
	if(type=='elderlyPeople' || type=='optimalObject' || type == 'unemployedPeople' || type=='nurturesWomen'||type=='positiveInfo'){
		return 'false';
	}else{
		return 'true';
	}
}
</script>