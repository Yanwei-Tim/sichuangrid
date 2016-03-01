package com.tianque.serviceList.dataConverter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.serviceList.domain.DrugsSafty;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListEnum;
import com.tianque.serviceList.service.DrugsSaftyService;
import com.tianque.serviceList.service.ReplyService;

@Component("drugsSaftyDataConverter")
public class DrugsSaftyDataConverter extends
		AbstractDataConverter<DrugsSafty> {
	@Autowired
	private DrugsSaftyService drugsSaftyService;

	@Qualifier("drugsSaftyImportValidator")
	@Autowired
	private DomainValidator<DrugsSafty> drugsSaftyImportValidator;
	@Autowired
	private ReplyService replyService;
	
	/** 将Excel数据转为对象 */
	@Override
	public DrugsSafty convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		DrugsSafty domain = new DrugsSafty();

		ExcelImportHelper.procImportDate(
				SpecialGroupsContext.getCompanyplaceImportArray(), cellValues,
				getUploadOrganization(), domain, vr);
		return domain;
	}

	/** 保存数据到数据库 */
	@Override
	public DrugsSafty persistenceDomain(DrugsSafty domain) {
		DrugsSafty drugsSafty = null;
		try {
			if (checkDataExitInCache(domain)) {
				return domain;
			}
			drugsSafty = drugsSaftyService.addDrugsSafty(domain);
			if(domain.getIsReply()==1){
				Reply reply=new Reply();
				reply.setReplyDate(domain.getReplyDate());
				reply.setReplyPeople(domain.getReplyPeople());
				reply.setReplyContent(domain.getReplyContent());
				reply.setServiceId(domain.getId());
				reply.setServiceType(ServiceListEnum.getValue("drugsSafty"));
				reply=replyService.addReply(reply);
			}
		} finally {
			cleanDataInCache(domain);
		}
		return drugsSafty;
	}

	/** 修改数据到数据库 */
	@Override
	public DrugsSafty updateDomain(DrugsSafty domain) {
		return null;
	}

	/** 验证Excel数据的正确性 */
	@Override
	public ValidateResult validate(DrugsSafty drugsSafty, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return drugsSaftyImportValidator.validate(drugsSafty);
	}

	@Override
	public boolean isRepeatData(DrugsSafty domain) {
		return false;
	}

	private boolean checkDataExitInCache(DrugsSafty domain) {
//		if (domain != null && domain.getName() != null
//				&& !"".equals(domain.getName())
//				&& domain.getOrganization() != null
//				&& domain.getOrganization().getId() != null
//				&& domain.getClassifiCation() != null
//				&& domain.getClassifiCation().getId() != null) {
//			String key = MemCacheConstant.getDrugsSaftyKey(
//					MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
//							.getClassifiCation().getId(), domain
//							.getOrganization().getId());
//			return !cacheService.add(MemCacheConstant.COMPANYPLACE_NAMESPACE,
//					key, 300, NewBaseInfoTables.COMPANYPLACEKEY);
//		}
		return false;
	}

	public void cleanDataInCache(DrugsSafty domain) {
//		String key = MemCacheConstant.getDrugsSaftyKey(
//				MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
//						.getClassifiCation().getId(), domain.getOrganization()
//						.getId());
//		cacheService.remove(MemCacheConstant.COMPANYPLACE_NAMESPACE, key);
	}
}
