package com.nhnacademy.family_relationship_certification.controller.api;

import com.nhnacademy.family_relationship_certification.domain.restRequest.RelationRequest;
import com.nhnacademy.family_relationship_certification.domain.ResidentId;
import com.nhnacademy.family_relationship_certification.domain.restRequest.ResidentRegisterRequest;
import com.nhnacademy.family_relationship_certification.exception.ValidationFailedException;
import com.nhnacademy.family_relationship_certification.service.ResidentService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public void updateResident(@PathVariable Integer serialNumber,
                               @Valid @RequestBody ResidentRegisterRequest request,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        residentService.update(serialNumber,request);
    }

    @PostMapping("/{serialNumber}/relationship")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRelationship(@PathVariable Integer serialNumber,
                                @Valid @RequestBody RelationRequest request,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        residentService.addRelationship(serialNumber, request);
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public RelationRequest modifiedRelationship(@PathVariable Integer serialNumber,
                                                @PathVariable Integer familySerialNumber,
                                                @Valid @RequestBody RelationRequest request,
                                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        return residentService.modifiedRelationship(serialNumber,familySerialNumber,request);
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void removeRelationship(@PathVariable Integer serialNumber,
                                   @PathVariable Integer familySerialNumber) {
       residentService.removeRelationship(serialNumber,familySerialNumber);
    }
}
