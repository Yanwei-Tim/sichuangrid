<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style type="text/css">
.imageC pre img{width:200px;height:200px;}

</style>
<table  class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${peopleLog.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>日志所属人</label></td>
    <td class="content"><span>${peopleLog.belonger}</span></td>
    <td class="title"><label>时间</label></td>
    <td class="content"><span><s:date name="peopleLog.publishDate" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>地点</label></td>
    <td class="content"><span>${peopleLog.address}</span></td>
    <td class="title"><label>标题</label></td>
    <td class="content">${peopleLog.title}</td>
  </tr>
  <tr>
    <td class="title" ><label>内容</label></td>
    <td class="content" colspan="3" style="padding-top: 8px; padding-bottom: 5px;line-height:45px;">
    	<span class="imageC">${peopleLog.contents}</span>
    </td>
  </tr>
  <tr>
    <td class="title"><label>总结</label></td>
    <td class="content" colspan="3">${peopleLog.summary}</td>
  </tr>
  <tr>
    <td class="title"><label>附件</label></td>
    <td class="content" colspan="3">
    <select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
    <div id="custom-queue" style="width:96%;height:65px;overflow:auto;overflow-x: hidden;"></div>
    </td>
  </tr>
</table>

<s:iterator value="peopleLog.comments" var="obj">
<table class="tablelist">
  <tr>
     <td class="title"><label>点评时间</label></td>
	 <td class="content"><span><s:date name="#obj.commentTime" format="yyyy-MM-dd"/></span></td>
	 <td class="title"><label>点评人 </label></td>
	 <td class="content"><span>${obj.commentUser}</span></td>
  </tr>
  <tr>
    <td class="title" rowspan="2"><label>点评语</label></td>
    <td class="content" colspan="3" rowspan="2"><span>${obj.comments}</span></td>
  </tr>
</table>
</s:iterator>

<script>
$(document).ready(function(){
<s:if test="peopleLog.peopleLogFiles!=null && peopleLog.peopleLogFiles.size>0">
	<s:iterator value="peopleLog.peopleLogFiles">
    	$("#custom-queue").addUploadFileValue({
	    	id:"<s:property value='id'/>",
	        filename:"<s:property value='fileName'/>",
	        link:"${path}/peopleLog/peopleLogManage/downloadPeopleLogAttachFile.action?peopleLogAttachFile.id=<s:property value='id'/>",
	        showCloseButton:false
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
     </s:iterator>
</s:if>
});
</script>