package com.tianque.serviceList.dataConverter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.serviceList.domain.BusinessManage;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.BusinessManageService;
import com.tianque.serviceList.service.ReplyService;

@Component("businessManageDataConverter")
public class BusinessManageDataConverter extends
		AbstractDataConverter<BusinessManage> {
	@Autowired
	private BusinessManageService businessManageService;

	@Qualifier("businessManageImportValidator")
	@Autowired
	private DomainValidator<BusinessManage> businessManageImportValidator;
	@Autowired
	private ReplyService replyService;
	
	/** 将Excel数据转为对象 */
	@Override
	public BusinessManage convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		BusinessManage domain = new BusinessManage();

		ExcelImportHelper.procImportDate(
				SpecialGroupsContext.getCompanyplaceImportArray(), cellValues,
				getUploadOrganization(), domain, vr);
		return domain;
	}

	/** 保存数据到数据库 */
	@Override
	public BusinessManage persistenceDomain(BusinessManage domain) {
		BusinessManage businessManage = null;
		try {
			if (checkDataExitInCache(domain)) {
				return domain;
			}
			businessManage = businessManageService.addBusinessManage(domain);
			if(domain.getIsReply()==1){
				Reply reply=new Reply();
				reply.setReplyDate(domain.getReplyDate());
				reply.setReplyPeople(domain.getReplyPeople());
				reply.setReplyContent(domain.getReplyContent());
				reply.setServiceId(domain.getId());
				reply.setServiceType(ServiceListEnum.getValue("bussiness"));
				reply=replyService.addReply(reply);
			}
		} finally {
			cleanDataInCache(domain);
		}
		return businessManage;
	}

	/** 修改数据到数据库 */
	@Override
	public BusinessManage updateDomain(BusinessManage domain) {
		return null;
	}

	/** 验证Excel数据的正确性 */
	@Override
	public ValidateResult validate(BusinessManage businessManage, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return businessManageImportValidator.validate(businessManage);
	}

	@Override
	public boolean isRepeatData(BusinessManage domain) {
		return false;
	}

	private boolean checkDataExitInCache(BusinessManage domain) {
//		if (domain != null && domain.getName() != null
//				&& !"".equals(domain.getName())
//				&& domain.getOrganization() != null
//				&& domain.getOrganization().getId() != null
//				&& domain.getClassifiCation() != null
//				&& domain.getClassifiCation().getId() != null) {
//			String key = MemCacheConstant.getBusinessManageKey(
//					MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
//							.getClassifiCation().getId(), domain
//							.getOrganization().getId());
//			return !cacheService.add(MemCacheConstant.COMPANYPLACE_NAMESPACE,
//					key, 300, NewBaseInfoTables.COMPANYPLACEKEY);
//		}
		return false;
	}

	public void cleanDataInCache(BusinessManage domain) {
//		String key = MemCacheConstant.getBusinessManageKey(
//				MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
//						.getClassifiCation().getId(), domain.getOrganization()
//						.getId());
//		cacheService.remove(MemCacheConstant.COMPANYPLACE_NAMESPACE, key);
	}
}
