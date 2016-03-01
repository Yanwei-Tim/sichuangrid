<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	response.addHeader("Content-Disposition", "attachment;filename="+new String("移交单".getBytes("gbk"),"ISO8859-1") + ".doc");
	response.addHeader("Content-Type","application/vnd.ms-word");
%>
<html>
	<head>
		<title>温州市龙湾综治网格化信息管理系统 ||移交单</title>
	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
		  <tr>
		    <td valign="top">
		    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		       		<tr>
			          	<td style="text-align: left;font-Size:16px font-family:'黑体';	height:20px;width:15%;font-weight:800;" colspan="6" align="left">单号：<s:property value="issue.serialNumber"/></td>
		       		</tr>
		        	<tr>
			          	<td colspan="6" style="text-align: center;font-Size:20px ;font-family:'创艺简标宋';height:20px;width:15%;	font-weight:800;">
			          	<c:if test="${source=='completed'}">
	                  	  网格化服务管理信息系统任务单
	                    </c:if>  
	                    <c:if test="${source=='need'}">
	                  	  网格化服务管理信息系统任务移交单
	                    </c:if>  
			          	<br><br></td>
		        	</tr>
		        	<tr>
		        		<td width="100%" colspan="6" ><b>事件名称：</b><s:property value="issue.subject"/></td>
		        	</tr>
		        	<tr>
			          	<td width="100%" colspan="6" align="center"><hr/></td>
		        	</tr>
		        	<c:if test="${source=='need'}">
		        	<tr>
			          	<td width="12%" style="text-align: right;font-Size:16px	font-family:'黑体';height:20px;width:15%;;">签收单位：</td>
			          	<td width="18%"></td>
			          	<td width="12%" style="text-align: right;font-Size:16px	font-family:'黑体';height:20px;width:15%;font-weight:800;">签收人：</td>
			          	<td width="20%"></td>
			          	<td width="16%" style="text-align: right;font-Size:16px	font-family:'黑体';height:20px;width:15%;font-weight:800;">联系电话：</td>
			          	<td width="15%"></td>
		        	</tr>
		        	<tr>
		        		<td colspan="4"></td>
		        		<td style="text-align: right;font-Size:16px	font-family:'黑体';height:20px;width:15%;font-weight:800;">年&nbsp;&nbsp;月&nbsp;&nbsp;日</td>
		        	</tr>
		        	<tr>
			          	<td width="100%" colspan="6" align="center"><hr style="border:1px dashed black; height:1px"/></td>
		        	</tr>
		        	<tr>
			          	<td colspan="4" style="text-align: left;font-Size:16px font-family:'黑体';	height:20px;width:15%;font-weight:800;" >单号：<s:property value="issue.serialNumber"/></td>
			          	<td ></td>
			          	<td ></td>
		       		</tr>
		       		<tr>
			          	<td colspan="6" style="text-align: center;font-Size:19px ;font-family:'创艺简标宋';height:20px;width:15%;font-weight:800;">网格化服务管理信息系统任务移交单</td>
		        	</tr>
		        	</c:if> 
		        	<c:if test="${source=='completed'}">
		        		<tr>
			          	<td width="12%" class="tdhr"></td>
			          	<td width="18%"></td>
			          	<td width="12%" class="tdhr"></td>
			          	<td width="20%"></td>
			          	<td width="16%" class="tdhr"></td>
			          	<td width="15%"></td>
		        	</tr>
		            </c:if>  
		       		<tr>
			          	<td width="12%" style="text-align: right;font-Size:16px	font-family:'黑体';height:20px;width:15%;font-weight:800;">派单单位：</td>
			          	<td width="5%" colspan="4"><s:property value="organization.orgName"/></td>

			          	<td width="60%" style="text-align: left;font-Size:16px font-family:'黑体';	height:20px;width:15%;font-weight:800;"  >年&nbsp;&nbsp;月&nbsp;&nbsp;日印</td>
		       		</tr>
		        </table>
		        <table width="100%" border="0" cellpadding="10" cellspacing="0" style="border:1px solid #000000;border-width:1px 1px 1px 1px;margin:0 0 0 0;text-align:center;border-collapse:collapse;width:100%;">
		        	<tr class="tr1">
		        		<td height="30" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" width="8%">主  题</td>
		        		<td colspan="5"  style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"><s:property value="issue.subject"/></td>
		        	</tr>
		        	<tr class="tr1">
		        		<td width="12%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" >类别</td>
			            <td width="18%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">
			            	<!--<c:forEach items="${selContradiction}" var="type">
		                        ${type.issueTypeName},
		                    </c:forEach>
		                    <c:forEach items="${selSpecialisation}" var="type">
		                    	${type.issueTypeName},
		                    </c:forEach>
		                    <c:forEach items="${selPeopleliveService}" var="type">
		                    	${type.issueTypeName},
		                    </c:forEach>-->
		                    ${issue.issueType.issueTypeDomain.domainName}:${issue.issueType.issueTypeName}
			            </td>
			            <td width="12%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">承办单位</td>
			            <td width="60%" colspan="3" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"></td>
		        	</tr>
		        	<tr class="tr1">
		        		<td width="10%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" >信息来源</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">${user.name }</td>
			            <td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">联系电话</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">${user.mobile }</td>
			            <td width="10%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">来源方式</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">
			            	<pop:PropertyDictViewTag defaultValue="${issue.sourceKind.id }" name="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND"></pop:PropertyDictViewTag>
			            </td>
		        	</tr>
		        	<tr class="tr1">
		        		<td height="30" align="left" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" width="8%">当事人</td>
		        		<td colspan="5" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">
		        			<s:property value="issue.mainCharacters"/>
		        		</td>
		        	</tr>
		        	<tr class="tr1">
		        		<td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">人员类别</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">&nbsp;</td>
			            <td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">涉及人数</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"><s:property value="issue.relatePeopleCount"/></td>
			            <td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">发生日期</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"><s:date name="issue.occurDate" format="yyyy年MM月dd日" /></td>
		        	</tr>
		        	<tr class="tr1">
		        		<td height="30" align="left" width="8%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">发生网格</td>
		        		<td colspan="5" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"><s:property value="issue.occurOrg.orgName"/></td>
		        	</tr>
		        	<tr class="tr1">
		        		<td height="80" style="text-align: center;font-Size:16pxfont-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" width="8%"><p></p><p>内</p><p>容</p><p></p></td>
		        		<td colspan="5" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">
			            	<s:property value="issue.issueContent" />
			            </td>
		        	</tr>
		        	<tr class="tr1">
		        		<td height="80" style="text-align: center;font-Size:16pxfont-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" width="8%"><p>领</p><p>导</p><p>意</p><p>见</p></td>
		        		<td colspan="5" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">&nbsp;
		        		<s:iterator status="index" value="issueLogs">
        				<c:if test="${dealType =='51' }">
	        				<div class="grid_24 heightAuto" style="width: 100%">
		                         <c:if test="${dealOrg.orgName =='中国' || dealUserName=='admin' || dealUserName =='超级管理员'  }">
			                        <b>系统消息</b>&nbsp;
		                         </c:if>
		                          <c:if test="${dealOrg.orgName !='中国' && dealUserName!='admin' && dealUserName !='超级管理员'  }">
		                          ${dealOrg.orgName }&nbsp;
			                        <b>${dealUserName }</b>&nbsp;
		                         </c:if>
		                        	联系手机：<s:property value="mobile"/>&nbsp;于&nbsp;
		                        <s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" />
		                        <s:property value="dealDescription"/><c:if test="${content != null && not empty content}">&nbsp;&nbsp;办理意见:</c:if><br/>
		                       	<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
			                        <div class="grid_24 grid-message heightAuto" align="left">
			                            <span>${content }</span>
			                        </div>
		                    	</c:if>
	        				</div>
        				</c:if>
                    </s:iterator>
		        		</td>
		        	</tr>
		        	<tr class="tr1">
		        		<td height="80" style="text-align: center;font-Size:16pxfont-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" width="8%"><p>处</p><p>理</p><p>记</p><p>录</p></td>
		        		<td colspan="5" valign="top" align="left">
		        			<s:iterator status="index" value="issueLogs">
		        			<s:property value="#dealType"/>
		        				<c:if test="${dealType !='51' }">
			        				<div class="grid_24 heightAuto" style="width: 100%">
			        					
				                         <c:if test="${dealOrg.orgName =='中国' || dealUserName=='admin' || dealUserName =='超级管理员'  }">
					                        <b>【系统消息】</b>&nbsp;
				                         </c:if>
				                          <c:if test="${dealOrg.orgName !='中国' && dealUserName!='admin' && dealUserName !='超级管理员'  }">
				                                                                          【${dealOrg.orgName }】&nbsp;
					                        <b>${dealUserName }</b>&nbsp;
				                        	联系手机：<s:property value="mobile"/>&nbsp;于&nbsp;
				                        <s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" />
				                         </c:if>
				                        <s:property value="dealDescription"/><c:if test="${content != null && not empty content}">&nbsp;&nbsp;办理意见:</c:if><br/>
				                       	<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
					                        <div class="grid_24 grid-message heightAuto" align="left">
					                            <span>${content }</span>
					                        </div>
				                    	</c:if>
			        				</div>
		        				</c:if>
		                    </s:iterator>
		        		</td>
		        	</tr>
		    </table>
		    <p><span style="font-Size:12px">注：承办单位处理完毕后，留复印件，将原件交派单单位</span></p>
		</table>
	</body>
</html>