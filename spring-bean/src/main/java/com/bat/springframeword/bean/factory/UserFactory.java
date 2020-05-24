package com.bat.springframeword.bean.factory;

import com.bat.springframeword.bean.entity.User;

/**
 * {@link com.bat.springframeword.bean.entity.User} 工厂
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 16:39
 **/
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }

    void initUserFactory();

    void destroyUserFactory();
}
