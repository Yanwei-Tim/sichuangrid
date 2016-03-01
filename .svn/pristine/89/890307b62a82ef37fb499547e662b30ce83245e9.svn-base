package com.tianque.approval.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.approval.domain.Item;
import com.tianque.approval.domain.vo.SearchItem;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("itemDao")
public class ItemDaoImpl extends BaseDaoImpl<Item, Item> implements ItemDao {

	@Qualifier("itemValidator")
	@Autowired
	private DomainValidator<Item> itemValidator;

	@Override
	protected void checkEntityWhenAdd(Item entity) {
		ValidateResult baseDataValidator = itemValidator.validate(entity);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	protected void checkEntityWhenUpdate(Item entity) {
		ValidateResult baseDataValidator = itemValidator.validate(entity);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		if (null == entity.getId()) {
			throw new BusinessValidationException("事项不能为空");
		}
	}

	@Override
	public PageInfo<Item> findItemPage(SearchItem searchItem, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Map<String, Object> map = getSearch(searchItem, sortField, order);
		Integer totalRowSize = (Integer) getSqlMapClientTemplate()
				.queryForObject("item.countItemPage", map);
		List<Item> list = getSqlMapClientTemplate().queryForList(
				"item.findItemPage", map, (pageNum - 1) * pageSize, pageSize);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(totalRowSize);
		pageInfo.setResult(list);
		return pageInfo;
	}

	private Map<String, Object> getSearch(SearchItem searchItem,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sortField);
		map.put("order", order);
		if (null != searchItem) {
			if (null != searchItem.getFastSearchKeyWords()
					&& !"".equals(searchItem.getFastSearchKeyWords().trim())) {
				map.put("fastSearchKeyWords",
						searchItem.getFastSearchKeyWords());
			}
			if (null != searchItem.getItemName()
					&& !"".equals(searchItem.getItemName().trim())) {
				map.put("itemName", searchItem.getItemName());
			}
			if (null != searchItem.getItemNumber()
					&& !"".equals(searchItem.getItemNumber().trim())) {
				map.put("itemNumber", searchItem.getItemNumber());
			}
		}
		return map;
	}

	@Override
	public Item getItemByItemNumber(String itemNumber) {
		return (Item) getSqlMapClientTemplate().queryForObject(
				"item.getItemByItemNumber", itemNumber);
	}
}
