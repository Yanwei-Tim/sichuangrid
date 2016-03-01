<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp" />

<div class="container container_24" >
	<div id="druggy" class="container container_24">
		<form id="maintainForm22" method="post"action="${path}/plugin/dataManage/${tempClassName}Manage/updateTempBusiness.action"  >
			<@pop.token />
			<input id="from" type="hidden" name="from" value="${from}" />
			<input id="previousOrNextOrSave" type="hidden" name="previousOrNextOrSave" value="" />
			<input type="hidden" name="population.id" value="${(population.id)!}" />
			<input type="hidden" name="population.actualPopulationType" value="${(population.actualPopulationType)!}" />
			<#if tempClassName =="householdStaffTemp">
				<#include "../householdStaffTempManage/householdStaff.ftl" />
			<#else>
				<@s.include value="${path}/baseinfo/commonPopulation/populationInfo/${actualClassName}.jsp" />
				</#if>
		</form>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	if($("input[name='population\.id']").size()>1){
		$($("input[name='population\.id']")[0]).attr("disabled","disabled");
	}

	$("#maintainForm22").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			if("handicapped" == "${actualClassName}"){
			$("input[type='hidden'][name='population.disabilityLevelIds']").each(function (){
				if($(this).val() == null || $(this).val() == ""){
					$(this).remove();
				}
			});}
			$(form).ajaxSubmit({
				success:function(data){
				 	<#if "FROM_CLAIM" != from>
					if(!data.id){
           	 			$.messageBox({ 
							evel: "error"
			 			});
            			return;
					}
	                $.messageBox({message:"修改成功！"});
	                
	                <#else>
	                	if(data){
		                	$.messageBox({message:"认领成功！"});
	                	}
	                	if(data.successList){
		               		 $("#claimErrorMsg").delRowData(${id!});
		                	 reloadClaimResultList(data);
	                	}
	                </#if>
	            	$("#${tempClassName}List").trigger("reloadGrid");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
		},
		messages:{
		}
	});
});

</script>