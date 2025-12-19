package com.example.praticando.spring.controller;

import com.example.praticando.spring.business.UsuarioService;
import com.example.praticando.spring.controller.dtos.UserDTO;
import com.example.praticando.spring.infrascturue.entity.Usuario;
import com.example.praticando.spring.infrascturue.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(usuarioService.salvarUsuario(usuario));
    }
    @PostMapping("/login")
    public String login(@RequestBody UserDTO usuarioDTO){
        Authentication authentication = authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha()));
        return jwtUtil.generateToken(authentication.getName());
    }
    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
       return ResponseEntity.noContent().build();
    }
}
