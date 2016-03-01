package com.tianque.baseInfo.personnelTrackInfo.service;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.personnelTrackInfo.domain.PersonnelTrackInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;

public interface PersonnelTrackInfoService {

	public PageInfo<PersonnelTrackInfo> findPersonnelTrackInfoByIdCardNo(
			String idCardNo, Integer page, Integer rows, String sidx,
			String sord);

	/**
	 * 
	 * @param basePersonnel
	 *            实有人口对象
	 * @param oldPersonnelOrg
	 *            原所在网格
	 * @param personnelType
	 *            实有人口类型
	 * @param operationType
	 *            操作类型
	 * @param personinitType
	 *            内置类型
	 * @param operationContent 操作内容
	 */
	public void addPersonnelTrackWhenAttentionedOrCancel(People basePersonnel,
			Organization oldPersonnelOrg, String personnelType,
			Integer operationType, Integer personinitType,
			String operationContent);
}
