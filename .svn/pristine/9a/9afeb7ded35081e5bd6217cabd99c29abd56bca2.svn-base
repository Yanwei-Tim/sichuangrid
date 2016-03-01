package com.tianque.serviceList.dataConverter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.serviceList.domain.PolicyPropaganda;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.PolicyPropagandaService;
import com.tianque.serviceList.service.ReplyService;

@Component("policyPropagandaDataConverter")
public class PolicyPropagandaDataConverter extends
		AbstractDataConverter<PolicyPropaganda> {
	@Autowired
	private PolicyPropagandaService policyPropagandaService;
	@Autowired
	private ReplyService replyService;

	@Qualifier("policyPropagandaImportValidator")
	@Autowired
	private DomainValidator<PolicyPropaganda> policyPropagandaImportValidator;

	/** 将Excel数据转为对象 */
	@Override
	public PolicyPropaganda convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		PolicyPropaganda domain = new PolicyPropaganda();

		ExcelImportHelper.procImportDate(
				SpecialGroupsContext.getCompanyplaceImportArray(), cellValues,
				getUploadOrganization(), domain, vr);
		return domain;
	}

	/** 保存数据到数据库 */
	@Override
	public PolicyPropaganda persistenceDomain(PolicyPropaganda domain) {
		PolicyPropaganda policyPropaganda = null;
		try {
			if (checkDataExitInCache(domain)) {
				return domain;
			}
			policyPropaganda = policyPropagandaService.addPolicyPropaganda(domain);
			if(domain.getIsReply()==1){
				Reply reply=new Reply();
				reply.setReplyDate(domain.getReplyDate());
				reply.setReplyPeople(domain.getReplyPeople());
				reply.setReplyContent(domain.getReplyContent());
				reply.setServiceId(domain.getId());
				reply.setServiceType(ServiceListEnum.getValue("policyPropaganda"));
				reply=replyService.addReply(reply);
			}
		} finally {
			cleanDataInCache(domain);
		}
		return policyPropaganda;
	}

	/** 修改数据到数据库 */
	@Override
	public PolicyPropaganda updateDomain(PolicyPropaganda domain) {
		return null;
	}

	/** 验证Excel数据的正确性 */
	@Override
	public ValidateResult validate(PolicyPropaganda policyPropaganda, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return policyPropagandaImportValidator.validate(policyPropaganda);
	}

	@Override
	public boolean isRepeatData(PolicyPropaganda domain) {
		return false;
	}

	private boolean checkDataExitInCache(PolicyPropaganda domain) {
//		if (domain != null && domain.getName() != null
//				&& !"".equals(domain.getName())
//				&& domain.getOrganization() != null
//				&& domain.getOrganization().getId() != null
//				&& domain.getClassifiCation() != null
//				&& domain.getClassifiCation().getId() != null) {
//			String key = MemCacheConstant.getPolicyPropagandaKey(
//					MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
//							.getClassifiCation().getId(), domain
//							.getOrganization().getId());
//			return !cacheService.add(MemCacheConstant.COMPANYPLACE_NAMESPACE,
//					key, 300, NewBaseInfoTables.COMPANYPLACEKEY);
//		}
		return false;
	}

	public void cleanDataInCache(PolicyPropaganda domain) {
//		String key = MemCacheConstant.getPolicyPropagandaKey(
//				MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
//						.getClassifiCation().getId(), domain.getOrganization()
//						.getId());
//		cacheService.remove(MemCacheConstant.COMPANYPLACE_NAMESPACE, key);
	}
}
