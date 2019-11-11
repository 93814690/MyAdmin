package top.liyf.admin.util;

import org.springframework.util.DigestUtils;

/**
 * @author liyf
 * Created in 2019-11-11
 */
public class EncryptUtils {

    /**
     * 功能描述: 密码加密
     *
     * @param password
     * @return java.lang.String
     * @author liyf
     */
    public static String encryptPassword(String password){
        String salt = "!@#";
        return  DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }
}
