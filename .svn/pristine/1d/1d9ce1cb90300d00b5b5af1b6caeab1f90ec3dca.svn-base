package com.tianque.openLayersMap.positioningTrajectory.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.openLayersMap.positioningTrajectory.domian.PositioningTrajectory;
import com.tianque.sysadmin.service.PermissionService;

@Component("positioningTrajectoryValidator")
public class PositioningTrajectoryValidatorImpl implements
		DomainValidator<PositioningTrajectory> {

	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private PermissionService permissionService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(PositioningTrajectory domain) {
		ValidateResult validateResult = new ValidateResult();
		if (domain == null) {
			validateResult.addErrorMessage("参数对象不能为空");
		} else {
			if (validateHelper.nullObj(domain.getLocateDate())
					|| null == domain.getLocateDate()) {
				validateResult.addErrorMessage("定位时间不能为空");
			}
			if (validateHelper.emptyString(domain.getUserName())
					|| "".equals(domain.getUserName())) {
				validateResult.addErrorMessage("使用用户的用户名不能为空");
			}

			if (permissionService.getFullUserByUerName(domain.getUserName()) == null) {
				validateResult.addErrorMessage("用户不存在");
			}
			if (validateHelper.nullObj(domain.getLongitude())
					|| null == domain.getLongitude()) {
				validateResult.addErrorMessage("经度不能为空");
			}
			if (validateHelper.nullObj(domain.getLatitude())
					|| null == domain.getLatitude()) {
				validateResult.addErrorMessage("纬度不能为空");
			}
			if (!validateHelper.emptyString(domain.getDeviceNumber())
					&& validateHelper.illegalStringLength(0, 30,
							domain.getDeviceNumber())) {
				validateResult.addErrorMessage("设备编号不能大于30字符");
			}
			if (!validateHelper.emptyString(domain.getDirection())
					&& validateHelper.illegalStringLength(0, 10,
							domain.getDirection())) {
				validateResult.addErrorMessage("方向不能大于10字符");
			}
			if (!validateHelper.emptyString(domain.getSpeed())
					&& validateHelper.illegalStringLength(0, 10,
							domain.getSpeed())) {
				validateResult.addErrorMessage("速度不能大于10字符");
			}
			if (!validateHelper.emptyString(domain.getPositionDescription())
					&& validateHelper.illegalStringLength(0, 20,
							domain.getPositionDescription())) {
				validateResult.addErrorMessage("位置描述不能大于20字符");
			}
		}
		return validateResult;
	}

}
