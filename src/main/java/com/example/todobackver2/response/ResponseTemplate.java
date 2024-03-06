package com.example.todobackver2.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate implements Serializable {

    private String msg;
    private Object data;
    private String err;

    public static ResponseTemplate responseSuccess(Object data){
        ResponseTemplate responseTemplate=new ResponseTemplate();
        responseTemplate.setData(data);
        responseTemplate.setMsg("success");
        return responseTemplate;
    }
    public static ResponseTemplate responseError(String err){
        ResponseTemplate responseTemplate=new ResponseTemplate();
        responseTemplate.setErr(err);
        responseTemplate.setMsg("error");
        return responseTemplate;
    }
    public static ResponseTemplate responseError(){
        ResponseTemplate responseTemplate=new ResponseTemplate();
        responseTemplate.setMsg("error");
        return responseTemplate;
    }
}
