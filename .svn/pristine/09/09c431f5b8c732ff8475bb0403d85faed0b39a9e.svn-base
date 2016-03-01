/**
 * 
 */
package com.tianque.serviceList.service;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.serviceList.domain.PolicyPropaganda;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListAttch;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface ReplyService {
	/**
	 * 保存
	 */
	public Reply addReply(Reply reply);

	/**
	 * 批量删除信息
	 * 
	 * @param ids
	 */
	public void deleteReplyByIds(Long id,Integer type);
	/**
	 * 删除信息
	 * 
	 * @param ids
	 */
	public void deleteReply(Long id);
	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public Reply getReplyById(Long id,Integer type);
	
	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Reply> getReplyByIdAndType(Long id,Integer type);
	
	/**
	 * 查询列表
	 */
	public PageInfo<Reply> getReplyList(Reply reply,
			Integer page, Integer rows, String sidx, String sord);

	public List<Reply> queryServiceListReplyByMobile(Long serviceId,Integer serviceType);
}
