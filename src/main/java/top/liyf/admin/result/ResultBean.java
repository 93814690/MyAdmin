package top.liyf.admin.result;

import lombok.Data;
import top.liyf.admin.exception.BusinessException;

import java.io.Serializable;

/**
 * @author liyf
 * Created in 2019-11-13
 */
@Data
public class ResultBean<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public ResultBean() {
        this(ResultCode.SUCCESS);
    }

    public ResultBean(T data) {
        this();
        this.data = data;
    }

    public ResultBean(ResultCode code) {
        this.code = code.val();
        this.msg = code.msg();
    }

    public ResultBean(BusinessException e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
    }
}
