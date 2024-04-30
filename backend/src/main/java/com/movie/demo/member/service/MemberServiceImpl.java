package com.movie.demo.member.service;


import com.movie.demo.member.entity.Member;
import com.movie.demo.member.exception.MemberUsernameNotExistException;
import com.movie.demo.member.exception.PasswordMisMatchException;
import com.movie.demo.member.repository.MemberRepository;
import com.movie.demo.member.request.LoginRequest;
import com.movie.demo.member.request.SignUpRequest;
import com.movie.demo.member.response.LoginResponse;
import com.movie.demo.member.response.SignUpResponse;
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
            throw new PasswordMisMatchException();
        };


        HttpSession httpSession = request.getSession(true);
        String sessionId = httpSession.getId();
        httpSession.setAttribute(sessionId,realMember);


        return new LoginResponse(true, "로그인 성공", realMember.getId());
    }

}
