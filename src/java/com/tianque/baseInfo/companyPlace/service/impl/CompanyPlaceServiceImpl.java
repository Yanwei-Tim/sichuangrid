package com.tianque.baseInfo.companyPlace.service.impl;

import java.util.List;

import org.oproject.framework.orm.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.dao.CompanyPlaceDAO;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.dataManage.location.companyPlaceTemp.service.CompanyPlaceTempServiceImpl;
import com.tianque.service.impl.LogableService;

@Service("companyPlaceService")
@Transactional
public class CompanyPlaceServiceImpl extends LogableService implements
		CompanyPlaceService {
	@Autowired
	private CompanyPlaceDAO companyPlaceDAO;
	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseSevice;

	public final static Logger logger = LoggerFactory
			.getLogger(CompanyPlaceTempServiceImpl.class);

	@Override
	public Long addCompanyPlaceToBase(CompanyPlace companyPlace) {
		if (companyPlace == null) {
			throw new BusinessValidationException("新增出错");
		}
		try {
			return companyPlaceDAO.addCompanyPlace(companyPlace);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceServiceImpl的addCompanyPlace方法出现异常，原因：",
					"新增单位场所出错！", e);
		}
	}

	@Override
	public CompanyPlace addCompanyPlace(CompanyPlace companyPlace) {
		if (companyPlace == null) {
			throw new BusinessValidationException("新增出错");
		}
		try {
			return companyPlaceBaseSevice.addCompanyPlaceBase(companyPlace,
					companyPlace.getClassifiCationEn(), null);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceServiceImpl的addCompanyPlace方法出现异常，原因：",
					"新增单位场所出错！", e);
		}
	}

	@Override
	public boolean deleteCompanyPlace(Long id) {
		if (id == null) {
			throw new BusinessValidationException("删除出错");
		}
		int result = companyPlaceDAO.deleteCompanyPlace(id);
		return result > 0;
	}

	@Override
	public void deleteCompanyPlaceByIds(Long[] ids) {
		StringBuffer baseIdsBuffer = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];
			if (id != null) {
				baseIdsBuffer.append(id + ",");
			}
		}
		String baseIds = new String(baseIdsBuffer.substring(0,
				baseIdsBuffer.length() - 1));
		companyPlaceBaseSevice.delteCompanyPlaceBaseByIds(baseIds, null);
	}

	@Override
	public CompanyPlace updateCompanyPlace(CompanyPlace companyPlace) {
		if (companyPlace == null) {
			throw new BusinessValidationException("修改出错");
		}
		try {
			companyPlaceDAO.updateCompanyPlace(companyPlace);
			return companyPlaceDAO.readeCompanyPlace(companyPlace.getId());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceServiceImpl的updateCompanyPlace方法出现异常，原因：",
					"单位场所修改出错！", e);
		}

	}

	@Override
	public CompanyPlace readeCompanyPlace(Long baseId) {
		if (baseId == null) {
			throw new BusinessValidationException("传参出错");
		}
		return companyPlaceDAO.readeCompanyPlace(baseId);
	}

	@Override
	public void batchDeleteCompanyPlace(List<Long> ids) {
		try {
			companyPlaceDAO.batchDeleteCompanyPlace(ids);
		} catch (Exception e) {
			throw new ServiceValidationException("删除出现错误！", e);
		}
	}

	@Override
	public PageResult<CompanyPlace> queryCompanyPlaceForPageResult(
			CompanyPlace companyPlace, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		companyPlace.setOrder(sord);
		companyPlace.setSortField(sidx);
		return companyPlaceDAO.queryCompanyPlaceForPageResult(companyPlace,
				pageNum, pageSize);
	}

	@Override
	public List<CompanyPlace> queryCompanyPlaceForList(CompanyPlace companyPlace) {
		return companyPlaceDAO.queryCompanyPlaceForList(companyPlace);
	}

	@Override
	public int getCount(CompanyPlace companyPlace) {
		return companyPlaceDAO.getCount(companyPlace);
	}

	@Override
	public void updateCompanyPlaceByCid(CompanyPlace companyPlace) {
		try {
			companyPlaceDAO.updateCompanyPlaceByCid(companyPlace);
		} catch (Exception e) {
			throw new ServiceValidationException("更新出现错误！", e);
		}
	}

	@Override
	public CompanyPlace hasDuplicateCompanyPlace(Long orgId, String placeName,
			Long typeId) {
		try {
			return companyPlaceBaseSevice.hasDuplicateCompanyPlace(orgId,
					placeName, typeId);
		} catch (Exception e) {
			throw new ServiceValidationException("根据名称查询出现错误!", e);
		}
	}

	@Override
	public void recoverCompanyPlaceForRecover(CompanyPlace companyPlace) {
		try {
			companyPlaceDAO.addCompanyPlaceForRecover(companyPlace);
		} catch (Exception e) {
			throw new ServiceValidationException("恢复单位场所信息出现错误", e);
		}
	}
}
