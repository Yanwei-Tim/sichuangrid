package com.tianque.component.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.stereotype.Service;

@Service("personelSender")
public class PersonelSender implements Command {
	private Message message;

	@Override
	public boolean execute(Context params) throws Exception {
		message = (Message) params.get("message");
		if (!validate())
			return true;
		return sendMessage();
	}

	private boolean sendMessage() {

		return false;
	}

	private boolean validate() {
		if (message == null)
			return false;
		if (message.getContent() == null || "".equals(message.getContent().trim()))
			return false;
		if (message.getTarget() == null || message.getTarget().size() == 0)
			return false;
		return true;
	}

}
