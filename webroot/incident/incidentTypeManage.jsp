<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="center-left" style="border-left: 1px solid #CCCBCB;">
	<jsp:include page="/incident/extTreeForIncident/incidentSystemTree.jsp"/>
</div>

<div class="center-right">
	<jsp:include page="/incident/incidentManage/index.jsp"/>
</div>
<script>
	var centerHeight=$(".ui-layout-center").outerHeight();
	$(".center-left").height(centerHeight-25);
</script>