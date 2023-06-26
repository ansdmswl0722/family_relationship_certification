package com.nhnacademy.family_relationship_certification.service;

import com.nhnacademy.family_relationship_certification.domain.CertificateIssueDto;
import com.nhnacademy.family_relationship_certification.domain.CertificateIssueVO;
import com.nhnacademy.family_relationship_certification.entity.CertificateIssue;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import com.nhnacademy.family_relationship_certification.repository.CertificateIssueRepository;
import com.nhnacademy.family_relationship_certification.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class CertificateIssueService {

    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;

    public CertificateIssueService(CertificateIssueRepository certificateIssueRepository, ResidentRepository residentRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
        this.residentRepository = residentRepository;
    }

    public CertificateIssueVO addCertificateIssuanceRecord(int residentId) {
        Resident resident = residentRepository.findById(residentId).get();
        Long num = randomCertificateNum();
        CertificateIssue certificateIssue = new CertificateIssue(
                num, resident, "가족관계증명서", LocalDate.now()
        );
        this.certificateIssueRepository.saveAndFlush(certificateIssue);
        CertificateIssueDto dto = certificateIssueRepository.findByCertificateId(num);
        return new CertificateIssueVO(
                dto.getCertificateId(),
                dto.getResident().getRegistrationBaseAddress(),
                dto.getCertificateIssueDate()
        );
    }

    public Long randomCertificateNum() {
        Random rand = new Random();
        String temp = Integer.toString(rand.nextInt(8) + 1);
        for (int i = 0; i < 15; i++) {
            temp += Integer.toString(rand.nextInt(9));
        }
        return Long.parseLong(temp);
    }


}
