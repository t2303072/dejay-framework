package com.dejay.framework.mapper;

import com.dejay.framework.domain.Member;
import com.dejay.framework.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> getMemberList();
    MemberVO findMemberById(Member member);
}
