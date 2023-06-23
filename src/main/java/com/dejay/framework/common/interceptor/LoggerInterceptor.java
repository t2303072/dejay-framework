package com.dejay.framework.common.interceptor;

import com.dejay.framework.domain.log.RestApi;
import com.dejay.framework.mapper.log.RestApiMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private final RestApiMapper restApiMapper;
    private RestApi restApi;
    private String reqParam;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(!StringUtils.hasText(reqParam)) {
//            reqParam = request.getReader().lines().collect(Collectors.joining());
//        }

        if(restApi != null && restApi.getLogSeq() > 0 && request.getRequestURI().equals("/error")) {
            restApi.setStatus(response.getStatus());
            restApiMapper.updateApiAccessLog(restApi);
            restApi = null;
        }else {
            restApi = RestApi.builder()
                    .requestUri(request.getRequestURI())
                    .httpMethod(request.getMethod())
//                    .resultJson(reqParam)
                    .status(response.getStatus())
                    .regId("ijzone")
                    .build();
            log.info(restApi.toString());

            restApiMapper.insertApiAccessLog(restApi);
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Callee ==> {}", handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("reqParam => {}", reqParam);
        if(restApi != null && (request.getRequestURI().equals("/error") || restApi.getStatus() != response.getStatus())) {
            restApi.setStatus(response.getStatus());
            log.info(restApi.toString());
            restApiMapper.updateApiAccessLog(restApi);
        }
//        if(StringUtils.hasText(reqParam)) reqParam = null;
    }
}
