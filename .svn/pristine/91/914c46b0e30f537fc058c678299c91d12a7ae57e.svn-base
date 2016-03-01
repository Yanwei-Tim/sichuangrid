<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table  class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${regionalBuildInfo.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>服务项目名称</label></td>
    <td colspan="3" class="content" id="serviceItem">${regionalBuildInfo.serviceItem}</td>
  </tr>
   <tr>
    <td class="title"><label>推进情况</label></td>
    <td colspan="3" class="content" id="serviceItem">${regionalBuildInfo.advancementInfo}</td>
  </tr>
  <tr>
    <td class="title"><label>附件</label></td>
    <td class="content" colspan="3">
    <select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
    <div id="custom-queue" style="width:96%;height:65px;overflow:auto;overflow-x: hidden;"></div>
    </td>
  </tr>
  </table>

<script type="text/javascript">
$(document).ready(function(){

	fillFile();

});
 function fillFile(){
	<c:if test="${regionalBuildInfo.regionalBuildInfoAttachFiles!=null && fn:length(regionalBuildInfo.regionalBuildInfoAttachFiles) > 0}">
        <s:iterator value="regionalBuildInfo.regionalBuildInfoAttachFiles">
        $("#custom-queue").addUploadFileValue({
	          id:"<s:property value='id'/>",
	          filename:"<s:property value='fileName'/>",
	          link:"${path}/partyBuilding/regionalBuildInfoManage/downloadRegionalBuildInfoAttachFiles.action?regionalBuildInfoAttachFile.id=<s:property value='id'/>",
	          showCloseButton:false	  
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
        </s:iterator>
	</c:if> 
}
</script>