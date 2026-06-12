package com.doug.financeapi.dto;

public record ErroPadraoDTO(
        String mensagem,
        int status
) {
}