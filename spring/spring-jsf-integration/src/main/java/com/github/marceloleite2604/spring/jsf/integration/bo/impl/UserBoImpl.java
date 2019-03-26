package com.github.marceloleite2604.spring.jsf.integration.bo.impl;

import javax.inject.Named;

import com.github.marceloleite2604.spring.jsf.integration.bo.UserBo;

@Named
public class UserBoImpl implements UserBo {
	
	public String getMessage() {
		return "JSF 2 + Spring Integration";
	}
}
