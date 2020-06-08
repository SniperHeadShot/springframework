package com.bat.springframeword.lifecycle;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.lifecycle.entity.SuperUser;
import com.bat.springframeword.lifecycle.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition 合并
 *
 * @author ZhengYu
 * @version 1.0 2020/6/6 16:11
 **/
public class BeanDefinitionMergeApi {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

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
}
