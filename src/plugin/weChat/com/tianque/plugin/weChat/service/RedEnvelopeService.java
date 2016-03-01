/**
 * 
 */
package com.tianque.plugin.weChat.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.redEnvelopeManagement.RedEnvelope;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.vo.RedEnvelopeVo;

/**
 * 微信红包服务
 */
public interface RedEnvelopeService {
	/**
	 * 创建红包
	 */
	public RedEnvelope addRedEnvelope(RedEnvelope redEnvelope);

	/**
	 * 修改红包
	 */
	public RedEnvelope updateRedEnvelope(RedEnvelope redEnvelope);

	/**
	 * 查看红包
	 */
	public RedEnvelope getRedEnvelopeById(Long id);

	/**
	 * 删除红包
	 */
	public void deleteRedEnvelopeById(Long[] ids);

	/**
	 * 列表分页
	 */
	public PageInfo<RedEnvelopeVo> findRedEnvelopeByPage(RedEnvelopeVo redEnvelopeVo, int pageNum,
			int pageSize, String sortField, String order);

	/**
	 * 列表分页(根据粉丝号以及当前部门id查询)
	 */
	public PageInfo<RedEnvelopeVo> findRedEnvelopeForInboxByPage(Fan fan,
			RedEnvelopeVo redEnvelopeVo, int pageNum, int pageSize, String sortField, String order);

}
