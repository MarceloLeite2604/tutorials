package com.github.marceloleite2604.tutorials.modelmapper.service;

import javax.inject.Inject;

import com.github.marceloleite2604.tutorials.modelmapper.util.ControllerUtil;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.MessageLoader;

public abstract class AbstractService {
	
	@Inject
	protected ControllerUtil controllerUtil;
	
	@Inject
	protected MessageLoader messageLoader;

}
