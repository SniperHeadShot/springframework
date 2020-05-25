package com.bat.springframeword.lookup;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 1. 回顾 {@link org.springframework.beans.factory.BeanFactory#getBeanProvider(Class)}
 * 2. 回顾 {@link org.springframework.beans.factory.ListableBeanFactory#getBeanNamesForType(Class)}
 * {@link org.springframework.beans.factory.ListableBeanFactory#getBeansOfType(Class)}
 * 3. 通过注解类型查找
 * {@link org.springframework.beans.factory.ListableBeanFactory#getBeanNamesForAnnotation(Class)}
 * {@link org.springframework.beans.factory.ListableBeanFactory#getBeansWithAnnotation(Class)}}
 * {@link org.springframework.beans.factory.ListableBeanFactory#findAnnotationOnBean(String, Class)}}
 *  {@link org.springframework.beans.factory.BeanFactoryUtils#beanNamesForTypeIncludingAncestors(ListableBeanFactory, Class)} (String)}
 * @author ZhengYu
 * @version 1.0 2020/5/26 6:14
 **/
public class ObjectProviderApi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderApi.class);

        applicationContext.refresh();

        // 回顾 {@link org.springframework.beans.factory.BeanFactory#getBeanProvider(Class)} API使用
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());

        applicationContext.close();
    }

    @Bean
    public String helloWorld() {
        return "Hello World";
    }


}
