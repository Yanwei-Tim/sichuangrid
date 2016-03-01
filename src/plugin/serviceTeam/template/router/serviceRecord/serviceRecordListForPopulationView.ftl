<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceRecord</@s.param>
</@s.include>
<div class="jqsubgrid" style="position:relative;overflow:hidden;zoom:1;height:auto;">
	<div style="width: 100%;">
		<table id="serviceRecordList1"> </table>
		<div id="serviceRecordListPager"></div>
	</div>
</div>
<input type="hidden" id="object_Type" value="<#if populationType??>${(populationType)!}<#else>${(module)!}</#if>"/>

<script type="text/javascript">

$(document).ready(function(){
	$("#serviceRecordList1").jqGridFunction({
		url:'${path}/plugin/serviceTeam/serviceRecord/findServiceRecords.action?objectIds=${(id)!}',
		postData:{
			"serviceRecordVo.organization.id":USER_ORG_ID,
			"populationType":$("#object_Type").val()
		},
		datatype: "json",
		height:350,
		colNames:['id',<@s.if test='pollution'>'巡查时间','巡查地点',</@s.if><@s.if test='!pollution'>'服务时间','服务地点',</@s.if>'记录所属人','内容','附件','创建时间'],
	   	colModel:[
	        {name:'id',index:'id',hidden:true},
	        {name:'occurDate',sortable:true,width:120},
	        {name:'occurPlace',sortable:false, width:120},
	        {name:'serviceMembers',sortable:true, width:180},
	   		{name:'serviceContent',sortable:false, width:200},
      		{name:'attachments',sortable:false, width:140, formatter:formatterAttachFile},
      		{name:'createDate',sortable:false,hidden:true, width:120}
		],
		scrollrow:true,
		loadComplete: afterLoad,
	    showColModelButton:false
	});
	
	
	//加载完时执行的方法
	function afterLoad() {
		loadFiles();
	}
	//加载文件
	function loadFiles() {
		$.each($(".popUpMore"), function(i, n){
			var popMoreData = [];
			$.each($("#serviceRecordList1").data($(n).attr("serviceRecordId")),function(ind, fn){
				popMoreData[ind]={id:fn.id, url:'${path}/plugin/serviceTeam/serviceRecord/downloadServiceRecordAttachment.action?attachmentId='+fn.id, text:fn.fileName,clickFun:function(){}};
			});
			$(n).popUpMore({data: popMoreData});
		});
	}
	function formatterAttachFile(el,options,rowData){
		if(rowData.serviceRecordAttachments.length>0){
			$("#serviceRecordList1").data(String(rowData.id), rowData.serviceRecordAttachments);
			return "<div style='clear:both' align='center'><a href='javascript:;' id='serviceRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.id+"' >附件>></a></div>";
		}
		return "";
	}
});
</script>