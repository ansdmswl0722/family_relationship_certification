package com.nhnacademy.family_relationship_certification.service;

import com.nhnacademy.family_relationship_certification.domain.restRequest.RelationRequest;
import com.nhnacademy.family_relationship_certification.domain.ResidentId;
import com.nhnacademy.family_relationship_certification.domain.ResidentNameDto;
import com.nhnacademy.family_relationship_certification.domain.restRequest.ResidentRegisterRequest;
import com.nhnacademy.family_relationship_certification.entity.FamilyRelationship;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import com.nhnacademy.family_relationship_certification.repository.FamilyRelationshipRepository;
import com.nhnacademy.family_relationship_certification.repository.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final FamilyRelationshipRepository repository;

    public ResidentService(ResidentRepository residentRepository, FamilyRelationshipRepository repository) {
        this.residentRepository = residentRepository;
        this.repository = repository;
    }

    public Page<ResidentNameDto> getResidents(Pageable pageable) {
        return residentRepository.getAllBy(pageable);
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

    public void update(Integer id, ResidentRegisterRequest request) {
        if (!residentRepository.existsById(id)) {
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

    public void addRelationship(Integer id, RelationRequest request) {
        if (!residentRepository.existsById(id)) {
            throw new NotFoundException("존재하지 않는 아이디 입니다.");
        }
        if (!residentRepository.existsById(request.getFamilySerialNumber())) {
            throw new NotFoundException("존재하지 않는 가족 아이디 입니다.");
        }
        Resident resident = residentRepository.findById(id).get();

        Resident familyResident = residentRepository.findById(request.getFamilySerialNumber()).get();

        FamilyRelationship familyRelationship = FamilyRelationship
                .builder()
                .pk(FamilyRelationship.Pk.builder()
                        .baseResidentSerialNumber(id)
                        .familyResidentSerialNumber(request.getFamilySerialNumber())
                        .build())
                .familyRelationshipCode(request.getRelationship())
                .resident(resident)
                .familyResident(familyResident)
                .build();
        repository.saveAndFlush(familyRelationship);
    }

    public RelationRequest modifiedRelationship(Integer id, Integer familyId,
                                                RelationRequest request) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(id,familyId);
        if(!repository.existsById(pk)) {
            throw new NotFoundException("존재하지 않는 가족관계 입니다.");
        }
        Resident resident = residentRepository.findById(id).get();
        Resident familyResident = residentRepository.findById(familyId).get();

        FamilyRelationship familyRelationship = new FamilyRelationship(
                pk,resident,familyResident,request.getRelationship()
        );
        RelationRequest response = new RelationRequest(familyId,request.getRelationship());
        repository.saveAndFlush(familyRelationship);
        return response;
    }

    public void removeRelationship(Integer id, Integer familyId) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(id,familyId);
        if(!repository.existsById(pk)) {
            throw new NotFoundException("존재하지 않는 가족관계 입니다.");
        }
       repository.deleteById(pk);
    }
}
