package com.bat.spring.ioc.event.event_02_spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring 的事件监听
 *
 * @author ZhengYu
 * @version 1.0 2020/9/7 20:21
 **/
@EnableAsync
public class ApplicationEventListenerApi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationEventListenerApi.class);
        // 方式1: 基于接口实现
        applicationContext.addApplicationListener(event -> System.out.println(String.format("[%s]接口实现 - 接收到 Spring 事件 [%s]", Thread.currentThread().getName(), event)));

        applicationContext.refresh();

        applicationContext.close();
    }

    // 方式2: 基于注解(异步)
    @Async
    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(String.format("[%s]注解实现 - 接收到 Spring 事件 [%s]", Thread.currentThread().getName(), event));
    }

    // 方式3： 基于 Bean 注解/xml
    @Bean
    public ApplicationListener applicationListener() {
        return new CustomApplicationEventListener();
    }

    private static class CustomApplicationEventListener implements ApplicationListener {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            System.out.println(String.format("[%s]Spring Bean实现 - 接收到 Spring 事件 [%s]", Thread.currentThread().getName(), event));
        }
    }
}
