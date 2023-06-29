package com.dejay.framework.vo.search;

import com.dejay.framework.common.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  공용 검색 모델 <br>
 *  검색 조건이 더 생길 경우 상속 클래스를 별도로 만들 것
 */
public class SearchVO {

    /**
     * <p>
     * 공용 날짜 표시 형식
     * </p>
     */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 공용 시각 표시 형식
     */
    public static final SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat("HH:mm");

    /**
     * 공용 날짜 시각 표시 형식
     */
    public static final SimpleDateFormat SIMPLE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final int MILLIS_IN_A_DAY = 24 * 60 * 60 * 1000;

    /**
     * 검색어
     */
    private String searchWord = "";

    private String type1 = "";

    private String type2 = "";

    private String type3 = "";

    private String type4 = "";

    private String type5 = "";

    /**
     * 해당 메뉴의 ID값
     */
    private String groupKey = "";

    /**
     * 정렬
     */
    private String orderBy = "";

    /**
     * 오름차순/내림차순
     */
    private String descAsc = "DESC";

    /**
     * 날짜 검색 시작일 YYYY-MM-DD
     */
    private String startDt = "";

    /**
     * 날짜 검색 종료일 YYYY-MM-DD
     */
    private String endDt = "";

    /**
     * 시간 검색 시작시간 HHmm
     */
    private String startTm = "";

    /**
     * 시간 검색 종료시간 HHmm
     */
    private String endTm = "";

    /**
     * [Date] 날짜 검색 시작일
     */
    private Date startDttm = null;

    /**
     * [Date] 날짜 검색 종료일
     */
    private Date endDttm = null;

    /**
     * [Date] 검색일
     */
    private Date searchDttm = null;

    /**
     * 검색일 YYYY-MM-DD
     */
    private String searchDt = "";

    /**
     * 요일 검색  D : 일(1) ~ 토(7)
     */
    private String dy = "";

    private String redirectURL = "";

    /**
     * 검색어 - 기본값 = ""
     *
     * @return
     */
    public String getSearchWord() {
        if (this.searchWord == null)
            this.searchWord = "";

        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = (searchWord == null || searchWord.length() == 0) ? ""
                : searchWord.trim();
    }

    /**
     * alias of searchWord
     *
     * @return
     */
    public String getKeyword() {
        return this.getSearchWord();
    }

    /**
     * alias of searchWord
     *
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.setSearchWord(keyword);
    }


    /**
     * order by 문자열 - 기본값 = ""
     * 주의 : searchVO 혼용으로 인해, 원하지 않는 정렬 결과가 나올 수 있음.
     * @return
     */
    public String getOrderBy() {
        if (this.orderBy == null)
            this.orderBy = "";
        return orderBy;
    }

    /**
     * order by 문자열
     * 주의 : searchVO 혼용으로 인해, 원하지 않는 정렬 결과가 나올 수 있음.
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /** 오름차순/내림차순  */
    public String getDescAsc() {
        return descAsc;
    }

    /** 오름차순/내림차순  */
    public void setDescAsc(String descAsc) {
        this.descAsc = descAsc;
    }

    /**
     * [읽기전용] orderBy 문자열을 배열로 변환 - Mybatis iterator용으로 사용 문자열이 없을 경우 null 반환
     * @return
     */
    public String[] getOrderBys() {
        if (StringUtil.isEmpty(this.orderBy))
            return null;

        String[] arr = this.orderBy.split(",");
        return arr;
    }

    /**
     * 해당 메뉴의 ID값
     * @return
     */
    public String getGroupKey() {
        return groupKey;
    }

    /**
     * 해당 메뉴의 ID값
     * @param groupKey
     */
    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    /**
     * 검색 날짜 시작일 yyyy-mm-dd - 기본값 = ""
     *
     * @return
     */
    public String getStartDt() {
        if (this.startDt == null)
            this.startDt = "";
        return startDt;
    }

    /**
     * 검색 날짜 시작일 yyyy-mm-dd
     *
     * @return
     */
    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    /**
     * 검색 날짜 종료일 yyyy-mm-dd
     *
     * @return
     */
    public String getEndDt() {
        if (this.endDt == null)
            this.endDt = "";
        return endDt;
    }

    /**
     * 검색 날짜 종료일 yyyy-mm-dd
     *
     * @return
     */
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    /**
     * 시간 검색 시작시간 HHmm
     * @return
     */
    public String getStartTm() {
        return startTm;
    }

    /**
     * 시간 검색 시작시간 HHmm
     * @param startTm
     */
    public void setStartTm(String startTm) {
        this.startTm = startTm;
    }

    /**
     * 시간 검색 종료시간 HHmm
     * @return
     */
    public String getEndTm() {
        return endTm;
    }

    /**
     * 시간 검색 종료시간 HHmm
     * @param endTm
     */
    public void setEndTm(String endTm) {
        this.endTm = endTm;
    }

    /**
     * [Date] 날짜 검색 시작일
     * @return
     */
    public Date getStartDttm() {
        return startDttm;
    }
    /**
     * [Date] 날짜 검색 시작일
     * @param startDttm
     */
    public void setStartDttm(Date startDttm) {
        this.startDttm = startDttm;
    }

    /**
     * [Date] 날짜 검색 종료일
     * @return
     */
    public Date getEndDttm() {
        return endDttm;
    }
    /**
     * [Date] 날짜 검색 종료일
     * @param endDttm
     */
    public void setEndDttm(Date endDttm) {
        this.endDttm = endDttm;
    }

    /**
     *
     * @return
     */
    public String getSearchDt() {
        if (this.searchDt == null)
            this.searchDt = "";
        return searchDt;
    }

    public void setSearchDt(String searchDt) {
        this.searchDt = searchDt;
    }

    /**
     * D : 일(1) ~ 토(7)
     * @return
     */
    public String getDy() {
        return dy;
    }

    /**
     * D : 일(1) ~ 토(7)
     * @return
     */
    public void setDy(String dy) {
        this.dy = dy;
    }

    /**
     * 검색조건1 - 기본값 = ""
     *
     * @return
     */
    public String getType1() {
        if (this.type1 == null)
            this.type1 = "";
        return type1;
    }

    /**
     * 검색조건1
     *
     * @return
     */
    public void setType1(String type1) {
        this.type1 = type1;
    }

    /**
     * 검색조건2 - 기본값 = ""
     *
     * @return
     */
    public String getType2() {
        if (this.type2 == null)
            this.type2 = "";
        return type2;
    }

    /**
     * 검색조건2
     *
     * @return
     */
    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getType4() {
        return type4;
    }

    public void setType4(String type4) {
        this.type4 = type4;
    }

    public String getType5() {
        return type5;
    }

    public void setType5(String type5) {
        this.type5 = type5;
    }

    /**
     * 로그인시 리다이렉트URL 파라미터
     * @return
     */
    public String getRedirectURL() {
        return redirectURL;
    }

    /**
     * 로그인시 리다이렉트URL 파라미터
     * @param redirectURL
     */
    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    /**
     * 날짜 표시 형식
     * @return
     */
    public String getDateFormat() {
        return SIMPLE_DATE_FORMAT.toPattern();
    }

    /**
     * 시각 표시 형식
     * @return
     */
    public String getTimeFormat() {
        return SIMPLE_TIME_FORMAT.toPattern();
    }

    /**
     * 날짜 시각 표시 형식
     * @return
     */
    public String getDateTimeFormat() {
        return SIMPLE_DATETIME_FORMAT.toPattern();
    }

}
