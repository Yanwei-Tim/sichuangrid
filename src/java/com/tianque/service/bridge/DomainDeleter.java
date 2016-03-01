package com.tianque.service.bridge;

import com.tianque.core.base.BaseDomain;

public interface DomainDeleter {
	boolean delete(BaseDomain domain);

	boolean checkCanDelete(BaseDomain domain);

}
