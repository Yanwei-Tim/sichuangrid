package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Contacter;
import com.tianque.domain.SmsSendBox;

public interface SmsSendBoxService {

	public SmsSendBox getSmsSendBoxById(Long id);

	public SmsSendBox addSmsSendBox(SmsSendBox smsSendBox,
			List<Contacter> contacters);

	public boolean deleteSmsSendBoxById(Long id);

	public PageInfo<SmsSendBox> findSmsSendBoxByOwnerId(Long ownerId,
			Integer year, Integer month, int pageNum, int pageSize,
			String sortField, String order);

}
