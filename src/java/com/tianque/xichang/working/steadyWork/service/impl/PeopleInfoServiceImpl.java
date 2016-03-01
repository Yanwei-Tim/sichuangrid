package com.tianque.xichang.working.steadyWork.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.IdCardUtil;
import com.tianque.xichang.working.steadyWork.dao.PeopleInfoDao;
import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;
import com.tianque.xichang.working.steadyWork.domain.vo.PeopleInfoVo;
import com.tianque.xichang.working.steadyWork.service.PeopleInfoService;

@Service("peopleInfoService")
@Transactional
public class PeopleInfoServiceImpl implements PeopleInfoService {

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PeopleInfoDao peopleInfoDao;

	@Override
	public void addPeopleInfo(PeopleInfo peopleInfo) {
		peopleInfoDao.addPeopleInfo(peopleInfo);

	}

	@Override
	public List<PeopleInfo> findPeopleInfoBySteadyWorkId(Long steadyWorkId) {
		if (steadyWorkId == null) {
			throw new BusinessValidationException("稳定台账id不能为空");
		}

		return peopleInfoDao.findPeopleInfoBySteadyWorkId(steadyWorkId);
	}

	@Override
	public boolean deletePeopleInfoBySteadyWorkIds(List<Long> ids) {
		if (ids == null || ids.size() == 0) {
			throw new BusinessValidationException("请选中一条或者多条要删除的记录");
		}

		return peopleInfoDao.deletePeopleInfoBySteadyWorkIds(ids);
	}

	@Override
	public boolean deletePeopleInfoBySteadyWorkId(Long steadyWorkId) {
		if (steadyWorkId == null) {
			throw new BusinessValidationException("数据为空");
		}

		return peopleInfoDao.deletePeopleInfoBySteadyWorkId(steadyWorkId);
	}

	@Override
	public List<PeopleInfo> setPeopleInfoParam(PeopleInfoVo peopleInfoVo) {
		List<PeopleInfo> peopleInfos = new ArrayList<PeopleInfo>();
		if (peopleInfoVo != null) {
			for (int i = 0; i < peopleInfoVo.getNameTemp().length; i++) {
				if (peopleInfoVo.getNameTemp()[i] != "") {
					PeopleInfo peopleInfo = new PeopleInfo();
					peopleInfo.setName(peopleInfoVo.getNameTemp()[i]);
					peopleInfo.setIdCardNo(peopleInfoVo.getIdCardNoTemp()[i]);
					if (peopleInfoVo.getGenderTemp()[i] != null) {
						peopleInfo.setGender(propertyDictService
								.getPropertyDictById(Long.valueOf(peopleInfoVo
										.getGenderTemp()[i])));
					}
					if (peopleInfoVo.getPositionTemp()[i] != null) {
						peopleInfo.setPosition(propertyDictService
								.getPropertyDictById(Long.valueOf(peopleInfoVo
										.getPositionTemp()[i])));
					}

					peopleInfo.setMobileNumber(peopleInfoVo
							.getMobileNumberTemp()[i]);
					if (peopleInfoVo.getIdCardNoTemp()[i] != null) {
						peopleInfo
								.setBirthDay(IdCardUtil
										.parseBirthday(peopleInfoVo
												.getIdCardNoTemp()[i]));// 根据身份证获取出生日期
					}

					peopleInfo.setIsPartyMember(peopleInfoVo
							.getIsPartyMemberTemp()[i]);
					peopleInfo.setPermanentAddress(peopleInfoVo
							.getAddressTemp()[i]);

					peopleInfos.add(peopleInfo);
				}

			}

		}
		return peopleInfos;
	}

}
