package club.banyuan.mall.integration.common;

import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class ResponseResult {
    private int code;
    private String message;
    private Object data;

    public ResponseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(ResponseCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public ResponseResult(ResponseCode code, Object data) {
        this(code);
        this.data = data;
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
                data);
    }

    public static ResponseResult forbidden() {
        return new ResponseResult(ResponseCode.FORBIDDEN);
    }

    public static ResponseResult forbidden(Object data) {
        return new ResponseResult(ResponseCode.FORBIDDEN, data);
    }

    public static ResponseResult unauthorized() {
        return new ResponseResult(ResponseCode.FORBIDDEN);
    }

    public static ResponseResult unauthorized(Object data) {
        return new ResponseResult(ResponseCode.FORBIDDEN, data);
    }

    public static ResponseResult badRequest() {
        return new ResponseResult(ResponseCode.BADREQUEST);
    }

    public static ResponseResult badRequest(Object data) {
        return new ResponseResult(ResponseCode.BADREQUEST, data);
    }

    public static ResponseResult serverError() {
        return new ResponseResult(ResponseCode.SERVERERROR);
    }

    public static ResponseResult serverError(Object data) {
        return new ResponseResult(ResponseCode.SERVERERROR,data);
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}


