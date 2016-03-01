<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceRecord</@s.param>
</@s.include>

		<table id="serviceRecordList"> </table>

<script type="text/javascript">

$(document).ready(function(){
	$("#serviceRecordList").jqGridFunction({
		url:'${path}/plugin/serviceTeam/serviceRecord/findServiceRecords.action?objectIds=${(id)!}&populationType=${(populationType)!}',
		postData:{
			"serviceRecordVo.organization.id":USER_ORG_ID
		},
		datatype: "json",
		colNames:['id','服务时间','服务地点地点','记录所属人','内容','创建时间'],
	   	colModel:[
	        {name:'id',index:'id',hidden:true},
	        {name:'occurDate',sortable:true,width:120},
	        {name:'occurPlace',sortable:false, width:120},
	        {name:'serviceMembers',sortable:true, width:180},
	   		{name:'serviceContent',sortable:false, width:200},
      		{name:'createDate',sortable:false,hidden:true, width:120}
		],
		scrollrow:true,
		loadComplete: afterLoad,
	    showColModelButton:false,
	    rowNum:Math.pow(2,31)-1,
	    shrinkToFit:true,
	    height:'auto'
	});
	
	
	//加载完时执行的方法
	function afterLoad() {
		loadFiles();
	}
	//加载文件
	function loadFiles() {
		$.each($(".popUpMore"), function(i, n){
			var popMoreData = [];
			$.each($("#serviceRecordList").data($(n).attr("serviceRecordId")),function(ind, fn){
				popMoreData[ind]={id:fn.id, url:'${path}/plugin/serviceTeam/serviceRecord/downloadServiceRecordAttachment.action?attachmentId='+fn.id, text:fn.fileName,clickFun:function(){}};
			});
			$(n).popUpMore({data: popMoreData});
		});
	}
	function formatterAttachFile(el,options,rowData){
		if(rowData.serviceRecordAttachments.length>0){
			$("#serviceRecordList").data(String(rowData.id), rowData.serviceRecordAttachments);
			return "<div style='clear:both' align='center'><a href='javascript:;' id='serviceRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.id+"' >附件>></a></div>";
		}
		return "";
	}
});
</script>