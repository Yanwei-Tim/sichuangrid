package com.tianque.plugin.orgchange.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;

@Component("tenHouseholdsJointHandler")
public class TenHouseholdsJointHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(TenHouseholdsJointHandler.class);

	private final static String UPDATE_ID_CODE_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN# = #OLDORGID#";
	private final static String DELETE_ID_SQL = "DELETE #TABLENAME# WHERE ID= #OLDID#";
	private final static String BACKUP_DELETE_SQL = "select id dataId,#OLDORGID# dataOrgId,'' expansionData from #TABLENAME# where ID= #OLDID#";
	private final static String FIND_REPEATDATA_IDS_SQL = "select t1.id ||','||t2.id AS RESULT from (select id,teamCode from #TABLENAME# where #ORGIDCOLUMN# = #OLDORGID#) t1,(select id,teamCode from #TABLENAME# where #ORGIDCOLUMN# = #NEWORGID#) t2 where t1.teamCode = t2.teamCode ";

	@Override
	public void backup(OrgMapping orgMapping) {
	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		String updateSql = orgMapping.getModuleTable().getUpdateScript();
		if (!StringUtil.isStringAvaliable(updateSql)) {
			updateSql = UPDATE_ID_CODE_SQL;
		}
		updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
		int num = commonHandlerDAO.updateData(updateSql);
		log.appendSuccessDesc("更新表【"
				+ orgMapping.getModuleTable().getTableName() + "】数据量[" + num
				+ "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end transfer.");
		}
	}

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.orgMapping:{}", orgMapping);
		}
		if (!orgMapping.getNewOrgId().equals(orgMapping.getOldOrgId())) {
			List<String> ids = findRepeatDataIds(orgMapping);
			if (ids != null) {
				for (int i = 0; i < ids.size(); i++) {
					Map<String, Long> idMap = toIdsMap(ids.get(i));
					updateFamilyTeam(orgMapping, idMap);
				}
			}
		}
		transfer(orgMapping);
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.");
		}
	}

	private Map<String, Long> toIdsMap(String oldIdAndNewId) {
		String[] ids = oldIdAndNewId.split(",");
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("oldId",
				(ids != null && ids.length > 0) ? Long.parseLong(ids[0]) : null);
		map.put("newId",
				(ids != null && ids.length > 1) ? Long.parseLong(ids[1]) : null);
		return map;
	}

	private List<String> findRepeatDataIds(OrgMapping orgMapping) {
		String repeatIdsSQL = orgMapping.getModuleTable().getDeleteScript();
		if (!StringUtil.isStringAvaliable(repeatIdsSQL)) {
			repeatIdsSQL = FIND_REPEATDATA_IDS_SQL;
		}
		repeatIdsSQL = OrgChangeUtils.replaceScript(orgMapping, repeatIdsSQL);
		return commonHandlerDAO.queryStringForList(repeatIdsSQL);
	}

	/** 分组列表 */
	private void updateFamilyTeam(OrgMapping orgMapping,
			Map<String, Long> ids) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		Long oldId = ids.get("oldId");
		Long newId = ids.get("newId");
		String teamCodeAppend = "来自"+orgMapping.getOldOrgName();
		String updateSql = "UPDATE familyteam SET teamCode=teamCode||'"+teamCodeAppend+"',orgid= #NEWORGID# ,orginternalcode=#NEWORGCODE# WHERE id = #OLDID#";
		updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
		updateSql = replaceOldIdAndNewId(updateSql, oldId, newId);
		int num = commonHandlerDAO.updateData(updateSql);
		log.appendSuccessDesc("更新活动记录表【activityRecords】数据量[" + num + "]");
	}

	private String replaceOldIdAndNewId(String sql, Long oldId, Long newId) {
		if (StringUtil.isStringAvaliable(sql)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("#OLDID#", oldId + "");
			map.put("#NEWID#", newId + "");
			sql = OrgChangeUtils.replaceMapValue(map, sql);
		}
		return sql;
	}
}
