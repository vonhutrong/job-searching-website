package com.trong.service;

import com.trong.model.Employee;
import com.trong.model.Recruitment;
import com.trong.model.RecruitmentReport;

import java.util.List;

public interface RecruitmentReportService {
    List<RecruitmentReport> findAll();

    Boolean isHasReported(Employee employee, Recruitment recruitment);

    RecruitmentReport save(RecruitmentReport recruitmentReport);
}
