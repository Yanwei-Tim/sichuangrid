<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="nav" class="ui-corner-all">
		<select name="year" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="month" id="month" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>
</div> 

<div id="gridbox" class="SigmaReport"></div>
<div id="newSocietyFederationDlg"></div>
<script>
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
var keyType=$("#keyType").val();
var grid = null;
function onOrgChanged(){
			loadAjax();
			enableOrDisableColumn(1);
	}

$(document).ready(function(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
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
		var columns = [
			{name:"organization.orgName",caption:"区域",width:120,mode:"string"},
			{name:"total",caption:"总数",width:100,mode:"number",format:"#"},
			{name:"general",caption:"已落实负责人",children:[
				{name:"practicalPlace",caption:"场所数量",width:100,mode:"number",format:"#"},
				{name:"practicalPlace/total",caption:"比率",width:80,mode:"number",format:"#.00%"}
			]},
			{name:"helpInfo",caption:"未落实负责人",children:[
				{name:"noPracticalPlace",caption:"场所数量",width:100,mode:"number",format:"#"},
				{name:"noPracticalPlace/total",caption:"比率",width:80,mode:"number",format:"#.00%"}
			]}

		];
		grid = new SigmaReport("gridbox",context,columns);
		setTimeout('loadAjax()',350);
		loadAjax();
		$("#searchList").click(function(){
			onOrgChanged();
		});
		$("#year").change(function(){
			$("#month").empty();
			getmonth();
		});
		$(".print").click(function(){
			$("#newSocietyFederationDlg").createDialog({
				width:650,
				height:480,
				title:"打印社会组织",
				url:'${path}/statAnalyse/baseInfoStat/newSocietyFederation/newSocietyFederationPrint.jsp?parentOrgId='+getCurrentOrgId()+"&keyType="+keyType+"&year="+$("#year").val()+"&month="+$("#month").val(),
				buttons: {
				   "打印" : function(){
					print();
			  	   },
				   "关闭" : function(){
				        $("#newSocietyFederationDlg").dialog("close");
				   }
				}
			});
		});

	});
function loadAjax(){
	$.ajax({
		url: "${path}/baseinfo/statAnalysePlace/findStatAnalysePlace.action?orgId="+getCurrentOrgId()+"&keyType="+keyType+"&year="+$("#year").val()+"&month="+$("#month").val(),
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
	$("#newSocietyFederationPrint").printArea();
	$("#newSocietyFederationDlg").dialog("close");
}

</script>
