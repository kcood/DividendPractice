package com.dayone.model;

<<<<<<< HEAD
=======
import com.dayone.persist.entity.MemberEntity;
>>>>>>> c283280 (Initial commit)
import lombok.Data;

import java.util.List;

public class Auth {

<<<<<<< HEAD
    @Data //로그인시 사용
=======
    @Data
>>>>>>> c283280 (Initial commit)
    public static class SignIn {
        private String username;
        private String password;
    }

<<<<<<< HEAD
    @Data //회원가입시 사용
=======
    @Data
>>>>>>> c283280 (Initial commit)
    public static class SignUp {
        private String username;
        private String password;
        private List<String> roles;

<<<<<<< HEAD
        //SignUp 클래스 내용 멤버엔티티로 바꾸기용
        public MemberEntity toEntity(){
            return MemberEntity.builder()
                    .username(this.username)
                    .password(this.password)
                    .roles(this.roles)
                    .build();
        }

=======
        public MemberEntity toEntity() {
            return MemberEntity.builder()
                            .username(this.username)
                            .password(this.password)
                            .roles(this.roles)
                            .build();
        }
>>>>>>> c283280 (Initial commit)
    }
}
