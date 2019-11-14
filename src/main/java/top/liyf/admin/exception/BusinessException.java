package top.liyf.admin.exception;

import lombok.Getter;
import lombok.Setter;
import top.liyf.admin.result.ResultCode;

import java.io.Serializable;

/**
 * @author liyf
 * Created in 2019-11-13
 */
@Getter
@Setter
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    public BusinessException(String msg) {
        super(msg);
        this.code = ResultCode.UNKNOWN_ERROR.val();
        this.msg = msg;
    }

    public BusinessException(ResultCode code) {
        super(code.msg());
        this.code = code.val();
        this.msg = code.msg();
    }

}
