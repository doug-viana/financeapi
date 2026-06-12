package com.doug.financeapi.controller;

import com.doug.financeapi.dto.FundoRequestDTO;
import com.doug.financeapi.dto.FundoResponseDTO;
import com.doug.financeapi.service.FundoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/fundos")
public class FundoController {

    @Autowired
    private FundoService service;

    @PostMapping
    public FundoResponseDTO criar(@Valid @RequestBody FundoRequestDTO dto) {
        return service.salvar(dto);
    }

    // --- NOVO: Endpoint de Atualização ---
    @PutMapping("/{id}")
    public FundoResponseDTO atualizar(@PathVariable UUID id, @Valid @RequestBody FundoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    // --- NOVO: Endpoint de Deleção ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}