package com.trong.repository;

import com.trong.model.Employee;
import com.trong.model.Recruitment;
import com.trong.model.RecruitmentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentReportRepository extends JpaRepository<RecruitmentReport, Long> {
    RecruitmentReport findByEmployeeAndRecruitment(Employee employee, Recruitment recruitment);
}
