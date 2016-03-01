package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SmsReceivedBox;

public interface SmsReceivedBoxDao {

	public SmsReceivedBox getSmsReceivedBoxById(Long id);

	public SmsReceivedBox addSmsReceivedBox(SmsReceivedBox smsReceivedBox);

	public SmsReceivedBox updateSmsReceivedBox(SmsReceivedBox smsReceivedBox);

	public int deleteSmsReceivedBoxById(Long id);

	public PageInfo<SmsReceivedBox> findSmsReceivedBoxByOwnerId(Long orgId,
			int pageNum, int pageSize, String sortField, String order);

	public PageInfo<SmsReceivedBox> findSmsReceivedBoxByOwnerId(Long orgId,
			String date, int pageNum, int pageSize, String sortField,
			String order);

	public int countUnprocessSmsReceivedBoxByOwnerId(Long userId);

}
