package com.doug.financeapi.repository;

import com.doug.financeapi.model.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface GestorRepository extends JpaRepository<Gestor, UUID> {

    boolean existsByCnpj(String cnpj);

    // NOVO: Verifica se o CNPJ existe, MAS ignorando o Gestor que estamos atualizando no momento
    boolean existsByCnpjAndIdNot(String cnpj, UUID id);
}