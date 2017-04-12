package com.trong.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RecruitmentForm {
    private String title;
    private String content;
    private Long departmentId;
    private Boolean requiredFemale;
    private Long educationalLevelId;
    private Long averageAge;
    private Long yearsOfExperience;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private java.util.Date deadlineForSubmission;
    private Double lowestSalary;
    private Double highestSalary;
}
