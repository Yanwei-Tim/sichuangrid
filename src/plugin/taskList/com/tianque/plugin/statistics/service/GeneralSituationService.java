package com.tianque.plugin.statistics.service;

import java.util.List;

import com.tianque.plugin.statistics.domain.GeneralSituation;
import com.tianque.plugin.statistics.vo.GeneralStituationSearchVo;

public interface GeneralSituationService {

	public void createGeneralSituationByDate(Integer year, Integer month,Integer quarter,
			Integer createDataType,Integer yearType);

	public List<GeneralSituation> getTaskListOfColumn(
			GeneralStituationSearchVo generalStituationSearchVo,
			String searchType);

	public List<Object[]> getTaskListOfPie(
			GeneralStituationSearchVo generalStituationSearchVo);

	// /**
	// * 根据条件查询统计报表
	// * @param generalStituationSearchVo 查询条件封装类
	// * @return
	// */
	// public List<GeneralSituation>
	// getStatisticInfoByConditions(GeneralStituationSearchVo
	// generalStituationSearchVo);

	// /***
	// * 饼状图统计
	// */
	// public List<Object[]> getTaskListStatisticsPie(GeneralStituationSearchVo
	// generalStituationSearchVo);
}
