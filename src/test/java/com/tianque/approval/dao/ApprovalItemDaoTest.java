package com.tianque.approval.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByName;

import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.Item;
import com.tianque.approval.domain.property.ApprovalItemStatus;
import com.tianque.approval.domain.vo.ApprovalItemVo;
import com.tianque.base.excel.BaseDaoTest;
import com.tianque.core.exception.DAOException;
import com.tianque.core.vo.PageInfo;
import com.tianque.util.XlsDataSetBeanFactory;

public class ApprovalItemDaoTest extends BaseDaoTest<ApprovalItem, ApprovalItemDao> {

	@SpringBeanByName
	private ApprovalItemDao approvalItemDao;

	@Override
	public ApprovalItemDao getDao() {
		return approvalItemDao;
	}

	@Test(expected = DAOException.class)
	public void 申请编号为null添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setApprovalNumber(null);
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请编号为空字符串添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setApprovalNumber(" ");
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请人名称为null添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setName(null);
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请人名称为空字符串添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setName(" ");
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请人身分证为null添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setIdCardNo(null);
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请人身分证为空字符串添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setIdCardNo(" ");
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请人手机号码为null添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setMobileNumber(null);
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请人手机号码为空字符串添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setMobileNumber(" ");
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请人详细地址为null添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setCurrentAddress(null);
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请人详细地址为空字符串添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setCurrentAddress(" ");
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请事项为null添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setItem(null);
		approvalItemDao.add(approvalItem);
	}

	@Test(expected = DAOException.class)
	public void 申请事项为id为null添加时() {
		ApprovalItem approvalItem = getApprovalItem();
		approvalItem.setItem(new Item());
		approvalItemDao.add(approvalItem);
	}

	@Test
	@DataSet
	public void 申请事项分页Test() {
		List<ApprovalItem> list = findApprovalItemList();
		ApprovalItemVo approvalItemVo = new ApprovalItemVo();
		approvalItemVo.setStatus(ApprovalItemStatus.IN_PROCESS);
		PageInfo<ApprovalItem> pageInfo = approvalItemDao.findApprovalItemPage("", approvalItemVo,
				1, 20, "id", "desc");
		assertEquals(pageInfo.getTotalRowSize(), list.size());
		assertEquals(pageInfo.getPageNum(), 1);
		assertEquals(pageInfo.getPerPageSize(), 20);
	}

	private ApprovalItem getApprovalItem() {
		try {
			return XlsDataSetBeanFactory.createBeans("ApprovalItemDaoTest.beans.xls",
					"addApprovalitems", ApprovalItem.class, this).get(0);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

	private List<ApprovalItem> findApprovalItemList() {
		try {
			return XlsDataSetBeanFactory.createBeans("ApprovalItemDaoTest.beans.xls",
					"addApprovalitems", ApprovalItem.class, this);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

}
