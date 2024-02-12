package com.nexg.easywash.controller;

import com.nexg.easywash.service.KakaoAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class memberController {

    KakaoAPI kakaoApi = new KakaoAPI();
    @GetMapping("/loginCallBack")
    @ResponseBody
    public RedirectView login(@RequestParam String code, HttpSession session){
      String url = "https://kauth.kakao.com/oauth/authorize?client_id=cd8b1886f132139b606ba3a3939880a9&redirect_uri=http://localhost:8282/api/user/loginCallBack&response_type=code";

        System.out.println("kakao callback 컨트롤러 접근");
        System.out.println(code);

        RedirectView redirectView = new RedirectView();

        //1. 코드전달
        String access_token = kakaoApi.getAccessToken(code);

        System.out.println("1. access_token : " + access_token);



        //2. 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(access_token);

        System.out.println("2. login info : " + userInfo);
        System.out.println("2-1. login info : " + userInfo.toString());

        if(userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_token", access_token);
        }

        redirectView.addStaticAttribute("email", userInfo.get("email"));
        redirectView.setUrl("http://naver.com");

        return redirectView;
    };

    /**
     * kakao callback
     */
    @GetMapping("/loginCallBacks")
    public void loginCallback(@RequestParam String code){
        System.out.println("kakao Callback 컨트롤러 접근");
        System.out.println(code);
    }
}
