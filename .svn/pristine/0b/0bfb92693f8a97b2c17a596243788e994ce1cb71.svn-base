package com.tianque.report.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tianque.dao.IssueInvestigationMediateDao;
import com.tianque.domain.IssueInvestigationMediate;
import com.tianque.domain.IssueType;
import com.tianque.report.vo.IssueInvestigationMediateItemVo;
import com.tianque.report.vo.IssueInvestigationMediateVo;

public class IssueInvestigationMediateVoBuilder {
	private static final String SUCC_COUNT = "成功数";
	private static final String SUCC_RATE = "成功率";

	private static final String OTHER_NAME = "开展集中排查";
	private static final String OTHER_SUBSET = "次数";

	private static final String TOTAL_NAME = "总况";
	// private static final String OTHER_SUBSET="次数";
	private List<IssueInvestigationMediate> monthDatas;

	private List<IssueType> allContradictionTypes;

	private String orgCode;

	private int month;

	private int year;

	private long cityInputCount = 0;
	private long citydealCount = 0;
	private long districtInputCount = 0;
	private long districtdealCount = 0;
	private long townInputCount = 0;
	private long towndealCount = 0;
	private long gridInputCount = 0;
	private long griddealCount = 0;
	private long inputSubCount = 0;
	private long dealSubCount = 0;
	private long inputTotalCount = 0;
	private long dealTotalCount = 0;

	private IssueInvestigationMediateDao dao;

	private IssueInvestigationMediateVo importantData;

	public IssueInvestigationMediateVoBuilder(String orgInternalCode, int year, int month,
			IssueInvestigationMediateDao dao) {
		this.orgCode = orgInternalCode;
		this.year = year;
		this.month = month;
		this.dao = dao;
	}

	public void setAllContradictionTypes(List<IssueType> contradictionTypes) {
		allContradictionTypes = contradictionTypes;
		Collections.sort(allContradictionTypes, new Comparator<IssueType>() {
			@Override
			public int compare(IssueType o1, IssueType o2) {
				int result = Boolean.valueOf(o1.isEnabled()).compareTo(o2.isEnabled());
				if (result == 0) {
					result = Integer.valueOf(o1.getIndexId()).compareTo(o2.getIndexId());
				}
				return result;
			}
		});
	}

	public void setMonthDatas(List<IssueInvestigationMediate> exsitedMonthDatas) {
		monthDatas = exsitedMonthDatas;
	}

	public List<IssueInvestigationMediateVo> getResult() {
		List<IssueInvestigationMediateVo> results = createBlankIssueInvestigationMediateVos();
		fillStatDataUseExsitedData(results);
		appendRemainExsitedData(results);
		insertTotalData(results);
		appendOtherBlankRecord(results);
		return results;
	}

	private void insertTotalData(List<IssueInvestigationMediateVo> results) {
		IssueInvestigationMediateVo vo = new IssueInvestigationMediateVo();
		vo.setTypeDisplayName(TOTAL_NAME);
		IssueInvestigationMediateItemVo totalInputItem = createBlankIssueInvestigationMediateItemVo(IssueInvestigationMediate.INPUT_ITEM);
		totalInputItem.setCity(cityInputCount);
		totalInputItem.setDistrict(districtInputCount);
		totalInputItem.setTown(townInputCount);
		totalInputItem.setGrid(gridInputCount);
		totalInputItem.setSubtotal(inputSubCount);
		totalInputItem.setAnnualTotal(inputTotalCount);
		vo.additem(totalInputItem);
		IssueInvestigationMediateItemVo totalDealItem = createBlankIssueInvestigationMediateItemVo(IssueInvestigationMediate.DEAL_ITEM);
		totalDealItem.setCity(citydealCount);
		totalDealItem.setDistrict(districtdealCount);
		totalDealItem.setTown(towndealCount);
		totalDealItem.setGrid(griddealCount);
		totalDealItem.setSubtotal(dealSubCount);
		totalDealItem.setAnnualTotal(dealTotalCount);
		vo.additem(totalDealItem);
		vo.additem(createBlankIssueInvestigationMediateItemVo("调处率"));
		vo.additem(createBlankIssueInvestigationMediateItemVo(SUCC_COUNT));
		vo.additem(createBlankIssueInvestigationMediateItemVo(SUCC_RATE));
		results.add(0, vo);
	}

