package com.github.marceloleite2604.tutorials.modelmapper.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import com.github.marceloleite2604.tutorials.modelmapper.configuration.BeanNames;
import com.github.marceloleite2604.tutorials.modelmapper.exception.BeanRetrievalRuntimeException;
import com.github.marceloleite2604.tutorials.modelmapper.exception.InvalidBeanTypeRuntimeException;
import com.github.marceloleite2604.tutorials.modelmapper.exception.ModelMapperApplicationContextRuntimeException;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.MessageLoader;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.MessageUtil;

@Component
public class ModelMapperApplicationContext implements ApplicationContextAware {

	private static final ModelMapperApplicationContextRuntimeException SYSTEM_CONTEXT_NOT_AVAILABLE_RUNTIME_EXCEPTION = new ModelMapperApplicationContextRuntimeException(
			"System context is not available yet.");

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		synchronized (ModelMapperApplicationContext.class) {
			ModelMapperApplicationContext.applicationContext = applicationContext;
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static MessageLoader getMessageLoader() {
		return retrieveBean(MessageLoader.class, BeanNames.MESSAGE_LOADER);
	}

	public static TemplateEngine getThymeleafTemplateEngine() {
		return retrieveBean(TemplateEngine.class);
	}

	public static MessageUtil getMessageUtil() {
		return retrieveBean(MessageUtil.class);
	}

	public static ObjectUtil getObjectUtil() {
		return retrieveBean(ObjectUtil.class);
	}

	private static <T> T retrieveBean(Class<T> beanClass) {
		return retrieveBean(beanClass, null);
	}

	@SuppressWarnings({ "unchecked" })
	private static <T> T retrieveBean(Class<T> beanClass, String beanName) {
		checkApplicationContextIsReady();

		Object bean;
		if (beanName != null) {
			bean = retrieveBeanFromApplicationContext(beanName);
		} else {
			bean = retrieveBeanFromApplicationContext(beanClass);
		}

		checkBeanType(bean, beanClass);

		return (T) bean;
	}

	private static void checkApplicationContextIsReady() {
		if (applicationContext == null) {
			throw SYSTEM_CONTEXT_NOT_AVAILABLE_RUNTIME_EXCEPTION;
		}
	}

	private static Object retrieveBeanFromApplicationContext(String beanName) {
		try {
			return applicationContext.getBean(beanName);

		} catch (BeansException exception) {
			throw new BeanRetrievalRuntimeException(beanName, exception);
		}
	}

	private static Object retrieveBeanFromApplicationContext(Class<?> beanClass) {
		try {
			return applicationContext.getBean(beanClass);

		} catch (BeansException exception) {
			throw new BeanRetrievalRuntimeException(beanClass, exception);
		}
	}

	private static void checkBeanType(Object bean, Class<?> clazz) {
		if (!clazz.isInstance(bean)) {
			throw new InvalidBeanTypeRuntimeException(bean, clazz);
		}
	}	
}
