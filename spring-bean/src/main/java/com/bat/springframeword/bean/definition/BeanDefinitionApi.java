package com.bat.springframeword.bean.definition;

import com.bat.springframeword.bean.entity.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * 构建 {@link org.springframework.beans.factory.config.BeanDefinition} 示例
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 8:12
 **/
public class BeanDefinitionApi {

    /**
     * BeanDefinition构建 方式一
     * 通过{@link org.springframework.beans.factory.support.BeanDefinitionBuilder}构建
     *
     * @param
     * @return org.springframework.beans.factory.config.BeanDefinition
     * @author ZhengYu
     */
    public static BeanDefinition buildBeanDefinitionMethodOne() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性
        beanDefinitionBuilder.addPropertyValue("name", "zs").addPropertyValue("age", 25);
        // 获取BeanDefinition实例
        return beanDefinitionBuilder.getBeanDefinition();
    }

    /**
     * BeanDefinition构建 方式二
     * 通过{@link org.springframework.beans.factory.support.AbstractBeanDefinition}以及派生类构建
     *
     * @param
     * @return org.springframework.beans.factory.config.BeanDefinition
     * @author ZhengYu
     */
    public static BeanDefinition buildBeanDefinitionMethodTwo() {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过MutablePropertyValues批量操作属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("name", "zs").add("age", 25);
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
        return genericBeanDefinition.getOriginatingBeanDefinition();
    }
}
