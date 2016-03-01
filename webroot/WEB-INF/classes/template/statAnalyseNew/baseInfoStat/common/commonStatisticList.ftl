<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

<div id="nav" class="ui-corner-all">
		<@s.if test='"all_attention_population".equals(#parameters.type[0]) || "all_civil_population".equals(#parameters.type[0]) || "unemployedPeople".equals(#parameters.type[0]) || "nurturesWomen".equals(#parameters.type[0])'>
			<select id="type" style="float:right;">
			<option value="1">各区域人口数量统计表1</option>
			<option value="2">各层级人口数量统计表2</option>
			</select>
		</@s.if>
		<@s.elseif test='"actualHouse".equals(#parameters.type[0])' >
			<select id="type" style="float:right;">
			<option value="1">各区域房屋数量统计表1</option>
			<option value="2">各层级房屋数量统计表2</option>
			</select>
		</@s.elseif>
		<@s.else>
			<input id="type" type="hidden" value="1" />
		</@s.else>
		<select name="year" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="month" id="month" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>

		<!--
		<@s.if test='!"all_attention_population".equals(type)'>
		-->
		<@s.if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
		<a hidden="true" id="createStatistic" href="javascript:void(0)"><span>生成报表</span></a>
		</@s.if>
		<!--
		</@s.if>
		-->

</div>
<div class="content">
	<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="<@s.property value='#parameters.type'/>PrintDlg"></div>
<script type="text/javascript">
if($("#currentOrgId").attr("orglevelinternalid") <= <@pop.static value='@com.tianque.domain.property.OrganizationLevel@DISTRICT' />){
	$("#type option:last").remove();
}
var grid = null;

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
}
function onOrgChanged(){

	switch($("#type").val()){
	case '1':
		$.ajax({
			url:'${path}/baseInfo/statisticManageNew/getBaseInfoStatisticList.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=<@s.property value='#parameters.type'/>",
			success:function(data){
				grid.bindData(data);
			}
		})
		break;
	case '2':
		$.ajax({
			url:'${path}/baseInfo/statisticManageNew/getBaseInfoStatisticList.action?orgLevelDistance=2&orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=<@s.property value='#parameters.type'/>",
			success:function(data){
				grid.bindData(data);
			}
		})
		break;
	}
	enableOrDisableColumn(2);
}

$(document).ready(function(){
	if(document.title!='总况'){
		$("#createStatistic").show();
	}

	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			//setTimeout('getmonth()',350);
			getmonth();
		}
	});
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns);
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216)});
	//setTimeout('onOrgChanged()',350);
	onOrgChanged();
	onTypeChanged();
	$("#searchList").click(function(){
		onOrgChanged();
	});

	$("#createStatistic").click(function(){
		$.ajax({
			url:'/baseInfo/statisticManageNew/createHistoryStatistic.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=<@s.property value='#parameters.type'/>",

			success:function(responseData){

				$.messageBox({message:"历史报表生成成功"});
			}
		});

	});
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	$(".print").click(function(){
		var _width = 680, _w670 = ['all_attention_population', 'superiorVisit', 'all_civil_population', 'optimalObject', 'elderlyPeople', 'unemployedPeople', 'nurturesWomen', 'actualHouse', 'actualCompany'];
		for(var i = 0; i < _w670.length; i++){
			if('<@s.property value='#parameters.type'/>' == _w670[i]){
				_width = 670;
				break;
			}
		}
		var url = '${path}/statAnalyseNew/baseInfoStat/common/commonStatisticPrint.ftl?parentOrgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=<@s.property value='#parameters.type'/>&moduleName="+document.title;
		if($("#type").val() == '2'){
			url = url + "&orgLevelDistance=2";
		}
		$("#<@s.property value='#parameters.type'/>PrintDlg").createDialog({
			width: _width,
			height:490,
			title:document.title,
			url:url,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#<@s.property value='#parameters.type'/>PrintDlg").dialog("close");
			   }
			}
		});
	});
	function print(){
		$("#<@s.property value='#parameters.type'/>Print").printArea();
		$("#<@s.property value='#parameters.type'/>PrintDlg").dialog("close");
	}
})
function onTypeChanged() {
	$("#type").change(function(){
		onOrgChanged();
	});
}
</script>