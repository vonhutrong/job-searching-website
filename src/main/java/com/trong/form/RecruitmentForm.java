package com.trong.form;

import com.trong.validation.annotation.ValidSalaryBounds;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ToString
@ValidSalaryBounds(min = "lowestSalary", max="highestSalary")
public class RecruitmentForm {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotNull
    private Long departmentId;
    private Boolean requiredFemale;
    @NotNull
    private Long educationalLevelId;
    @NotNull
    @Min(16)
    private Long averageAge;
    @NotNull
    @Min(0)
    private Long yearsOfExperience;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Future
    private java.util.Date deadlineForSubmission;
    @NotNull
    private Double lowestSalary;
    @NotNull
    private Double highestSalary;
}
