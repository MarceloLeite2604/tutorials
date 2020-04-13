package io.github.marceloleite2604.configuration;

import io.github.marceloleite2604.exception.ApplicationContextRuntimeException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationApplicationContextAware implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        synchronized (ApplicationApplicationContextAware.class) {
            ApplicationApplicationContextAware.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T retrieveBean(Class<T> beanClass) {
        return retrieveBean(beanClass, null);
    }

    @SuppressWarnings({ "unchecked" })
    public static <T> T retrieveBean(Class<T> beanClass, String beanName) {
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
            throw new ApplicationContextRuntimeException(
                    "System context is not available yet.");
        }
    }

    private static Object retrieveBeanFromApplicationContext(String beanName) {
        try {
            return applicationContext.getBean(beanName);

        } catch (BeansException exception) {
            throw new ApplicationContextRuntimeException(
                    "Could not locate bean \"" + beanName + "\".", exception);
        }
    }

    private static Object retrieveBeanFromApplicationContext(Class<?> beanClass) {
        try {
            return applicationContext.getBean(beanClass);

        } catch (BeansException exception) {
            throw new ApplicationContextRuntimeException(
                    "Could not locate a bean of type \"" + beanClass.getName() + "\".", exception);
        }
    }

    private static void checkBeanType(Object bean, Class<?> clazz) {
        if (!clazz.isInstance(bean)) {
            throw new ApplicationContextRuntimeException(
                    "Bean \"" + bean + "\" is not of type \"" + clazz.getCanonicalName() + "\".");
        }

    }
}
