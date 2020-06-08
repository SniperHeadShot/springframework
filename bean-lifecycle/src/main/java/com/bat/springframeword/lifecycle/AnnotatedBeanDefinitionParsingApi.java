package com.bat.springframeword.lifecycle;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.lifecycle.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解 BeanDefinition 解析示例
 *
 * @author ZhengYu
 * @version 1.0 2020/6/6 13:38
 **/
public class AnnotatedBeanDefinitionParsingApi {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int BeanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionParsingApi.class);

        // 这里读取不到@Bean的定义, 是因为AnnotatedBeanDefinitionReader#register()是将参数当作配置类来读取, 并没有解析其中的注解
        System.out.println(String.format("使用 注解 方式读取到的 Bean 数量为: [%d]", beanFactory.getBeanDefinitionCount() - BeanDefinitionCountBefore));

        Map<String, AnnotatedBeanDefinitionParsingApi> beansOfType = beanFactory.getBeansOfType(AnnotatedBeanDefinitionParsingApi.class);
        System.out.println(JSONObject.toJSONString(beanFactory));
    }

    @Bean
    public User annotationUser() {
        return new User("annotationUser", 26);
    }
}

