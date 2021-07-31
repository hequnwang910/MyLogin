package com.message.server.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//生成有参构造方法
@NoArgsConstructor//生成无参构造方法
public class MyException extends RuntimeException{


    private Integer code;//状态码

    private String msg;//异常信息
}
