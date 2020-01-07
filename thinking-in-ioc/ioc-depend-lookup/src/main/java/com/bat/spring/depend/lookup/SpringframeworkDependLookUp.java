package com.bat.spring.depend.lookup;

import com.alibaba.fastjson.JSONObject;
import com.bat.spring.depend.lookup.annotation.CustomAnnotation;
import com.bat.spring.depend.lookup.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 *
 * @author ZhengYu
 * @version 1.0 2020/1/8 1:57
 **/
public class SpringframeworkDependLookUp {
    public static void main(String[] args) {
        // 配置 XML 配置文件并启动 Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 实时查找Bean
        realTimeLookupBean(beanFactory);

        // 延迟加载Bean
        lazyLookupBean(beanFactory);

        // 根据类型查找Bean列表
        lookupCollectionBeansByType(beanFactory);

        // 根据注解查找Bean集合
        lookupCollectionBeansByAnnotation(beanFactory);
    }

    /**
     * 实时查找Bean
     *
     * @param beanFactory beanFactory
     * @author ZhengYu
     */
    private static void realTimeLookupBean(BeanFactory beanFactory) {
        // 通过名称查找Bean
        User userFindByName = (User) beanFactory.getBean("user");

        // 通过类型查找Bean(在注入了多个User对象的情况下会抛出NoUniqueBeanDefinitionException异常),必须知道primary对象
        User userFindByType = beanFactory.getBean(User.class);

        // 通过名称查找Bean
        User userFindByNameAndType = beanFactory.getBean("user", User.class);
        System.out.println("实时查找 ==> " + JSONObject.toJSONString(userFindByNameAndType));
    }

    /**
     * 延迟加载Bean
     *
     * @param beanFactory beanFactory
     * @author ZhengYu
     */
    @SuppressWarnings("unchecked")
    private static void lazyLookupBean(BeanFactory beanFactory) {
        // 通过名称查找Bean
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟加载 ==> " + JSONObject.toJSONString(user));
    }

    /**
     * 根据类型查找Bean列表
     *
     * @param beanFactory beanFactory
     * @author ZhengYu
     */
    private static void lookupCollectionBeansByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);

            System.out.println("根据类型获取对象集合 ==> " + JSONObject.toJSONString(beansOfType));
        }
    }

    /**
     * 根据注解查找Bean集合
     *
     * @param beanFactory beanFactory
     * @author ZhengYu
     */
    private static void lookupCollectionBeansByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beansOfAnnotation = listableBeanFactory.getBeansWithAnnotation(CustomAnnotation.class);
            System.out.println("根据注解查找Bean集合 ==> " + JSONObject.toJSONString(beansOfAnnotation));
        }
    }
}
