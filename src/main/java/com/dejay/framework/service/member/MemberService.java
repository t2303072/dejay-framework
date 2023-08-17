package com.dejay.framework.service.member;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.exception.CustomLoginException;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.common.utils.TokenFactory;
import com.dejay.framework.common.utils.ValidationUtil;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.domain.common.TokenObjectVO;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.domain.user.SignUpRequest;
import com.dejay.framework.domain.user.User;
import com.dejay.framework.mapper.member.MemberMapper;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.common.CollectionPagingVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.SearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService extends ParentService {
// TODO: IJ NPE 위험코드 Optional로 변경
    private final MemberMapper memberMapper;
    private final ValidationUtil validationUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenFactory tokenFactory;

    /**
     * 멤버 목록 조회
     * @param searchObject {@link SearchObject}
     * @return
     */
    public CollectionPagingVO getMemberList(SearchObject searchObject) {

        int totalCount = getCommonMapper().getMemberMapper().memberListCount();
        CollectionPagingVO collectionPagingVO = null;
        if(totalCount > 0) {
            int totalSearchCount = getCommonMapper().getMemberMapper().memberListSearchCount(searchObject.getSearch());
            Paging paging = ObjectHandlingUtil.pagingOperator(searchObject, totalSearchCount);
            SearchVO searchVO = new SearchVO();
            searchVO.setPaging(paging);
            List<MemberVO> memberList = getCommonMapper().getMemberMapper().getMemberList(searchVO);
            collectionPagingVO = CollectionPagingVO.builder()
                    .objects(memberList)
                    .paging(paging)
                    .build();
        }else {
            collectionPagingVO = CollectionPagingVO.builder()
                    .objects(new ArrayList<>())
                    .paging(Paging.builder().currentPage(1).build())
                    .build();
        }

        return collectionPagingVO;
    }

    /**
     * 멤버 상세 조회
     * @param id int
     * @return
     */
    public MemberVO findMemberById(int id) {
        return memberMapper.findMemberById(id);
    }

    /**
     * 멤버 상세 조회
     * @param userName String
     * @return
     */
    public MemberVO findMemberByUserName(String userName) {
        return memberMapper.findMemberByUserName(userName);
    }

    /**
     * 멤버 등록
     * @param member {@link Member}
     * @return
     */
    public Member insertMember(Member member) {
        var target = Member.builder()
                .memberId(member.getMemberId())
                .password(bCryptPasswordEncoder.encode(member.getPassword()))
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .build();

        validationUtil.parameterValidator(target, Member.class);
        memberMapper.insertMember(target);

        return target;
    }

    /**
     * 사용자 등록
     * @param user
     * @return
     */
    public User insert(User user) {
        var target = User.builder()
                .id(user.getId())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .email(user.getEmail())
                .build();

        validationUtil.parameterValidator(target, User.class);
        memberMapper.insert(target);

        return target;
    }

    /**
     * 사용자 등록(w/ token)
     * @param signUpRequest {@link SignUpRequest}
     * @return
     */
    public TokenObjectVO signUp(SignUpRequest signUpRequest) {
        var target = User.builder()
                .id(signUpRequest.getId())
                .password(bCryptPasswordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .deptCode(signUpRequest.getAuthority().stream().toList().get(0).getDeptCode())
                .build();

        validationUtil.parameterValidator(target, User.class);
        long inserted = memberMapper.insert(target);

        if(inserted > 0) {
            TokenObjectVO tokenObjectVO = tokenFactory.createJWT(signUpRequest.getId(), signUpRequest.getPassword(), signUpRequest.getAuthority());
            return tokenObjectVO;
        }

        return new TokenObjectVO();
    }

    /**
     * 로그인 정보 조회
     * @param loginRequest {@link LoginRequest}
     * @return
     */
    public MemberVO getLoginInfo(LoginRequest loginRequest) {
        MemberVO target = findMemberByUserName(loginRequest.getUserName());
        if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), target.getPassword())) {
            return MemberVO.builder()
                    .memberId(target.getMemberId())
                    .memberName(target.getMemberName())
                    .email(target.getEmail())
                    .authority(Set.of(target.getDeptCode()))
                    .deptCode(target.getDeptCode())
                    .build();
        }

        try {
            throw new CustomLoginException(ExceptionCodeMsgEnum.INVALID_PASSWORD.getCode(), ExceptionCodeMsgEnum.INVALID_PASSWORD.getMsg());
        } catch (CustomLoginException e) {
            throw new RuntimeException(e);
        }
    }
}
