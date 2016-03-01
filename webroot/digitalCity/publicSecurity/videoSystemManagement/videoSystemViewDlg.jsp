<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>


<input type="hidden" id="address"  value="${videoSystem.address}" />
<input type="hidden" id="urlPath"  value="${videoSystem.url}" />
<input type="hidden" id="userName"  value="${videoSystem.account}" />
<input type="hidden" id="passWord"  value="${videoSystem.password}" />
<input type="hidden" id="guid"  value="${videoSystem.videoNo}" />
<table  width="200" class="tablelist">

	<tr>
    	 <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="videoSystemOrgName">${videoSystem.organization.orgName}</td>
	</tr>
	<tr>
    	<td class="title"><label>编号</label></td>
    	<td class="content"><span>${videoSystem.videoNo}</span></td>
    	<td class="title"><label>&nbsp;</label></td>
    	<td class="content"><span>&nbsp;</span></td>
	</tr>
	<tr>
    	<td class="title"><label>地址</label></td>
    	<td class="content" colspan="3" ><span>${videoSystem.address}</span></td>
	</tr>
    <tr>
    	<td class="title"><label>网络地址</label></td>
    	<td class="content" colspan="3" id="url"><span>${videoSystem.url}</span>
    		<input type="button" onclick="showVedio()" value="观看" style="width:80;"/>
    	</td>
	</tr>
	 <tr>
    	<td class="title"><label>账号</label></td>
    	<td class="content" colspan="3"><span>${videoSystem.account}</span></td>
	</tr>
	 <tr>
    	<td class="title"><label>密码</label></td>
    	<td class="content" colspan="3"><span>**********</span></td>
	</tr>
	<tr>
    	<td class="title"><label>2.5维经度</label></td>
    	<td class="content"><span>${videoSystem.openLayersMapInfo.centerLon}</span></td>
    	<td class="title"><label>2.5维纬度</label></td>
    	<td class="content"><span>${videoSystem.openLayersMapInfo.centerLat}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>二维经度</label></td>
    	<td class="content"><span>${videoSystem.openLayersMapInfo.centerLon2}</span></td>
    	<td class="title"><label>二维纬度</label></td>
    	<td class="content"><span>${videoSystem.openLayersMapInfo.centerLat2}</span></td>
	</tr>
	
	<c:if test="${videoSystem.isEmphasis }">
	  <tr>
	  	 <td class="title"><label>取消关注时间</label></td>
		 <td class="content"><span id=""><fmt:formatDate value="${videoSystem.logoutTime}" type="date" pattern="yyyy-MM-dd" /></span></td>	
		 <td class="title"><label>取消关注原因</label></td>
		 <td class="content"><span id="">${videoSystem.logoutReason}</span></td>
	  </tr>
  </c:if>

</table>
<div id="vedioShowDialog"></div>

<script type="text/javascript">
function showVedio(){
		
	    var address = $("#address").val();
		var urlPath = $("#urlPath").val();
		var userName = $("#userName").val();
		var passWord = $("#passWord").val();
		var ip = urlPath.split(":")[0];
		var portNum = urlPath.split(":")[1];
		var guid = $("#guid").val();
		$("#vedioShowDialog").createDialog({
			width: 707,
			height: 580,
			title:'视频播放',
			url:PATH+'/digitalCity/publicSecurity/videoSystemManagement/videoShow.jsp',
			postData:{
				address:address,
				ip:ip,
				portNum:portNum,
				account:userName,
				password:passWord,
				guid:guid
				},
			buttons: {
	   			"关闭" : function(){
	        		$(this).dialog("close");
	   			}
			},
		});
	}
</script>
