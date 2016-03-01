package com.tianque.plugin.transfer.handler;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.plugin.transfer.vo.ModifyVo;
import com.tianque.service.PopulationTypeService;

@Component("deleteRelateInfoHandler")
public class DeleteRelateInfoHandler extends Handler {
	@Autowired
	public PopulationTypeService populationTypeService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		/** 1.取出前两部操作所生成的新旧对象信息对比list */
		List<ModifyVo> modifyVoList = (List<ModifyVo>) context.getMap().get(
				"modifyVoList");
		/** 2.循环list删除无用的老数据 */
		for (int i = modifyVoList.size() - 1; i >= 0; i--) {
			/** 如果是业务人员，删除与实口的关联关系 */
			populationTypeService.deletePopulationTypeByPopulationIdAndType(
					modifyVoList.get(i).getOldId(), modifyVoList.get(i)
							.getType());
			Object target = SpringBeanUtil
					.getBeanFromSpringByBeanName(getServiceBeanName(modifyVoList
							.get(i).getType()));
			/** 调用数据认领之前撤销认领存在的删除方法 */
			String deleteMethod = "delete"
					+ getClassName(getServiceBeanName(modifyVoList.get(i)
							.getType())) + "ByIds";
			Long[] ids = { modifyVoList.get(i).getOldId() };
			try {
				Method method = target.getClass().getMethod(deleteMethod,
						Long[].class);
				method.invoke(target, (Object) ids);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** 根据传入type获取service名 */
	private String getServiceBeanName(String objetType) {
		if (Constants.SAFETYPRODUCTIONKEY_KEY.equals(objetType)
				|| Constants.SECURITYKEY_KEY.equals(objetType)
				|| Constants.ENTERPRISEKEY_KEY.equals(objetType)
				|| Constants.ENTERPRISEDOWNKEY_KEY.equals(objetType)) {
			return "enterpriseService";
		} else if (Constants.OVERSEAPERSONNEL_KEY.equals(objetType)) {
			return "overseaStaffService";
		} else {
			return objetType + "Service";
		}
	}

	/** 根据传入type获取类名（用于拼接删除方法名） */
	private String getClassName(String objetType) {
		if (Constants.SAFETYPRODUCTIONKEY_KEY.equals(objetType)
				|| Constants.SECURITYKEY_KEY.equals(objetType)
				|| Constants.ENTERPRISEKEY_KEY.equals(objetType)
				|| Constants.ENTERPRISEDOWNKEY_KEY.equals(objetType)) {
			return "Enterprise";
		} else {
			return objetType.substring(0, 1).toUpperCase()
					+ objetType.substring(1);
		}
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Context context) {
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
