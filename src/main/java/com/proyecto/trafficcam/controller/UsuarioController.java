package com.proyecto.trafficcam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trafficcam.model.entity.Usuario;
import com.proyecto.trafficcam.model.response.LoginRequest;
import com.proyecto.trafficcam.service.UsuarioService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@AllArgsConstructor
@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/users")
	public List<Usuario> getUsers() {
		return usuarioService.getUsers();	
	}

	@GetMapping("/users/{id}")
	public Usuario getUserById(@PathVariable("id") long id) {
		return usuarioService.getUserById(id);
	}

    @PostMapping("/users")
    public void saveUser(@RequestBody Usuario usuario) {
        usuarioService.createUser(usuario);
    }
    
    @PutMapping("/users")
    public void updateUser(@RequestBody Usuario usuario) {
        usuarioService.updateUser(usuario);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        usuarioService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Usuario usuario = usuarioService.iniciarSesion(request.getName(), request.getPassword());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    
}
