<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
<!--
#tb {
	border: 1px;
	border-color: black;
	border-style: solid;
}

#tb td {
	border: 1px;
	border-color: black;
	border-style: solid;
}
-->
</style>
<script src="jquery.js" type="text/javascript"></script>
<script type="text/javascript"><!--
	var dailyId=$("#dailyLogId").val();
	var mode=$("#mode").val();
	var shortName=$("#dailyDirectoryDetail-shortName").val();
	var orgLevle=$("#orgLevle").val();
	var reportType=1;
	if(shortName=='季报')
		reportType=2;
	else if(shortName=='月报')
		reportType=1;
	else
		reportType=0;
	$(document).ready(function(){
		hideByOrgCode();
		if(mode=="view"){
			findData();
		}
		findCollectionData();
	});

 	function collectData(){
 		if($("#dailyLogRemark").val()=="已上报"){
 			$.messageBox({message:"数据已经上报，无权修改"});
			return;
 	 	}else{
		 	var	action='${path}/dailylog/societySafeCheck/addSocietySafeCheck.action?dailyLogId='+dailyId+'&reportType='+reportType;
	 	    var rows = document.getElementById("tb").rows;
			var dataArray=new Array(rows.length+3);
			for(var i=0;i<rows.length;i++){
				var row = rows[i];
				var cell = row.cells[row.cells.length-1];
				dataArray[i]=cell.innerHTML;
			}
			dataArray[rows.length]=$("#createTabUser").val();
			dataArray[rows.length+1]=$("#checkUser").val();
			dataArray[rows.length+2]=$("#dailyLogName").val();
				
			$.ajax({
				url: action,
				type: 'post',
				data:{dataCollectionArray:dataArray.toString()},
				error: function(){
					$.messageBox({message:"保存报表数据失败,有非正常数据输入"});
				},
				success: function(){
					$.messageBox({message:"保存报表信息成功!"});
				}
			});
 		}
 	}

	function reportUp(){

		if($("#dailyLogRemark").val()=="已上报"){
			$.messageBox({message:"上报失败，不能重复上报!"});
			$("#dailyLogMaintanceDialog").dialog("close");
			return;
		}

		if($("#checkUser").val()==null||$("#checkUser").val()==""){
			$.messageBox({message:"上报失败，签发人不能为空!"});
			document.getElementById("checkUser").focus();
			return;
		}
		$.confirm({
			title:"确认上报",
			message:"上报后，将无法修改和删除,您确认上报吗?",
			width: 400,
			okFunc: function(){
				$("#collectData").click();
				var checkUser=$("#checkUser").val();
				$.ajax({
					url: '${path}/dailylog/societySafeCheck/reportUp.action?dailyLogId='+dailyId,
					type: 'get',
					data:{checkUser:checkUser},
					error: function(){
						$.messageBox({message:"上报失败!"});
					},
					success: function(){
						$.messageBox({message:"上报成功!"});
						init();
					}
				});
			}
		});
	}

	function findCollectionData(){
		var action='${path}/dailylog/societySafeCheck/findCollectionData.action?dailyLogId='+dailyId+'&reportType='+reportType;
		doCollectionData(action);
	}

	function reloadCollectionData(){
		if($("#dailyLogRemark").val()=="已上报"){
			$.messageBox({message:"已上报，无法重新生成!"});
			return;
		}
		var action='${path}/dailylog/societySafeCheck/reloadCollectionData.action?dailyLogId='+dailyId+'&reportType='+reportType;
		doCollectionData(action);
	}
 	
	function doCollectionData(action){
 		$.ajax({
			url: action,
			type: 'post',
			error: function(){
				if(orgLevle>1)
					$.messageBox({message:"目前还没有上报的数据!"});
			},
			success: function(resultData){
				$.each(resultData,function(index,value){
					if(index=='resultData'){
						var rows = document.getElementById("tb").rows;
						$.each(value,function(i,value){
							var row = rows[i];
							var cell = row.cells[row.cells.length-1];
							cell.innerHTML=value;
						})
					}
				});
			}
		});
	}

 	function findData(){
 		$.ajax({
			url: '${path}/dailylog/societySafeCheck/findSocietySafeCheck.action?dailyLogId='+dailyId,
			type: 'post',
			error: function(){
				$.messageBox({message:"查找失败!"});
			},
			success: function(resultData){
				$.each(resultData,function(index,value){
					if(index=='resultData'){
						var rows = document.getElementById("tb").rows;
						$.each(value,function(i,value){
							var row = rows[i];
							var cell = row.cells[row.cells.length-1];
							cell.innerHTML=value;
						})
					}
				});
			}
		});

	}
	function init(){
		$("#dailyLogRemark").val("已上报");
		$("#dailyLog-form").attr("action","${path}/dailylog/dailyLogManage/updateDailyLog.action");
		$("#dailyLog-form").ajaxSubmit();
		$("#dailyLogMaintanceDialog").dialog("close");
		reloadDailyLogList(false);
	}

	function hideByOrgCode(){
		var rows = document.getElementById("tb").rows;
		if(orgLevle>5){//国不具有上报功能
			$(".ui-dialog-buttonpane button").eq(2).hide();
			if($("#dailyLogRemark").val()!="已上报"){//国只能查看下级
				$("#checkUserContainer").hide();
			}
		}
		if(orgLevle<4){//市以下单位不具有对季报的上报功能
			if(shortName=='季报'){
				$(".ui-dialog-buttonpane button").eq(2).hide();
				$("#checkUserContainer").hide();
			}
		}
		if(mode!='view'&&$("#dailyLogRemark").val()=="已上报"){//上报后只能查看
			$("#checkUserContainer").hide();
		}
		if($("#dailyLogRemark").val()=="已上报"||mode=='view'){
				$("#checkUserContainer").html("签发人:"+"<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+$("#checkUser").val()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>");
		}
		
		if(orgLevle==2){//乡
			rows[12].cells[1].style.display="none";
			rows[12].cells[2].style.display="none";
			rows[22].cells[1].style.display="none";
			rows[22].cells[2].style.display="none";
			rows[27].cells[1].style.display="none";
			rows[27].cells[2].style.display="none";
		}
		if(orgLevle==1){//村
			rows[12].cells[1].style.display="none";
			rows[12].cells[2].style.display="none";
			rows[13].cells[0].style.display="none";
			rows[13].cells[1].style.display="none";
			rows[22].cells[1].style.display="none";
			rows[22].cells[2].style.display="none";
			rows[23].cells[0].style.display="none";
			rows[23].cells[1].style.display="none";
			rows[27].cells[1].style.display="none";
			rows[27].cells[2].style.display="none";
			rows[28].cells[0].style.display="none";
			rows[28].cells[1].style.display="none";
		}
	}
--></script>
<center><script>
		function toEdit(e){
			if(mode=='view'){
				return;
			}
			if(mode!='view'&&$("#dailyLogRemark").val()=="已上报"){
				$.messageBox({message:"上报之后无权修改!"});
				return;
			}
			var td = e.srcElement;
			if(td.id=='cell_11_3'||td.id=='cell_21_3'||td.id=='cell_26_3'||td.id=='cell_44_3'||td.id=='cell_48_3')
				return;
			
			if(document.getElementById("#input_"+td.id)!=null){
				return ;
			}
			var value = td.innerHTML;
			td.innerHTML="";
			e.srcElement.focus();
			$("<input style='' type='text' maxLength='10' value='' id='input_"+td.id+"'/>")
				.width($(td).width()-14)
				.css("border-left", "0px")
				.css("border-right","0px")
				.css("border-top","0px")
				.css("margin","0px")
				.bind('blur',function(evt){
					if(isNaN(evt.srcElement.value*1)){
						$.messageBox({message:"请输入数字!"});
						evt.srcElement.value=value;
					}
					if(evt.srcElement.value=='')
						evt.srcElement.value=value;
					toView(evt);
					if(evt.srcElement.value!=value)
						sumDate();
					
				})
				.appendTo(td);

			document.getElementById("input_"+td.id).focus();
		}

		function sumDate(){
			  var rows = document.getElementById("tb").rows;
				var dataQuery=new Array(rows.length);
				for(var i=0;i<rows.length;i++){
					var row = rows[i];
					var cell = row.cells[row.cells.length-1];
					dataQuery[i]=cell.innerHTML;
				}
				//dataQuery[0] = eval(dataQuery[1]) + eval(dataQuery[2]) + eval(dataQuery[3])+ eval(dataQuery[4]);				                                       	
	      		//dataQuery[5] = eval(dataQuery[6]) + eval(dataQuery[7]) + eval(dataQuery[8])+ eval(dataQuery[9]) + eval(dataQuery[10]);
	      		dataQuery[11] = eval(dataQuery[12]) + eval(dataQuery[13]) + eval(dataQuery[14])+ eval(dataQuery[15]);
	      		dataQuery[21] = eval(dataQuery[22])+ eval(dataQuery[23]) + eval(dataQuery[24])+ eval(dataQuery[25]);
	      		dataQuery[26] = eval(dataQuery[27]) + eval(dataQuery[28]) + eval(dataQuery[29])+ eval(dataQuery[30]);
	      		//dataQuery[31] = eval(dataQuery[32]) + eval(dataQuery[33]) + eval(dataQuery[34]);
	      		//dataQuery[35] = eval(dataQuery[36]) + eval(dataQuery[37]) + eval(dataQuery[38])+ eval(dataQuery[39]) + eval(dataQuery[40]);
	      		//dataQuery[41] = eval(dataQuery[42]) + eval(dataQuery[43]);
	      		dataQuery[44] = eval(dataQuery[45]) + eval(dataQuery[46]) + eval(dataQuery[47]);
	      		dataQuery[48] = eval(dataQuery[49]) + eval(dataQuery[50]) + eval(dataQuery[51]);
	      		for(var i=0;i<rows.length;i++){
					var row = rows[i];
					var cell = row.cells[row.cells.length-1];
					cell.innerHTML=eval(dataQuery[i]);
				}
		}
		
		function toView(e){
			var input = e.srcElement;
			if(input.value=="")
				input.value = 0;
			input.parentNode.innerHTML = parseInt(input.value,10) ;//防止01、001自动转换成八进制数
		}
		(function($){
			$(document).ready (function(){
				var rows = document.getElementById("tb").rows;
				for(var i=0;i<rows.length;i++){
					var row = rows[i];
					var cell = row.cells[row.cells.length-1];
					cell.style.height="24px";
					if(cell.id==null || cell.id==""){
						cell.id="cell_"+i+"_3";
					}
					$(cell).bind("click",function(e){
						toEdit(e);
					});
				}
			});
		})(jQuery)
	</script>

<div style="display: none;">
<form id="dailyLog-form" method="post" action=""><input id="mode"
	type="hidden" name="mode" value="${mode}" /> <input id="dailyLogId"
	type="hidden" name="dailyLog.id" value="${dailyLog.id}" /> <input
	id="dailyLogDailyYearId" type="hidden" name="dailyLog.dailyYear.id"
	value="${dailyLog.dailyYear.id}" /> <input id="dailyDirectoryId"
	type="hidden" name="dailyLog.dailyDirectory.id"
	value="${dailyLog.dailyDirectory.id}" /> <input id="organizationId"
	type="hidden" name="dailyLog.organization.id"
	value="${dailyLog.organization.id}" /> <input type="text"
	name="dailyLog.dailyDirectory.shortName"
	id="dailyDirectoryDetail-shortName"
	value="${dailyLog.dailyDirectory.shortName}" /> <input type="text"
	name="dailyLog.dealDate" id="dailyLogDealDate"
	value='<s:date name="dailyLog.dealDate" format="yyyy-MM-dd"/>' /> <input
	type="text" name="dailyLog.name" id="dailyLogName"
	value="${dailyLog.name}" /> <input type="text" name="dailyLog.remark"
	id="dailyLogRemark" value="${dailyLog.remark}" /> <input type="text"
	name="dailyLog.theme" id="dailyLogTheme" value="${dailyLog.theme}" />
<input type="text" name="dailyLog.convenor" class="form-txt"
	value="${dailyLog.convenor}" /><input type="text"
	name="organization.orgInternalCode"
	value="${organization.orgInternalCode}" id="orgInternalCode" />
	<input type="text"
	name="orgLevle"
	value="${orgLevle}" id="orgLevle" />
	</form>

<a href="javascript:void(0)" onclick="collectData()" id="collectData"></a>
<a href="javascript:void(0)" onclick="reloadCollectionData()"
	id="reloadCollectionData"></a> <a href="javascript:void(0)"
	onclick="findData()" id="findData"></a> <a href="javascript:void(0)"
	onclick="reportUp()" id="reportUp"></a></div>

<form id="societySafeCheck-form" method="post" action=""><span
	id="reportName"> ${dailyLog.name}</span><br />
<div>
<table id="tb" border="1"
	style="table-layout: fixed; border-collapse: collapse;">
	<tr>
		<td rowspan="5" align="center" style="width: 130px; height: 120px;">排查次数</td>
		<td colspan="2" align="center" style="width: 260px;">总数</td>
		<td style="width: 100px; height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="4" align="center" style="width: 130px; height: 98px;">其中</td>
		<td style="width: 130px; height: 24px;">发动干部数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>发动群众数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>组织工作组数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>其他</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td rowspan="6" align="center" style="width: 130px; height: 120px;">宣传发动数</td>
		<td colspan="2" align="center" style="width: 260px;">总数</td>
		<td style="width: 100px; height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="5" align="center" style="width: 130px; height: 98px;">其中</td>
		<td style="width: 130px; height: 24px;">发布通告数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>召开座谈会数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>群众举报数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>从中破获刑事案件数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>从中抓获犯罪嫌疑人数</td>
		<td style="height: 24px;">0</td>
	</tr>


	<tr>
		<td rowspan="10" align="center" style="width: 140px;">排查发现治安重点地区数</td>
		<td colspan="2" align="center" style="width: 260px;">总数</td>
		<td style="width: 100px; height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="4" align="center" style="width: 130px;">其中</td>
		<td style="width: 130px; height: 24px;">县市区数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>乡镇(街道)数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>村(社区)数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>其他</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td rowspan="5" align="center" style="width: 130px;">其中</td>
		<td style="width: 130px; height: 24px;">黑恶势力地区数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>杀人爆炸等严重暴力犯罪地区数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>两抢一盗犯罪地区数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>黄赌毒违法犯罪地区数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>邪教组织违法犯罪地区数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td rowspan="5" align="center" style="width: 130px;">已整治重点地区数</td>
		<td colspan="2" align="center" style="width: 260px;">总数</td>
		<td style="width: 100px; height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="4" align="center" style="width: 130px;">其中</td>
		<td style="width: 130px; height: 24px;">县市区数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>乡镇(街道)数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>村(社区)数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>其他</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="5" align="center" style="width: 130px;">正在整治重点地区数</td>
		<td colspan="2" align="center" style="width: 260px;">总数</td>
		<td style="width: 100px; height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="4" align="center" style="width: 130px;">其中</td>
		<td style="width: 130px; height: 24px;">县市区数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>乡镇(街道)数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>村(社区)数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>其他</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td rowspan="13" align="center" style="width: 130px; height: 120px;">打击整治数</td>
		<td rowspan="4" align="center" style="width: 130px; height: 98px;">破获刑事案件数</td>
		<td>总数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>杀人爆炸等严重暴力案件数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>两抢一盗案件数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>黄赌毒案件数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="6" align="center" style="width: 130px; height: 98px;">抓获犯罪嫌疑人数</td>
		<td>总数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>流串犯罪嫌疑人数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>外来人员</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>无业人员</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>刑释人员</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>14-25周岁青少年</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="3" align="center" style="width: 130px; height: 72px;">打掉黑恶势力数</td>
		<td>总数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>黑社会性质组织数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>恶势力</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td rowspan="4" align="center" style="width: 130px; height: 96px;">警示数</td>
		<td colspan="2" align="center" style="width: 260px;">总数</td>
		<td style="width: 100px; height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="3" align="center" style="width: 130px; height: 72px;">其中</td>
		<td style="width: 130px; height: 24px;">省级警示数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>地市警示数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>县市区警示数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td rowspan="4" align="center" style="width: 130px; height: 96px;">挂牌整治数</td>
		<td colspan="2" align="center" style="width: 260px;">总数</td>
		<td style="width: 100px; height: 24px;">0</td>
	</tr>
	<tr>
		<td rowspan="3" align="center" style="width: 130px; height: 72px;">其中</td>
		<td style="width: 130px; height: 24px;">省级挂牌数</td>
		<td style="height: 24px;">0</td>
	</tr>
	<tr>
		<td>地市挂牌数</td>
		<td style="height: 24px;">0</td>
	</tr>

	<tr>
		<td>县市区挂牌数</td>
		<td style="height: 24px;">0</td>
	</tr>

</table>

<br />
<div style="display: none;">建表人：<input
	name="societySafeCheck.createTabUser" id="createTabUser"
	value="${dailyLog.convenor}" type="text" /></div><div id="checkUserContainer">
签发人：<input name="societySafeCheck.checkUser" id="checkUser"  maxlength="10" type="text" style="border-width: 0;BORDER-BOTTOM:black 1px solid;" 
	value="${societySafeCheck.checkUser}" /> (注意:该项上报时填写)</div></div>
</form>
</center>

