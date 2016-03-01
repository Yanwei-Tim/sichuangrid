<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="areaInfoBoxC">
	<ul class="areaList">
 	    <li><div class="cl">辖区名称：</div><div class="cr"><span>${drawAreaStatisticVo.organization.orgName}</span></div></li>
		<li><div class="cl">事件数量：</div><div class="cr"><span>${drawAreaStatisticVo.issueSum }</span>件</div></li>
		<li><div class="cl">住房数量：</div><div class="cr"><span>${drawAreaStatisticVo.housePropertySum  }</span>间</div></li>
		<li><div class="cl">楼宇数量：</div><div class="cr"><span>${drawAreaStatisticVo.buildDataSum }</span>幢</div></li>
		<li><div class="cl">重点场所数量：</div><div class="cr"><span>${drawAreaStatisticVo.keyPlaceSum  }</span>个</div></li>
		<li><div class="cl">特殊人群数量：</div><div class="cr"><span>${drawAreaStatisticVo.keyPersonSum }</span>人</div></li>
	</ul>
	
	<div class="areaInfoBoxShadow"></div>
	<div class="gridZoom close"></div>
</div>

<script>
	$(function(){
		$("#statisticInfo").delegate(".gridZoom","click",function(){
			if($("#statisticInfo .areaList").is(":visible")){
				$("#statisticInfo .areaList").hide();
				$(this).css("left","170px").addClass("open").removeClass("close");
			}else{
				$("#statisticInfo .areaList").show();
				$(this).css("left","-15px").addClass("close").removeClass("open");
			}
		})
	})
</script>