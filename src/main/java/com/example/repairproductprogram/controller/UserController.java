package com.example.repairproductprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.repairproductprogram.model.Member;
import com.example.repairproductprogram.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody Member member) {
        // 이름으로 사용자 조회
        Member existingMember = userService.findByName(member.getName());
        // 사원번호와 이름이 일치하는지 확인
        if (existingMember != null && existingMember.getEmployeeNum().equals(member.getEmployeeNum())) {
            return "로그인 성공";
        } else {
            return "아이디 또는 비밀번호가 잘못되었습니다";
        }
    }
    @PostMapping("/register")
    public Member register(@RequestBody Member member) {
        return userService.saveUser(member);
    }
}