	private void appendRemainExsitedData(List<IssueInvestigationMediateVo> results) {
		String lastedVoName = "";
		IssueInvestigationMediateVo lastVo = null;
		for (IssueInvestigationMediate data : monthDatas) {
			if (!lastedVoName.equals(data.getTypeName())) {
				lastedVoName = data.getTypeName();
				lastVo = new IssueInvestigationMediateVo();
				lastVo.setTypeDisplayName(data.getTypeName());
				results.add(lastVo);
			}
			lastVo.additem(createIssueInvestigationMediateItemVo(lastVo, data));
		}
	}

	private IssueInvestigationMediateItemVo createIssueInvestigationMediateItemVo(
			IssueInvestigationMediateVo vo, IssueInvestigationMediate exsitedData) {
		IssueInvestigationMediateItemVo result = new IssueInvestigationMediateItemVo();
		result.setCity(exsitedData.getCityCount());
		result.setDistrict(exsitedData.getDistrictCount());
		result.setTown(exsitedData.getTownCount());
		result.setGrid(exsitedData.getVillageCount());
		result.setSubtotal(exsitedData.getCityCount() + exsitedData.getDistrictCount()
				+ exsitedData.getTownCount() + exsitedData.getVillageCount());
		result.setAnnualTotal(caculateYearTotal(vo, result));
		result.setName(exsitedData.getTypeSubsetName());
		if (vo.getTypeId() != null) {
			increateTotal(result.getName(), result);
		}
		return result;
	}

	private void appendOtherBlankRecord(List<IssueInvestigationMediateVo> results) {
		importantData.additem(createBlankIssueInvestigationMediateItemVo(SUCC_COUNT));
		importantData.additem(createBlankIssueInvestigationMediateItemVo(SUCC_RATE));
		results.add(createBlankOtherVo());
	}

	private void fillStatDataUseExsitedData(List<IssueInvestigationMediateVo> datas) {
		for (IssueInvestigationMediateVo vo : datas) {
			fillStatDataUseExsitedData(vo);
		}
	}

	private void fillStatDataUseExsitedData(IssueInvestigationMediateVo vo) {
		for (IssueInvestigationMediateItemVo item : vo.getItems()) {
			IssueInvestigationMediate exsitedData = lookupExistedStatData(vo.getTypeId(),
					vo.getTypeDisplayName(), item.getName());
			if (exsitedData != null) {
				item.setCity(exsitedData.getCityCount());
				item.setDistrict(exsitedData.getDistrictCount());
				item.setTown(exsitedData.getTownCount());
				item.setGrid(exsitedData.getVillageCount());
				item.setSubtotal(exsitedData.getCityCount() + exsitedData.getDistrictCount()
						+ exsitedData.getTownCount() + exsitedData.getVillageCount());
			}
			item.setAnnualTotal(caculateYearTotal(vo, item));
			if (vo.getTypeId() != null) {
				increateTotal(item.getName(), item);
			}
		}
	}

	private void increateTotal(String name, IssueInvestigationMediateItemVo item) {
		if (IssueInvestigationMediate.INPUT_ITEM.equals(name)) {
			cityInputCount = cityInputCount + item.getCity();
			districtInputCount = districtInputCount + item.getDistrict();
			townInputCount = townInputCount + item.getTown();
			gridInputCount = gridInputCount + item.getGrid();
			inputSubCount = inputSubCount + item.getSubtotal();
			inputTotalCount = inputTotalCount + item.getAnnualTotal();
		} else if (IssueInvestigationMediate.DEAL_ITEM.equals(name)) {
			citydealCount = citydealCount + item.getCity();
			districtdealCount = districtdealCount + item.getDistrict();
			towndealCount = towndealCount + item.getTown();
			griddealCount = griddealCount + item.getGrid();
			dealSubCount = dealSubCount + item.getSubtotal();
			dealTotalCount = dealTotalCount + item.getAnnualTotal();
		}
	}

	private Long caculateYearTotal(IssueInvestigationMediateVo vo,
			IssueInvestigationMediateItemVo item) {
		Long result = null;
		if (isFirstMonth(month)) {
			result = item.getSubtotal();
		} else {
			if (vo.getTypeId() != null) {
				result = dao.statYearTotalCountByIssueType(orgCode, year, month, vo.getTypeId(),
						item.getName());
			} else {
				result = dao.statYearTotalCountByTypeName(orgCode, year, month,
						vo.getTypeDisplayName(), item.getName());
			}
		}
		return result == null ? Long.valueOf(0) : result;
	}

	private IssueInvestigationMediate lookupExistedStatData(Long typeId, String typeDisplayName,
			String name) {
		if (typeId != null) {
			return lookupExistedStatDataByTypeId(typeId, name);
		} else {
			return lookupExistedStatDataByTypeName(typeDisplayName, name);
		}
	}

