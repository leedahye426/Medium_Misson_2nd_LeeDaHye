package com.ll.medium_clone_coding.global.initData;

import com.ll.medium_clone_coding.domain.member.member.entity.Member;
import com.ll.medium_clone_coding.domain.member.member.service.MemberService;
import com.ll.medium_clone_coding.domain.post.post.service.PostService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(MemberService memberService, PostService postService) {
        return args -> {
            Member admin = memberService.join("admin", "1234");
//            Member member1 = memberService.join("user1", "1234");
//            Member member2 = memberService.join("user2", "1234");
//            memberService.setPaidForMember("user2", true);
//
//            for(int i = 1; i <= 10; i++) {
//                String title = "user1의 공개 글 제목" + i;
//                String body = "내용" + i;
//                postService.write(member1, title, body, "true", "false");
//            }
//
//            for(int i = 1; i <= 10; i++) {
//                String title = "user1의 비공개 글 제목" + i;
//                String body = "내용" + i;
//                postService.write(member1, title, body, "false", "false");
//            }
//
//            for(int i = 1; i <= 10; i++) {
//                String title = "user2의 무료 글 제목" + i;
//                String body = "내용" + i;
//                postService.write(member2, title, body, "true", "false");
//            }
//
//            for(int i = 1; i <= 10; i++) {
//                String title = "user2의 유료 글 제목" + i;
//                String body = "내용" + i;
//                postService.write(member2, title, body, "true", "true");
//            }
//
            for(int i = 1; i <= 100; i++) {
                String username = "샘플 회원" + i;
                Member member = memberService.join(username, "1234");

                memberService.setPaidForMember(username, true);

                String title = "샘플 데이터 " + i;
                postService.write(member, title, "샘플 데이터 내용", "true", "true");
            }


        };
    }
}
