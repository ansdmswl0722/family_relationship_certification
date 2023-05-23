package com.nhnacademy.family_relationship_certification.controller.api;

import com.nhnacademy.family_relationship_certification.domain.restRequest.ReportRequest;
import com.nhnacademy.family_relationship_certification.exception.ValidationFailedException;
import com.nhnacademy.family_relationship_certification.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/residents")
public class RestReportController {

    private final ReportService reportService;

    public RestReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/{serialNumber}/birth")
    public void registerBirthReport(@PathVariable Integer serialNumber,
                                    @Valid @RequestBody ReportRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        reportService.registerBirthReport(serialNumber, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public void modifiedBirthReport(@PathVariable Integer serialNumber,
                                    @PathVariable Integer targetSerialNumber,
                                    @Valid @RequestBody ReportRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        reportService.modifiedBirthReport(serialNumber, targetSerialNumber, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public void removeBirthReport(@PathVariable Integer serialNumber,
                                  @PathVariable Integer targetSerialNumber) {
        reportService.removeBirthReport(serialNumber, targetSerialNumber);
    }

    @PostMapping("/{serialNumber}/death")
    public void registerDeathReport(@PathVariable Integer serialNumber,
                                    @Valid @RequestBody ReportRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        reportService.registerDeathReport(serialNumber, request);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{serialNumber}/death/{targetSerialNumber}")
    public void modifiedDeathReport(@PathVariable Integer serialNumber,
                                    @PathVariable Integer targetSerialNumber,
                                    @Valid @RequestBody ReportRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        reportService.modifiedDeathReport(serialNumber,targetSerialNumber, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
    public void removeDeathReport(@PathVariable Integer serialNumber,
                                  @PathVariable Integer targetSerialNumber) {
        reportService.removeDeathReport(serialNumber, targetSerialNumber);
    }



}


