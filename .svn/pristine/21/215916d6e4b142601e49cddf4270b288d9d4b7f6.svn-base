<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<form action="${path }/issues/pacificUnionFoundingManage/addPacificUnionFoundings.action" id="maintainForm" method="post">
		<table width="100%" cellspacing="0" class="countlist">
			<tr>
				<th colspan="13" class="title"><a href="javascript:;"
					class="edit">编辑</a><label id="orgName"></label></th>
			</tr>
			<tr>
				<th>地区</th>
				<th>1月</th>
				<th>2月</th>
				<th>3月</th>
				<th>4月</th>
				<th>5月</th>
				<th>6月</th>
				<th>7月</th>
				<th>8月</th>
				<th>9月</th>
				<th>10月</th>
				<th>11月</th>
				<th>12月</th>
			</tr>
			<input type="hidden" name="year" value="${param.year}" />
			<input type="hidden" name="parentOrgId" value="${param.parentOrgId}" />
			<c:forEach var="pacificUnionFounding"
				items="${pacificUnionFoundings}" varStatus="pff">
				<input type="hidden" name="pacificUnionFoundings[${pff.index}].year" value="${param.year}" />
				<tr>
					<td><c:out
							value="${pacificUnionFounding.organization.orgName}" /><input
						type="hidden"
						name="pacificUnionFoundings[${pff.index}].organization.id"
						value="${pacificUnionFounding.organization.id}" />
						<input
						type="hidden"
						name="pacificUnionFoundings[${pff.index}].orgInternalCode"
						value="${pacificUnionFounding.orgInternalCode}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.january eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].january"
						value="${pacificUnionFounding.january}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.february eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].february"
						value="${pacificUnionFounding.february}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.march eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].march"
						value="${pacificUnionFounding.march}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.april eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].april"
						value="${pacificUnionFounding.april}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.may eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].may"
						value="${pacificUnionFounding.may}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.june eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].june"
						value="${pacificUnionFounding.june}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.july eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].july"
						value="${pacificUnionFounding.july}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.august eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].august"
						value="${pacificUnionFounding.august}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.september eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].september"
						value="${pacificUnionFounding.september}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.october eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].october"
						value="${pacificUnionFounding.october}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.november eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].november"
						value="${pacificUnionFounding.november}" /></td>
					<td
						class="evaluate<c:if test='${pacificUnionFounding.december eq 0}'> select</c:if>"><input
						type="hidden" name="pacificUnionFoundings[${pff.index}].december"
						value="${pacificUnionFounding.december}" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</div>
<script>
	$(function() {
		//$("#orgSelector").val();
		if($("#searchOrgLevelId").val()=="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>")
			$("#orgName").html('${param.year}年平安和谐街道考核情况');
		else if($("#searchOrgLevelId").val()=="<s:property value='@com.tianque.domain.property.OrganizationLevel@TOWN'/>")
			$("#orgName").html('${param.year}年平安和谐社区考核情况');
		else if($("#searchOrgLevelId").val()=="<s:property value='@com.tianque.domain.property.OrganizationLevel@CITY'/>")
			$("#orgName").html('${param.year}年平安和谐区县考核情况');
		else if($("#searchOrgLevelId").val()=="<s:property value='@com.tianque.domain.property.OrganizationLevel@PROVINCE'/>")
			$("#orgName").html('${param.year}年平安和谐市级考核情况');
		else if($("#searchOrgLevelId").val()=="<s:property value='@com.tianque.domain.property.OrganizationLevel@COUNTRY'/>")
			$("#orgName").html('${param.year}年平安和谐省级考核情况');
		$(".countlist tr:odd").addClass("even");
		$(".countlist tr .evaluate").click(function() {
			if ($(".countlist .edit").hasClass("on")) {//如果是编辑则加上红旗
				if($(this).attr("class").indexOf("select")!=-1){
					$(this).children("input").val("1");
				}else{
					$(this).children("input").val("0");
				}
				$(this).toggleClass("select");
			}
		})
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
		        $(form).ajaxSubmit({
					success: function(data){
						if(data!=true) {
							$.messageBox({message:"保存失败!",level:"error"});
						} else {
							$.messageBox({message:"保存成功!"});
						}
					}
		      	});
			},
			rules:{
			},
			messages:{
			},
			ignore:':hidden'
		});
		if($("#searchOrgId").val()==USER_ORG_ID){
			$(".countlist .edit").click(function() {
				$(this).toggleClass("on");//开/关
				if ($(this).hasClass("on")) {//开关替换
					$(this).text("保存");
				} else {
					$(this).text("编辑");
					$("#maintainForm").submit();
				}
			});
		}else{
			$(".countlist .edit").hide();
		}
	})
</script>