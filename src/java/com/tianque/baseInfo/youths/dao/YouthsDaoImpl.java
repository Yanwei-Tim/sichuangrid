package com.tianque.baseInfo.youths.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.youths.domain.Youths;
import com.tianque.baseInfo.youths.vo.SearchYouthsVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.newExcelExport.NewExcelDataExportThread;

@Repository("youthsDao")
public class YouthsDaoImpl extends AbstractBaseDao implements YouthsDao {

	@Override
	public PageInfo<Youths> findYouthsForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchYouthsVo searchYouthsVo) {

		if (pageSize == NewExcelDataExportThread.pageRows) {
			return findYouthsForAll(pageNum, pageSize, sortField, order,
					searchYouthsVo);
		} else {
			return findYouthsForPages(pageNum, pageSize, sortField, order,
					searchYouthsVo);
		}
	}

	public PageInfo<Youths> findYouthsForPages(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchYouthsVo searchYouthsVo) {

		Integer countHouseNum = getHouseCount(searchYouthsVo);
		Integer countFloatNum = getFloatCount(searchYouthsVo);
		Integer countUnsettleNum = getUnsettleCount(searchYouthsVo);
		Integer countNum = countHouseNum + countFloatNum + countUnsettleNum;
		if (countNum == 0) {
			return createPageInfo(0, pageSize, 0, new ArrayList<Youths>());
		}
		if (searchYouthsVo != null) {
			searchYouthsVo.setSortField(sortField);
			searchYouthsVo.setOrder(order);
		}
		if (countNum <= pageSize) {
			return findYouthsForAll(pageNum, pageSize, sortField, order,
					searchYouthsVo);
		}
		int pageCount = 0;
		List<Youths> list;
		if (pageNum != null) {
			pageCount = countNum % pageSize == 0 ? countNum / pageSize
					: countNum / pageSize + 1;
			pageNum = pageNum > pageCount ? pageCount : pageNum;
		} else {
			pageNum = 1;
		}
		if ((pageNum - 1) * pageSize <= countHouseNum
				&& countHouseNum >= pageNum * pageSize) {
			searchYouthsVo.setStartRow((pageNum - 1) * pageSize);
			searchYouthsVo.setEndRow((pageNum) * pageSize);
			list = getSqlMapClientTemplate().queryForList("youths.findHouse",
					searchYouthsVo);
		} else if ((pageNum - 1) * pageSize <= countHouseNum
				&& countHouseNum < pageNum * pageSize) {
			searchYouthsVo.setStartRow((pageNum - 1) * pageSize);
			searchYouthsVo.setEndRow(pageNum * pageSize);
			list = getSqlMapClientTemplate().queryForList("youths.findHouse",
					searchYouthsVo);

			if (countHouseNum + countFloatNum >= pageNum * pageSize) {
				searchYouthsVo.setStartRow(0);
				searchYouthsVo.setEndRow(pageNum * pageSize - countHouseNum);
				List<Youths> listf = getSqlMapClientTemplate().queryForList(
						"youths.findFloat", searchYouthsVo);
				list.addAll(listf);
			} else {
				searchYouthsVo.setStartRow(0);
				searchYouthsVo.setEndRow(pageNum * pageSize - countHouseNum);
				List<Youths> listf = getSqlMapClientTemplate().queryForList(
						"youths.findFloat", searchYouthsVo);
				list.addAll(listf);

				searchYouthsVo.setStartRow(0);
				searchYouthsVo.setEndRow(pageNum * pageSize - countHouseNum
						- countFloatNum);
				List<Youths> listu = getSqlMapClientTemplate().queryForList(
						"youths.findUnsettle", searchYouthsVo);
				list.addAll(listu);
			}
		} else if ((pageNum - 1) * pageSize <= (countHouseNum + countFloatNum)
				&& (countHouseNum + countFloatNum) >= pageNum * pageSize) {
			searchYouthsVo
					.setStartRow((pageNum - 1) * pageSize - countHouseNum);
			searchYouthsVo.setEndRow(pageNum * pageSize - countHouseNum);
			list = getSqlMapClientTemplate().queryForList("youths.findFloat",
					searchYouthsVo);
		} else if ((pageNum - 1) * pageSize <= (countHouseNum + countFloatNum)
				&& (countHouseNum + countFloatNum) < pageNum * pageSize) {
			searchYouthsVo
					.setStartRow((pageNum - 1) * pageSize - countHouseNum);
			searchYouthsVo.setEndRow(pageNum * pageSize - countHouseNum);
			list = getSqlMapClientTemplate().queryForList("youths.findFloat",
					searchYouthsVo);

			searchYouthsVo.setStartRow(0);
			searchYouthsVo.setEndRow(pageNum * pageSize - countHouseNum
					- countFloatNum);
			List<Youths> listf = getSqlMapClientTemplate().queryForList(
					"youths.findUnsettle", searchYouthsVo);
			list.addAll(listf);
		} else {
			searchYouthsVo.setStartRow((pageNum - 1) * pageSize - countHouseNum
					- countFloatNum);
			searchYouthsVo.setEndRow(pageNum * pageSize - countHouseNum
					- countFloatNum);
			list = getSqlMapClientTemplate().queryForList(
					"youths.findUnsettle", searchYouthsVo);
		}
		int i = 1;
		for (Youths youths : list) {
			youths.setRowNum(i);
			i++;
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<Youths> findYouthsForAll(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchYouthsVo searchYouthsVo) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"youths.countYouths", searchYouthsVo);
		if (countNum == 0) {
			return createPageInfo(0, pageSize, 0, new ArrayList<Youths>());
		}

		if (searchYouthsVo != null) {
			searchYouthsVo.setSortField(sortField);
			searchYouthsVo.setOrder(order);
		}

		int pageCount = 0;
		List<Youths> list;
		if (pageNum != null) {
			pageCount = countNum % pageSize == 0 ? countNum / pageSize
					: countNum / pageSize + 1;
			pageNum = pageNum > pageCount ? pageCount : pageNum;
			list = getSqlMapClientTemplate().queryForList("youths.findYouths",
					searchYouthsVo, (pageNum - 1) * pageSize, pageSize);
		} else {
			pageNum = 1;
			list = getSqlMapClientTemplate().queryForList("youths.findYouths",
					searchYouthsVo);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<Youths> createPageInfo(Integer pageNum, Integer pageSize,
			Integer countNum, List<Youths> list) {
		PageInfo<Youths> pageInfo = new PageInfo<Youths>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getYouthsCount(SearchYouthsVo searchYouthsVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"youths.countYouths", searchYouthsVo);
	}

	private Integer getHouseCount(SearchYouthsVo searchYouthsVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"youths.countHouse", searchYouthsVo);
	}

	private Integer getFloatCount(SearchYouthsVo searchYouthsVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"youths.countFloat", searchYouthsVo);
	}

	private Integer getUnsettleCount(SearchYouthsVo searchYouthsVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"youths.countUnsettle", searchYouthsVo);
	}

}
