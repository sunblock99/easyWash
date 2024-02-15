package com.nexg.easywash.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    /**
     * 회원번호
     */
    int memNo;

    /**
     * 이름
     */
    String nickname;

    /**
     * 이메일
     */
    String email;

    /**
     * 전화번호
     */
    String telNo;

    /**
     * 생년월일
     */
    Date birth;

    /**
     * 주소
     */
    String address;

    /**
     * 로그인일자
     */
    Date loginDttm;

    /**
     * 생성일자
     */
    Date createDttm;

    /**
     * 상태
     */
    String status;

    /**
     * 사용자 추가정보 입력 여부
     */
    boolean isLoginInfo;

}
