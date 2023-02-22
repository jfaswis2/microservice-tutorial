package com.carro.service.repository;

import com.carro.service.entidades.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Long> {

    List<Carro> findByUsuarioId(Long usuarioId);
}
