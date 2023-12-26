package com.ll.medium_clone_coding.global.initData;

import com.ll.medium_clone_coding.domain.member.member.entity.Member;
import com.ll.medium_clone_coding.domain.member.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(MemberService memberService) {
        return args -> {
            Member member1 = memberService.join("user1", "1234");
            Member member2 = memberService.join("user2", "1234");
            Member member3 = memberService.join("user3", "1234");
        };
    }
}
