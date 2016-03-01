<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
	<table class="tablelist">
	  <tr>
	    <td colspan="4" style="text-align:center">基本信息</td>
	  </tr>
	  <tr>
	    <td class="title">用户名</td>
	    <td class="content">${user.userName}</td>
	    <td class="title">姓名</td>
	    <td class="content">${user.name}</td>
	  </tr>
	  <tr>
	    <td class="title">手机</td>
	    <td class="content">${user.mobile}</td>
	    <td class="title">工作电话</td>
	    <td class="content">${user.workPhone}</td>
	  </tr>
	  <tr>
	    <td class="title">住宅电话</td>
	    <td class="content">&nbsp;${user.homePhone}</td>
	    <td class="title">工作单位</td>
	    <td class="content">&nbsp;${user.workCompany}</td>
	  </tr>
	  <tr>
	    <td class="title">所属组织机构</td>
	    <td colspan="3" class="content">${user.organization.orgName}</td>
	  </tr>
	  <tr>
	    <td class="title">IMSI码</td>
	    <td colspan="3" class="content">${user.imsi}</td>
	  </tr>
	  <tr></tr>
	  <tr>
	    <td colspan="4" style="text-align:center">系统信息</td>
	  </tr>
	  <tr>
	    <td class="title">所在岗位</td>
	    <td colspan="3" class="content">&nbsp;${userRole}</td>
	  </tr>
	  <tr>
	    <td class="title">负责部门</td>
	    <td colspan="3" class="content">&nbsp;${userDept}</td>
	  </tr>
	  <tr>
	    <td class="title">登陆时必须修改密码</td>
	    <td class="content"><s:if test="user.changePassword">是</s:if><s:else>否</s:else></td>
	    <td class="title">登录时是否连接vpdn</td>
	    <td class="content"><s:if test="user.connectVpdn">是</s:if><s:else>否</s:else></td>
      </tr>
	  <tr>	    
	    <td class="title">是否pc可登录</td>
	    <td class="content"><s:if test="user.pcusable">是</s:if><s:else>否</s:else></td>
	    <td class="title">是否手机可登录</td>
	    <td class="content"><s:if test="user.mobileusable">是</s:if><s:else>否</s:else></td>
	  </tr>
	  <tr>
	    <td class="title">是否为系统管理员</td>
	    <td class="content"><s:if test="user.admin">是</s:if><s:else>否</s:else></td>
	    <td class="title">是否验证IMSI码</td>
	    <td class="content"><s:if test="user.validatorImsi">是</s:if><s:else>否</s:else></td>
	  </tr>
	   <tr>
	    <td class="title">是否四级体系建设账号</td>
	    <td class="content"><s:if test="user.fourthAccount">是</s:if><s:else>否</s:else></td>
	    <td class="title">是否GPS定位</td>
	    <td class="content"><s:if test="user.gps">是</s:if><s:else>否</s:else></td>
	  </tr>
	   <tr>
	    <td class="title">是否四支队伍</td>
	    <td class="content" colspan="3"><s:if test="user.fourTeams">是</s:if><s:else>否</s:else></td>
	   </tr>
	</table>
