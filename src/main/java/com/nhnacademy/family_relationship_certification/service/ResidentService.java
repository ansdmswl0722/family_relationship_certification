package com.nhnacademy.family_relationship_certification.service;

import com.nhnacademy.family_relationship_certification.domain.ResidentId;
import com.nhnacademy.family_relationship_certification.domain.ResidentNameDto;
import com.nhnacademy.family_relationship_certification.domain.ResidentRegisterRequest;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import com.nhnacademy.family_relationship_certification.repository.ResidentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }
    public List<ResidentNameDto> getResidents(Pageable pageable){
        return residentRepository.getAllBy(pageable).getContent();
    }

    public ResidentId createResident(ResidentRegisterRequest request) {
        Resident resident = Resident
                .builder()
                .name(request.getName())
                .residentRegistrationNumber(request.getResidentRegistrationNumber())
                .genderCode(request.getGenderCode())
                .birthDate(request.getBirthDate())
                .birthPlaceCode(request.getBirthPlaceCode())
                .registrationBaseAddress(request.getRegistrationBaseAddress())
                .build();
        residentRepository.saveAndFlush(resident);
        return new ResidentId(resident.getResidentId());
    }

    public void update(Integer id,ResidentRegisterRequest request) {
        if(!residentRepository.existsById(id)){
            throw new NotFoundException("존재하지 않는 아이디 입니다.");
        }
        Resident resident = Resident
                .builder()
                .residentId(id)
                .name(request.getName())
                .residentRegistrationNumber(request.getResidentRegistrationNumber())
                .genderCode(request.getGenderCode())
                .birthDate(request.getBirthDate())
                .birthPlaceCode(request.getBirthPlaceCode())
                .registrationBaseAddress(request.getRegistrationBaseAddress())
                .deathDate(request.getDeathDate())
                .deathPlaceCode(request.getDeathPlaceCode())
                .deathPlaceAddress(request.getDeathPlaceAddress())
                .build();
        residentRepository.saveAndFlush(resident);


    }
}
