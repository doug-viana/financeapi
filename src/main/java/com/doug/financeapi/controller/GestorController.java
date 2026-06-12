package com.doug.financeapi.controller;

import com.doug.financeapi.dto.GestorRequestDTO;
import com.doug.financeapi.dto.GestorResponseDTO;
import com.doug.financeapi.service.GestorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gestores")
public class GestorController {

    @Autowired
    private GestorService service;

    @PostMapping
    public GestorResponseDTO criar(@Valid @RequestBody GestorRequestDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<GestorResponseDTO> listar() {
        return service.listarTodos();
    }

    // --- NOVO: Endpoint de Atualização ---
    @PutMapping("/{id}")
    public GestorResponseDTO atualizar(@PathVariable UUID id, @Valid @RequestBody GestorRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    // --- NOVO: Endpoint de Deleção ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna Status 204 (No Content) indicando sucesso sem corpo
    }
}