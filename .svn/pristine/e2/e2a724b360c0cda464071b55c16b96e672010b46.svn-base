package com.tianque.plugin.analyzing.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.BasicOrgType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.analyzing.domain.PrimaryOrganizationDataColumnTwoVo;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * 统计各类队伍信息
 * 
 * @author wangshirui
 */
@Repository("searchPrimaryOrganizationDataColumnDao")
public class SearchPrimaryOrganizationDataColumnDaoImpl extends AbstractBaseDao
		implements SearchPrimaryOrganizationDataColumnDao {

	@Autowired
	private PropertyDictDubboService propertyDictDubboService;

	/**
	 * 根据内部代码，统计队伍信息
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PrimaryOrganizationDataColumnTwoVo> searchPrimaryOrganizationDataColumnByOrgInternalCode(
			Long parentOrgId) {
		// SearchPrimaryOrganizationDataColumnVo
		// searchPrimaryOrganizationDataColumnVo = new
		// SearchPrimaryOrganizationDataColumnVo();
		// searchPrimaryOrganizationDataColumnVo
		// .setOrgInternalCode(orgInternalCode);
		// return getSqlMapClientTemplate()
		// .queryForList(
		// "searchPrimaryOrganizationDataColumnVo.getPrimaryOrganizationDataColumnVo",
		// searchPrimaryOrganizationDataColumnVo);
		Map<String, Object> map = getSearchMap(parentOrgId);

		return getSqlMapClientTemplate()
				.queryForList(
						"searchPrimaryOrganizationDataNewColumnVo.getPrimaryOrganizationDataColumnVo",
						map);
	}

	private Map<String, Object> getSearchMap(Long parentOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentOrgId", parentOrgId);
		map.put("partyCommitteeTeamClass",
				getPropertyDictId(PropertyTypes.TEAMCLAZZ,
						BasicOrgType.DEPARTMENTPARTY));
		map.put("partyCommitteeTeamTypeOne",
				getPropertyDictId(PropertyTypes.DEPARTMENTPARTY_TYPE,
						BasicOrgType.ZOGNZHIBAN));
		map.put("partyCommitteeTeamTypeTwo",
				getPropertyDictId(PropertyTypes.DEPARTMENTPARTY_TYPE,
						BasicOrgType.ZOGNZHIWEI));
		map.put("partyCommitteeTeamTypeThree",
				getPropertyDictId(PropertyTypes.DEPARTMENTPARTY_TYPE,
						BasicOrgType.ZONGZHICHENGYUANDANWEI));
		map.put("partyCommitteeTeamTypeFour",
				getPropertyDictId(PropertyTypes.DEPARTMENTPARTY_TYPE,
						BasicOrgType.ZHUANXIANGGONGZUOLINGDAOXIAOZU));
		map.put("partyTeamClass",
				getPropertyDictId(PropertyTypes.TEAMCLAZZ,
						BasicOrgType.BASICLEVELPARTY));
		map.put("autonomyTeamClass",
				getPropertyDictId(PropertyTypes.TEAMCLAZZ,
						BasicOrgType.AUTONOMYORG));
		map.put("masseDutyTeamClass",
				getPropertyDictId(PropertyTypes.TEAMCLAZZ,
						BasicOrgType.MASSTREATTEAM));
		map.put("postulantTeamClass",
				getPropertyDictId(PropertyTypes.TEAMCLAZZ,
						BasicOrgType.LEADERGROUP));
		return map;
	}

	private Long getPropertyDictId(String propertyDomainName,
			String dictDisplayName) {
		PropertyDict propertyDict = propertyDictDubboService
				.findPropertyDictByDomainNameAndDictDisplayName(
						propertyDomainName, dictDisplayName);
		return propertyDict != null ? propertyDict.getId() : null;
	}

}
