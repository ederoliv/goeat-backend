package com.example.goeat_api.service;

import com.example.goeat_api.domain.Partner;
import com.example.goeat_api.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    public boolean loginPartner(String email, String password){
        Optional<Partner> partner = partnerRepository.findByEmailAndPassword(email, password);
        return partner.isPresent();
    }

}
