package com.example.repairproductprogram.model;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeNum;  // 사원 번호 (비밀번호로 사용)

    @Column(name = "name", nullable = false)
    private String name;  // 사용자 이름

    @ManyToOne
    @JoinColumn(name = "department_code", nullable = false)
    private Department department;  // 부서 정보

    // 기본 생성자
    public Member() {}

    // 생성자
    public Member(String name, Long employeeNum) {
        this.name = name;
        this.employeeNum = employeeNum;
    }

    // Getter와 Setter
    public Long getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Long employee_num) {
        this.employeeNum = employee_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
