package com.bat.springframeword.bean.initialization;

import com.bat.springframeword.bean.factory.DefaultUserFactory;
import com.bat.springframeword.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化 示例
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 23:39
 **/
@Configuration
public class BeanInitialization {
    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Class(配置类)
        applicationContext.register(BeanInitialization.class);

        // 启动上下文
        applicationContext.refresh();
        System.out.println("Spring 上下文已启动...");

        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);

        // 显式关闭
        System.out.println("Spring 上下文准备关闭...");
        applicationContext.close();
        System.out.println("Spring 上下文已关闭...");
    }

    // 延迟初始化与非延迟初始化的区别就是：延迟初始化在Spring上下文启动之后, 非延迟初始化在Spring上下文之前
    @Lazy
    @Bean(initMethod = "initUserFactory", destroyMethod = "destroyUserFactory")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
