package club.banyuan.mall.integration.common;

public enum  ResponseCode {
    SUCCESS(200,"操作成功"),
    FORBIDDEN(401,"用户身份验证失败"),
    UNAUTHORIZED(403,"用户未授权"),
    BADREQUEST(400,"请求错误"),
    SERVERERROR(500,"服务器错误");

    private final int code;
    private final String message;

    ResponseCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
