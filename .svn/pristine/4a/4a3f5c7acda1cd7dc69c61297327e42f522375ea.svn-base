package com.tianque.baseInfo.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.dao.SourcesStateDao;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

@Service("sourcesStateServiceImpl")
public class SourcesStateServiceImpl extends AbstractBaseService implements
		SourcesStateService {

	@Autowired
	private SourcesStateDao sourcesStateDao;

	public void updateSourcesStateById(String tableName, Long id,
			Long sourcesState) {
		if (id == null || sourcesState == null || tableName == null) {
			logger.error("类SourcesStateServicesImpl的updateSourcesStateById方法出现异常");
			throw new BusinessValidationException("修改数据来源错误");
		}
		if ("".equals(tableName)) {
			throw new BusinessValidationException("修改数据来源时，根据Type获得不到表名异常");
		}
		if (tableName.endsWith(DataManageBaseInfoTypes.COMPANYPLACE)) {
			tableName = "companyplacebase";
		}
		sourcesStateDao.updateSourcesStateById(tableName, id, sourcesState);
	}
}
