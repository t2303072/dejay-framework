package com.dejay.framework.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * TODO: IJ 인터셉터를 통한 table 관련 logging은 추후에 설계가 명확하게 잡히면 진행
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyBatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String method = invocation.getMethod().getName();
        Object[] args = invocation.getArgs();
        Object param = args[1];
        MappedStatement mappedStatement = ((MappedStatement) args[0]);
        String mapperId = mappedStatement.getId();
        String sql = mappedStatement.getBoundSql(param).getSql();

        log.info("method: {}", method);
        log.info("param: {}", param);
        log.info("mapperId: {}", mapperId);
        log.info("sql: {}", sql);

        log.info(args.toString());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
