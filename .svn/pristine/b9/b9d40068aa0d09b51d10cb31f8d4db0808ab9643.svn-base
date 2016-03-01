<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="center-left" id="plaitSchemeTreeBox" style="overflow:auto;">
	<jsp:include page="/incident/extTreeForIncident/plaitSchemeTree.jsp"/>
</div>

<div class="center-right">
</div>
<script>
	$(function(){
		var plaitSchemeTreeBoxHeight=function(){
			var centerHeight=$(".ui-layout-center").outerHeight();
			$("#plaitSchemeTreeBox").height(centerHeight-35);
		}
		$(window).resize(function(){
			clearTimeout(window._plaitSchemeTreeBoxTimer);
			window._plaitSchemeTreeBoxTimer=setInterval(function(){plaitSchemeTreeBoxHeight()},300);
		})
		plaitSchemeTreeBoxHeight();
	})
</script>