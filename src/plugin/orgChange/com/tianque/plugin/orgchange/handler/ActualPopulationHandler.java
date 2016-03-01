package com.tianque.plugin.orgchange.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.familyInfo.domain.GroupFamilyHasPopulation;
import com.tianque.baseInfo.familyInfo.service.GroupFamilyService;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.Organization;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.plugin.orgchange.common.PopulationTypeTable;
import com.tianque.plugin.orgchange.dao.ActualPopulationHandlerDAO;
import com.tianque.plugin.orgchange.domain.BackupBaseData;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeInfo;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.plugin.orgchange.service.BackupBaseDataService;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 组织机构迁移和合并(实有人口)
 * 
 * 
 */
@Component("actualPopulationHandler")
public class ActualPopulationHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(ActualPopulationHandler.class);

	@Autowired
	private ActualPopulationHandlerDAO actualPopulationHandlerDAO;

	@Autowired
	private HouseholdStaffService householdStaffService;

	@Autowired
	private FloatingPopulationService floatingPopulationService;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private GroupFamilyService groupFamilyService;

	@Autowired
	private BackupBaseDataService backupBaseDataService;

	@Autowired
	private ShardConversion shardConversion;

	@Autowired
	private ActualHouseService actualHouseService;

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin merge.");
		// 合并时如果判断orgid是否变更 如变更则为网格合并
		if (orgMapping.getNewOrgId().equals(orgMapping.getOldOrgId())) {
			execute(orgMapping);
		} else {
			// 网格合并处理
			executeMerge(orgMapping);
		}
		if (logger.isDebugEnabled())
			logger.debug("end merge.");

	}

	public void executeMerge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin executeMerge.orgMapping:{}", orgMapping);
		if (orgMapping.isHasData()) {
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			ModuleTable moduleTable = orgMapping.getModuleTable();
			String shardCode = shardConversion.getShardCode(orgMapping
					.getNewOrgId());

			// 户籍人口处理
			ActualPopulation newActualPopulation = null;
			List<ActualPopulation> list = null;
			String householdTableName = "householdStaffs_" + shardCode;
			if (householdTableName.equalsIgnoreCase(moduleTable.getTableName())) {

				// 户籍重复数据查询
				list = queryRepeatDataForList(householdTableName,
						householdTableName, orgMapping.getOldOrgId(),
						orgMapping.getNewOrgId());
				// 合并网格重复身份证处理
				for (ActualPopulation repeatActualPopulation : list) {

					// 清除户籍家庭与户主的关系(户籍重复人口要删除 如果只更新了户籍家庭表的户主身份证为空)
					updateCensusregisterfamilysForIdCardNo(
							repeatActualPopulation.getId(),
							orgMapping.getNewOrgId(), shardCode);

					// 业务数据处理
					newActualPopulation = householdStaffService
							.getHouseholdStaffByBaseInfoId(
									repeatActualPopulation.getBaseInfoId(),
									orgMapping.getNewOrgId());

					householdStaffAndFloatingPopulationRepeatDataHandler(
							orgMapping, repeatActualPopulation,
							newActualPopulation,
							BaseInfoTables.HOUSEHOLDSTAFF_KEY,
							BaseInfoTables.HOUSEHOLDSTAFF_KEY);

					// 备份删除的数据
					String whereConditions = "id = "
							+ repeatActualPopulation.getId();
					backupMonomer(orgMapping, moduleTable.getTableName(),
							whereConditions);
					// 删除户籍人口中的迁移合并重复数据
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", repeatActualPopulation.getId());
					map.put("shardCode", shardCode);
					actualPopulationHandlerDAO.deleteHouseholdStaffById(map);
				}
				log.appendSuccessDesc("户籍与户籍重复数据[" + list.size() + "]条");
				// 户籍流口重复数据查询
				list = queryRepeatDataForList(householdTableName,
						"floatingPopulations", orgMapping.getOldOrgId(),
						orgMapping.getNewOrgId());

				for (ActualPopulation repeatActualPopulation : list) {

					// 清除户籍家庭与户主的关系(户籍表与户主的关系 在下面更新为注销状态时一并更新)
					updateCensusregisterfamilysForIdCardNo(
							repeatActualPopulation.getId(),
							orgMapping.getNewOrgId(), shardCode);

					// 业务数据处理
					newActualPopulation = floatingPopulationService
							.getFloatingPopulationByBaseInfoId(
									repeatActualPopulation.getBaseInfoId(),
									orgMapping.getNewOrgId());

					householdStaffAndFloatingPopulationRepeatDataHandler(
							orgMapping, repeatActualPopulation,
							newActualPopulation,
							BaseInfoTables.HOUSEHOLDSTAFF_KEY,
							BaseInfoTables.FLOATINGPOPULATION_KEY);

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", repeatActualPopulation.getId());
					map.put("shardCode", shardCode);
					actualPopulationHandlerDAO.updateHouseholdStaffById(map);
				}
				log.appendSuccessDesc("户籍与流口互斥数据[" + list.size() + "]条");
				// 户籍未落户重复
				List<UnsettledPopulation> unsettledPopulationAndHouseholdStaffList = queryUnsettledAndHouseholdStaffRepeatDataForList(
						householdTableName, "unsettledPopulations",
						orgMapping.getOldOrgId(), orgMapping.getNewOrgId());
				for (UnsettledPopulation unsettledPopulation : unsettledPopulationAndHouseholdStaffList) {
					deleteUnsettledPopulationAndRelation(orgMapping,
							unsettledPopulation);
				}
				log.appendSuccessDesc("户籍与未落户重复数据["
						+ unsettledPopulationAndHouseholdStaffList.size()
						+ "]条");

			} else if ("floatingPopulations".equalsIgnoreCase(moduleTable
					.getTableName())) {

				// 流口户籍重复数据查询
				list = queryRepeatDataForList("floatingPopulations",
						householdTableName, orgMapping.getOldOrgId(),
						orgMapping.getNewOrgId());
				// 合并网格重复身份证处理
				for (ActualPopulation repeatActualPopulation : list) {

					// 业务数据处理
					newActualPopulation = householdStaffService
							.getHouseholdStaffByBaseInfoId(
									repeatActualPopulation.getBaseInfoId(),
									orgMapping.getNewOrgId());

					householdStaffAndFloatingPopulationRepeatDataHandler(
							orgMapping, repeatActualPopulation,
							newActualPopulation,
							BaseInfoTables.FLOATINGPOPULATION_KEY,
							BaseInfoTables.HOUSEHOLDSTAFF_KEY);

					// 更新流口中的人员 为注销状态
					actualPopulationHandlerDAO
							.updateFloatingPopulationById(repeatActualPopulation
									.getId());
				}
				log.appendSuccessDesc("流口与户籍互斥数据[" + list.size() + "]条");
				// 流口重复数据查询
				list = queryRepeatDataForList("floatingPopulations",
						"floatingPopulations", orgMapping.getOldOrgId(),
						orgMapping.getNewOrgId());

				// 合并网格重复身份证处理
				for (ActualPopulation repeatActualPopulation : list) {

					// 业务数据处理
					newActualPopulation = floatingPopulationService
							.getFloatingPopulationByBaseInfoId(
									repeatActualPopulation.getBaseInfoId(),
									orgMapping.getNewOrgId());

					householdStaffAndFloatingPopulationRepeatDataHandler(
							orgMapping, repeatActualPopulation,
							newActualPopulation,
							BaseInfoTables.FLOATINGPOPULATION_KEY,
							BaseInfoTables.FLOATINGPOPULATION_KEY);

					// 备份删除的数据
					String whereConditions = "id = "
							+ repeatActualPopulation.getId();
					backupMonomer(orgMapping, moduleTable.getTableName(),
							whereConditions);

					// 删除流口的迁移合并重复数据
					actualPopulationHandlerDAO
							.deleteFloatingPopulationById(repeatActualPopulation
									.getId());

				}
				log.appendSuccessDesc("流口与流口重复数据[" + list.size() + "]条");
			} else if ("unsettledPopulations".equalsIgnoreCase(moduleTable
					.getTableName())) {
				List<UnsettledPopulation> unsettledPopulationList = queryUnsettledPopulationRepeatDataForList(
						"unsettledPopulations", "unsettledPopulations",
						orgMapping.getOldOrgId(), orgMapping.getNewOrgId());

				for (UnsettledPopulation unsettledPopulation : unsettledPopulationList) {
					deleteUnsettledPopulationAndRelation(orgMapping,
							unsettledPopulation);
				}
				log.appendSuccessDesc("未落户与未落户重复数据["
						+ unsettledPopulationList.size() + "]条");
				// 未落户户籍重复
				List<UnsettledPopulation> unsettledPopulationAndHouseholdStaffList = queryUnsettledAndHouseholdStaffRepeatDataForList(
						householdTableName, "unsettledPopulations",
						orgMapping.getNewOrgId(), orgMapping.getOldOrgId());
				for (UnsettledPopulation unsettledPopulation : unsettledPopulationAndHouseholdStaffList) {
					deleteUnsettledPopulationAndRelation(orgMapping,
							unsettledPopulation);
				}
				log.appendSuccessDesc("未落户与户籍重复数据["
						+ unsettledPopulationAndHouseholdStaffList.size()
						+ "]条");

			} else if ("overseaPersonnel".equalsIgnoreCase(moduleTable
					.getTableName())) {
				List<OverseaPersonnel> overseaPersonneList = queryOverseaPersonnelRepeatDataForList(
						"overseaPersonnel", "overseaPersonnel",
						orgMapping.getOldOrgId(), orgMapping.getNewOrgId());

				for (OverseaPersonnel overseaPersonnel : overseaPersonneList) {
					backupGroupFamilyByPopulation(orgMapping,
							overseaPersonnel.getId(),
							BaseInfoTables.OVERSEAPERSONNEL_KEY);

					groupFamilyService
							.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
									overseaPersonnel.getId(),
									BaseInfoTables.OVERSEAPERSONNEL_KEY,
									overseaPersonnel.getCertificateNo());

					// 切断A人房关系
					deleteHouseHasActualPopulationByIdAndActualType(orgMapping,
							overseaPersonnel.getId(),
							BaseInfoTables.OVERSEAPERSONNEL_KEY);

					// 备份删除的数据
					String whereConditions = "id = " + overseaPersonnel.getId();
					backupMonomer(orgMapping, moduleTable.getTableName(),
							whereConditions);

					actualPopulationHandlerDAO
							.deleteOverseaPersonnelById(overseaPersonnel
									.getId());
				}
				log.appendSuccessDesc("境外人员与境外人员重复数据["
						+ overseaPersonneList.size() + "]条");
			} else if ("censusregisterfamilys".equalsIgnoreCase(moduleTable
					.getTableName())) {
				Organization oldOrganization = organizationDubboService
						.getSimpleOrgById(orgMapping.getOldOrgId());
				List<CensusRegisterFamily> censusRegisterFamilyList = queryCensusRegisterFamilyRepeatDataForList(
						"censusregisterfamilys", "censusregisterfamilys",
						orgMapping.getOldOrgId(), orgMapping.getNewOrgId());

				for (CensusRegisterFamily censusRegisterFamily : censusRegisterFamilyList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("accountnumber",
							censusRegisterFamily.getAccountNumber());
					map.put("orgId", orgMapping.getOldOrgId());
					map.put("newAccountnumber",
							censusRegisterFamily.getAccountNumber() + "来自"
									+ oldOrganization.getOrgName());
					map.put("shardCode", shardCode);
					// 更新户籍表户口号
					actualPopulationHandlerDAO
							.updateHouseholdStaffForAccountnumber(map);
					// 更新户籍家庭表户口号
					actualPopulationHandlerDAO
							.updateCensusregisterfamilysForAccountnumber(map);
				}
				log.appendSuccessDesc("更新户口号相同数据["
						+ censusRegisterFamilyList.size() + "]条");
			} else if ("groupfamily".equalsIgnoreCase(moduleTable
					.getTableName())) {

				Organization oldOrganization = organizationDubboService
						.getSimpleOrgById(orgMapping.getOldOrgId());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("oldOrgCode", orgMapping.getOldOrgCode());
				map.put("newOrgCode", orgMapping.getNewOrgCode());
				map.put("orgName", "来自" + oldOrganization.getOrgName());
				// 更新户籍表户口号
				int num = actualPopulationHandlerDAO
						.updateGroupfamilyForAccountnumber(map);
				log.appendSuccessDesc("更新家庭编号相同数据[" + num + "]条");

			}
			execute(orgMapping);
		}
		if (logger.isDebugEnabled())
			logger.debug("end executeMerge.");
	}

	private List<PopulationTypeBean> queryPopulationTypeByPopulationIdForList(
			Long id, String populationType) {
		PopulationTypeBean populationTypeBean = new PopulationTypeBean();
		populationTypeBean.setActualId(id);
		populationTypeBean.setActualType(populationType);
		return actualPopulationHandlerDAO
				.queryPopulationTypeByPopulationIdForList(populationTypeBean);
	}

	private void deleteoldPopulationType(OrgMapping orgMapping,
			PopulationTypeBean oldPopulationType,
			PopulationTypeBean newPopulationType) {

		// 严重精神障碍患者与吸毒人员监管措施处理
		if (BaseInfoTables.MENTALPATIENT_KEY.equals(oldPopulationType
				.getPopulationType())
				|| BaseInfoTables.DRUGGY_KEY.equals(oldPopulationType
						.getPopulationType())) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mentalpatientid", oldPopulationType.getPopulationId());
			map.put("type", oldPopulationType.getPopulationType().toUpperCase());
			map.put("newMentalpatientid", newPopulationType.getPopulationId());
			actualPopulationHandlerDAO.updateServiceSupervisionMeasures(map);
		}
		// 重点青少年人员类型
		if (BaseInfoTables.IDLEYOUTH_KEY.equals(oldPopulationType
				.getPopulationType())) {
			// 备份删除关系
			backupIdleYouthsHasPropertyDicts(orgMapping, oldPopulationType);

			actualPopulationHandlerDAO
					.deleteIdleYouthsHasPropertyDictsById(oldPopulationType
							.getPopulationId());
		}

		// 重点上访人员
		if (BaseInfoTables.SUPERIORVISIT_KEY.equals(oldPopulationType
				.getPopulationType())) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("visitid", oldPopulationType.getPopulationId());
			map.put("newVisitid", newPopulationType.getPopulationId());
			actualPopulationHandlerDAO.updateSuperiorVisitHistoryById(map);
		}

		// 残疾人残疾类型
		if (BaseInfoTables.HANDICAPPED_KEY.equals(oldPopulationType
				.getPopulationType())) {
			// 备份删除关系
			backupHandicappedsDisabilityType(orgMapping, oldPopulationType);

			actualPopulationHandlerDAO
					.deleteHandicappedsDisabilityTypeById(oldPopulationType
							.getPopulationId());
		}

		// 失业人员 意向
		if (BaseInfoTables.UNEMPLOYEDPEOPLE_KEY.equals(oldPopulationType
				.getPopulationType())) {
			// 备份就业意向
			backupPeopleHasEIntention(orgMapping, oldPopulationType);

			actualPopulationHandlerDAO
					.deletePeopleHasEIntentionById(oldPopulationType
							.getPopulationId());

			// 备份培训意向
			backupPeopleHasTIntention(orgMapping, oldPopulationType);
			actualPopulationHandlerDAO
					.deletePeopleHasTIntentionById(oldPopulationType
							.getPopulationId());
		}

		// 服务成员服务记录合并
		serviceMemberAndrecordsHandle(orgMapping, oldPopulationType,
				newPopulationType);

		// 备份删除业务人员对应的人房关系
		backupHouseHasImportantPopulation(orgMapping, oldPopulationType);
		// 删除业务人员对应的人房关系
		actualPopulationHandlerDAO
				.deleteHouseHasImportantPopulationByIdAndActualType(oldPopulationType);

		// 删除对应业务表中的数据
		if (PopulationTypeTable.populationTypeTable.get(oldPopulationType
				.getPopulationType()) != null) {
			// 备份删除数据
			String tableName = PopulationTypeTable.populationTypeTable
					.get(oldPopulationType.getPopulationType());
			// 老年人 育龄妇女分表..分表code增加
			if (BaseInfoTables.NURTURESWOMEN_KEY.equals(oldPopulationType
					.getPopulationType())
					|| BaseInfoTables.ELDERLYPEOPLE_KEY
							.equals(oldPopulationType.getPopulationType())) {
				tableName = tableName
						+ '_'
						+ shardConversion
								.getShardCode(orgMapping.getOldOrgId());
			}
			String whereConditions = "id = "
					+ oldPopulationType.getPopulationId();
			backupMonomer(orgMapping, tableName, whereConditions);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("table", tableName);
			map.put("id", oldPopulationType.getPopulationId());
			actualPopulationHandlerDAO.deletePopulationTypeTableById(map);
		}
		// 备份删除关系表中的数据
		backupPopulationType(orgMapping, oldPopulationType.getId());
		// 删除关系表中的数据
		actualPopulationHandlerDAO.deletePopulationTypeById(oldPopulationType
				.getId());
	}

	private void updateOldPopulationType(PopulationTypeBean oldPopulationType) {

		// 更新对应业务表中的数据关注状态
		if (PopulationTypeTable.populationTypeTable.get(oldPopulationType
				.getPopulationType()) != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("table", PopulationTypeTable.populationTypeTable
					.get(oldPopulationType.getPopulationType()));
			map.put("id", oldPopulationType.getPopulationId());
			actualPopulationHandlerDAO.updatePopulationTypeTableById(map);
		}
	}

	private void deleteHouseHasActualPopulationByIdAndActualType(
			OrgMapping orgMapping, Long id, String actualType) {
		HouseHasActualPopulation houseHasActualPopulation = new HouseHasActualPopulation();
		houseHasActualPopulation.setPopulationId(id);
		houseHasActualPopulation.setPopulationType(actualType);
		// 备份删除的人房关系
		backupHouseHasActualPopulation(orgMapping, houseHasActualPopulation);

		actualPopulationHandlerDAO
				.deleteHouseHasActualPopulationByIdAndActualType(houseHasActualPopulation);
	}

	private void populationTypeHandle(OrgMapping orgMapping, Long oldId,
			Long newId, String oldActualType, String newActualType) {
		// 取到新旧网格重复人员的 业务信息进行比对
		Boolean flg = false;
		List<PopulationTypeBean> oldPopulationTypeList = queryPopulationTypeByPopulationIdForList(
				oldId, oldActualType);
		List<PopulationTypeBean> newPopulationTypeList = queryPopulationTypeByPopulationIdForList(
				newId, newActualType);
		for (PopulationTypeBean oldPopulationType : oldPopulationTypeList) {
			flg = false;
			for (PopulationTypeBean newPopulationType : newPopulationTypeList) {
				// 社区矫正
				if (BaseInfoTables.POSITIVEINFO_KEY.equals(oldPopulationType
						.getPopulationType())) {
					if (BaseInfoTables.RECTIFICATIVEPERSON_KEY
							.equals(newPopulationType.getPopulationType())) {
						flg = true;
						updateOldPopulationType(oldPopulationType);
						// 备份删除关系表中的数据
						backupPopulationType(orgMapping,
								oldPopulationType.getId());
						// 删除关系表中的数据
						actualPopulationHandlerDAO
								.deletePopulationTypeById(oldPopulationType
										.getId());
						break;
					}
				}
				// 刑释解教
				if (BaseInfoTables.RECTIFICATIVEPERSON_KEY
						.equals(oldPopulationType.getPopulationType())) {
					if (BaseInfoTables.POSITIVEINFO_KEY
							.equals(newPopulationType.getPopulationType())) {
						flg = true;
						updateOldPopulationType(oldPopulationType);
						// 备份删除关系表中的数据
						backupPopulationType(orgMapping,
								oldPopulationType.getId());
						// 删除关系表中的数据
						actualPopulationHandlerDAO
								.deletePopulationTypeById(oldPopulationType
										.getId());
						break;
					}
				}
				if (oldPopulationType.getPopulationType().equals(
						newPopulationType.getPopulationType())) {
					flg = true;
					deleteoldPopulationType(orgMapping, oldPopulationType,
							newPopulationType);
					break;
				}
			}
			if (flg == false) {
				PopulationTypeBean updatePopulationTypeBean = new PopulationTypeBean();
				updatePopulationTypeBean.setId(oldPopulationType.getId());
				updatePopulationTypeBean.setActualId(newId);
				updatePopulationTypeBean.setActualType(newActualType);
				actualPopulationHandlerDAO
						.updatePopulationTypeById(updatePopulationTypeBean);
			}
		}
	}

	private void deleteUnsettledPopulationAndRelation(OrgMapping orgMapping,
			UnsettledPopulation unsettledPopulation) {
		// 备份删除的关系
		backupGroupFamilyByPopulation(orgMapping, unsettledPopulation.getId(),
				BaseInfoTables.UNSETTED_POPULATION);

		groupFamilyService
				.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
						unsettledPopulation.getId(),
						BaseInfoTables.UNSETTED_POPULATION,
						unsettledPopulation.getIdCardNo());

		// 切断A人房关系
		deleteHouseHasActualPopulationByIdAndActualType(orgMapping,
				unsettledPopulation.getId(), BaseInfoTables.UNSETTED_POPULATION);

		// 备份删除的数据
		String whereConditions = "id = " + unsettledPopulation.getId();
		backupMonomer(orgMapping, "unsettledPopulations", whereConditions);

		// 删除未落户表中
		actualPopulationHandlerDAO
				.deleteUnsettledPopulationById(unsettledPopulation.getId());
	}

	private void updateCensusregisterfamilysForIdCardNo(Long id, Long orgId,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		map.put("shardCode", shardCode);
		actualPopulationHandlerDAO.updateCensusregisterfamilysForIdCardNo(map);
	}

	private void serviceMemberAndrecordsHandle(OrgMapping orgMapping,
			PopulationTypeBean oldPopulationType,
			PopulationTypeBean newPopulationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oldObjecttype", oldPopulationType.getPopulationType());
		map.put("oldObjectid", oldPopulationType.getPopulationId());
		map.put("newObjecttype", newPopulationType.getPopulationType());
		map.put("newObjectid", newPopulationType.getPopulationId());

		// 处理服务记录合并
		actualPopulationHandlerDAO.updateServiceRecordRelyObject(map);
		// 处理监管人员合并
		actualPopulationHandlerDAO.updateServiceGuardersHasObject(map);
		// 处理服务成员合并

		// 备份删除的重复同一服务成员
		backupServiceMemberHasObject(orgMapping, map);
		// 1.删除重复同一服务成员
		actualPopulationHandlerDAO.deleteServiceMemberHasObject(map);

		// 2.更新服务成员合并
		actualPopulationHandlerDAO.updateServiceMemberHasObject(map);

		// 备份删除的重复同一服务团队
		backupServiceTeamHasObject(orgMapping, map);
		// 3.删除重复同一服务团队
		actualPopulationHandlerDAO.deleteServiceTeamHasObject(map);

		// 4.更新服务团队合并
		actualPopulationHandlerDAO.updateServiceTeamHasObject(map);
	}

	private List<ActualPopulation> queryRepeatDataForList(String oldTableName,
			String newTableName, Long oldOrgId, Long newOrgId) {
		return actualPopulationHandlerDAO.queryRepeatDataForList(createMap(
				oldTableName, newTableName, oldOrgId, newOrgId));
	}

	private List<UnsettledPopulation> queryUnsettledAndHouseholdStaffRepeatDataForList(
			String oldTableName, String newTableName, Long oldOrgId,
			Long newOrgId) {
		return actualPopulationHandlerDAO
				.queryUnsettledAndHouseholdStaffRepeatDataForList(createMap(
						oldTableName, newTableName, oldOrgId, newOrgId));
	}

	private List<UnsettledPopulation> queryUnsettledPopulationRepeatDataForList(
			String oldTableName, String newTableName, Long oldOrgId,
			Long newOrgId) {
		return actualPopulationHandlerDAO
				.queryUnsettledPopulationRepeatDataForList(createMap(
						oldTableName, newTableName, oldOrgId, newOrgId));
	}

	private List<OverseaPersonnel> queryOverseaPersonnelRepeatDataForList(
			String oldTableName, String newTableName, Long oldOrgId,
			Long newOrgId) {
		return actualPopulationHandlerDAO
				.queryOverseaPersonnelRepeatDataForList(createMap(oldTableName,
						newTableName, oldOrgId, newOrgId));
	}

	private List<CensusRegisterFamily> queryCensusRegisterFamilyRepeatDataForList(
			String oldTableName, String newTableName, Long oldOrgId,
			Long newOrgId) {
		return actualPopulationHandlerDAO
				.queryCensusRegisterFamilyRepeatDataForList(createMap(
						oldTableName, newTableName, oldOrgId, newOrgId));
	}

	private Map<String, Object> createMap(String oldTableName,
			String newTableName, Long oldOrgId, Long newOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oldTableName", oldTableName);
		map.put("newTableName", newTableName);
		map.put("oldOrgId", oldOrgId);
		map.put("newOrgId", newOrgId);
		return map;
	}

	private void householdStaffAndFloatingPopulationRepeatDataHandler(
			OrgMapping orgMapping, ActualPopulation repeatActualPopulation,
			ActualPopulation newActualPopulation, String oldActualType,
			String newActualType) {
		// 备份删除的关系
		backupGroupFamilyByPopulation(orgMapping,
				repeatActualPopulation.getId(), oldActualType);

		// 清楚A家庭关系处理
		groupFamilyService
				.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
						repeatActualPopulation.getId(), oldActualType,
						actualPopulationHandlerDAO
								.getIdCardNoById(repeatActualPopulation
										.getBaseInfoId()));

		// 业务处理
		populationTypeHandle(orgMapping, repeatActualPopulation.getId(),
				newActualPopulation.getId(), oldActualType, newActualType);

		// 切断A人房关系
		deleteHouseHasActualPopulationByIdAndActualType(orgMapping,
				repeatActualPopulation.getId(), oldActualType);

	}

	/***
	 * 删除的数据备份
	 */
	private void backupMonomer(OrgMapping orgMapping, String tableName,
			String whereConditions) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		String delSql = "select id dataId," + moduleTable.getOrgIdColumn()
				+ " dataOrgId,'' expansionData FROM " + tableName + " where "
				+ whereConditions;
		moduleTable.setSelectScript(delSql);
		// 记录处理模块的表名
		String moduleTableName = moduleTable.getTableName();
		// 设定备份表的表名
		orgMapping.getModuleTable().setTableName(tableName);
		backupBaseDataService.addBackupBaseData(orgMapping);
		// 还原处理模块的表表名
		orgMapping.getModuleTable().setTableName(moduleTableName);
	}

	/***
	 * 删除关系的数据备份
	 */
	private void backupRelation(OrgMapping orgMapping, String tableName,
			Long dataId, String expansionData) {
		BackupBaseData backupBaseData = new BackupBaseData();
		backupBaseData.setOrgChangeInfo(new OrgChangeInfo());
		backupBaseData.getOrgChangeInfo().setId(
				orgMapping.getOrgChangeInfo().getId());
		backupBaseData.setDataId(dataId);
		backupBaseData.setExpansionData(expansionData);
		backupBaseData.setTableName(tableName);
		backupBaseData.setOrganization(new Organization());
		backupBaseData.getOrganization().setId(orgMapping.getOldOrgId());
		backupBaseData.setCreateUser(orgMapping.getModuleTable()
				.getCreateUser());
		backupBaseData.setCreateDate(orgMapping.getModuleTable()
				.getCreateDate());
		backupBaseData.setUpdateUser(orgMapping.getModuleTable()
				.getUpdateUser());
		backupBaseData.setUpdateDate(orgMapping.getModuleTable()
				.getUpdateDate());
		backupBaseDataService.addBackupBaseDataRelation(backupBaseData);
	}

	private void backupGroupFamilyByPopulation(OrgMapping orgMapping, Long id,
			String oldActualType) {

		GroupFamilyHasPopulation groupFamilyHasPopulation = groupFamilyService
				.getGroupFamilyHasPopulationByPopulationIdAndPopulationType(id,
						oldActualType);
		if (groupFamilyHasPopulation != null) {
			String expansionData = "删除人与家庭的关系:"
					+ groupFamilyHasPopulation.getPopulationType() + "-"
					+ groupFamilyHasPopulation.getPopulationId() + "="
					+ "FAMILYRELATION:"
					+ groupFamilyHasPopulation.getFamilyRelation().getId()
					+ "," + "FAMILYID:"
					+ groupFamilyHasPopulation.getFamilyId();

			backupRelation(orgMapping, "groupfamilyhaspopulation",
					groupFamilyHasPopulation.getPopulationId(), expansionData);
		}

	}

	private void backupIdleYouthsHasPropertyDicts(OrgMapping orgMapping,
			PopulationTypeBean oldPopulationType) {

		List<Long> ids = actualPopulationHandlerDAO
				.queryIdleYouthsHasPropertyDictsForList(oldPopulationType
						.getPopulationId());
		if (ids != null && ids.size() > 0) {
			String expansionData = "删除青少年对应的人员类型:"
					+ oldPopulationType.getPopulationId() + "=" + ids;

			backupRelation(orgMapping, "IdleYouthsHasPropertyDicts",
					oldPopulationType.getPopulationId(), expansionData);
		}
	}

	private void backupHandicappedsDisabilityType(OrgMapping orgMapping,
			PopulationTypeBean oldPopulationType) {

		List<HandicappedSdisabilityType> handicappedSdisabilityTypeList = actualPopulationHandlerDAO
				.queryHandicappedSdisabilityTypeForList(oldPopulationType
						.getPopulationId());
		if (handicappedSdisabilityTypeList != null
				&& handicappedSdisabilityTypeList.size() > 0) {
			String handicappedHasTypes = "";
			for (HandicappedSdisabilityType handicappedSdisabilityType : handicappedSdisabilityTypeList) {
				handicappedHasTypes += "{HANDICAPPEDSTYPE:"
						+ handicappedSdisabilityType.getHandicappedstype()
						+ ",HANDICAPPEDSLEVEL:"
						+ handicappedSdisabilityType.getHandicappedslevel()
						+ "} ";
			}
			String expansionData = "删除残疾人对应的残疾类型:"
					+ oldPopulationType.getPopulationId() + "="
					+ handicappedHasTypes;

			backupRelation(orgMapping, "HandicappedsDisabilityType",
					oldPopulationType.getPopulationId(), expansionData);
		}
	}

	private void backupPeopleHasEIntention(OrgMapping orgMapping,
			PopulationTypeBean oldPopulationType) {

		List<Long> ids = actualPopulationHandlerDAO
				.queryPeopleHasEIntentionForList(oldPopulationType
						.getPopulationId());

		if (ids != null && ids.size() > 0) {
			String expansionData = "删除失业人员对应的就业意向:"
					+ oldPopulationType.getPopulationId() + "=" + ids;

			backupRelation(orgMapping, "uPeopleHasEIntention",
					oldPopulationType.getPopulationId(), expansionData);
		}
	}

	private void backupPeopleHasTIntention(OrgMapping orgMapping,
			PopulationTypeBean oldPopulationType) {

		List<Long> ids = actualPopulationHandlerDAO
				.queryPeopleHasTIntentionForList(oldPopulationType
						.getPopulationId());

		if (ids != null && ids.size() > 0) {
			String expansionData = "删除失业人员对应的培训意向:"
					+ oldPopulationType.getPopulationId() + "=" + ids;

			backupRelation(orgMapping, "uPeopleHasTIntention",
					oldPopulationType.getPopulationId(), expansionData);
		}
	}

	private void backupServiceMemberHasObject(OrgMapping orgMapping, Map map) {
		List<ServiceMemberWithObject> serviceMemberWithObjectList = actualPopulationHandlerDAO
				.queryServiceMemberWithObjectForList(map);
		if (serviceMemberWithObjectList != null
				&& serviceMemberWithObjectList.size() > 0) {
			for (ServiceMemberWithObject serviceMemberWithObject : serviceMemberWithObjectList) {
				String expansionData = "删除人员所对应的服务成员:OBJECTTYPE:"
						+ serviceMemberWithObject.getObjectType()
						+ "-OBJECTID:" + serviceMemberWithObject.getObjectId()
						+ "=TEAMID:" + serviceMemberWithObject.getTeamId()
						+ ",MEMBERID:" + serviceMemberWithObject.getMemberId();
				backupRelation(orgMapping, "serviceMemberHasObject",
						serviceMemberWithObject.getId(), expansionData);
			}
		}
	}

	private void backupServiceTeamHasObject(OrgMapping orgMapping, Map map) {
		List<ServiceTeamHasObjects> serviceTeamHasObjectsList = actualPopulationHandlerDAO
				.queryServiceTeamHasObjectsForList(map);
		if (serviceTeamHasObjectsList != null
				&& serviceTeamHasObjectsList.size() > 0) {
			for (ServiceTeamHasObjects serviceTeamHasObjects : serviceTeamHasObjectsList) {
				String expansionData = "删除服务团队和服务对象的关联关系表:OBJECTTYPE:"
						+ serviceTeamHasObjects.getObjectType() + "-OBJECTID:"
						+ serviceTeamHasObjects.getObjectId() + "=TEAMID:"
						+ serviceTeamHasObjects.getTeamId() + ",MEMBERID:"
						+ serviceTeamHasObjects.getMemberId();
				backupRelation(orgMapping, "serviceTeamHasObject",
						serviceTeamHasObjects.getId(), expansionData);
			}
		}
	}

	private void backupHouseHasImportantPopulation(OrgMapping orgMapping,
			PopulationTypeBean oldPopulationType) {
		List<Long> ids = actualPopulationHandlerDAO
				.queryHouseHasImportantPopulationForList(oldPopulationType);
		if (ids != null && ids.size() > 0) {
			String expansionData = "删除业务人员对应的人房关系:"
					+ oldPopulationType.getPopulationType() + "-"
					+ oldPopulationType.getPopulationId() + "=" + ids;

			backupRelation(orgMapping, "houseHasImportantPopulation",
					oldPopulationType.getPopulationId(), expansionData);
		}
	}

	private void backupPopulationType(OrgMapping orgMapping, Long id) {
		PopulationTypeBean populationType = actualPopulationHandlerDAO
				.getPopulationTypeBeanById(id);
		if (populationType != null) {
			String expansionData = "删除实口与业务关系:"
					+ populationType.getActualType() + "-"
					+ populationType.getActualId() + "="
					+ populationType.getPopulationType() + "-"
					+ populationType.getPopulationId();

			backupRelation(orgMapping, "populationTypes",
					populationType.getId(), expansionData);
		}
	}

	private void backupHouseHasActualPopulation(OrgMapping orgMapping,
			HouseHasActualPopulation houseHasActualPopulation) {
		List<Long> ids = actualPopulationHandlerDAO
				.queryHouseHasActualPopulationForList(houseHasActualPopulation);
		if (ids != null && ids.size() > 0) {
			String expansionData = "删除实有人口对应的人房关系:"
					+ houseHasActualPopulation.getPopulationType() + "-"
					+ houseHasActualPopulation.getPopulationId() + "=" + ids;

			backupRelation(orgMapping, "houseHasActualPopulation",
					houseHasActualPopulation.getPopulationId(), expansionData);

			// 房屋人数
			for (Long id : ids) {
				String shardCode = IdConversionShardUtil.getShardCodeById(id);
				actualHouseService.updateHouseInfoMemberNum(shardCode, id, -1);
			}
		}
	}
}
