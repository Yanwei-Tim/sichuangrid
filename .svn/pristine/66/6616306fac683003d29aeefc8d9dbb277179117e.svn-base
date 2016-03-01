package com.tianque.plugin.transfer.handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tianque.domain.Organization;
import com.tianque.plugin.taskList.service.PositiveInfoRecordService;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @author weiminglong
 *2016年1月4日下午3:51:22
 */
@Component("interviewHandler")
public class InterviewHandler extends Handler{

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PositiveInfoRecordService positiveInfoRecordService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		Organization org = organizationDubboService.getSimpleOrgById(toOrgId);
		String orgCode = org.getOrgInternalCode();
        Long orgId = org.getId();
        positiveInfoRecordService.updateTransferPositiveInfoRecord(id,orgCode,orgId);

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
