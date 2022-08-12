package com.dayone.model;

import lombok.Data;

import java.util.List;

public class Auth {

    @Data //로그인시 사용
    public static class SignIn {
        private String username;
        private String password;
    }

    @Data //회원가입시 사용
    public static class SignUp {
        private String username;
        private String password;
        private List<String> roles;

        //SignUp 클래스 내용 멤버엔티티로 바꾸기용
        public MemberEntity toEntity(){
            return MemberEntity.builder()
                    .username(this.username)
                    .password(this.password)
                    .roles(this.roles)
                    .build();
        }

    }
}
