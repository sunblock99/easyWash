package com.nexg.easywash.service;

import com.nexg.easywash.dto.Member;
import com.nexg.easywash.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public Member login(String email) {
        Member member = new Member();
        member = memberMapper.selectUser(email);
        if(member == null) {
            System.out.println("회원가입진행!");
            memberMapper.insertUser(email);
            member = memberMapper.selectUser(email);
        }
        memberMapper.updateLogindttm(email);
        return member;

    }
}
