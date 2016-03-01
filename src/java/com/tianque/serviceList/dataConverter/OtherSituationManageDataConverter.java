package com.tianque.serviceList.dataConverter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.serviceList.domain.OtherSituationManage;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.OtherSituationManageService;
import com.tianque.serviceList.service.ReplyService;

@Component("otherSituationManageDataConverter")
public class OtherSituationManageDataConverter extends
		AbstractDataConverter<OtherSituationManage> {
	@Autowired
	private OtherSituationManageService otherSituationManageService;

	@Qualifier("otherSituationManageImportValidator")
	@Autowired
	private DomainValidator<OtherSituationManage> otherSituationManageImportValidator;
	@Autowired
	private ReplyService replyService;

	/** 将Excel数据转为对象 */
	@Override
	public OtherSituationManage convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		OtherSituationManage domain = new OtherSituationManage();

		ExcelImportHelper.procImportDate(
				SpecialGroupsContext.getCompanyplaceImportArray(), cellValues,
				getUploadOrganization(), domain, vr);
		return domain;
	}

	/** 保存数据到数据库 */
	@Override
	public OtherSituationManage persistenceDomain(OtherSituationManage domain) {
		OtherSituationManage otherSituationManage = null;
		try {
			if (checkDataExitInCache(domain)) {
				return domain;
			}
			otherSituationManage = otherSituationManageService.addOtherSituationManage(domain);
			if(domain.getIsReply()==1){
				Reply reply=new Reply();
				reply.setReplyDate(domain.getReplyDate());
				reply.setReplyPeople(domain.getReplyPeople());
				reply.setReplyContent(domain.getReplyContent());
				reply.setServiceId(domain.getId());
				reply.setServiceType(ServiceListEnum.getValue("otherSituation"));
				reply=replyService.addReply(reply);
			}
		} finally {
			cleanDataInCache(domain);
		}
		return otherSituationManage;
	}

	/** 修改数据到数据库 */
	@Override
	public OtherSituationManage updateDomain(OtherSituationManage domain) {
		return null;
	}

	/** 验证Excel数据的正确性 */
	@Override
	public ValidateResult validate(OtherSituationManage otherSituationManage, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return otherSituationManageImportValidator.validate(otherSituationManage);
	}

	@Override
	public boolean isRepeatData(OtherSituationManage domain) {
		return false;
	}

	private boolean checkDataExitInCache(OtherSituationManage domain) {
//		if (domain != null && domain.getName() != null
//				&& !"".equals(domain.getName())
//				&& domain.getOrganization() != null
//				&& domain.getOrganization().getId() != null
//				&& domain.getClassifiCation() != null
//				&& domain.getClassifiCation().getId() != null) {
//			String key = MemCacheConstant.getOtherSituationManageKey(
//					MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
//							.getClassifiCation().getId(), domain
//							.getOrganization().getId());
//			return !cacheService.add(MemCacheConstant.COMPANYPLACE_NAMESPACE,
//					key, 300, NewBaseInfoTables.COMPANYPLACEKEY);
//		}
		return false;
	}

	public void cleanDataInCache(OtherSituationManage domain) {
//		String key = MemCacheConstant.getOtherSituationManageKey(
//				MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
//						.getClassifiCation().getId(), domain.getOrganization()
//						.getId());
//		cacheService.remove(MemCacheConstant.COMPANYPLACE_NAMESPACE, key);
	}
}
