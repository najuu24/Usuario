package com.example.InterfaceUsuario.Service;


import com.example.InterfaceUsuario.DTO.UsuarioDTO;
import com.example.InterfaceUsuario.Entity.Usuario;
import com.example.InterfaceUsuario.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. buscar todos os usuarios
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    // 2. buscar user pelo id
    public Optional<UsuarioDTO> getById(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            return Optional.of(usuarioDTO.fromUsuario(usuarioOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    // 3. cadastrar usuario com dados completos
    public UsuarioDTO create(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDTO.toUsuario();
        usuario = usuarioRepository.save(usuario);
        return usuarioDTO.fromUsuario(usuario);
    }

    // 4. atualizar dados do usuario (sem user e senha)
    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.setId(usuarioDTO.getId());
            usuario.setNome(usuarioDTO.getNome());
            usuario.setSobrenome(usuarioDTO.getSobrenome());
            usuario.setCPF(usuarioDTO.getCPF());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setDataDeNascimento(usuarioDTO.getDataDeNascimento());
            usuario = usuarioRepository.save(usuario);
            return Optional.of(usuarioDTO.fromUsuario(usuario));
        }else{
            return Optional.empty();
        }
    }

    // 5. deletar usuario
    public boolean delete(Long id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    // 6. atualizar senha
    public Optional<UsuarioDTO> updateUsuario2(Long id, UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.setSenha(usuarioDTO.getSenha());
            usuario = usuarioRepository.save(usuario);
            return Optional.of(usuarioDTO.fromUsuario(usuario));
        }else{
            return Optional.empty();
        }
    }

    //7 e 8. buscar usuarios por nome e cpf
    @GetMapping
    public List<Usuario> getAll(@RequestParam(required = false) String nome,
                                @RequestParam(required = false) String cpf){
        return usuarioRepository.findAll();
    }
}
