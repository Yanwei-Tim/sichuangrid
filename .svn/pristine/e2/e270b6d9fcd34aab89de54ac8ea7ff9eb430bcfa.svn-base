package com.tianque.baseInfo.permanentAddress.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.permanentAddress.dao.PermanentAddressDao;
import com.tianque.baseInfo.permanentAddress.domain.JobStatusType;
import com.tianque.baseInfo.permanentAddress.domain.OperateType;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLevel;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;

@Transactional
@Service("permanentAddressService")
public class PermanentAddressServiceImpl implements PermanentAddressService,
		PermanentAddressLevel {

	@Autowired
	private PermanentAddressDao permanentAddressDao;
	@Autowired
	private PermanentAddressLogService permanentAddressLogService;
	public final static Logger logger = LoggerFactory
			.getLogger(PermanentAddressServiceImpl.class);
	@Autowired
	private SystemLogService systemLogService;

	@Override
	public PermanentAddress getPermanentAddressByNo(
			PermanentAddress PermanentAddress) {
		try {
			return permanentAddressDao
					.getPermanentAddressByNo(PermanentAddress);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getPermanentAddressByNo方法出现异常，原因：",
					"查询户籍地信息失败！", e);
		}
	}

	@Override
	public PermanentAddress getPermanentAddressByAddressNo(String addressNo) {
		try {
			return permanentAddressDao
					.getPermanentAddressByAddressNo(addressNo);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getPermanentAddressByAddressNo方法出现异常，原因：",
					"查询户籍地信息失败！", e);
		}
	}

	@Override
	public PermanentAddress addPermanentAddress(
			PermanentAddress permanentAddress) {
		try {
			String parentAddressNames = permanentAddress.getParentid();
			String parentAddressNo = getParentAddressNoByParentName(
					permanentAddress.getAddressLevel(),
					permanentAddress.getParentid());
			permanentAddress.setParentid(parentAddressNo);
			isHaveRepeatAddressNo(permanentAddress);
			isHaveRepeatAddressName(permanentAddress);
			check(permanentAddress);
			PermanentAddressLog palog = createAddPermanentAddressLog(
					parentAddressNames, permanentAddress, OperateType.ADD);
			permanentAddress = permanentAddressDao.addPermanentAddress(
					permanentAddress, palog);
			systemLogService.log(com.tianque.core.logger.Logger.INFO,
					ModelType.PERMANENTADDRESS, OperatorType.ADD,
					ThreadVariable.getSession().getUserName() + "新增区域名称为\""
							+ permanentAddress.getAddressName() + "\"的户籍地",
					null);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的addPermanentAddress方法出现异常，原因：",
					"添加户籍地信息失败！", e);
		}
		return permanentAddress;
	}

	@Override
	public PageInfo<PermanentAddress> getPermanentAddressByLevel(String level,
			Integer page, Integer rows, String sidx, String sord) {
		try {
			PageInfo<PermanentAddress> pageInfo = permanentAddressDao
					.getPermanentAddressByLevel(level, page, rows, sidx, sord);
			return setPage(pageInfo, page, rows);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getPermanentAddressByLevel方法出现异常，原因：",
					"查询户籍地信息失败！", e);
		}
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByParentid(String parentid) {
		try {
			return permanentAddressDao.getPermanentAddressByParentid(parentid);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getPermanentAddressByParentid方法出现异常，原因：",
					"查询户籍地信息失败！", e);
		}
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByParentName(
			String parentName) {
		try {
			return permanentAddressDao
					.getPermanentAddressByParentName(parentName);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getPermanentAddressByParentName方法出现异常，原因：",
					"查询户籍地信息失败！", e);
		}
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByParentNameAndPId(
			String parentName) {
		try {
			if ("".equals(parentName) || parentName.length() == 1) {
				return new ArrayList<PermanentAddress>();
			}
			String matchs[] = parentName.split("\\,");
			if (matchs.length <= 1) {
				return permanentAddressDao
						.getPermanentAddressByParentName(matchs[0]);
			}
			return permanentAddressDao.getPermanentAddressByParentNameAndPId(
					matchs[0], matchs[1]);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getPermanentAddressByParentNameAndPId方法出现异常，原因：",
					"查询户籍地信息失败！", e);
		}
	}

	@Override
	public PageInfo<PermanentAddress> findPermanentAddress(Integer page,
			Integer rows, String sidx, String sord) {
		try {
			PageInfo<PermanentAddress> pageInfo = permanentAddressDao
					.findPermanentAddress(page, rows, sidx, sord);
			return setPage(pageInfo, page, rows);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的findPermanentAddress方法出现异常，原因：",
					"查询户籍地信息失败！", e);
		}
	}

	private PageInfo<PermanentAddress> setPage(
			PageInfo<PermanentAddress> pageInfo, Integer page, Integer rows) {
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public boolean deletePermanentAddress(String addressNo) {
		if (isHasJurisdiction(addressNo)) {
			throw new BusinessValidationException("存在下级户籍地信息！");
		}
		try {
			PermanentAddressLog palog = createDeletePermanentAddressLog(
					addressNo, OperateType.DELETE);
			palog.setJobState(JobStatusType.DELETE);
			PermanentAddress permanentAddress = getPermanentAddressByAddressNo(addressNo);
			boolean flag = permanentAddressDao.deletePermanentAddress(
					addressNo, palog);
			systemLogService.log(com.tianque.core.logger.Logger.INFO,
					ModelType.PERMANENTADDRESS, OperatorType.DELETE,
					ThreadVariable.getSession().getUserName() + "删除区域名称为\""
							+ permanentAddress.getAddressName() + "\"的户籍地",
					null);
			return flag;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的deletePermanentAddress方法出现异常，原因：",
					"删除户籍地失败！", e);
		}
	}

	private boolean isHasJurisdiction(String parentid) {
		return getPermanentAddressByParentid(parentid).size() > 0;
	}

	@Override
	public List<PermanentAddress> getPermanentAddressByLevel(String level) {
		if (level == null) {
			throw new BusinessValidationException("查询出错，请与管理员联系！");
		}
		try {
			return permanentAddressDao.getPermanentAddressByLevel(level);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getPermanentAddressByLevel方法出现异常，原因：",
					"查询户籍地信息失败！", e);
		}

	}

	@Override
	public PermanentAddress updatePermanentAddress(
			PermanentAddress permanentAddress) {
		try {
			isHaveRepeatAddressName(permanentAddress);
			PermanentAddressLog palog = createUpdatePermanentAddressLog(
					permanentAddress, OperateType.UPDATE);
			PermanentAddress oldPermanentAddress = getPermanentAddressByAddressNo(permanentAddress
					.getAddressNo());
			permanentAddress = permanentAddressDao.updatePermanentAddress(
					permanentAddress, palog);
			systemLogService.log(com.tianque.core.logger.Logger.INFO,
					ModelType.PERMANENTADDRESS, OperatorType.UPDATE,
					ThreadVariable.getSession().getUserName() + "修改户籍地:"
							+ oldPermanentAddress.getAddressName() + "更改为"
							+ permanentAddress.getAddressName(), null);

			return permanentAddress;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的updatePermanentAddress方法出现异常，原因：",
					"户籍地修改失败！", e);
		}
	}

	// 校验permanentAddress
	private void check(PermanentAddress permanentAddress) {
		if (permanentAddress != null) {
			if (permanentAddress.getAddressName().length() < 1) {
				throw new BusinessValidationException("地方名称不能为空");
			}
			if (permanentAddress.getAddressName().length() > 20) {
				throw new BusinessValidationException("地方名称必须小于20位");
			}
			if (permanentAddress.getAddressNo().length() != 6) {
				throw new BusinessValidationException("地区编号长度必须为6");
			}
			if (checkNumeric(permanentAddress.getAddressNo()) != true) {
				throw new BusinessValidationException("地区编号必须为数字！");
			}

		} else if (permanentAddress == null) {
			throw new BusinessValidationException("添加出错！");
		}

	}

	// 该地区编号是否存在
	private void isHaveRepeatAddressNo(PermanentAddress permanentAddress) {
		if (permanentAddressDao.getPermanentAddressByAddressNo(permanentAddress
				.getAddressNo()) != null) {
			throw new BusinessValidationException("该地区编号已存在");
		}
	}

	// 验证地区编号是否为数字
	private boolean checkNumeric(String idcard) {
		char a1 = idcard.charAt(idcard.length() - 1);
		if (idcard.length() == 18 && a1 == 'X') {
			return !illegalNum(idcard.substring(0, 17));
		} else {
			return !illegalNum(idcard);
		}
	}

	private boolean illegalNum(String text) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();
	}

	// 验证同父级下是否有同名的户籍地;
	private void isHaveRepeatAddressName(PermanentAddress permanentAddress) {
		List<PermanentAddress> result = permanentAddressDao
				.getPermanentAddressByAddressNameAndPIdAndAddressNo(permanentAddress);
		if (result.size() > 0) {
			throw new BusinessValidationException("该层级下已存在相同名称的户籍地!");
		}
	}

	@Override
	public PageInfo<PermanentAddress> searchPermanentAddress(String str,
			Integer page, Integer rows, String sidx, String sord) {
		try {
			PageInfo<PermanentAddress> pageInfo = permanentAddressDao
					.searchPermanentAddress(str, page, rows, sidx, sord);
			return setPage(pageInfo, page, rows);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的searchPermanentAddress方法出现异常，原因：",
					"查询户籍地信息出错！", e);
		}
	}

	@Override
	public String getParentAddressNoByParentName(String addressLevel,
			String selectStr) {
		String[] strs = selectStr.split(",");
		Map searchMap = new HashMap<String, String>();
		if (PROVINCE_LEVEL.equals(addressLevel)) {
			return DEFAULT_PADDRESSNO;
		} else if (CITY_LEVEL.equals(addressLevel) && strs.length >= 1) {
			searchMap.put("province", strs[0]);
		} else if (DISTRICT_LEVEL.equals(addressLevel) && strs.length >= 2) {
			searchMap.put("province", strs[0]);
			searchMap.put("city", strs[1]);
		}
		try {
			return permanentAddressDao
					.getPermanentAddressByParentNames(searchMap);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getParentAddressNoByParentName方法出现异常，原因：",
					"无法定位上级辖区编码！", e);
		}
	}

	private PermanentAddressLog createAddPermanentAddressLog(
			String parentAddressNames, PermanentAddress permanentAddress,
			int operateType) {
		if (parentAddressNames == null || parentAddressNames.length() == 0
				|| permanentAddress == null) {
			throw new BusinessValidationException("创建日志失败，参数错误");
		}
		PermanentAddressLog palog = new PermanentAddressLog();
		palog.setAddressNo(permanentAddress.getAddressNo());
		palog.setAddslevel(Integer.valueOf(permanentAddress.getAddressLevel()));
		palog.setChangedName(permanentAddress.getAddressName());
		palog.setOptionType(operateType);
		String[] parentAddressName = parentAddressNames.split(",");
		if (parentAddressName.length == 1
				&& !DEFAULT_PADDRESSNO.equals(parentAddressName[0])) {
			palog.setProvince(parentAddressName[0]);
		} else if (parentAddressName.length == 2) {
			palog.setProvince(parentAddressName[0]);
			palog.setCity(parentAddressName[1]);
		}
		palog.setJobState(JobStatusType.ADD);
		return permanentAddressLogService.addPermanentAddressLog(palog);
	}

	private PermanentAddressLog createDeletePermanentAddressLog(
			String addressNo, int operateType) throws Exception {
		PermanentAddress pa = new PermanentAddress();
		pa.setAddressNo(addressNo);
		return createUpdatePermanentAddressLog(pa, operateType);
	}

	private PermanentAddressLog createUpdatePermanentAddressLog(
			PermanentAddress permanentAddress, int operateType)
			throws Exception {
		PermanentAddress old = permanentAddressDao
				.getPermanentAddressByAddressNo(permanentAddress.getAddressNo());
		if (null != old
				&& null != permanentAddress
				&& null != permanentAddress.getAddressName()
				&& permanentAddress.getAddressName().equals(
						old.getAddressName())) {
			return null;
		}
		PermanentAddressLog palog = new PermanentAddressLog();
		String addsLevel = permanentAddress.getAddressLevel() == null ? old
				.getAddressLevel() : permanentAddress.getAddressLevel();
		palog.setAddslevel(addsLevel == null ? null : Integer
				.valueOf(addsLevel));
		palog.setAddressNo(permanentAddress.getAddressNo());
		palog.setChangedName(permanentAddress.getAddressName());
		palog.setOptionType(operateType);
		List<String> addrNames = new ArrayList<String>();
		while (null != old) {
			addrNames.add(old.getAddressName());
			if (!DEFAULT_PADDRESSNO.equals(old.getParentid())) {
				old = permanentAddressDao.getPermanentAddressByAddressNo(old
						.getParentid());
			} else {
				break;
			}
		}
		Collections.reverse(addrNames);
		switch (addrNames.size()) {// case穿透有用，请勿加break;语句
		case 3:
			palog.setDistrict(addrNames.get(2));
		case 2:
			palog.setCity(addrNames.get(1));
		case 1:
			palog.setProvince(addrNames.get(0));
			break;
		default:
			throw new BusinessValidationException("未知的户籍地名称");
		}
		palog.setJobState(JobStatusType.UPDATE);
		return permanentAddressLogService.addPermanentAddressLog(palog);
	}

	@Override
	public List<String> getPermanentAddressByParentNameAndAddressLevel(
			String parentName, String addressLevel) {
		if (null == parentName || null == addressLevel) {
			return null;
		}
		try {
			return permanentAddressDao
					.getPermanentAddressByParentNameAndAddressLevel(parentName,
							addressLevel);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PermanentAddressServiceImpl的getPermanentAddressByParentNameAndAddressLevel方法出现异常，原因：",
					"查询户籍地信息出错！", e);
		}

	}

	@Override
	public List<PermanentAddress> getPermanentAddressByAddressNameAndAddressLevel(
			String addressName, String addressLevel) {
		try {
			return permanentAddressDao
					.getPermanentAddressByAddressNameAndAddressLevel(
							addressName, addressLevel);
		} catch (Exception e) {
			throw new ServiceValidationException("户籍地查询出错，请重试", e);
		}
	}

	@Override
	public List<String> getPermanentAddressByParentNameAndAddressNameAndAddressLevel(
			String addressName, String addressLevel, String parentName) {
		try {
			return permanentAddressDao
					.getPermanentAddressByParentNameAndAddressNameAndAddressLevel(
							addressName, addressLevel, parentName);
		} catch (Exception e) {
			throw new ServiceValidationException("户籍地查询出错，请重试", e);
		}
	}

	@Override
	public PermanentAddressLog addPermanentAddressClean(
			PermanentAddress permanentAddress) {
		PermanentAddressLog log = new PermanentAddressLog();
		Integer addressLevel = null;
		if (permanentAddress.getAddressLevel() != null
				&& permanentAddress.getAddressLevel().trim().length() != 0) {
			addressLevel = Integer.parseInt(permanentAddress.getAddressLevel());
		}
		if (addressLevel == null) {
			throw new BusinessValidationException("清洗日志添加失败");
		}
		log.setProvince(permanentAddress.getProviceName());
		log.setCity(permanentAddress.getCityName());
		log.setDistrict(permanentAddress.getDistrict());
		log.setChangedName(permanentAddress.getNewAddressName());
		log.setOptionType(OperateType.UPDATE);
		log.setAddslevel(addressLevel);
		log.setAddressNo(permanentAddress.getAddressNo());
		log.setJobState(JobStatusType.UPDATE);
		return permanentAddressLogService.addPermanentAddressLog(log);
	}
}
