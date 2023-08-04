package com.dejay.framework.service.authority;

import com.dejay.framework.mapper.authority.AuthorityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorityService {

    private final AuthorityMapper authorityMapper;
}
