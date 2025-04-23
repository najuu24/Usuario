package com.example.InterfaceUsuario.Controller;

import com.example.InterfaceUsuario.DTO.UsuarioDTO;
import com.example.InterfaceUsuario.Entity.Usuario;
import com.example.InterfaceUsuario.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){
        Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.getById(id);
        if (usuarioDTOOptional.isPresent()){
            return ResponseEntity.ok(usuarioDTOOptional.get());
        } else {
                return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create (@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO usuarioDTOSave = usuarioService.create(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.updateUsuario(id, usuarioDTO);
        if (usuarioDTOOptional.isPresent()){
            return ResponseEntity.ok(usuarioDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (usuarioService.delete(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
