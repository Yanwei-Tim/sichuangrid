package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Contacter;
import com.tianque.domain.SmsSendBox;

public interface SmsSendBoxDao {

	public SmsSendBox getSmsSendBoxById(Long id);

	public SmsSendBox addSmsSendBox(SmsSendBox smsSendBox);

	public void addSmsSendTargets(Long smsId, Contacter contacter);

	public SmsSendBox updateSmsSendBox(SmsSendBox smsSendBox);

	public int deleteSmsSendBoxById(Long id);

	public int deleteSmsSendTargets(Long smsId);

	public PageInfo<SmsSendBox> findSmsSendBoxByOwnerId(Long ownerId,
			int pageNum, int pageSize, String sortField, String order);

	public PageInfo<SmsSendBox> findSmsSendBoxByOwnerId(Long ownerId,
			String date, int pageNum, int pageSize, String sortField,
			String order);

}
