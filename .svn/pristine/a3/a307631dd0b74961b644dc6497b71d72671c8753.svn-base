package com.tianque.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.cache.util.MemCachedManage;
import com.tianque.core.exception.ConditionsNotMatchException;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 数据统计缓存helper
 * 
 * @author yulei
 * 
 */
@Component("pageInfoCacheHelper")
public final class PageInfoCacheHelper {
	@Autowired
	@Qualifier("memCachedManage")
	private MemCachedManage memCachedManage;// java memcached 管理类 单例的
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private static final long MAX_EXPIRED_SECS = 60 * 60 * 2L;// 各个计数器失效时间，默认为2小时

	/**
	 * 将统计结果存入缓存中，缓存key为orgId_moduleName，value为统计结果
	 * 
	 * @param moduleName
	 *            需要存入的domain类名称，例如吸毒人员模块，该值则为Druggy.class.getSimpleName()
	 * @param orgId
	 *            需要查询的组织机构Id
	 * @param countNum
	 *            统计结果
	 * @return 统计结果
	 */
	public Integer storeCounter(String moduleName, Long orgId, Integer countNum) {
		deleteCounter(moduleName, orgId);
		return (int) memCachedManage.storeCounter(getKey(moduleName, orgId),
				countNum);
	}

	private void validate(Integer count) {
		if (count == null) {
			count = 1;
		}
		if (count <= 0) {
			throw new ConditionsNotMatchException("传入的条件不符合,数量必须大于零");
		}
	}

	/**
	 * 将计数器增加count,更新数据所在层级到指定层级的计数器
	 * 
	 * @param module
	 *            需要存入的domain类名称，例如吸毒人员模块，该值则为Druggy.class.getSimpleName()
	 * @param orgId
	 *            数据存储组织机构Id
	 * @param orgLevel
	 *            指定的需要更新计数器的最高层级
	 * @param count
	 *            需要增加的量
	 */
	public void incrementCounter(String module, Long orgId, Integer orgLevel,
			Integer count) {
		List<Long> orgIds = new ArrayList<Long>();
		initOrgIds(orgId, orgLevel, orgIds);
		for (Long id : orgIds) {
			if (isExpired(module, id)) {
				Integer result = incrementCounter(module, id, count);
				// 判断计数器增加时是否命中，如果未命中则删除该计数器
				if (result != null && result.intValue() == -1) {
					deleteCounter(module, id);
				}
			} else {
				Integer result = getCounter(module, id);
				if (result != null && result.intValue() > 0) {
					deleteCounter(module, id);
				}
			}
		}
	}

	/**
	 * 将计数器减少count,更新数据所在层级到指定层级的计数器
	 * 
	 * @param module
	 *            需要存入的domain类名称，例如吸毒人员模块，该值则为Druggy.class.getSimpleName()
	 * @param orgId
	 *            数据存储组织机构Id
	 * @param orgLevel
	 *            指定的需要更新计数器的最高层级
	 * @param count
	 *            需要减少的量
	 */
	public void decreaseCounter(String module, Long orgId, Integer orgLevel,
			Integer count) {
		List<Long> orgIds = new ArrayList<Long>();
		initOrgIds(orgId, orgLevel, orgIds);
		for (Long id : orgIds) {
			if (!isExpired(module, orgId)) {
				// 如果失效时间过期，则删除该计数器
				deleteCounter(module, orgId);
			}
			Integer result = decreaseCounter(module, id, count);
			// 判断计数器减少时是否命中，如果未命中则删除该计数器
			if (result != null && result.intValue() == -1) {
				deleteCounter(module, id);
			}
		}
	}

	public void initOrgIds(Long orgId, Integer orgLevel, List<Long> orgIds) {
		orgIds.add(orgId);
		Organization organization = organizationDubboService.getFullOrgById(
				orgId).getParentOrg();
		if (organization.getOrgLevel().getInternalId() > orgLevel) {
			return;
		}
		initOrgIds(organization.getId(), orgLevel, orgIds);
	}

	private Integer incrementCounter(String moduleName, Long orgId,
			Integer increment) throws ConditionsNotMatchException {
		validate(increment);
		return (int) memCachedManage.incr(getKey(moduleName, orgId), increment);
	}

	private Integer decreaseCounter(String moduleName, Long orgId,
			Integer decrease) {
		validate(decrease);
		return (int) memCachedManage.decr(getKey(moduleName, orgId), decrease);
	}

	/**
	 * 给指定的计数器设置失效时间，计数器key为orgId_moduleName
	 * 
	 * @param module
	 *            需要存入的domain类名称，例如吸毒人员模块，该值则为Druggy.class.getSimpleName()
	 * @param orgId
	 *            需要查询的组织机构Id
	 * @param expired
	 *            失效时间，单位为秒
	 */
	public void storeCounterTimeoutFlag(String moduleName, Long orgId,
			long expired) {
		String key = getTimeoutCacheKey(moduleName, orgId);
		memCachedManage.storeCounter(key, Long.MAX_VALUE, new Date(
				1000 * expired));
	}

	/**
	 * 给指定的计数器设置失效时间，计数器key为orgId_moduleName，默认失效时间为2小时
	 * 
	 * @param module
	 *            需要存入的domain类名称，例如吸毒人员模块，该值则为Druggy.class.getSimpleName()
	 * @param orgId
	 *            需要查询的组织机构Id
	 */
	public void storeCounterTimeoutFlag(String moduleName, Long orgId) {
		storeCounterTimeoutFlag(moduleName, orgId, MAX_EXPIRED_SECS);
	}

	/**
	 * 判断计数器是否失效,如果未命中则也认为计数器失效
	 * 
	 * @param module
	 *            需要存入的domain类名称，例如吸毒人员模块，该值则为Druggy.class.getSimpleName()
	 * @param orgId
	 *            需要查询的组织机构Id
	 * @return 没有失效返回true，否则返回false
	 */
	public boolean isExpired(String moduleName, Long orgId) {
		return memCachedManage.get(getTimeoutCacheKey(moduleName, orgId)) != null;
	}

	/**
	 * 获取计数器存储的值
	 * 
	 * @param module
	 *            需要存入的domain类名称，例如吸毒人员模块，该值则为Druggy.class.getSimpleName()
	 * @param orgId
	 *            需要查询的组织机构Id
	 * @return 计数器存储的值
	 */
	public Integer getCounter(String moduleName, Long orgId) {
		return (int) memCachedManage.getCounter(getKey(moduleName, orgId));
	}

	private String getTimeoutCacheKey(String moduleName, Long orgId) {
		return getKey(moduleName, orgId) + "_" + "counterTimeOut";
	}

	public String getKey(String moduleName, Long orgId) {
		if (!StringUtil.isStringAvaliable(moduleName)) {
			throw new ConditionsNotMatchException("传入的条件不符合,moduleName不合法");
		}
		if (orgId == null || orgId.longValue() == 0) {
			throw new ConditionsNotMatchException("传入的条件不符合,orgId不合法");
		}
		return orgId + "_" + moduleName;
	}

	public void deleteCounter(String moduleName, Long orgId) {
		memCachedManage.delete(getKey(moduleName, orgId));
	}

	// 将count的缓存计数器+1
	public void incrementCounter(String module, Long orgId) {
		incrementCounter(module, orgId, OrganizationLevel.TOWN, 1);
	}

	// 将count的缓存计数器-1
	public void decreaseCounter(String module, Long orgId) {
		decreaseCounter(module, orgId, OrganizationLevel.TOWN, 1);
	}
}
