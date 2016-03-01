package com.tianque.approval.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tianque.approval.dao.ApprovalItemDao;
import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.Item;
import com.tianque.base.excel.BaseServiceTest;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.service.IssueService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.impl.IssueBusinessDelegate;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class ApprovalItemServiceTest extends BaseServiceTest {

	@Mock
	private IssueBusinessDelegate issueBusinessDelegate;
	@Mock
	private OrganizationDubboService organizationDubboService;
	@Mock
	private IssueService issueService;
	@Mock
	private IssueTypeService issueTypeService;
	@Mock
	private ApprovalItemFileService approvalItemFileService;
	@Mock
	private ItemService itemService;
	@Mock
	private DomainValidator<ApprovalItem> approvalItemValidator;
	@Mock
	private ApprovalItemDao approvalItemDao;
	@InjectMocks
	private ApprovalItemService approvalItemService = new ApprovalItemServiceImpl();

	@Test
	public void addApprovalItemTest() {
		ApprovalItem approvalItem = new ApprovalItem();
		Item item = new Item();
		item.setId(1L);
		approvalItem.setItem(item);
		Organization organization = new Organization();
		organization.setId(1L);
		approvalItem.setOrganization(organization);
		ThreadVariable.setOrganization(organization);
		when(
				issueTypeService.findEnabledIssueTypesByParentName(1L,
						IssueTypes.ITEM))
				.thenReturn(new ArrayList<IssueType>());
		when(organizationDubboService.getFullOrgById(1L)).thenReturn(
				new Organization());
		when(itemService.getItemById(1L)).thenReturn(item);
		when(approvalItemDao.add(approvalItem)).thenReturn(approvalItem);
		approvalItemService.addApprovalItem(approvalItem, null);
		verify(approvalItemDao, times(1)).add(approvalItem);
	}

	@Test(expected = ServiceException.class)
	public void 申请事项编号重复时添加() {
		ApprovalItem approvalItem = new ApprovalItem();
		Item item = new Item();
		item.setId(1L);
		approvalItem.setItem(item);
		approvalItem.setApprovalNumber("AAAAAAAAAAAAAAA");
		Organization organization = new Organization();
		organization.setId(1L);
		organization.setOrgInternalCode("1.1.1.");
		approvalItem.setOrganization(organization);
		approvalItem.setOrgInternalCode(organization.getOrgInternalCode());
		when(approvalItemDao.getApprovalItemByApprovalNumber(anyString()))
				.thenReturn(approvalItem);
		when(itemService.getItemById(1L)).thenReturn(new Item());
		when(organizationDubboService.getFullOrgById(organization.getId()))
				.thenReturn(organization);
		approvalItemService.addApprovalItem(approvalItem, null);
	}

	@Test
	public void deleteApprovalItemByIdTest() {
		approvalItemService.deleteApprovalItemById(1L);
		verify(approvalItemDao, times(1)).delete(1L);
	}

	@Test
	public void updateApprovalItemTest() {
		ApprovalItem approvalItem = new ApprovalItem();
		approvalItem.setId(1L);
		Organization organization = new Organization();
		organization.setId(1L);
		organization.setOrgInternalCode("1.1.1.");
		approvalItem.setOrganization(organization);
		when(organizationDubboService.getFullOrgById(organization.getId()))
				.thenReturn(organization);
		approvalItemService.updateApprovalItem(approvalItem, null);
		verify(approvalItemDao, times(1)).update(approvalItem);
	}

	@Test(expected = ServiceException.class)
	public void 修改申请事项编号重复() {
		ApprovalItem approvalItem = new ApprovalItem();
		approvalItem.setApprovalNumber("AAAAAAAAAAAAA");
		approvalItem.setId(1L);
		when(
				approvalItemDao.getApprovalItemByApprovalNumber(approvalItem
						.getApprovalNumber())).thenReturn(approvalItem);

		ApprovalItem appItem = new ApprovalItem();
		appItem.setApprovalNumber("AAAAAAAAAAAAA");
		appItem.setId(2L);
		Organization organization = new Organization();
		organization.setId(1L);
		organization.setOrgInternalCode("1.1.1.");
		appItem.setOrganization(organization);
		when(organizationDubboService.getFullOrgById(organization.getId()))
				.thenReturn(organization);
		approvalItemService.updateApprovalItem(appItem, null);
	}

	@Test
	public void getApprovalItemByIdTest() {
		approvalItemService.getApprovalItemById(1L);
		verify(approvalItemDao, times(1)).get(1L);
	}

}
