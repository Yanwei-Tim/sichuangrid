package com.tianque.baseInfo.youths.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.youths.constants.YouthsType;
import com.tianque.baseInfo.youths.dao.YouthsDao;
import com.tianque.baseInfo.youths.domain.Youths;
import com.tianque.baseInfo.youths.vo.SearchYouthsVo;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.impl.LogableService;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Service("youthsService")
public class YouthsServiceImpl extends LogableService implements YouthsService {
	@Autowired
	private YouthsDao youthsDao;

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public PageInfo<Youths> findYouthsForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchYouthsVo searchYouthsVo) {
		if (null == searchYouthsVo || null == searchYouthsVo.getKeyType()
				|| "".equals(searchYouthsVo.getKeyType())) {
			throw new BusinessValidationException("参数不正确");
		}
		searchYouthsVo.setShardCode(shardConversion.getShardCode(searchYouthsVo
				.getOrganization()));
		YouthsType.fillBeginAgeAndEndAge(searchYouthsVo, propertyDictService);
		PageInfo<Youths> youths = youthsDao.findYouthsForPage(pageNum, pageSize, sortField, order,
				searchYouthsVo);
		//隐藏身份证中间4位
		youths=hiddenIdCard(youths);
		return youths;
	}
	
	//隐藏身份证中间4位
		private PageInfo<Youths> hiddenIdCard(PageInfo<Youths> pageInfo){
						//判断权限，有权限不隐藏
						if(permissionDubboService.
								isUserHasPermission(ThreadVariable.getUser().getId(), "isYoungstersManagementNotHidCard")){
							return pageInfo;
						}
						List<Youths> list = pageInfo.getResult();
						int index=0;
						for (Youths verification:list) {
							verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
							list.set(index, verification);
							index++;
						}
						pageInfo.setResult(list);
						return pageInfo;
		}

	@Override
	public Integer getYouthsCount(SearchYouthsVo searchYouthsVo) {
		if (null == searchYouthsVo) {
			throw new BusinessValidationException("参数不正确");
		}
		searchYouthsVo.setShardCode(shardConversion.getShardCode(searchYouthsVo
				.getOrganization()));
		return youthsDao.getYouthsCount(searchYouthsVo);
	}
}
