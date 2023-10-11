package com.dejay.framework.service.member;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.TableNameEnum;
import com.dejay.framework.common.exception.CustomLoginException;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.common.utils.TokenFactory;
import com.dejay.framework.common.utils.ValidationUtil;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.domain.common.TokenObjectVO;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.domain.user.SignUpRequest;
import com.dejay.framework.domain.user.User;
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


@Slf4j
@RequiredArgsConstructor
@Service
public class RestMemberServiceImpl extends ParentService implements MemberService  {
// TODO: IJ NPE 위험코드 Optional로 변경
    private final ValidationUtil validationUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenFactory tokenFactory;

    /**
     * 멤버 목록 조회
     * @param searchObject {@link SearchObject}
     * @return {@link CollectionPagingVO}
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
     * @param id int {@link Integer}
     * @return {@link MemberVO}
     */
    public MemberVO findMemberById(int id) {
        MemberVO memberVO = getCommonMapper().getMemberMapper().findMemberById(id);

        if(memberVO == null) {
            return null;
        }

        MemberVO target = MemberVO.builder()
                .memberSeq(memberVO.getMemberSeq())
                .userId(memberVO.getUserId())
                .deptCode(memberVO.getDeptCode())
                .authority(memberVO.getDeptCode())
                .userName(memberVO.getUserName())
                .userEmail(memberVO.getUserEmail())
                .build();

        return target;
    }

    /**
     * 멤버 상세 조회
     * @param userName {@link String}
     * @return {@link MemberVO}
     */
    public MemberVO findMemberByUserName(String userName) {
        return getCommonMapper().getMemberMapper().findMemberByUserName(userName);
    }

    /**
     * 멤버 등록
     * @param member {@link Member}
     * @return {@link Member}
     */
    public Member insertMember(Member member) {
        var target = Member.builder()
                .memberId(member.getMemberId())
                .password(bCryptPasswordEncoder.encode(member.getPassword()))
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .build();

        validationUtil.parameterValidator(target, Member.class);
        getCommonMapper().getMemberMapper().insertMember(target);

        return target;
    }


    /**
     * 사용자 등록(w/ token)
     * @param signUpRequest {@link SignUpRequest}
     * @return {@link TokenObjectVO}
     */
    public TokenObjectVO signUp(SignUpRequest signUpRequest) {
        var target = User.builder()
                .id(signUpRequest.getId())
                .password(bCryptPasswordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .tel(signUpRequest.getUserTel())
                .email(signUpRequest.getEmail())
//              .deptCode(signUpRequest.getAuthority().stream().toList().get(0).getDeptCode())
                .deptCode(signUpRequest.getDeptCode())
                .appointCode(signUpRequest.getAppointCode())
                .positionCode(signUpRequest.getPositionCode())
                .tableName(TableNameEnum.MEMBER.name())
                .logId1("")
                .logType(RequestTypeEnum.CREATE.getRequestType())
                .logId2(null)
                .logJson(null)
                .remark(null)
                .regId(signUpRequest.getId())
                .build();

        validationUtil.parameterValidator(target, User.class);
        int inserted = getCommonMapper().getMemberMapper().insert(target);
        if(inserted > 0) {
            TokenObjectVO tokenObjectVO = tokenFactory.createJWT(signUpRequest.getId(), signUpRequest.getPassword(), signUpRequest.getAuthority());
            return tokenObjectVO;
        }

        return new TokenObjectVO();
    }

    /**
     * 로그인 정보 조회
     * @param loginRequest {@link LoginRequest}
     * @return {@link MemberVO}
     */
    public MemberVO getLoginInfo(LoginRequest loginRequest) {

        MemberVO target = getCommonMapper().getMemberMapper().getLoginInfo(loginRequest.getUserId());
        if(target == null) {
            try {
                throw new CustomLoginException(ExceptionCodeMsgEnum.ACCOUNT_NOT_EXISTS.getCode(), ExceptionCodeMsgEnum.ACCOUNT_NOT_EXISTS.getMsg());
            } catch (CustomLoginException e) {
                throw new RuntimeException(e);
            }
        }

        if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), target.getUserPwd())) {
            return MemberVO.builder()
                    .userId(target.getUserId())
                    .userName(target.getUserName())
                    .userEmail(target.getUserEmail())
                    .authority(target.getDeptCode())
                    .deptCode(target.getDeptCode())
                  /*.tableName(TableNameEnum.LOGIN.name())
                    .logId1(String.valueOf(target.getMemberSeq()))
                    .logType(RequestTypeEnum.LOGIN.getRequestType())
                    .logId2(null)
                    .logJson(null)
                    .remark(null)
                    .regId(target.getUserId())*/
                    .build();
        }else {
            try {
                throw new CustomLoginException(ExceptionCodeMsgEnum.INVALID_PASSWORD.getCode(), ExceptionCodeMsgEnum.INVALID_PASSWORD.getMsg());
            } catch (CustomLoginException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
