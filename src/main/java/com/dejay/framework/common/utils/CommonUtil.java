package com.dejay.framework.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommonUtil {

    private final TokenFactory tokenFactory;
    private final SessionFactory sessionFactory;
    private final CookieFactory cookieFactory;

    public TokenFactory tokenFactory() {
        return this.tokenFactory;
    }

    public SessionFactory sessionFactory() {
        return this.sessionFactory;
    }

    public CookieFactory cookieFactory() {
        return this.cookieFactory;
    }
}
