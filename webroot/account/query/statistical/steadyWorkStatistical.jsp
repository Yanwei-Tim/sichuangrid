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
<table style="width: 6000px;" class="countlist" id="tableId">
	<tr> 
		<td colspan="43" id="timeShow" style="height: 50px;text-align: center;font-size: 24px;color: black;font-weight: bold;">
		中江县三本台账工作稳定信息情况统计表（第一部分）
		</td>
		<td colspan="42" id="timeShow" style="height: 50px;text-align: center;font-size: 24px;color: black;font-weight: bold;">
		中江县三本台账工作稳定信息情况统计表（第二部分）
		</td>
		<td colspan="40" id="timeShow" style="height: 50px;text-align: center;font-size: 24px;color: black;font-weight: bold;">
		中江县三本台账工作稳定信息情况统计表（第三部分）
		</td>
	</tr>
	<tr>
		<td colspan="43" style="text-align: center;font-size: 14px;">数据收集期间： <s:date name="searchVo.beginCreateDate" format="yyyy年MM月dd日" />——<s:date name="searchVo.endCreateDate" format="yyyy年MM月dd日" /></td>
		<td colspan="42" style="text-align: center;font-size: 14px;">数据收集期间： <s:date name="searchVo.beginCreateDate" format="yyyy年MM月dd日" />——<s:date name="searchVo.endCreateDate" format="yyyy年MM月dd日" /></td>
		<td colspan="40" style="text-align: center;font-size: 14px;">数据收集期间： <s:date name="searchVo.beginCreateDate" format="yyyy年MM月dd日" />——<s:date name="searchVo.endCreateDate" format="yyyy年MM月dd日" /></td>
	</tr>
	
	
	<tr class="countlistHead" style="text-align: center;">
		<td rowspan="2" style="text-align: center;">内容</td>
		<td colspan="7" style="text-align: center;">合计</td>
		<td colspan="7" style="text-align: center;">涉法涉诉</td>
		<td colspan="7" style="text-align: center;">林水土</td>
		<td colspan="7" style="text-align: center;">惠农政策问题及村(社区)政务、财务</td>
		<td colspan="7" style="text-align: center;">民政问题</td>
		<td colspan="7" style="text-align: center;">人口与医疗卫生</td>
		<td colspan="7" style="text-align: center;">劳动保障</td>
		<td colspan="7" style="text-align: center;">交通运输</td>
		<td colspan="7" style="text-align: center;">城建及综合执法</td>
		<td colspan="7" style="text-align: center;">党纪政纪</td>
		<td colspan="7" style="text-align: center;">教育</td>
		<td colspan="7" style="text-align: center;">企业改制</td>
		<td colspan="7" style="text-align: center;">环境保护</td>
		<td colspan="7" style="text-align: center;">组织人事</td>
		<td colspan="7" style="text-align: center;">其他类</td>
		<td colspan="7" style="text-align: center;">重点人员</td>
		<td colspan="7" style="text-align: center;">其他</td>
		<td colspan="5" style="text-align: center;">处理办结情况</td>
	</tr>
	<tr class="countlistHead">
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>件数</td>
		<td>反映群体人数</td>
		<td>涉稳人数</td>
		<td>蓝色</td>
		<td>黄色</td>
		<td>橙色</td>
		<td>红色</td>
		
		<td>实质化解</td>
		<td>程序性终结</td>
		<td>实质性终结</td>
		<td>程序性办结</td>
		<td>办理中</td>
	</tr>
	${tr }
</table>
</div>