package com.tianque.openLayersMap.positioningTrajectory.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;
import com.tianque.domain.WorkCalendar;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.openLayersMap.positioningTrajectory.dao.PositioningTrajectoryDao;
import com.tianque.openLayersMap.positioningTrajectory.domian.PositioningTrajectory;
import com.tianque.openLayersMap.positioningTrajectory.domian.vo.SearchPositioningTrajectoryVo;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.util.GisProperties;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Service("positioningTrajectoryService")
public class PositioningTrajectoryServiceImpl implements
		PositioningTrajectoryService {
	@Autowired
	@Qualifier("positioningTrajectoryValidator")
	private DomainValidator<PositioningTrajectory> validator;
	@Autowired
	private PositioningTrajectoryDao positioningTrajectoryDao;
	// @Autowired
	// private DeviceInformationService deviceInformationService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private WorkCalendarService workCalendarService;
	@Autowired
	private Gis2DLayerService gis2dLayerService;

	@Override
	public PositioningTrajectory addPositioningTrajectory(
			PositioningTrajectory domain) {
		validator(domain);
		if (!validatorLocateTime(domain)) {
			return domain;
		}
		domain.setCreateDate(new Date());
		try {
			String[] lonlats = GisTransformatUtil.transformatPoint(
					domain.getLongitude() + "", domain.getLatitude() + "",
					domain.getGisType(), gis2dLayerService);
			domain.setLongitude(Double.valueOf(lonlats[0]));
			domain.setLatitude(Double.valueOf(lonlats[1]));
			domain.setLongitude2(lonlats[2]);
			domain.setLatitude2(lonlats[3]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (GisProperties.UPGRADE) {
			return domain;
		}
		return positioningTrajectoryDao.addPositioningTrajectory(domain);

	}

	private void validator(PositioningTrajectory positioningTrajectory) {
		ValidateResult validateResult = validator
				.validate(positioningTrajectory);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
		// Assert.isFalse(validateResult.hasError(), new
		// BusinessValidationException(validateResult.getErrorMessages()));
	}

	private boolean validatorLocateTime(
			PositioningTrajectory positioningTrajectory) {
		Date locateDate = positioningTrajectory.getLocateDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
		String dateStr = dateFormat.format(locateDate);
		try {
			boolean isWorkDay = workCalendarService.isWorkDayByDate(dateStr);
			if (isWorkDay) {
				dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
				WorkCalendar workCalendar = workCalendarService
						.getWorkCalendarByDateString(dateStr);
				if (dateFormat.parse(
						dateStr + " " + workCalendar.getStartWorkTimeAM())
						.before(locateDate)
						&& dateFormat
								.parse(dateStr + " "
										+ workCalendar.getEndWorkTimeAM())
								.after(locateDate)) {
					return true;
				}
				if (dateFormat.parse(
						dateStr + " " + workCalendar.getStartWorkTimePM())
						.before(locateDate)
						&& dateFormat
								.parse(dateStr + " "
										+ workCalendar.getEndWorkTimePM())
								.after(locateDate)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePositioningTrajectoryByDeviceNumber(String deviceNumber) {
		if (deviceNumber == null) {
			throw new BusinessValidationException("删除定位轨迹信息设备编号为空!");
		}
		// Assert.notNull(deviceNumber, new
		// BusinessValidationException("删除定位轨迹信息设备编号为空!"));
		try {
			positioningTrajectoryDao
					.deletePositioningTrajectoryByDeviceNumber(deviceNumber);
		} catch (Exception e) {
			throw new ServiceValidationException("删除定位轨迹信息数据时出现错误!", e);
		}
		return true;
	}

	@Override
	public PositioningTrajectory getPositioningTrajectoryByDeviceNumber(Long id) {
		// Assert.notNull(id, new BusinessValidationException("定位轨迹信息id为空!"));
		if (id == null) {
			throw new BusinessValidationException("定位轨迹信息id为空!");
		}
		PositioningTrajectory positioningTrajectory = null;

		try {
			positioningTrajectory = positioningTrajectoryDao
					.getPositioningTrajectoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException("查看定位轨迹信息数据出现错误!", e);
		}
		return positioningTrajectory;
	}

	@Override
	public PageInfo<PositioningTrajectory> findPositioningTrajectoryByDeviceNumberAndLocateDate(
			SearchPositioningTrajectoryVo searchPositioningTrajectoryVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		try {
			return positioningTrajectoryDao
					.findPositioningTrajectoryByDeviceNumberAndLocateDate(
							searchPositioningTrajectoryVo, pageNum, pageSize,
							sortField, order);
		} catch (Exception e) {
			throw new ServiceValidationException("查询设备信息数据列表时出现错误!", e);
		}
	}

	@Override
	public PositioningTrajectory getPositioningTrajectoryById(Long id) {
		try {
			PositioningTrajectory positioningTrajectory = positioningTrajectoryDao
					.getPositioningTrajectoryById(id);
			if (positioningTrajectory != null) {
				// DeviceInformation deviceInformation = null;
				// if (positioningTrajectory.getDeviceNumber() != null) {
				// deviceInformation = deviceInformationService
				// .getDeviceInformationByDeviceNumber(positioningTrajectory
				// .getDeviceNumber());
				// positioningTrajectory
				// .setDeviceInformation(deviceInformation);
				// }
				if (positioningTrajectory.getUserName() != null) {
					User user = permissionService
							.findUserByUserName(positioningTrajectory
									.getUserName());
					if (user != null) {
						user.setOrganization(organizationDubboService
								.getSimpleOrgById(user.getOrganization()
										.getId()));
						positioningTrajectory.setUser(user);
					}
				}
			}
			return positioningTrajectory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException(
					"getPositioningTrajectoryById出错：", e);
		}
	}

	@Override
	public void updateLonlatById(Long id, String centerLon, String centerLat,
			String centerLon2, String centerLat2) {
		if (id == null) {
			throw new BusinessValidationException("请选择一条记录再进行修");
		}
		positioningTrajectoryDao.updateLonlatById(id, centerLon, centerLat,
				centerLon2, centerLat2);
	}
}
