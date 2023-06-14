package com.dejay.framework.domain;

import com.dejay.framework.vo.ResultVO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = false)
@Getter
@EqualsAndHashCode(of = {"id", "userId"})
public class Member {

    @Min(message = "id must be larger than 10", value = 10) private Long id;
    @NotNull(message = "userId is required") String userId;
    private String name;
    private String email;
    private List<String> list = new ArrayList<>();
    private ResultVO resultVO;
    @Builder
    public Member(Long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.resultVO = new ResultVO();
    }
}
