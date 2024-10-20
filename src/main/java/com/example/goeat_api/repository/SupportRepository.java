package com.example.goeat_api.repository;

import com.example.goeat_api.entities.Product;
import com.example.goeat_api.entities.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupportRepository extends JpaRepository<Support, UUID> {
    Optional<Support> findByTitle(String title);
    Optional<List<Support>> findSupportByPartnerId(UUID partnerId);
}