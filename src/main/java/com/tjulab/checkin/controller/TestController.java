package com.tjulab.checkin.controller;

import com.tjulab.checkin.entity.User;
import com.tjulab.checkin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("test")
    @ResponseBody
    public String test(){
        return "hello";
    }

    @GetMapping("getAll")
    @ResponseBody
    public List<User> selectAll(){
        return userMapper.selectList(null);
    }
}
