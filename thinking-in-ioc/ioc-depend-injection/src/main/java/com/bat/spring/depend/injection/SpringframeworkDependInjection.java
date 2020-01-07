package com.bat.spring.depend.injection;

import com.alibaba.fastjson.JSONObject;
import com.bat.spring.depend.injection.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入
 *
 * @author ZhengYu
 * @version 1.0 2020/1/8 1:57
 **/
public class SpringframeworkDependInjection {
    public static void main(String[] args) {
        // 配置 XML 配置文件并启动 Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:spring.xml");

        realTimeLookupBean(beanFactory);
    }

    /**
     * 实时查找Bean
     *
     * @param beanFactory beanFactory
     * @author ZhengYu
     */
    private static void realTimeLookupBean(BeanFactory beanFactory) {
        // 通过名称查找Bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");

        System.out.println("实时查找 ==> " + JSONObject.toJSONString(userRepository.getUsers()));

        System.out.println("实时查找 ==> " + JSONObject.toJSONString(userRepository.getObjectFactory().getObject()));
    }


}
