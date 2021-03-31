package zzn.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Msg {
/*
    @JsonInclude(JsonInclude.Include.NON_NULL)
*/
    private String  Code;
    private String  Msg;
    private Object  data;
    private String  token;

    @Override
    public String toString() {
        return "Msg{" +
                "Code='" + Code + '\'' +
                ", Msg='" + Msg + '\'' +
                ", data=" + data +
                ", token='" + token + '\'' +
                '}';
    }

    public Msg successMsg(Object object){
        Msg msg=new Msg();
        msg.setData(object);
        msg.setCode("0000");
        msg.setMsg("success");
        return msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
