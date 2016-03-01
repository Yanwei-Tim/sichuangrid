<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

    <div id="flashcontent" style="width: 100%; height: 350px">

	</div>

	<script type="text/javascript">
	var scopeId ='<%=request.getParameter("scopeId")%>';
	var orgId ='<%=request.getParameter("orgId")%>';

	var minYear ='<%=request.getParameter("minYear")%>';
	var minMonth ='<%=request.getParameter("minMonth")%>';
	var maxYear ='<%=request.getParameter("maxYear")%>';
	var maxMonth ='<%=request.getParameter("maxMonth")%>';
	var issueTypeStanalIssueTypeDomainId = '<%=request.getParameter("issueTypeStanal.issueTypeDomain.id")%>';

	$("#flashcontent").columnChart({
		url: "${path}/issueTypeStanal/issueTypeStanalManage/columnTypeSon.action?issueTypeStanal.issueTypeDomain.id="+issueTypeStanalIssueTypeDomainId+"&scopeId="+scopeId+"&orgId="+orgId+"&minYear="+minYear+"&minMonth="+minMonth+"&maxYear="+maxYear+"&maxMonth="+maxMonth,
		moduleName:"事件处理类别统计"
		
	})
	</script>

