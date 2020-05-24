package com.bat.springframeword.bean.entity;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class User {

    private String name;

    private Integer age;

    public static User createUser() {
        return new User("赵六", 26);
    }
}
