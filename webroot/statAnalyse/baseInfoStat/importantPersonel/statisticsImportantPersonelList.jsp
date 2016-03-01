<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="month" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>
</div>
<div class="content" >
	<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="importPersonSituationDlg"></div>
<script type="text/javascript">
var grid = null;
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
function onOrgChanged(){
	$.ajax({
		url:'/baseInfoStat/statisticsPersonnel/getStatisticsImportantPersonlList.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&typeTableName=IMPORTANTPERSONNEL",
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
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#year").val());
	$("#isNowMonth").val($("#month").val());
	$.gridboxHeight();
	var context = {};
	var columns = [
			{name:"org.orgName",caption:"区域",width:120,mode:"string"},
			{name:"general",caption:"总况",children:[
				{name:"personnelDetailDatas[index].name",caption:"类型",width:150,mode:"string"},
				{name:"personnelDetailDatas[index].amount",caption:"数量",width:80,mode:"link",format:"#",showLink:"personnelDetailDatas[index].showLink",href:"shwoInfoInDialog(row.data.personnelDetailDatas[index].name, row.data.org.id)"},
				{name:"personnelDetailDatas[index].amountPercentage",caption:"百分比",width:80,mode:"number",format:"#.00%"}
			]}
		];
	grid = new SigmaReport("gridbox",context,columns);
	onOrgChanged();
	$("#searchList").click(function(){
		onOrgChanged();
	});
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	$(".print").click(function(){
		$("#importPersonSituationDlg").createDialog({
			width:650,
			height:550,
			title:"打印人员总况",
			url:"${path}/statAnalyse/baseInfoStat/importantPersonel/importPersonelSituationPrint.jsp?parentOrgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&typeTableName=IMPORTANTPERSONNEL",
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#importPersonSituationDlg").dialog("close");
			   }
			}
		});
	});

});
function print(){
	$("#importPersonSituationPrint").printArea();
	$("#importPersonSituationDlg").dialog("close");
}


</script>