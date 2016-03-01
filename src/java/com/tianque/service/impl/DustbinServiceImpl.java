package com.tianque.service.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.DustbinDao;
import com.tianque.domain.Dustbin;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypesYinchuan;
import com.tianque.domain.vo.SearchDustbinVo;
import com.tianque.excel.definition.SpecialGroupsContextYinchuan;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.service.DustbinService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.Assert;

@Service("dustbinService")
@Transactional
public class DustbinServiceImpl extends LogableService implements
		DustbinService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DustbinDao dustbinDao;

	@Qualifier("dustbinValidator")
	@Autowired
	private DomainValidator<Dustbin> domainValidator;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public PageInfo<Dustbin> findDustbinForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			Boolean isEmphasis, String partType) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if ("publicFacilities".equals(partType)) {

			}
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				Long partTypeId = null;
				PropertyDict dict = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypesYinchuan.PART_TYPE,
								getPartTypeName(partType));
				if (dict != null) {
					partTypeId = dict.getId();
				}
				return dustbinDao.findDustbinForPageByOrgInternalCode(
						org.getOrgInternalCode(), pageNum, pageSize, sidx,
						sord, isEmphasis != null && isEmphasis ? 1L : 0L,
						partType, partTypeId);
			}
		}
	}

	private Dustbin getDustbinByName(String unitName, Long orgId) {
		return dustbinDao.getDustbinByUnitName(unitName, orgId);
	}

	private PageInfo<Dustbin> constructEmptyPageInfo() {
		PageInfo<Dustbin> result = new PageInfo<Dustbin>();
		result.setResult(new ArrayList<Dustbin>());
		return result;
	}

	@Override
	public Dustbin getDustbinById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("部件信息id不合法");
		}
		return dustbinDao.getDustbinById(id);
	}

	@Override
	public Dustbin addDustbin(Dustbin dustbin) {
		try {
			dangerCheUnitValidate(dustbin);
			autoFillOrganizationInternalCode(dustbin);
			dustbin = dustbinDao.addDustbin(dustbin);
			return dustbin;
		} catch (Exception e) {
			return dealException(this, "addDustbin", "新增部件信息类出现错误", e);
		}

	}

	@Override
	public Dustbin updateDustbin(Dustbin dustbin) {
		validateUpdate(dustbin);
		dangerCheUnitValidate(dustbin);
		autoFillOrganizationInternalCode(dustbin);
		dustbin = dustbinDao.updateDustbin(dustbin);
		return dustbin;
	}

	@Override
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.DUSTBIN_KEY)
	public void deleteDustbinByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("id没有获得");
		}
		for (int i = 0; i < ids.length; i++) {
			deleteDustbinById(ids[i]);
		}
	}

	private void deleteDustbinById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("id没有获得");
		}
		try {
			Dustbin dustbin = dustbinDao.getDustbinById(id);
			log(WARN, DRUGGY, ThreadVariable.getSession().getUserName()
					+ "删除垃圾箱" + dustbin.getDustbinCode(), OperatorType.DELETE,
					ObjectToJSON.convertJSON(dustbin));
		} catch (Exception e) {
			throw new BusinessValidationException(e.getMessage());
		}
		dustbinDao.deleteDustbinById(id);
	}

	private void validateUpdate(Dustbin update) {
		Dustbin dustbin = this.dustbinDao.getDustbinById(update.getId());
		if (null != update.getOrganization()
				&& null != update.getOrganization().getId()
				&& !update.getOrganization().getId()
						.equals(dustbin.getOrganization().getId())) {
			throw new BusinessValidationException("所属网格不能修改");
		}
		if (null != update.getCreateUser()
				&& !update.getCreateUser().equals(dustbin.getCreateUser())) {
			throw new BusinessValidationException("创建人不能修改");
		}
		if (null != update.getCreateDate()
				&& !update.getCreateDate().equals(dustbin.getCreateDate())) {
			throw new BusinessValidationException("创建时间不能修改");
		}
	}

	private void autoFillOrganizationInternalCode(Dustbin dustbin) {
		Organization org = organizationDubboService.getSimpleOrgById(dustbin
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		dustbin.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void dangerCheUnitValidate(Dustbin dustbin) {
		ValidateResult dangerCheUnitValidator = domainValidator
				.validate(dustbin);
		if (dangerCheUnitValidator.hasError()) {
			throw new BusinessValidationException(
					dangerCheUnitValidator.getErrorMessages());
		}
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		for (int i = 0; i < ids.length; i++) {
			updateEmphasiseById(ids[i], isEmphasis, logoutReason, logoutDate);
		}
	}

	private void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		dustbinDao
				.updateEmphasiseById(id, isEmphasis, logoutReason, logoutDate);
	}

	@Override
	public Dustbin getDustbinByDustbinCode(String dustbinCode, Long orgId) {
		return dustbinDao.getDustbinByDustbinCode(dustbinCode, orgId);
	}

	@Override
	public boolean hasDuplicateDustbin(Dustbin dustbin) {
		Dustbin hasDustbin = getDustbinByDustbinCode(dustbin.getDustbinCode(),
				dustbin.getOrganization().getId());
		return hasDustbin != null;
	}

	@Override
	public PageInfo<Dustbin> searchDustbinPagerBySearchVo(Long orgid,
			SearchDustbinVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order, String partType) {
		Assert.notNull(searchVo, new BusinessValidationException("参数错误!"));
		String orgInternalCode = assertAndGetOrgInternalCode(orgid);
		searchVo.setOrgInternalCode(orgInternalCode);
		searchVo.setPartType(partType);
		searchVo.setTypeName(PropertyTypesYinchuan.PART_TYPE);
		searchVo.setPartTypeName(getPartTypeName(partType));
		try {
			PropertyDict dict = propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							searchVo.getTypeName(), searchVo.getPartTypeName());
			if (dict != null) {
				searchVo.setPartTypeId(dict.getId());
			}
			return dustbinDao.searchDustbinPagerBySearchVo(searchVo, pageNum,
					pageSize, sortField, order, null);
		} catch (Exception e) {
			return dealException(this, "searchDustbinPagerBySearchVo",
					"查询部件信息数据列表时出现错误!", e);
		}
	}

	private String getPartTypeName(String partType) {
		if ("publicFacilities".equals(partType)) {
			return PropertyTypesYinchuan.PUBLIC_FACILITIES;
		} else if ("roadTraffic".equals(partType)) {
			return PropertyTypesYinchuan.ROAD_TRAFFIC;
		} else if ("cityEnvironmrnt".equals(partType)) {
			return PropertyTypesYinchuan.CITY_ENVIRONMENT;
		} else if ("landscaping".equals(partType)) {
			return PropertyTypesYinchuan.LANDSCAPING;
		} else if ("houseLand".equals(partType)) {
			return PropertyTypesYinchuan.HOUSELAND;
		} else if ("otherFacilities".equals(partType)) {
			return PropertyTypesYinchuan.OTHER_FACILITIES;
		} else if ("expansionComponents".equals(partType)) {
			return PropertyTypesYinchuan.EXPANSION_COMPONENTS;
		}
		return null;
	}

	private String assertAndGetOrgInternalCode(Long orgid) {
		Assert.notNull(orgid, new BusinessValidationException("网格为空!"));
		Organization organization = organizationDubboService.getSimpleOrgById(orgid);
		Assert.notNull(organization, new BusinessValidationException("网格不存在!"));
		return organization.getOrgInternalCode();
	}

	@Override
	public String[][] getExportPopertyArray(String partyName) {
		return SpecialGroupsContextYinchuan.getDustbinPropertyArray(partyName);
	}

	@Override
	public String getVideoParamterById(Long id) {
		Dustbin dustbin = getDustbinById(id);
		String dustbinCode = dustbin.getDustbinCode();
		if (!StringUtil.isStringAvaliable(dustbinCode)) {
			throw new BusinessValidationException(dustbinCode);
		}
		String url = GridProperties.VIDEOURL + dustbinCode
				+ "/getLinkVideoParamInfo";
		HttpClient client = new HttpClient();
		HttpMethod httpMethod = null;
		try {
			URL _url = new URL(url);
			String host = _url.getHost();
			String path = _url.getPath();
			int port = _url.getPort();
			port = port != -1 ? port : 8080;
			String query = _url.getQuery();
			url = "http://"
					+ host
					+ ":"
					+ port
					+ path
					+ (query != null && !"".equals(query.trim()) ? "?" + query
							: "");
			httpMethod = new GetMethod(url);
			client.executeMethod(httpMethod);
			String content = httpMethod.getResponseBodyAsString();
			return content;
		} catch (Exception e) {
			throw new BusinessValidationException(e.getMessage());
		} finally {
			if (httpMethod != null)
				httpMethod.releaseConnection();
		}
	}
}
