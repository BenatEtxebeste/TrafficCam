package com.proyecto.trafficcam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.trafficcam.model.entity.Incidencia;
import com.proyecto.trafficcam.model.entity.Usuario;
import com.proyecto.trafficcam.repository.IncidenciaRepository;
import com.proyecto.trafficcam.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Usuario> getUsers() {
        List<Usuario> users = new ArrayList<Usuario>();
        usuarioRepository.findAll().forEach(user -> users.add(user));;
        return users;
    }

    public Usuario getUserById(long id) {
        return usuarioRepository.findById(id).get();
    }

    public void createUser(Usuario user) {
        usuarioRepository.save(user);
    }

    public void deleteUser(long id) {
        usuarioRepository.deleteById(id);
    }

    public void updateUser(Usuario user){
        usuarioRepository.save(user);
    }
}
