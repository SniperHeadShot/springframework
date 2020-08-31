package com.bat.spring.ioc.beandefinition.bd_01_build;

import com.bat.spring.common.entity.CustomEntity;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * BeanDefinition 两种构建方式
 *
 * @author ZhengYu
 * @version 1.0 2020/8/18 23:01
 **/
public class BeanDefinitionBuildApi {
    public static void main(String[] args) {

        // 方式1
        BeanDefinition beanDefinition1 = buildMethodOne();

        // 方式2
        BeanDefinition beanDefinition2 = buildMethodTwo();
    }

    /**
     * 基于 {@link BeanDefinitionBuilder} 构建 BeanDefinition
     *
     * @author ZhengYu
     */
    private static BeanDefinition buildMethodOne() {
        return BeanDefinitionBuilder.genericBeanDefinition(CustomEntity.class)
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "x")
                .addPropertyValue("age", 25)
                .getBeanDefinition();
    }

    /**
     * 基于 {@link AbstractBeanDefinition} 以及派生类构建 BeanDefinition
     *
     * @author ZhengYu
     */
    private static BeanDefinition buildMethodTwo() {
        GenericBeanDefinition result = new GenericBeanDefinition();
        result.setBeanClass(CustomEntity.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        mutablePropertyValues.addPropertyValue("id", 1L);
        mutablePropertyValues
                .add("id", 1L)
                .add("name", "x")
                .add("age", 25);
        result.setPropertyValues(mutablePropertyValues);
        return result;
    }
}
