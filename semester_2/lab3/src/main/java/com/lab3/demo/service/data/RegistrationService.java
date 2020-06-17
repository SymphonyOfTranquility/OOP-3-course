package com.lab3.demo.service.data;

import com.lab3.demo.entity.Client;
import com.lab3.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client currentClient) {
        Optional<Client> oldClient = clientRepository.findByEmail(currentClient.getEmail());
        return oldClient.orElseGet(()-> clientRepository.save(currentClient));
    }

}
