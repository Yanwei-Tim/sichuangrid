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
		<td colspan="36" id="timeShow" style="height: 50px;text-align: center;font-size: 24px;color: black;font-weight: bold;">
		中江县三本台账工作民生信息情况统计表(能源类)(第一部分)
		</td>
		<td colspan="32" id="timeShow" style="height: 50px;text-align: center;font-size: 24px;color: black;font-weight: bold;">
		中江县三本台账工作民生信息情况统计表(能源类)(第二部分)
		</td>
	</tr>
	<tr>
		<td colspan="36" style="text-align: center;font-size: 14px;">数据收集期间： <s:date name="searchVo.beginCreateDate" format="yyyy年MM月dd日" />——<s:date name="searchVo.endCreateDate" format="yyyy年MM月dd日" /></td>
		<td colspan="32" style="text-align: center;font-size: 14px;">数据收集期间： <s:date name="searchVo.beginCreateDate" format="yyyy年MM月dd日" />——<s:date name="searchVo.endCreateDate" format="yyyy年MM月dd日" /></td>
	</tr>
	
	
	<tr class="countlistHead" style="text-align: center;">
		<td rowspan="3" style="text-align: center;">内容</td>
		<td colspan="6" style="text-align: center;">合计</td>
		<td colspan="10" style="text-align: center;">天然气</td>
		<td colspan="10" style="text-align: center;">石油液化气</td>
		<td colspan="9" style="text-align: center;">汽柴油</td>
		<td colspan="13" style="text-align: center;">电力</td>
		<td colspan="9" style="text-align: center;">沼气</td>
		<td colspan="6" style="text-align: center;">其他</td>
		<td colspan="4" style="text-align: center;">处理办结情况</td>
	</tr>
	<tr class="countlistHead">
		<td rowspan="2">件数</td>
		<td rowspan="2">反映群体人数</td>
		<td rowspan="2">计划投资（万元）</td>
		<td rowspan="2">自筹资金（万元）</td>
		<td rowspan="2">缺口资金（万元）</td>
		<td rowspan="2">受益人口（人）</td>
		
		<td rowspan="2">件数</td>
		<td rowspan="2">反映群体人数</td>
		<td colspan="2">管道建设</td>
		<td colspan="2">管道养护</td>
		<td rowspan="2">户表工程（处）</td>
		<td rowspan="2">安全设施建设（处）</td>
		<td rowspan="2">计划投资（万元）</td>
		<td rowspan="2">受益人口（人）</td>
		
		<td rowspan="2">件数</td>
		<td rowspan="2">反映群体人数</td>
		<td colspan="2">管道建设</td>
		<td colspan="2">管道养护</td>
		<td rowspan="2">户表工程（处）</td>
		<td rowspan="2">安全设施建设（处）</td>
		<td rowspan="2">计划投资（万元）</td>
		<td rowspan="2">受益人口（人）</td>
		
		<td rowspan="2">件数</td>
		<td rowspan="2">反映群体人数</td>
		<td rowspan="2">防护栏（处）</td>
		<td rowspan="2">防撞墩（处）</td>
		<td rowspan="2">减速带（处）</td>
		<td rowspan="2">安全标志标牌（处）</td>
		<td rowspan="2">防火设施（处）</td>
		<td rowspan="2">计划投资（万元）</td>
		<td rowspan="2">受益人口（人）</td>
		
		<td rowspan="2">件数</td>
		<td rowspan="2">反映群体人数</td>
		<td colspan="3">电网设施</td>
		<td colspan="3">电网维护</td>
		<td rowspan="2">户表工程（处）</td>
		<td rowspan="2">计划投资（万元）</td>
		<td rowspan="2">自筹资金（万元）</td>
		<td rowspan="2">缺口资金（万元）</td>
		<td rowspan="2">受益人口（人）</td>
		
		<td rowspan="2">件数</td>
		<td rowspan="2">反映群体人数</td>
		<td colspan="2">沼气池建设</td>
		<td rowspan="2">沼气池安全设施建设（处）</td>
		<td rowspan="2">计划投资（万元）</td>
		<td rowspan="2">自筹资金（万元）</td>
		<td rowspan="2">缺口资金（万元）</td>
		<td rowspan="2">受益人口（人）</td>
		
		<td rowspan="2">件数</td>
		<td rowspan="2">反映群体人数</td>
		<td rowspan="2">计划投资（万元）</td>
		<td rowspan="2">自筹资金（万元）</td>
		<td rowspan="2">缺口资金（万元）</td>
		<td rowspan="2">受益人口（人）</td>
		
		<td rowspan="2">实质办结</td>
		<td rowspan="2">问题终止</td>
		<td rowspan="2">程序性办结</td>
		<td rowspan="2">办理中</td>
	</tr>
	
	<tr class="countlistHead">
		<td>条</td>
		<td>公里</td>
		<td>处</td>
		<td>公里</td>
		
		<td>条</td>
		<td>公里</td>
		<td>处</td>
		<td>公里</td>
		
		<td>处</td>
		<td>公里</td>
		<td>台</td>
		<td>处</td>
		<td>公里</td>
		<td>台</td>
		
		<td>口</td>
		<td>立方米</td>
	</tr>
	${tr }
</table>
</div>