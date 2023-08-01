package com.dejay.framework.mapper.member;

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
    long insertMember(Member member);
    MemberVO findMemberByUserName(String userName);
    long insertUser(User user);
    MemberVO getLoginInfo(LoginRequest loginRequest);
}
