package com.example.goeat_api.service;

import com.example.goeat_api.DTO.partner.PartnerLoginRequestDTO;
import com.example.goeat_api.DTO.partner.PartnerResponseDTO;
import com.example.goeat_api.entities.Partner;
import com.example.goeat_api.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerService {


    public final PartnerRepository partnerRepository;

    public PartnerResponseDTO getPartnerById(UUID partnerId) {

        Optional<Partner> partner = partnerRepository.findById(partnerId);

        if(partner.isPresent()) {

            PartnerResponseDTO responseDTO = new PartnerResponseDTO(partner.get().getId(), partner.get().getName());

            return responseDTO;
        }

        return null;

    }

    public List<PartnerResponseDTO> listAllPartners() {

        List<Partner> partners = partnerRepository.findAll();

        return partners.stream()
                .map(partner -> new PartnerResponseDTO(partner.getId(), partner.getName()))
                .collect(Collectors.toList());

    }

    public Optional<Partner> loginPartner(PartnerLoginRequestDTO request){
        Optional<Partner> partner = partnerRepository.findByEmailAndPassword(request.email(), request.password());
        if (partner.isPresent()){
            return partner;
        }else {
            return Optional.empty();
        }
    }

    public Partner registerPartner(Partner partner){
        Optional<Partner> existingEmail = partnerRepository.findByEmail(partner.getEmail());
        Optional<Partner> existingCnpj = partnerRepository.findByCnpj(partner.getCnpj());

        if(existingEmail.isPresent()){
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (existingCnpj.isPresent()){
            throw new IllegalArgumentException("Cnpj já está cadastrado");
        }
        return partnerRepository.save(partner);
    }

    public String getPartnerName(String email){
        Optional<Partner> partner = partnerRepository.findByEmail(email);

        return partner.get().getName();
    }

    public String getPartnerUUID(String email){
        Optional<Partner> partner = partnerRepository.findByEmail(email);

        return partner.get().getId().toString();
    }
}