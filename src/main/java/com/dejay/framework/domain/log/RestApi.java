package com.dejay.framework.domain.log;

import lombok.*;

import java.sql.Timestamp;

@ToString(callSuper = false)
@AllArgsConstructor
@Builder
@Getter
public class RestApi {

    private long logSeq;
    private String requestUri;
    private String httpMethod;
    private String resultJson;
    @Setter
    private int status;
    private String regId;
    private Timestamp regDttm;
}
