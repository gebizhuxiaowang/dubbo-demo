package com.example.dubbo.excepiton;

import lombok.Data;

/**
 * @version 1.0
 * @classname BzException
 * @description todo
 */
@Data
public class BzException extends RuntimeException {
    private Integer code;
    private String message;
    private Integer isShowToast;
    private String toastMsg;

    public BzException() {
        this.code = null;
        this.message = null;
        this.isShowToast = 0;
        this.toastMsg = null;
    }

    public BzException(Integer code, String message) {
        this(code, message, 0, (String)null);
    }

    public BzException(Integer code, String massage, String toastMsg) {
        this.code = code;
        this.message = massage;
        this.isShowToast = 1;
        this.toastMsg = toastMsg;
    }


    public BzException(Integer code, String massage, Integer isShowToast, String toastMsg) {
        this.code = code;
        this.message = massage;
        this.isShowToast = isShowToast;
        this.toastMsg = toastMsg;
    }

}
