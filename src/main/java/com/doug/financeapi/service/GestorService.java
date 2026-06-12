package com.doug.financeapi.service;

import com.doug.financeapi.dto.FundoResponseDTO;
import com.doug.financeapi.dto.GestorRequestDTO;
import com.doug.financeapi.dto.GestorResponseDTO;
import com.doug.financeapi.model.Gestor;
import com.doug.financeapi.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GestorService {

    @Autowired
    private GestorRepository gestorRepository;

    public GestorResponseDTO salvar(GestorRequestDTO dto) {
        if (gestorRepository.existsByCnpj(dto.cnpj())) {
            throw new IllegalArgumentException("Erro: Já existe um gestor cadastrado com este CNPJ!");
        }

        Gestor gestor = new Gestor();
        gestor.setNome(dto.nome());
        gestor.setCnpj(dto.cnpj());

        Gestor gestorSalvo = gestorRepository.save(gestor);

        return mapToResponse(gestorSalvo);
    }

    public List<GestorResponseDTO> listarTodos() {
        return gestorRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // --- NOVO: Método de Atualizar (PUT) ---
    public GestorResponseDTO atualizar(UUID id, GestorRequestDTO dto) {
        Gestor gestorExistente = gestorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado!"));

        if (gestorRepository.existsByCnpjAndIdNot(dto.cnpj(), id)) {
            throw new IllegalArgumentException("Erro: Este CNPJ já pertence a outro gestor!");
        }

        gestorExistente.setNome(dto.nome());
        gestorExistente.setCnpj(dto.cnpj());

        Gestor gestorAtualizado = gestorRepository.save(gestorExistente);
        return mapToResponse(gestorAtualizado);
    }

    // --- NOVO: Método de Deletar (DELETE) ---
    public void deletar(UUID id) {
        Gestor gestorExistente = gestorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado!"));

        gestorRepository.delete(gestorExistente);
    }

    // Método auxiliar para evitar repetição de código
    private GestorResponseDTO mapToResponse(Gestor gestor) {
        List<FundoResponseDTO> fundosDTO = java.util.Collections.emptyList();
        if (gestor.getFundos() != null) {
            fundosDTO = gestor.getFundos().stream()
                    .map(fundo -> new FundoResponseDTO(fundo.getId(), fundo.getNome(), fundo.getPatrimonioLiquido()))
                    .toList();
        }
        return new GestorResponseDTO(gestor.getId(), gestor.getNome(), gestor.getCnpj(), fundosDTO);
    }
}