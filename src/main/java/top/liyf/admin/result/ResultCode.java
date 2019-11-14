package top.liyf.admin.result;

/**
 * @author liyf
 * Created in 2019-11-11
 */
public enum ResultCode {

    /** 请求成功 */
    SUCCESS(0, "成功"),

    USER_NOT_FOUND(100, "用户不存在"),

    WRONG_PASSWORD(101, "用户名或密码错误"),

    DEACTIVATED_ACCOUNT(102,"账号已停用"),

    /** 未知的错误 */
    UNKNOWN_ERROR(999, "未知异常，请联系管理员");

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
