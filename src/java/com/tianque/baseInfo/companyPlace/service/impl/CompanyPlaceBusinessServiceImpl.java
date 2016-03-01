package com.tianque.baseInfo.companyPlace.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.companyPlace.constant.IsKeyType;
import com.tianque.baseInfo.companyPlace.dao.CompanyPlaceBusinessDAO;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceAnnex;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBusiness;
import com.tianque.baseInfo.companyPlace.domain.ContaminationInfo;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceBusinessVO;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceAnnexService;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBusinessService;
import com.tianque.baseInfo.companyPlace.service.ContaminationInfoService;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.KeyPlace;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.service.KeyPlaceService;
import com.tianque.sysAttachment.domain.SysAttachment;
import com.tianque.sysAttachment.service.SysAttachmentService;

@Service("companyPlaceBusinessService")
@Transactional
public class CompanyPlaceBusinessServiceImpl extends AbstractBaseService
		implements CompanyPlaceBusinessService {
	@Autowired
	private CompanyPlaceBusinessDAO companyPlaceBusinessDao;
	@Autowired
	private CompanyPlaceAnnexService companyPlaceAnnexService;
	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseSevice;
	@Autowired
	private KeyPlaceService keyPlaceService;
	@Autowired
	private SysAttachmentService sysAttachmentService;
	@Autowired
	private ContaminationInfoService contaminationInfoService;

	@Qualifier("CompanyPlaceBusinessValidatorImpl")
	@Autowired
	private DomainValidator<CompanyPlaceBusinessVO> domainValidator;

	@Override
	public void addCompanyPlaceBusiness(CompanyPlaceBusinessVO vo,
			Long companyPlaceId, String modulKey) {
		try {
			companyPlaceBusinessValidator(vo);
			if (vo.getProKeyCompanyPlaceBusiness() != null
					&& vo.getProKeyCompanyPlaceBusiness().getIsKeyType() != null) {

				add(vo, vo.getProKeyCompanyPlaceBusiness(), modulKey,
						companyPlaceId, vo.getProKeyAttachFiles(),
						ModulTypes.SAFETYPRODUCTIONKEY_KEY);
			}
			if (vo.getFireSafetyKeyCompanyPlaceBusiness() != null
					&& vo.getFireSafetyKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				add(vo, vo.getFireSafetyKeyCompanyPlaceBusiness(), modulKey,
						companyPlaceId, vo.getFireSafetyKeyAttachFiles(),
						ModulTypes.FIRESAFETYKEY_KEY);
			}
			if (vo.getSecurityKeyCompanyPlaceBusiness() != null
					&& vo.getSecurityKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				add(vo, vo.getSecurityKeyCompanyPlaceBusiness(), modulKey,
						companyPlaceId, vo.getSecurityKeyAttachFiles(),
						ModulTypes.SECURITYKEY_KEY);
			}
			if (vo.getPollSourceCompanyPlaceBusiness() != null
					&& vo.getPollSourceCompanyPlaceBusiness().getIsKeyType() != null) {
				if (vo.getContaminationInfo() != null
						&& vo.getContaminationInfo().getOpenLayersMapInfo() != null) {
					GisTransformatUtil.autoFillOpenLayersMapInfo(vo
							.getContaminationInfo().getOpenLayersMapInfo());
				}
				add(vo, vo.getPollSourceCompanyPlaceBusiness(), modulKey,
						companyPlaceId, vo.getPollSourceAttachFiles(),
						ModulTypes.POLLUTIONSOURCE_KEY);
				// 污染源业务信息
				if (vo.getContaminationInfo() != null) {
					addContaminationInfo(vo.getContaminationInfo(), vo
							.getPollSourceCompanyPlaceBusiness().getId());
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBusinessServiceImpl的addCompanyPlaceBusiness方法出现异常，原因：",
					"新增单位场所业务信息出现错误", e);
		}
	}

	// 提取新增方法
	private void add(CompanyPlaceBusinessVO vo, CompanyPlaceBusiness bean,
			String modulKey, Long companyPlaceId, String[] files, String type) {
		try {
			ContaminationInfo contaminationInfo = null;
			if (ModulTypes.POLLUTIONSOURCE_KEY.equals(type)
					&& vo.getContaminationInfo() != null) {
				contaminationInfo = vo.getContaminationInfo();
			}
			if (bean.getId() != null) {
				updateCompanyPlaceBusiness(vo, companyPlaceId, modulKey);
			} else {
				if (modulKey != null) {
					bean.setCompanyPlaceSource(modulKey);
					bean.setCompanyPlaceId(companyPlaceId);
				}
				companyPlaceBusinessDao.addCompanyPlaceBusiness(bean);
				iterableAttachFiles(companyPlaceId, type, files, bean);
				manageKeyPlace(null, companyPlaceId, type, contaminationInfo);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceBusinessServiceImpl的add方法出现异常，原因：",
					"新增单位场所业务信息出现错误", e);
		}
	}

	private void addContaminationInfo(ContaminationInfo contaminationInfo,
			Long businessId) {
		try {
			contaminationInfo.setBusinessId(businessId);
			if (contaminationInfo.getId() != null) {
				contaminationInfoService
						.updateContaminationInfo(contaminationInfo);
			} else {
				contaminationInfoService
						.addContaminationInfo(contaminationInfo);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceBusinessServiceImpl的addContaminationInfo方法出现异常，原因：",
					"新增单位场所-污染源业务信息出现错误", e);
		}
	}

	@Override
	public void addCompanyPlaceBusinessForMobile(CompanyPlaceBusinessVO vo,
			Long companyPlaceId, String modulKey) {
		try {
			companyPlaceBusinessValidator(vo);
			if (vo.getProKeyCompanyPlaceBusiness() != null
					&& vo.getProKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				if (vo.getProKeyCompanyPlaceBusiness().getId() != null) {
					updateCompanyPlaceBusiness(vo, companyPlaceId, modulKey);
				} else {
					if (modulKey != null) {
						vo.getProKeyCompanyPlaceBusiness()
								.setCompanyPlaceSource(modulKey);
						vo.getProKeyCompanyPlaceBusiness().setCompanyPlaceId(
								companyPlaceId);
					}
					companyPlaceBusinessDao.addCompanyPlaceBusiness(vo
							.getProKeyCompanyPlaceBusiness());
					iterableAttachFilesForMobile(vo.getProKeyAttachFiles(),
							vo.getProKeyCompanyPlaceBusiness());
					manageKeyPlace(null, companyPlaceId,
							ModulTypes.SAFETYPRODUCTIONKEY_KEY, null);
				}
			}
			if (vo.getFireSafetyKeyCompanyPlaceBusiness() != null
					&& vo.getFireSafetyKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				if (vo.getFireSafetyKeyCompanyPlaceBusiness().getId() != null) {
					updateCompanyPlaceBusiness(vo, companyPlaceId, modulKey);
				} else {
					if (modulKey != null) {
						vo.getFireSafetyKeyCompanyPlaceBusiness()
								.setCompanyPlaceSource(modulKey);
						vo.getFireSafetyKeyCompanyPlaceBusiness()
								.setCompanyPlaceId(companyPlaceId);
					}
					companyPlaceBusinessDao.addCompanyPlaceBusiness(vo
							.getFireSafetyKeyCompanyPlaceBusiness());
					iterableAttachFilesForMobile(
							vo.getFireSafetyKeyAttachFiles(),
							vo.getFireSafetyKeyCompanyPlaceBusiness());
					manageKeyPlace(null, companyPlaceId,
							ModulTypes.FIRESAFETYKEY_KEY, null);
				}
			}
			if (vo.getSecurityKeyCompanyPlaceBusiness() != null
					&& vo.getSecurityKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				if (vo.getSecurityKeyCompanyPlaceBusiness().getId() != null) {
					updateCompanyPlaceBusiness(vo, companyPlaceId, modulKey);
				} else {
					if (modulKey != null) {
						vo.getSecurityKeyCompanyPlaceBusiness()
								.setCompanyPlaceSource(modulKey);
						vo.getSecurityKeyCompanyPlaceBusiness()
								.setCompanyPlaceId(companyPlaceId);
					}
					companyPlaceBusinessDao.addCompanyPlaceBusiness(vo
							.getSecurityKeyCompanyPlaceBusiness());
					iterableAttachFilesForMobile(
							vo.getSecurityKeyAttachFiles(),
							vo.getSecurityKeyCompanyPlaceBusiness());
					manageKeyPlace(null, companyPlaceId,
							ModulTypes.SECURITYKEY_KEY, null);
				}
			}
			if (vo.getPollSourceCompanyPlaceBusiness() != null
					&& vo.getPollSourceCompanyPlaceBusiness().getIsKeyType() != null) {
				if (vo.getPollSourceCompanyPlaceBusiness().getId() != null) {
					updateCompanyPlaceBusiness(vo, companyPlaceId, modulKey);
				} else {
					if (modulKey != null) {
						vo.getPollSourceCompanyPlaceBusiness()
								.setCompanyPlaceSource(modulKey);
						vo.getPollSourceCompanyPlaceBusiness()
								.setCompanyPlaceId(companyPlaceId);
					}
					// 污染源地图
					if (vo.getContaminationInfo() != null
							&& vo.getContaminationInfo().getOpenLayersMapInfo() != null) {
						GisTransformatUtil.autoFillOpenLayersMapInfo(vo
								.getContaminationInfo().getOpenLayersMapInfo());
					}

					companyPlaceBusinessDao.addCompanyPlaceBusiness(vo
							.getPollSourceCompanyPlaceBusiness());
					iterableAttachFilesForMobile(vo.getPollSourceAttachFiles(),
							vo.getPollSourceCompanyPlaceBusiness());

					// 污染源业务信息
					if (vo.getContaminationInfo() != null) {
						manageKeyPlace(null, companyPlaceId,
								ModulTypes.POLLUTIONSOURCE_KEY,
								vo.getContaminationInfo());
						addContaminationInfo(vo.getContaminationInfo(), vo
								.getPollSourceCompanyPlaceBusiness().getId());
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBusinessServiceImpl的addCompanyPlaceBusiness方法出现异常，原因：",
					"新增单位场所业务信息出现错误", e);
		}
	}

	private void manageKeyPlace(KeyPlace keyPlace, Long companyPlaceId,
			String type, ContaminationInfo contaminationInfo) {
		CompanyPlace companyPlace = companyPlaceBaseSevice
				.getCompanyPlaceInfoByCid(companyPlaceId);
		if (keyPlace != null) {
			keyPlace.setName(companyPlace.getName());
			keyPlace.setType(type);
			keyPlace.setAddress(companyPlace.getAddress());
			keyPlace.setChargePerson(companyPlace.getUserName());
			Map<String, String> pinyin = Chinese2pinyin
					.changeChinese2Pinyin(companyPlace.getName());
			keyPlace.setFullPinyin((String) pinyin.get("fullPinyin"));
			keyPlace.setSimplePinyin((String) pinyin.get("simplePinyin"));
			keyPlace = keyPlaceService.updateKeyPlace(keyPlace);
			updateTwoDimensionMap(keyPlace, contaminationInfo);
		} else {
			keyPlace = new KeyPlace();
			keyPlace.setId(companyPlace.getCid());
			keyPlace.setOrgId(companyPlace.getOrg().getId());
			keyPlace.setName(companyPlace.getName());
			keyPlace.setOrgInternalCode(companyPlace.getOrginternalcode());
			keyPlace.setType(type);
			keyPlace.setAddress(companyPlace.getAddress());
			keyPlace.setChargePerson(companyPlace.getUserName());
			Map<String, String> pinyin = Chinese2pinyin
					.changeChinese2Pinyin(companyPlace.getName());
			keyPlace.setFullPinyin((String) pinyin.get("fullPinyin"));
			keyPlace.setSimplePinyin((String) pinyin.get("simplePinyin"));
			keyPlace = keyPlaceService.addKeyPlace(keyPlace);
			updateTwoDimensionMap(keyPlace, contaminationInfo);
		}
	}

	// 污染源地图数据更改
	private void updateTwoDimensionMap(KeyPlace keyPlace,
			ContaminationInfo contaminationInfo) {
		KeyPlaceInfoVo keyPlaceInfoVo = new KeyPlaceInfoVo();
		if (contaminationInfo != null
				&& contaminationInfo.getOpenLayersMapInfo() != null) {
			keyPlaceInfoVo.setLat(contaminationInfo.getOpenLayersMapInfo()
					.getCenterLat());
			keyPlaceInfoVo.setLon(contaminationInfo.getOpenLayersMapInfo()
					.getCenterLon());
			keyPlaceInfoVo.setLat2(contaminationInfo.getOpenLayersMapInfo()
					.getCenterLat2().equals("0.0") ? "" : contaminationInfo
					.getOpenLayersMapInfo().getCenterLat2());
			keyPlaceInfoVo.setLon2(contaminationInfo.getOpenLayersMapInfo()
					.getCenterLon2().equals("0.0") ? "" : contaminationInfo
					.getOpenLayersMapInfo().getCenterLon2());
			keyPlaceInfoVo.setBuildDataId(contaminationInfo.getBuildDataId());
			keyPlaceInfoVo.setId(keyPlace.getId());
			keyPlaceInfoVo.setType(keyPlace.getType());
			keyPlaceService.updateDomainByKeyplaces(keyPlaceInfoVo);
		}
	}

	@Override
	public void updateCompanyPlaceBusiness(CompanyPlaceBusinessVO vo,
			Long companyPlaceId, String modulKey) {
		try {
			companyPlaceBusinessValidator(vo);
			// 删除keyplaces中单位场所 业务的信息
			deleteKeyPlaceByIds(companyPlaceId, ModulTypes.businessTypes);

			if (vo.getProKeyCompanyPlaceBusiness() != null
					&& vo.getProKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				update(vo, vo.getProKeyCompanyPlaceBusiness(), modulKey,
						companyPlaceId, vo.getProKeyAttachFiles(),
						ModulTypes.SAFETYPRODUCTIONKEY_KEY);
			}
			if (vo.getFireSafetyKeyCompanyPlaceBusiness() != null
					&& vo.getFireSafetyKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				update(vo, vo.getFireSafetyKeyCompanyPlaceBusiness(), modulKey,
						companyPlaceId, vo.getFireSafetyKeyAttachFiles(),
						ModulTypes.FIRESAFETYKEY_KEY);
			}
			if (vo.getSecurityKeyCompanyPlaceBusiness() != null
					&& vo.getSecurityKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				update(vo, vo.getSecurityKeyCompanyPlaceBusiness(), modulKey,
						companyPlaceId, vo.getSecurityKeyAttachFiles(),
						ModulTypes.SECURITYKEY_KEY);
			}
			if (vo.getPollSourceCompanyPlaceBusiness() != null
					&& vo.getPollSourceCompanyPlaceBusiness().getIsKeyType() != null) {
				if (vo.getContaminationInfo() != null
						&& vo.getContaminationInfo().getOpenLayersMapInfo() != null) {
					GisTransformatUtil.autoFillOpenLayersMapInfo(vo
							.getContaminationInfo().getOpenLayersMapInfo());
				}
				update(vo, vo.getPollSourceCompanyPlaceBusiness(), modulKey,
						companyPlaceId, vo.getPollSourceAttachFiles(),
						ModulTypes.POLLUTIONSOURCE_KEY);
				if (vo.getContaminationInfo() != null) {
					updateContaminationInfo(vo.getContaminationInfo(), vo
							.getPollSourceCompanyPlaceBusiness().getId());
				}
			}
			deleteCompanyPlaceBusiness(vo);// 清除掉页面上去掉勾选的数据
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBusinessServiceImpl的updateCompanyPlaceBusiness方法出现异常，原因：",
					"修改单位场所业务信息出现错误", e);
		}
	}

	// 提取的修改方法
	private void update(CompanyPlaceBusinessVO vo, CompanyPlaceBusiness bean,
			String modulKey, Long companyPlaceId, String[] files, String type) {
		try {
			if (modulKey != null) {
				bean.setCompanyPlaceSource(modulKey);
				bean.setCompanyPlaceId(companyPlaceId);
			}
			updateFileForDelete(files, bean);
			CompanyPlaceBusiness business = updateCompanyPlaceBusiness(bean,
					companyPlaceId);
			if (business != null) {
				if (ModulTypes.SAFETYPRODUCTIONKEY_KEY.equals(type)) {
					vo.setProKeyCompanyPlaceBusiness(business);
				} else if (ModulTypes.FIRESAFETYKEY_KEY.equals(type)) {
					vo.setFireSafetyKeyCompanyPlaceBusiness(business);
				} else if (ModulTypes.SECURITYKEY_KEY.equals(type)) {
					vo.setSecurityKeyCompanyPlaceBusiness(business);
				} else if (ModulTypes.POLLUTIONSOURCE_KEY.equals(type)) {
					vo.setPollSourceCompanyPlaceBusiness(business);
				}
			} else {
				throw new BusinessValidationException("处理业务信息时出错");
			}
			updateFileForAdd(companyPlaceId, type, files, bean);
			KeyPlace keyPlace = keyPlaceService.getKeyPlaceByIdAndType(
					companyPlaceId, type);
			ContaminationInfo contaminationInfo = null;
			if (ModulTypes.POLLUTIONSOURCE_KEY.equals(type)
					&& vo.getContaminationInfo() != null) {
				contaminationInfo = vo.getContaminationInfo();
			}
			manageKeyPlace(keyPlace, companyPlaceId, type, contaminationInfo);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBusinessServiceImpl的update方法出现异常，原因：",
					"修改单位场所业务信息出现错误", e);
		}
	}

	private void updateContaminationInfo(ContaminationInfo contaminationInfo,
			Long businessId) {
		try {
			if (contaminationInfo.getId() == null) {
				contaminationInfo.setBusinessId(businessId);
				contaminationInfoService
						.addContaminationInfo(contaminationInfo);
			} else {
				contaminationInfoService
						.updateContaminationInfo(contaminationInfo);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceBusinessServiceImpl的updateContaminationInfo方法出现异常，原因：",
					"修改单位场所-污染源业务信息出现错误", e);
		}
	}

	private void deleteCompanyPlaceBusiness(CompanyPlaceBusinessVO vo) {
		if (null == vo) {
			throw new BusinessValidationException("参数错误!");
		}
		if (StringUtil.isStringAvaliable(String.valueOf(vo.getDelProKeyID()))) {
			companyPlaceBusinessDao.deleteCompanyPlaceBusiness(vo
					.getDelProKeyID());
		}
		if (StringUtil.isStringAvaliable(String.valueOf(vo.getDelfirKeyID()))) {
			companyPlaceBusinessDao.deleteCompanyPlaceBusiness(vo
					.getDelfirKeyID());
		}
		if (StringUtil.isStringAvaliable(String.valueOf(vo.getDelsecKeyID()))) {
			companyPlaceBusinessDao.deleteCompanyPlaceBusiness(vo
					.getDelsecKeyID());
		}
		if (StringUtil.isStringAvaliable(String.valueOf(vo.getDelpollKeyID()))) {
			companyPlaceBusinessDao.deleteCompanyPlaceBusiness(vo
					.getDelpollKeyID());
		}
	}

	@Override
	public void updateCompanyPlaceBusinessForMobile(CompanyPlaceBusinessVO vo,
			Long companyPlaceId, String modulKey) {
		try {
			companyPlaceBusinessValidator(vo);
			// // 删除keyplaces中单位场所 业务的信息
			deleteKeyPlaceByIds(companyPlaceId, ModulTypes.businessTypes);

			if (vo.getProKeyCompanyPlaceBusiness() != null
					&& vo.getProKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				if (modulKey != null) {
					vo.getProKeyCompanyPlaceBusiness().setCompanyPlaceSource(
							modulKey);
					vo.getProKeyCompanyPlaceBusiness().setCompanyPlaceId(
							companyPlaceId);
				}
				CompanyPlaceBusiness business = updateCompanyPlaceBusiness(
						vo.getProKeyCompanyPlaceBusiness(), companyPlaceId);
				if (business != null) {
					vo.setProKeyCompanyPlaceBusiness(business);
				} else {
					throw new BusinessValidationException("处理业务信息时出错");
				}
				updateFileForAddForMobile(vo.getProKeyAttachFiles(),
						vo.getProKeyCompanyPlaceBusiness());
				KeyPlace keyPlace = keyPlaceService.getKeyPlaceByIdAndType(
						companyPlaceId, ModulTypes.SAFETYPRODUCTIONKEY_KEY);
				manageKeyPlace(keyPlace, companyPlaceId,
						ModulTypes.SAFETYPRODUCTIONKEY_KEY, null);
			}
			if (vo.getFireSafetyKeyCompanyPlaceBusiness() != null
					&& vo.getFireSafetyKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				if (modulKey != null) {
					vo.getFireSafetyKeyCompanyPlaceBusiness()
							.setCompanyPlaceSource(modulKey);
					vo.getFireSafetyKeyCompanyPlaceBusiness()
							.setCompanyPlaceId(companyPlaceId);
				}
				CompanyPlaceBusiness business = updateCompanyPlaceBusiness(
						vo.getFireSafetyKeyCompanyPlaceBusiness(),
						companyPlaceId);
				if (business != null) {
					vo.setFireSafetyKeyCompanyPlaceBusiness(business);
				} else {
					throw new BusinessValidationException("处理业务信息时出错");
				}
				updateFileForAddForMobile(vo.getFireSafetyKeyAttachFiles(),
						vo.getFireSafetyKeyCompanyPlaceBusiness());

				KeyPlace keyPlace = keyPlaceService.getKeyPlaceByIdAndType(
						companyPlaceId, ModulTypes.FIRESAFETYKEY_KEY);
				manageKeyPlace(keyPlace, companyPlaceId,
						ModulTypes.FIRESAFETYKEY_KEY, null);
			}
			if (vo.getSecurityKeyCompanyPlaceBusiness() != null
					&& vo.getSecurityKeyCompanyPlaceBusiness().getIsKeyType() != null) {
				if (modulKey != null) {
					vo.getSecurityKeyCompanyPlaceBusiness()
							.setCompanyPlaceSource(modulKey);
					vo.getSecurityKeyCompanyPlaceBusiness().setCompanyPlaceId(
							companyPlaceId);
				}
				CompanyPlaceBusiness business = updateCompanyPlaceBusiness(
						vo.getSecurityKeyCompanyPlaceBusiness(), companyPlaceId);
				if (business != null) {
					vo.setSecurityKeyCompanyPlaceBusiness(business);
				} else {
					throw new BusinessValidationException("处理业务信息时出错");
				}
				updateFileForAddForMobile(vo.getSecurityKeyAttachFiles(),
						vo.getSecurityKeyCompanyPlaceBusiness());
				KeyPlace keyPlace = keyPlaceService.getKeyPlaceByIdAndType(
						companyPlaceId, ModulTypes.SECURITYKEY_KEY);
				manageKeyPlace(keyPlace, companyPlaceId,
						ModulTypes.SECURITYKEY_KEY, null);
			}
			if (vo.getPollSourceCompanyPlaceBusiness() != null
					&& vo.getPollSourceCompanyPlaceBusiness().getIsKeyType() != null) {
				if (modulKey != null) {
					vo.getPollSourceCompanyPlaceBusiness()
							.setCompanyPlaceSource(modulKey);
					vo.getPollSourceCompanyPlaceBusiness().setCompanyPlaceId(
							companyPlaceId);
				}
				// 污染源地图
				if (vo.getContaminationInfo() != null
						&& vo.getContaminationInfo().getOpenLayersMapInfo() != null) {
					GisTransformatUtil.autoFillOpenLayersMapInfo(vo
							.getContaminationInfo().getOpenLayersMapInfo());
				}

				CompanyPlaceBusiness business = updateCompanyPlaceBusiness(
						vo.getPollSourceCompanyPlaceBusiness(), companyPlaceId);
				if (business != null) {
					vo.setPollSourceCompanyPlaceBusiness(business);
				} else {
					throw new BusinessValidationException("处理业务信息时出错");
				}
				updateFileForAddForMobile(vo.getPollSourceAttachFiles(),
						vo.getPollSourceCompanyPlaceBusiness());
				KeyPlace keyPlace = keyPlaceService.getKeyPlaceByIdAndType(
						companyPlaceId, ModulTypes.POLLUTIONSOURCE_KEY);

				// 修改污染源业务信息
				if (vo.getContaminationInfo() != null) {
					manageKeyPlace(keyPlace, companyPlaceId,
							ModulTypes.POLLUTIONSOURCE_KEY,
							vo.getContaminationInfo());
					updateContaminationInfo(vo.getContaminationInfo(), vo
							.getPollSourceCompanyPlaceBusiness().getId());
				}
			}

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBusinessServiceImpl的updateCompanyPlaceBusinessForMobile方法出现异常，原因：",
					"修改单位场所业务信息出现错误", e);
		}
	}

	@Override
	public CompanyPlaceBusinessVO queryCompanyPlaceBusinessVOByCompanyPlaceIdForList(
			Long companyPlaceId) {
		CompanyPlaceBusinessVO vo = null;
		try {
			if (companyPlaceId == null) {
				throw new BusinessValidationException("没有选中任何场所信息");
			}
			List<CompanyPlaceBusiness> placeBusinessesList = companyPlaceBusinessDao
					.queryCompanyPlaceBusinessByCompanyPlaceIdForList(companyPlaceId);
			if (placeBusinessesList != null && placeBusinessesList.size() > 0) {
				vo = iterablePlaceBusinessesListToVO(placeBusinessesList);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBusinessServiceImpl的queryCompanyPlaceBusinessVOByCompanyPlaceIdForList方法出现异常，原因：",
					"根据场所信息查关联信息出现错误", e);
		}
		return vo;
	}

	@Override
	public CompanyPlaceBusiness getCompanyPlaceBusiness(Long id) {
		CompanyPlaceBusiness companyPlaceBusiness = companyPlaceBusinessDao
				.getCompanyPlaceBusiness(id);
		return companyPlaceBusiness;
	}

	@Override
	public void deleteCompanyPlaceBusinessByCompanyPlaceID(Long companyPlaceId) {
		try {
			List<CompanyPlaceBusiness> companyPlaceBusinessList = queryCompanyPlaceBusinessByCompanyPlaceIdForList(companyPlaceId);
			if (companyPlaceBusinessList != null
					&& companyPlaceBusinessList.size() > 0) {
				companyPlaceBusinessDao
						.deleteCompanyPlaceBusinessByCompanyPalceId(companyPlaceId);
				for (CompanyPlaceBusiness companyPlaceBusiness : companyPlaceBusinessList) {
					companyPlaceAnnexService
							.deleteCompanyPlaceAnnexForBusinessId(companyPlaceBusiness
									.getId());
					// 删除污染源对应的业务信息
					contaminationInfoService
							.deleteContaminationInfoByBusinessId(companyPlaceBusiness
									.getId());
				}
			}
			sysAttachmentService.deleteSysAttachmentByTargetId(companyPlaceId);// 根据场所ID删除对应的附件信息
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBusinessServiceImpl的deleteCompanyPlaceBusinessByCompanyPlaceID方法出现异常，原因：",
					"删除单位场所业务信息出现错误", e);
		}
	}

	@Override
	public void deleteCompanyPlaceBusinessByIdAndIsKeyType(Long companyPlaceId,
			Long isKeyType, String modulKey) {
		try {
			List<CompanyPlaceBusiness> companyPlaceBusinessList = queryCompanyPlaceBusinessByCompanyPlaceIdForList(companyPlaceId);
			CompanyPlaceBusiness business = null;
			for (CompanyPlaceBusiness companyPlaceBusiness : companyPlaceBusinessList) {
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("businessId", companyPlaceBusiness.getId());
				map.put("isKeyType", isKeyType);
				business = companyPlaceBusinessDao
						.getCompanyPlaceBusinessByMap(map);
				if (business != null) {
					companyPlaceAnnexService
							.deleteCompanyPlaceAnnexForBusinessId(business
									.getId());
					companyPlaceBusinessDao.deleteCompanyPlaceBusiness(business
							.getId());
				}

			}
			sysAttachmentService.deleteSysAttachmentByTargetIdAndType(
					companyPlaceId, modulKey);// 根据场所ID删除对应的附件信息
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBusinessServiceImpl的deleteCompanyPlaceBusinessByIdAndIsKeyType方法出现异常，原因：",
					"删除单位场所业务信息出现错误", e);
		}
	}

	private void iterableAttachFiles(Long companyPlaceId, String type,
			String[] files, CompanyPlaceBusiness business) throws Exception {
		if (files != null && files.length > 0) {
			SysAttachment sysAttachment = null;
			for (String attachFileName : files) {
				sysAttachment = getSysAttachments(type, attachFileName,
						companyPlaceId);
				if (sysAttachment != null) {
					addAttachFile(sysAttachment.getRealName(),
							sysAttachment.getFileName(), business);
					deleteSysAttachments(sysAttachment.getId());
				}
			}
		}
	}

	private SysAttachment getSysAttachments(String type, String attachFileName,
			Long companyPlaceId) {
		if (attachFileName.indexOf(",") == 0) {
			attachFileName = attachFileName.substring(1);
		}
		SysAttachment sysAttachment = null;
		List<SysAttachment> list = sysAttachmentService
				.querySysAttachmentByTypeAndFileNameForList(type,
						attachFileName, companyPlaceId);
		for (SysAttachment attachment : list) {
			sysAttachment = attachment;
		}
		return sysAttachment;
	}

	private void deleteSysAttachments(Long id) {
		sysAttachmentService.deleteSysAttachment(id);
	}

	private void updateFileForDelete(String[] attachFiles,
			CompanyPlaceBusiness business) throws Exception {
		List<Long> ids = new ArrayList<Long>();
		if (attachFiles != null && attachFiles.length > 0) {
			for (String fileName : attachFiles) {
				if (fileName.indexOf(",") > 1) {
					ids.add(Long.parseLong(fileName.split(",")[0]));
				}
			}
		}
		deleteFileNotInId(ids, business.getId());
	}

	private void deleteFileNotInId(List<Long> ids, Long businessId) {
		List<CompanyPlaceAnnex> addList = new ArrayList<CompanyPlaceAnnex>();
		CompanyPlaceAnnex companyPlaceAnnex = null;
		for (Long id : ids) {
			companyPlaceAnnex = companyPlaceAnnexService
					.getCompanyPlaceAnnex(id);
			addList.add(companyPlaceAnnex);
		}
		companyPlaceAnnexService
				.deleteCompanyPlaceAnnexForBusinessId(businessId);
		for (int i = 0; i < addList.size(); i++) {
			companyPlaceAnnexService.addCompanyPlaceAnnex(addList.get(i));
		}
	}

	private void updateFileForAdd(Long companyPlaceId, String type,
			String[] files, CompanyPlaceBusiness business) throws Exception {
		if (null != files && files.length > 0) {
			SysAttachment sysAttachment = null;
			for (String fileName : files) {
				if (fileName.indexOf(",") < 1) {
					sysAttachment = getSysAttachments(type, fileName,
							companyPlaceId);
					addAttachFile(sysAttachment.getRealName(),
							sysAttachment.getFileName(), business);
					deleteSysAttachments(sysAttachment.getId());
				}
			}
		}
	}

	private void updateFileForAddForMobile(String[] files,
			CompanyPlaceBusiness business) throws Exception {
		if (null != files && files.length > 0) {
			for (String fileName : files) {
				if (fileName.indexOf(",") < 1) {
					addAttachFileForMobile(fileName, business);
				}
			}
		}
	}

	private CompanyPlaceBusiness updateCompanyPlaceBusiness(
			CompanyPlaceBusiness business, Long companyPlaceId) {
		if (business != null && business.getId() != null) {
			companyPlaceBusinessDao.updateCompanyPlaceBusiness(business);
		} else {
			Long id = companyPlaceBusinessDao.addCompanyPlaceBusiness(business);
			business = companyPlaceBusinessDao.getCompanyPlaceBusiness(id);
		}
		return business;
	}

	private void addAttachFile(String attachFileName, String realName,
			CompanyPlaceBusiness business) throws Exception {
		CompanyPlaceAnnex companyPlaceAnnex = new CompanyPlaceAnnex();
		companyPlaceAnnex.setBusinessId(business);
		StoredFile storeFile = FileUtil.copyTmpFileToStoredFile(realName,
				GridProperties.COMPANY_PALCE_PATH);
		companyPlaceAnnex.setAnnexAddress(storeFile.getStoredFilePath()
				+ File.separator + storeFile.getStoredFileName());
		companyPlaceAnnex.setFileName(attachFileName);
		companyPlaceAnnexService.addCompanyPlaceAnnex(companyPlaceAnnex);
	}

	// 手机端 附件上传
	private void iterableAttachFilesForMobile(String[] files,
			CompanyPlaceBusiness business) throws Exception {
		if (files != null && files.length > 0) {
			for (String attachFileName : files) {
				addAttachFileForMobile(attachFileName, business);
			}
		}
	}

	private void addAttachFileForMobile(String attachFileName,
			CompanyPlaceBusiness business) throws Exception {
		CompanyPlaceAnnex companyPlaceAnnex = new CompanyPlaceAnnex();
		companyPlaceAnnex.setBusinessId(business);
		StoredFile storeFile = FileUtil.copyTmpFileToStoredFile(attachFileName,
				GridProperties.COMPANY_PALCE_PATH);
		companyPlaceAnnex.setAnnexAddress(storeFile.getStoredFilePath()
				+ File.separator + storeFile.getStoredFileName());
		companyPlaceAnnex.setFileName(attachFileName);
		companyPlaceAnnexService.addCompanyPlaceAnnex(companyPlaceAnnex);
	}

	private CompanyPlaceBusinessVO iterablePlaceBusinessesListToVO(
			List<CompanyPlaceBusiness> list) {
		CompanyPlaceBusinessVO vo = new CompanyPlaceBusinessVO();
		CompanyPlaceBusiness business = null;
		for (int i = 0; i < list.size(); i++) {
			business = list.get(i);
			if (business != null) {
				if (business.getIsKeyType().equals(
						IsKeyType.PRODUCTION_KEY_TYPE)) {
					vo.setProKeyCompanyPlaceBusiness(business);
					List<CompanyPlaceAnnex> placeAnnexFiles = companyPlaceAnnexService
							.queryCompanyPlaceAnnexByBusinessForList(business
									.getId());
					vo.getProKeyCompanyPlaceBusiness().setPlaceAnnexFiles(
							placeAnnexFiles);
				}
				if (business.getIsKeyType().equals(
						IsKeyType.FIRESAFETY_KEY_TYPE)) {
					vo.setFireSafetyKeyCompanyPlaceBusiness(business);
					List<CompanyPlaceAnnex> placeAnnexFiles = companyPlaceAnnexService
							.queryCompanyPlaceAnnexByBusinessForList(business
									.getId());
					vo.getFireSafetyKeyCompanyPlaceBusiness()
							.setPlaceAnnexFiles(placeAnnexFiles);
				}
				if (business.getIsKeyType().equals(IsKeyType.SECURITY_KEY_TYPE)) {
					vo.setSecurityKeyCompanyPlaceBusiness(business);
					List<CompanyPlaceAnnex> placeAnnexFiles = companyPlaceAnnexService
							.queryCompanyPlaceAnnexByBusinessForList(business
									.getId());
					vo.getSecurityKeyCompanyPlaceBusiness().setPlaceAnnexFiles(
							placeAnnexFiles);
				}
				if (business.getIsKeyType().equals(
						IsKeyType.POLLUTION_SOURCE_TYPE)) {
					vo.setPollSourceCompanyPlaceBusiness(business);
					List<CompanyPlaceAnnex> placeAnnexFiles = companyPlaceAnnexService
							.queryCompanyPlaceAnnexByBusinessForList(business
									.getId());
					vo.getPollSourceCompanyPlaceBusiness().setPlaceAnnexFiles(
							placeAnnexFiles);
					// 拼装污染源业务信息
					ContaminationInfo contaminationInfo = contaminationInfoService
							.getContaminationInfoByBusinessId(business.getId());
					vo.setContaminationInfo(contaminationInfo);
				}
			}
		}
		return vo;
	}

	private void companyPlaceBusinessValidator(
			CompanyPlaceBusinessVO companyPlaceBusinessVO) {
		ValidateResult baseDataValidator = domainValidator
				.validate(companyPlaceBusinessVO);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	private void deleteKeyPlaceByIds(Long id, String type) {
		keyPlaceService.deleteKeyPlaceByIdAndInType(id, type);
	}

	@Override
	public List<CompanyPlaceBusiness> queryCompanyPlaceBusinessByCompanyPlaceIdForList(
			Long compnayPlaceId) {
		List<CompanyPlaceBusiness> list = companyPlaceBusinessDao
				.queryCompanyPlaceBusinessByCompanyPlaceIdForList(compnayPlaceId);
		return list;
	}

	@Override
	public void deleteCompanyPlaceBusinessByID(Long id) {
		companyPlaceBusinessDao.deleteCompanyPlaceBusiness(id);
	}
}
