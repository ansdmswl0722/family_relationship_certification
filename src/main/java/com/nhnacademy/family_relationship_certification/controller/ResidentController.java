package com.nhnacademy.family_relationship_certification.controller;

import com.nhnacademy.family_relationship_certification.domain.CertificateIssueVO;
import com.nhnacademy.family_relationship_certification.domain.FamilyVO;
import com.nhnacademy.family_relationship_certification.domain.ResidentNameDto;
import com.nhnacademy.family_relationship_certification.service.CertificateIssueService;
import com.nhnacademy.family_relationship_certification.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
public class ResidentController {

    private final ResidentService residentService;
    private final CertificateIssueService certificateIssueService;

    public ResidentController(ResidentService residentService, CertificateIssueService certificateIssueService) {
        this.residentService = residentService;
        this.certificateIssueService = certificateIssueService;
    }
    @GetMapping("/")
    public String residents(Model model,@PageableDefault(size = 5) Pageable pageable){
        Page<ResidentNameDto> page = residentService.getResidents(pageable);
        model.addAttribute("page" ,page);
        model.addAttribute("pageable",pageable);
        return "index";
    }

    @GetMapping("/residents/{serialNumber}/relationship")
    public String familyRelationship(Model model, @PathVariable int serialNumber) {
        CertificateIssueVO certificateIssueVO = certificateIssueService.addCertificateIssuanceRecord(serialNumber);
        List<FamilyVO> family = residentService.selectRelationship(serialNumber);
        model.addAttribute("issue",certificateIssueVO);
        model.addAttribute("family",family);
        return "relationship";
    }

}
