package com.tianque.baseInfo.scenicManage.scenicEquipment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.scenicManage.scenicEquipment.dao.ScenicEquipmentDao;
import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.baseInfo.scenicManage.scenicEquipment.vo.SearchScenicEquipmentVo;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.openLayersMap.util.EmphasisType;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.solr.domain.DocumentType;

@Service("scenicEquipmentService")
public class ScenicEquipmentServiceImpl extends LogableService implements
		ScenicEquipmentService {
	@Autowired
	private ScenicEquipmentDao scenicEquipmentDao;
	@Autowired
	@Qualifier("scenicEquipmentValidator")
	private DomainValidator<ScenicEquipment> scenicEquipmentValidateImpl;
	@Autowired
	private KeyPlaceService placeService;

	private void autoFillChinesePinyin(ScenicEquipment scenicEquipment) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(scenicEquipment.getEquipName());
		scenicEquipment.setFullPinyin((String) pinyin.get("fullPinyin"));
		scenicEquipment.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private void validateEquip(ScenicEquipment scenicEquipment) {
		ValidateResult validateResult = scenicEquipmentValidateImpl
				.validate(scenicEquipment);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
	}

	@Override
	public ScenicEquipment addScenicEquipment(ScenicEquipment scenicEquipment) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validateEquip(scenicEquipment);
			}
			autoFillChinesePinyin(scenicEquipment);
			ScenicEquipment scenicEquipment1 = scenicEquipmentDao
					.addScenicEquipment(scenicEquipment);
			placeService.execute(DocumentType.SCENICEQUIPMENT,
					OperateMode.ADD.toString(), scenicEquipment1);
			return scenicEquipment1;
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public ScenicEquipment getScenicEquipmentById(Long id) {
		return scenicEquipmentDao.getScenicEquipmentById(id);
	}

	@Override
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.SCENICEQUIPMENT_KEY)
	public void deleteScenicEquipmentByIds(Long[] ids) {
		for (Long id : ids) {
			deleteScenicEquipmentById(id);
		}
	}

	private void deleteScenicEquipmentById(Long id) {
		ScenicEquipment domain = getScenicEquipmentById(id);
		log(WARN, SCENICSEQUIPMENT, ThreadVariable.getSession().getUserName()
				+ "删除景点配置" + domain.getEquipName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(domain));
		scenicEquipmentDao.deleteScenicEquipmentById(id);
		placeService.execute(DocumentType.SCENICEQUIPMENT,
				OperateMode.DELETE.toString(), domain);
	}

	@Override
	public ScenicEquipment updateScenicEquipment(ScenicEquipment scenicEquipment) {
		validateEquip(scenicEquipment);
		autoFillChinesePinyin(scenicEquipment);
		ScenicEquipment scenicEquipment1 = scenicEquipmentDao
				.updateScenicEquipment(scenicEquipment);
		placeService.execute(DocumentType.SCENICEQUIPMENT,
				OperateMode.EDIT.toString(), scenicEquipment1);
		return scenicEquipment1;
	}

	@Override
	public ScenicEquipment updateEmphasiseById(Long id, ScenicEquipment location) {
		validateEquip(location);
		scenicEquipmentDao.updateEmphasiseById(id, location.getIsEmphasis(),
				location.getLogOutReason(), location.getLogOutTime());
		return scenicEquipmentDao.getScenicEquipmentById(id);
	}

	@Override
	public PageInfo<ScenicEquipment> searchScenicEquipmentForPage(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchScenicEquipmentVo searchScenicEquipmentVo) {
		return scenicEquipmentDao.searchScenicEquipmentForPage(pageNum,
				pageSize, sortField, order, searchScenicEquipmentVo);
	}

	@Override
	public ScenicEquipment updateScenicEquipmentForImport(
			ScenicEquipment scenicEquipment) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validateEquip(scenicEquipment);
			}
			autoFillChinesePinyin(scenicEquipment);
			scenicEquipment.setId(scenicEquipment.getId());
			ScenicEquipment scenicEquipment1 = scenicEquipmentDao
					.updateScenicEquipment(scenicEquipment);
			return scenicEquipment1;
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
	public Integer getCount(SearchScenicEquipmentVo searchScenicEquipmentVo) {
		// TODO Auto-generated method stub
		return scenicEquipmentDao.getCount(searchScenicEquipmentVo);
	}

	@Override
	public List<ScenicEquipment> updateEmphasisByIdList(Long[] idList,
			ScenicEquipment location) {
		validatorIdList(idList);
		List<ScenicEquipment> scenicEquipmentList = new ArrayList<ScenicEquipment>();
		for (Long id : idList) {
			ScenicEquipment scenicEquipment = scenicEquipmentDao
					.getScenicEquipmentById(id);
			scenicEquipment.setIsEmphasis(location.getIsEmphasis());
			scenicEquipment.setLogOutReason(location.getLogOutReason());
			scenicEquipment.setLogOutTime(location.getLogOutTime());
			scenicEquipment = updateScenicEquipment(scenicEquipment);
			scenicEquipmentList.add(scenicEquipment);
			Long emphasis;
			if (scenicEquipment.getIsEmphasis()) {
				emphasis = EmphasisType.isNotEmphasis;
			} else {
				emphasis = EmphasisType.Emphasis;
			}
			placeService.emphasisAndNotEmphasis(id,
					DocumentType.SCENICEQUIPMENT.toString(), emphasis);
		}

		return scenicEquipmentList;
	}

	public List<Long> validatorIdList(Long[] idList) {
		if (null == idList) {
			throw new BusinessValidationException("配套设施idList不能为空");
		}
		List<Long> list = new ArrayList<Long>();
		for (Long id : idList) {
			if (null == id || id < 0L) {
				throw new BusinessValidationException("配套设施id不合法");
			}
			list.add(id);
		}
		return list;
	}

}
