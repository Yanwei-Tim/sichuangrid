package com.tianque.baseInfo.druggy.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.domain.vo.SearchDruggyVo;

@Repository("druggyDao")
public class DruggyDaoImpl extends BaseInfoPopulationBaseDaoImpl<Druggy, SearchDruggyVo> implements
		DruggyDao {

	@Override
	public void deleteServiceSupervisionMeasuresByIdAndType(Long id,
			String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id+"");
		map.put("type", type);
		getSqlMapClientTemplate().delete("mentalPatient.deleteServiceSupervisionMeasuresByIdAndType", map);
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("druggys", id);
	}

}
