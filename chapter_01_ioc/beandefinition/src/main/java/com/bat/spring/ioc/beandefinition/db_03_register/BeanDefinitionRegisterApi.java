package com.bat.spring.ioc.beandefinition.db_03_register;

import com.bat.spring.common.entity.CustomEntity;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanDefinition 注册
 *
 * @author ZhengYu
 * @version 1.0 2020/8/18 23:52
 **/
public class BeanDefinitionRegisterApi {
    public static void main(String[] args) {
        // 基于 xml 文件注册
        registerBeanDefinitionBaseXmlConfig();

        // 基于 annotation 实现
        registerBeanDefinitionBaseAnnotation();

        // 基于 api 实现
        registerBeanDefinitionBaseApi();
    }

    /**
     * 基于 xml 文件注册 Bean
     *
     * @author ZhengYu
     */
    private static void registerBeanDefinitionBaseXmlConfig() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        applicationContext.refresh();
        displayBeanInfo(applicationContext, "xml");
        applicationContext.close();
    }

    /**
     * 基于 annotation 注册 Bean
     *
     * @author ZhengYu
     */
    private static void registerBeanDefinitionBaseAnnotation() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanDefinitionRegisterApi.class);
        applicationContext.refresh();
        displayBeanInfo(applicationContext, "annotation");
        applicationContext.close();
    }

    @Bean
    public CustomEntity customEntity() {
        CustomEntity customEntity = new CustomEntity();
        customEntity.setId(2L);
        customEntity.setName("zy2");
        customEntity.setAge(21);
        return customEntity;
    }

    /**
     * 基于 api 注册 BeanDefinition
     *
     * @author ZhengYu
     */
    private static void registerBeanDefinitionBaseApi() {
        DefaultListableBeanFactory applicationContext = new DefaultListableBeanFactory();
        registerBeanDefinition(applicationContext);
        displayBeanInfo(applicationContext, "api");
    }

    private static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        // 方式1
        // registry.registerBeanDefinition(beanName, buildBeanDefinition());

        // 方式2
        BeanDefinitionReaderUtils.registerWithGeneratedName((AbstractBeanDefinition) buildBeanDefinition(), registry);
    }

    private static void displayBeanInfo(BeanFactory beanFactory, String byWay) {
        CustomEntity customEntity = beanFactory.getBean(CustomEntity.class);
        System.out.println(String.format("基于 [%s] 方式获取到的 Bean 为: [%s]", byWay, customEntity.toString()));
    }

    /**
     * 基于 {@link BeanDefinitionBuilder} 构建 BeanDefinition
     *
     * @author ZhengYu
     */
    private static BeanDefinition buildBeanDefinition() {
        return BeanDefinitionBuilder.genericBeanDefinition(CustomEntity.class)
                .addPropertyValue("id", 3L)
                .addPropertyValue("name", "zy3")
                .addPropertyValue("age", 22)
                .getBeanDefinition();
    }
}
