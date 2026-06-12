package com.doug.financeapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record FundoRequestDTO(
        @NotBlank(message = "O nome do fundo é obrigatório!") String nome,
        @NotNull(message = "O patrimônio líquido é obrigatório!") BigDecimal patrimonioLiquido,
        @NotNull(message = "O ID do gestor é obrigatório!") UUID gestorId
) {
}
