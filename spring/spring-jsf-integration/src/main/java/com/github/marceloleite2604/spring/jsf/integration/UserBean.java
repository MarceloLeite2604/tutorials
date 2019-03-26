package com.github.marceloleite2604.spring.jsf.integration;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.spring.jsf.integration.bo.UserBo;

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
