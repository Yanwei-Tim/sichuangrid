package com.tianque.working.service;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tianque.approval.domain.Item;
import com.tianque.base.excel.BaseServiceTest;
import com.tianque.core.exception.ServiceException;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.dao.WorkingRecordDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.service.impl.WorkingRecordServiceImpl;

public class WorkingRecordServiceTest extends BaseServiceTest {

	@Mock
	private WorkingRecordDao workingRecordDao;
	@Mock
	private DailyLogAttachFileService dailyLogAttachFileService;
	@InjectMocks
	private WorkingRecordService workingRecordService = new WorkingRecordServiceImpl();
	@Mock
	private PropertyDictService propertyDictService;

	@Test(expected = ServiceException.class)
	public void 事项已被使用删除() {
		WorkingRecord workingRecord = new WorkingRecord();
		WorkingRecord workingRecord2 = new WorkingRecord();
		when(workingRecordService.addWorkingRecord(workingRecord, null)).thenReturn(workingRecord2);

		workingRecordService.addWorkingRecord(workingRecord2, null);
	}

	/*
	 * @Test
	 * public void addItemTest(){
	 * Item item = getItem();
	 * when(itemDao.add(item)).thenReturn(item);
	 * itemService.addItem(item, null);
	 * verify(itemDao, times(1)).add(item);
	 * }
	 * @Test(expected=ServiceException.class)
	 * public void 事项编号重复时添加(){
	 * Item im = getItem();
	 * when(itemDao.getItemByItemNumber(im.getItemNumber())).thenReturn(im);
	 * itemService.addItem(im, null);
	 * }
	 * @Test
	 * public void upateItemTest(){
	 * Item item = getItem();
	 * item.setId(1L);
	 * when(itemDao.update(item)).thenReturn(item);
	 * Item savedItem = getItem();
	 * savedItem.setId(1L);
	 * itemService.updateItem(savedItem, null);
	 * verify(itemDao, times(1)).update(savedItem);
	 * }
	 * @Test(expected=ServiceException.class)
	 * public void 事项编号重复修改(){
	 * Item im = getItem();
	 * im.setId(1L);
	 * when(itemDao.getItemByItemNumber(im.getItemNumber())).thenReturn(im);
	 * Item updatedIm = getItem();
	 * updatedIm.setId(2L);
	 * itemService.updateItem(updatedIm, null);
	 * }
	 * @Test
	 * public void deleteItemByIdTest(){
	 * itemService.deleteItemById(1L);
	 * verify(itemDao, times(1)).delete(1L);
	 * }
	 * @Test
	 * public void findItemPageTest(){
	 * itemService.findItemPage(null,1, 20, "id", "desc");
	 * verify(itemDao, times(1)).findItemPage(null,1, 20, "id", "desc");
	 * }
	 */

	private Item getItem() {
		Item item = new Item();
		item.setItemName("事项名称");
		item.setItemNumber("8888A999999");
		item.setPromiseTime(5L);
		item.setLegalTime(5L);
		item.setFees(true);
		item.setStandardFees(30L);
		item.setMatterKind(propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
				PropertyTypes.ITEM_MATTER_KIND, "行政审批类"));
		item.setLegalBasis("法定依据法定依据法定依据法定依据法定依据");
		item.setLawGuide("办事指南办事指南办事指南办事指南办事指南");
		item.setRemark("备注备注备注备注备注备注备注备注备注");
		return item;
	}

	private WorkingRecord getWorkingRecord() {
		WorkingRecord workingRecord = new WorkingRecord();
		DailyDirectory dailyDirectory = new DailyDirectory();
		workingRecord.setDailyDirectory(dailyDirectory);
		return workingRecord;
	}
}
