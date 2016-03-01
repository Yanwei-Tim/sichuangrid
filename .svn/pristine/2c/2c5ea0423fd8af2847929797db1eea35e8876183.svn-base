<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<script type="text/javascript">
$("#ecxcelExport").click(function(){
	exportReport('tableId');
});

var idTmr;
function  getExplorer() {
	var explorer = window.navigator.userAgent ;
	//ie 
	if (explorer.indexOf("MSIE") >= 0) {
		return 'ie';
	}
	//firefox 
	else if (explorer.indexOf("Firefox") >= 0) {
		return 'Firefox';
	}
	//Chrome
	else if(explorer.indexOf("Chrome") >= 0){
		return 'Chrome';
	}
	//Opera
	else if(explorer.indexOf("Opera") >= 0){
		return 'Opera';
	}
	//Safari
	else if(explorer.indexOf("Safari") >= 0){
		return 'Safari';
	}
}
function exportReport(tableId) {//整个表格拷贝到EXCEL中
	if(getExplorer()=='ie'){
		var curTbl = document.getElementById(tableId);
		var oXL = new ActiveXObject("Excel.Application");
		
		//创建AX对象excel 
		var oWB = oXL.Workbooks.Add();
		//获取workbook对象 
		var xlsheet = oWB.Worksheets(1);
		//激活当前sheet 
		var sel = document.body.createTextRange();
		sel.moveToElementText(curTbl);
		//把表格中的内容移到TextRange中 
		sel.select();
		//全选TextRange中内容 
		sel.execCommand("Copy");
		//复制TextRange中内容  
		xlsheet.Paste();
		//粘贴到活动的EXCEL中       
		oXL.Visible = true;
		//设置excel可见属性

		try {
			var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
		} catch (e) {
			print("Nested catch caught " + e);
		} finally {
			oWB.SaveAs(fname);

			oWB.Close(savechanges = false);
			//xls.visible = false;
			oXL.Quit();
			oXL = null;
			//结束excel进程，退出完成
			//window.setInterval("Cleanup();",1);
			idTmr = window.setInterval("Cleanup();", 1);

		}
		
	}
	else{
		tableToExcel(tableId)
	}
}
function Cleanup() {
    window.clearInterval(idTmr);
    CollectGarbage();
}
var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,',
	  template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
		base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
		format = function(s, c) {
			return s.replace(/{(\w+)}/g,
			function(m, p) { return c[p]; }) }
		return function(table, name) {
		if (!table.nodeType) table = document.getElementById(table)
		var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
		window.location.href = uri + base64(format(template, ctx))
	  }
	})()

</script>

<div id="nav" class="ui-corner-all">
	<a id="ecxcelExport" href="javascript:void(0)"><span>报表下载</span></a>
</div>

<div style="overflow: auto;height: 440px;width: 1150px;" class="reportData">
<table style="width: 5000px;" class="countlist" id="tableId">
	<tr> 
		<td colspan="54" id="timeShow" style="height: 50px;text-align: center;font-size: 24px;color: black;font-weight: bold;">
		中江县三本台账工作困难群众信息情况统计表
		</td>
	</tr>
	<tr>
		<td colspan="54" style="text-align: center;font-size: 14px;">数据收集期间： <s:date name="searchVo.beginCreateDate" format="yyyy年MM月dd日" />——<s:date name="searchVo.endCreateDate" format="yyyy年MM月dd日" /></td>
	</tr>
	
	
	<tr class="countlistHead" style="text-align: center;">
		<td rowspan="2" style="text-align: center;">内容</td>
		<td rowspan="2" style="text-align: center;">合计（件数）</td>
		<td colspan="6" style="text-align: center;">生活困难</td>
		<td colspan="3" style="text-align: center;">医疗困难</td>
		<td colspan="6" style="text-align: center;">住房困难</td>
		<td colspan="7" style="text-align: center;">就学困难</td>
		<td colspan="9" style="text-align: center;">就业困难</td>
		<td colspan="11" style="text-align: center;">具体需求</td>
		<td colspan="10" style="text-align: center;">处理办结情况</td>
	</tr>
	<tr class="countlistHead">
		<td>小计（件数）</td>
		<td>因病（件数）</td>
		<td>因残（件数</td>
		<td>因灾（件数）</td>
		<td>缺乏劳动能力（件数）</td>
		<td>其他（件数）</td>
		
		<td>小计（件数）</td>
		<td>重大疾病（件数）</td>
		<td>其他（件数）</td>
		
		<td>小计（件数）</td>
		<td>水灾（件数）</td>
		<td>地灾（件数）</td>
		<td>火灾（件数）</td>
		<td>危房改造（件数）</td>
		<td>其他（件数）</td>
		
		<td>小计（件数）</td>
		<td>学前教育（件数）</td>
		<td>小学（件数）</td>
		<td>初中（件数）</td>
		<td>高中职高（件数）</td>
		<td>大学（件数）</td>
		<td>其他（件数）</td>
		
		<td>小计</td>
		<td>城镇登记失业（人数）</td>
		<td>4050人员（人数）</td>
		<td>残疾人员（人数）</td>
		<td>低收入家庭人员（人数）</td>
		<td>按城镇人口安置的被征地农民（人数）</td>
		<td>连续失业一年以上（人数）</td>
		<td>土地（林地）被依法征用或流转的农村劳动者（人数）</td>
		<td>其他（人数）</td>
		
		<td>口粮（件数）</td>
		<td>五保（件数）</td>
		<td>低保（件数）</td>
		<td>救助资金（件数）</td>
		<td>救助物资（件数）</td>
		<td>住房（件数）</td>
		<td>职业培训或指导（件数）</td>
		<td>职业指导（件数）</td>
		<td>求职登记（件数）</td>
		<td>职业介绍件数）</td>
		<td>其他（件数）</td>
		
		<td>实质性解决困难</td>
		<td>脱贫</td>
		<td>问题终止</td>
		<td>政策解答</td>
		<td>纳入低保</td>
		<td>纳入五保</td>
		<td>纳入项目库</td>
		<td>临时救助</td>
		<td>落实帮扶</td>
		<!-- <td>呈报乡镇</td>
		<td>呈报县台账办</td>
		<td>申报县联席会议</td>
		<td>申报县委县政府</td> -->
		<td>其他</td>
	</tr>
	${tr }
</table>
</div>