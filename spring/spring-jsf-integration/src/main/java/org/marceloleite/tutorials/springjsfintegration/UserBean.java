package org.marceloleite.tutorials.springjsfintegration;

import javax.inject.Inject;

import org.marceloleite.tutorials.springjsfintegration.bo.UserBo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UserBean {
	
	@Inject
	UserBo userBo;

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	public String printMsgFromSpring() {

		return userBo.getMessage();

	}
}
