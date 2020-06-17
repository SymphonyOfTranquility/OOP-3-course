package com.lab3.demo.service;

import com.lab3.demo.converter.ClientConverter;
import com.lab3.demo.dto.ClientDTO;
import com.lab3.demo.entity.Client;
import com.lab3.demo.service.data.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientControllerService {
    private final ClientService clientService;
    private final ClientConverter clientConverter;

    public List<ClientDTO> findByEmail(String email) {
        List<ClientDTO> clientDTOList = new ArrayList<>();
        Optional<Client> client = clientService.findUserByEmail(email);
        client.ifPresent(value -> clientDTOList.add(clientConverter.convertToDTO(client.get())));
        return clientDTOList;
    }

}
