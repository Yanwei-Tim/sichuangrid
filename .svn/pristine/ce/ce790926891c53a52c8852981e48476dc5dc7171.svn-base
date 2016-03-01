<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<body>
<table width="300" class="tablelist">
<s:if test='!"noObject".equals(flag)'>
  <tr>
    <td class="title"><label>主送</label></td>
    <td class="content" colspan="3">
    <span>${resultMap.sendName }</span>
    </td>
  </tr>
    <tr>
    <td class="title"><label> 抄送</label></td>
    <td class="content" colspan="3">
        <span>${resultMap.copyName}</span>
    </td>
  </tr>
  </s:if>
  <tr>
    <td class="title"><label>文件标题</label></td>
    <td class="content"><span>${document.title}</span></td>
    <td class="title"><label>发文单位</label></td>
    <td class="content"><span>${document.dispatchUnit}</span></td>
  </tr>
  <tr>
    <td class="title"><label>文件号</label></td>
	<td class="content"><span>${document.documentNo}</span></td>
    <td class="title"><label>主题词</label></td>
	<td class="content"><span>${document.theme}</span></td>
  </tr>
  <tr>
    <td class="title"><label>机密程度</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SECRETDEGREE" defaultValue="${document.secretDegree.id}"/></span></td>		
    <td class="title"><label>紧急程度</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@URGENTDEGREE" defaultValue="${document.urgentDegree.id}"/></span></td>		
  </tr>
    <tr>
    <td class="title"><label>签收时间</label></td>
    <td class="content"><span><s:date name="document.signDate" format="yyyy-MM-dd HH:mm:ss"/></span></td>		
    <td class="title"><label>签收人</label></td>
    <td class="content"><span>${document.signer }</span></td>		
  </tr>
   <tr>		
   	 <td class="title" ><label>签收回执</label></td>
	 <td  colspan="3" class="content"><span>${document.receiptContent}</span></td>	 
  </tr> 
  <tr>		
   	 <td class="title" ><label>内容</label></td>
	 <td  colspan="3" class="content"><span>${document.contents}</span></td>	 
  </tr> 
  <tr id="approvalOpinionTr">		
   	 <td class="title" ><label>审批意见</label></td>
	 <td  colspan="3" class="content" style="margin-left:0px;"><span id="approvalOpinion" style="margin-left: -4px;">${document.approvalOpinion}</span></td>	 
  </tr> 
	<tr>
	 <td class="title">附件</td>
	 <td class="content" colspan="3">
		 <s:iterator value="docfiles" var="obj">
			<a href="/documents/dispatchDocumentsManage/downLoadDocfiles.action?fileId=${obj.fileId}" >
				<img  src="${resource_path}/resource/images/fujian.jpg"/>${obj.fileName}
			</a>
		</s:iterator>
	 </td>
	</tr>
</table>

</body>
<script>
$(document).ready(function(){
	approvalOpinion();
	$("#receiveDocumentsList").trigger("reloadGrid");
});

function approvalOpinion(){
	if($("#approvalOpinion").text()==null || $("#approvalOpinion").text()==""){
		$("#approvalOpinionTr").hide();
	}
}

</script>
