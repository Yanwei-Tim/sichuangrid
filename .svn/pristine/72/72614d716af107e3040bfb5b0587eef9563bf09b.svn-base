/**
 * 
 */
package com.tianque.serviceList.service;

import java.util.ArrayList;
import java.util.List;

import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface ReplyAttachService {
	/**
	 * 保存
	 */
	public void addReplyAttch(String[] attachFileNames, Long objectId,Integer serviceType);

	/**
	 * 批量删除信息
	 * 
	 * @param ids
	 */
	public void deleteReplyAttchByIds(Long id,Integer type);
	/**
	 * 删除信息
	 * 
	 * @param ids
	 */
	public void deleteReplyAttch(Long id);
	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public ReplyAttch getReplyAttchById(Long id);
	
	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<ReplyAttch> getReplyAttchByIdAndType(Long id,Integer type);
}
