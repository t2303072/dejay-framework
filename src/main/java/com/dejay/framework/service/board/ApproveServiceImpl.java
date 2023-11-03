package com.dejay.framework.service.board;

import com.dejay.framework.common.utils.DateUtil;
import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.board.BoardPublic;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.service.file.FilePublicServiceImpl;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.board.BoardReplyVO;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.common.SelectOptionVO;
import com.dejay.framework.vo.file.FilePublicVO;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.SearchVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Primary
@Component
public class ApproveServiceImpl extends ParentService implements ApproveService {

    /**
     * 게시판 다 건 조회
     * @param boardSearchVO
     * @return list {@link List}
     */
    @Override
    public List<BoardPublicVO> findAll(BoardSearchVO boardSearchVO) {
        int totalCount = this.totalCount(boardSearchVO);
        if(totalCount < 1) {
            return new ArrayList<>();
        }

        List<BoardPublicVO> list = getCommonMapper().getApproveMapper().findAll(boardSearchVO);
        list.forEach(ele -> {
            ele.setRegDtStr(DateUtil.convertLocalDateTimeToString(ele.getRegDt(), DateUtil.DATETIME_YMDHM_PATTERN));
            ele.setLastDtStr(DateUtil.convertLocalDateTimeToString(ele.getLastDt(), DateUtil.DATETIME_YMDHM_PATTERN));
            if(!ele.getApproveState().isBlank()) {
                ele.setApproveStateInKorean();
                if(ele.getApproveState().equalsIgnoreCase("HDLE0404")) {
                    ele.setCompleteDtStr(ele.getLastDtStr());
                }else {
                    ele.setCompleteDtStr("-");
                }
            }
        });

        return list;
    }

    @Override
    public List<SelectOptionVO> getSearchStateTypeOptionList() {
        var list = new ArrayList<SelectOptionVO>();
        list.add(new SelectOptionVO(0, "ALL", "전체"));
        list.add(new SelectOptionVO(0, "HDLE0402", "대기"));
        list.add(new SelectOptionVO(0, "HDLE0403", "진행중"));
        list.add(new SelectOptionVO(0, "HDLE0404", "완료"));
        list.add(new SelectOptionVO(0, "HDLE0405", "승인"));
        list.add(new SelectOptionVO(0, "HDLE0406", "반려"));

        return list;
    }


    /**
     * 결제 게시판 전체 게시물 수 조회
     * @param boardSearchVO {@link BoardSearchVO}
     * @return {@link Integer}
     */
    public int totalCount(BoardSearchVO boardSearchVO) {
        return getCommonMapper().getApproveMapper().findAllTotalCount(boardSearchVO);
    }

}
