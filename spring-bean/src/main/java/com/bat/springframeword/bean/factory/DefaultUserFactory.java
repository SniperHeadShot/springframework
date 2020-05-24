package com.bat.springframeword.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * User工厂实现
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 16:40
 **/
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct UserFactory 初始化...");
    }

    @Override
    public void initUserFactory() {
        System.out.println("@Bean自定义 UserFactory 初始化...");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean#afterPropertiesSet() UserFactory 初始化...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy UserFactory 销毁中...");
    }

    @Override
    public void destroyUserFactory() {
        System.out.println("@Bean自定义 UserFactory 销毁中...");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean#destroy() UserFactory 销毁中...");
    }
}
