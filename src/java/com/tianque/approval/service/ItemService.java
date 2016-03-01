package com.tianque.approval.service;

import com.tianque.approval.domain.Item;
import com.tianque.approval.domain.vo.SearchItem;
import com.tianque.core.vo.PageInfo;

public interface ItemService {

	public Item addItem(Item item, String[] attachFiles);

	public Item updateItem(Item item, String[] attachFiles);

	public PageInfo<Item> findItemPage(SearchItem searchItem, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public void deleteItemById(Long id);

	public Item getItemById(Long id);
}
