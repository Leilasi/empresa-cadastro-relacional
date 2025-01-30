package com.cadastro.relacional.empresa.repository;

import com.cadastro.relacional.empresa.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
