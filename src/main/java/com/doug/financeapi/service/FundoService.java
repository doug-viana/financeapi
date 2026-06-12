package com.doug.financeapi.service;

import com.doug.financeapi.dto.FundoRequestDTO;
import com.doug.financeapi.dto.FundoResponseDTO;
import com.doug.financeapi.model.Fundo;
import com.doug.financeapi.model.Gestor;
import com.doug.financeapi.repository.FundoRepository;
import com.doug.financeapi.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class FundoService {

    @Autowired
    private FundoRepository fundoRepository;

    @Autowired
    private GestorRepository gestorRepository;

    public FundoResponseDTO salvar(FundoRequestDTO dto) {
        if (dto.patrimonioLiquido().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Erro: O patrimônio líquido não pode ser negativo!");
        }

        Gestor gestor = gestorRepository.findById(dto.gestorId())
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado!"));

        Fundo fundo = new Fundo();
        fundo.setNome(dto.nome());
        fundo.setPatrimonioLiquido(dto.patrimonioLiquido());
        fundo.setGestor(gestor);

        Fundo fundoSalvo = fundoRepository.save(fundo);
        return new FundoResponseDTO(fundoSalvo.getId(), fundoSalvo.getNome(), fundoSalvo.getPatrimonioLiquido());
    }

    // --- NOVO: Método de Atualizar (PUT) ---
    public FundoResponseDTO atualizar(UUID id, FundoRequestDTO dto) {
        if (dto.patrimonioLiquido().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Erro: O patrimônio líquido não pode ser negativo!");
        }

        Fundo fundoExistente = fundoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fundo não encontrado!"));

        Gestor gestor = gestorRepository.findById(dto.gestorId())
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado!"));

        fundoExistente.setNome(dto.nome());
        fundoExistente.setPatrimonioLiquido(dto.patrimonioLiquido());
        fundoExistente.setGestor(gestor);

        Fundo fundoAtualizado = fundoRepository.save(fundoExistente);
        return new FundoResponseDTO(fundoAtualizado.getId(), fundoAtualizado.getNome(), fundoAtualizado.getPatrimonioLiquido());
    }

    // --- NOVO: Método de Deletar (DELETE) ---
    public void deletar(UUID id) {
        Fundo fundoExistente = fundoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fundo não encontrado!"));
        fundoRepository.delete(fundoExistente);
    }
}