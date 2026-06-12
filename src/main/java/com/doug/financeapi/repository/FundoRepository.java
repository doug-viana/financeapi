package com.doug.financeapi.repository;

import com.doug.financeapi.model.Fundo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FundoRepository extends JpaRepository<Fundo, UUID> {
}
