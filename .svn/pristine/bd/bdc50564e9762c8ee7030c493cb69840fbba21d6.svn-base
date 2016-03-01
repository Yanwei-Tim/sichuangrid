<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table  border="1" class="tablelist">

	<tr>
    	<td class="title"><label>部门</label></td>
    	<td class="content" colspan="3"><span id="dept"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ACCOUNT_TIME_LIMIT_LEVEL" defaultValue="${parametertimelimit.departmentlevel.id}" /></span></td>
	</tr>
	<tr>
    	<td class="title"><label>办理时限</label></td>
    	<td class="content"><span>${parametertimelimit.handlelimit}</span></td>
    	<td class="title"><label>办结时限</label></td>
    	<td class="content"><span>${parametertimelimit.transferredlimit}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>流转时限</label></td>
    	<td class="content"><span>${parametertimelimit.circulationlimit}</span></td>
    	<td class="title"><label>&nbsp;</label></td>
    	<td class="content"><span>&nbsp;</span></td>
	</tr>

</table>

<script type="text/javascript">
$(function(){
	if($("#dept").html()==""){
		$("#dept").html("默认时限");
	}
});
</script>