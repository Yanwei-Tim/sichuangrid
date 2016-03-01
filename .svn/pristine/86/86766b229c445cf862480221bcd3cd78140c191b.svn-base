<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="dustbin" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="#commonLocal">基本信息</a> </li>
			</ul>
			<div id="commonLocal"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs();
		$.ajax({
			url:"${path}/baseinfo/landscapingManage/viewLandscaping.action?location.id=${location.id}",
			success:function(data){
				$("#commonLocal").html("");
				$("#commonLocal").html(data);
			}
		});	
	});
	</script>