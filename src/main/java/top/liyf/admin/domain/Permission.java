package top.liyf.admin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Permission extends BaseEntity {
    private Integer pid;

    private String name;

    private String url;

    private String permission;

    private String type;
}