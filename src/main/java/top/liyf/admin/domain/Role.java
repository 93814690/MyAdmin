package top.liyf.admin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Role extends BaseEntity {
    /**
     * rid
     */
    private Integer rid;

    /**
     * name
     */
    private String name;

    /**
     * permission
     */
    private String permission;
}