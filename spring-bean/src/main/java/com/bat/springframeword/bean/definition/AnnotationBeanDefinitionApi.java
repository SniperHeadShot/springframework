package com.bat.springframeword.bean.definition;

import com.alibaba.fastjson.JSONObject;
import com.bat.springframeword.bean.entity.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 1. 注册Bean的三种方式
 * 2. {@link org.springframework.beans.factory.support.BeanDefinitionRegistry} API注册Bean
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 9:23
 **/
// 第三种: 通过@Import方式来进行导入
@Import(AnnotationBeanDefinitionApi.Config.class)
public class AnnotationBeanDefinitionApi {

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Bean
        applicationContext.register(AnnotationBeanDefinitionApi.class);

        // 通过BeanDefinition注册 API实现
        registerUserBeanDefinition(applicationContext, "api-user");
        registerUserBeanDefinition(applicationContext, null);

        // 启动上下文
        applicationContext.refresh();
        // 验证 - 依赖查找
        dependencyLookup(applicationContext);
        // 显式关闭
        applicationContext.close();
    }

    /**
     * 使用API方式注册Bean[底层]
     *
     * @param registry 注册
     * @param beanName Bean名称(为Null自动生成)
     * @return void
     * @author ZhengYu
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        // 使用BeanDefinitionBuilder构建BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("name", "李四")
                .addPropertyValue("age", 25);

        if (StringUtils.hasText(beanName)) {
            // 命名方式注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名方式注册BeanDefinition
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    // 第二种: 通过@Component方式, 定义当前类作为Spring Bean(组件)
    @Component
    public class Config {

        // 第一种: 通过@Bean方式
        @Bean(name = {"annotation-user"})
        public User user() {
            return new User("张三", 22);
        }
    }

    public static void dependencyLookup(ApplicationContext applicationContext) {
        Map<String, Config> configBeans = applicationContext.getBeansOfType(Config.class);
        System.out.println(String.format("Config 类型的所有Beans [%s]", JSONObject.toJSONString(configBeans)));

        Map<String, User> userBeans = applicationContext.getBeansOfType(User.class);
        System.out.println(String.format("User 类型的所有Beans [%s]", JSONObject.toJSONString(userBeans)));
    }
}
