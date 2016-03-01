<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/includes/baseInclude.jsp" />

<script type="text/javascript">
	
	var 
	 columns = [
			{name:"orgName",caption:"区域",width:120,mode:"string"},
			{name:"amount",caption:"人数",width:80,mode:"number",align:'center',format:"#"},
			{name:"general",caption:"总况",children:[
				{name:"baseinfoStatisticDetailVo[index].typeName",caption:"类型",width:100,mode:"string"},
				{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",width:80,mode:"number",align:'center',format:"#"}
				
			]},
			
			{name:"countField",caption:"本年度重新犯罪情况",children:[
			                            	{name:"baseinfoStatisticDetailVo[index].countValue",caption:"本年度重新犯罪人数",width:80,mode:"number",align:'center',format:"#"},
			                            	{name:"baseinfoStatisticDetailVo[index].countValue/baseinfoStatisticDetailVo[index].sum",caption:"重新犯罪率",width:80,align:'center',mode:"number",format:"#.00%"}
			                       ]}

		];
	
</script>
<s:include value="/statAnalyse/baseInfoStat/common/commonStatisticList.jsp">
	<s:param  name="type">
	<s:property value="@com.tianque.service.util.PopulationType@POSITIVE_INFO"/>
	</s:param>
</s:include>
