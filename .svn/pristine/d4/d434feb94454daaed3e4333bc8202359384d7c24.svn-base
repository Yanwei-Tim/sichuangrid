package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.SysGisFunctionDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.SysGisFunctionService;

/**
 * gis功能(精确搜索、辖区分布、图层搜索)管理service实现类
 * 
 * @author yubin
 * 
 */
@Transactional
@Service("sysGisFunctionService")
public class SysGisFunctionServiceImpl extends BaseService implements
		SysGisFunctionService {

	@Autowired
	private SysGisFunctionDao sysGisFunctionDao;
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public GisFunction addGisFunction(GisFunction gisFunction) {
		validateGisFunction(gisFunction);
		return sysGisFunctionDao.addGisFunction(gisFunction);
	}

	@Override
	public GisFunction getGisFunctionById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("id不能为空!");
		}
		return sysGisFunctionDao.getGisFunctionById(id);
	}

	@Override
	public void deleteGisFunctionByModuleId(Long moduleId) {
		if (null == moduleId) {
			throw new BusinessValidationException("moduleId不能为空!");
		}
		sysGisFunctionDao.deleteGisFunctionByModuleId(moduleId);
	}

	@Override
	public void deleteGisFunctionBySonClassId(Long sonClassId) {
		if (null == sonClassId) {
			throw new BusinessValidationException("sonClassId不能为空!");
		}
		sysGisFunctionDao.deleteGisFunctionBySonClassId(sonClassId);
	}

	@Override
	public GisFunction updateGisFunction(GisFunction gisFunction) {
		validateGisFunction(gisFunction);
		return sysGisFunctionDao.updateGisFunction(gisFunction);
	}

	@Override
	public GisFunction getGisFunctionByModuleIdAndFunctionType(Long moduleId,
			String functionType) {
		if (null == moduleId || null == functionType) {
			throw new BusinessValidationException("moduleId或功能类型不能为空!");
		}
		return sysGisFunctionDao.getGisFunctionByModuleIdAndFunctionType(
				moduleId, functionType);
	}

	/**
	 * 根据对象添加功能
	 * 
	 * @param gisFunction
	 *            功能对象
	 * @param moduleId
	 *            父类id
	 * @param sonClassId
	 *            子类id
	 * @param functionType
	 *            功能类型
	 */
	public void addFunction(GisFunction gisFunction, Long moduleId,
			Long sonClassId) {
		if (null != gisFunction && null != gisFunction.getFunctionType()) {
			gisFunction.setFunctionType(gisFunction.getFunctionType());
			if (null != moduleId) {
				gisFunction.setModuleId(moduleId);
			}
			if (null != sonClassId) {
				gisFunction.setSonClassId(sonClassId);
			}
			addGisFunction(gisFunction);
		} else
			throw new BusinessValidationException("功能类型不能为空!");
	}

	/**
	 * 修改功能对象
	 * 
	 * @param gisFunction
	 *            功能对象
	 * @param moduleId
	 *            父类id
	 * @param sonClassId
	 *            子类id
	 * @param functionType
	 *            功能类型
	 */
	public void updateFunction(GisFunction gisFunction, Long moduleId,
			Long sonClassId) {
		if (null != gisFunction && null != gisFunction.getFunctionType()) {
			if (null != moduleId) {
				gisFunction.setModuleId(moduleId);
			}
			if (null != sonClassId) {
				gisFunction.setSonClassId(sonClassId);
			}
			updateGisFunction(gisFunction, gisFunction.getFunctionType());
		} else
			throw new BusinessValidationException("功能类型不能为空!");
	}

	/**
	 * 如果对象为空或者对象的id为空则进行新增操作， 否则进行修改操作
	 * 
	 * @param gisFunction
	 *            功能对象
	 * @param functionType
	 *            功能类型
	 * @return GisFunction
	 */
	private GisFunction updateGisFunction(GisFunction gisFunction,
			String functionType) {
		if (null == gisFunction || null == gisFunction.getId()
				|| null == gisFunction.getFunctionType()) {
			return addGisFunction(gisFunction);
		}
		return updateGisFunction(gisFunction);
	}

	@Override
	public GisFunction getGisFunctionBySonClassIdAndFunctionType(
			Long sonClassId, String functionType) {
		if (null == sonClassId || null == functionType) {
			throw new BusinessValidationException("sonClassId或功能类型不能为空!");
		}
		return sysGisFunctionDao.getGisFunctionBySonClassIdAndFunctionType(
				sonClassId, functionType);
	}

	/**
	 * 校验功能对象
	 * 
	 * @param gisFunction
	 */
	private void validateGisFunction(GisFunction gisFunction) {
		if (null == gisFunction) {
			throw new BusinessValidationException("对象不能为空");
		} else if (StringUtil
				.isStringAvaliable(gisFunction.getBoundEventName())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getBoundEventName())) {
			throw new BusinessValidationException("绑定按钮中文名只能输入2-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getUnBoundEventName())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getUnBoundEventName())) {
			throw new BusinessValidationException("解绑按钮中文名只能输入2-20个字符");
		} else if (null != gisFunction.getEvent()
				&& null != gisFunction.getEvent().getId()
				&& validateHelper.illegalNumberZZ(String.valueOf(gisFunction
						.getEvent().getId()))) {
			throw new BusinessValidationException("绑定事件非法,请联系管理员");
		} else if (null != gisFunction.getModuleId()
				&& validateHelper.illegalNumberZZ(String.valueOf(gisFunction
						.getModuleId()))) {
			throw new BusinessValidationException("主表id非法,请联系管理员");
		} else if (null != gisFunction.getSonClassId()
				&& validateHelper.illegalNumberZZ(String.valueOf(gisFunction
						.getSonClassId()))) {
			throw new BusinessValidationException("子表id非法,请联系管理员");
		} else if (StringUtil.isStringAvaliable(gisFunction.getIconUrl())
				&& validateHelper.illegalStringLength(2, 200,
						gisFunction.getIconUrl())) {
			throw new BusinessValidationException("图标路径只能输入2-200个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getTitleName())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getTitleName())) {
			throw new BusinessValidationException("标题名称只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getTitleValue())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getTitleValue())) {
			throw new BusinessValidationException("标题内容只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getDetailsUrl())
				&& validateHelper.illegalStringLength(2, 200,
						gisFunction.getDetailsUrl())) {
			throw new BusinessValidationException("详情查看url只能输入2-200个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldNameA())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getFieldNameA())) {
			throw new BusinessValidationException("内容A中文名只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldA())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getFieldA())) {
			throw new BusinessValidationException("字段A只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldNameB())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getFieldNameB())) {
			throw new BusinessValidationException("内容B中文名只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldB())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getFieldB())) {
			throw new BusinessValidationException("字段B只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldNameC())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getFieldNameC())) {
			throw new BusinessValidationException("内容C中文名只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldC())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getFieldC())) {
			throw new BusinessValidationException("字段C只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldNameD())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getFieldNameD())) {
			throw new BusinessValidationException("内容D中文名只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldD())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getFieldD())) {
			throw new BusinessValidationException("字段D只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldNameE())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getFieldNameE())) {
			throw new BusinessValidationException("内容E中文名只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getFieldE())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getFieldE())) {
			throw new BusinessValidationException("字段E只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getSearchFieldAName())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getSearchFieldAName())) {
			throw new BusinessValidationException("搜索A中文只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getSearchFieldA())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getSearchFieldA())) {
			throw new BusinessValidationException("搜索字段A只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getSearchFieldBName())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getSearchFieldBName())) {
			throw new BusinessValidationException("搜索B中文只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getSearchFieldB())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getSearchFieldB())) {
			throw new BusinessValidationException("搜索字段B只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getSearchFieldCName())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getSearchFieldCName())) {
			throw new BusinessValidationException("搜索C中文只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getSearchFieldC())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getSearchFieldC())) {
			throw new BusinessValidationException("搜索字段C只能输入1-60个字符");
		} else if (null == gisFunction.getFunctionType()
				|| validateHelper.illegalStringLength(1, 60,
						gisFunction.getFunctionType())) {
			throw new BusinessValidationException("功能类型只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getDetailFieldNameA())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getDetailFieldNameA())) {
			throw new BusinessValidationException("详情查看内容A只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getDetailFieldA())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getDetailFieldA())) {
			throw new BusinessValidationException("详情查看字段A只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getDetailFieldNameB())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getDetailFieldNameB())) {
			throw new BusinessValidationException("详情查看内容B只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getDetailFieldB())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getDetailFieldB())) {
			throw new BusinessValidationException("详情查看字段B只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getDetailFieldNameC())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getDetailFieldNameC())) {
			throw new BusinessValidationException("详情查看内容C只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getDetailFieldC())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getDetailFieldC())) {
			throw new BusinessValidationException("详情查看字段C只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getDetailFieldNameD())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getDetailFieldNameD())) {
			throw new BusinessValidationException("详情查看内容D只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getDetailFieldD())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getDetailFieldD())) {
			throw new BusinessValidationException("详情查看字段D只能输入1-60个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction
				.getDetailFieldNameE())
				&& validateHelper.illegalStringLength(1, 20,
						gisFunction.getDetailFieldNameE())) {
			throw new BusinessValidationException("详情查看内容E只能输入1-20个字符");
		} else if (StringUtil.isStringAvaliable(gisFunction.getDetailFieldE())
				&& validateHelper.illegalStringLength(1, 60,
						gisFunction.getDetailFieldE())) {
			throw new BusinessValidationException("详情查看字段E只能输入1-60个字符");
		}
	}

}
