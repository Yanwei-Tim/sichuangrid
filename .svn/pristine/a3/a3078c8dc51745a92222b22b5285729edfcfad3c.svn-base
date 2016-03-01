package com.tianque.issue.migrateData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;

@Transactional
@Repository("migrationIssueData")
public class MigrationIssueData extends SqlMapClientDaoSupport {

	@Resource(name = "sqlMapClient")
	public void setSqlMapClientBase(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	public List<IssueMigrationVo> getHaveEmergencyLevelData() {
		return getSqlMapClientTemplate().queryForList(
				"migrationIssueData.getHaveEmergencyLevelData");
	}

	public void updateIssuestepEmergencyLevel(IssueMigrationVo issueMigrationVo) {
		getSqlMapClientTemplate().update(
				"migrationIssueData.updateIssuestepEmergencyLevel",
				issueMigrationVo);
	}

	public List<Long> getIssueIdsFromIssuehastypeByDomainid(
			Long contradictionDomainId, Long securitytroubleDomainId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("contradictionDomainId", contradictionDomainId);
		map.put("securitytroubleDomainId", securitytroubleDomainId);
		return getSqlMapClientTemplate()
				.queryForList(
						"migrationIssueData.getIssueIdsFromIssuehastypeByDomainid",
						map);
	}

	public List<IssueHasType> getIssueHasTypeByIssueId(Long issueId) {
		return getSqlMapClientTemplate().queryForList(
				"migrationIssueData.getIssueHasTypeByIssueId", issueId);
	}

	public void deleteIssueHasTypeByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"migrationIssueData.deleteIssueHasTypeByIssueId", issueId);
	}

	public void addIssuehastypes(IssueHasType issueHasType) {
		getSqlMapClientTemplate().insert("migrationIssueData.addIssuehastypes",
				issueHasType);
	}

	public void addIssueHandleStat(IssueAreaStat issueAreaStat) {
		getSqlMapClientTemplate().insert(
				"migrationIssueData.addIssueHandleStat", issueAreaStat);
	}

	public void addIssueClassificationStat(IssueAreaStat issueAreaStat) {
		getSqlMapClientTemplate().insert(
				"migrationIssueData.addIssueClassificationStat", issueAreaStat);
	}

	public void addIssueStepStat(IssueAreaStat issueAreaStat) {
		getSqlMapClientTemplate().insert("migrationIssueData.addIssueStepStat",
				issueAreaStat);
	}
}
