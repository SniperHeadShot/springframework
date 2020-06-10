package com.bat.springframeword.lifecycle;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.lifecycle.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 临时测试
 * {@link org.springframework.beans.factory.config.ConfigurableBeanFactory#getMergedBeanDefinition(String)}
 *
 * @author ZhengYu
 * @version 1.0 2020/6/6 14:19
 **/
public class CommonTestApi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(CommonTestApi.class);

        applicationContext.refresh();

        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        System.out.println(JSONObject.toJSONString(beansOfType));

        applicationContext.close();
    }

    @Bean
    public User user1() {
        return new User("user1", 1);
    }

    @Bean
    public User user2() {
        return new User("user2", 2);
    }
}
