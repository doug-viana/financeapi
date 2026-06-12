package com.doug.financeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Fundo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "O nome do fundo é obrigatório!")
    private String nome;

    @NotNull(message = "O patrimônio líquido é obrigatório!")
    private BigDecimal patrimonioLiquido;

    // AQUI ESTÁ O CASAMENTO: Muitos fundos pertencem a UM gestor
    @ManyToOne
    @JoinColumn(name = "gestor_id", nullable = false)
    private Gestor gestor;

    // --- GETTERS E SETTERS ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getPatrimonioLiquido() { return patrimonioLiquido; }
    public void setPatrimonioLiquido(BigDecimal patrimonioLiquido) { this.patrimonioLiquido = patrimonioLiquido; }

    public Gestor getGestor() { return gestor; }
    public void setGestor(Gestor gestor) { this.gestor = gestor; }
}

