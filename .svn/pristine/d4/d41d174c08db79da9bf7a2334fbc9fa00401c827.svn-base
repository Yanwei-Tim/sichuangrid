<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.tablelist .title{width:11%;}
.tablelist .content{width:17%;}
</style>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>事项类别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ITEM_MATTER_KIND" defaultValue="${item.matterKind.id}" /></span></td>
    <td class="title"><label>事项编号</label></td>
    <td class="content" colspan="1"><span>${item.itemNumber}</span></td>
    <td class="title"><label>事项名称</label></td>
    <td class="content" colspan="3"><span>${item.itemName}</span></td>
  </tr>
  <tr>
    <td class="title"><label>承诺时限</label></td>
    <td class="content" width=""><span>${item.promiseTime}</span></td>
    <td class="title"><label>法定时限</label></td>
    <td class="content"><span>${item.legalTime}</span></td>
    <td class="title"><label>是否收费项目</label></td>
    <td class="content"><span><s:if test="item.Fees==true">是</s:if><s:else>否</s:else></span></td>
    <td class="title"><label>收费标准</label></td>
    <td class="content" ><span>${item.standardFees }</span></td>
   </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="7" ><div style="height:55px;overflow-y:auto;"><span>${item.remark}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>法定依据</label></td>
    <td class="content" colspan="7" ><div style="height:55px;overflow-y:auto;"><span>${item.legalBasis}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>办事指南</label></td>
    <td class="content" colspan="7" ><div style="height:50px;overflow-y:auto;"><span>${item.lawGuide}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>附件</label></td>
    <td class="content" colspan="7" >
	    <div style="height:60px;overflow-y:auto;">
			<c:forEach items="${itemAttachmentList}" var="itemAttachment">
				<a href="${path }/approval/itemManage/downLoadActualFile.action?itemAttachmentId=${id}"><img  src="${resource_path}/resource/images/fujian.jpg"/><span>${itemAttachment.fileName}</span></a>;
			</c:forEach>
		</div>
	</td>
  </tr>
</table>

<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>申请人名称</label></td>
    <td class="content"><span>${approvalItem.name}</span></td>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${approvalItem.idCardNo}</span></td>
   
  </tr>
  <tr>
   <td class="title"><label>移动电话</label></td>
    <td class="content"><span>${approvalItem.mobileNumber}</span></td>
    <td class="title"><label>常住地址</label></td>
    <td class="content"><span>${approvalItem.currentAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="3" ><div style="height:55px;overflow-y:auto;"><span>${approvalItem.remark}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>附件</label></td>
    <td class="content" colspan="3" >
	    <div style="height:60px;overflow-y:auto;">
			<c:forEach items="${approvalItemFileList }" var="approvalItemFile">
				<a href="${path }/approval/itemManage/downLoadActualFile.action?itemAttachmentId=${id}"><img  src="${resource_path}/resource/images/fujian.jpg"/><span>${approvalItemFile.fileName }</span></a><br>
			</c:forEach>
		</div>
	</td>
  </tr>
</table>
