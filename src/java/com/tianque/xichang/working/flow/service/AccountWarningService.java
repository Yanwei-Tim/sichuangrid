package com.tianque.xichang.working.flow.service;

import com.tianque.core.vo.GridPage;
import com.tianque.xichang.working.domain.CommonWorking;

public interface AccountWarningService {

	public void fillUnDoEaringWarn(Long orgId, String orgCode,
			GridPage<CommonWorking> gridPage, Integer isFinish,
			String accountType);

	public void fillDoneEaringWarn(Long orgId, String orgCode,
			GridPage<CommonWorking> gridPage, Integer isFinish,
			String accountType);

}
