package com.example.repairproductprogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repairproductprogram.model.Member;
import com.example.repairproductprogram.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 이름으로 사용자 찾기
    public Member findByName(String name) {
        return userRepository.findByName(name); // 수정된 메소드 호출
    }

    // 사용자 저장
    public Member saveUser(Member member) {
        return userRepository.save(member);
    }
}