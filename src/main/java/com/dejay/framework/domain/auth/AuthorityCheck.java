package com.dejay.framework.domain.auth;

import com.dejay.framework.common.enums.AuthorityEnum;
import lombok.*;

import java.util.Set;

@ToString(callSuper = false)
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class AuthorityCheck {
    private String id;
    private Set<AuthorityEnum> authList;
    private String menuId;
    private String menuPath;

    @Builder
    public AuthorityCheck(String id, Set<AuthorityEnum> authList, String menuId, String menuPath) {
        this.id = id;
        this.authList = authList;
        this.menuId = menuId;
        this.menuPath = menuPath;
    }
}
