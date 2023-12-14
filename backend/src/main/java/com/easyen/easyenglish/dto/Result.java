package com.easyen.easyenglish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    //统一返回格式为:
    // code  响应码
    // msg   响应信息 描述字符串
    // data  返回的数据
    private static final int FAILRE_CODE = 504;
    private static final int SUCCESS_CODE = 200;
    private static final int OTHER_CODE = 250;
    private int code;
    private String msg;
    private Object resultData;
    //查询 成功响应
    public static Result success(Object data){
        return new Result(SUCCESS_CODE,"success",data);
    }
    //增删改 成功响应
    public static Result successCode(){
        return new Result(SUCCESS_CODE,"success",null);
    }
    public static Result failure(String msg){
        return new Result(FAILRE_CODE,msg,null);
    }

//    public Result(int code,String msg, Object resultData) {
//        this.code = code;
//        this.msg = msg;
//        this.resultData = resultData;
//    }

}
