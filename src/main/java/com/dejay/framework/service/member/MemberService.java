package com.dejay.framework.service.member;

import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.domain.common.TokenObjectVO;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.domain.user.SignUpRequest;
import com.dejay.framework.vo.common.CollectionPagingVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.SearchVO;
import com.dejay.framework.vo.user.UserVO;

import java.util.List;
import java.util.Map;

public interface MemberService {

    // 멤버 목록 조회
    CollectionPagingVO getMemberList(SearchObject searchObject);

    // 멤버 상세 조회
    MemberVO findMemberById(int id);

    // 멤버 상세 조회
    MemberVO findMemberByUserName(String userName);

    // 멤버 등록
    Member insertMember(Member member);

    // 사용자 등록(w/ token)
    TokenObjectVO signUp(SignUpRequest signUpRequest);

    // 로그인 정보 조회
    MemberVO getLoginInfo(LoginRequest loginRequest);

    Map<String,Object> updatePwd(MemberVO member);

    // 전체 사용자 조회
    List<UserVO> findAllUsers(SearchVO searchVO);
}
