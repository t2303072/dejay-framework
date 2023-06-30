package com.dejay.framework.service.token;

import com.dejay.framework.domain.token.Token;
import com.dejay.framework.mapper.token.TokenMapper;
import com.dejay.framework.vo.common.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenMapper tokenMapper;

    public void saveToken(Token token) {
        tokenMapper.saveToken(token);
    }
}
