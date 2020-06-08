package com.bat.springframeword.lifecycle.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 *
 * @author ZhengYu
 * @version 1.0 2020/6/6 12:42
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
