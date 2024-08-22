package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
}
