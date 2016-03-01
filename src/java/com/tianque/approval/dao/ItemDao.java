package com.tianque.approval.dao;

import com.tianque.approval.domain.Item;
import com.tianque.approval.domain.vo.SearchItem;
import com.tianque.core.base.BaseDao;
import com.tianque.core.vo.PageInfo;

public interface ItemDao extends BaseDao<Item, Item> {

	public PageInfo<Item> findItemPage(SearchItem searchItem, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public Item getItemByItemNumber(String itemNumber);
}
