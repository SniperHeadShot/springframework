package com.bat.springframeword.lifecycle;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.lifecycle.entity.User;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * Bean 元信息三种配置方式
 * 1. 面向配置
 * -    1.1 xml配置文件
 * -    1.2 properties配置文件
 * 2. 面向注解
 * 3. 面向API
 *
 * @author ZhengYu
 * @version 1.0 2020/6/6 12:42
 **/
public class BeanMetadataConfigurationApi {
    public static void main(String[] args) {
        // 创建 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        beanFactory.setParentBeanFactory(applicationContext);

        // 使用 xml 方式配置元信息
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int xmlCount = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/bean-definition-metadata.xml");
        System.out.println(String.format("使用 xml 方式读取到的 Bean 数量为: [%d]", xmlCount));

        // 使用 properties 方式配置元信息
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int propCount = propertiesBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/bean-definition-metadata.properties");
        System.out.println(String.format("使用 properties 方式读取到的 Bean 数量为: [%d]", propCount));

        // 使用 注解方式
        applicationContext.register(BeanMetadataConfigurationApi.class);

        // 使用 API 方式
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(User.class)
                .addPropertyValue("name", "apiUser")
                .addPropertyValue("age", 26)
                .getBeanDefinition();
        applicationContext.registerBeanDefinition("apiUser", beanDefinition);

        // 启动上下文
        applicationContext.refresh();

        Map<String, User> userMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, User.class);
        System.out.println(JSONObject.toJSONString(userMap));

        // 关闭资源
        applicationContext.close();
    }

    @Bean
    public User annotationUser() {
        return new User("annotationUser", 26);
    }
}
