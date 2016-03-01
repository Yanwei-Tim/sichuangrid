/**
 * 
 */
package com.tianque.serviceList.dao;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.serviceList.domain.PolicyPropaganda;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface ReplyAttchDao extends BaseDao<ReplyAttch, ReplyAttch>{
	public void deleteReplyAttchByIds(Long id,Integer type);
	
	public ArrayList<ReplyAttch> getReplyAttchByIdAndType(Long id,Integer type);
}
