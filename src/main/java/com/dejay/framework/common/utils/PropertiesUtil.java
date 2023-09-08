package com.dejay.framework.common.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

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

    /** File [[ **/
    private File file;
    @Getter
    @Setter
    public static class File{
        private String rootDir;
        private String tempDir;
        private String realDir;
    }
    /** File ]] **/

}
