<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<link rel="stylesheet" type="text/css" href="/resource/external/blockBox/css/blackbox.css" />
<script type="text/javascript" src="${resource_path}/resource/external/jsPlumb/js/jquery.jsPlumb-1.3.8-all-min.js"></script>
<script src="${resource_path}/resource/system/js/issueFlow.js"></script>
<script src="${resource_path}/resource/external/blockBox/js/jquery.blackbox.js"></script>
<script>
	jsPlumb.bind("ready", function() {
		$.ajax({
			url:'${path}/issues/issueManage/viewIssueMap.action?issue.id=${issue.id}',
			success:function(data){
				$("#issueFlow").issueFlow({
					data:data
				});
			}
		});
	});
</script>
<div id="holder" style="padding:10px;">
	<div id="issueFlow" class="issueFlow" style="position:relative;"></div>
</div>
