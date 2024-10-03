package com.example.goeat_api.repository;

import com.example.goeat_api.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmailAndPassword(String email, String password);
    Optional<Client> findByEmail(String email);
    Optional<Client> findByCpf(String cpf);
}
