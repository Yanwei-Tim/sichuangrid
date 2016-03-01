package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SmsReceivedBox;

public interface SmsReceivedBoxService {

	public SmsReceivedBox getSmsReceivedBoxById(Long id);

	public SmsReceivedBox updateSmsReceivedBox(SmsReceivedBox smsReceivedBox);

	public boolean deleteSmsReceivedBoxById(Long id);

	public PageInfo<SmsReceivedBox> findSmsReceivedBoxByOwnerId(Long orgId,
			Integer year, Integer month, int pageNum, int pageSize,
			String sortField, String order);

	public int countUnprocessSmsReceivedBoxByOwnerId(Long userId);

	public SmsReceivedBox addSmsReceivedBox(SmsReceivedBox smsReceivedBox);

}
