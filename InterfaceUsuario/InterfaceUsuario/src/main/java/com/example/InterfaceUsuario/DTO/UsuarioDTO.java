package com.example.InterfaceUsuario.DTO;

import com.example.InterfaceUsuario.Entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {
    private Long id;
    private String nome;
    private String sobrenome;
    private String username;
    private String senha;
    private String CPF;
    private String email;
    private int dataDeNascimento;

    public Usuario toUsuario(){
        return new Usuario(
                this.id,
                this.nome,
                this.sobrenome,
                this.username,
                this.senha,
                this.CPF,
                this.email,
                this.dataDeNascimento
        );
    }
    public UsuarioDTO fromUsuario(@NotNull Usuario usuario){
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getCPF(),
                usuario.getEmail(),
                usuario.getDataDeNascimento()
        );
    }
}