	private IssueInvestigationMediate lookupExistedStatDataByTypeName(String typeName, String name) {
		for (int index = 0; index < monthDatas.size(); index++) {
			IssueInvestigationMediate data = monthDatas.get(index);
			if (data.getTypeName().equals(typeName) && data.getTypeSubsetName().equals(name)) {
				monthDatas.remove(index);
				return data;
			}
		}
		return null;
	}

	private IssueInvestigationMediate lookupExistedStatDataByTypeId(Long typeId, String name) {
		for (int index = 0; index < monthDatas.size(); index++) {
			IssueInvestigationMediate data = monthDatas.get(index);
			if (data.getIssueType() != null && data.getIssueType().getId().equals(typeId)
					&& data.getTypeSubsetName().equals(name)) {
				monthDatas.remove(index);
				return data;
			}
		}
		return null;
	}

	private List<IssueInvestigationMediateVo> createBlankIssueInvestigationMediateVos() {
		List<IssueInvestigationMediateVo> result = new ArrayList<IssueInvestigationMediateVo>();
		result.addAll(createBlankContradictionVos());
		result.addAll(createBlankPersonCountVos());
		result.add(createBlankImportantVo());
		return result;
	}

	private IssueInvestigationMediateVo createBlankOtherVo() {
		IssueInvestigationMediateVo vo = new IssueInvestigationMediateVo();
		vo.setTypeDisplayName(OTHER_NAME);
		vo.additem(createBlankIssueInvestigationMediateItemVo(OTHER_SUBSET));
		return vo;
	}

	private IssueInvestigationMediateVo createBlankImportantVo() {
		importantData = new IssueInvestigationMediateVo();
		importantData.setTypeDisplayName(IssueInvestigationMediate.IMPORTANT_ITEM);
		importantData
				.additem(createBlankIssueInvestigationMediateItemVo(IssueInvestigationMediate.INPUT_ITEM));
		importantData
				.additem(createBlankIssueInvestigationMediateItemVo(IssueInvestigationMediate.DEAL_ITEM));
		return importantData;
	}

	private List<IssueInvestigationMediateVo> createBlankPersonCountVos() {
		List<IssueInvestigationMediateVo> result = new ArrayList<IssueInvestigationMediateVo>();
		result.add(createBlankPersonCountVo(IssueInvestigationMediate.BELOW_FIFTY_PERSON));
		result.add(createBlankPersonCountVo(IssueInvestigationMediate.FIFTY_TO_ONEHUNDRED_PERSON));
		result.add(createBlankPersonCountVo(IssueInvestigationMediate.ONEHUNDRED_TO_FIVEHUNDRED_PERSON));
		result.add(createBlankPersonCountVo(IssueInvestigationMediate.OVER_FIVEHUNDRED_PERSON));
		return result;
	}

	private IssueInvestigationMediateVo createBlankPersonCountVo(String displayName) {
		IssueInvestigationMediateVo vo = new IssueInvestigationMediateVo();
		vo.setTypeDisplayName(displayName);
		vo.additem(createBlankIssueInvestigationMediateItemVo(IssueInvestigationMediate.FIX_SUBSET));
		return vo;
	}

	private List<IssueInvestigationMediateVo> createBlankContradictionVos() {
		List<IssueInvestigationMediateVo> result = new ArrayList<IssueInvestigationMediateVo>();
		for (IssueType type : allContradictionTypes) {
			IssueInvestigationMediateVo vo = new IssueInvestigationMediateVo();
			vo.setTypeDisplayName(type.getIssueTypeName());
			vo.setTypeId(type.getId());
			vo.additem(createBlankIssueInvestigationMediateItemVo(IssueInvestigationMediate.INPUT_ITEM));
			vo.additem(createBlankIssueInvestigationMediateItemVo(IssueInvestigationMediate.DEAL_ITEM));
			result.add(vo);
		}
		return result;
	}

	private IssueInvestigationMediateItemVo createBlankIssueInvestigationMediateItemVo(
			String displayName) {
		IssueInvestigationMediateItemVo result = new IssueInvestigationMediateItemVo();
		result.setName(displayName);
		result.setCity(0L);
		result.setDistrict(0L);
		result.setGrid(0L);
		result.setTown(0L);
		result.setSubtotal(0L);
		result.setAnnualTotal(0L);
		return result;
	}

	private boolean isFirstMonth(int month) {
		return Calendar.JANUARY == month - 1;
	}

}
