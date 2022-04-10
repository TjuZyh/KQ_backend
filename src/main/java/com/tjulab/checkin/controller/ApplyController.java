package com.tjulab.checkin.controller;

import com.tjulab.checkin.service.ApplyForVacationService;
import com.tjulab.checkin.vo.QueryApplyInfoResp;
import com.tjulab.checkin.vo.QueryApplyRecordResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ApplyController {

    @Autowired
    private ApplyForVacationService applyForVacationService;

    @ApiOperation("员工申请假期")
    @PostMapping("/applyVacation/{empId}/{startTime}/{duringTime}/{reason}/{type}")
    public boolean applyVacation(@PathVariable("empId") long empId,@PathVariable("startTime") Date startTime,@PathVariable("duringTime") Integer duringTime,@PathVariable("reason") String reason, @PathVariable("type") Integer type){
        return applyForVacationService.applyForVacation(empId ,startTime, duringTime , reason , type );
    }

    @ApiOperation("领导审批假，更新审批表状态")
    @PostMapping("/checkApplyInfo/{applyId}/{state}")
    public boolean checkApplyInfo(@PathVariable("applyId") long applyId ,@PathVariable("state") Integer state){
        return applyForVacationService.updateApplyState(applyId , state);
    }

    @ApiOperation("查询本人请假记录")
    @GetMapping("/getApplyRecordById/{empId}")
    public List<QueryApplyRecordResp> getApplyRecordById(@PathVariable("empId") long empId){
        return applyForVacationService.queryApplyByEmpId(empId);
    }

    @ApiOperation("展示给审批人员的审批表信息")
    @GetMapping("/getApplyInfo")
    public List<QueryApplyInfoResp> getApplyInfo(){
        return applyForVacationService.queryApplyByState();
    }
}
