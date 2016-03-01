package com.tianque.plugin.transfer.handler;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.AddressInfoService;
import com.tianque.baseInfo.overseaPersonnel.service.OverseaPersonnelService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.HouseInfoDao;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.transfer.dao.HouseDao;
import com.tianque.plugin.transfer.dao.TransferOtherInfoDao;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.shard.util.IdConversionShardUtil;

@Component("houseHandler")
public class HouseHandler extends Handler {
	@Autowired
	private HouseDao houseDaoImpl;
	@Autowired
	private HouseInfoDao houseinfo;
	@Autowired
	private TransferOtherInfoDao transferOtherInfoDao;
	@Autowired
	private AddressInfoService addressInfoService;
	@Autowired
	private OverseaPersonnelService overseaPersonnelService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		if ("person".equals(TransferUtil.getBigType(type))) {
			try {

				// 通过type、id来得到具体的数据
				Countrymen population = this.<Countrymen> executeGet(type, id);// 原来网格中的人员信息
				if (population == null) {
					throw new BusinessValidationException("处理被转移对象的房屋信息出错！");
				}
				// // 通过查询身份证和目标网格去寻找信息
				// Countrymen populationB = this.<Countrymen>
				// executeGetByOrgIdAndIdCardNo(population
				// .getId(), type, toOrgId, population.getIdCardNo());//
				// 目标网格中的人员信息
				if ("unsettledPopulation".equals(type)
						|| "overseaPersonnel".equals(type)) {
					// 未落户及境外人员是没有相关的业务人员的，因此直接判断自己即可
					if (Boolean.TRUE.equals(population.getIsHaveHouse())) {
						// 有房，看房屋的网格与目标网格是否一致，不一致时删除原来的人房关联，改成未知
						if ("overseaPersonnel".equals(type)) {
							operationWhenActualHasHouseDealSelf(population,
									NewBaseInfoTables.OVERSEASTAFF_KEY, toOrgId);
						} else {
							operationWhenActualHasHouseDealSelf(population,
									type, toOrgId);
						}

					}
				} else if ("householdStaff".equals(type)
						|| "floatingPopulation".equals(type)) {
					// 通过查询身份证和目标网格去寻找信息
					Countrymen populationB = this
							.<Countrymen> executeGetByOrgIdAndIdCardNo(
									population.getId(), type, toOrgId,
									population.getIdCardNo());// 目标网格中的人员信息
					// 若是实口类型
					// 找到实口对应的所有业务人员的id和type
					List<PopulationTypeBean> beanList = houseDaoImpl
							.getAllImportantByActualIdAndType(
									populationB.getId(), type);
					if (beanList.size() > 0) {
						for (PopulationTypeBean allTypeAndId : beanList) {
							transferOtherInfoDao.updatePopulationAddressInfoId(
									BaseInfoTables.getKeytables().get(
											allTypeAndId.getPopulationType()),
									populationB.getAddressInfoId(),
									allTypeAndId.getPopulationId());
						}
						// 实口对应的业务人员存在时，判断实口的房屋信息
						if (Boolean.TRUE.equals(populationB.getIsHaveHouse())) {
							// A 有房
							operationWhenActualHasHouse(populationB, type,
									toOrgId, beanList);
						} else if (Boolean.FALSE.equals(populationB
								.getIsHaveHouse())) {
							// B 无房
							operationWhenActualNoHouse(beanList, populationB);
						} else {
							// C 未知
							operationWhenActualHouseIsNull(beanList,
									populationB);
						}
					} else {
						// 实口对应的业务人员不存在（只需要处理实口有房时的情况）
						if (Boolean.TRUE.equals(populationB.getIsHaveHouse())) {
							// 实口有房
							operationWhenActualHasHouseDealSelf(populationB,
									type, toOrgId);
						}
					}
				} else {
					// 通过查询身份证和目标网格去寻找信息
					Countrymen populationB = this
							.<Countrymen> executeGetByOrgIdAndIdCardNo(
									population.getId(), type, toOrgId,
									population.getIdCardNo());// 目标网格中的人员信息
					// 若是业务人员类型
					// 得到业务人员的相关的实口id和实口类型
					PopulationTypeBean bean = (PopulationTypeBean) houseDaoImpl
							.getByPopulationIdAndPopulationType(
									populationB.getId(), type);
					// 若是业务人员对应的实口id和实口类型存在则处理房屋(业务人员注销状态下 无关联实口)
					if (bean != null) {
						// 找到相关的实口信息
						Countrymen men = this.<Countrymen> executeGet(
								bean.getActualType(), bean.getActualId());
						transferOtherInfoDao.updatePopulationAddressInfoId(
								BaseInfoTables.getKeytables().get(
										bean.getPopulationType()),
								men.getAddressInfoId(), bean.getPopulationId());
						if (Boolean.TRUE.equals(men.getIsHaveHouse())) {
							// 若实口中的房屋信息是有房
							importantTypeAndActualHasHouse(bean, populationB,
									toOrgId, type, men);
						} else if (Boolean.FALSE.equals(men.getIsHaveHouse())) {
							// 若实口中的房屋信息是无房,就将业务人员的人房关联去除，并更新房屋信息为无房
							importantTypeWhenActualNoHouse(populationB, men,
									type);
						} else {
							// 若实口中的房屋信息是未知,就将业务人员的人房关联去除，并更新房屋信息为未知
							importantTypeWhenActualIsNull(populationB, men,
									type);
						}
					}
				}
			} catch (Exception e) {
				throw new ServiceValidationException("处理被转移对象的房屋信息出错！", e);
			}
		}
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Context context) {
		// System.out.println("sssssssssss");
	}

	/**
	 * 通过id得到人口信息
	 * 
	 * @param type
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private <T> T executeGet(String type, Long id) throws Exception {
		Object target;
		if ("overseaPersonnel".equals(type)) {
			target = SpringBeanUtil
					.getBeanFromSpringByBeanName("overseaStaffService");
		} else if (PopulationType.AIDSPOPULATIONS_KEY.equals(type)) {
			target = SpringBeanUtil
					.getBeanFromSpringByBeanName(PopulationType.AIDSPOPULATIONS
							+ "Service");
		} else {
			target = SpringBeanUtil.getBeanFromSpringByBeanName(type
					+ "Service");
		}
		String methodName;
		Method method;
		if ("dangerousGoodsPractitioner".equals(type)
				|| "otherAttentionPersonnel".equals(type)) {
			methodName = "getSimple" + conventClassNameFirstUpper(type)
					+ "ById";
		}
		// else if ("overseaPersonnel".equals(type)) {
		// methodName = "getOverseaPersonnelById";
		// }
		else {
			methodName = "get" + conventClassNameFirstUpper(type) + "ById";
		}
		method = target.getClass().getMethod(methodName, Long.class);
		return (T) method.invoke(target, new Object[] { id });

	}

	/**
	 * 通过查询身份证和目标网格去寻找信息
	 * 
	 * @param id
	 * @param type
	 * @param toOrgId
	 * @param idCardNo
	 * @return
	 * @throws Exception
	 */
	private <T> T executeGetByOrgIdAndIdCardNo(Long id, String type,
			Long toOrgId, String idCardNo) throws Exception {
		Object target = SpringBeanUtil
				.getBeanFromSpringByBeanName(type + "Dao");
		String methodName;
		Method method;
		methodName = "getByOrgIdAndIdCardNo";
		method = target.getClass().getMethod(methodName, Long.class,
				String.class);
		return (T) method.invoke(target, new Object[] { toOrgId, idCardNo });
	}

	/**
	 * 更新人员的房屋信息
	 * 
	 * @param type
	 * @param men
	 * @return
	 * @throws Exception
	 */
	private <T> T executeupdateImport(String type, Countrymen men)
			throws Exception {
		Object target = SpringBeanUtil
				.getBeanFromSpringByBeanName(type + "Dao");
		// String methodName = "update" + conventClassNameFirstUpper(type);
		String methodName = "update";
		Method method = target.getClass().getMethod(methodName,
				Countrymen.class);
		return (T) method.invoke(target, new Object[] { men });

	}

	/**
	 * 房屋信息的组装(针对业务人员)
	 * 
	 * @param actualP
	 * @param importantP
	 * @return
	 */
	private Countrymen constructImportantHouseInfo(Countrymen actualP,
			Countrymen importantP) {
		importantP.setIsHaveHouse(actualP.getIsHaveHouse());
		importantP.setCurrentAddress(actualP.getCurrentAddress());
		importantP.setCurrentAddressType(actualP.getCurrentAddressType());
		importantP.setNoHouseReason(actualP.getNoHouseReason());
		importantP.setCommunity(actualP.getCommunity());
		importantP.setBlock(actualP.getBlock());
		importantP.setUnit(actualP.getUnit());
		importantP.setRoom(actualP.getRoom());
		return importantP;
	}

	/**
	 * 房屋信息的组装（针对实口）
	 * 
	 * @param actualP
	 * @return
	 */
	private Countrymen constructActualHouseInfo(Countrymen actualP) {
		actualP.setIsHaveHouse(null);// 改好后就是未知了
		actualP.setCurrentAddress(null);
		actualP.setNoHouseReason(null);
		return actualP;
	}

	/**
	 * 通过type判断是哪类大类
	 * 
	 * @param type
	 * @return
	 */
	private String isWhichType(String type) {
		String str = "";
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		if (PopulationCatalog.ALL_ATTENTION_POPULATION.equals(catalog
				.getParentCatalog())) {
			// 重点人员
			str = "all_attention_population";
		} else if (PopulationCatalog.ALL_UNEMPLOYED_POPULATION.equals(catalog
				.getParentCatalog())) {
			// 失业人员
			str = "all_unemployed_population";
		} else if (PopulationCatalog.ALL_LOVINGCARE_POPULATION.equals(catalog
				.getParentCatalog())) {
			// 关怀人员
			str = "all_lovingCare_population";
		} else if (PopulationCatalog.ALL_BIRTH_POPULATION.equals(catalog
				.getParentCatalog())) {
			// 计生
			str = "all_birth_population";
		} else {
			// 民政
			str = "all_civil_population";
		}
		return str;

	}

	/**
	 * 组装人员信息，并且更新各自的房屋信息
	 * 
	 * @param importantPopulation
	 * @param populationB
	 * @param type
	 */
	private void operationPopulationInfo(Countrymen importantPopulation,
			Countrymen populationB, String type) {
		try {
			importantPopulation = constructImportantHouseInfo(populationB,
					importantPopulation);
			this.<Countrymen> executeupdateImport(type, importantPopulation);
		} catch (Exception e) {
			throw new ServiceValidationException("处理组装人员信息更新房屋信息出错！", e);

		}
	}

	/**
	 * 将类型名按首字母大写返回 druggy->Druggy
	 * 
	 * @param className
	 * @return
	 */
	private <T> String conventClassNameFirstUpper(String className) {
		return className.substring(0, 1).toUpperCase() + className.substring(1);
	}

	/**
	 * 网格相同(实口类型)
	 * 
	 * @param beanList
	 * @param populationB
	 * @param type
	 * @param actualHouseIdRelation
	 */
	private void operationWhenActualTypeAndOrgIsEq(
			List<PopulationTypeBean> beanList, Countrymen populationB,
			String type, HouseHasActualPopulation actualHouseIdRelation) {
		for (PopulationTypeBean allTypeAndId : beanList) {
			// 查询业务人员的houseid，若与实口的houseid不同，就要改变
			HouseHasActualPopulation importantHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
					.getByImportantIdAndImportantType(
							allTypeAndId.getPopulationId(),
							allTypeAndId.getPopulationType());
			// 业务人员的关联存在
			if (null != importantHouseIdRelation) {
				if (!importantHouseIdRelation.getHouseId().equals(
						actualHouseIdRelation.getHouseId())) {
					// 两者的房屋编号不一致就要更新业务人员的人房关联和信息(addressInfoId)
					operationWhenHouseIdExistButNotEqual(allTypeAndId,
							importantHouseIdRelation, actualHouseIdRelation,
							populationB);
				}
			} else {
				// 业务人员的关联不存在，说明原本无关联，就要增加关联，修改房屋信息
				operationWhenHouseIdNotExist(allTypeAndId,
						actualHouseIdRelation, populationB);
			}
		}
	}

	/**
	 * 网格不同(实口类型)
	 * 
	 * @param beanList
	 * @param populationB
	 * @param type
	 * @param actualHouseIdRelation
	 */
	private void operationWhenActualTypeAndOrgIsNotEq(
			List<PopulationTypeBean> beanList, Countrymen populationB,
			String type, HouseHasActualPopulation actualHouseIdRelation) {
		try {
			// 房屋人数
			Long houseId = houseHasActualPopulationService
					.getHouseIdByPopulationTypeAndPopulationId(type,
							populationB.getId());

			if (houseId != null) {
				// 去除实口的人房关联，修改实口的房屋信息
				houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId(
						"true", populationB.getId(), type);
				String shardCode = IdConversionShardUtil
						.getShardCodeById(houseId);
				actualHouseService.updateHouseInfoMemberNum(shardCode, houseId,
						-1);
			}
			populationB = constructActualHouseInfo(populationB);
			// this.<Countrymen> executeupdateImport(type, populationB);
			addressInfoService.update(populationB);
			// 处理业务人员
			for (PopulationTypeBean allTypeAndId : beanList) {
				// 得到业务人员的关联的Houseid
				HouseHasActualPopulation importantHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
						.getByImportantIdAndImportantType(
								allTypeAndId.getPopulationId(),
								allTypeAndId.getPopulationType());
				if (null != importantHouseIdRelation) {
					// 业务人员关联存在，就删除人房关联
					orgNotEqOperationWhenHouseIdExist(allTypeAndId,
							importantHouseIdRelation, populationB);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"HouseHandler类的operationWhenActualTypeAndOrgIsNotEq方法出错",
					"处理实口类型网格不同时出错！", e);
		}
	}

	/**
	 * 业务人员houseid不存在，说明原本无关联，就要增加关联，新增房屋信息（实口类型网格相同）
	 * 
	 * @param allTypeAndId
	 * @param type
	 * @param actualHouseIdRelation
	 * @param populationB
	 */
	private void operationWhenHouseIdNotExist(PopulationTypeBean allTypeAndId,
			HouseHasActualPopulation actualHouseIdRelation,
			Countrymen populationB) {
		try {
			houseDaoImpl.addHouseRelation(
					isWhichType(allTypeAndId.getPopulationType()),
					allTypeAndId.getPopulationType(),
					actualHouseIdRelation.getHouseId(),
					allTypeAndId.getPopulationId(), Boolean.TRUE);
			// 先得到业务人员信息，再组装新的信息，然后更新房屋信息11
			// Countrymen importantPopulation;
			// importantPopulation = this.<Countrymen>
			// executeGet(allTypeAndId.getPopulationType(),
			// allTypeAndId.getPopulationId());
			// operationPopulationInfo(importantPopulation, populationB,
			// allTypeAndId.getPopulationType());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"HouseHandler类的operationWhenHouseIdNotExist方法出错\n",
					"处理实口类型网格相同时业务人员增加人房关联信息出错！", e);
		}

	}

	/**
	 * 业务人员houseid存在但跟实口的不一样,就要更新业务人员的人房关联和信息（实口类型网格相同）
	 * 
	 * @param allTypeAndId
	 * @param importantHouseIdRelation
	 * @param actualHouseIdRelation
	 * @param populationB
	 */
	private void operationWhenHouseIdExistButNotEqual(
			PopulationTypeBean allTypeAndId,
			HouseHasActualPopulation importantHouseIdRelation,
			HouseHasActualPopulation actualHouseIdRelation,
			Countrymen populationB) {
		try {
			houseDaoImpl.updateImportantHouseId(allTypeAndId.getPopulationId(),
					allTypeAndId.getPopulationType(),
					importantHouseIdRelation.getHouseId(),
					actualHouseIdRelation.getHouseId());
			// 先得到业务人员信息，再组装新的信息，然后更新房屋信息22
			// Countrymen importantPopulation;
			// importantPopulation = this.<Countrymen>
			// executeGet(allTypeAndId.getPopulationType(),
			// allTypeAndId.getPopulationId());
			// operationPopulationInfo(importantPopulation, populationB,
			// allTypeAndId.getPopulationType());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"HouseHandler类的operationWhenHouseIdExistButNotEqual方法出错\n",
					"处理实口类型网格相同时业务人员修改人房关联信息出错！", e);
		}
	}

	/**
	 * 业务人员的houseid存在，就删除人房关联，更新信息(实口类型网格不同)
	 * 
	 * @param allTypeAndId
	 * @param importantHouseIdRelation
	 * @param populationB
	 */
	private void orgNotEqOperationWhenHouseIdExist(
			PopulationTypeBean allTypeAndId,
			HouseHasActualPopulation importantHouseIdRelation,
			Countrymen populationB) {
		try {
			houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId("false",
					allTypeAndId.getPopulationId(),
					allTypeAndId.getPopulationType());
			// 先得到业务人员信息，再组装新的信息，然后更新房屋信息33
			// Countrymen importantPopulation;
			// importantPopulation = this.<Countrymen>
			// executeGet(allTypeAndId.getPopulationType(),
			// allTypeAndId.getPopulationId());
			// operationPopulationInfo(importantPopulation, populationB,
			// allTypeAndId.getPopulationType());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"HouseHandler类的operationWhenHouseIdExistButNotEqual方法出错\n",
					"处理实口类型网格不同时业务人员删除人房关联信息出错！", e);
		}

	}

	/**
	 * 业务人员的houseid不存在，就查到业务人员的信息，看房屋状态，是无的改成未知，原来就是未知的就不变(实口类型网格不同)
	 * 
	 * @param allTypeAndId
	 * @param importantHouseIdRelation
	 * @param populationB
	 */
	private void orgNotEqOperationWhenHouseIdNotExist(
			PopulationTypeBean allTypeAndId,
			HouseHasActualPopulation importantHouseIdRelation,
			Countrymen populationB) {
		try {
			Countrymen importantPopulation = this.<Countrymen> executeGet(
					allTypeAndId.getPopulationType(),
					allTypeAndId.getPopulationId());
			if (Boolean.FALSE.equals(importantPopulation.getIsHaveHouse())) {
				operationPopulationInfo(importantPopulation, populationB,
						allTypeAndId.getPopulationType());
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"HouseHandler类的orgNotEqOperationWhenHouseIdNotExist方法出错\n",
					"处理实口类型网格不同时业务人员修改房屋信息出错！", e);
		}
	}

	/**
	 * A 有房（实口类型）
	 * 
	 * @param populationB
	 * @param type
	 * @param toOrgId
	 * @param beanList
	 */
	private void operationWhenActualHasHouse(Countrymen populationB,
			String type, Long toOrgId, List<PopulationTypeBean> beanList) {
		// 1、得到实口对应的houseid的网格信息
		HouseHasActualPopulation actualHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
				.getByActualIdAndActualType(populationB.getId(), type);
		if (actualHouseIdRelation == null) {
			return;
		}
		HouseInfo actualHouseInfo = houseinfo
				.getSimpleHouseInfoById(actualHouseIdRelation.getHouseId());
		// 2、比较网格，相同实口不变，业务要变的变；不同实口去除人房关联，业务也要变动
		if (toOrgId.equals(actualHouseInfo.getOrganization().getId())) {
			// 网格相同
			operationWhenActualTypeAndOrgIsEq(beanList, populationB, type,
					actualHouseIdRelation);
		} else {
			// 网格不同
			operationWhenActualTypeAndOrgIsNotEq(beanList, populationB, type,
					actualHouseIdRelation);
		}
	}

	/**
	 * B 无房（实口类型）
	 * 
	 * @param beanList
	 * @param populationB
	 */
	private void operationWhenActualNoHouse(List<PopulationTypeBean> beanList,
			Countrymen populationB) {
		try {
			for (PopulationTypeBean allTypeAndId : beanList) {
				// 找到各个业务人员的信息
				HouseHasActualPopulation importantHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
						.getByImportantIdAndImportantType(
								allTypeAndId.getPopulationId(),
								allTypeAndId.getPopulationType());
				if (null != importantHouseIdRelation) {
					// 业务人员有房,去除关联，更新房屋信息
					houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId(
							"false", allTypeAndId.getPopulationId(),
							allTypeAndId.getPopulationType());
					// operationPopulationInfo(importantPopulation, populationB,
					// allTypeAndId.getPopulationType());
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"HouseHandler类的operationWhenActualNoHouse方法出错\n",
					"处理实口类型无房时出错！", e);
		}
	}

	/**
	 * C 未知（实口类型）
	 * 
	 * @param beanList
	 * @param populationB
	 */
	private void operationWhenActualHouseIsNull(
			List<PopulationTypeBean> beanList, Countrymen populationB) {
		try {
			for (PopulationTypeBean allTypeAndId : beanList) {
				HouseHasActualPopulation importantHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
						.getByImportantIdAndImportantType(
								allTypeAndId.getPopulationId(),
								allTypeAndId.getPopulationType());
				if (null != importantHouseIdRelation) {
					// 业务人员有房,去除关联，更新房屋信息
					houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId(
							"false", allTypeAndId.getPopulationId(),
							allTypeAndId.getPopulationType());
				}
			}
		} catch (Exception e) {
			logger.error("HouseHandler类的operationWhenActualHouseIsNull方法出错\n",
					e);
			throw new ServiceValidationException("处理实口类型未知时出错！", e);
		}

	}

	/**
	 * 业务人口类型，实口有房
	 * 
	 * @param bean
	 * @param populationB
	 * @param toOrgId
	 * @param importantHouseId
	 * @param type
	 * @param men
	 */
	private void importantTypeAndActualHasHouse(PopulationTypeBean bean,
			Countrymen populationB, Long toOrgId, String type, Countrymen men) {
		// 1、得到实口关联的房屋id
		HouseHasActualPopulation actualHouseId = (HouseHasActualPopulation) houseDaoImpl
				.getByActualIdAndActualType(bean.getActualId(),
						bean.getActualType());
		// 2、根据房屋id得到房屋信息
		HouseInfo houseB = houseinfo.getSimpleHouseInfoById(actualHouseId
				.getHouseId());
		// 3、判断房屋所在网格是否跟目标网格相同
		if (toOrgId.equals(houseB.getOrganization().getId())) {
			// 3.1 若网格相同，看实口和业务的房屋id是否相同，不相同时更新业务人员的房屋信息
			orgEqImportantType(actualHouseId, populationB, type, men);

		} else {
			// 3.2 网格不同
			orgNotEqImportantType(bean, men, type, populationB);

		}
	}

	/**
	 * 3.1
	 * 若网格相同，看业务人员是否有房，有房时得到它关联的houseid，跟实口houseid对比，同不变，不同改变；若业务人员无房，直接新增人房关联
	 * ，并修改房屋信息（业务人口类型）
	 * 
	 * @param importantHouseId
	 * @param actualHouseId
	 * @param populationB
	 * @param type
	 * @param men
	 */
	private void orgEqImportantType(HouseHasActualPopulation actualHouseId,
			Countrymen populationB, String type, Countrymen men) {
		HouseHasActualPopulation importantHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
				.getByImportantIdAndImportantType(populationB.getId(), type);
		if (null != importantHouseIdRelation) {
			// 1、业务人员有房
			// 通过type、id来得到业务人员相关的房屋id
			if (!importantHouseIdRelation.getHouseId().equals(
					actualHouseId.getHouseId())) {
				// 当实口业务houseid不同时，就要修改业务人员的人房关联和信息
				// 更新业务人员的人房关联
				houseDaoImpl.updateImportantHouseId(populationB.getId(), type,
						importantHouseIdRelation.getHouseId(),
						actualHouseId.getHouseId());
			}
		} else {
			// 2、业务人员无房或未知
			// 增加业务人员的人房关联
			houseDaoImpl.addHouseRelation(isWhichType(type), type,
					actualHouseId.getHouseId(), populationB.getId(),
					Boolean.TRUE);
		}
	}

	/**
	 * 3.2 网格不同（业务人口类型）
	 * 
	 * @param bean
	 * @param men
	 * @param type
	 * @param populationB
	 */
	private void orgNotEqImportantType(PopulationTypeBean bean, Countrymen men,
			String type, Countrymen populationB) {
		try {
			// 房屋人数
			Long houseId = houseHasActualPopulationService
					.getHouseIdByPopulationTypeAndPopulationId(
							bean.getActualType(), bean.getActualId());

			if (houseId != null) {
				// 删除实口的人房关联，变未知
				houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId(
						"true", bean.getActualId(), bean.getActualType());
				String shardCode = IdConversionShardUtil
						.getShardCodeById(houseId);
				actualHouseService.updateHouseInfoMemberNum(shardCode, houseId,
						-1);
			}
			men = constructActualHouseInfo(men);
			addressInfoService.update(men);
			HouseHasActualPopulation importantHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
					.getByImportantIdAndImportantType(populationB.getId(), type);
			// 1、业务人员有房，就删掉人房关联，并进行修改房屋信息
			if (null != importantHouseIdRelation) {
				houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId(
						"false", populationB.getId(), type);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"HouseHandler类的orgNotEqImportantType方法出错\n",
					"处理业务人口类型网格不同时出错！", e);
		}
	}

	/**
	 * 若实口中的房屋信息是无房,就将业务人员的人房关联去除，并更新房屋信息为无房（业务人口类型）
	 * 
	 * @param populationB
	 * @param men
	 * @param type
	 */
	private void importantTypeWhenActualNoHouse(Countrymen populationB,
			Countrymen men, String type) {
		HouseHasActualPopulation importantHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
				.getByImportantIdAndImportantType(populationB.getId(), type);
		if (null != importantHouseIdRelation) {
			// 业务人员有房
			houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId("false",
					populationB.getId(), type);
		}
	}

	/**
	 * 若实口中的房屋信息是未知,就将业务人员的人房关联去除，并更新房屋信息为未知（业务人口类型）
	 * 
	 * @param populationB
	 * @param men
	 * @param type
	 */
	private void importantTypeWhenActualIsNull(Countrymen populationB,
			Countrymen men, String type) {
		HouseHasActualPopulation importantHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
				.getByImportantIdAndImportantType(populationB.getId(), type);
		if (null != importantHouseIdRelation) {
			// 业务人员有房
			houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId("false",
					populationB.getId(), type);
		}
	}

	/**
	 * 实口对应的业务人员不存在（只需要处理实口有房时的情况）
	 * 
	 * @param populationB
	 * @param type
	 * @param toOrgId
	 */
	private void operationWhenActualHasHouseDealSelf(Countrymen populationB,
			String type, Long toOrgId) {
		try {
			// 得到实口对应的houseid的网格信息
			HouseHasActualPopulation actualHouseIdRelation = (HouseHasActualPopulation) houseDaoImpl
					.getByActualIdAndActualType(populationB.getId(), type);
			HouseInfo actualHouseInfo = houseinfo
					.getSimpleHouseInfoById(actualHouseIdRelation.getHouseId());
			if (!toOrgId.equals(actualHouseInfo.getOrganization().getId())) {
				// 网格不同，要修改成未知
				// 房屋人数
				Long tempHouseId = houseHasActualPopulationService
						.getHouseIdByPopulationTypeAndPopulationId(type,
								populationB.getId());

				if (tempHouseId != null) {
					houseDaoImpl.deleteRelationByPopulationTypeAndPopulationId(
							"true", populationB.getId(), type);
					if (tempHouseId.equals(actualHouseInfo.getId())) {
						String shardCode = IdConversionShardUtil
								.getShardCodeById(actualHouseInfo.getId());
						actualHouseService.updateHouseInfoMemberNum(shardCode,
								actualHouseInfo.getId(), -1);
					}
				}
				populationB = constructActualHouseInfo(populationB);
				/** 因为境外人员的住房信息等没有存在addressinfo表中，所以要手动将境外人员表中的相关字段置空 */
				if (NewBaseInfoTables.OVERSEASTAFF_KEY.equals(type)) {
					overseaPersonnelService.updateOverseaPersonnelHouseInfo(
							populationB.getId(), Boolean.FALSE, "被"
									+ ThreadVariable.getUser().getName() + "于"
									+ DateUtil.formateYMD(new Date()) + "移除");
				}
				addressInfoService.update(populationB);

			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"HouseHandler类的operationWhenActualHasHouseDealSelf方法出错\n",
					"处理实口类型对应业务人员不存在时出错！", e);
		}
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
	}

	@Override
	public void invoke(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
		invoke(type, id, toOrgId, context);
	}
}
