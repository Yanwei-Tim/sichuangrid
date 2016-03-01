<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
	$(function() {
		$( "#tabLevelChart" ).tabs({
			ajaxOptions: {
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html("无数据");
				}
			}
		});
	});
</script>
<div id="tabLevelChart">
	<ul>
		<li><a id="plen_char"  href="#tabs-1" onclick="onOrgChanged1()">特殊人群</a></li>
		<li><a id="place"  href="#tabs-1" onclick="onOrgChanged2()">重点场所</a></li>
		<li><a id="letting_house"  href="#tabs-1" onclick="onOrgChanged3()">出租房</a></li>

         <div id="div1" style="display: inline;">
		<li><a id="place_ent"  href="#tabs-1" onclick="onOrgChanged4()">规上企业</a> </li>
        </div>
		<li><a id="place_new"  href="#tabs-1" onclick="onOrgChanged5()">社会组织</a></li>
	</ul>
	<div id="tabs-1" style="overflow:hidden;overflow-y:auto;position:relative;height: 100%;">
		<div id="importantPersonlColumn" class="SigmaReport"></div>
		<div id="importantPersonlPie" class="SigmaReport" style="display: none;"></div>
	</div>
</div>
<div id="infoList"></div>