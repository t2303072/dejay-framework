package com.dejay.framework.common.utils;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "properties")
public class PropertiesUtil {

    /** jasypt [[ **/
    private Jasypt jasypt;
    @Getter
    @Setter
    public static class Jasypt {
        private Encryptor encryptor;
    }
    @Getter
    @Setter
    public static class Encryptor {
        private String bean;
        private String password;
    }
    /** jasypt ]] **/

}
