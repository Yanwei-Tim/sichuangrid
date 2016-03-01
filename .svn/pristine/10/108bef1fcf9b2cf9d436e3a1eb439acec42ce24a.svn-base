<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<jsp:include page="/includes/baseInclude.jsp" />

<script type="text/javascript">
	var columns = [		
			{name:"orgName",caption:"区域",width:120,mode:"string"},	
			{name:"amount",caption:"人数",width:80,mode:"number",align:'center',format:"#"},
			{name:"general",caption:"总况",children:[
				{name:"baseinfoStatisticDetailVo[index].typeName",caption:"类型",width:100,mode:"string"},
				{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",width:80,mode:"number",align:'center',format:"#"}
			]}
		];
</script>
<s:include value="/statAnalyse/baseInfoStat/commonNew/commonStatisticList.jsp">
	<s:param  name="type">
	<s:property value="@com.tianque.service.util.PopulationType@OTHER_ATTENTION_PERSONNEL"/>
	</s:param>
</s:include>