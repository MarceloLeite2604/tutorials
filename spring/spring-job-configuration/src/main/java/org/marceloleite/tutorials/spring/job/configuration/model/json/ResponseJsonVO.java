package org.marceloleite.tutorials.spring.job.configuration.model.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseJsonVO {

	@JsonProperty("results")
	private List<UserJsonVO> userJsonVOs;

	public List<UserJsonVO> getUserJsonVOs() {
		return userJsonVOs;
	}

	public void setUserJsonVOs(List<UserJsonVO> userJsonVos) {
		this.userJsonVOs = userJsonVos;
	}
	
}
