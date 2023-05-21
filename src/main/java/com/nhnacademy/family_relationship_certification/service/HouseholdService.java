package com.nhnacademy.family_relationship_certification.service;

import com.nhnacademy.family_relationship_certification.domain.HouseholdId;
import com.nhnacademy.family_relationship_certification.domain.restRequest.HouseholdRequest;
import com.nhnacademy.family_relationship_certification.domain.restRequest.MovementAddressRequest;
import com.nhnacademy.family_relationship_certification.entity.Household;
import com.nhnacademy.family_relationship_certification.entity.HouseholdMovementAddress;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import com.nhnacademy.family_relationship_certification.repository.HouseholdRepository;
import com.nhnacademy.family_relationship_certification.repository.MovementAddressRepository;
import com.nhnacademy.family_relationship_certification.repository.ResidentRepository;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;
    private final MovementAddressRepository movementAddressRepository;

    public HouseholdService(HouseholdRepository householdRepository, ResidentRepository residentRepository, MovementAddressRepository movementAddressRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
        this.movementAddressRepository = movementAddressRepository;
    }

    public HouseholdId addHousehold(HouseholdRequest request) {
        LocalDate date =LocalDate.parse(request.getHouseholdCompositionDate());
        Resident resident = residentRepository.findById(request.getResidentId()).get();
        Household household = Household.builder()
                .resident(resident)
                .householdCompositionDate(date)
                .householdCompositionReasonCode(request.getCompositionReasonCode())
                .currentHouseMovementAddress(request.getCurrentHouseMovementAddress())
                .build();
        householdRepository.saveAndFlush(household);
        return new HouseholdId(household.getHouseholdId());
    }

    public HouseholdId removeHousehold(Integer id) {
        if(!householdRepository.existsById(id)) {
            throw new NotFoundException("존재하지 않는 세대아이디 입니다.");
        }
        householdRepository.deleteById(id);
        return new HouseholdId(id);
    }

    public void addMovementHousehold(Integer householdId,
                                                       MovementAddressRequest request) {
        if(!householdRepository.existsById(householdId)) {
            throw new NotFoundException("존재하지 않는 세대아이디 입니다.");
        }
        LocalDate date = dateFormatter(request.getHouseMovementReportDate());
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(householdId,date);
        Household household = householdRepository.findById(householdId).get();

        HouseholdMovementAddress address = HouseholdMovementAddress.builder()
                .pk(pk)
                .household(household)
                .houseMovementAddress(request.getHouseMovementAddress())
                .lastAddressYN(request.getLastAddressYN())
                .build();
        movementAddressRepository.saveAndFlush(address);
    }

    public void updateMovement(Integer householdId,String reportDate,
                               MovementAddressRequest request) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(householdId,
                dateFormatter(reportDate));
        if (!movementAddressRepository.existsById(pk)) {
            throw new NotFoundException("존재하지 않는 세대 전입 주소 입니다.");
        }
        Household household = householdRepository.findById(householdId).get();

        HouseholdMovementAddress address = HouseholdMovementAddress.builder()
                .pk(pk)
                .household(household)
                .houseMovementAddress(request.getHouseMovementAddress())
                .lastAddressYN(request.getLastAddressYN())
                .build();
        movementAddressRepository.saveAndFlush(address);
    }

    public void removeMovement(Integer householdId,String reportDate) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(householdId,
                dateFormatter(reportDate));
        if (!movementAddressRepository.existsById(pk)) {
            throw new NotFoundException("존재하지 않는 세대 전입 주소 입니다.");
        }
        movementAddressRepository.deleteById(pk);
    }

    public LocalDate dateFormatter(String date) {
        String formatter = date.substring(0,4) + "-" +
                date.substring(4,6)+"-"+
                date.substring(6);
        return LocalDate.parse(formatter);
    }
}
