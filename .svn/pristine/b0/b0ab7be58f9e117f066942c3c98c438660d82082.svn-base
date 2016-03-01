<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table class="tablelist">
  <tr>
    <td class="title"><label>名称</label></td>
    <td colspan="3" class="content" >${myProfile.name}</td>
    
  </tr>
  <tr>
    <td class="title"><label>撰文时间</label></td>
    <td class="content" ><span><s:date name="myProfile.releaseTime" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>作者</label></td>
    <td  class="content">${myProfile.releaseUnit}</td>
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

