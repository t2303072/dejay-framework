package com.dejay.framework.common.config;

import com.dejay.framework.common.utils.CommonUtil;
import com.dejay.framework.common.utils.CookieFactory;
import com.dejay.framework.common.utils.SessionFactory;
import com.dejay.framework.common.utils.TokenFactory;
import com.dejay.framework.domain.common.BaseEntity;
import com.dejay.framework.domain.common.EntityLog;
import com.dejay.framework.mapper.common.EntityLogMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EntityLogAspect extends CommonUtil {

    private final EntityLogMapper entityLogMapper;
    private final ObjectMapper objectMapper;

    public EntityLogAspect(TokenFactory tokenFactory, SessionFactory sessionFactory, CookieFactory cookieFactory, EntityLogMapper entityLogMapper, ObjectMapper objectMapper) {
        super(tokenFactory, sessionFactory, cookieFactory);
        this.entityLogMapper = entityLogMapper;
        this.objectMapper = objectMapper;
    }

//    @Around("execution(* com.dejay.framework..*.*Service.*(..))")
//    public Object elapsedTimeLog(ProceedingJoinPoint pjp) throws Throwable {
//        long start = System.currentTimeMillis();
//        System.out.println("Start: " + start);
//        Object proceed = pjp.proceed();
//        System.out.println("End: " + (System.currentTimeMillis() - start));
//
//        return proceed;
//    }

    @Pointcut("execution(public void com.dejay.framework.service.member.MemberService.*(com.dejay.framework.domain.user.User))")
    public void saveEntityLog() {}

    @AfterReturning(value = "@annotation(com.dejay.framework.common.annotation.EntityLog)", returning = "retVal")
    public void saveEntityLog(JoinPoint joinPoint, Object retVal) throws Throwable {
        System.out.println("###################### @@AFTER RETURNING@@  ######################## ");

        Object[] args = joinPoint.getArgs();
        String parameterName = args[0].getClass().getSimpleName();
//        String methodName = joinPoint.getSignature().getName();
        String tablePrimaryKey = entityLogMapper.getTablePrimaryKey(((BaseEntity) args[0]).getTableName());

        /** TODO: IJ
         * 1. 파라미터 세팅
         * 2. entity_log 저장
         * */
        if(retVal.getClass().equals(Integer.class) && ((Integer) retVal).intValue() > 0) {
            BaseEntity castObj = (BaseEntity) args[0];
            BaseEntity target = new BaseEntity(castObj.getTableName()
                                             , castObj.getLogId1()
                                             , castObj.getLogId2()
                                             , castObj.getLogType()
                                             , objectMapper.writeValueAsString(castObj)
                                             , castObj.getRemark()
                                             , castObj.getRegId());

            int insert = entityLogMapper.saveEntityLogData(target);
        }

        System.out.println("==============================");
    }
}
