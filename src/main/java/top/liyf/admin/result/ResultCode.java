package top.liyf.admin.result;

/**
 * @author liyf
 * Created in 2019-11-11
 */
public enum ResultCode {

    /** 请求成功 */
    SUCCESS(0, "成功"),



    /** 未知的错误 */
    UNKNOWN_ERROR(500, "未知错误");

    ResultCode(int value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public int val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private int val;
    private String msg;
}
