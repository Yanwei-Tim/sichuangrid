package com.tianque.domain;

import java.io.Serializable;

import com.tianque.core.base.BaseDomain;

public class NoMaintantInfoDomain implements Serializable {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		if (id != null) {
			final int prime = 31;
			int result = 1;
			result = prime * (prime * result + getClass().hashCode()) + id.hashCode();
			return result;
		}
		return super.hashCode();
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BaseDomain)) {
			return false;
		}

		if ((getClass().isAssignableFrom(obj.getClass()))
				|| (obj.getClass().isAssignableFrom(getClass()))) {

		} else {
			return false;
		}

		NoMaintantInfoDomain other = (NoMaintantInfoDomain) obj;
		if (other.getId() == null || getId() == null) {
			return false;
		} else {
			if (other.getId().equals(getId())) {
				return true;
			} else {
				return false;
			}
		}
	}
}
