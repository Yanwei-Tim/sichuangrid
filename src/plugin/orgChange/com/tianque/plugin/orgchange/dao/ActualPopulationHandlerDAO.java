package com.tianque.plugin.orgchange.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;

@DynamicIbatisDAO(value = "ActualPopulationHandlerDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("ActualPopulationHandlerDAO")
public interface ActualPopulationHandlerDAO {

	// 查询重复数据(户籍流口)
	public List<ActualPopulation> queryRepeatDataForList(Map map);

	// 查询户籍未落户重复
	public List<UnsettledPopulation> queryUnsettledAndHouseholdStaffRepeatDataForList(
			Map map);

	// 查询未落重复数据
	public List<UnsettledPopulation> queryUnsettledPopulationRepeatDataForList(
			Map map);

	// 查询境外重复
	public List<OverseaPersonnel> queryOverseaPersonnelRepeatDataForList(Map map);

	// 查询户籍家庭中重复的户口号
	public List<CensusRegisterFamily> queryCensusRegisterFamilyRepeatDataForList(
			Map map);

	// 根据人口ID删除户籍表人口
	public void deleteHouseholdStaffById(Map map);

	// 根据人口ID删除流动
	public void deleteFloatingPopulationById(Long id);

	// 根据人口ID删除未落户人口
	public void deleteUnsettledPopulationById(Long id);

	// 根据人口ID删除境外人口
	public void deleteOverseaPersonnelById(Long id);

	// 更新户籍表中关注状态
	public void updateHouseholdStaffById(Map map);

	// 更新流口表中关注状态
	public void updateFloatingPopulationById(Long id);

	// 更新业务表关注状态
	public void updatePopulationTypeTableById(Map map);

	// 根据人口ID 删除家庭信息关系
	public void deleteGroupFamilyHasPopulationByPopulationId(
			GroupFamilyHasPopulation groupFamilyHasPopulation);

	// 查询人口关联的业务信息
	public List<PopulationTypeBean> queryPopulationTypeByPopulationIdForList(
			PopulationTypeBean populationTypeBean);

	// 删除人口重复的业务表中的信息
	public void deletePopulationTypeTableById(Map map);

	// 删除人口业务关系表
	public void deletePopulationTypeById(Long id);

	// 更新人口业务关系表
	public void updatePopulationTypeById(PopulationTypeBean populationTypeBean);

	// 删除人房关系
	public void deleteHouseHasActualPopulationByIdAndActualType(
			HouseHasActualPopulation houseHasActualPopulation);

	// 取baseinfo的身份证id
	public String getIdCardNoById(Long id);

	// 取baseinfo的id
	public Long getIdByIdCardNo(String idCardNo);

	// 删除业务人员的人房关系
	public void deleteHouseHasImportantPopulationByIdAndActualType(
			PopulationTypeBean oldPopulationType);

	// 更新服务成员记录关系表
	public void updateServiceRecordRelyObject(Map map);

	// 更新监管人员记录关系表
	public void updateServiceGuardersHasObject(Map map);

	// 删除服务成员关系表(重复)
	public void deleteServiceMemberHasObject(Map map);

	// 更新服务成员关系表
	public void updateServiceMemberHasObject(Map map);

	// 删除服务团队关系表(重复)
	public void deleteServiceTeamHasObject(Map map);

	// 更新服务团队关系表
	public void updateServiceTeamHasObject(Map map);

	// 更新户籍人口中户口号(来自A网格)
	public void updateHouseholdStaffForAccountnumber(Map map);

	// 更新户籍家庭中户口号(来自A网格)
	public void updateCensusregisterfamilysForAccountnumber(Map map);

	// 更新家庭信息中的户口号(来自A网格)
	public int updateGroupfamilyForAccountnumber(Map map);

	// 更新监管措施
	public void updateServiceSupervisionMeasures(Map map);

	// 删除青少年人员类型
	public void deleteIdleYouthsHasPropertyDictsById(Long id);

	// 更新上访历时记录
	public void updateSuperiorVisitHistoryById(Map map);

	// 删除残疾人员残疾类型
	public void deleteHandicappedsDisabilityTypeById(Long id);

	// 删除失业人员就业意向
	public void deletePeopleHasEIntentionById(Long id);

	// 删除失业人员培训意向
	public void deletePeopleHasTIntentionById(Long id);

	// 更新户籍家庭身份证号为空
	public void updateCensusregisterfamilysForIdCardNo(Map map);

	// 查询青少年类型
	public List<Long> queryIdleYouthsHasPropertyDictsForList(Long id);

	// 查询残疾人残疾类型
	public List<HandicappedSdisabilityType> queryHandicappedSdisabilityTypeForList(
			Long id);

	// 查询失业人员就业意向
	public List<Long> queryPeopleHasEIntentionForList(Long id);

	// 查询失业人员培训意向
	public List<Long> queryPeopleHasTIntentionForList(Long id);

	// 查询重复同一服务成员
	public List<ServiceMemberWithObject> queryServiceMemberWithObjectForList(
			Map map);

	// 查询重复同一服务成员
	public List<ServiceTeamHasObjects> queryServiceTeamHasObjectsForList(Map map);

	// 查询业务人员的关联的房屋
	public List<Long> queryHouseHasImportantPopulationForList(
			PopulationTypeBean oldPopulationType);

	// 查询实口与业务的关系
	public PopulationTypeBean getPopulationTypeBeanById(Long id);

	// 查询实有人口的关联的房屋
	public List<Long> queryHouseHasActualPopulationForList(
			HouseHasActualPopulation houseHasActualPopulation);
}
