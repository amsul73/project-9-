package com.gatchaPedia.demo.member.service;


import com.gatchaPedia.demo.member.entity.Member;
import com.gatchaPedia.demo.member.exception.*;
import com.gatchaPedia.demo.member.repository.MemberRepository;
import com.gatchaPedia.demo.member.request.LoginRequest;
import com.gatchaPedia.demo.member.request.SignUpRequest;
import com.gatchaPedia.demo.member.response.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public SignUpResponse signup(SignUpRequest signUpRequest) {

        // 이미 존재하는 username로 회원 가입을 시도 한다면
        if(checkDuplicateUsername(signUpRequest.getUsername())) throw new DuplicateMemberByUsernameException();
        // 이미 존재하는 email로 회원 가입을 시도 한다면
        if(checkDuplicateEmail(signUpRequest.getEmail())) throw new DuplicateMemberByEmailException();


        // 모두 통과한 성공로직
        Member member = Member.builder()
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .build();

        memberRepository.save(member);

        return new SignUpResponse(true,"회원가입이 성공적으로 완료되었습니다.",member.getId());
    }



    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) {

        // 서버가 배포가되면 DB와 Spring 서버가 다른곳에 올라감 == 통신하려면 네트워크 비용이 들어감
        // 따라서 이렇게 member를 한번 확인하고 다시 꺼내오는 로직은 안좋음, 하지만 이 프로젝트는 일단 localhost로 시연만 할거니까
        // 나중에 리팩토링

        // DB에 해당 아이디가 없을경우 예외처리는 나중에
        if(!memberRepository.existsByUsername(loginRequest.getUsername())) throw new MemberUsernameNotExistException();
        // DB에서 해당 아이디로 조회한 멤버의 비밀번호랑 입력 비밀번호가 다를시
        Member realMember = memberRepository.findByUsername(loginRequest.getUsername());
        if(!realMember.getPassword().equals(loginRequest.getPassword()))throw new PasswordMissMatchException();


        // 세션 하나 만들어서 쿠키에 넣어주고
        HttpSession httpSession = request.getSession(true);
        String sessionId = httpSession.getId();
        // 서버 세션 저장소에 JSESSIONID의 값과, realMember의 정보 저장
        httpSession.setAttribute(sessionId,realMember);

        return new LoginResponse(true, "로그인 성공", realMember.getId(), realMember.getName());
    }



    private boolean checkDuplicateUsername(String username){
        return memberRepository.existsByUsername(username);
    }

    private boolean checkDuplicateEmail(String email){
        return memberRepository.existsByEmail(email);
    }





    @Override
    public LogoutResponse logout(HttpServletRequest request){

        HttpSession httpSession = request.getSession(false);

        if (httpSession != null) httpSession.invalidate();

        return new LogoutResponse(true,"로그아웃 성공");
    }



    @Override
    @Transactional
    public SignoutResponse signout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String sessionId = session.getId();

        Member member = (Member) session.getAttribute(sessionId);
        memberRepository.delete(member);
        session.invalidate();

        return new SignoutResponse(true, "회원 탈퇴 완료");
    }

    @Override
    public InvaildSessionCheckResponse sessionCheck(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String sessionId = session.getId();
        Member member = (Member) session.getAttribute(sessionId);

        return new InvaildSessionCheckResponse(true, member.getName(),"세션 유효 체크 성공");
    }
}
