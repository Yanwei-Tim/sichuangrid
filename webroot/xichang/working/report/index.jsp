<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<style>
#orgSelector{
	width:138px
}
.print{
	float:right;
	width: 24px;
	height: 20px;
	margin: 4px;
	background: url(/resource/system/images/table_print_hover.jpg) no-repeat;
}
</style>
<input type="hidden" id="accountType" value="<s:property value='#parameters.accountType[0]'/>"/>
<input type="hidden" id="canCreateStatistic" value="0" />
<div id="nav" class="ui-corner-all">
	<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<select id="townType" style="float:right;">
		<option value="1">乡镇1</option>
		<option value="2">乡镇2</option>
	</select>
	<select id="districtType" style="float:right;">
		<option value="1">县(区)</option>
		<option value="2">西昌市1</option>
		<option value="3">西昌市2</option>
		<option value="4">西昌市3</option>
	</select>
	<select name="reportYear" id="year" onchange="">
    </select>
    <label style="padding:0 10px;line-height:25px;">年</label>
    <select name="reportMonth" id="month" onchange="">
    </select>
    <label style="padding:0 10px;line-height:25px;">月</label>
<%-- 	<a id="searchList" href="javascript:void(0)"><span>查询</span></a> --%>
	<a id="createStatistic" href="javascript:void(0)"><span>刷新</span></a>
	  <s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
            	<a id="createDistrict" href="javascript:void(0)"><span>生成县乡镇数据</span></a>
            	<a id="createVillage" href="javascript:void(0)"><span>生成社区数据</span></a>
            </s:if>
	<a class="edit" style="float:right;"  href="javascript:void(0)"><span>刷新并编辑</span></a>
	<label class="print" title="打印"></label>
</div>
<div id="gridboxType">
</div>
<div id="accountPrintDlg">
</div>

<script type="text/javascript">
$(function(){
	//时间初始化
	autoFillYearAndMonth();
	loadPage();
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	$("#year,#month").change(function(){
		loadCountDatas();
	})
	$("#townType").hide().change(function(){
		loadPageByTownType(this.value);
	});
	$("#districtType").hide().change(function(){
		loadPageByDistrictType(this.value);
	});
	$(".edit").hide().click(function(){
		var date = new Date();
		if (!((parseInt($("#year").val()) == date.getFullYear()) && (parseInt($("#month").val()) == date.getMonth() + 1))) {
			$.messageBox({level:"error",message:"历史报表不允许被编辑！"});
			return;
		}
		$(this).toggleClass("on");//开/关
		if ($(this).hasClass("on")) {//开关替换
			$(this).html("<span>保存</span>");
			editAccountReport();
			bindClickForEditEnableTr();
		} else {
			$(this).html("<span>刷新并编辑</span>");
			saveAccountReport();
			$("tr.editEnable td[id*='col']").unbind("click");
		}
	});
	 //生成县乡镇数据
	   $("#createDistrict").click(function (){
		   createAccountReportData($("#year").val(),$("#month").val(),1);
	   });
	   //生成社区数据
	   $("#createVillage").click(function (){
		   createAccountReportData($("#year").val(),$("#month").val(),0);
	   });
	$("#createStatistic").click(function(){
		var date = new Date();
		var canCreateStatistic=$("#canCreateStatistic").val();
		if (!((parseInt($("#year").val()) == date.getFullYear()) && (parseInt($("#month").val()) == date.getMonth() + 1))) {
			$.messageBox({level:"error",message:"不允许重新生成历史报表！"});
			return;
		}
		if(canCreateStatistic==1){
			editAccountReport();
		}
	});
	
	$(".print").click(function(){
		$("#accountPrintDlg").createDialog({
			width:900,
			height:600,
			title:"打印",
			url:'${path}/xichang/working/report/print.jsp',
			buttons: {
			   "打印" : function(){
				   print();
		  	   },
			   "关闭" : function(){
				   $("#accountPrintDlg").dialog("close");
			   }
			}
		});
	});
});

function createAccountReportData(year,month,accountLevel){
	$.ajax({
		url: "${path }/account/reportManage/createAccountReportData.action?searchVo.reportYear="+year+"&searchVo.reportMonth="+month+"&searchVo.reportType="+accountLevel,
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	});
	
}
function loadPage(){//加载页面
	var loadComplete = function(){
		if(isTownOrganization()){
			$("#townType").show();
		}else if(isDistrictOrganization() && $("#currentOrgId").attr("text")=="西昌市"){
			$("#districtType").show();
		}
	}
	if($("#accountType").val()=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@PEOPLEASPIRATION'/>"){
		$("#gridboxType").load("${path}/xichang/working/report/peopleAspirationReportList.jsp",loadComplete);
	}else if($("#accountType").val()=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE'/>"){
		$("#gridboxType").load("${path}/xichang/working/report/poorPeopleReportList.jsp",loadComplete);
	}else if($("#accountType").val()=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@STEADYWORK'/>"){
		$("#gridboxType").load("${path}/xichang/working/report/steadyWorkReportList.jsp",loadComplete);
	}
}
function autoFillYearAndMonth(){
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
	
}

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

function bindClickForEditEnableTr(){
	$("tr.editEnable td[id*='col']").closest("td[id!='colAll']").unbind("click").click(function(){
		var self = $(this);
		if(!self.hasClass("inputOut")){
			self.addClass("inputOut");
			var value=self.text();
			self.html("");
			var inputDataReport=$("<input onblur='inputBlur($(this))' onkeydown='inputKeyDown($(this))' onkeyup='inputKeyOut($(this))' onfocus='inputOnFocus($(this))' title='只能输入非负整数!' style='width:50px' name='' class='reportDataInput' maxLength='12' />");
			self.append(inputDataReport);
			inputDataReport.focus().val(value!=0?value:'');
		}
	});
}

function inputBlur(selfDoc){
	selfDoc.keyup();
	var selfDocValue=selfDoc.val();
	var tdDoc = selfDoc.parent();
	tdDoc.html(selfDocValue!=''?selfDocValue:0);
	tdDoc.removeClass("inputOut");
	countRowData(tdDoc.parent())
}

function inputKeyDown(selfDoc){

}

function inputKeyOut(selfDoc){
	$(selfDoc).val($(selfDoc).val().replace(/[^0-9]/g,''));
}

function inputOnFocus(selfDoc){
	$(document).unbind("keypress");
	$(document).keypress(function(e) {
		if (e.which =='13'){
			selfDoc.closest("tr").nextAll("tr.editEnable").each(function(i){
				if($(this).find("td[id*='col']")[0]){
					$(this).find("td[id*='col']").click();
					return false;
				}
			})
		}
	})
}

function countRowData($row){
	$cols = $row.find("td[id*='col']");
	$colAll = $row.find("td[id='colAll']");
	var sum = 0;
	$cols.each(function(){
		if(this.id!="colAll"){
			sum += eval($(this).text());
		}
	})
	$colAll.text(sum);
}

function print(){
	$("#accountPrint").printArea();
	$("#accountPrintDlg").dialog("close");
}


//县区
function isDistrictOrganization(){
	return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>;
}

//乡镇
function isTownOrganization(){
	return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>;
}

//村
function isVillageOrganization(){
	return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
}

</script>