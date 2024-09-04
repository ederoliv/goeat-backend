package com.example.goeat_api.service;

import com.example.goeat_api.domain.Client;
import com.example.goeat_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public boolean loginClient(String email, String password){
        Optional<Client> client = clientRepository.findByEmailAndPassword(email, password);
        return client.isPresent();
    }
}
