package com.tianque.sms.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.sms.domain.Smsdownlink;
import com.tianque.sms.domain.vo.SearchSmsdownlinkVo;

/**
 * 短信收件箱:数据操作层接口
 * 
 * @author
 * @date 2013-07-08 17:27:15
 */
public interface SmsdownlinkDao extends
		BaseDao<Smsdownlink, SearchSmsdownlinkVo> {
	public void addSmsdownlinkByBatch(List<Smsdownlink> datas);

	public void addSmsdownlinkBigId(Long id);

	public void updateSmsdownlinkBigId(Long id);

	public Long getSmsdownlinkBigId();

	public List<Smsdownlink> findSmsdownlinkBySender(Long isRead,
			String sender, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public Integer countSmsdownlinkBySender(String sender);

	public List<Smsdownlink> getSmsdownlinkBySender(String sender);

	/**
	 * 根据手机号码得到消息内容
	 * 
	 * @param phone
	 * @return List
	 */
	public List<Smsdownlink> findSmsContentBySender(String phone);

	public void updateDeleteStatusSmsdownlinkBySender(String sender);

	public void updateSmsDownLinkBySender(Long isRead, String sender);

	/**
	 * 更新短信删除状态为删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean updateDeleteStatusByIds(String ids);

	/**
	 * 更新短信删除状态为未删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean updateRestoreDeleteStatusById(Long id);

}
