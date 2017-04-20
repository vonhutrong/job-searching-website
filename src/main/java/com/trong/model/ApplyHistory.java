package com.trong.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "apply_history")
@Data
@ToString(exclude = {"employee", "recruitment"})
public class ApplyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "cv_path ")
    private String cvPath;
    private java.util.Date datetime;
    private Boolean approved;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;
}
