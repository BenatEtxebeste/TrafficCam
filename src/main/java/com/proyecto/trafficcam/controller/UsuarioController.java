package com.proyecto.trafficcam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trafficcam.model.entity.Usuario;
import com.proyecto.trafficcam.model.response.LoginRequest;
import com.proyecto.trafficcam.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Usuarios")
@AllArgsConstructor
@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/users")
    @Operation(summary = "Recoger usuarios", description = "Devuelve todos los usuarios existentes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuarios encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
	})
	public List<Usuario> getUsers() {
		List<Usuario> users = new ArrayList<Usuario>();
        usuarioRepository.findAll().forEach(user -> users.add(user));
        return users;
	}

	@GetMapping("/users/{id}")
    @Operation(summary = "Recoger usuario mediante id", description = "Devuelve el usuario con la id introducida")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
	})
	public Usuario getUserById(@PathVariable("id") long id) {
		return usuarioRepository.findById(id).get();
	}

    @PostMapping("/users")
    @Operation(summary = "Insertar usuario", description = "Inserta un nuevo usuario en la base de datos")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuario insertado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
	})
    public ResponseEntity<String> saveUser(@RequestBody LoginRequest request) {
        Optional<Usuario> existingUser = usuarioRepository.findByName(request.getName());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario con este nombre ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setName(request.getName());
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        usuario.setPassword(hashedPassword);
        usuario.setAdmin(false);
        usuarioRepository.save(usuario);

        enviarCorreoNotificacion(usuario);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario creado correctamente");
    }
    
    @PatchMapping("/users")
    @Operation(summary = "Modificar usuario", description = "Modifica el usuario con la id introducida")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuario modificado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
	})
    public void updateUser(@RequestBody Usuario usuario) {
        String hashedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        usuarioRepository.save(usuario);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina el usuario con la id introducida")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuario eliminado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
	})
    public void deleteUser(@PathVariable("id") long id) {
        usuarioRepository.deleteById(id);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Comprueba si el usuario con el nombre y la contraseña existe")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Inicio de sesión correcto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
	})
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Usuario usuario = usuarioRepository.findByName(request.getName())
                    .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));
    
            if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
                throw new RuntimeException("Usuario o contraseña incorrectos");
            }

            return ResponseEntity.ok(usuario);
    
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    private void enviarCorreoNotificacion(Usuario usuario) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo("trafficcamprueba@gmail.com"); 
            mensaje.setSubject("Nuevo usuario creado");
            mensaje.setText("El usuario " + usuario.getName() + " ha sido creado exitosamente.");

            mailSender.send(mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
    
}
