package com.example.goeat_api.service;

import com.example.goeat_api.DTO.client.ClientLoginRequestDTO;
import com.example.goeat_api.entities.Client;
import com.example.goeat_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public Optional<Client> loginClient(ClientLoginRequestDTO request){
        Optional<Client> client = clientRepository.findByEmailAndPassword(request.email(), request.password());
         if (client.isPresent()){
             return client;
         } else {
             return Optional.empty();
         }
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