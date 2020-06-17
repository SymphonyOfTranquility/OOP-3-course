package com.lab3.demo.controller;

import com.lab3.demo.dto.ClientDTO;
import com.lab3.demo.entity.Client;
import com.lab3.demo.service.ClientControllerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ClientsController {
    private final ClientControllerService clientControllerService;

    @GetMapping(value = "/clients")
    public List<ClientDTO> findByEmail(@RequestParam String email) { return clientControllerService.findByEmail(email); }

}
