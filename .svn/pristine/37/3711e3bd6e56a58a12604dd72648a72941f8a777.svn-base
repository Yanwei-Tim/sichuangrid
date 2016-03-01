package com.tianque.baseInfo.scenicManage.scenicSpot.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.scenicManage.scenicSpot.dao.ScenicSpotDao;
import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.baseInfo.scenicManage.scenicSpot.validator.ScenicSpotValidatorImpl;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.openLayersMap.util.EmphasisType;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("scenicSpotService")
public class ScenicSpotServiceImpl extends LogableService implements
		ScenicSpotService {

	@Autowired
	@Qualifier("scenicSpotValidator")
	private ScenicSpotValidatorImpl scenicSpotValidatorImpl;
	@Autowired
	private ScenicSpotDao scenicSpotDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private KeyPlaceService placeService;

	@Override
	public ScenicSpot addScenicSpot(ScenicSpot scenicSpot) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				scenicSpotValidatorImpl = new ScenicSpotValidatorImpl();
				scenicSpotValidatorImpl
						.setOrganizationDubboService(organizationDubboService);
				scenicSpotValidatorImpl.setValidateHelper(validateHelper);
				ValidateResult scenicSpotValidator = ((DomainValidator<ScenicSpot>) scenicSpotValidatorImpl)
						.validate(scenicSpot);
				if (scenicSpotValidator.hasError()) {
					throw new BusinessValidationException(
							scenicSpotValidator.getErrorMessages());
				} else if (hasDuplicateScenicSpotName(scenicSpot
						.getOrganization().getId(), scenicSpot.getName(),
						scenicSpot.getId())) {
					throw new BusinessValidationException("该网格下已存在相同名称的景点");
				}
				ScenicSpotServiceHelper.checkScenicSpot(scenicSpot);
			}
			this.autoFillOrganizationInternalCode(scenicSpot);
			this.autoFillChinesePinyin(scenicSpot);
			scenicSpot = scenicSpotDao.addScenicSpot(scenicSpot);
			placeService.execute(DocumentType.SCENICSPOTS,
					OperateMode.ADD.toString(), scenicSpot);
			return scenicSpot;
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		}
	}

	private void autoFillOrganizationInternalCode(ScenicSpot scenicSpot) {
		if (scenicSpot.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(scenicSpot.getOrganization().getId());
			if (organization == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			scenicSpot.setOrgInternalCode(organization.getOrgInternalCode());
		}

	}

	private void autoFillChinesePinyin(ScenicSpot scenicSpot) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(scenicSpot.getName());
		scenicSpot.setFullPinyin((String) pinyin.get("fullPinyin"));
		scenicSpot.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	@Override
	public boolean deleteScenicSpotById(Long id) {
		ScenicSpot domain = getScenicSpotById(id);
		log(WARN, SCENICSSPOT, ThreadVariable.getSession().getUserName()
				+ "删除景点" + domain.getName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(domain));
		scenicSpotDao.deleteScenicSpotById(id);
		placeService.execute(DocumentType.SCENICSPOTS,
				OperateMode.DELETE.toString(), domain);
		return true;
	}

	@Override
	public PageInfo<ScenicSpot> findScenicSpotList(ScenicSpot scenicSpot,
			Integer page, Integer rows, String sidx, String sord,
			Boolean isEmphasis) {
		return scenicSpotDao.findScenicSpotList(scenicSpot, page, rows, sidx,
				sord, isEmphasis);
	}

	@Override
	public ScenicSpot getScenicSpotById(Long id) {
		return scenicSpotDao.getSimpleScenicSpotById(id);
	}

	@Override
	public ScenicSpot updateScenicSpot(ScenicSpot scenicSpot) {
		scenicSpotValidatorImpl = new ScenicSpotValidatorImpl();
		scenicSpotValidatorImpl.setOrganizationDubboService(organizationDubboService);
		scenicSpotValidatorImpl.setValidateHelper(validateHelper);
		ValidateResult scenicSpotValidator = ((DomainValidator<ScenicSpot>) scenicSpotValidatorImpl)
				.validate(scenicSpot);
		if (scenicSpotValidator.hasError()) {
			throw new BusinessValidationException(
					scenicSpotValidator.getErrorMessages());
		} else if (hasDuplicateScenicSpotName(scenicSpot.getOrganization()
				.getId(), scenicSpot.getName(), scenicSpot.getId())) {
			throw new BusinessValidationException("该网格下已存在相同名称的景点");
		}
		this.autoFillOrganizationInternalCode(scenicSpot);
		this.autoFillChinesePinyin(scenicSpot);
		scenicSpot = scenicSpotDao.updateScenicSpot(scenicSpot);
		placeService.execute(DocumentType.SCENICSPOTS,
				OperateMode.EDIT.toString(), scenicSpot);
		return scenicSpot;
	}

	@Override
	public ScenicSpot findScenicSpotByNameAndOrgId(String name, Long orgId) {
		return scenicSpotDao.findScenicSpotByNameAndOrgId(name, orgId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScenicSpot> findScenicSpotByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode) {
		return scenicSpotDao.findScenicSpotByNameAndPinyinAndOrgInternalCode(
				name, orgInternalCode);
	}

	@Override
	public ScenicSpot updateScenicSpotByName(String chineseName, Long orgId,
			ScenicSpot domain) {
		try {
			ScenicSpot older = scenicSpotDao.getScenicSpotByName(chineseName,
					orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateScenicSpot(domain);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public boolean hasDuplicateScenicSpotName(Long ownerOrgId, String name,
			Long exceptedId) {
		ScenicSpot exsited = scenicSpotDao
				.getScenicSpotByName(name, ownerOrgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	public ScenicSpot updateScenicSpotByEmphasis(ScenicSpot scenicSpot) {
		return scenicSpotDao.updateScenicSpot(scenicSpot);
	}

	@Override
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.SCENICSPOT_KEY)
	public List<Long> deleteScenicSpotByIds(List<Long> placeIds) {
		for (Long id : placeIds) {
			deleteScenicSpotById(id);
		}
		return placeIds;
	}

	@Override
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.SCENICSPOT_KEY)
	public List<Long> deleteScenicSpotByIds(Long[] ids) {
		return this.deleteScenicSpotByIds(Arrays.asList(ids));
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, ScenicSpot location) {
		for (int i = 0; i < ids.length; i++) {
			Boolean isEmphasis = false;
			if (location.getIsEmphasis()) {
				isEmphasis = true;
			}
			updateEmphasiseById(ids[i], isEmphasis, location.getLogOutReason(),
					location.getLogOutTime());
		}
	}

	private void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate) {
		scenicSpotDao.updateEmphasiseById(id, isEmphasis, logoutReason,
				logoutDate);
		Long emphasis;
		if (isEmphasis) {
			emphasis = EmphasisType.isNotEmphasis;
		} else {
			emphasis = EmphasisType.Emphasis;
		}
		placeService.emphasisAndNotEmphasis(id,
				DocumentType.SCENICSPOTS.toString(), emphasis);// 更新keyPlaces表的状态
	}

	@Override
	public ScenicSpot hasDuplicateScenicSpot(Long orgId, String chineseName) {
		return scenicSpotDao.getScenicSpotByName(chineseName, orgId);
	}

}
