<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.tablelist .title{width:6%;}
	.issueTableList{border-top:1px solid #ccc;border-right: 1px solid #ccc;width:100%;}
	.issueTableList td{background:#EFEFF0;height:18px; padding:0 0 0 10px;border:1px solid #ccc;border-top:none;border-right:none;border-collapse:collapse;line-height:20px; word-break:break-all; word-warp:break-word;}
	.issueTableList .issueTitle{width:15%;font:bold 12px/20px "microsoft yahei"; color:#000; padding:0 0 0 5px;}
	.issueTableList .text{width:25%;}
	.issueTableList .issueContable{width:auto;}
</style>
<input type="hidden" id="mode" name="mode" value="${mode}"/>
<input type="hidden" id="dailyDirectoryId" name="workingRecord.dailyDirectory.id" <s:if test='"add".equals(mode)'>value="${dailyDirectory.id}"</s:if><s:if test='"edit".equals(mode)'>value="${workingRecord.dailyDirectory.id}"</s:if> />
<input type="hidden" id="workingRecordId" name="workingRecord.id" value="${workingRecord.id}"/>
<div class="grid_20" style="display: none;">
	<select name="workingRecord.dailyLogType.id" id="workingRecrodTypeHidden" class='form-txt {required:true,messages:{required:"请选择类型"}}' tabindex="1">
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE" defaultValue="${workingRecord.dailyLogType.id}"/>
	</select>
</div>

<table id="table1" style="display: none;" class="tablelist">
	<tr>
		<td class="title"><label>台账类型：</label></td>
		<td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE" defaultValue="${workingRecord.dailyLogType.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>会议名称：</label></td>
		<td class="content" colspan="3">${workingRecord.name}</td>
	</tr>
	<tr>
		<td class="title"><label>会议时间：</label></td>
		<td class="content" colspan="3"><s:date name="workingRecord.dealDate" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td class="title"><label>会议地点：</label></td>
		<td class="content" colspan="3">${workingRecord.proceedSite}</td>
	</tr>
	<tr>
		<td class="title"><label>参与人员：</label></td>
		<td class="content" colspan="3">${workingRecord.participant}</td>
	</tr>
	<tr>
		<td class="title"><label>会议主题：</label></td>
		<td class="content" colspan="3">${workingRecord.subject}</td>
	</tr>
	<tr>
		<td class="title"><label>会议内容：</label></td>
		<td class="content" colspan="3">${workingRecord.content}</td>
	</tr>
	<tr>
		<td class="title"><label>附件：</label></td>
		<td class="content" colspan="3"><div align="left" id='${workingRecord.id}' class='showAttach'></div></td>
	</tr>
</table>

<table id="table2" style="display: none;" class="tablelist">
	<tr>
		<td class="title"><label>台账类型：</label></td>
		<td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE" defaultValue="${workingRecord.dailyLogType.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>文件名称：</label></td>
		<td class="content" colspan="3">${workingRecord.name}</td>
	</tr>
	<tr>
		<td class="title"><label>发文时间：</label></td>
		<td class="content" colspan="3"><s:date name="workingRecord.dealDate" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td class="title"><label>发文单位：</label></td>
		<td class="content" colspan="3">${workingRecord.workingUnit}</td>
	</tr>
	<tr>
		<td class="title"><label>文件号：</label></td>
		<td class="content" colspan="3">${workingRecord.fileNo}</td>
	</tr>
	<tr>
		<td class="title"><label>文件主题：</label></td>
		<td class="content" colspan="3">${workingRecord.subject}</td>
	</tr>
	<tr>
		<td class="title"><label>文件内容：</label></td>
		<td class="content" colspan="3">${workingRecord.content}</td>
	</tr>
	<tr>
		<td class="title"><label>附件：</label></td>
		<td class="content" colspan="3"><div align="left" id='${workingRecord.id}' class='showAttach'></div></td>
	</tr>
</table>

<table id="table3" style="display: none;" class="tablelist">
	<tr>
		<td class="title"><label>台账类型：</label></td>
		<td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE" defaultValue="${workingRecord.dailyLogType.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>活动名称：</label></td>
		<td class="content" colspan="3">${workingRecord.name}</td>
	</tr>
	<tr>
		<td class="title"><label>活动时间：</label></td>
		<td class="content" colspan="3"><s:date name="workingRecord.dealDate" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td class="title"><label>活动地点：</label></td>
		<td class="content" colspan="3">${workingRecord.proceedSite}</td>
	</tr>
	<tr>
		<td class="title"><label>参与人员：</label></td>
		<td class="content" colspan="3">${workingRecord.participant}</td>
	</tr>
	<tr>
		<td class="title"><label>参与单位：</label></td>
		<td class="content" colspan="3">${workingRecord.workingUnit}</td>
	</tr>
	<tr>
		<td class="title"><label>活动主题：</label></td>
		<td class="content" colspan="3">${workingRecord.subject}</td>
	</tr>
	<tr>
		<td class="title"><label>活动内容：</label></td>
		<td class="content" colspan="3">${workingRecord.content}</td>
	</tr>
	<tr>
		<td class="title"><label>附件：</label></td>
		<td class="content" colspan="3"><div align="left" id='${workingRecord.id}' class='showAttach'></div></td>
	</tr>
</table>

<table id="table4" style="display: none;" class="tablelist">
	<tr>
		<td class="title"><label>台账类型：</label></td>
		<td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE" defaultValue="${workingRecord.dailyLogType.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>名称：</label></td>
		<td class="content" colspan="3">${workingRecord.name}</td>
	</tr>
	<tr>
		<td class="title"><label>内容：</label></td>
		<td class="content" colspan="3">${workingRecord.content}</td>
	</tr>
	<tr>
		<td class="title"><label>附件：</label></td>
		<td class="content" colspan="3"><div align="left" id='${workingRecord.id}' class='showAttach'></div></td>
	</tr>
</table>

<table id="table5" style="display: none;" class="tablelist">
	<tr>
		<td class="title"><label>台账类型：</label></td>
		<td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE" defaultValue="${workingRecord.dailyLogType.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>名称：</label></td>
		<td class="content" colspan="3">${workingRecord.name}</td>
	</tr>
	<tr>
		<td class="title"><label>内容：</label></td>
		<td class="content" colspan="3">${workingRecord.content}</td>
	</tr>
	<tr>
		<td class="title"><label>附件：</label></td>
		<td class="content" colspan="3"><div align="left" id='${workingRecord.id}' class='showAttach'></div></td>
	</tr>
</table>
<script type="text/javascript">
$(document).ready(function(){
	var index=$("#workingRecrodTypeHidden").get(0).selectedIndex; 
	$("#table"+index).show();
	showAttachFun();
	function showAttachFun(){
		$.ajax({
			url:"${path}/workingRecord/workingRecordManage/getWorkingRecordById.action?workingRecord.id="+$(".showAttach")[0].id,
			success:function(data){
				var popMoreData = [];
				var content = "";
				if(data != null){
					for(var j = 0; j < data.dailyLogAttachFile.length; j++){
						popMoreData[j]={
							id:data.dailyLogAttachFile[j].id, 
							url:'${path}/workingRecord/workingRecordManage/downloadDailyLogAttachFile.action?dailyLogAttachFile.id='+data.dailyLogAttachFile[j].id, 
							text:data.dailyLogAttachFile[j].fileName,
							clickFun:function(){}
						};
						content+="<a id="+popMoreData[j].id+" href="+popMoreData[j].url+">"+popMoreData[j].text+"</a><br/>";
					}
					$(".showAttach").append(content);
				}
			}
		});
	}
});
</script>