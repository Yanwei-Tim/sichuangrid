<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="tabList">
	<ul>
		<li><a  href="javascript:;" id="infoTab">信息</a> </li>
		<li><a  href="javascript:;" id="taskListReplyTab">回复列表</a> </li>
	</ul>
</div>
<table width="200" class="tablelist" id="infoList">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="dangerousChemicalsUnitOrgName">${hiddenDanger.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>地点</label></td>
    <td class="content" colspan="3">${hiddenDanger.address}</td>
  </tr>

  <tr>
    <td class="title"><label>发现时间</label></td>
    <td class="content"><s:date name="hiddenDanger.discoverDate" format="yyyy-MM-dd HH:mm:ss" /></td>
    <td class="title"><label>异常类型</label></td>
    <td class="content" id="exceptionType"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE" defaultValue="${hiddenDanger.exceptionType.id}" /></td>
   </tr>
 
  
  <tr>
  	<td class="title"><label>异常情况</label></td>
    <td class="content" colspan="3">${(hiddenDanger.exceptionSituation)}</td>
  </tr>
  
  <tr>
    <td class="title"><label>网格员手机</label></td>
    <td colspan="3" class="content" id="hiddenDangerOrgName">${(hiddenDanger.telephone)}</td>
  </tr>
  
   <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="3">${(hiddenDanger.remark)}</td>
  </tr>
   <tr>
   <td class="title"><label>签收人姓名</label></td>
    <td class="content" colspan="3">${(hiddenDanger.signUserName)}</td>
  </tr>
   <tr>
    <td class="title"><label>签收意见</label></td>
    <td class="content" colspan="3">${(hiddenDanger.advice)}</td>
  </tr>
  
   <tr>
    <td class="title"><label>签收时间</label></td>
    <td class="content" colspan="3"><s:date name="hiddenDanger.signDate" format="yyyy-MM-dd HH:mm:ss" /></td>
   </tr>
   
   <tr>
		<c:if test="${hiddenDanger.hiddenDangerAnnexFiles!=null && fn:length(hiddenDanger.hiddenDangerAnnexFiles) > 0}">
						<p>
							<td class="title"><div class="grid-accessory"><span class="filetype-clip"></span>附件</div></td>
							<td class="contable" colspan="3">	
							<div id="gallery">
								<c:forEach items="${hiddenDanger.hiddenDangerAnnexFiles}" var="hiddenDangerAttachFile">
									<c:choose>
									    <c:when test="${fn:endsWith(fn:toLowerCase(hiddenDangerAttachFile.physicsFullFileName),'.jpg')||fn:endsWith(fn:toLowerCase(hiddenDangerAttachFile.physicsFullFileName),'.jpge')||fn:endsWith(fn:toLowerCase(hiddenDangerAttachFile.physicsFullFileName),'.png')||fn:endsWith(fn:toLowerCase(hiddenDangerAttachFile.physicsFullFileName),'.gif')}">
									     	<a data-gallery="gallery" style="color: red" class="view" target="_Blank" href="${hiddenDangerAttachFile.physicsFullFileName }" title="${hiddenDangerAttachFile.fileName}">${hiddenDangerAttachFile.fileName}</a>;
			                        		<div class="clear"></div>
									    </c:when>
									    <c:otherwise>
									    	<a href="${path }/baseInfo/hiddenDangerManage/downLoadAttachFile.action?keyId=${hiddenDangerAttachFile.id}" style="color: red" >${hiddenDangerAttachFile.fileName}</a>;
			                        		<div class="clear"></div>
									    </c:otherwise>
									</c:choose>
		                        </c:forEach>
		                   <a href="javascript:;" class="viewImage" id="viewImages"></a>
					</div>
					</td>
				</p>
			</c:if>
	</tr>

   </table>
<div id="showTaskListReply"></div>
<script type="text/javascript">
$(function(){
	$( "#tabList" ).tabs({ selected: 0});
	$("#infoTab").click(function(){
		$("#infoList").show();
		$("#showTaskListReply").hide();
	});
	$("#taskListReplyTab").click(function(){
		$("#infoList").hide();
		$("#showTaskListReply").show();
		if($("#showTaskListReply").html()==""){
			$.get(PATH+"/plugin/taskListManage/common/taskListReplyListDlg.action",{'taskListReply.moduleKey':'reply_hiddenDanger','taskListReply.taskId':'${hiddenDanger.id}'},function(data){
				$("#showTaskListReply").html(data);
			});
		}
	});
})
</script>


