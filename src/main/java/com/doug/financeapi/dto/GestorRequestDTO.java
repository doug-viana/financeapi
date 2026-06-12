package com.doug.financeapi.dto;

import jakarta.validation.constraints.NotBlank;

public record GestorRequestDTO(
        @NotBlank(message = "O nome é obrigatório!") String nome,
        @NotBlank(message = "O CNPJ é obrigatório!") String cnpj
) {
}