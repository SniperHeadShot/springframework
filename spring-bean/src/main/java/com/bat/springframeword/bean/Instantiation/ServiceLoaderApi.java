package com.bat.springframeword.bean.Instantiation;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.bean.factory.DefaultUserFactory;
import com.bat.springframeword.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 1. 使用 {@link java.util.ServiceLoader} 创建Bean实例
 * 2. 使用 {@link org.springframework.beans.factory.config.AutowireCapableBeanFactory} 创建Bean实例
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 22:52
 **/
public class ServiceLoaderApi {
    public static void main(String[] args) {
        // ServiceLoader创建Bean实例的两种方式, 这两种方式都依赖 META-INF/services/com.bat.springframeword.bean.factory.UserFactory 配置文件
        // 1. 原生方式 使用 {@link java.util.ServiceLoader} 创建Bean实例
        naticeServiceLoader();
        // 2. 配置文件方式
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:special-bean-instantiation-context.xml");
        ServiceLoader<UserFactory> userFactoryServiceLoader = applicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(userFactoryServiceLoader);

        // AutowireCapableBeanFactory创建BeanFactory
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        DefaultUserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(JSONObject.toJSONString(userFactory.createUser()));
    }

    /**
     * 原生使用ServiceLoader
     *
     * @author ZhengYu
     */
    public static void naticeServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(JSONObject.toJSONString(userFactory.createUser()));
        }
    }
}
