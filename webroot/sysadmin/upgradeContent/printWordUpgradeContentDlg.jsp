<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	response.addHeader("Content-Disposition", "attachment;filename="+new String("升级内容".getBytes("gbk"),"ISO8859-1") + ".doc");
	response.addHeader("Content-Type","application/vnd.ms-word");
%>
<html>
	<head>
		<title>四川省网格化服务管理信息系统 ||升级内容</title>
	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
		  <tr>
		    <td valign="top">
		        <table width="100%" border="0" cellpadding="10" cellspacing="0" style="border:1px solid #000000;border-width:1px 1px 1px 1px;margin:0 0 0 0;text-align:center;border-collapse:collapse;width:100%;">
		        	<tr>
			          	<td colspan="6" style="text-align: center;font-Size:20px ;font-family:'创艺简标宋';height:20px;width:15%;	font-weight:800;">
			          		四川省网格化服务管理信息系统升级内容
			          	<br><br></td>
		        	</tr>
		        	<tr class="tr1">
		        		<td height="30" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:5%;font-weight:800;border:1px solid #000000;" width="8%">升级时间 </td>
		        		<td colspan="5"  style="text-align: left;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">
		        			<s:date name='upgradeContent.upgradeDate' format='yyyy-MM-dd'/>
		        		</td>
		        	</tr>
		        	<tr class="tr1">
		        		<td height="80" style="text-align: center;font-Size:16pxfont-family:'黑体';height:20px;width:5%;font-weight:800;border:1px solid #000000;" width="8%">
		        			<p>&nbsp;</p><p>&nbsp;</p><p>升级内容</p><p>&nbsp;</p></td>
		        		<td colspan="5" style="text-align: left;font-Size:14px; height:20px;width:15%;border:1px solid #000000;">
			            	<span>${upgradeContent.upgradeContent }</span>
			            </td>
		        	</tr>
		    </table>
		    <p><span style="font-Size:12px">注：承办单位处理完毕后，留复印件，将原件交派单单位</span></p>
		</table>
	</body>
</html>