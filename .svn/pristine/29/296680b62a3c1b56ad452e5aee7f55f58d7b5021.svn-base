<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	request.setAttribute("baseInfoType",request.getParameter("baseInfoType"));
%>
<script type="text/javascript">
$("#mapSlideBox").slide({mainCell:".bd ul",autoPlay:false,effect:"left"});
var positionPanel=function(){
	var portletNum=parseInt((document.documentElement.clientWidth-260)/350);
	var sortTableWidth=(document.documentElement.clientWidth-260)/portletNum;
	var sortTable=$("#leaderGroup li");
	sortTable.each(function(i){
		$(this).width(sortTableWidth-15);
	});
	$("#leaderGroup").height("auto").height($("#leaderGroup").height());
}

function callback(){
    var dfop = {
    	path:'${path}',
    	baseInfoType:'${baseInfoType}'
    }
    TQ.basicCaseListFunc(dfop);
}
$.cacheScript({
    url:'/resource/getScript/partyBuilding/baseInfos/basicCaseListFunc.js',
    callback: callback
})
</script>