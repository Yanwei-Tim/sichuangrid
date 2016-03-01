package com.tianque.serviceList.domain;

import com.tianque.core.base.BaseDomain;

public class ServiceListAttch extends BaseDomain{
    /**
     * 附件名
     */
    private String name;

    /**
     * 附件路径
     */
    private String path;

    /**
     * 关联的服务清单具体模块id
     */
    private Long serviceId;

    /**
     * 服务清单具体模块类型
     */
    private Integer serviceType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

}