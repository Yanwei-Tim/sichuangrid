<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<script type="text/javascript">
function earingWarnFormatter(el,options,rowData){
	var returnString="";
	if(rowData.earingWarn == '<s:property value="@com.tianque.xichang.working.flow.constant.HandleType@NORMAL_HANDLE"/>'){
		returnString="<strong class='myAccountStyle account-green' title='正常办理中'></strong>";
	}else if(rowData.earingWarn == '<s:property value="@com.tianque.xichang.working.flow.constant.HandleType@OVERTIME_HANDLE"/>'){
		returnString="<strong class='myAccountStyle account-yellow' title='超时办理'></strong>";
	}else if(rowData.earingWarn == '<s:property value="@com.tianque.xichang.working.flow.constant.HandleType@NORMAL_FINISH"/>'){
		returnString="<strong class='myAccountStyle account-blue' title='正常办结'></strong>";
	}else if(rowData.earingWarn == '<s:property value="@com.tianque.xichang.working.flow.constant.HandleType@OVERTIME_FINISH"/>'){
		returnString="<strong class='myAccountStyle account-purple' title='超时办结'></strong>";
	}else if(rowData.earingWarn == '<s:property value="@com.tianque.xichang.working.flow.constant.HandleType@OVERTIME_CIRCULATION"/>'){
		returnString="<strong class='myAccountStyle account-red' title='超时流转'></strong>";
	}
	return returnString;
}
</script>