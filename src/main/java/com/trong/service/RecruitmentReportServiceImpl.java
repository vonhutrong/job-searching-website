package com.trong.service;

import com.trong.model.Employee;
import com.trong.model.Recruitment;
import com.trong.model.RecruitmentReport;
import com.trong.repository.RecruitmentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentReportServiceImpl implements RecruitmentReportService {
    @Autowired
    private RecruitmentReportRepository recruitmentReportRepository;

    @Override
    public List<RecruitmentReport> findAll() {
        return recruitmentReportRepository.findAll();
    }

    @Override
    public Boolean isHasReported(Employee employee, Recruitment recruitment) {
        return recruitmentReportRepository.findByEmployeeAndRecruitment(employee, recruitment) != null;
    }

    @Override
    public RecruitmentReport save(RecruitmentReport recruitmentReport) {
        return recruitmentReportRepository.save(recruitmentReport);
    }
}
