<@s.include value="/includes/baseInclude.jsp"/>   
 <div id="druggy" class="container container_24">
    <input id="populationId" type="hidden" name="population.populationId" value="${(overseaPersonnel.id)!}" />
	<input id="tempClassName" type="hidden" name="" value="${(tempClassName)!}" />
	
		
		<div id=tabs>
			<ul>
				<li id="populationBaseInfo"><a href="">基本信息</a> </li>
				
			</ul>
   		</div>
   		
  </div>
<script>
var tempClassName = $("#tempClassName").val(); 
	
	var commonPopulationUrl = "${path}/plugin/dataManage/overseaPersonnelTempManage/viewCommonPopulation.action?id=${(overseaPersonnel.id)!}&mode=view";

$("#populationBaseInfo a").attr("href",commonPopulationUrl);
$(document).ready(function(){

	$( "#tabs").tabs();
});
</script>