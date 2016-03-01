<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="jqsubgrid" style="position:relative;overflow:hidden;zoom:1;height:auto;">
	<div style="width: 100%;">
		<table id="serviceRecordList"> </table>
		<div id="serviceRecordListPager"></div>
	</div>
</div>
<script type="text/javascript">
function formatterAttachFile(el,options,rowData){
	if(rowData.attachments.length>0){
	$("#serviceRecordList").data( "'"+rowData.id+"'", rowData.attachments);
		return "<div style='clear:both' align='center'><a href='javascript:;' id='serviceRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}
$(document).ready(function(){
	$("#serviceRecordList").jqGridFunction({
		url:'${path}/baseinfo/searchServiceRecordManage/searchServiceRecords.action',
		postData:{
			"searchServiceRecordVo.teamId":'${searchServiceRecordVo.teamId}'
		},
		datatype: "json",
		height:360,
		colNames:['id','发生时间','地点','服务人员啊啊啊啊啊','服务对象','内容','附件','创建时间'],
	   	colModel:[
	        {name:'id',index:'id',hidden:true},
	        {name:'occurDate',sortable:true,width:60},
	        {name:'occurPlace',sortable:false, width:120},
	        {name:'serviceMembers',sortable:true, width:160},
	        {name:'serviceObjects',sortable:true, width:280},
	   		{name:'serviceContent',sortable:false, width:130},
      		{name:'attachments',sortable:false, width:130, formatter:formatterAttachFile},
      		{name:'createDate',sortable:false, width:120}
		],
		scrollrow:true,
		loadComplete: afterLoad
	});
	
	function afterLoad() {
		$.each($(".popUpMore"), function(i, n){
			$.ajax({
				url:"${path}/baseinfo/serviceRecordManage/listServiceRecordAttachments.action?serviceRecord.id="+$(n).attr("serviceRecordId"),
				success:function(attachFileList){
					if(null != attachFileList){
						var popMoreData = [];
						for(var j = 0; j < attachFileList.length; j++){
							popMoreData[j]={id:attachFileList[j].id, url:'${path}/baseinfo/searchServiceRecordManage/downloadServiceRecordAttachment.action?attachmentId='+attachFileList[j].id, text:attachFileList[j].fileName,clickFun:function(){}};
						}
						$(n).popUpMore({data: popMoreData});
					}
				}
			})
		});
	}
});
</script>