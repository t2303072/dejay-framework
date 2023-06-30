package com.dejay.framework.domain.common;

import com.dejay.framework.common.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "유저명은 필수값 입니다.")
    private String username;
    @NotNull(message = "password is required field")
    private String password;
    private String email;

    private Role role;
}
