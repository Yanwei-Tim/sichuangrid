<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<style>
	.setOption{
		margin-left: 15px;
	}
</style>

<div>
		<p class="pTop" >1.培育有优势特色产业<input type="text" class="setOption" id="setOption1"/></p>
		<p class="pTop" >2.农业主导产业收入占农村家庭经营收入的比重<input class="setOption" type="text" id="setOption2"/></p>
		<p class="pTop" >3.农民人均可支配收入<input class="setOption" type="text" id="setOption3"/></p>
		<p class="pTop" >4.农村人均安全住房面积<input class="setOption" type="text" id="setOption4"/></p>
		<p class="pTop" >5.无房户、危房户、住房困难户住房问题全部解决<input class="setOption" type="text" id="setOption5"/></p>
		<p class="pTop" >6.九年义务教育目标人群全覆盖<input class="setOption" type="text" id="setOption6"/></p>
		<p class="pTop" >7.新农合参保率<input class="setOption" type="text" id="setOption7"/></p>
		<p class="pTop" >8.新农保应保尽保<input class="setOption" type="text" id="setOption8"/></p>
		<p class="pTop" >9.每年组织群众性文体活动<input class="setOption" type="text" id="setOption9"/></p>
		<p class="pTop" >10.村级公共服务活动中心服务设施面积<input class="setOption" type="text" id="setOption10"/></p>
		<p class="pTop" >11.基层党组织和农村党员调查满意度<input class="setOption" type="text" id="setOption11"/></p>
		<p class="pTop" >12.三务公开群众满意度<input class="setOption" type="text" id="setOption12"/></p>
		<p class="pTop" >13.通过新建、改造、保护等形式推进新村建设全覆盖<input class="setOption" type="text" id="setOption13"/></p>
		<p class="pTop" >14.通村通组道路硬化率<input class="setOption" type="text" id="setOption14"/></p>
		<p class="pTop" >15.安全饮水全覆盖<input class="setOption" type="text" id="setOption15"/></p>
		<p class="pTop" >16.农村电网改造全覆盖<input class="setOption" type="text" id="setOption16"/></p>
		<p class="pTop" >17.面源污染有效治理<input class="setOption" type="text" id="setOption17"/></p>
		<p class="pTop" >18.生活垃圾处理全覆盖<input class="setOption" type="text" id="setOption18"/></p>
		<p class="pTop" >19.生活污水处理覆盖率<input class="setOption" type="text" id="setOption19"/></p>
		<p class="pTop" >20.农村院落整治覆盖面<input class="setOption" type="text" id="setOption20"/></p>
</div>

<script type="text/javascript">
	var optionNum = 1;//记录值的ID序列
	//遍历获得现字段值
	$(".setOption").each(function(){
		$(this).val($("#option"+optionNum).val());	
		optionNum++;
	});
</script>
