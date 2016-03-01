package com.tianque.plugin.transfer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.cache.PageInfoCacheThreadLocal;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.redis.utils.RedisDefaultPageUtils;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.transfer.handler.Handler;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.util.PopulationType;

@Service("transferService")
@Transactional
public class TransferServiceImpl implements TransferService {
	public final static Logger logger = LoggerFactory
			.getLogger(TransferServiceImpl.class);
	@Autowired
	@Qualifier("selfHandler")
	private Handler selfHandler;
	@Autowired
	@Qualifier("otherInfoHandler")
	private Handler otherInfoHandler;
	@Autowired
	@Qualifier("censusRegisterFamilyHandler")
	private Handler censusRegisterFamilyHandler;
	@Autowired
	@Qualifier("groupFamilyHandler")
	private Handler groupFamilyHandler;
	@Autowired
	@Qualifier("houseHandler")
	private Handler houseHandler;
	@Autowired
	@Qualifier("serviceTeamHandler")
	private Handler serviceTeamHandler;
	@Autowired
	@Qualifier("issueHandler")
	private Handler issueHandler;
	@Autowired
	@Qualifier("finalHandler")
	private Handler finalHandler;
	@Autowired
	private HousePopulationApplyService housePopulationApplyService;
	@Autowired
    @Qualifier("interviewHandler")
    private Handler interviewHandler;
	private void invoke(String type, Long id, Long toOrgId, Context context) {
		// 组装责任链
		List<Handler> handlers = new ArrayList<Handler>();
		handlers.add(selfHandler);
		handlers.add(otherInfoHandler);
		if (!(NewBaseInfoTables.ACTUALHOUSE_KEY.equals(type) || NewBaseInfoTables.RENTALHOUSE_KEY
				.equals(type))) {
			handlers.add(censusRegisterFamilyHandler);
			handlers.add(groupFamilyHandler);
			handlers.add(houseHandler);
			handlers.add(serviceTeamHandler);
			handlers.add(issueHandler);
			handlers.add(interviewHandler);
		}
		handlers.add(finalHandler);

		// 执行
		try {
			for (Handler handler : handlers) {
				handler.invoke(type, id, toOrgId, context);
			}

		} catch (Exception e) {
			throw new ServiceValidationException("数据转移时出现错误！", e);
		}
	}

