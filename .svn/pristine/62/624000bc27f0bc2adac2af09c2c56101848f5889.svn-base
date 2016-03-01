package com.tianque.sms.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.sms.domain.Smsdownlink;
import com.tianque.sms.domain.vo.SearchSmsdownlinkVo;

/**
 * 短信收件箱:业务逻辑层接口
 * 
 * @author
 * @date 2013-07-08 17:27:15
 */
public interface SmsdownlinkService extends
		BaseService<Smsdownlink, SearchSmsdownlinkVo> {
	public PageInfo<Smsdownlink> findSmsdownlinkForPager(Long isRead,
			String sender, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	/**
	 * 根据手机号码得到消息内容
	 * 
	 * @param phone
	 * @return List
	 */
	public List<Smsdownlink> findSmsContentBySender(String sender);

	public void updateDeleteStatusBySender(String[] senders);

	public void updateSmsDownLinkBySender(Long isRead, String sender);

	/**
	 * 更新短信删除状态为删除
	 * 
	 * @param ids
	 * @return
	 */
	public Boolean updateDeleteStatusByIds(Long[] ids);

	/**
	 * 更新短信删除状态为未删除
	 * 
	 * @param ids
	 * @return
	 */
	public Boolean updateRestoreDeleteStatusById(Long id);

}
