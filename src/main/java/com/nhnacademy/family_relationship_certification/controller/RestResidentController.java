package com.nhnacademy.family_relationship_certification.controller;

import com.nhnacademy.family_relationship_certification.domain.ResidentId;
import com.nhnacademy.family_relationship_certification.domain.ResidentRegisterRequest;
import com.nhnacademy.family_relationship_certification.exception.ValidationFailedException;
import com.nhnacademy.family_relationship_certification.service.ResidentService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/residents")
public class RestResidentController {

    private final ResidentService residentService;

    public RestResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public ResidentId registerResident(@Valid @RequestBody ResidentRegisterRequest request,
                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return residentService.createResident(request);

    }

    @PutMapping("/{serialNumber}")
    public void updateResident(@PathVariable Integer serialNumber,
                               @Valid @RequestBody ResidentRegisterRequest request,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        residentService.update(serialNumber,request);


    }
}
