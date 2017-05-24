package com.trong.service;

import com.mysema.query.jpa.impl.JPAQuery;
import com.trong.form.AdvancedSearchForm;
import com.trong.model.Employer;
import com.trong.model.QRecruitment;
import com.trong.model.QRecruitmentReport;
import com.trong.model.Recruitment;
import com.trong.repository.DepartmentRepository;
import com.trong.repository.EducationalLevelRepository;
import com.trong.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentServiceImpl extends AbstractService implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final DepartmentRepository departmentRepository;
    private final EducationalLevelRepository educationalLevelRepository;

    @Autowired
    public RecruitmentServiceImpl(RecruitmentRepository recruitmentRepository, DepartmentRepository departmentRepository, EducationalLevelRepository educationalLevelRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.departmentRepository = departmentRepository;
        this.educationalLevelRepository = educationalLevelRepository;
    }

    @Override
    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    @Override
    public Recruitment findById(Long id) {
        return recruitmentRepository.findById(id);
    }

    @Override
    public Page<Recruitment> recent(Pageable pageable) {
        return recruitmentRepository.findByBannedIsNullOrBannedIsFalseOrderByCreatedDatetimeDesc(pageable);
    }

    @Override
    public Page<Recruitment> searchBasic(String keyword, Long departmentId, Pageable pageable) {
        QRecruitment recruitment = QRecruitment.recruitment;
        JPAQuery query = from(recruitment);
        query.where(recruitment.banned.isNull().or(recruitment.banned.eq(false)));
        if (!keyword.isEmpty())
            query.where(recruitment.title.containsIgnoreCase(keyword));
        if (0 != departmentId)
            query.where(recruitment.department.id.eq(departmentId));
        query.orderBy(recruitment.createdDatetime.desc());
        long total = query.count();
        List<Recruitment> recruitments = query
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .list(recruitment);
        return new PageImpl<Recruitment>(recruitments, pageable, total);
    }

    @Override
    public Page<Recruitment> findByEmployer(Employer employer, Pageable pageable) {
        return recruitmentRepository.findByEmployerOrderByCreatedDatetimeAsc(employer, pageable);
    }

    @Override
    public Page<Recruitment> search(AdvancedSearchForm advancedSearchForm, Pageable pageable) {
        QRecruitment recruitment = QRecruitment.recruitment;
        JPAQuery query = from(recruitment);

        if (advancedSearchForm.getKeyword() != null && !advancedSearchForm.getKeyword().trim().isEmpty())
            query.where(recruitment.title.contains(advancedSearchForm.getKeyword()));
        if (advancedSearchForm.getDepartmentId() != null)
            query.where(recruitment.department.eq(departmentRepository.findById(advancedSearchForm.getDepartmentId())));
        if (advancedSearchForm.getRequiredFemale() != null)
            query.where(recruitment.requiredFemale.eq(advancedSearchForm.getRequiredFemale()));
        if (advancedSearchForm.getAverageAge() != null)
            query.where(recruitment.averageAge.eq(advancedSearchForm.getAverageAge()));
        if (advancedSearchForm.getYearsOfExperience() != null)
            query.where(recruitment.yearsOfExperience.eq(advancedSearchForm.getYearsOfExperience()));
        if (advancedSearchForm.getSalary() != null) {
            query.where(recruitment.lowestSalary.lt(advancedSearchForm.getSalary())
                    .or(recruitment.lowestSalary.eq(advancedSearchForm.getSalary())));
            query.where(recruitment.highestSalary.gt(advancedSearchForm.getSalary())
                    .or(recruitment.highestSalary.eq(advancedSearchForm.getSalary())));
        }
        if (advancedSearchForm.getEducationalLevelId() != null)
            query.where(recruitment.educationalLevel.eq(educationalLevelRepository.findById(advancedSearchForm.getEducationalLevelId())));
        query.where(recruitment.banned.isNull().or(recruitment.banned.eq(false)));
        query.orderBy(recruitment.createdDatetime.desc());

        long totalPages = query.count();
        List<Recruitment> recruitments = query
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .list(recruitment);
        return new PageImpl<Recruitment>(recruitments, pageable, totalPages);
    }

    @Override
    public Page<Recruitment> getTopReportedRecruitments(Pageable pageable) {
        QRecruitment recruitment = QRecruitment.recruitment;
        QRecruitmentReport recruitmentReport = QRecruitmentReport.recruitmentReport;

        JPAQuery query = from(recruitment)
                .rightJoin(recruitment.recruitmentReports, recruitmentReport);
        query.where(recruitment.banned.isNull());
        query.groupBy(recruitment.id);
        query.orderBy(recruitmentReport.id.countDistinct().desc());

        long total = query.list(recruitment).size();
        List<Recruitment> recruitments = query
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .list(recruitment);
        return new PageImpl<Recruitment>(recruitments, pageable, total);
    }

    @Override
    public Page<Recruitment> findByEmployerNotBanned(Employer employer, Pageable pageable) {
        QRecruitment recruitment = QRecruitment.recruitment;
        JPAQuery query = from(recruitment);
        query.where(recruitment.employer.eq(employer))
                .where(recruitment.banned.isNull().or(recruitment.banned.eq(false)))
                .orderBy(recruitment.createdDatetime.desc());
        return generateRecruitmentPage(recruitment, query, pageable);
    }

    private Page<Recruitment> generateRecruitmentPage(QRecruitment recruitment, JPAQuery query, Pageable pageable) {
        long total = query.count();
        List<Recruitment> recruitments = query
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .list(recruitment);
        return new PageImpl<Recruitment>(recruitments, pageable, total);
    }
}
