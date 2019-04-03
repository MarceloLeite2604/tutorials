package org.marceloleite.tutorials.spring.job.configuration.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserJsonVO {

	@JsonProperty("name")
	private NameJsonVO nameJsonVO;

	@JsonProperty("login")
	private LoginJsonVO loginJsonVO;

	public NameJsonVO getNameJsonVO() {
		return nameJsonVO;
	}

	public void setNameJsonVO(NameJsonVO nameJsonVO) {
		this.nameJsonVO = nameJsonVO;
	}

	public LoginJsonVO getLoginJsonVO() {
		return loginJsonVO;
	}

	public void setLoginJsonVO(LoginJsonVO loginJsonVO) {
		this.loginJsonVO = loginJsonVO;
	}

	@Override
	public String toString() {
		return "UserJsonVO [nameJsonVO=" + nameJsonVO + ", loginJsonVO=" + loginJsonVO + "]";
	}
}
