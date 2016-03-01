package com.tianque.baseInfo.companyPlace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.dao.CompanyPlaceAnnexDAO;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceAnnex;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceAnnexService;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.ServiceValidationException;

@Service("companyPlaceAnnexService")
@Transactional
public class CompanyPlaceAnnexServiceImpl extends AbstractBaseService implements
		CompanyPlaceAnnexService {
	@Autowired
	private CompanyPlaceAnnexDAO companyPlaceAnnexDao;
	private List<CompanyPlaceAnnex> companyPlaceAnnexs;

	@Override
	public CompanyPlaceAnnex addCompanyPlaceAnnex(
			CompanyPlaceAnnex companyPlaceAnnex) {
		try {
			if (companyPlaceAnnex != null) {
				companyPlaceAnnexDao.addCompanyPlaceAnnex(companyPlaceAnnex);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceAnnexServiceImpl的addCompanyPlaceAnnex方法出现异常，原因：",
					"新增单位场所业务附件信息出现错误", e);
		}
		return null;
	}

	@Override
	public void updateCompanyPlaceAnnex(CompanyPlaceAnnex companyPlaceAnnex) {
		try {
			if (companyPlaceAnnex != null) {
				companyPlaceAnnexDao.updateCompanyPlaceAnnex(companyPlaceAnnex);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceAnnexServiceImpl的updateCompanyPlaceAnnex方法出现异常，原因：",
					"修改单位场所业务附件信息出现错误", e);
		}

	}

	@Override
	public CompanyPlaceAnnex getCompanyPlaceAnnex(Long id) {
		CompanyPlaceAnnex companyPlaceAnnex = null;
		try {
			companyPlaceAnnex = companyPlaceAnnexDao.getCompanyPlaceAnnex(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceAnnexServiceImpl的getCompanyPlaceAnnex方法出现异常，原因：",
					"单条查询单位场所业务附件信息出现错误", e);
		}
		return companyPlaceAnnex;
	}

	@Override
	public List<CompanyPlaceAnnex> queryCompanyPlaceAnnexByBusinessForList(
			Long businessId) {
		try {
			if (businessId != null) {
				companyPlaceAnnexs = companyPlaceAnnexDao
						.queryCompanyPlaceAnnexByBusinessForList(businessId);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceAnnexServiceImpl的getCompanyPlaceAnnex方法出现异常，原因：",
					"根据业务查询附件信息出现错误", e);
		}
		return companyPlaceAnnexs;
	}

	@Override
	public void deleteCompanyPlaceAnnex(Long id) {
		try {
			if (id != null) {
				companyPlaceAnnexDao.deleteCompanyPlaceAnnex(id);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceAnnexServiceImpl的deleteCompanyPlaceAnnex方法出现异常，原因：",
					"删除附件信息出现错误", e);
		}
	}

	@Override
	public void deleteCompanyPlaceAnnexForBusinessId(Long businessId) {
		try {
			if (businessId != null) {
				companyPlaceAnnexDao
						.deleteCompanyPlaceAnnexForBusinessId(businessId);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceAnnexServiceImpl的deleteCompanyPlaceAnnex方法出现异常，原因：",
					"删除附件信息出现错误", e);
		}

	}

	public List<CompanyPlaceAnnex> getCompanyPlaceAnnexs() {
		return companyPlaceAnnexs;
	}

	public void setCompanyPlaceAnnexs(List<CompanyPlaceAnnex> companyPlaceAnnexs) {
		this.companyPlaceAnnexs = companyPlaceAnnexs;
	}
}
