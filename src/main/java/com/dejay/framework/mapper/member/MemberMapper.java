package com.dejay.framework.mapper.member;

import com.dejay.framework.domain.Member;
import com.dejay.framework.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberVO> getMemberList();
    MemberVO findMemberById(int id);
    long insertMember(Member member);
}
