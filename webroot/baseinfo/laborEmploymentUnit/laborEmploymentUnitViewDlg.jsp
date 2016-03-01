<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="laborEmploymentUnit" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="#laborEmploymentUnitBaseInfo">基本信息</a> </li>
				<li><a href="#laborEmploymentUnitP">业务信息</a> </li>
			</ul>
			<div id="laborEmploymentUnitBaseInfo"></div>
			<div id="laborEmploymentUnitP"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs({ selected: 0 });
		$.ajax({
			url:"${path}/baseinfo/laborEmploymentUnitManage/viewLaborEmploymentUnit.action?location.id=${location.id}",
			success:function(data){
				$("#laborEmploymentUnitBaseInfo").html("");
				$("#laborEmploymentUnitBaseInfo").html(data);
			}
		});
		$.ajax({
			url:"${path}/baseinfo/laborEmploymentUnitManage/viewLaborEmploymentUnit.action?location.id=${location.id}&mode=businessData",
			success:function(data){
				$("#laborEmploymentUnitP").html("");
				$("#laborEmploymentUnitP").html(data);
			}
		});
	});
	</script>