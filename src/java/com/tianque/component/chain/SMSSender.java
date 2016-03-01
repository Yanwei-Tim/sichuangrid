package com.tianque.component.chain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Contacter;
import com.tianque.domain.SmsSendBox;
import com.tianque.domain.vo.ContacterVo;
import com.tianque.service.SmsSendBoxService;
import com.tianque.userAuth.api.ContacterDubboService;

@Service("smsSender")
@Transactional
public class SMSSender implements Command {

	private Message message;
	@Autowired
	private SmsSendBoxService sendBoxService;
	@Autowired
	private ContacterDubboService contacterDubboService;

	@Override
	public boolean execute(Context params) throws Exception {
		message = (Message) params.get("message");
		if (!validate())
			return true;
		return sendMessage();
	}

	private boolean sendMessage() {
		SmsSendBox smsbox = new SmsSendBox();
		smsbox.setSmsContent(message.getContent());
		smsbox.setSendUser(message.getSource());
		smsbox.setSender(message.getSource().getName());
		smsbox.setSendMobile(message.getSource().getMobile());
		for (Contacter contacter : message.getTarget()) {
			if (contacter.getBelongClass().equals(Contacter.MYCONTACTER)
					|| contacter.getBelongClass().equals(Contacter.WORKCONTACTER)) {
				addSingleContacterMessage(smsbox, contacter);
			} else {
				List<ContacterVo> contacterVos = contacterDubboService
						.findMyGroupHasContactersByGroupId(contacter.getId());
				addMultipleContacterMessage(smsbox, contacterVos);
			}
		}
		return false;
	}

	private void addSingleContacterMessage(SmsSendBox smsbox, Contacter contacter) {
		smsbox.setReceiver(contacter.getName());
		sendBoxService.addSmsSendBox(smsbox, Arrays.asList(new Contacter[] { contacter }));
	}

	private void addMultipleContacterMessage(SmsSendBox smsbox, List<ContacterVo> contacters) {
		List<Contacter> result = new ArrayList<Contacter>();
		for (ContacterVo contacter : contacters) {
			result.add(contacter);
		}
		sendBoxService.addSmsSendBox(smsbox, result);
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
