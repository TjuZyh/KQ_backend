package com.tjulab.checkin.controller;

import com.tjulab.checkin.service.ApplyForVacationService;
import com.tjulab.checkin.util.R;
import com.tjulab.checkin.util.ResultCode;
import com.tjulab.checkin.vo.QueryApplyInfoResp;
import com.tjulab.checkin.vo.QueryApplyRecordResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public R applyVacation(@PathVariable("empId") long empId, @PathVariable("startTime")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startTime, @PathVariable("duringTime") Integer duringTime, @PathVariable("reason") String reason, @PathVariable("type") Integer type){
        int resType = applyForVacationService.applyForVacation(empId ,startTime, duringTime , reason , type );
        if(resType == 1){
            return R.error(ResultCode.REPEAT_APPLY);
        }else if(resType == 2){
            return R.error(ResultCode.LEFT_VACATION_TIME);
        }else{
            return R.ok();
        }
    }

    @ApiOperation("领导审批假，更新审批表状态")
    @PostMapping("/checkApplyInfo/{applyId}/{state}")
    public R checkApplyInfo(@PathVariable("applyId") long applyId ,@PathVariable("state") Integer state){
        int resType = applyForVacationService.updateApplyState(applyId , state);
        if(resType == 1){
            return R.error();
        }else if(resType == 2){
            return R.error(ResultCode.APPLY_TYPE_FAIL);
        }else{
            return R.ok();
        }
    }

    @ApiOperation("查询本人请假记录")
    @GetMapping("/getApplyRecordById/{empId}")
    public R<List<QueryApplyRecordResp>> getApplyRecordById(@PathVariable("empId") long empId){
        return R.ok(applyForVacationService.queryApplyByEmpId(empId));
    }

    @ApiOperation("展示给部门经理的审批表信息，只展示小于3天的请假信息")
    @GetMapping("/getApplyInfoForEvent")
    public R<List<QueryApplyInfoResp>> queryApplyByStateForEventManager(){
        return R.ok(applyForVacationService.queryApplyByStateForEventManager());
    }

    @ApiOperation("展示给总经理以及副总经理的审批表信息，只展示大于等于3天的请假信息")
    @GetMapping("/getApplyInfoForBig")
    public R<List<QueryApplyInfoResp>> queryApplyByStateForBigManager(){
        return R.ok(applyForVacationService.queryApplyByStateForBigManager());
    }

    @ApiOperation("根据Id查询员工当前申请的记录")
    @GetMapping("/queryApplyingByEmpId/{empId}")
    public R<List<QueryApplyInfoResp>> queryApplyingByEmpId(@PathVariable("empId") long empId){
        return R.ok(applyForVacationService.queryApplyingByEmpId(empId));
    }
}
