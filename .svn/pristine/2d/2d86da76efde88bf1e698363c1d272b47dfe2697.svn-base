<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="steadyWork" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="#commonSteadyWork"></a> </li>
			</ul>
			<div id="commonSteadyWork"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs();
		$.ajax({
			url:"${path}/account/steadyWorkManage/viewSteadyWork.action?steadyWork.id=${steadyWork.encryptId}",
			success:function(data){
				$("#commonSteadyWork").html("");
				$("#commonSteadyWork").html(data);
			}
		});	
	});
	</script>