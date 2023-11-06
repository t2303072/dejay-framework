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

    public int totalCount(BoardSearchVO boardSearchVO) {
        return getCommonMapper().getApproveMapper().findAllTotalCount(boardSearchVO);
    }

    @Override
    public Map<String, Object> deleteBySeq(String lastId, List<Integer> tgtList) {
        // TODO 삭제 처리 전 해당 시퀀스 조회
        var result = new HashMap<String, Object>();
        result.put("code", 200);

        var paramMap = new HashMap<String, Object>();
        paramMap.put("lastId", lastId);
        paramMap.put("boardSeqList", tgtList);
        int deleteCount = getCommonMapper().getBoardMapper().deleteList(paramMap);
        if(deleteCount < 1) {
            result.put("code", 204);
            result.put("message", "삭제할 대상이 없습니다.");
            return result;
        }
        paramMap.put("seqList",tgtList); // 파일 입출력을 위한 seqList Setting
        getCommonMapper().getFileMapper().deleteFileList(paramMap);
        result.put("message", "삭제 되었습니다.");


        return result;
    }

}