	private void invoke(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
		// 组装责任链
		List<Handler> handlers = new ArrayList<Handler>();
		handlers.add(selfHandler);
		handlers.add(otherInfoHandler);
		if (!(NewBaseInfoTables.ACTUALHOUSE_KEY.equals(type) || NewBaseInfoTables.RENTALHOUSE_KEY
				.equals(type))) {
			handlers.add(censusRegisterFamilyHandler);
			handlers.add(groupFamilyHandler);
			handlers.add(houseHandler);
			handlers.add(serviceTeamHandler);
			handlers.add(issueHandler);
			handlers.add(interviewHandler);
		}
		handlers.add(finalHandler);

		// 执行
		try {
			for (Handler handler : handlers) {
				handler.invoke(type, id, toOrgId, fromOrgId, context);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("数据转移时出现错误！", e);
		}
	}

	private void validate(String type, Long id, Long toOrgId, Context context) {
		// 组装责任链
		List<Handler> handlers = new ArrayList<Handler>();
		handlers.add(selfHandler);
		// handlers.add(otherInfoHandler);

		// 执行
		for (Handler handler : handlers) {
			handler.validate(type, id, toOrgId, context);
		}
	}

	private void validate(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
		// 组装责任链
		List<Handler> handlers = new ArrayList<Handler>();
		handlers.add(selfHandler);
		// handlers.add(otherInfoHandler);

		// 执行
		for (Handler handler : handlers) {
			handler.validate(type, id, toOrgId, fromOrgId, context);
		}
	}

	private void invoke(String type, List<Long> ids, Long toOrgId,
			Context context) {
		for (Long id : ids) {
			context.getNewMap().clear();
			context.getOldMap().clear();
			context.setExistedToOrgId(false);
			invoke(type, id, toOrgId, context);
		}
	}

	private void invoke(String type, List<Long> ids, Long toOrgId,
			Long fromOrgId, Context context) {
		for (Long id : ids) {
			context.getNewMap().clear();
			context.getOldMap().clear();
			context.setExistedToOrgId(false);

			// 过滤只有人口才进行查询
			if (Constants.getClassNameByType(type) != null) {
				People fromPeople = TransferUtil
						.getPeopleByPopulationTypeAndId(type, id);
				if (fromPeople != null && fromPeople.getOrganization() != null
						&& fromPeople.getOrganization().getId() != null) {
					fromOrgId = fromPeople.getOrganization().getId();
				}
			}
			invoke(type, id, toOrgId, fromOrgId, context);

			// 过滤只有人口才进行缓存计数器增减
			if (Constants.getClassNameByType(type) != null) {
				getClassNameByType(type, fromOrgId, toOrgId, id, context);
			}
		}
	}

	@Override
	public List<ErrorMessageVo> validate(String type, List<Long> ids,
			Long toOrgId) {
		Context context = new Context();
		Map map = context.getMap();
		map.put(Constants.ERRORLIST, new ArrayList<ErrorMessageVo>());
		map.put(Constants.ERRORIDLIST, new ArrayList<Long>());
		for (Long id : ids) {
			validate(type, id, toOrgId, context);
		}
		List<Long> errorIdList = (List<Long>) map.get(Constants.ERRORIDLIST);
		for (Long id : errorIdList) {
			if (ids.contains(id)) {
				ids.remove(id);
			}
		}
		invoke(type, ids, toOrgId, context);
		return (List<ErrorMessageVo>) map.get(Constants.ERRORLIST);
	}

	@Override
	public List<ErrorMessageVo> validate(String type, List<Long> ids,
			Long toOrgId, Long fromOrgId) {
		Context context = new Context();
		Map map = context.getMap();
		map.put(Constants.ERRORLIST, new ArrayList<ErrorMessageVo>());
		map.put(Constants.ERRORIDLIST, new ArrayList<Long>());
		for (Long id : ids) {
			validate(type, id, toOrgId, fromOrgId, context);
		}
		List<Long> errorIdList = (List<Long>) map.get(Constants.ERRORIDLIST);
		for (Long id : errorIdList) {
			if (ids.contains(id)) {
				ids.remove(id);
			}
		}
		invoke(type, ids, toOrgId, fromOrgId, context);
		return (List<ErrorMessageVo>) map.get(Constants.ERRORLIST);
	}

	// 过滤 只进行人口模块相关的计数器
	private void getClassNameByType(String type, Long fromOrgId, Long toOrgId,
			Long id, Context context) {
		String objectType = PopulationType
				.getFirstCharUpperCaseTypeByType(type);
		if (!context.isExistedToOrgId() && objectType != null) {
			// 缓存计数器
			Organization fromOrg = new Organization();
			fromOrg.setId(fromOrgId);
			People fromPeople = new People();
			fromPeople.setId(id);
			fromPeople.setOrganization(fromOrg);
			type = StringUtil.firstCharUpperCase(objectType);
			String modelName = MemCacheConstant.getCachePageKey(type);
			PageInfoCacheThreadLocal.decrease(modelName, fromPeople, 1);
			Organization toOrg = new Organization();
			toOrg.setId(toOrgId);
			People toPeople = RedisDefaultPageUtils
					.getPeopleByPopulationTypeAndId(objectType, id, modelName,
							toOrg.getId());
			toPeople.setId(id);
			toPeople.setOrganization(toOrg);
			PageInfoCacheThreadLocal.increment(modelName, toPeople, 1);
		}
	}
}
