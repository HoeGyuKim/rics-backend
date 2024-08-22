package com.example.repairproductprogram.service;

import com.example.repairproductprogram.model.Member;
import com.example.repairproductprogram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Member findByName(String name) {
        return userRepository.findByName(name);
    }

    public Member searchByName(String name) {
        return userRepository.findByName(name);
    }

    public List<Member> getAllUsers() {
        return userRepository.findAll();
    }

    public Member saveUser(Member member) {
        return userRepository.save(member);
    }
}
