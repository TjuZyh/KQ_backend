package com.tjulab.checkin.controller;

import com.tjulab.checkin.service.ApplyForVacationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ApplyController {

    @Autowired
    private ApplyForVacationService applyForVacationService;

    @ApiOperation("员工申请假期")
    @PostMapping("/applyVacation")
    public boolean applyVacation(long empId, Date startTime, Integer duringTime, String reason, Integer type){
        return applyForVacationService.applyForVacation(empId ,startTime, duringTime , reason , type );
    }

    @ApiOperation("领导审批假，更新审批表状态")
    @PostMapping("/checkApplyInfo")
    public boolean checkApplyInfo(long applyId , Integer state){
        return applyForVacationService.updateApplyState(applyId , state);
    }
}
