package com.tianque.listerner.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.domain.User;
import com.tianque.domain.WorkContacter;
import com.tianque.sysadmin.listerner.UserListernerAdapter;
import com.tianque.userAuth.api.ContacterDubboService;

@Component("workContactListerner")
public class WorkContactListerner extends UserListernerAdapter {

	@Autowired
	private ContacterDubboService contacterDubboService;

	@Override
	public void addUser(User user) {
		WorkContacter workContacter = new WorkContacter();
		workContacter.setMobileNumber(user.getMobile());
		workContacter.setFromUser(user);
		workContacter.setName(user.getName());
		contacterDubboService.addWorkContacter(workContacter);
	}

	@Override
	public void deleteUser(String userName) {
		WorkContacter contacter = contacterDubboService.getWorkContactersByName(userName);
		if (null != contacter) {
			contacterDubboService.deleteContacterById(contacter.getId());
		}
	}

	@Override
	public void update(User user) {
		WorkContacter workContacter = contacterDubboService.getSimpleWorkContacterByUserId(user.getId());
		if (workContacter != null) {
			workContacter.setName(user.getName());
			workContacter.setFromUser(user);
			workContacter.setMobileNumber(user.getMobile());
			contacterDubboService.updateWorkContacter(workContacter);
		}
	}
}
