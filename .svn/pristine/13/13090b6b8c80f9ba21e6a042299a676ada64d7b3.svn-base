<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24">
	<form id="populationTypeForm" method="post" action="/baseinfo/populationSpecializedInfo/proccessPopulationSpecializedInfo.action">
		<pop:token />
		<input type="hidden" name="population.actualPopulationType" value="<s:property value="#parameters.populationType[0]"/>">
		<s:if test="#parameters.populationType[0].equals(@com.tianque.service.util.PopulationType@FLOATING_POPULATION)">
			<s:action var="dispathBaseInfo" name="dispathFloatingPopulationBaseInfo" namespace="/baseinfo/floatingPopulationManage" executeResult="true" ignoreContextParams="true">
				<s:param name="id" value="#parameters['id'][0]"></s:param>
			</s:action>
		</s:if>
		<s:else>
			<s:action var="dispathBaseInfo" name="dispathHouseholdStaffBaseInfo" namespace="/baseinfo/householdStaff" executeResult="true" ignoreContextParams="true">
				<s:param name="id" value="#parameters['id'][0]"></s:param>
			</s:action>
		</s:else>
		<input name="populationType" type="hidden" value='<s:property value="#parameters.populationType[0]"/>'/>
		<input name="population.id" type="hidden" value='<s:property value="#parameters['id'][0]"/>'/>
		<input id="population-organization-id" name="population.organization.id" type="hidden" value='<s:property value="#dispathBaseInfo.population.organization.id"/>'/>
		<hr width="90%"></hr>
		<s:action name="getPopulationSpecializedInfoByOrgIdAndIdCardNo" namespace="/baseinfo/populationSpecializedInfo" executeResult="true" ignoreContextParams="true">
			<s:param name="idCardNo">
				<s:property value="#dispathBaseInfo.population.idCardNo"/>
			</s:param>
			<s:param name="orgId" value="#dispathBaseInfo.population.organization.id"/>
			<s:param name="actualId" value="#parameters['id'][0]"/>
		</s:action>
	</form>
</div>
<script>
$(function(){
	$("#populationTypeForm").formValidate({
		promptPosition:"bottomLeft",
		ignore:'.typeInfoHidden,:hidden',
		submitHandler:function(form) {
			$.each($("input,select"),function(i,n){
				var self=$(n);
				if($(n).attr("isProccesed")){
					return;
				}
				var name=$(n).attr("name");
				if(name&&""!=name&&name.indexOf("population.")==0){
					var str = $(n).closest(".gridList").attr("id");
					if(str != undefined && str.indexOf('Div') == -1){
						str=str +"."+ name.substr("population.".length,name.length);
					}else{
						str = name.substr("population.".length,name.length);
					}
					if(str.indexOf(".")>=0){
						//str = str.replace(".","_P_");
						str = str.replace(new RegExp(/\./g),'_P_');
						//self.attr("name","population['"+str+"']");
						self.attr("name","population."+str+"");
					}
				}
				$(n).attr("isProccesed","true");
			});
			$.each($(".typeInfoContext"),function(i,n){
				if(!$(n).attr("checked")){
					$("#"+$(n).val()+"Div").remove();
				}
			});
			$(form).ajaxSubmit({
				success:function(data){
					if(data){
			             $.messageBox({message:"业务信息成功保存！"});
						doAction('<s:property value="#parameters.dailogName[0]"/>',data.id,data);
					}
				}
			});
		},
		rules:{},
		messages:{}
	});
})
</script>