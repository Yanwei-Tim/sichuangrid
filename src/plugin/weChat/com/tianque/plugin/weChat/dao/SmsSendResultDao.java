package com.tianque.plugin.weChat.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.sms.SmsSendResult;

/**
 * 短信发送结果
 * @ClassName: SmsSendResultDao 
 * @author: he.simin
 * @date: 2015年11月5日 下午3:24:11
 */
public interface SmsSendResultDao {
	/**
	 * 添加短信发送结果
	 * @Title: saveSmsSendResult 
	 * @param smsSendResult
	 * @return: 
	 */
	public Long saveSmsSendResult(SmsSendResult smsSendResult);

	public List<Long> saveSmsSendResult(List<SmsSendResult> smsSendResultList);

	/**
	 * id获取短信发送结果
	 * @Title: getById 
	 * @Description: TODO
	 * @param id
	 * @return: SmsSendResult
	 */
	public SmsSendResult getById(Long id);


	/**
	 * 更新短信发送结果
	 * @Title: updateSmsSendResult 
	 * @param smsSendResult
	 * @return: void
	 */
	public void updateSmsSendResult(SmsSendResult smsSendResult);

	/**
	 * 分页查询短信发送结果
	 * @Title: findSmsSendResult 
	 * @param smsSendResult
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return: PageInfo<SmsSendResult>
	 */
	public PageInfo<SmsSendResult> findSmsSendResult(SmsSendResult smsSendResult,
			Integer pageNum,
			Integer pageSize, String sidx, String sord);

}
