package com.tianque.jms.msg;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Enterprise;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.jms.OperateMode;

public class SiteMsg extends BaseMsg {

	public SiteMsg(LocationBaseInfo locationBaseInfo, OperateMode mode) {
		setOrgId(locationBaseInfo.getOrganization().getId());
		setObjectId(locationBaseInfo.getId());
		setObjectType(locationBaseInfo.getLocationType());
		if (BaseInfoTables.ENTERPRISEKEY_KEY.equals(locationBaseInfo.getLocationType())) {
			Enterprise temp = (Enterprise) locationBaseInfo;
			setObjectType(temp.getKeyType().toUpperCase());
		}
		setMode(mode);
		setMsgType("siteMsg");
	}

}
