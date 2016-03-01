<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<select id="isSearch" name="isSearch" class="S_object">
			<option value="0">全部</option>
			<option value="1">导入中</option>
			<option value="2">导入结束</option>
		</select>
		<pop:JugePermissionTag ename="searchExcelImportLog">
			<a id="searchExcelImportLog" href="javascript:void(0)"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
	</div>
	
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="excelImportLogList"></table>
		<div id="excelImportLogListPager"></div>
	</div>
	<div id="excelImportLoglog" style="overflow: hidden"></div>
</div>
<script type="text/javascript">
function downErrorMsg(errorMessageExcelName,dataType){
	return "<a href='${path}/dataTransfer/downloadErrorDataTemplate.action?dataTemplatesName="+errorMessageExcelName+"&dataType="+dataType+"' ><font color='red'> 下载  </font></a>";
}
function callback(){
    var dfop = {
    		
    }
    TQ.excelImportLogList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/baseinfo/excelimportlog/excelImportLogList.js',
    callback: callback
})



</script>