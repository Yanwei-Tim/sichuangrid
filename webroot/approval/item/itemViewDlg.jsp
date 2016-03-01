<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>事项编号</label></td>
    <td class="content"><span>${item.itemNumber}</span></td>
    <td class="title"><label>事项名称</label></td>
    <td class="content"><span>${item.itemName}</span></td>
  </tr>
  <tr>
    <td class="title"><label>承诺时限</label></td>
    <td class="content"><span>${item.promiseTime}</span></td>
    <td class="title"><label>法定时限</label></td>
   <td class="content"><span>${item.legalTime}</span></td>
  </tr>
  <tr>
    <td class="title"><label>事项类别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ITEM_MATTER_KIND" defaultValue="${item.matterKind.id}" /></span></td>
    <td class="title"><label>是否收费项目</label></td>
    <td class="content"><span><s:if test="item.Fees==true">是</s:if><s:else>否</s:else></span></td>
  </tr>
  <tr>
    <td class="title"><label>收费标准</label></td>
    <td class="content" colspan="4" ><span>${item.standardFees }</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="4" >${item.remark}</td>
  </tr>
  <tr>
    <td class="title"><label>法定依据</label></td>
    <td class="content" colspan="4" ><div style="height:100px;overflow-y:auto;"><span>${item.legalBasis}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>办事指南</label></td>
    <td class="content" colspan="4" ><div style="height:100px;overflow-y:auto;"><span>${item.lawGuide}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>附件</label></td>
    <td class="content" colspan="4" >
	    <div style="height:100px;overflow-y:auto;">
			<c:forEach items="${itemAttachmentList}" var="itemAttachment">
			  <a href="${path}/approval/itemManage/downLoadActualFile.action?itemAttachmentId=${id}"><img  src="${resource_path}/resource/images/fujian.jpg"/><span>${itemAttachment.fileName}</span></a><br>
			</c:forEach>
		</div>
	</td>
  </tr>
  
</table>
