<@s.include value="/includes/baseInclude.jsp"/>   
 <div id="druggy" class="container container_24">
    <input id="populationId" type="hidden" name="population.populationId" value="${(population.id)!}" />
	<input id="tempClassName" type="hidden" name="" value="${(tempClassName)!}" />
	
		
		<div id=tabs>
			<ul>
				<li id="populationBaseInfo"><a href="">基本信息</a> </li>
				<#if "unsettledPopulationTemp"!=tempClassName>
				<li id="populationBussiness">
					<a href=""><#if tempClassName=="householdStaffTemp">户籍信息<#elseif tempClassName=="floatingPopulationTemp">流入信息<#else>业务信息</#if></a>
				</li>
				</#if>
				
			</ul>
   		</div>
   		
  </div>
<script>
var tempClassName = $("#tempClassName").val(); 
var lowerCaseModuleName = tempClassName.substring(0,1).toLowerCase()+tempClassName.substring(1);
<@s.if test='"unsettledPopulationTemp".equals(lowerCaseModuleName)||"overseaPersonnelTemp".equals(lowerCaseModuleName)'>
	var commonPopulationUrl = "${path}/plugin/dataManage/"+lowerCaseModuleName+"Manage/viewCommonPopulation.action?id=${(population.id)!}&mode=view";
</@s.if>
<@s.else>
	var commonPopulationUrl = "${path}/plugin/dataManage/"+lowerCaseModuleName+"Manage/viewCommonPopulation.action?id=${(population.id)!}&mode=view";
	var bussinessUrl = "${path}/plugin/dataManage/"+lowerCaseModuleName+"Manage/viewBussiness.action?id=${(population.id)!}&mode=view";
</@s.else>

$("#populationBaseInfo a").attr("href",commonPopulationUrl);
$("#populationBussiness a").attr("href",bussinessUrl);
$(document).ready(function(){

	$( "#tabs").tabs();
});
</script>