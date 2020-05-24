package com.bat.springframeword.bean.Instantiation;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.bean.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 常规实例化的四种方式
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 16:21
 **/
public class BeanInstantiationApi {
    public static void main(String[] args) {
        // 创建上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean-instantiation-context.xml");

        // 方式一：静态方法实例化Bean
        User beanByStaticMethod = applicationContext.getBean("user-by-static-method", User.class);
        System.out.println(String.format("通过静态工厂方法实例化: [%s]", JSONObject.toJSONString(beanByStaticMethod)));

        // 方式二: 实例方法实例化Bean
        User beanByInstanceMethod = applicationContext.getBean("user-by-static-method", User.class);
        System.out.println(String.format("通过实例方法实例化: [%s]", JSONObject.toJSONString(beanByInstanceMethod)));

        // 方式三：factoryBean实例化Bean
        User beanByFactoryBean = applicationContext.getBean("user-by-factory-bean", User.class);
        System.out.println(String.format("通过factoryBean实例化: [%s]", JSONObject.toJSONString(beanByFactoryBean)));
    }
}
