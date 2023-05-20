package com.nhnacademy.family_relationship_certification.service;

import com.nhnacademy.family_relationship_certification.domain.ReportRequest;
import com.nhnacademy.family_relationship_certification.entity.BirthDeathReportResident;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import com.nhnacademy.family_relationship_certification.repository.BirthDeathReportRepository;
import com.nhnacademy.family_relationship_certification.repository.ResidentRepository;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportService {

    private final BirthDeathReportRepository reportRepository;
    private final ResidentRepository residentRepository;

    public ReportService(BirthDeathReportRepository reportRepository, ResidentRepository residentRepository) {
        this.reportRepository = reportRepository;
        this.residentRepository = residentRepository;
    }

    public void registerBirthReport(Integer id, ReportRequest request) {
        if (!residentRepository.existsById(id)) {
            throw new NotFoundException("존재하지 않는 가족 아이디 입니다.");
        }
        if (!residentRepository.existsById(request.getResidentSerialNumber())){
            throw new NotFoundException("존재하지 않는 아이디 입니다.");
        }
        Resident resident = residentRepository.findById(request.getResidentSerialNumber()).get();
        Resident reportResident = residentRepository.findById(id).get();
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(
                resident.getResidentId(),reportResident.getResidentId(), request.getBirthDeathTypeCode()
        );
        BirthDeathReportResident report = BirthDeathReportResident
                .builder()
                .pk(pk)
                .birthDeathReportDate(LocalDateTime.now())
                .birthReportQualificationsCode(request.getBirthReportQualificationsCode())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .resident(resident)
                .reportResident(reportResident)
                .build();
        reportRepository.saveAndFlush(report);
    }

    public void modifiedBirthReport(Integer familyId,Integer id, ReportRequest request) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(
                id,familyId, request.getBirthDeathTypeCode()
        );
        if(!reportRepository.existsById(pk)) {
            throw new NotFoundException("존재하지 않는 신고기록 입니다.");
        }
        Resident resident = residentRepository.findById(id).get();
        Resident reportResident = residentRepository.findById(familyId).get();
        BirthDeathReportResident pastReport = reportRepository.findById(pk).get();

        BirthDeathReportResident report = BirthDeathReportResident
                .builder()
                .pk(pk)
                .birthDeathReportDate(pastReport.getBirthDeathReportDate())
                .birthReportQualificationsCode(request.getBirthReportQualificationsCode())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .resident(resident)
                .reportResident(reportResident)
                .build();
        reportRepository.saveAndFlush(report);
    }

    public void removeBirthReport(Integer familyId,Integer id) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(
                id,familyId, "출생"
        );
        if(!reportRepository.existsById(pk)) {
            throw new NotFoundException("존재하지 않는 신고기록 입니다.");
        }
        reportRepository.deleteById(pk);
    }

    public void registerDeathReport(Integer id, ReportRequest request) {
        if (!residentRepository.existsById(id)) {
            throw new NotFoundException("존재하지 않는 가족 아이디 입니다.");
        }
        if (!residentRepository.existsById(request.getResidentSerialNumber())){
            throw new NotFoundException("존재하지 않는 아이디 입니다.");
        }
        Resident resident = residentRepository.findById(request.getResidentSerialNumber()).get();
        Resident reportResident = residentRepository.findById(id).get();
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(
                resident.getResidentId(),reportResident.getResidentId(), "사망"
        );
        BirthDeathReportResident report = BirthDeathReportResident
                .builder()
                .pk(pk)
                .birthDeathReportDate(LocalDateTime.now())
                .birthReportQualificationsCode(request.getDeathReportQualificationsCode())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .resident(resident)
                .reportResident(reportResident)
                .build();
        reportRepository.saveAndFlush(report);
    }

    public void modifiedDeathReport(Integer familyId,Integer id, ReportRequest request) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(
                id,familyId, request.getBirthDeathTypeCode()
        );
        if(!reportRepository.existsById(pk)) {
            throw new NotFoundException("존재하지 않는 신고기록 입니다.");
        }
        Resident resident = residentRepository.findById(id).get();
        Resident reportResident = residentRepository.findById(familyId).get();
        BirthDeathReportResident pastReport = reportRepository.findById(pk).get();

        BirthDeathReportResident report = BirthDeathReportResident
                .builder()
                .pk(pk)
                .birthDeathReportDate(pastReport.getBirthDeathReportDate())
                .birthReportQualificationsCode(request.getDeathReportQualificationsCode())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .resident(resident)
                .reportResident(reportResident)
                .build();
        reportRepository.saveAndFlush(report);
    }
    public void removeDeathReport(Integer reportId,Integer id) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(
                id,reportId, "사망"
        );
        if(!reportRepository.existsById(pk)) {
            throw new NotFoundException("존재하지 않는 신고기록 입니다.");
        }
        reportRepository.deleteById(pk);
    }

}
