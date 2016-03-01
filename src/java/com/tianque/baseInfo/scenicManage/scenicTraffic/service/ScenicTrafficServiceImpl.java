package com.tianque.baseInfo.scenicManage.scenicTraffic.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.scenicManage.scenicTraffic.dao.ScenicTrafficDAO;
import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.baseInfo.scenicManage.scenicTraffic.vo.SearchScenicTrafficVo;
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

/**
 * @ClassName: ScenicTrafficServiceImpl
 * @Description: 景区交通
 * @author wangxiaohu wsmalltiger@163.com
 * @date Oct 31, 2013 11:34:02 AM
 */
@Service("scenicTrafficService")
public class ScenicTrafficServiceImpl extends LogableService implements
		ScenicTrafficService {
	@Autowired
	private ScenicTrafficDAO scenicTrafficDAO;
	@Autowired
	@Qualifier("scenicTrafficValidator")
	private DomainValidator<ScenicTraffic> scenicTrafficValidateImpl;
	@Autowired
	private KeyPlaceService placeService;

	private void autoFillChinesePinyin(ScenicTraffic scenicTraffic) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(scenicTraffic.getTrafficName());
		scenicTraffic.setFullPinYin((String) pinyin.get("fullPinyin"));
		scenicTraffic.setSimplePinYin((String) pinyin.get("simplePinyin"));
	}

	private void validatePlace(ScenicTraffic scenicTraffic) {
		ValidateResult validateResult = scenicTrafficValidateImpl
				.validate(scenicTraffic);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
	}

	@Override
	public ScenicTraffic addScenicTraffic(ScenicTraffic scenicTraffic) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validatePlace(scenicTraffic);
			}
			autoFillChinesePinyin(scenicTraffic);
			ScenicTraffic scenicTraffic1 = scenicTrafficDAO
					.addScenicTraffic(scenicTraffic);
			placeService.execute(DocumentType.SCENICTRAFFIC,
					OperateMode.ADD.toString(), scenicTraffic1);
			return scenicTraffic1;
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
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.SCENICTRAFFIC_KEY)
	public void deleteScenicTrafficByIds(Long[] ids) {
		if (ids == null) {
			throw new BusinessValidationException("删除信息出现错误");
		}
		for (Long id : ids) {
			deleteScenicTrafficById(id);
		}
	}

	private void deleteScenicTrafficById(Long id) {
		ScenicTraffic domain = getScenicTrafficById(id);
		log(WARN, SCENICTRAFFIC, ThreadVariable.getSession().getUserName()
				+ "删除景区交通" + domain.getTrafficName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(domain));
		scenicTrafficDAO.deleteScenicTrafficById(id);
		placeService.execute(DocumentType.SCENICTRAFFIC,
				OperateMode.DELETE.toString(), domain);
	}

	@Override
	public ScenicTraffic getScenicTrafficById(Long id) {
		return scenicTrafficDAO.getScenicTrafficById(id);
	}

	@Override
	public PageInfo searchScenicTrafficForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchScenicTrafficVo searchScenicTrafficVo) {
		return scenicTrafficDAO.searchScenicTrafficForPage(pageNum, pageSize,
				sortField, order, searchScenicTrafficVo);
	}

	@Override
	public ScenicTraffic updateEmphasiseById(Long[] ids,
			ScenicTraffic scenicTraffic) {
		if (ids == null) {
			throw new BusinessValidationException("关注/取消关注出现错误");
		}
		for (Long id : ids) {
			scenicTrafficDAO.updateEmphasiseById(id,
					scenicTraffic.getIsEmphasis(),
					scenicTraffic.getLogOutReason(),
					scenicTraffic.getLogOutTime());
			Long emphasis;
			if (scenicTraffic.getIsEmphasis()) {
				emphasis = EmphasisType.isNotEmphasis;
			} else {
				emphasis = EmphasisType.Emphasis;
			}
			placeService.emphasisAndNotEmphasis(id,
					DocumentType.SCENICTRAFFIC.toString(), emphasis);
		}
		return scenicTraffic;
	}

	@Override
	public ScenicTraffic updateScenicTraffic(ScenicTraffic scenicTraffic) {
		validatePlace(scenicTraffic);
		autoFillChinesePinyin(scenicTraffic);
		ScenicTraffic scenicTraffic1 = scenicTrafficDAO
				.updateScenicTraffic(scenicTraffic);
		placeService.execute(DocumentType.SCENICTRAFFIC,
				OperateMode.EDIT.toString(), scenicTraffic1);
		return scenicTraffic1;
	}

	@Override
	public ScenicTraffic updateScenicTrafficForImport(
			ScenicTraffic scenicTraffic) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validatePlace(scenicTraffic);
			}
			autoFillChinesePinyin(scenicTraffic);
			scenicTraffic.setId(scenicTraffic.getId());
			ScenicTraffic scenicTraffic1 = scenicTrafficDAO
					.updateScenicTraffic(scenicTraffic);
			return scenicTraffic1;
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
	public Integer getCount(SearchScenicTrafficVo searchScenicTrafficVo) {
		return scenicTrafficDAO.getCount(searchScenicTrafficVo);
	}

}
