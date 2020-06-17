package com.lab3.demo.service.data;

import com.lab3.demo.entity.Client;
import com.lab3.demo.exception.ClientNotExists;
import com.lab3.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> findAll() { return clientRepository.findAll(); }

    public Optional<Client> findUserByEmail(String email) { return clientRepository.findByEmail(email); }

    public Client setDiscount(Long clientId, Long discountSize) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        Client client = clientOpt.orElseThrow(() -> new ClientNotExists("Client with id " + clientId + " not exists"));
        client.setDiscount(discountSize);
        return clientRepository.findById(clientId).orElseThrow(() -> new ClientNotExists("Client with id " + clientId + " not exists"));
    }
}
