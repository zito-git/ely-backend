package com.github.ely.domain.member;

import com.github.ely.domain.member.dto.LoginDto;
import com.github.ely.domain.member.dto.MemberDto;
import com.github.ely.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void save(MemberDto memberDto) {


        if (memberRepository.existsByMilitaryId(memberDto.getMilitary_id())) {
            throw new RuntimeException("이미 존재하는 유저입니다.");
        }



        Member member = Member.toEntity(memberDto);

        member.passwordEncode(bCryptPasswordEncoder);

        memberRepository.save(member);
    }
}
