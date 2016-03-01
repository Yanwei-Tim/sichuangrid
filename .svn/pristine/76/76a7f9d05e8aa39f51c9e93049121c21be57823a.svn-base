package com.tianque.hbase.uitl;

import com.tianque.jms.domain.JmsVo;
import com.tianque.jms.domain.SystemOperateLogDTO;
import com.tianque.systemOperateLog.domain.SystemOperateLog;

public class TransferData {

	public static JmsVo convertToJmsVo(Object object) {
		if (object instanceof SystemOperateLog) {
			return createSystemOperateLogDTO((SystemOperateLog) object);
		}
		return new JmsVo();
	}

	private static SystemOperateLogDTO createSystemOperateLogDTO(
			SystemOperateLog systemOperateLog) {
		SystemOperateLogDTO systemOperateLogDTO = new SystemOperateLogDTO();
		systemOperateLogDTO.setId(systemOperateLog.getId());
		systemOperateLogDTO.setOperateDate(systemOperateLog.getOperateDate());
		systemOperateLogDTO.setOperatePerson(systemOperateLog
				.getOperatePerson());
		systemOperateLogDTO.setModuleType(systemOperateLog.getModuleType());
		systemOperateLogDTO.setBusinessType(systemOperateLog.getBusinessType());
		systemOperateLogDTO.setOperateType(systemOperateLog.getOperateType());
		systemOperateLogDTO.setDataKeyword(systemOperateLog.getDataKeyword());
		if (null != systemOperateLog.getDataOrgId())
			systemOperateLogDTO.setDataOrgId(systemOperateLog.getDataOrgId()
					.getId());
		systemOperateLogDTO.setDataOrgCode(systemOperateLog.getDataOrgCode());
		if (systemOperateLog.getDataBeforeOrgId() != null)
			systemOperateLogDTO.setDataBeforeOrgId(systemOperateLog
					.getDataBeforeOrgId().getId());
		systemOperateLogDTO.setDataBeforeOperate(systemOperateLog
				.getDataBeforeOperate());
		systemOperateLogDTO.setDataAfterOperate(systemOperateLog
				.getDataAfterOperate());
		systemOperateLogDTO.setOperateDescribe(systemOperateLog
				.getOperateDescribe());
		systemOperateLogDTO.setContrastState(systemOperateLog
				.getContrastState());
		systemOperateLogDTO.setDataName(systemOperateLog.getDataName());
		systemOperateLogDTO.setOperateSource(systemOperateLog
				.getOperateSource());
		systemOperateLogDTO.setDataId(systemOperateLog.getDataId());
		systemOperateLogDTO.setOperateDateHbase(systemOperateLog
				.getOperateDateHbase());
		return systemOperateLogDTO;
	}
}
