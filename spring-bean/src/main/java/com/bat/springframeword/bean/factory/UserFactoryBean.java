package com.bat.springframeword.bean.factory;

import com.bat.springframeword.bean.entity.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * User Bean 的 {@link org.springframework.beans.factory.FactoryBean} 实现
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 22:42
 **/
public class UserFactoryBean implements FactoryBean {

    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
