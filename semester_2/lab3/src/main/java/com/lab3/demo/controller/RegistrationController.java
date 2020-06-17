package com.lab3.demo.controller;

import com.lab3.demo.dto.ClientDTO;
import com.lab3.demo.service.RegistrationControllerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationControllerService registrationControllerService;

    @PostMapping(value = "/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody ClientDTO clientDTO) {
        registrationControllerService.save(clientDTO);
        return ResponseEntity.accepted().build();
    }
}