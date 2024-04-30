package com.movie.demo.member.service;


import com.movie.demo.member.entity.Member;
import com.movie.demo.member.repository.MemberRepository;
import com.movie.demo.member.request.SignUpRequest;
import com.movie.demo.member.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
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

        Member member = Member.builder()
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .build();

        memberRepository.save(member);

        return new SignUpResponse(true,"회원가입이 성공적으로 완료되었습니다.",member.getId());
    }
}
