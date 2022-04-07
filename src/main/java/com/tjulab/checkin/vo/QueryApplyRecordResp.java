package com.tjulab.checkin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class QueryApplyRecordResp {

    private long applyId;

    private Date startTime;

    private Integer duringTime;

    private String reason;

    private Integer type;

    private Integer state;

    private String account;

    private String name;

}
