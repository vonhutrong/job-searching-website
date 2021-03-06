package com.trong.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
@ToString(exclude = {"user", "applyHistories"})
@EqualsAndHashCode(exclude = {"user", "applyHistories", "recruitmentReports"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "employee")
    private Set<ApplyHistory> applyHistories;
    @OneToMany(mappedBy = "employee")
    private Set<RecruitmentReport> recruitmentReports;
}
