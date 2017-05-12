package com.trong.form;

import lombok.Data;

@Data
public class AdvancedSearchForm {
    private String keyword;
    private Integer departmentId;
    private Boolean requiredFemale;
    private Integer averageAge;
    private Integer yearsOfExperience;
    private Double salary;
    private Integer educationalLevelId;
}
