package com.example.repairproductprogram.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department
{
    @Id
    private Long id;
    @Column(name = "department_name")
    private String departmentName;
}
