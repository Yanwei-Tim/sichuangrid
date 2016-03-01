<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${partyConstructionFiles.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>标题：</label></td>
    <td class="content"  colspan="3"><span>${partyConstructionFiles.title}</span></td>
  </tr>
 <tr>
    <td class="title"><label>大类：</label></td>
    <td class="content"><span>${partyConstructionFiles.dalei}</span></td>
    <td class="title"><label>小类：</label></td>
    <td class="content"><span>${partyConstructionFiles.xiaolei}</span></td>
  </tr>
  <tr>
    <td class="title"><label>附件描述：</label></td>
    <td class="content" colspan="3"><span>${partyConstructionFiles.describe}</span></td>
  </tr>
  
   <tr>
    <td class="title"><label>附件名称：</label></td>
    <td class="content"  colspan="3"><span>${partyConstructionFiles.fileName}</span>
    <span style="" class="lable-right"><a href="javascript:void(0)" id="downLoad" ><B> 下载</B></a></span></td>
  </tr>
</table>
<script type="text/javascript">

$(document).ready(function(){
	$("#downLoad").click(function(){
		window.open("${path }/baseinfo/partyConstructionFilesManage/downloadFile.action?partyConstructionFiles.id="+${partyConstructionFiles.id});
		
	}); 
	
	
});

</script>