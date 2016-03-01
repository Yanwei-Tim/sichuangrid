package com.tianque.plugin.weChat.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.redEnvelopeManagement.RedEnvelopesPaymentRecords;

/**
 * 微信红包发放记录
 */
public interface RedEnvelopesPaymentRecordsDao {
	/**
	 * 新增红包发放记录
	 */
	public RedEnvelopesPaymentRecords addRedEnvelopesPaymentRecords(
			RedEnvelopesPaymentRecords redEnvelopesPaymentRecords);

	/**
	 * 获取红包发放记录
	 */
	public RedEnvelopesPaymentRecords getRedEnvelopesPaymentRecordsById(Long id);

	/**
	 * 删除红包发放记录
	 */
	public void deleteRedEnvelopesPaymentRecordsById(Long id);

	/**
	 * 红包发放记录列表分页
	 */
	public PageInfo<RedEnvelopesPaymentRecords> findRedEnvelopesPaymentRecordsByPage(
			RedEnvelopesPaymentRecords redEnvelopesPaymentRecords, int pageNum, int pageSize,
			String sortField, String order);

}
