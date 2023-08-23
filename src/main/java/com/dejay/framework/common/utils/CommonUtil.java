package com.dejay.framework.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommonUtil {

    private final TokenFactory tokenFactory;
    private final SessionFactory sessionFactory;
    private final CookieFactory cookieFactory;

    public TokenFactory getTokenFactory() {
        return this.tokenFactory;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public CookieFactory getCookieFactory() {
        return this.cookieFactory;
    }
}
