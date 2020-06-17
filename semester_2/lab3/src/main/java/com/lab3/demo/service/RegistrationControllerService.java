package com.lab3.demo.service;

import com.lab3.demo.converter.ClientConverter;
import com.lab3.demo.dto.ClientDTO;
import com.lab3.demo.entity.Client;
import com.lab3.demo.service.data.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationControllerService {
    private final ClientConverter clientConverter;
    private final RegistrationService registrationService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ClientDTO save(ClientDTO clientDTO) {
        Client client = clientConverter.convertToEntity(clientDTO);
        ClientDTO savedClientDTO = clientConverter.convertToDTO(registrationService.save(client));
        applicationEventPublisher.publishEvent(savedClientDTO);
        return savedClientDTO;
    }

}
