package com.dejay.framework.service.record;

import com.dejay.framework.common.utils.DateUtil;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.common.SelectOptionVO;
import com.dejay.framework.vo.record.RecordVO;
import com.dejay.framework.vo.search.record.RecordSearchVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl extends ParentService implements RecordService {

    @Override
    public List<SelectOptionVO> getSearchDateRangeOptionList() {
        var list = new ArrayList<SelectOptionVO>();
        list.add(new SelectOptionVO("ALL", "전체"));
        list.add(new SelectOptionVO("REG_DT", "접속 일시"));

        return list;
    }

    @Override
    public List<SelectOptionVO> getSearchKeywordTypeList() {
        var list = new ArrayList<SelectOptionVO>();
        list.add(new SelectOptionVO("ALL", "전체"));
        list.add(new SelectOptionVO("USER", "회원 ID"));
        list.add(new SelectOptionVO("MENU", "접속 메뉴"));

        return list;
    }

    @Override
    public int totalCount(RecordSearchVO recordSearchVO) {
        if(recordSearchVO == null) {

        }

        return getCommonMapper().getRecordMapper().findAllTotalCount(recordSearchVO);
    }

    @Override
    public List<RecordVO> findAll(RecordSearchVO recordSearchVO) {
        int totalCount = this.totalCount(recordSearchVO);
        if(totalCount < 1) {
            return new ArrayList<>();
        }

        List<RecordVO> list = getCommonMapper().getRecordMapper().findAll(recordSearchVO);
        list.forEach(ele -> ele.setRegDtStr(DateUtil.convertLocalDateTimeToString(ele.getRegDt(), DateUtil.DATETIME_YMDHM_PATTERN)));

        return list;
    }

    @Override
    public RecordVO findById(long logSeq) {
        return getCommonMapper().getRecordMapper().findById(logSeq);
    }
}
