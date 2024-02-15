package com.nexg.easywash.controller;

import com.nexg.easywash.dto.Member;
import com.nexg.easywash.service.KakaoAPI;
import com.nexg.easywash.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class MemberController {

    KakaoAPI kakaoApi = new KakaoAPI();

    @Autowired
    MemberService memberService;

    @GetMapping("/loginCallBack")
    @ResponseBody
    @ApiOperation(value = "카카오로그인", notes = "요청 URL : https://kauth.kakao.com/oauth/authorize?client_id=cd8b1886f132139b606ba3a3939880a9&redirect_uri=http://localhost:8282/api/user/loginCallBack&response_type=code")
    public ResponseEntity<Member> login(@RequestParam String code, HttpSession session){
      String url = "https://kauth.kakao.com/oauth/authorize?client_id=cd8b1886f132139b606ba3a3939880a9&redirect_uri=http://localhost:8282/api/user/loginCallBack&response_type=code";

        //1. 코드전달
        String access_token = kakaoApi.getAccessToken(code);

        //2. 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(access_token);

        System.out.println("2. login info : " + userInfo);
        System.out.println("2-1. login info : " + userInfo.toString());

        if(userInfo.get("email") != null) {
            session.setAttribute("email", userInfo.get("email"));
            session.setAttribute("access_token", access_token);
        }

        String email = String.valueOf(userInfo.get("email"));
        Member member = memberService.login(email);

        return ResponseEntity.ok(member);
    };


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
