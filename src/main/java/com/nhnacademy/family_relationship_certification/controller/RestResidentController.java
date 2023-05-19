package com.nhnacademy.family_relationship_certification.controller;

import com.nhnacademy.family_relationship_certification.domain.ResidentId;
import com.nhnacademy.family_relationship_certification.domain.ResidentRegisterRequest;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import com.nhnacademy.family_relationship_certification.service.ResidentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/residents")
public class RestResidentController {

    private final ResidentService residentService;

    public RestResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

//    @PostMapping
//    public ResidentId registerResident(@RequestBody ResidentRegisterRequest request) {
//
//        return
//    }

}
