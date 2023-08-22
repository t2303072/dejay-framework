package com.dejay.framework.domain.user;

import com.dejay.framework.common.enums.AuthorityEnum;
import com.dejay.framework.domain.common.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Set;

@ToString(callSuper = false)
@Getter
@EqualsAndHashCode(of = {"id", "name"})
public class User extends BaseEntity {

    private long seq;
    @NotNull(message = "아이디는 필수 값 입니다.")
    private String id;
    @NotNull(message = "비밀번호는 필수 값 입니다.")
    private String password;
    @NotNull(message = "이름은 필수 값 입니다.")
    private String name;
    private String email;
    private String picture;
    private Set<AuthorityEnum> authority;
    private String deptCode;


    @Builder
    public User(String id, String password, String name, String email, String picture, Set<AuthorityEnum> authority, String deptCode, String logId1, String logId2, String remark, String regId, Timestamp regDttm) {
        super.setLogId1(logId1);
        super.setLogId2(logId2);
        super.setRemark(remark);
        super.setRegId(regId);
        super.setRegDttm(regDttm);
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.authority = authority;
        this.deptCode = deptCode;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getAuthorityKey() {
        return String.valueOf(this.authority);
    }
}
