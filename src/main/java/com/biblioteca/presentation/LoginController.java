package com.biblioteca.presentation;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @Inject
    HttpServletRequest request;

    @Inject
    FacesContext facesContext;

    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void login() {
        try {
            request.login(usuario, senha);

            facesContext.getExternalContext()
                    .redirect(
                            facesContext.getExternalContext().getRequestContextPath()
                                    + "/index.xhtml"
                    );

        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, "Falha do login", e);

            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Credenciais inválidas",
                    "Usuário ou senha incorretos. Tente novamente."
            ));

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Falha de redirecionamento após o login", e);
        }
    }

    public boolean getIsLogged() {
        return request.getUserPrincipal() != null;
    }

    public String getUserPrincipal() {
        return request.getUserPrincipal() !=null ? request.getUserPrincipal().toString() : "null";
    }

    public String getUsername() {
        return request.getUserPrincipal() !=null ? request.getUserPrincipal().getName() : "null";
    }

    public boolean hasRole(String role) {
        return request.getUserPrincipal() != null && request.isUserInRole(role);
    }
}
