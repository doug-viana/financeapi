package com.doug.financeapi.infra;

import com.doug.financeapi.dto.ErroPadraoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    // 1. Captura as nossas Regras de Negócio (Ex: Patrimônio Negativo, CNPJ Duplicado)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroPadraoDTO> tratarErroDeNegocio(IllegalArgumentException ex) {
        ErroPadraoDTO erro = new ErroPadraoDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // 2. Captura buscas de IDs que não existem no banco (Ex: Fundo não encontrado)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroPadraoDTO> tratarErroNaoEncontrado(RuntimeException ex) {
        ErroPadraoDTO erro = new ErroPadraoDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 3. Captura os erros de DTO vazio (@NotBlank, @NotNull)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadraoDTO> tratarErrosDeValidacao(MethodArgumentNotValidException ex) {
        // Pega todos os campos que deram erro e junta em uma frase só
        String camposInvalidos = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        ErroPadraoDTO erro = new ErroPadraoDTO("Dados inválidos -> " + camposInvalidos, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}