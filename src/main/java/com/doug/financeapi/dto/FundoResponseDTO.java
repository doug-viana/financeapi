package com.doug.financeapi.dto;

import java.math.BigDecimal;
import java.util.UUID;

// A "Xerox" do Fundo: Só tem o que queremos mostrar na tela
public record FundoResponseDTO(
        UUID id,
        String nome,
        BigDecimal patrimonioLiquido
) {
}
