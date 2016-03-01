package com.tianque.ticket;

import com.tianque.core.base.BaseDomain;

public abstract class AbstractTicket implements Ticket {
	private String ticketId;
	private Long failureTime;
	private Integer state;

	@Override
	public String getTicketId() {
		return ticketId;
	}

	@Override
	public void setTicketId(String id) {
		this.ticketId = id;
	}

	@Override
	public int hashCode() {
		if (ticketId != null)
			return ticketId.hashCode();
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

		Ticket other = (Ticket) obj;
		if (other.getTicketId() == null || getTicketId() == null) {
			return false;
		} else {
			if (other.getTicketId().equals(getTicketId())) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Long getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(Long failureTime) {
		this.failureTime = failureTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
