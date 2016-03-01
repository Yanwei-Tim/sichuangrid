package com.tianque.baseInfo.idleYouth.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.idleYouth.domain.IdleYouthsHasPropertyDicts;

@DynamicIbatisDAO(value = "IdleYouthsHasPropertyDictsDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("IdleYouthsHasPropertyDictsDAO")
public interface IdleYouthsHasPropertyDictsDAO {
	public void updateIdleYouthsHasPropertyDictsByIdleYouthId(
			Map<String, Object> map);

	public List<IdleYouthsHasPropertyDicts> queryIdleYouthsHasPropertyDictsByIdleYouthIdForList(
			Long idleYouthId);

	public void deleteIdleYouthsHasPropertyDictsByIdleYouthId(Long idleYouthId);

	public void addIdleYouthsHasPropertyDicts(
			IdleYouthsHasPropertyDicts idleYouthsHasPropertyDicts);
}
