package com.bat.springframeword.lifecycle;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.lifecycle.entity.SuperUser;
import com.bat.springframeword.lifecycle.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化 生命周期实现{@link }
 *
 * @author ZhengYu
 * @version 1.0 2020/6/10 22:56
 **/
public class BeanInstantiationLifecycleApi {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 添加 BeanPostProcessor 实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        // 读取外部配置文件
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = new ClassPathResource("META-INF/bean-definition-merge.xml");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        // 加载 BeanDefinition
        int beanDefinitions = xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println(String.format("已加载 BeanDefinition [%d] 个", beanDefinitions));


        User xmlUser = beanFactory.getBean("xmlUser", User.class);
        System.out.println(JSONObject.toJSONString(xmlUser));

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(JSONObject.toJSONString(superUser));
    }


    /**
     * Bean 实例化前后可以对实例化的 Bean 进行拦截
     * {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInstantiation(Class, String)}
     *
     * @author ZhengYu
     * @version 1.0 2020/6/10 22:56
     **/
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        /**
         * 提前生成代理对象替换掉默认 Spring Ioc 里面的内容
         *
         * @author ZhengYu
         * @version 1.0 2020/6/10 22:56
         **/
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            // 拦截 SuperUser 并使用自定义实例化信息
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }
            return null;
        }

        /**
         * 实例化完成之后对属性的控制, 判断属性应不应该赋值
         *
         * @param bean     实例
         * @param beanName bean名称
         * @return boolean true 表示使用配置的元信息实例化Bean, false 表示不适用配置的元信息
         * @author ZhengYu
         */
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("xmlUser", beanName) && User.class.equals(bean.getClass())) {
                return false;
            }
            return true;
        }
    }
}