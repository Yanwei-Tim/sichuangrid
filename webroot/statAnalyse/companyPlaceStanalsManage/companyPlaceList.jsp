<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="nav" class="ui-corner-all">
		<s:if test="#parameters.moduleType == null">
			<select id="type" style="float:right;">
			<option value="1">单位场所数量统计表1</option>
			<option value="2">单位场所数量统计表2</option>
			</select>
		</s:if>
		<s:else>
			<input id="type" type="hidden" value="1" />
		</s:else>
		<select name="queryYear" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="month" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
		<a id="createStatistic" hidden="true" href="javascript:void(0)"><span>生成报表</span></a>
		</s:if>
</div>
<div class="content">
	<div id="gridbox" class="SigmaReport" style="overflow:hidden !important;"></div>
	<div id="number"> </div>
</div>
<div id="populationSituationDlg"></div>
<script type="text/javascript">
if($("#currentOrgId").attr("orglevelinternalid") <= <s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT' />){
	$("#type option:last").remove();
}
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
function onOrgChanged(){
	var moduleType='<s:property value="#parameters.moduleType[0]"/>';
	switch($("#type").val()){
	case '1':
		getStatisticsCompanyPlaceList(null,moduleType);
		break;
	case '2':
		getStatisticsCompanyPlaceList(2,moduleType);
		break;
	}
	enableOrDisableColumn(1);
}

function getStatisticsCompanyPlaceList(orgLevelDistance,moduleType){
	var url = "/baseInfoStat/companyPlaceStanalsManage/getStatisticsCompanyPlaceList.action?orgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&moduleType="+moduleType;
	if(orgLevelDistance != null){
		url = url + "&orgLevelDistance="+orgLevelDistance;
	}
	$.ajax({
			url:url,
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
				{name:"populationDetailDatas[index].name",caption:"类型",width:150,mode:"string"},
				{name:"populationDetailDatas[index].amount",caption:"数量",width:80,format:"#",showLink:false},
				{name:"populationDetailDatas[index].amountPercentage",caption:"百分比",width:80,mode:"number",align:'center',format:"#.00%"}
			]}
		];
	grid = new SigmaReport("gridbox",context,columns);
	onOrgChanged();


	$("#createStatistic").click(function(){
		var moduleType='${param.moduleType}';
		$.ajax({
			url:'/baseInfoStat/companyPlaceStanalsManage/createStatisticsCompanyPlaceList.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&moduleType="+moduleType,

			success:function(responseData){

				$.messageBox({message:"报表生成成功"});
			}
		});

	});


	$("#searchList").click(function(){
		onOrgChanged();
	});
	onTypeChanged();
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	$(".print").click(function(){
		var moduleType='${param.moduleType}';
		var url = "${path}/statAnalyse/companyPlaceStanalsManage/companyPlaceSituationPrint.jsp?parentOrgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&moduleType="+moduleType;
		if($("#type").val() == '2'|| $("#type").val() == 2){
			url = url + "&orgLevelDistance=2";
		}
		$("#populationSituationDlg").createDialog({
			width:700,
			height:550,
			title:"打印单位场所总况",
			url:url,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#populationSituationDlg").dialog("close");
			   }
			}
		});
	});

});

function onTypeChanged() {
	$("#type").change(function(){
		onOrgChanged();
	});
}
function print(){
	$("#personSituationGridbox").printArea();
	$("#populationSituationDlg").dialog("close");
}


</script>