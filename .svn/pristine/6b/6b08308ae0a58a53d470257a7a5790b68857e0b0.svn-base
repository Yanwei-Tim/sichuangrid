package com.tianque.plugin.dataManage.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;

@Repository("dataManageDBJobDao")
public class DataManageDBJobDaoImpl extends AbstractBaseDao implements DataManageDBJobDao {

	@Override
	public int countUnDisposeData(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dataManageDBJob.countUnDisposeData", map);
	}

	@Override
	public void updateCanClaimData(Map<String, Object> map) {
		getSqlMapClientTemplate().update("dataManageDBJob.updateCanClaimData", map);
	}

	@Override
	public Map<String, Object> getRepeatData(Map<String, Object> queryMap) {
		List<Map<String, Object>> results = (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList(
				"dataManageDBJob.getRepeatData", queryMap);
		if(results != null && results.size() > 0){
			return results.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void updateRepeatData(Map<String, Object> queryMap) {
		getSqlMapClientTemplate().update("dataManageDBJob.updateRepeatData", queryMap);
	}

	@Override
	public List<Map> getUnDisposeData(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("dataManageDBJob.getUnDisposeData", map);
	}

	@Override
	public void updateBeClaimRepeatData(Map<String, Object> queryMap) {
		getSqlMapClientTemplate().update("dataManageDBJob.updateBeClaimRepeatData", queryMap);
	}

}
