package com.github.marceloleite2604.tutorials.modelmapper.service;

import javax.inject.Inject;

import com.github.marceloleite2604.tutorials.modelmapper.util.ServiceUtil;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.MessageLoader;

public abstract class AbstractService {
	
	@Inject
	protected ServiceUtil serviceUtil;
	
	@Inject
	protected MessageLoader messageLoader;

}
