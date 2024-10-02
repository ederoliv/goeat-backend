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

    public Client registerClient(Client client){
        Optional<Client> existingEmail = clientRepository.findByEmail(client.getEmail());
        Optional<Client> existingCpf = clientRepository.findByCpf(client.getCpf());

        if(existingEmail.isPresent()){
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if(existingCpf.isPresent()){
            throw new IllegalArgumentException("Cpf ja cadastrado");
        }
        return clientRepository.save(client);
    }

    public String getClientName(String email){
        Optional<Client> client = clientRepository.findByEmail(email);

        return client.get().getName();
    }

    public String getClientUUID(String email){
        Optional<Client> client = clientRepository.findByEmail(email);

        return client.get().getId().toString();
    }
}