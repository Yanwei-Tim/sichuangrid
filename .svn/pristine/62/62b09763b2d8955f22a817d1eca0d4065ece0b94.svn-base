package com.tianque.abtest;

public class ABTask {

	private String taskName;
	private String concurrentCount;
	private String requestsCount;
	private String requestsUrl;

	public String getCommand(String abPath) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(abPath);
		buffer.append(" -n" + getConcurrentCount());
		buffer.append(" -c" + getRequestsCount());
		buffer.append(" \"" + getRequestsUrl() + "\"");
		return buffer.toString();
	}

	public ABTask(String taskName, String concurrentCount,
			String requestsCount, String requestsUrl) {
		super();
		this.taskName = taskName;
		this.concurrentCount = concurrentCount;
		this.requestsCount = requestsCount;
		this.requestsUrl = requestsUrl;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getConcurrentCount() {
		return concurrentCount;
	}

	public void setConcurrentCount(String concurrentCount) {
		this.concurrentCount = concurrentCount;
	}

	public String getRequestsCount() {
		return requestsCount;
	}

	public void setRequestsCount(String requestsCount) {
		this.requestsCount = requestsCount;
	}

	public String getRequestsUrl() {
		return requestsUrl;
	}

	public void setRequestsUrl(String requestsUrl) {
		this.requestsUrl = requestsUrl;
	}
}
