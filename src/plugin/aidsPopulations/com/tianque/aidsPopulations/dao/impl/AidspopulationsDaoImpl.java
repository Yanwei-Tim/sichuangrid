package com.tianque.aidsPopulations.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.aidsPopulations.dao.AidspopulationsDao;
import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.domain.vo.SearchAidspopulationsVo;
import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;

/**
 * :数据操作层
 * 
 * @author liaoshanshan
 * @date 2013-06-09 16:34:13
 */
@Repository("aidspopulationsDao")
public class AidspopulationsDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<Aidspopulations, SearchAidspopulationsVo>
		implements AidspopulationsDao {

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("aidspopulations", id);
	}

}
