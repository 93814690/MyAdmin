package top.liyf.admin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class User extends BaseEntity {

    private Long uid;

    private String username;

    private String password;

    private String email;

    private String phone;

    private LocalDateTime lastPasswordResetTime;

}