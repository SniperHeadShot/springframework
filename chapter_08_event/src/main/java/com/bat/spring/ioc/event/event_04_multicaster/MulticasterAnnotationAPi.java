package com.bat.spring.ioc.event.event_04_multicaster;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于注解实现{@link ApplicationEventMulticaster}
 *
 * @author ZhengYu
 * @version 1.0 2020/9/7 23:57
 **/
@EnableAsync
public class MulticasterAnnotationAPi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MulticasterAnnotationAPi.class);

        applicationContext.refresh();

        applicationContext.close();
    }

    @Async
    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(String.format("线程[%s]监听到事件: [%s]", Thread.currentThread().getName(), event.toString()));
    }

    // 信息: No task executor bean found for async processing: no bean of type TaskExecutor and no bean named 'taskExecutor' either
    @Bean
    public ExecutorService taskExecutor() {
        return Executors.newSingleThreadExecutor(new CustomizableThreadFactory("custom-spring-event-thread"));
    }
}
