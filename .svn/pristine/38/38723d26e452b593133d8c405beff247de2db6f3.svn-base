/**
 * 
 */
package com.tianque.serviceList.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.HiddenDanger;
import com.tianque.serviceList.domain.PolicyPropaganda;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface PolicyPropagandaService {
	/**
	 * 保存
	 */
	public PolicyPropaganda addPolicyPropaganda(PolicyPropaganda info);

	/**
	 * 查询列表
	 */
	public PageInfo<PolicyPropaganda> getPolicyPropagandaListByQuery(PolicyPropaganda policyPropaganda,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 更新信息
	 * 
	 * @param companyBaseInfo
	 * @return
	 * @throws Exception
	 */
	public PolicyPropaganda updatePolicyPropaganda(PolicyPropaganda policyPropaganda);

	/**
	 * 批量删除信息
	 * 
	 * @param ids
	 */
	public void deletePolicyPropagandaByIds(String ids);

	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public PolicyPropaganda getPolicyPropagandaById(Long id);
	/**
	 * 签收
	 */
	public PolicyPropaganda signPolicyPropaganda(PolicyPropaganda policyPropaganda);
	/**
	 * 回复
	 */
	public PolicyPropaganda replyPolicyPropaganda(PolicyPropaganda policyPropaganda);
/**
 * 手机新增
 * @param info
 * @param attachFileNames
 * @return
 */
	public PolicyPropaganda addPolicyPropaganda(PolicyPropaganda info,String[] attachFileNames);
}
