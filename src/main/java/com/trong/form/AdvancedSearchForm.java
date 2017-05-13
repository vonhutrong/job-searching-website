package com.trong.form;

import lombok.Data;

@Data
public class AdvancedSearchForm {
    private String keyword;
    private Long departmentId;
    private Boolean requiredFemale;
    private Long averageAge;
    private Long yearsOfExperience;
    private Double salary;
    private Long educationalLevelId;
}
