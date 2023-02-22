package com.usuario.service.feignclients;

import com.usuario.service.modelos.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carro-Service", url = "http://localhost:8082/")
@RequestMapping("/carro")
public interface CarroFeignClient {

    @PostMapping()
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable("usuarioId") Long usuarioId);
}
