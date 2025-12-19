package com.example.praticando.spring.infrascturue.repository;

import com.example.praticando.spring.infrascturue.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone,Long> {
}
