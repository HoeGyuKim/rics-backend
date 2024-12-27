package com.example.repairproductprogram.service;

import com.example.repairproductprogram.model.Approval;
import com.example.repairproductprogram.model.Detail;
import com.example.repairproductprogram.model.Member;
import com.example.repairproductprogram.repository.UserRepository;
import com.example.repairproductprogram.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
@Service
public class DetailUploadService {


    private final DetailRepository detailRepository;
    private final UserRepository userRepository;

    @Autowired
    public DetailUploadService(DetailRepository detailRepository, UserRepository userRepository) {
        this.detailRepository = detailRepository;
        this.userRepository = userRepository;
    }

    public Detail saveDetailWithApproval(Detail detail, int approvalStatus, Long middleManagerNum, Long workerNum, Long lastManagerNum) {
        Approval approval = detail.getApproval();
        if (approval == null) {
            approval = new Approval();
            approval.setApprovalStatus(approvalStatus);
            approval.setSubmitTime(LocalDateTime.now());

            // Manager 및 Worker 설정
            Member lastManager = userRepository.findById(lastManagerNum).orElseThrow(() -> new RuntimeException("LastManager not found"));
            Member middleManager = userRepository.findById(middleManagerNum).orElseThrow(() -> new RuntimeException("MiddleManager not found"));
            Member worker = userRepository.findById(workerNum).orElseThrow(() -> new RuntimeException("Worker not found"));

            approval.setMiddleManager(middleManager);
            approval.setLastManager(lastManager);
            approval.setWorker(worker);

            detail.setApproval(approval);
        }

        return detailRepository.save(detail);
    }
}
