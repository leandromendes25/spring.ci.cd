package com.example.praticando.spring.business;

import com.example.praticando.spring.infrascturue.entity.Usuario;
import com.example.praticando.spring.infrascturue.exceptions.ConflictException;
import com.example.praticando.spring.infrascturue.exceptions.ResourceNotFoundException;
import com.example.praticando.spring.infrascturue.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public Usuario salvarUsuario(Usuario usuario){
        try{
        emailExiste(usuario.getEmail());
       usuario.setSenha( passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);

        } catch (ConflictException e) {
        throw new ConflictException("Email já cadastrado", e.getCause());
        }

    }
    
    public void emailExiste(String email){
    boolean existe = verificaEmailExistente(email);
    try{
        if(existe){
            throw new ConflictException("Email já cadastrado: " + email);
        }
    }catch (ConflictException e){
        throw new ConflictException("Email já cadastrado:" + e.getCause());
    }

    }
    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }
    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado: " + email));
    }
    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}
