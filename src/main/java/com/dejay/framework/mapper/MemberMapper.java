package com.dejay.framework.mapper;

import com.dejay.framework.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> getMemberList();
}
