package com.example.demo.controller;

import com.example.demo.dao.ClasssMapper;
import com.example.demo.dao.StudentMapper;
import com.example.demo.domain.BaseResult;
import com.example.demo.domain.UserRequest;
import com.example.demo.model.Classs;
import com.example.demo.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("学生管理")
@RestController
public class UserController {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ClasssMapper classsMapper;

    @ApiOperation(value = "根据id查看学生信息")
    @RequestMapping(value = "getUserById",method = RequestMethod.GET)
    public BaseResult<UserRequest> getUserById(@ApiParam(value = "学生id",required = true) @RequestParam(value = "userId",required = true) int userId) {
        Student student = studentMapper.selectByPrimaryKey(userId);
        BaseResult baseResult = new BaseResult();
        if (student == null) {
            baseResult.setCode(1);
            baseResult.setMessage("学生不存在");
            return baseResult;
        }
        UserRequest userRequest = new UserRequest();
        userRequest.setName(student.getName());
        userRequest.setAge(student.getAge());
        userRequest.setClassId(student.getClassId());
        baseResult.setCode(1);
        baseResult.setMessage("查询成功");
        baseResult.setData(userRequest);
        return baseResult;
    }

    @ApiOperation(value = "添加学生")
    @RequestMapping(value = "addStudent",method = RequestMethod.POST)
    public BaseResult addStudent(@RequestBody UserRequest userRequest) {
        BaseResult baseResult = new BaseResult();
        String result = checkParam(userRequest);
        baseResult.setCode(1);
        baseResult.setMessage(result);
        if (result.isEmpty()) {
            try {
                Student student = new Student();
                student.setName(userRequest.getName());
                student.setAge(userRequest.getAge());
                student.setClassId(userRequest.getClassId());
                student.setCreateDate(new Date());
                int success = studentMapper.insert(student);
                System.out.println("success:"+success+","+"id:"+student.getId());
                baseResult.setMessage("添加成功");
            }catch (Exception e) {
                baseResult.setCode(0);
                baseResult.setMessage("存在异常");
            }
        }
        return baseResult;
    }

    private String checkParam(UserRequest userRequest) {
        if (userRequest.getName() == null || "".equals(userRequest.getName())) {
            return "姓名不能为空";
        }
        if (userRequest.getAge() == 0) {
            return "年龄不能为0";
        }

        Classs classs = classsMapper.selectByPrimaryKey(userRequest.getClassId());
        if (classs == null) {
            return "班级不存在";
        }
        return "";
    }
}
