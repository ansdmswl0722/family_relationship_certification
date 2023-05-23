package com.nhnacademy.family_relationship_certification.controller.api;

import com.nhnacademy.family_relationship_certification.domain.HouseholdId;
import com.nhnacademy.family_relationship_certification.domain.restRequest.HouseholdRequest;
import com.nhnacademy.family_relationship_certification.domain.restRequest.MovementAddressRequest;
import com.nhnacademy.family_relationship_certification.exception.ValidationFailedException;
import com.nhnacademy.family_relationship_certification.service.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/household")
public class RestHouseholdController {

    private final HouseholdService householdService;

    public RestHouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public HouseholdId addHousehold(@Valid @RequestBody HouseholdRequest householdRequest,
                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return householdService.addHousehold(householdRequest);
    }

    @DeleteMapping("{/householdSerialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public HouseholdId removeHouseHold(@PathVariable Integer householdSerialNumber) {
        return householdService.removeHousehold(householdSerialNumber);
    }

    @PostMapping("/{householdSerialNumber}/movement")
    @ResponseStatus(HttpStatus.OK)
    public void addMovement(@PathVariable Integer householdSerialNumber,
                            @Valid @RequestBody MovementAddressRequest request,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        householdService.addMovementHousehold(householdSerialNumber,request);
    }

    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovement(@PathVariable Integer householdSerialNumber,
                               @PathVariable String reportDate,
                               @Valid @RequestBody MovementAddressRequest request,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        householdService.updateMovement(householdSerialNumber,reportDate,request);
    }

    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    @ResponseStatus(HttpStatus.OK)
    public void removeMovement(@PathVariable Integer householdSerialNumber,
                               @PathVariable String reportDate) {
        householdService.removeMovement(householdSerialNumber,reportDate);
    }


}
