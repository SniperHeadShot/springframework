package com.bat.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link ComponentScan} 示例
 * {@link org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass(ConfigurationClass, ConfigurationClassParser.SourceClass)}
 *
 * @author ZhengYu
 * @version 1.0 2020/9/8 22:21
 **/
@ComponentScan(basePackages = "com.bat.spring.annotation")
public class ComponentScanApi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ComponentScanApi.class);

        applicationContext.refresh();

        Test bean = applicationContext.getBean(Test.class);
        System.out.println(bean);

        applicationContext.close();
    }
}
