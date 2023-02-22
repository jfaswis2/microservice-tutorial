package com.carro.service.servicio;

import com.carro.service.entidades.Carro;
import com.carro.service.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAll() {
        return carroRepository.findAll();
    }

    public Carro getCarroById(Long id) {
        return carroRepository.findById(id).orElse(null);
    }

    public Carro save(Carro usuario) {
        return carroRepository.save(usuario);
    }

    public List<Carro> byUsuarioId(Long usuarioId) {
        return carroRepository.findByUsuarioId(usuarioId);
    }
}
