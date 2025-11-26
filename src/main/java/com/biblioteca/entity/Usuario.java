package com.biblioteca.entity;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@UserDefinition
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "nome_usuario", nullable = false, unique = true, length = 30)
    @Username
    private String nomeUsuario;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min=6, message = "A senha deve ter no mínimo 6 caracteres")
    @Column(name="senha", nullable = false, length = 255)
    @Password
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="usuarios_roles",
            joinColumns = @JoinColumn(name="usuario_id"),
            indexes = @Index(name="idx_usuarios_roles", columnList = "usuario_id")
    )
    @Column(name="role", length = 50)
    @Roles
    private Set<String> roles = new HashSet<>();

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
