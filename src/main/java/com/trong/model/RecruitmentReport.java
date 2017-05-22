package com.trong.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "recruitment_report")
@Data
@ToString(exclude = {"employee", "recruitment"})
public class RecruitmentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;
}
