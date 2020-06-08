package com.bat.springframeword.ioccontainer;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.ioccontainer.entity.User;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Set;

/**
 * 依赖处理过程分析
 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String, Set, TypeConverter)}
 *
 * @author ZhengYu
 * @version 1.0 2020/5/30 9:20
 **/
public class IocContainerProcess {

    @Autowired
    private User user1;

    @Autowired
    private User user2;

    @Autowired
    private Collection<User> users;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(IocContainerProcess.class);

        applicationContext.refresh();

        ObjectProvider<IocContainerProcess> beanProvider = applicationContext.getBeanProvider(IocContainerProcess.class);
        IocContainerProcess iocContainerProcess = beanProvider.getObject();
        System.out.println(JSONObject.toJSONString(iocContainerProcess.getUsers()));


    }

    @Bean
    public User user1() {
        return new User("user1", 1);
    }

    @Bean
    public User user2() {
        return new User("user2", 2);
    }

    public Collection<User> getUsers() {
        return users;
    }
}
