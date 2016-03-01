<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<a href="javascript:;" class="portlet_roll_left"></a>
	<a href="javascript:;" class="portlet_roll_right"></a>
	<div class="portlet_box_list clearfix">
		<c:forEach items="${myVisits }" var="p">
			<div class="portlet_box" id="${p.eName}" >
			<div class="portlet_box_person">
				<a href="javascript:;"><img src="${resource_path}/resource/workBench/images/icon/${p.eName}.png"/></a>
			</div>
			<div class="portlet_box_personData">
				<a href="javascript:;" class="number">${p.amount}</a>
				<div class="downSorrow"></div>
			</div>
			<p class="portlet_box_title">
				<a href="javascript:;">
					${p.cName}
				</a>
			</p>
			</div>
		</c:forEach>
	</div>
	<div class="portlet_object">
	</div>