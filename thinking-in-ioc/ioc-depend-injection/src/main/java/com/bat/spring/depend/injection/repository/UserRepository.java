package com.bat.spring.depend.injection.repository;

import com.bat.spring.depend.injection.domain.User;
import org.springframework.beans.factory.ObjectFactory;

import java.util.List;

/**
 * 用户信息仓库
 *
 * @author ZhengYu
 * @version 1.0 2020/1/8 3:53
 **/
public class UserRepository {

    // 依赖注入
    private List<User> users;

    // 实现延迟加载
    private ObjectFactory<User> objectFactory;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public ObjectFactory<User> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<User> objectFactory) {
        this.objectFactory = objectFactory;
    }
}
