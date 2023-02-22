package com.usuario.service.servicio;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usuario.service.repositorio.UsuarioRepositorio;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

    //Inicio RestTemplate
    public List<Carro> getCarros(Long usuarioId) {
        return restTemplate.getForObject("http://localhost:8082/carro/usuario/" + usuarioId, List.class);
    }

    public List<Moto> getMotos(Long usuarioId) {
        return restTemplate.getForObject("http://localhost:8083/moto/usuario/" + usuarioId, List.class);
    }
    //Fin RestTemplate

    //Inicio FeignClient
    public Carro saveCarro(Long usuarioId, Carro carro) {
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(Long usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(Long usuarioId) {
        Map<String,Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
        if (usuario == null) {
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }
        resultado.put("Usuario", usuario);

        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if (carros.isEmpty()) {
            resultado.put("Carros", "El usuario no tiene carros");
        } else {
            resultado.put("Carros", carros);
        }

        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if (motos.isEmpty()) {
            resultado.put("Motos", "El usuario no tiene motos");
        } else {
            resultado.put("Motos", motos);
        }
        return resultado;
    }
    //Fin FeignClient

    public List<Usuario> getAll() {
        return usuarioRepositorio.findAll();
    }

    public Usuario getUsuariosById(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }
}
