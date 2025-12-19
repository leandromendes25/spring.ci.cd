package com.example.praticando.spring.infrascturue.repository;

import com.example.praticando.spring.infrascturue.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
