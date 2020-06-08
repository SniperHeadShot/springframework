package com.bat.springframeword.ioccontainer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 *
 * @author ZhengYu
 * @version 1.0 2020/5/24 8:17
 **/
@Data
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
