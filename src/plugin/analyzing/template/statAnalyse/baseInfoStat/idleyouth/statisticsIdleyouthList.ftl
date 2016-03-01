<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select  id="queryBygroup" onchange="getData();" style="float:left;">
			<option value="<@s.property  value="@com.tianque.domain.property.PropertyTypes@IDLEYOUTH_STAFF_TYPE"  />">按类型</option>
			<option value="<@s.property  value="@com.tianque.domain.property.PropertyTypes@IDLE_YOUTH_AGE_GROUP"/>">按年龄</option>
        </select>
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
<div class="content" >
	<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="<@s.property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>PrintDlg"></div>
<div class="statisticsTip"><font color="red">注：同一个青少年可同时属于多种类型。</font></div>
<script type="text/javascript">
	
	var 
	 columns = [
			{name:"orgName",caption:"区域",width:120,mode:"string"},
			{name:"amount",caption:"人数",width:80,mode:"number",align:'center',format:"#"},
			{name:"general",caption:"总况",children:[
				{name:"baseinfoStatisticDetailVo[index].typeName",caption:"类型",width:100,mode:"string"},
				{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].amountPercentage",caption:"百分比",width:80,mode:"number",align:'center',format:"#.00%"}
			]},
			{name:"general",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@IDLEYOUTH_DISPLAYNAME)[2]"/>",children:[
				{name:"baseinfoStatisticDetailVo[index].helped",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@IDLEYOUTH_DISPLAYNAME)[0]"/>",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].noHelp",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@IDLEYOUTH_DISPLAYNAME)[1]"/>",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].helped/baseinfoStatisticDetailVo[index].sum",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@IDLEYOUTH_DISPLAYNAME)[3]"/>",width:80,align:'center',mode:"number",format:"#.00%"}
			]}

		];

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

function getData(){
	onOrgChanged();
}

function onOrgChanged(){
	$.ajax({
		url:'${path}/baseInfo/statisticManage/getBaseInfoStatisticList.action?domainName='+encodeURI($("#queryBygroup").val())+'&orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=<@s.property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>",
		success:function(data){
			grid.bindData(data);
		}
	})
	enableOrDisableColumn(2);
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
			//setTimeout('getmonth()',350);
			getmonth();
		}
	});
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns);
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 258 : 234)});
	//setTimeout('onOrgChanged()',350);
	onOrgChanged();
	$("#searchList").click(function(){
		onOrgChanged();
	});

	$("#createStatistic").click(function(){
		$.ajax({
			url:'/baseInfo/statisticManage/createHistoryStatistic.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=<@s.property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>",

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
		$("#<@s.property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>PrintDlg").createDialog({
			width:670,
			height:480,
			title:document.title,
			url:'${path}/statAnalyse/baseInfoStat/common/commonStatisticPrint.ftl?parentOrgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=<@s.property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>&moduleName="+document.title,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#<@s.property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>PrintDlg").dialog("close");
			   }
			}
		});
	});
	function print(){
		$("#<@s.property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>Print").printArea();
		$("#<@s.property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>PrintDlg").dialog("close");
	}
})
</script>

