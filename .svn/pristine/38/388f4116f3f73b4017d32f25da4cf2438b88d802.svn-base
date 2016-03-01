package com.tianque.sms.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.base.BaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.sms.domain.SmsUplink;
import com.tianque.sms.domain.vo.SearchSmsUplinkVo;
import com.tianque.sms.domain.vo.SmsJobSqlVo;
import com.tianque.sms.domain.vo.SmsSendObjectVo;

/**
 * 短信发件箱:数据操作层接口
 * 
 * @author
 * @date 2013-07-02 09:53:32
 */
public interface SmsUplinkDao extends BaseDao<SmsUplink, SearchSmsUplinkVo> {
	/**
	 * 批量新增短信
	 * 
	 * @param smsUplinkList
	 */
	public void addSmsUplinkByBatch(List<SmsUplink> smsUplinkList);

	/**
	 * 批量更新短信发送状态
	 * 
	 * @param smsUplinkList
	 */
	public void updateSmsUplinkStatusByBatch(List<SmsUplink> smsUplinkList);

	/**
	 * 查询短信发送对象人数
	 * 
	 * @param map
	 * @return
	 */
	public int countSmsSendObjectNumber(String sql);

	/**
	 * 查询短信发送对象
	 * 
	 * @param sql
	 * @return
	 */
	public List<SmsSendObjectVo> findSmsSendObject(String sql);

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
	 * 从发件箱中挑取一条，id最小且发送状态是：处理中或待发送的数据
	 * 
	 * @return
	 */
	public SmsUplink getSmsUplinkByMinIdAndProcessed();

	/**
	 * 根据短信发送平台短信Id 挑取数据
	 * 
	 * @param messageId
	 * @return
	 */
	public SmsUplink getSmsUplinkByMessageId(Long messageId);

	/**
	 * 从发件箱中挑取number条,发送状态是待处理的数据
	 * 
	 * @param id
	 * @param number
	 * @return
	 */
	public List<SmsUplink> getSmsUplinkListBySendStatus(Integer number);

	/**
	 * 分页查询复杂对象人员信息
	 * 
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<SmsSendObjectVo> findSmsSendPeopleInfoPager(String sql,
			Integer pageNum, Integer pageSize, String sortField, String order);

	public void updateDeleteStatusSmsUplinkByReceiverMobile(
			String receiverMobile);

	/**
	 * 根据组织机构查询不同的手机类型发送短信数量
	 * 
	 * @param orgIds
	 * @return
	 */
	public List<Map<String, Object>> findDifferPhoneTypeMessagesNumberByOrgIds(
			String orgIds);

	/**
	 * 更新短信删除状态
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
