package com.example.repairproductprogram.repository;

import com.example.repairproductprogram.model.Approval;
import com.example.repairproductprogram.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    List<Approval> findByApprovalStatus(int approvalStatus);
    List<Approval> findByWorker(Member worker);
}