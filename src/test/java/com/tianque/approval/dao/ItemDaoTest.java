package com.tianque.approval.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByName;

import com.tianque.approval.domain.Item;
import com.tianque.base.excel.BaseDaoTest;
import com.tianque.core.exception.DAOException;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.util.XlsDataSetBeanFactory;

public class ItemDaoTest extends BaseDaoTest<Item, ItemDao> {

	@SpringBeanByName
	private ItemDao itemDao;

	@Test(expected = DAOException.class)
	public void 当事项编号为null添加时() {
		Item item = getItem();
		item.setItemNumber(null);
		itemDao.add(item);
	}

	@Test(expected = DAOException.class)
	public void 当事项编号为空字符串添加时() {
		Item item = getItem();
		item.setItemNumber(" ");
		itemDao.add(item);
	}

	@Test(expected = DAOException.class)
	public void 当事项名称为null添加时() {
		Item item = getItem();
		item.setItemName(null);
		itemDao.add(item);
	}

	@Test(expected = DAOException.class)
	public void 当事项名称为空字符串添加时() {
		Item item = getItem();
		item.setItemName(" ");
		itemDao.add(item);
	}

	@Test(expected = DAOException.class)
	public void 当事项承诺时限为null添加时() {
		Item item = getItem();
		item.setPromiseTime(null);
		itemDao.add(item);
	}

	@Test(expected = DAOException.class)
	public void 当事项法定时限为null添加时() {
		Item item = getItem();
		item.setLegalTime(null);
		itemDao.add(item);
	}

	@Test(expected = DAOException.class)
	public void 当事项分类为null添加时() {
		Item item = getItem();
		item.setMatterKind(null);
		itemDao.add(item);
	}

	@Test(expected = DAOException.class)
	public void 当事项分类id为null添加时() {
		Item item = getItem();
		item.setMatterKind(new PropertyDict());
		itemDao.add(item);
	}

	@Test
	@DataSet
	public void 事项分页Test() {
		List<Item> list = findItemList();
		PageInfo<Item> pageInfo = itemDao.findItemPage(null, 1, 20, "id", "desc");
		assertEquals(pageInfo.getTotalRowSize(), list.size());
		assertEquals(pageInfo.getPageNum(), 1);
		assertEquals(pageInfo.getPerPageSize(), 20);
	}

	@Override
	public ItemDao getDao() {
		return itemDao;
	}

	// com.tianque.approval.dao
	private Item getItem() {
		try {
			return XlsDataSetBeanFactory.createBeans("ItemDaoTest.beans.xls", "addItems",
					Item.class, this).get(0);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

	private List<Item> findItemList() {
		try {
			return XlsDataSetBeanFactory.createBeans("ItemDaoTest.beans.xls", "addItems",
					Item.class, this);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}
}
