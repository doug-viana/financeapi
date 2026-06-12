package com.doug.financeapi.dto;

import java.util.UUID;
import java.util.List;

// A "Xerox" do Gestor: Leva os dados dele e uma lista com as "Xerox" dos Fundos
public record GestorResponseDTO(
        UUID id,
        String nome,
        String cnpj,
        List<FundoResponseDTO> fundos
) {
}
