package com.dejay.framework.mapper.common;

import com.dejay.framework.vo.search.SearchVO;

import java.util.List;

/**
 * Mapper 일반화 인터페이스
 */
public interface GeneralMapper {


    /**
     * 단건 조회
     * @param id
     * @return
     */
    Object rowByID(Object id);


    /**
     * 단건 조회
     * @param search
     * @return
     */
    Object rowByKey(SearchVO search);

    /**
     * 목록 조회
     * @param search
     * @return
     */
    List<?> listBySearch(SearchVO search);

    /**
     * 페이지 목록 조회
     * @param search
     * @return
     */
    List<?> pagingBySearch(SearchVO search);

    /**
     * 페이지 목록 조회  건수
     * @param search
     * @return
     */
    int pagingCountBySearch(SearchVO search);

    /**
     * 저장
     * @param obj
     * @return
     */
    int insert(Object obj);

    /**
     * 수정
     * @param obj
     * @return
     */
    int update(Object obj);

    /**
     * merge
     * @param obj
     * @return
     */
    int merge(Object obj);

    /**
     * 삭제
     * @param key
     * @return
     */
    int delete(Object key);
}
