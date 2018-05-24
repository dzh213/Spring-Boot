package com.example.demo.domain;

import io.swagger.annotations.ApiModelProperty;

public class UserRequest {
    @ApiModelProperty(value = "学生姓名",required = true)
    private String name;
    @ApiModelProperty(value = "学生年龄",required = true)
    private int age;
    @ApiModelProperty(value = "所属班级",required = true)
    private int classId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
