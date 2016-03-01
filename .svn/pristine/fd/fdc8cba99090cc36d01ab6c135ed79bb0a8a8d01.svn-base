<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="content">
	<div class="ui-corner-all content-top" id="nav">
		身份证号码：<input type="text" id="expIdCardNo" style="padding: 3px; width: 200px;"/>
		<a id="queryIllegalIdCardNoList" href="javascript:void(0)"><span>查询</span></a>
		<a id="reloadIllegalIdCardNoList" href="javascript:void(0)"><span>刷新</span></a>
		<a id="exportIllegalIdCardNoList" href="javascript:void(0)"><span>导出</span></a>
	</div>
	<div style="width: 100%;">
		<table id="illegalIdCardNoList"></table>
		<div id="illegalIdCardNoListPager"></div>
	</div>
	<form action="" method="post" id="exceldownload" ></form>
</div>
<script type="text/javascript">
function reloadIllegalIdCardNoList(){
	$("#illegalIdCardNoList").setGridParam({
		url:"${path}/tbScheduleLog/illegalIdCardNoList.action",
		datatype: "json",
		page:1
	});
	$("#illegalIdCardNoList").setPostData({
		"idCardNoExpLog.expIdCardNo": $("#expIdCardNo").val()
   	});
	$("#illegalIdCardNoList").trigger("reloadGrid");
}
$(document).ready(function(){
	$("#illegalIdCardNoList").jqGridFunction({
		datatype: "local",
		sortname: 'expIdCardNo',
		sotorder: 'desc',
	   	colModel:[
		       {name:"expIdCardNo", label:"身份证号码", width: 360, align: 'center'},
		       {name:'expMessage', label:"描述", width: 720, align: 'center'}
	   	],
	   height:$(".ui-layout-center").height()-170
	});
	reloadIllegalIdCardNoList();
	$("#reloadIllegalIdCardNoList").click(function(){
		$("#expIdCardNo").val('');
		reloadIllegalIdCardNoList();
	});
	$("#queryIllegalIdCardNoList").click(function(){
		reloadIllegalIdCardNoList();
	});
	
	$("#exportIllegalIdCardNoList").click(function(event){
		if ($("#illegalIdCardNoList").getGridParam("records")>0){
			$("#exceldownload").attr("action",PATH+'/tbScheduleLog/downloadIllegalIdCardNo.action?idCardNoExpLog.expIdCardNo=' + $("#expIdCardNo").val());
			$("#exceldownload").submit();
		}else{
			$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
		}
	});
});
</script>