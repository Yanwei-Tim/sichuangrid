<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="emergencyShelter" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="#emergencyShelterBaseInfo">基本信息</a> </li>
			</ul>
			<div id="emergencyShelterBaseInfo"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs({ selected: 0 });
		$.ajax({
			url:"${path}/baseinfo/emergencyShelterManage/viewEmergencyShelter.action?location.id=${location.id}",
			success:function(data){
				$("#emergencyShelterBaseInfo").html("");
				$("#emergencyShelterBaseInfo").html(data);
			}
		});
	});
	</script>