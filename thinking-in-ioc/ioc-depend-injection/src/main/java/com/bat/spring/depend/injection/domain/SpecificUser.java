package com.bat.spring.depend.injection.domain;

/**
 * 特殊的用户对象
 *
 * @author ZhengYu
 * @version 1.0 2020/1/8 3:05
 **/
public class SpecificUser extends User {

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "CollectionUser{" +
                "nickName='" + nickName + '\'' +
                "} " + super.toString();
    }
}
