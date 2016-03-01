<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			          	<td colspan="6" style="text-align: center;font-Size:16px font-family:'创艺简标宋';height:20px;width:15%;	font-weight:800;">综治网格化信息平台任务移交单(回执联)<br><br></td>
		        	</tr>
		        	<tr>
		        		<td width="100%" colspan="6" ><b>事件名称：</b><s:property value="issue.subject"/></td>
		        	</tr>
		        	<tr>
			          	<td width="100%" colspan="6" align="center"><hr/></td>
		        	</tr>
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
			          	<td colspan="6" style="text-align: center;font-Size:16px font-family:'创艺简标宋';height:20px;width:15%;font-weight:800;">综治网格化信息平台任务移交单(承办联)</td>
		        	</tr>
		       		<tr>
			          	<td width="12%" style="text-align: right;font-Size:16px	font-family:'黑体';height:20px;width:15%;font-weight:800;">派发单位：</td>
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
			            	<s:iterator value="selContradiction" var="type" >
		                        <s:property value="issueTypeName"/>,
		                    </s:iterator>
		                    <s:iterator value="selSpecialisation" var="type" >
		                        <s:property value="issueTypeName"/>,
		                    </s:iterator>
		                    <s:iterator value="selPeopleliveService" var="type" >
		                        <s:property value="issueTypeName"/>,
		                    </s:iterator>
			            </td>
			            <td width="12%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">承办单位</td>
			            <td width="18%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"></td>
			            <td width="12%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">办理时限</td>
			            <td width="18%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">&nbsp;</td>
		        	</tr>
		        	<tr class="tr1">
		        		<td width="10%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" >信息来源</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"></td>
			            <td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">联系电话</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"></td>
			            <td width="10%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;">来源方式</td>
			            <td width="20%" style="text-align: center;font-Size:14px; height:20px;width:15%;border:1px solid #000000;"></td>
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
		        		</td>
		        	</tr>
		        	<tr class="tr1">
		        		<td height="80" style="text-align: center;font-Size:16pxfont-family:'黑体';height:20px;width:15%;font-weight:800;border:1px solid #000000;" width="8%"><p>处</p><p>理</p><p>记</p><p>录</p></td>
		        		<td colspan="5" valign="top" align="left">
		        			<s:iterator status="index" value="issueLogs">
		        				<div class="grid_24 heightAuto">
		        					<s:property value="dealOrg.orgName"/>&nbsp;
			                        <b><s:property value="dealUserName"/></b>&nbsp;
			                        	联系手机：<s:property value="mobile"/>&nbsp;于&nbsp;
			                        <s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" />
			                        <s:property value="dealDescription"/><s:if test="content != null && !content.equals('')"><br/>&nbsp;&nbsp;办理意见:</s:if>
			                       	<s:if test="(content != null && !content.equals('')) || (issueLogAttachFilesMap[id] != null && issueLogAttachFilesMap[id].size()>0 )">
			                        <div class="grid_24 grid-message heightAuto">
			                            <em>${content }</em>
			                        </div>
			                    	</s:if>
		        				</div>
		                    </s:iterator>
		        		</td>
		        	</tr>
		    </table>
		    <p><span style="font-Size:12px">注：承办单位处理完毕后，留复印件，将原件交派单单位</span></p>
		</table>
	</body>
</html>