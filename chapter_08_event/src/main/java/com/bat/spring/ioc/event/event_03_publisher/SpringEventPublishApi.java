package com.bat.spring.ioc.event.event_03_publisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring 的事件发布 {@link org.springframework.context.ApplicationEventPublisher#publishEvent(ApplicationEvent)}
 *
 * @author ZhengYu
 * @version 1.0 2020/9/7 20:21
 **/
@EnableAsync
public class SpringEventPublishApi implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringEventPublishApi.class);
        // 添加事件监听
        applicationContext.addApplicationListener(event -> System.out.println(String.format("[%s]接口实现 - 接收到 Spring 事件 [%s]", Thread.currentThread().getName(), event)));

        applicationContext.refresh();

        applicationContext.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello World") {
        });
    }
}
