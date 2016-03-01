<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>

<div class="tabChange">
	<div class="new_personList">
		<div class="gis_org">
			<div class="gis_img clearfix">
				<img src="#" width="310" height="224" id="image" />
			</div>
			<div class="gis_org_info">
				<div id="gis-org-introduce">
					<strong><span id="villageProfileName">当前层级名称</span></strong><p id="natureGeography_p"></p>
				</div>
				<a href="javascript:;" class="more">展开更多>>></a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
/*$(function(){
	$(".gis_org_info .more").toggle(function(){
		$("#gis-org-introduce").height("auto");
		$(this).text("收缩>>>");
	},function(){
		$("#gis-org-introduce").height(350);
		$(this).text("展开更多>>>");
	})

	var mainHeight=document.documentElement.clientHeight-125;
	$(".gis_info_box").height(mainHeight);
	
	$(window).resize(function(){
		$(".gis_info_box").height(mainHeight);
	})

})
*/
</script>