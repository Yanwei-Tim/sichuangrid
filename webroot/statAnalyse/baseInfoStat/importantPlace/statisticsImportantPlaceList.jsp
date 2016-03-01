<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="queryYear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="queryMonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>
</div> 

<div id="gridbox" class="SigmaReport"></div> 
<div id="placeStuationDlg"></div>

<script type="text/javascript">

var grid = null;


function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#queryYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}

function onOrgChanged(){
	$.ajax({
		url:'/baseInfoStat/statisticsPlace/getStatisticsPlaceInfoList.action?orgId='+getCurrentOrgId()+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val()+"&typeTableName=IMPORTANTPLACE",
		success:function(data){
			if(data == null || (data[0]!= null &&data[0].org == null)){
				$.messageBox({
					message:"查询的月份没有数据生成",
					level: "error"
				});
				return;
			}
			grid.bindData(data);
		}
	})
	enableOrDisableColumn(1);
}

$(document).ready(function(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			//setTimeout('getmonth()',350);
			getmonth();
		}
	});
	$("#isNowYear").val($("#queryYear").val());
	$("#isNowMonth").val($("#queryMonth").val());	
	$.gridboxHeight();
	var context = {};
	var columns = [
			{name:"org.orgName",caption:"区域",width:120,mode:"string"},	
			{name:"general",caption:"总况",children:[
				{name:"personnelDetailDatas[index].name",caption:"类型",width:150,mode:"string"},
				{name:"personnelDetailDatas[index].amount",caption:"数量",width:80,mode:"number",format:"#", align:'center',showLink:"personnelDetailDatas[index].showLink", href:"shwoInfoInDialog(row.data.personnelDetailDatas[index].name, row.data.org.id)"},
				{name:"personnelDetailDatas[index].amountPercentage",caption:"百分比",width:80,mode:"number",align:'center',format:"#.00%"}
			]}			
		];
	grid = new SigmaReport("gridbox",context,columns);
	setTimeout('onOrgChanged()',350);
	onOrgChanged();
	$("#searchList").click(function(){
		onOrgChanged();
	});
	$("#queryYear").change(function(){
		$("#queryMonth").empty();
		getmonth();
	});
	$(".print").click(function(){
		$("#placeStuationDlg").createDialog({
			width:654,
			height:450,
			title:"打印重点场所总况",
			url:"${path}/statAnalyse/baseInfoStat/importantPlace/importPalceSituationPrint.jsp?parentOrgId="+getCurrentOrgId()+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val()+"&typeTableName=IMPORTANTPLACE",
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#placeStuationDlg").dialog("close");
			   }
			}
		});
	});
});

function print(){
	$("#placeStuationPrint").printArea();
	$("#placeStuationDlg").dialog("close");
}


</script>