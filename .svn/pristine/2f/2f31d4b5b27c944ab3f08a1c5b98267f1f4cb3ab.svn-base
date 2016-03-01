package com.tianque.plugin.orgchange.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeams;
import com.tianque.fourTeams.fourTeamsManage.service.FourTeamsService;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.plugin.orgchange.service.BackupBaseDataService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("fourteamHandler")
public class FourteamHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(FourteamHandler.class);

	@Autowired
	private FourTeamsService fourteamsService;
	@Autowired
	private BackupBaseDataService backupBaseDataService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin transfer.");
		execute(orgMapping);
		if (logger.isDebugEnabled())
			logger.debug("end transfer.");
	}

	public void execute(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			if (logger.isDebugEnabled())
				logger.debug("begin execute.orgMapping:{}", orgMapping);

		if (orgMapping.isHasData()) {
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			// 查询源网格队伍数据
			List<FourTeams> oldFourteamsList = getFourteamsByOrganizations(
					orgMapping.getOldOrgId(), orgMapping.getOldOrgCode());

			// 如果执行合并操作，考虑数据重复
			if (OrgChangeUtils.MERGE == orgMapping.getOrgChangeInfo()
					.getOperateType()
					&& !(orgMapping.getOldOrgId().equals(orgMapping
							.getNewOrgId()))) {

				// 查询目标网格队伍数据
				List<FourTeams> newFourteamsList = getFourteamsByOrganizations(
						orgMapping.getNewOrgId(), orgMapping.getNewOrgCode());

				// 需要删除的四支队伍数据
				List<FourTeams> removeList = new ArrayList<FourTeams>();
				// 队伍类型不同 名称重复的数据
				List<FourTeams> updateList = new ArrayList<FourTeams>();

				Map<Long, Long> megeMap = new HashMap<Long, Long>();// 存放需要被删除的队伍ID以及对应的目标网格重复ID
				if (oldFourteamsList != null && oldFourteamsList.size() != 0
						&& newFourteamsList != null
						&& newFourteamsList.size() != 0) {

					removeList = checkRepeatDataForFourteams(oldFourteamsList,
							newFourteamsList, megeMap);

					updateList = checkUpdateDataForFourteams(oldFourteamsList,
							newFourteamsList);

				}
				if (removeList.size() != 0) {
					// 数据备份
					backup(orgMapping, getStringDataByList(removeList));

					// 将重复队伍的成员合并
					megerTeamMember(getStringDataByList(removeList), megeMap,
							log);
					// 删除重复队伍数据
					deleteRepeatInfo(getStringDataByList(removeList), log);
				}
				if (updateList.size() != 0) {
					Organization oldOrganization = organizationDubboService
							.getSimpleOrgById(orgMapping.getOldOrgId());
					// 更新队伍类型不一致 名称重复的数据 队伍名称+来自某某网格
					for (FourTeams fourTeam : updateList) {
						fourteamsService.updateFourTeamsNameByIds(
								fourTeam.getNames() + "来自"
										+ oldOrganization.getOrgName(),
								fourTeam.getId());
					}
				}

			}

			// 获得需要修改的队伍信息ID
			List<String> ids = new ArrayList<String>();
			if (oldFourteamsList != null && oldFourteamsList.size() != 0) {
				ids = getFourteamsIds(oldFourteamsList);
			}
			int num = 0;
			if (ids != null && ids.size() != 0) {
				// 执行数据ORG信息更改
				num = fourteamsService.updateFourteamsOrganizationByIds(
						orgMapping.getNewOrgId(), orgMapping.getNewOrgCode(),
						ids);
			}

			if (num != ids.size()) {
				log.setErrorDesc(log.getDescription()
						+ "四支队伍数据更新错误：需要更新条数和实际更新条数不一致");
				throw new OrgChangeHandlerException(log);
			} else {
				log.appendSuccessDesc("四支队伍数据更新成功，更新数据量为[" + num + "]条");
			}
		}
		if (logger.isDebugEnabled())
			logger.debug("end execute.");
	}

	/***
	 * 将listId串成字符串数组
	 * 
	 */
	private String[] getStringDataByList(List<FourTeams> removeList) {
		if (removeList.size() != 0) {
			List<String> idsList = getFourteamsIds(removeList);
			String[] deleteIds = new String[idsList.size()];
			if (idsList != null && idsList.size() != 0) {
				for (int i = 0; i < idsList.size(); i++) {
					deleteIds[i] = idsList.get(i);
				}
			}
			return deleteIds;
		}
		return null;
	}

	/***
	 * 删除重复的队伍信息
	 */
	private void deleteRepeatInfo(String[] deleteIds, OrgChangeLog log) {
		// 将需要删除的数据删除 并且将相关联的成员信息删除
		try {
			if (deleteIds != null && deleteIds.length != 0) {
				// 删除队伍数据以及所属成员数据
				int teamNum = fourteamsService.deleteFourteams(deleteIds);
				log.appendSuccessDesc("四支队伍删除数据量为[" + teamNum + "]条");
				// int memberNum = fourteamsService
				// .deleteFourteamsMemberByTeamId(deleteIds);
				// log.appendSuccessDesc("根据队伍删除成员数据量为[" + memberNum + "]条");
			}
		} catch (Exception e) {
			log.appendErrorDesc("删除四支队伍信息数据异常，异常信息：" + e.getMessage());
			throw new OrgChangeHandlerException(log);
		}
	}

	/***
	 * 将重复队伍的成员合并至目标队伍
	 */
	private void megerTeamMember(String[] deleteIds, Map<Long, Long> megeId,
			OrgChangeLog log) {
		try {
			if (deleteIds != null && deleteIds.length != 0 && megeId != null) {
				int num = 0;
				for (String id : deleteIds) {
					Long targetId = megeId.get(Long.parseLong(id));
					if (targetId != null) {
						int memberNumber = 0;
						String updateSql = "update FOURTEAMMEMBERS set teamId = "
								+ targetId + " where teamId=" + id;
						memberNumber = commonHandlerDAO.updateData(updateSql);
						String updateTeamSql = "update FOURTEAMS set memberNumber = memberNumber+"
								+ memberNumber + " where id =" + targetId;
						commonHandlerDAO.updateData(updateTeamSql);
						num += memberNumber;
					}
				}
				log.appendSuccessDesc("合并四支队伍成员信息影响数据量为:[" + num + "]条");
			}
		} catch (Exception e) {
			log.appendErrorDesc("合并四支队伍成员信息数据异常，异常信息：" + e.getMessage());
			throw new OrgChangeHandlerException(log);
		}
	}

	/***
	 * 删除的数据备份
	 */
	private void backup(OrgMapping orgMapping, String[] deleteIds) {
		if (deleteIds != null && deleteIds.length != 0) {

			ModuleTable moduleTable = orgMapping.getModuleTable();
			String delTeamIds = OrgChangeUtils.getStringByData(deleteIds);
			String delSql = "select id dataId,"
					+ moduleTable.getOrgIdColumn()
					+ " dataOrgId,'' expansionData FROM fourteams where id in ("
					+ delTeamIds + ")";
			moduleTable.setSelectScript(delSql);
			backupBaseDataService.addBackupBaseData(orgMapping);

			// Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
			// for (String id : deleteIds) {
			// List<FourTeamMembers> list = fourteamsService
			// .findMemberByTeamId(id);
			// List<Long> memberIds = getMemberIds(list);
			// map.put(Long.parseLong(id), memberIds);
			// }
			// String expansionData = "删除四支队伍成员与队伍关系数据队列：" + map;
			// String delMemberScript = "select id dataId,"
			// + moduleTable.getOrgIdColumn() + " dataOrgId,'"
			// + expansionData
			// + "' expansionData FROM  fourteammembers where teamId in ("
			// + delTeamIds + ")";
			// moduleTable.setSelectScript(delMemberScript);
			// moduleTable.setTableName("FOURTEAMMEMBERS");
			// backupBaseDataService.addBackupBaseData(orgMapping);
		}

	}

	/***
	 * 对比两个层级的ORG数据名称重复 类型一致，有则将源队伍数据删除，没有则转移
	 * 
	 * @param orgId
	 * @param orgCode
	 * @return
	 */
	private List<FourTeams> checkRepeatDataForFourteams(
			List<FourTeams> oldFourteamsList, List<FourTeams> newFourteamsList,
			Map<Long, Long> megeMap) {
		List<FourTeams> removeList = new ArrayList<FourTeams>();
		for (FourTeams oldFourteams : oldFourteamsList) {
			for (FourTeams newFourteams : newFourteamsList) {
				if (oldFourteams.getNames().equals(newFourteams.getNames())
						&& oldFourteams.getTeamType().equals(
								newFourteams.getTeamType())) {
					removeList.add(oldFourteams);
					megeMap.put(oldFourteams.getId(), newFourteams.getId());
				}
			}
		}
		if (removeList.size() != 0) {
			oldFourteamsList.removeAll(removeList);
		}

		return removeList;
	}

	/***
	 * 对比两个层级的ORG数据名称重复 类型不一致
	 * 
	 * @param orgId
	 * @param orgCode
	 * @return
	 */
	private List<FourTeams> checkUpdateDataForFourteams(
			List<FourTeams> oldFourteamsList, List<FourTeams> newFourteamsList) {
		List<FourTeams> updateList = new ArrayList<FourTeams>();
		for (FourTeams oldFourteams : oldFourteamsList) {
			for (FourTeams newFourteams : newFourteamsList) {
				if (oldFourteams.getNames().equals(newFourteams.getNames())
						&& !oldFourteams.getTeamType().equals(
								newFourteams.getTeamType())) {
					updateList.add(oldFourteams);
				}
			}
		}
		return updateList;
	}

	/***
	 * 将需要更新的数据的ID串成list
	 * 
	 * @param orgId
	 * @param orgCode
	 * @return
	 */
	private List<String> getFourteamsIds(List<FourTeams> oldFourteamsList) {
		List<String> ids = new ArrayList<String>();
		for (FourTeams fourteams : oldFourteamsList) {
			if (fourteams.getId() != null) {
				ids.add(fourteams.getId().toString());
			}
		}
		return ids;
	}

	/***
	 * 根据组织机构ID和Code查询四支队伍数据
	 * 
	 * @param orgId
	 * @param orgCode
	 * @return
	 */
	private List<FourTeams> getFourteamsByOrganizations(Long orgId,
			String orgCode) {
		return fourteamsService.findteamsByOrgIdAndOrgCode(orgId, orgCode);
	}

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin transfer.");
		execute(orgMapping);
		if (logger.isDebugEnabled())
			logger.debug("end transfer.");
	}

}
