package com.tjulab.checkin.controller;

import com.tjulab.checkin.entity.Employer;
import com.tjulab.checkin.service.EmployerService;
import com.tjulab.checkin.util.R;
import com.tjulab.checkin.util.ResultCode;
import com.tjulab.checkin.vo.QueryEmpInfoResp;
import com.tjulab.checkin.vo.QueryEmpStateResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @ApiOperation("查询全部员工信息")
    @GetMapping("/queryAllEmployeeInfo")
    public R<List<QueryEmpInfoResp>> queryAllEmployeeInfo(){
        return R.ok(employerService.queryAllEmployeeInfo());
    }

    @ApiOperation("根据id查询员工信息")
    @GetMapping("/queryEmpInfoById/{empId}")
    public R<Employer> queryEmpInfoById(@PathVariable("empId") long empId) {
        return R.ok(employerService.queryEmpInfoById(empId));
    }

    @ApiOperation("查询全部经理信息")
    @GetMapping("/queryAllEmployerInfo")
    public R<List<QueryEmpInfoResp>> queryAllEmployerInfo() {
        return R.ok(employerService.queryAllEmployerInfo());
    }

    @ApiOperation("员工登录")
    @GetMapping("login/{account}/{password}")
    public R<Employer> login(@PathVariable("account") String account,@PathVariable("password") String password) {
        Map<Employer, Integer> resData = employerService.login(account, password);
        Integer type = -1;
        Employer employer = new Employer();
        for(Employer emp : resData.keySet()){
            type = resData.get(emp);
            employer = emp;
        }
        if(type == 1){
            return R.error(ResultCode.USER_NOT_FOUND);
        }else if(type == 2){
            return R.ok(employer);
        }else{
            return R.error(ResultCode.PASSWORD_FAIL);
        }
    }

    @ApiOperation("新增员工信息")
    @PostMapping("/insertEmpInfo")
    public R insertEmpInfo(Employer employer) {
        if(employerService.insertEmpInfo(employer)){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation("查询公司全部人员的状态")
    @GetMapping("/queryEmpState")
    public R<List<QueryEmpStateResp>> queryEmpState(){
        return R.ok(employerService.queryEmpState());
    }

    @ApiOperation("查询当前员工的状态")
    @GetMapping("queryStateById/{empId}")
    public R<Integer> queryStateById(@PathVariable("empId") long empId){
        return R.ok(employerService.queryStateById(empId));
    }
    
}
