package com.bat.spring.ioc.event.event_04_multicaster;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于接口实现{@link ApplicationEventMulticaster}
 * <p>
 * 1. 对于{@link AbstractApplicationContext#earlyApplicationListeners} 属性的解释：
 * 因为在{@link AbstractApplicationContext#refresh()}方法中，步骤onRefresh()是在步骤initApplicationEventMulticaster();之后的，
 * 所以在既实现了{@link ApplicationListener}又实现了{@link BeanPostProcessor}的Bean会在flush之前初始化，所以这时候需要将事件暂存
 * 起来，等上下文启动结束后再进行调用，参考{@link AbstractApplicationContext#registerListeners()}方法中对earlyEventsToProcess的处理}
 *
 * @author ZhengYu
 * @version 1.0 2020/9/7 23:57
 **/
@EnableAsync
public class MulticasterInterfaceAPi {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        applicationContext.addApplicationListener(event -> System.out.println(String.format("线程[%s]监听到事件: [%s]", Thread.currentThread().getName(), event.toString())));
        applicationContext.addApplicationListener(new CustomEventListener());

        // 刷新方法会执行 步骤initApplicationEventMulticaster()，初始化了ApplicationEventMulticaster
        applicationContext.refresh();

        ApplicationEventMulticaster applicationEventMulticaster = applicationContext.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);

        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;

            ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("custom-spring-event-thread"));

            // 自定义异步事件执行线程池
            simpleApplicationEventMulticaster.setTaskExecutor(executorService);

            // 自定义事件异常后的操作
            simpleApplicationEventMulticaster.setErrorHandler(t -> System.out.println(String.format("事件执行异常, 原因: [%s]", t.getMessage())));

            // 优雅关闭线程池
            applicationContext.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> {
                if (!executorService.isShutdown()) {
                    executorService.shutdown();
                }
            });
        }

        applicationContext.publishEvent(new CustomEvent(applicationContext));

        applicationContext.close();
    }

    // 自定义事件
    private static class CustomEvent extends ApplicationEvent {

        public CustomEvent(Object source) {
            super(source);
        }
    }

    // 自定义事件监听类
    private static class CustomEventListener implements ApplicationListener<CustomEvent> {

        @Override
        public void onApplicationEvent(CustomEvent event) {
            System.out.println(String.format("接收到自定义事件 [%s], 可能会处理异常!", event.toString()));
            if (new Random().nextBoolean()) {
                throw new RuntimeException("事件处理异常");
            }
        }
    }
}
