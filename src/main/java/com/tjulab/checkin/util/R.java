package com.tjulab.checkin.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


//同一返回结果类
@Data
public class R<T> {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 无参构造器 - 请求成功，无返回data
     */
    private R() {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.success = true;
    }

    /**
     * 有参构造器 - 请求成功，返回data
     * @param data data
     */
    private R(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.success = true;
        this.data = data;
    }

    /**
     * 有参构造器 - 请求失败
     * @param resultCode resultCode
     */
    private R(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.success = false;
        this.message = resultCode.getMessage();
    }


    public static<T> R<T> ok(){
        return new R<>();
    }

    public static<T> R<T> ok(T data){
        return new R<>(data);
    }

    public static<T> R<T> error(ResultCode resultCode) {
        return new R<>(resultCode);
    }

    public static<T> R<T> error() {
        return new R<>(ResultCode.FAIL);
    }


}
