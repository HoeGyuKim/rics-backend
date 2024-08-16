package com.example.repairproductprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.repairproductprogram.model.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {
    // 사용자 이름으로 사용자 조회
    Member findByName(String name);
}