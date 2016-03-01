package com.tianque.sms.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.base.BaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.sms.domain.SmsUplink;
import com.tianque.sms.domain.vo.SearchSmsUplinkVo;
import com.tianque.sms.domain.vo.SmsJobSqlVo;
import com.tianque.sms.domain.vo.SmsSendObjectVo;

/**
 * 短信发件箱:业务逻辑层接口
 * 
 * @author
 * @date 2013-07-02 09:53:32
 */
public interface SmsUplinkService extends
		BaseService<SmsUplink, SearchSmsUplinkVo> {

	/**
	 * 新增发件箱发送短信信息
	 * 
	 * @param map
	 * @return
	 */
	public boolean addSendSMSInfo(Map<String, String> map);

	/**
	 * 查询短信发送对象人数
	 * 
	 * @param map
	 * @return
	 */
	public int countSmsSendObjectNumber(Map<String, String> map);

	/**
	 * 查询短信发送对象
	 * 
	 * @param map
	 * @return
	 */
	public PageInfo<SmsSendObjectVo> findSmsSendPeopleInfoPager(
			Map<String, String> map, Integer pageNum, Integer pageSize,
			String sortField, String order);

	/**
	 * 新增短信发送对象jobSql
	 * 
	 * @param smsJobSqlVo
	 * @return
	 */
	public SmsJobSqlVo addSmsJobSqlVo(SmsJobSqlVo smsJobSqlVo);

	/**
	 * 根据Id得到短信发送对象jobSql
	 * 
	 * @param id
	 * @return
	 */
	public SmsJobSqlVo getSmsJobSqlVoById(Long id);

	/**
	 * 查询所有短信发送对象未发送的jobSql
	 * 
	 * @return
	 */
	public List<SmsJobSqlVo> findSmsJobSqlVoBySmsLevel();

	/**
	 * 更新短信发送对象jobSql的smsLevel
	 * 
	 * @return
	 */
	public SmsJobSqlVo updateSmsJobSqlVo(SmsJobSqlVo smsJobSqlVo);

	/**
	 * 批量新增发件箱短信
	 * 
	 * @param smsUplinkList
	 */
	public void addBatchSmsUplink(List<SmsUplink> smsUplinkList);

	/**
	 * 根据parentId 分页查询短信发送
	 * 
	 * @param searchVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<SmsUplink> findSubSmsUplinksBySearchVo(
			SearchSmsUplinkVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	/**
	 * 从已处理的表中挑取id最小的数据
	 * 
	 * @return
	 */
	public SmsUplink getSmsUplinkByMinIdAndProcessed();

	public void updateDeleteStatusSmsUplinkByReceiverMobile(
			String receiverMobile);

	/**
	 * 根据组织机构查询不同的手机类型发送短信数量
	 * 
	 * @param orgIds
	 * @return
	 */
	public List<Map<String, Object>> findDifferPhoneTypeMessagesNumberByOrgIds(
			List<Long> orgIds);

	/**
	 * 更新短信删除状态为删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean updateDeleteStatusByIds(Long[] ids);

	/**
	 * 更新短信删除状态为未删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean updateRestoreDeleteStatusById(Long id);

}
