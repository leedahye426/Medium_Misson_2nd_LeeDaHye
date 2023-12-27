package com.ll.medium_clone_coding.domain.member.member.controller;

import com.ll.medium_clone_coding.domain.member.member.entity.MemberCreateForm;
import com.ll.medium_clone_coding.domain.member.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join(MemberCreateForm memberCreateForm) {
        return "domain/member/member/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "domain/member/member/join";
        }

        if(!memberCreateForm.getPassword().equals(memberCreateForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
        }

        try {
            memberService.join(memberCreateForm.getUsername(), memberCreateForm.getPassword());
        } catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "domain/member/member/join";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "domain/member/member/login";
    }


}
