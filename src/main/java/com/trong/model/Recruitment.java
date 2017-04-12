package com.trong.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "recruitment")
@Data
@ToString
@EqualsAndHashCode(exclude = {"department", "educationalLevel", "employer"})
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    @Column(name = "required_female")
    private Boolean requiredFemale;
    @Column(name = "average_age")
    private Long averageAge;
    @Column(name = "years_of_experience")
    private Long yearsOfExperience;
    @Column(name = "created_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDatetime;
    @Column(name = "lowest_salary")
    private Double lowestSalary;
    @Column(name = "highest_salary")
    private Double highestSalary;
    @Column(name = "deadline_for_submission")
    private java.sql.Date deadlineForSubmission;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "educational_level_id")
    private EducationalLevel educationalLevel;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
