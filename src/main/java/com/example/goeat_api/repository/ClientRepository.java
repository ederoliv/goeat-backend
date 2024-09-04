package com.example.goeat_api.repository;

import com.example.goeat_api.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmailAndPassword(String email, String password);
}
