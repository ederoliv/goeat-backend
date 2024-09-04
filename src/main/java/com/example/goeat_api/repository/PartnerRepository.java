package com.example.goeat_api.repository;

import com.example.goeat_api.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, UUID> {
    Optional<Partner> findByEmailAndPassword(String email, String password);
    Optional<Partner> findByEmail(String email);
    Optional<Partner> findByCnpj(String cnpj);
}
