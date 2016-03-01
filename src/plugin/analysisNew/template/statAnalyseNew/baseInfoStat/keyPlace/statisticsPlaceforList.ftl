<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="queryYear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="queryMonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
		<@s.if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
		<a hidden="true" id="createStatistic" href="javascript:void(0)"><span>生成报表</span></a>
		</@s.if>
</div> 

<div  class="content" >
<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="enterPrisePrintDlg"></div>
<script>
function getmonth(){
	$.ajax({
		async: false,
		url: "${(path)!}/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#queryYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
var org=getCurrentOrgId();
var grid = null;
var keyType=$("#keyType").val();
function onOrgChanged(){
			loadAjax();
			enableOrDisableColumn(2);
	}

$(document).ready(function(){
	if(document.title!='总况'){
		$("#createStatistic").show();
	}

	$.ajax({
		async: false,
		url: "${(path)!}/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			//setTimeout('getmonth()',350);
			getmonth();
		}
	});
	if($("#queryMonth").val() == 1){
		$("#queryMonth").empty();
		$("#queryYear").val($("#queryYear").val()-1);
		getmonth();
	}else{
		$("#queryMonth option")[1].selected = "selected";
	}	
	$.gridboxHeight();
	var str = "";
	if(keyType=="SAFETYPRODUCTIONKEY"){
		str = "安全生产重点";
	}else if(keyType=="FIRESAFETYKEY"){
		str = "消防安全重点";
	}else if(keyType=="SECURITYKEY"){
		str = "治安重点";
	}else if(keyType=="SCHOOL"){
		str = "学校";
	}else if(keyType=="ENTERPRISEKEY"){
		str = "规上企业";
	}else{
		str = "场所";
	}
	var context = {};
	var columns = [
		{name:"organization.orgName",caption:"区域",width:120,mode:"string"},
		{name:"total",caption:"总数",width:100,mode:"number",align:'center',format:"#"},
		{name:"general",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@SAFETYPRODUCTIONKEY_DISPLAYNAME)[2]"/>",children:[
			{name:"helped",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@SAFETYPRODUCTIONKEY_DISPLAYNAME)[0]"/>",width:80,mode:"number",align:'center',format:"#"},
			{name:"noHelp",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@SAFETYPRODUCTIONKEY_DISPLAYNAME)[1]"/>",width:80,mode:"number",align:'center',format:"#"},
			{name:"helped/total",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@SAFETYPRODUCTIONKEY_DISPLAYNAME)[3]"/>",width:80,align:'center',mode:"number",format:"#.00%"}
			
		]},
		{name:"percentage",caption:"百分比",width:80,mode:"number",align:'center',format:"#.00%"}
	];
		grid = new SigmaReport("gridbox",context,columns);
		$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216)});
		//setTimeout('loadAjax()',350);
		loadAjax();
	$("#search").click(function(){
		onOrgChanged();
	});
	$("#queryYear").change(function(){
		$("#queryMonth").empty();
		getmonth();
	});
	$(".print").click(function(){
		$("#enterPrisePrintDlg").createDialog({
			width:680,
			height:350,
			title:"打印"+str,
			url:"${(path)!}/statAnalyse/baseInfoStat/keyPlace/enterPrisePrint.ftl?parentOrgId="+getCurrentOrgId()+"&keyType="+keyType+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val(),
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#enterPrisePrintDlg").dialog("close");
			   }
			}
		});
	});
	
	$("#createStatistic").click(function(){
		var url = '${(path)!}/baseinfo/statAnalysePlace/createHistoryStatistic.action?orgId='+getCurrentOrgId()+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val()+"&type=<@s.property value='#parameters.type'/>";
		if(keyType=="ENTERPRISEKEY"){
			url =  '${(path)!}/baseinfo/statAnalysePlace/createEnterpriseHistoryStatistic.action?orgId='+getCurrentOrgId()+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val()+"&type=<@s.property value='#parameters.type'/>";
		}
		
		$.ajax({
			url:url,

			success:function(responseData){

				$.messageBox({message:"历史报表生成成功"});
			}
		});

	});
});
function loadAjax(){
		$.ajax({
			url: "${(path)!}/baseinfo/statAnalysePlace/findStatAnalysePlace.action?orgId="+getCurrentOrgId()+"&keyType="+keyType+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val(),
			success:function(responseData){
				if(responseData == null || (responseData[0]!= null &&responseData[0].organization == null)){
				$.messageBox({
					message:"查询的月份没有数据生成",
					level: "error"
				});
				return;
			}
				grid.bindData(responseData);
			}
		});
}
function print(){
	$("#enterPrisePrint").printArea();
	$("#enterPrisePrintDlg").dialog("close");
}

</script>
