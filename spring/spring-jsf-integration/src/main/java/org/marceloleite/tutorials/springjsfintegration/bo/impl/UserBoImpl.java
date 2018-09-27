package org.marceloleite.tutorials.springjsfintegration.bo.impl;

import javax.inject.Named;

import org.marceloleite.tutorials.springjsfintegration.bo.UserBo;

@Named
public class UserBoImpl implements UserBo {
	
	public String getMessage() {
		return "JSF 2 + Spring Integration";
	}
}
