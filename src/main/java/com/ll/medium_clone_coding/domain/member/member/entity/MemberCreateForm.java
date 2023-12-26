package com.ll.medium_clone_coding.domain.member.member.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {
    @NotBlank(message = "사용자명(ID)은 필수항목입니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수항목입니다.")
    private String password;
    @NotBlank(message = "비밀번호 확인은 필수항목입니다.")
    private String passwordConfirm;
}
