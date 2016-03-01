<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<script type="text/javascript">
function viewBusinessPopulation(datas,info){
	for(var i=0;i<datas.length;i++){
		<pop:JugePermissionTag ename="viewRectificativePerson">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@RECTIFICATIVE_POPULATION"/>'){
			info.after('<li><a href="${path}/baseinfo/rectificativePersonManage/viewRectificativePerson.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@RECTIFICATIVE_POPULATION)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewPositiveInfo">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@POSITIVE_INFO"/>'){
			info.after('<li><a href="${path}/baseinfo/positiveInfoManage/viewPositiveInfoById.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@POSITIVE_INFO)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewMentalPatient">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@MENTAL_PATIENT"/>'){
			info.after('<li><a href="${path}/baseinfo/mentalPatientManage/viewMentalPatient.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@MENTAL_PATIENT)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewDruggy">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@DRUGGY"/>'){
			info.after('<li><a href="${path}/baseinfo/druggyManage/viewDruggy.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@DRUGGY)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewAidspopulations">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@AIDSPOPULATIONS_KEY"/>'){
			info.after('<li><a href="${path}/baseinfo/aidspopulationsManage/viewAidspopulations.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@AIDSPOPULATIONS)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewDangerousGoodsPractitioner">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@DANGEROUS_GOODS_PRACTITIONER"/>'){
			info.after('<li><a href="${path}/baseinfo/dangerousGoodsPractitionerManage/viewDangerousGoodsPractitioner.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@DANGEROUS_GOODS_PRACTITIONER)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		//fateson add  otheratten
		<pop:JugePermissionTag ename="viewOtherAttentionPersonnel">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@OTHER_ATTENTION_PERSONNEL"/>'){
			info.after('<li><a href="${path}/baseinfo/otherAttentionPersonnelManage/viewOtherAttentionPersonnel.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@OTHER_ATTENTION_PERSONNEL)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewUnemployedPeople">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@UNEMPLOYED_PEOPLE"/>'){
			info.after('<li><a href="${path}/baseinfo/unemployedPeopleManage/viewUnemployedPeople.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@UNEMPLOYED_PEOPLE)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewIdleYouth">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@IDLE_YOUTH"/>'){
			info.after('<li><a href="${path}/baseinfo/idleYouthManage/viewIdleYouth.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@IDLE_YOUTH)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewNurturesWomen">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@NURTURES_WOMEN"/>'){
			info.after('<li><a href="${path}/baseinfo/nurturesWomenManage/viewNurturesWomen.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@NURTURES_WOMEN)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewElderlyPeople">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@ELDERLY_PEOPLE"/>'){
			info.after('<li><a href="${path}/baseinfo/elderlyPeopleManage/viewElderlyPeople.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@ELDERLY_PEOPLE)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewHandicapped">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@HANDICAPPED"/>'){
			info.after('<li><a href="${path}/baseinfo/handicappedManage/viewHandicapped.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@HANDICAPPED)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewOptimalObject">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@OPTIMAL_OBJECT"/>'){
			info.after('<li><a href="${path}/baseinfo/optimalObjectManage/viewOptimalObject.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@OPTIMAL_OBJECT)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewAidNeedPopulation">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@AID_NEED_POPULATION"/>'){
			info.after('<li><a href="${path}/baseinfo/aidNeedPopulationManage/viewAidNeedPopulation.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@AID_NEED_POPULATION)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewSuperiorVisit">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@SUPERIOR_VISIT"/>'){
			info.after('<li><a href="${path}/baseinfo/superiorVisitManage/viewSuperiorVisit.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@SUPERIOR_VISIT)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewAidspopulations">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@AIDSPOPULATIONS"/>'){
			info.after('<li><a href="${path}/baseinfo/aidspopulationsManage/viewAidspopulations.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@AIDSPOPULATIONS)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewFPersonnel">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@F_PERSONNEL"/>'){
			info.after('<li><a href="${path}/baseinfo/fPersonnelManage/viewFPersonnel.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@F_PERSONNEL)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewQPersonnel">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@Q_PERSONNEL"/>'){
			info.after('<li><a href="${path}/baseinfo/qPersonnelManage/viewQPersonnel.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@Q_PERSONNEL)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="viewMPersonnel">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@M_PERSONNEL"/>'){
			info.after('<li><a href="${path}/baseinfo/mPersonnelManage/viewMPersonnel.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@M_PERSONNEL)"/></a></li>');
		}
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewGoodSamaritan">
		if(datas[i]!=null&&datas[i].populationType=='<s:property value="@com.tianque.service.util.PopulationType@GOOD_SAMARITAN"/>'){
			info.after('<li><a href="${path}/baseinfo/goodSamaritanManage/viewGoodSamaritan.action?population.id='+datas[i].encryptPopulationId+'"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@GOOD_SAMARITAN)"/></a></li>');
		}
		</pop:JugePermissionTag>
		
	}
}
</script>
	
