package top.liyf.admin.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    BaseEntity() {
        this.status = "1";
        this.createTime = LocalDateTime.now();
    }

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
