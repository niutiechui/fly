package cn.kgc.commons;


/**
 * DTO 统一返回对象
 */

public class ServerResponse {
    private Boolean success;
    private String msg;
    private Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private ServerResponse(Boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public static ServerResponse creatResultByData(String msg,Object data,Boolean success){
        return new ServerResponse(success,msg,data);
    }

    public static ServerResponse creatResultByMsg(String msg,Boolean success){
        return new ServerResponse(success,msg);
    }
}
