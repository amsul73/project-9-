package com.gatchaPedia.demo.member.service;


import com.gatchaPedia.demo.member.entity.Member;
import com.gatchaPedia.demo.member.exception.*;
import com.gatchaPedia.demo.member.repository.MemberRepository;
import com.gatchaPedia.demo.member.request.LoginRequest;
import com.gatchaPedia.demo.member.request.SignUpRequest;
import com.gatchaPedia.demo.member.response.LoginResponse;
import com.gatchaPedia.demo.member.response.SignUpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public SignUpResponse signup(SignUpRequest signUpRequest) {


        // 이미 존재하는 name으로 회원 가입을 시도 한다면
        if(checkDuplicateName(signUpRequest.getName())) throw new DuplicateMemberByNameException();
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

        // DB에 해당 아이디가 없을경우 예외처리는 나중에
        if(!memberRepository.existsByUsername(loginRequest.getUsername())){
            System.out.println("그런 아이디는 없음");
            throw new MemberUsernameNotExistException();
        }

        // DB에서 해당 아이디로 조회한 멤버의 비밀번호랑 입력 비밀번호가 다를시
        Member realMember = memberRepository.findByUsername(loginRequest.getUsername());
        if(!realMember.getPassword().equals(loginRequest.getPassword())){
            log.info("비밀번호 불일치");
            throw new PasswordMissMatchException();
        };


        HttpSession httpSession = request.getSession(true);
        String sessionId = httpSession.getId();
        httpSession.setAttribute(sessionId,realMember);


        return new LoginResponse(true, "로그인 성공", realMember.getId());
    }






    private boolean checkDuplicateName(String name){
        return memberRepository.existsByName(name);
    }

    private boolean checkDuplicateUsername(String username){
        return memberRepository.existsByUsername(username);
    }

    private boolean checkDuplicateEmail(String email){
        return memberRepository.existsByEmail(email);
    }
}
