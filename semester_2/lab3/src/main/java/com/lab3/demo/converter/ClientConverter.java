package com.lab3.demo.converter;

import com.lab3.demo.dto.ClientDTO;
import com.lab3.demo.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {
    public ClientDTO convertToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setName(client.getName());
        clientDTO.setSurname(client.getSurname());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setDiscount(client.getDiscount());

        return clientDTO;
    }

    public Client convertToEntity(ClientDTO clientDTO) {
        Client client = new Client();

        client.setName(clientDTO.getName());
        client.setSurname(clientDTO.getSurname());
        client.setEmail(clientDTO.getEmail());
        client.setDiscount(clientDTO.getDiscount());

        return client;
    }
}
