<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table class="tablelist">
  <tr>
    <td class="title" style="width:6%;"><label>名称</label></td>
    <td colspan="3" class="content" id="myProfile_name">${myProfile.name}</td>
    
  </tr>
  <tr>
    <td class="title"><label>发文单位</label></td>
    <td colspan="3" class="content">${myProfile.releaseUnit}</td>
  </tr>
  <tr>
    <td class="title"><label>发文时间</label></td>
    <td class="content"><s:date name="myProfile.releaseTime" format="yyyy-MM-dd" /></td>
    <td class="title"><label>文件号</label></td>
   <td class="content">${myProfile.fileNo }</td>
  </tr>
  <tr>
    <td class="title"><label>文件主题</label></td>
    <td class="content" colspan="3">${myProfile.documentSubject}</td>
  </tr>
  <tr>
    <td class="title"><label>文件内容</label></td>
    <td class="content"  colspan="3">${myProfile.content}</td>
  </tr>
  <tr>
	 <td class="title">附件</td>
	 <td class="content" colspan="3">
		 <s:iterator value="attachFileList" var="obj">
			<a href="/resourcePool/myProfileManage/downloadMyProfileAttachFile.action?myProfileAttachFile.id=${obj.id}" >
				<img  src="${resource_path}/resource/images/fujian.jpg"/>${obj.fileName}
			</a>
		</s:iterator>
	 </td>
	</tr>
  </table>
<script>

</script>
