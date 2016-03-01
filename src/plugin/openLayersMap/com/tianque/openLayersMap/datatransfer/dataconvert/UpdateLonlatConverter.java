package com.tianque.openLayersMap.datatransfer.dataconvert;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.HouseContext;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.openLayersMap.datatransfer.domain.UpdateLonlat;
import com.tianque.openLayersMap.positioningTrajectory.service.PositioningTrajectoryService;
import com.tianque.openLayersMap.service.SysTableOperateService;

@Component("updateLonlatConverter")
public class UpdateLonlatConverter extends AbstractDataConverter<UpdateLonlat> {

	@Autowired
	private SysTableOperateService tableOperateService;
	@Autowired
	private PositioningTrajectoryService positioningTrajectoryService;

	@Override
	public boolean isRepeatData(UpdateLonlat domain) {
		return false;
	}

	@Override
	public UpdateLonlat persistenceDomain(UpdateLonlat domain) {
		logger.error("重置坐标值：id=" + (domain != null ? domain.getId() : ""));
		if (domain == null
				|| !StringUtil.isStringAvaliable(domain.getTableName())
				|| domain.getId() == null) {
			throw new BusinessValidationException("重置坐标值时，参数错误！");
		}
		try {
			if ("positioningtrajectory".equals(domain.getTableName()
					.toLowerCase())) {
				executePositioningTrajectorySql(domain);
			} else if ("keyplaces".equals(domain.getTableName())) {
				executeTypeSql(domain);
			} else {
				tableOperateService.updateLonlatById(domain.getId(),
						domain.getTableName(), domain.getCenterLon(),
						domain.getCenterLat(), domain.getCenterLon2(),
						domain.getCenterLat2());
			}
			return domain;
		} catch (Exception e) {
			logger.error("重置坐标值出错：tableName=" + domain.getTableName() + "，id="
					+ domain.getId(), e);
			throw new ServiceValidationException("重置坐标值出错", e);
		}
	}

	@Override
	public UpdateLonlat updateDomain(UpdateLonlat domain) {
		return domain;
	}

	@Override
	public ValidateResult validate(UpdateLonlat actualhouse, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return null;
	}

	@Override
	public UpdateLonlat convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		UpdateLonlat updateLonlat = new UpdateLonlat();
		String[][] excelColumArray = HouseContext.getHouseInfoImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), updateLonlat, vr);
		updateLonlat.setCreateDate(new Date());
		return updateLonlat;
	}

	private void executePositioningTrajectorySql(UpdateLonlat domain) {
		positioningTrajectoryService.updateLonlatById(domain.getId(),
				domain.getCenterLon(), domain.getCenterLat(),
				domain.getCenterLon2(), domain.getCenterLat2());
	}

	private void executeTypeSql(UpdateLonlat domain) {
		tableOperateService.updateLonlatByIdAndType(domain.getId(),
				domain.getType(), domain.getTableName(), domain.getCenterLon(),
				domain.getCenterLat(), domain.getCenterLon2(),
				domain.getCenterLat2());
	}
}
