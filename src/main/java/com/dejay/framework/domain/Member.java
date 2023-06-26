package com.dejay.framework.domain;

import com.dejay.framework.domain.common.Paging;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = false)
@Getter
@EqualsAndHashCode(of = {"memberId", "memberName"})
public class Member {

    @Min(message = "아이디는 0보다 큰 수여야 합니다.", value = 0)
    private Long memberSeq;
    @NotNull(message = "멤버 아이디는 필수값 입니다.")
    private String memberId;
    private String memberName;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private List<String> list = new ArrayList<>();

    @Builder
    public Member(Long memberSeq, String memberId, String memberName, String email) {
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
    }
}
