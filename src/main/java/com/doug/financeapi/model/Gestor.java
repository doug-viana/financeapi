package com.doug.financeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "tb_gestores")
public class Gestor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome do gestor é obrigatório!")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório!")
    @Column(unique = true)
    private String cnpj;

    // UM gestor tem MUITOS fundos.
    // mappedBy = "gestor" avisa que a regra de ligação já foi feita lá na classe Fundo.
    @OneToMany(mappedBy = "gestor")
    private java.util.List<Fundo> fundos;

    public Gestor() {}

    // Getters e Setters
    public UUID getId() {return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() {return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public java.util.List<Fundo> getFundos() { return fundos; }
    public void setFundos(java.util.List<Fundo> fundos) { this.fundos = fundos; }
}
