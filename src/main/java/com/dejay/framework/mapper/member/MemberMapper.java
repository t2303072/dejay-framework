package com.dejay.framework.mapper.member;

import com.dejay.framework.common.annotation.EntityLog;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.domain.user.User;
import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper extends GeneralMapper {

    int memberListCount();
    int memberListSearchCount(SearchVO search);
    List<MemberVO> getMemberList(SearchVO searchObject);
    MemberVO findMemberById(int id);
    int insertMember(Member member);
    MemberVO findMemberByUserName(String userName);
    MemberVO getLoginInfo(LoginRequest loginRequest);

    @EntityLog
    int signUp(User user);
}
