package com.bat.springframeword.lifecycle.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 超级用户类
 *
 * @author ZhengYu
 * @version 1.0 2020/6/6 16:07
 **/
@Data
@NoArgsConstructor
public class SuperUser extends User {

    private String sex;

    public SuperUser(String name, Integer age, String sex) {
        super(name, age);
        this.sex = sex;
    }
}
