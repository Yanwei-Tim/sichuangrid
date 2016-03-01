package com.tianque.component.chain;

import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.Contacter;
import com.tianque.domain.User;

public class Message {
	/** 发送目标 */
	private List<Contacter> target = new ArrayList<Contacter>();
	/** 来源人 */
	private User source;
	/** 附件 */
	private List<String> attachFiles;
	/** 内容 */
	private String content;
	/** 主题 */
	private String subject;

	public List<Contacter> getTarget() {
		return target;
	}

	public void setTarget(List<Contacter> target) {
		this.target = target;
	}

	public User getSource() {
		return source;
	}

	public void setSource(User source) {
		this.source = source;
	}

	public List<String> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<String> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
